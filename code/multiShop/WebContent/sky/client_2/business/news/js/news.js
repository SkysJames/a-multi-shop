angular.module('newsApp',["client-index.filter","client-index.httpService","newsDetail"].concat($directiveList))
.controller("newsCtrl",['$timeout', '$scope', '$document', '$filter', 'clientIndexHttpService', 
function($timeout, $scope, $document, $filter, clientIndexHttpService){
	//页面导航
	$scope.clientNavs = common.clientNavs;
	//当前导航对象
	$scope.currentNav = $scope.clientNavs.c_news;
	//主背景图片url
	$scope.indexBgUrl = "sky/client/component/bgShow/img/news_bg.jpg";
	//详情背景图片url
	$scope.detailBgUrl = "sky/client/component/bgShow/img/news_detail_bg.jpg";
	//当前背景图片url
	$scope.currentBgUrl = $scope.indexBgUrl;
	//当前背景标题
	$scope.currentHeadText = "";
	//新闻类型map
	$scope.newsTypes = common.newsContants.newsType;
	//当前目标对象
	$scope.currentItem = null;
	//查询条件
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 10,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 1,		//总页码数
			newsType		: -1,
	};
	
	/**
	 * 切换页面
	 */
	$scope.triggerPage = function(url){
		window.location.href = $contextPath + url;
	};
	
	/**
	 * 选中目标对象
	 */
	$scope.$root.selectedItem = function(item){
		$scope.currentItem = item;
		if($scope.currentItem){
			$scope.currentBgUrl = $scope.detailBgUrl;
			$scope.currentItem.height = "440px";
		}else{
			$scope.currentBgUrl = $scope.indexBgUrl;
		}
		window.scrollTo(0,0);
	};
	
	/**
	 * 获取新闻列表
	 */
	$scope.getNewsList = function(pageNo){
		if(pageNo){
			$scope.condition.pageNo = pageNo;
		}
		
		$scope.isLoadingNews = true;
		clientIndexHttpService.getNewsList($scope.condition)
		.then(function(response){
			var data = response.data;
			$scope.newsList = data.pager.resultList;
			$scope.condition.totalCount = data.pager.totalCount;
			$scope.condition.pageCount = data.pager.pageCount;
			$scope.isLoadingNews = false;
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 根据ID获取产品详情
	 */
	$scope.getNewsDetail = function(id){
		clientIndexHttpService.getNewsById(id)
		.then(function(response){
			var data = response.data;
			$scope.$root.selectedItem(data.news);
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 过滤新闻类型
	 */
	$scope.filterType = function(type){
		if(type!=undefined && type!=null){
			$scope.condition.newsType = type;
		}
		$scope.getNewsList();
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		if(code && code != ""){
			$scope.getNewsDetail(code);
		}
		
		if(type != null){
			$scope.condition.newsType = type;
		}
		
		//初始化新闻列表
		$scope.getNewsList();
	};
	$document.ready($scope.initFunc);
	
}]);