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
				
				//若当前用户的店铺ID不为system，则待保存的用户店铺为当前用户的店铺
				if(common.shopContants.shopSystem!=$currentUser.shopId){
					user.shopId = $currentUser.shopId;
					
					if(!user.rightgroups || user.rightgroups==""){
						user.rightgroups = common.rightGroupContants.salesclerkRightgroup;
					}
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
				if(!user.userStatus || user.userStatus==""){
					return false;
				}
				
				return true;
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
				if($scope.$parent.isAdminRight){
					//获取所有角色
					$scope.getAllRightGroupList();
				}
			};
			$document.ready($scope.initFunc);
			
		}
	};
});