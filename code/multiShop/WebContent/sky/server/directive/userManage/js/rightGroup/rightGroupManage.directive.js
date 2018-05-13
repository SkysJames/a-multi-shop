angular.module('rightGroupManage',[])
.directive('rightGroupManage',function(){
	return {
		restrict:'E',
		scope : {
		},
		templateUrl : $contextPath +"/sky/server/directive/userManage/template/rightGroup/rightGroupManage.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			//保存的角色对象
			$scope.rightGroupSave = {};
			//角色查询条件
			$scope.condition = {
					pageNo		: 1,		//当前页码
					pageSize		: 10,	//每页数据量
					totalCount	: 0,		//数据总量
					pageCount	: 1,		//总页码数
					keywords		: "",
			};
			
			/**
			 * 切换面板
			 */
			$scope.triggerPanel = function(panelContant, selectedObj){
				//顶部标题的切换
				$scope.$root.triggerTitle(panelContant);
				
				switch(panelContant){
				case $scope.$root.panelContants.RIGHT_GROUP_INDEX:
					$scope.getRightGroupList();
					break;
				case $scope.$root.panelContants.RIGHT_GROUP_SAVE:
					$scope.rightGroupSave = {};
					$scope.typeRightList = common.checkedTypeRightList($scope.typeRightList);
					break;
				case $scope.$root.panelContants.RIGHT_GROUP_EDIT:
					$scope.rightGroupSave = _.cloneDeep(selectedObj);
					$scope.typeRightList = common.checkedTypeRightList($scope.typeRightList, $scope.rightGroupSave.rights);
					break;
				}
			};
			
			/**
			 * 获取角色列表
			 */
			$scope.getRightGroupList = function(pageNo){
				if(pageNo){
					$scope.condition.pageNo = pageNo;
				}else{
					$scope.condition.pageNo = 1;
				}
				
				$scope.isLoadingRightGroup = true;
				serverIndexHttpService.getRightGroupList($scope.condition)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.pager){
						$scope.rightGroupList = data.pager.resultList;
						
						$scope.condition.totalCount = data.pager.totalCount;
						$scope.condition.pageCount = data.pager.pageCount;
						$scope.isLoadingRightGroup = false;
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 删除角色
			 */
			$scope.deleteRightGroup = function(rightGroup){
				common.triggerAlertMesg("确定要删除角色 " + rightGroup.name + "？", "", function(){}, function(){
					serverIndexHttpService.deleteRightGroup(rightGroup)
					.then(function(response){
						var data = response.data;
						if(data && data.statusCode=="200"){
							common.triggerSuccessMesg(data.message);
							$scope.getRightGroupList();
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
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//获取角色列表
				$scope.getRightGroupList();
				//获取所有权限列表（已经分好类）
				$scope.getTypeRightList();
			};
			$document.ready($scope.initFunc);
		}
	};
});