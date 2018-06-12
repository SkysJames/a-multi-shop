angular.module('productIndexApp',
		["client-index.filter","client-index.httpService"]
		.concat($commonDirectiveList).concat($directiveList)
)
.controller("productIndexCtrl",['$timeout', '$scope', '$sce', '$filter', '$document', 'clientIndexHttpService', 
function($timeout, $scope, $sce, $filter,$document, clientIndexHttpService){
	$scope.sce = $sce;
	//商品信息
	$scope.productInfo = {};
	//被选中查看的商品图
	$scope.selectedImg = "";
	
	
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
		if(window.history.length > 1){
			window.history.go(-1);
		}else{
			window.location.href = $contextPath;
		}
	};
	
	/**
	 * 跳到店铺页面
	 */
	$scope.toShopPage = function(){
		window.location.href = $contextPath + '/home/shop-index?shopId=' + $scope.shopInfo.id;
	};
	
	/**
	 * 当前页面跳到指定位置
	 */
	$scope.scrollTo = function(target){
		common.scrollTo(target);
	};
	
	/**
	 * 选中相应的商品图
	 */
	$scope.selectImg = function(url){
		$scope.selectedImg = url;
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
				//命名浏览器标题
				$("title").text($scope.productInfo.shopName);
				//设置被选中的商品图
				$scope.selectedImg = $filter("getImgByImgList")($scope.productInfo.picPathList);
				//获取该商品的店铺
				$scope.getShopInfo($scope.productInfo.shopId);
				//增加浏览量
				clientIndexHttpService.addProClickCount($scope.productInfo.id);
				
				$(".pindex-container").fadeIn();
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