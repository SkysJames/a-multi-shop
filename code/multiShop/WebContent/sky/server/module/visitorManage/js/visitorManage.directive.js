angular.module('visitorManage',[])
.service('visitorManageService',function($http,$filter,$timeout){
	$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest';
	$http.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.put['X-Requested-With'] = 'XMLHttpRequest';
	
	
})
.directive('visitorManage',function(){
	return {
		restrict:'E',
		scope : {
			
		},
		templateUrl : $contextPath +"/sky/server/directive/visitorManage/template/visitorManage.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			//当前面板
			$scope.$root.currentPanel = $scope.$root.panelContants.INDEX_PANEL;
			//保存的访客对象
			$scope.visitorSave = {};
			//访客查询条件
			$scope.condition = {
					pageNo		: 1,		//当前页码
					pageSize		: 10,	//每页数据量
					totalCount	: 0,		//数据总量
					pageCount	: 1,		//总页码数
					keywords		: "",
					visitedTimeA	: "",
					visitedTimeZ	: "",
					status		: common.visitorContants.status.USING,
			};
			//日期对象
			$scope.rangeDate = {
					startDate	: null,	//开始时间
					endDate		: null,	//结束时间
			};
			//筛选条件的日期选项
			$scope.dateRangePickerOptions = defaultDateRangePickerOptions;
			$scope.dateRangePickerOptions.eventHandlers = {
				'hide.daterangepicker' : function(event, picker){
					if($scope.rangeDate.startDate==null || $scope.rangeDate.endDate==null){
						$("#searchDateRangeId").val("");
					}
				},
				'apply.daterangepicker' : function(event, picker){
					$timeout(function(){
						$scope.rangeDate.startDate = picker.startDate;
						$scope.rangeDate.endDate = picker.endDate;
						if($scope.rangeDate.startDate==null || $scope.rangeDate.endDate==null){
							common.triggerFailMesg('请选择搜索的时间段!','');
							return;
						}
						$scope.getVisitorList();
					}, 100);
				},
			};
			
			/**
			 * 显示日期选择器
			 */
			$scope.showSearchDateRange = function(){
				$("#searchDateRangeId").data("daterangepicker").show();
			};
			
			/**
			 * 切换面板
			 */
			$scope.triggerPanel = function(panelContant, selectedObj){
				//顶部标题的切换
				$scope.$root.triggerTitle(panelContant);
				
				switch(panelContant){
				case $scope.$root.panelContants.INDEX_PANEL:
					$scope.getVisitorList();
					break;
				case $scope.$root.panelContants.EDIT_PANEL:
					$scope.visitorSave = _.cloneDeep(selectedObj);
					break;
				}
			};
			
			/**
			 * 清空查询条件-日期
			 */
			$scope.clearDate = function(){
				$scope.rangeDate.startDate = null;
				$scope.rangeDate.endDate = null;
				
				$scope.getVisitorList();
			};
			
			/**
			 * 封装查询条件
			 */
			$scope.packageCondition = function(pageNo){
				//页码
				if(pageNo){
					$scope.condition.pageNo = pageNo;
				}else{
					$scope.condition.pageNo = 1;
				}
				
				//更新开始结束时间
				if($scope.rangeDate.startDate && $scope.rangeDate.endDate){
					$scope.condition.visitedTimeA = $scope.rangeDate.startDate.format(DateUtil.timeFormat);
					$scope.condition.visitedTimeZ = $scope.rangeDate.endDate.format(DateUtil.timeFormat);
				}else{
					$scope.condition.visitedTimeA = "";
					$scope.condition.visitedTimeZ = "";
				}
			};
			
			/**
			 * 获取访客列表
			 */
			$scope.getVisitorList = function(pageNo){
				$scope.packageCondition(pageNo);
				
				$scope.isLoadingVisitor = true;
				serverIndexHttpService.getVisitorList($scope.condition)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.pager){
						$scope.visitorList = data.pager.resultList;
						$scope.condition.totalCount = data.pager.totalCount;
						$scope.condition.pageCount = data.pager.pageCount;
						$scope.isLoadingVisitor = false;
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 删除访客
			 */
			$scope.deleteVisitor = function(visitor){
				common.triggerAlertMesg("确定要删除访客 " + visitor.ip + "？", "", function(){}, function(){
					serverIndexHttpService.deleteVisitor(visitor)
					.then(function(response){
						var data = response.data;
						if(data && data.statusCode=="200"){
							common.triggerSuccessMesg(data.message);
							$scope.getVisitorList();
						}else{
							common.triggerFailMesg(data.message);
						}
					},function(err){
						console.log(err);
					});
				}, $scope);
			};
			
			/**
			 * 初始化判断是否有该面板权限
			 */
			$scope.initHasRight = function(){
				if($scope.$root.currentUser.allRights.indexOf('visitor_manage') < 0){
					common.triggerFailMesg("该用户无此权限模块");
					$scope.$root.showDetailPanel($scope.$root.navObj.index);
				}
			};
			
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//初始化权限
				$scope.initHasRight();
				//获取访客列表
				$scope.getVisitorList();
			};
			$document.ready($scope.initFunc);
		}
	};
});