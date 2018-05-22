angular.module('userManageSave',[])
.directive('userManageSave',function(){
	return {
		restrict:'E',
		scope : {
			user				: "=",
		},
		templateUrl : $contextPath +"/sky/server/module/userManage/template/userManageSave.html",
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
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveUser(user)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedUserList();
						$scope.$parent.togglePanel(null);
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
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editUser(user)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedUserList();
						$scope.$parent.togglePanel(null);
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
				
				return true;
			};
			
			/**
			 * 获取所有店铺
			 */
			$scope.getAllShopList = function(){
				serverIndexHttpService.getAllShopList()
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.shopAll){
						$scope.shopAll = data.shopAll;
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
			 * 初始化当前登陆用户的权限
			 */
			$scope.initCurrentRight = function(){
				//是否为管理员权限
				$scope.isAdminRight = false;
				if($currentUser.rightgroups && $currentUser.rightgroups.indexOf(common.rightGroupContants.adminRightgroup)>-1){
					$scope.isAdminRight = true;
				}
			};
			
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//初始化当前登陆用户的权限
				$scope.initCurrentRight();
				//获取所有角色
				$scope.getAllRightGroupList();
				//获取所有店铺
				$scope.getAllShopList();
			};
			$document.ready($scope.initFunc);
		}
	};
});