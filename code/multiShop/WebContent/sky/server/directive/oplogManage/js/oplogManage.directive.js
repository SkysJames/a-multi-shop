angular.module('oplogManage',[])
.service('oplogManageService',function($http,$filter,$timeout){
	$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest';
	$http.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.put['X-Requested-With'] = 'XMLHttpRequest';
	
	
})
.directive('oplogManage',function(){
	return {
		restrict:'E',
		scope : {
			
		},
		templateUrl : $contextPath +"/sky/server/directive/oplogManage/template/oplogManage.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			//日志查询条件
			$scope.condition = {
					pageNo		: 1,		//当前页码
					pageSize		: 20,	//每页数据量
					totalCount	: 0,		//数据总量
					pageCount	: 1,		//总页码数
					keywords		: "",
					opTimeA		: "",
					opTimeZ		: "",
					opType		: "",
			};
			//日期对象
			$scope.rangeDate = {
					startDate	: moment(thisToday+" 00:00:00"),	//开始时间
					endDate		: moment(thisToday+" 23:59:59"),	//结束时间
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
						$scope.getOplogList();
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
			 * 获取访客列表
			 */
			$scope.getOplogList = function(pageNo){
				$scope.condition.opTimeA = $scope.rangeDate.startDate.format(DateUtil.timeFormat);
				$scope.condition.opTimeZ = $scope.rangeDate.endDate.format(DateUtil.timeFormat);
				
				if(pageNo){
					$scope.condition.pageNo = pageNo;
				}else{
					$scope.condition.pageNo = 1;
				}
				
				$scope.isLoadingOplog = true;
				serverIndexHttpService.getOplogList($scope.condition)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.pager){
						$scope.oplogList = data.pager.resultList;
						$scope.condition.totalCount = data.pager.totalCount;
						$scope.condition.pageCount = data.pager.pageCount;
						$scope.isLoadingOplog = false;
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 初始化日志类型列表
			 */
			$scope.initOpTypeList = function(){
				//日志类型列表
				$scope.opTypeList = [];
				for(key in $opTypeMap){
					$scope.opTypeList.push({
						key		: key,
						value	: $opTypeMap[key],
					});
				}
			};
			
			/**
			 * 初始化判断是否有该面板权限
			 */
			$scope.initHasRight = function(){
				if($scope.$root.currentUser.allRights.indexOf('oplog_manage') < 0){
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
				//初始化日志类型列表
				$scope.initOpTypeList();
				//获取日志列表
				$scope.getOplogList();
			};
			$document.ready($scope.initFunc);
		}
	};
});