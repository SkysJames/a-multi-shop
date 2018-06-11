angular.module('productIndexApp',
		["client-index.filter","client-index.httpService"]
		.concat($commonDirectiveList).concat($directiveList)
)
.controller("productIndexCtrl",['$timeout', '$scope', '$document', 'clientIndexHttpService', 
function($timeout, $scope, $document, clientIndexHttpService){
	//商品信息
	$scope.productInfo = {};
	
	
	/**
	 * 回到主页
	 */
	$scope.toIndexPage = function(){
		window.location.href = $contextPath;
	};
	
	/**
	 * 回到上一页
	 */
	$scope.toBackPage = function(){
		window.history.go(-1);
	};
	
	/**
	 * 当前页面跳到指定位置
	 */
	$scope.scrollTo = function(target){
		common.scrollTo(target);
	};
	
	/**
	 * 获取商品信息
	 */
	$scope.getProductInfo = function(productId){
		if(!productId || productId==""){
			console.log("传入的商品id为空");
			return;
		}
		
		clientIndexHttpService.getProductById(productId)
		.then(function(response){
			$scope.productInfo = response.data.product;
			
			if($scope.productInfo){
				$("title").text($scope.productInfo.shopName);
				$scope.getShopInfo($scope.productInfo.shopId);
				clientIndexHttpService.addProClickCount($scope.productInfo.id);
			}else{
				common.triggerFailMesg("该商品已不存在");
			}
		});
	};
	
	/**
	 * 获取店铺信息
	 */
	$scope.getShopInfo = function(shopId){
		if(!shopId || shopId==""){
			console.log("传入的店铺id为空");
			return;
		}
		
		clientIndexHttpService.getShopById(shopId)
		.then(function(response){
			$scope.shopInfo = response.data.shop;
			
			if(!$scope.shopInfo){
				common.triggerFailMesg("该店铺已不存在");
			}
		});
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//获取商品信息
		$scope.getProductInfo(productId);
	};
	$document.ready($scope.initFunc);
	
}]);