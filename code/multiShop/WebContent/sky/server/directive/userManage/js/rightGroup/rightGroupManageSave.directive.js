angular.module('rightGroupManageSave',[])
.directive('rightGroupManageSave',function(){
	return {
		restrict:'E',
		scope : {
			rightGroup			: "=",
			typeRightList		: "=",
			getRightGroupList	: "&",
		},
		templateUrl : $contextPath +"/sky/server/directive/userManage/template/rightGroup/rightGroupManageSave.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			/**
			 * 新增保存角色
			 */
			$scope.saveRightGroup = function(rightGroup){
				if(!$scope.isSaveRightGroup(rightGroup)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				//获取已勾选的权限
				rightGroup.rights = common.getRightsByTypeRightList($scope.typeRightList);
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveRightGroup(rightGroup)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.getRightGroupList();
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
			 * 编辑保存角色
			 */
			$scope.editRightGroup = function(rightGroup){
				if(!$scope.isSaveRightGroup(rightGroup)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				//获取已勾选的权限
				rightGroup.rights = common.getRightsByTypeRightList($scope.typeRightList);
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editRightGroup(rightGroup)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.getRightGroupList();
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
			 * 判断该角色对象是否符合保存的条件
			 */
			$scope.isSaveRightGroup = function(rightGroup){
				if(!rightGroup){
					return false;
				}
				
				if(!rightGroup.name || rightGroup.name==""){
					return false;
				}
				
				return true;
			};
			
		}
	};
});