angular.module('shopListManageSave',[])
.directive('shopListManageSave',function(){
	return {
		restrict:'E',
		scope : {
			shop			: "=",
		},
		templateUrl : $contextPath +"/sky/server/module/shopManage/template/shopListManageSave.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			/**
			 * 新增保存店铺
			 */
			$scope.saveShop = function(shop){
				if(!$scope.isSaveShop(shop)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveShop(shop)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedShopList();
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
			 * 编辑保存店铺
			 */
			$scope.editShop = function(shop){
				if(!$scope.isSaveShop(shop)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editShop(shop)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedShopList();
						$scope.$parent.togglePanel(null);
						
						//初始化待审批店铺的数量
						if(angular.element($('.server-index')).scope()){
							angular.element($('.server-index')).scope().initShopCount();
						}
					}else{
						common.triggerFailMesg(data.message);
					}
					$scope.isLoadingSave = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 判断该店铺对象是否符合保存的条件
			 */
			$scope.isSaveShop = function(shop){
				if(!shop){
					return false;
				}
				
				//塞入过期时间
				shop.overTimeString = $("#overTimeId").val();
				
				if(!shop.name || shop.name==""){
					return false;
				}
				if(!shop.shopType || shop.shopType==""){
					return false;
				}
				if(null==shop.status || undefined==shop.status){
					return false;
				}
				if(null==shop.recommend || undefined==shop.recommend){
					return false;
				}
				if(!shop.overTimeString || shop.overTimeString==""){
					return false;
				}
				
				return true;
			};
			
		}
	};
});