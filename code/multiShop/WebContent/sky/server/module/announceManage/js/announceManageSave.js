angular.module('announceManageSave',[])
.directive('announceManageSave',function(){
	return {
		restrict:'E',
		scope : {
			announce			: "=",
		},
		templateUrl : $contextPath +"/sky/server/module/announceManage/template/announceManageSave.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			/**
			 * 新增保存用户
			 */
			$scope.saveAnnounce = function(announce){
				if(!$scope.isSaveAnnounce(announce)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveAnnounce(announce)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedAnnounceList();
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
			$scope.editAnnounce = function(announce){
				if(!$scope.isSaveAnnounce(announce)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editAnnounce(announce)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedAnnounceList();
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
			$scope.isSaveAnnounce = function(announce){
				if(!announce){
					return false;
				}
				
				//塞入店铺ID
				if(!$scope.$parent.isAdminRight){
					announce.shopId = $currentUser.shopId;
				}
				
				//塞入过期时间
				announce.overTimeString = $("#overTimeId").val();
				
				if(!announce.name || announce.name==""){
					return false;
				}
				if(!announce.shopId || announce.shopId==""){
					return false;
				}
				if(!announce.status || announce.status==""){
					return false;
				}
				
				return true;
			};
			
		}
	};
});