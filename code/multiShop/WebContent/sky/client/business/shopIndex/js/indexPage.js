angular.module('indexPage.module',[])
.controller("indexPageCtrl",['$timeout', '$scope', '$routeParams', '$filter', '$document', "clientIndexHttpService", 
function($timeout, $scope, $routeParams, $filter, $document, clientIndexHttpService){
	//路由过来的参数
	$scope.routeParams = $routeParams;
	//根作用域
	$scope.shopIndexScope = angular.element($('#shopIndexId')).scope();
	//搜索关键字
	$scope.shopIndexScope.keywords = $routeParams.keywords?$routeParams.keywords:"";
	//是否显示更多类型
	$scope.isTypeMore = false;
	//搜索条件对象
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 30,	//每页数据量
			pageCount	: 1,
			totalCount	: 0,
			shopId		: shopId,//店铺ID
			isOver		: "0",	//未过期
			status		: common.productContants.status.ONTABLE,	//状态上架
			keywords		: $routeParams.keywords?$routeParams.keywords:"",	//搜索关键字
			proType		: $routeParams.type,//商品类型
	};
	//选中的导航
	$scope.selectedNav = "index";
	
	
	/**
	 * 跳转页面
	 */
	$scope.toPage = function(url){
		common.toPage($contextPath + url, true);
	};
	
	/**
	 * 加载更多商品
	 */
	$scope.loadMoreProduct = function(){
		if($scope.condition && $scope.condition.pageNo<$scope.condition.pageCount && !$scope.isLoadingProduct){
			$scope.condition.pageNo++;
			$scope.pagedProductList(true);
		}
	};
	
	/**
	 * 选择过滤商品类型
	 */
	$scope.selectType = function(typeId){
		$scope.condition.proType = typeId;
		$scope.pagedProductList();
	};
	
	/**
	 * 分页获取商品列表
	 */
	$scope.pagedProductList = function(isMore){
		$scope.isLoadingProduct = true;
		clientIndexHttpService.pagedProductList($scope.condition)
		.then(function(response){
			var data = response.data;
			
			if(!isMore || !$scope.productList || $scope.productList.length==0){
				$scope.productList = data.pager.resultList;
			}else{
				$scope.productList = $scope.productList.concat(data.pager.resultList);
			}
			
			$scope.condition.pageCount = data.pager.pageCount;
			$scope.condition.totalCount = data.pager.totalCount;
			$scope.isLoadingProduct = false;
			
			if($scope.productList && $scope.productList.length>0){
				$(".shop-product").fadeIn("slow");
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//分页获取商品列表
		$scope.pagedProductList();
		
		//还原屏幕
		$(".sh-phone").removeClass("sh-phone-scroll");
		$(".shop-index-page").removeClass("shop-index-page-scroll");
		
		//手机端的商品面板滚动事件
		$(".sphone-product").scroll(function(){
			var allHeight = $(".sphone-product .shoppro-content").height();
			
			//判断向下滚动是否大于0，且可扩大屏幕
			if($(".sphone-product").scrollTop() > 0 
					&& allHeight > ($(".sphone-product").height() + $(".sn-phone").height() + $(".sh-phone").height())){
				$(".sh-phone").addClass("sh-phone-scroll");
				$(".shop-index-page").addClass("shop-index-page-scroll");
			}
			
			//判断是否滚动到div的顶部
			if($(".sphone-product").scrollTop() == 0){
				$(".sh-phone").removeClass("sh-phone-scroll");
				$(".shop-index-page").removeClass("shop-index-page-scroll");
			}
			
			//判断是否滚动到该div的底部
			if($(".sphone-product").scrollTop() >= (allHeight - $(".sphone-product").height())){
				$scope.loadMoreProduct();
			}
		});
		
		//页面滚动事件
		$(window).scroll(function(){
			//判断是否滚动到底部
			if($(window).scrollTop() == ($(document).height() - $(window).height())){
				$scope.loadMoreProduct();
			}
		});
	};
	$document.ready($scope.initFunc);
}]);
