angular.module('userManage',[])
.service('userManageService',function($http,$filter,$timeout){
	$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest';
	$http.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.put['X-Requested-With'] = 'XMLHttpRequest';
	
	/**
	 * 用户状态列表
	 * @returns
	 */
	this.userStatusList = [
		{
			status		: common.userContants.userStatus.USING,
			statusName	: "启用",
		},
		{
			status		: common.userContants.userStatus.UNUSING,
			statusName	: "禁用",
		},
	];
	
})
.directive('userManage',function(){
	return {
		restrict:'E',
		scope : {
			
		},
		templateUrl : $contextPath +"/sky/server/directive/userManage/template/user/userManage.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService, userManageService){
			//当前面板
			$scope.$root.currentPanel = $scope.$root.panelContants.INDEX_PANEL;
			//用户状态对象列表
			$scope.userStatusList = userManageService.userStatusList;
			//保存的用户对象
			$scope.userSave = {};
			//用户查询条件
			$scope.condition = {
					pageNo		: 1,		//当前页码
					pageSize		: 10,	//每页数据量
					totalCount	: 0,		//数据总量
					pageCount	: 1,		//总页码数
					keywords		: "",
					loginTimeA	: "",
					loginTimeZ	: "",
					userStatus	: common.userContants.userStatus.USING,
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
						$scope.getUserList();
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
					$scope.getUserList();
					break;
				case $scope.$root.panelContants.SAVE_PANEL:
					$scope.userSave = {};
					$scope.typeRightList = common.checkedTypeRightList($scope.typeRightList);
					break;
				case $scope.$root.panelContants.EDIT_PANEL:
					$scope.userSave = _.cloneDeep(selectedObj);
					$scope.userSave.rightGroupIdList = common.packetStrToList($scope.userSave.rightgroups);
					$scope.typeRightList = common.checkedTypeRightList($scope.typeRightList, $scope.userSave.rights);
					break;
				}
			};
			
			/**
			 * 清空查询条件-日期
			 */
			$scope.clearDate = function(){
				$scope.rangeDate.startDate = null;
				$scope.rangeDate.endDate = null;
				
				$scope.getUserList();
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
					$scope.condition.loginTimeA = $scope.rangeDate.startDate.format(DateUtil.timeFormat);
					$scope.condition.loginTimeZ = $scope.rangeDate.endDate.format(DateUtil.timeFormat);
				}else{
					$scope.condition.loginTimeA = "";
					$scope.condition.loginTimeZ = "";
				}
			};
			
			/**
			 * 获取用户列表
			 */
			$scope.getUserList = function(pageNo){
				$scope.packageCondition(pageNo);
				
				$scope.isLoadingUser = true;
				serverIndexHttpService.getUserList($scope.condition)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.pager){
						$scope.userList = data.pager.resultList;
						
						$scope.condition.totalCount = data.pager.totalCount;
						$scope.condition.pageCount = data.pager.pageCount;
						$scope.isLoadingUser = false;
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 删除用户
			 */
			$scope.deleteUser = function(user){
				common.triggerAlertMesg("确定要删除用户 " + user.name + "？", "", function(){}, function(){
					serverIndexHttpService.deleteUser(user)
					.then(function(response){
						var data = response.data;
						if(data && data.statusCode=="200"){
							common.triggerSuccessMesg(data.message);
							$scope.getUserList();
						}else{
							common.triggerFailMesg(data.message);
						}
					},function(err){
						console.log(err);
					});
				}, $scope);
			};
			
			/**
			 * 获取所有权限列表（已经分好类）
			 */
			$scope.getTypeRightList = function(){
				serverIndexHttpService.getTypeRightList()
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						$scope.typeRightList = data.typeRightList;
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 初始化判断是否有该面板权限
			 */
			$scope.initHasRight = function(){
				if($scope.$root.currentUser.allRights.indexOf('user_manage') < 0){
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
				//获取用户列表
				$scope.getUserList();
				//获取所有权限列表（已经分好类）
				$scope.getTypeRightList();
			};
			$document.ready($scope.initFunc);
		}
	};
});