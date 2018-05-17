angular.module('indexApp',["client-index.filter","client-index.httpService"].concat($directiveList))
.controller("indexCtrl",['$timeout', '$scope', '$document', 'clientIndexHttpService', 
function($timeout, $scope, $document, clientIndexHttpService){
	//页面导航
	$scope.clientNavs = common.clientNavs;
	//当前导航对象
	$scope.currentNav = $scope.clientNavs.a_index;
	//当前轮播的图片列表
	$scope.slideList = common.clientSlider.a_index;
	//新闻类型map
	$scope.newsTypes = common.newsContants.newsType;
	//产品类型map
	$scope.proTypes = common.productContants.proType;
	
	/**
	 * 切换页面
	 */
	$scope.triggerPage = function(url){
		window.location.href = $contextPath + url;
	};
	
	/**
	 * 当前页面跳到指定位置
	 */
	$scope.scrollTo = function(target){
		common.scrollTo(target);
	};
	
	/**
	 * 获取产品列表
	 */
	$scope.getProductList = function(pageNo){
		var condition = {
				pageNo		: 1,		//当前页码
				pageSize		: 6,		//每页数据量
				proType		: -1,
		};
		
		$scope.isLoadingProduct = true;
		clientIndexHttpService.getProductList(condition)
		.then(function(response){
			var data = response.data;
			$scope.productList = data.pager.resultList;
			$scope.isLoadingProduct = false;
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 获取新闻列表
	 */
	$scope.getNewsList = function(pageNo){
		var condition = {
				pageNo		: 1,		//当前页码
				pageSize		: 6,		//每页数据量
				newsType		: -1,
		};
		
		$scope.isLoadingNews = true;
		clientIndexHttpService.getNewsList(condition)
		.then(function(response){
			var data = response.data;
			$scope.newsList = data.pager.resultList;
			$scope.isLoadingNews = false;
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//初始化产品列表
		$scope.getProductList();
		//初始化新闻列表
		$scope.getNewsList();
	};
	$document.ready($scope.initFunc);
	
}]);