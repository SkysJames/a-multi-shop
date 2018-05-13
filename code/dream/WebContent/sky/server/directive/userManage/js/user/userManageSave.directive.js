angular.module('userManageSave',[])
.directive('userManageSave',function(){
	return {
		restrict:'E',
		scope : {
			user				: "=",
			typeRightList	: "=",
			getUserList		: "&",
		},
		templateUrl : $contextPath +"/sky/server/directive/userManage/template/user/userManageSave.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			/**
			 * 新增保存用户
			 */
			$scope.saveUser = function(user){
				if(!$scope.isSaveUser(user)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				user = $scope.packageSaveUser(user);
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveUser(user)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.getUserList();
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
			 * 编辑保存用户
			 */
			$scope.editUser = function(user){
				if(!$scope.isSaveUser(user)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				user = $scope.packageSaveUser(user);
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editUser(user)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.getUserList();
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
			 * 判断该用户对象是否符合保存的条件
			 */
			$scope.isSaveUser = function(user){
				if(!user){
					return false;
				}
				
				if(!user.id || user.id==""){
					return false;
				}
				
				if(!user.name || user.name==""){
					return false;
				}
				
				if(!user.passwd || user.passwd==""){
					return false;
				}
				
				if(!user.departmentId || user.departmentId==""){
					return false;
				}
				
				if(!user.userStatus){
					return false;
				}
				
				return true;
			};
			
			/**
			 * 封装保存的用户对象
			 */
			$scope.packageSaveUser = function(user){
				if(user){
					user.rights = common.getRightsByTypeRightList($scope.typeRightList);
					user.rightgroups = common.packetListToStr(user.rightGroupIdList);
				}
				return user;
			};
			
			/**
			 * 获取所有部门
			 */
			$scope.getAllDepartmentList = function(){
				serverIndexHttpService.getAllDepartmentList()
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.departmentAll){
						$scope.departmentAll = data.departmentAll;
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 获取所有角色
			 */
			$scope.getAllRightGroupList = function(){
				serverIndexHttpService.getAllRightGroupList()
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.rightGroupAll){
						$scope.rightGroupAll = data.rightGroupAll;
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
				//获取所有部门
				$scope.getAllDepartmentList();
				//获取所有角色
				$scope.getAllRightGroupList();
			};
			$document.ready($scope.initFunc);
		}
	};
});