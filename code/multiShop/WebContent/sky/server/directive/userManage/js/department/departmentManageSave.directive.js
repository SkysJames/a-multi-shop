angular.module('departmentManageSave',[])
.directive('departmentManageSave',function(){
	return {
		restrict:'E',
		scope : {
			department			: "=",
			getDepartmentList	: "&",
		},
		templateUrl : $contextPath +"/sky/server/directive/userManage/template/department/departmentManageSave.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			/**
			 * 新增保存部门
			 */
			$scope.saveDepartment = function(department){
				if(!$scope.isSaveDepartment(department)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveDepartment(department)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.getDepartmentList();
						$scope.$root.returnPanel();
					}else{
						common.triggerFailMesg(data.message);
					}
					$scope.isLoadingSave = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 编辑保存部门
			 */
			$scope.editDepartment = function(department){
				if(!$scope.isSaveDepartment(department)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editDepartment(department)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.getDepartmentList();
						$scope.$root.returnPanel();
					}else{
						common.triggerFailMesg(data.message);
					}
					$scope.isLoadingSave = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 判断该部门对象是否符合保存的条件
			 */
			$scope.isSaveDepartment = function(department){
				if(!department){
					return false;
				}
				
				if(!department.name || department.name==""){
					return false;
				}
				
				return true;
			};
		}
	};
});