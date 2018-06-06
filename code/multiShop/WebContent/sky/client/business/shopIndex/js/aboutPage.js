angular.module('indexPage.module',[])
.controller("indexPageCtrl",['$timeout', '$scope', '$routeParams', '$filter', '$document', "clientIndexHttpService", 
function($timeout, $scope, $routeParams, $filter, $document, clientIndexHttpService){
	console.log($routeParams.keywords, $routeParams.type);
	//搜索条件对象
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 30,	//每页数据量
			pageCount	: 1,
			totalCount	: 0,
			shopId		: shopId,//店铺ID
			isOver		: "0",	//未过期
			status		: common.productContants.status.ONTABLE,	//状态上架
			keywords		: $routeParams.keywords,	//搜索关键字
			proType		: $routeParams.type,//商品类型
	};
	
	
	/**
	 * 加载更多商品
	 */
	$scope.loadMoreProduct = function(){
		if($scope.condition && $scope.condition.pageNo<$scope.condition.pageCount){
			$scope.condition.pageNo++;
			$scope.pagedProductList(true);
		}
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
	 * 获取商品列表
	 */
	$scope.getProductList = function(){
		$scope.isLoadingProduct = true;
		clientIndexHttpService.getProductList($scope.condition)
		.then(function(response){
			var data = response.data;
			$scope.productList = data.list;
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
		if(!$routeParams.type || $routeParams.type==""){
			$scope.pagedProductList();
		}else{
			$scope.getProductList();
		}
		
		//页面滚动事件
		$(window).scroll(function(){
			//判断是否滚动到底部，且被选中的类型是否为全部
			if(($(window).scrollTop() + $(window).height() == $(document).height()) && (!$routeParams.type || $routeParams.type=="")){
				$scope.loadMoreProduct();
			}
		});
	};
	$document.ready($scope.initFunc);
}]);
