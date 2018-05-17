angular.module('departmentManage',[])
.directive('departmentManage',function(){
	return {
		restrict:'E',
		scope : {
		},
		templateUrl : $contextPath +"/sky/server/directive/userManage/template/department/departmentManage.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			//保存的部门对象
			$scope.departmentSave = {};
			//部门查询条件
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
				case $scope.$root.panelContants.DEPARTMENT_INDEX:
					$scope.getDepartmentList();
					break;
				case $scope.$root.panelContants.DEPARTMENT_SAVE:
					$scope.departmentSave = {};
					break;
				case $scope.$root.panelContants.DEPARTMENT_EDIT:
					$scope.departmentSave = _.cloneDeep(selectedObj);
					break;
				}
			};
			
			/**
			 * 获取部门列表
			 */
			$scope.getDepartmentList = function(pageNo){
				if(pageNo){
					$scope.condition.pageNo = pageNo;
				}else{
					$scope.condition.pageNo = 1;
				}
				
				$scope.isLoadingDepartment = true;
				serverIndexHttpService.getDepartmentList($scope.condition)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.pager){
						$scope.departmentList = data.pager.resultList;
						
						$scope.condition.totalCount = data.pager.totalCount;
						$scope.condition.pageCount = data.pager.pageCount;
						$scope.isLoadingDepartment = false;
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 删除部门
			 */
			$scope.deleteDepartment = function(department){
				common.triggerAlertMesg("确定要删除部门 " + department.name + "？", "", function(){}, function(){
					serverIndexHttpService.deleteDepartment(department)
					.then(function(response){
						var data = response.data;
						if(data && data.statusCode=="200"){
							common.triggerSuccessMesg(data.message);
							$scope.getDepartmentList();
						}else{
							common.triggerFailMesg(data.message);
						}
					},function(err){
						console.log(err);
					});
				}, $scope);
			};
			
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//获取部门列表
				$scope.getDepartmentList();
			};
			$document.ready($scope.initFunc);
		}
	};
});