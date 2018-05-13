angular.module('newsDetail',[])
.directive('newsDetail',function(){
	return {
		restrict:'E',
		scope : {
			currentItem	: "=",
		},
		templateUrl : $contextPath +"/sky/client/business/news/template/newsDetail.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, $sce, clientIndexHttpService){
			$scope.sce = $sce;
			
			/**
			 * 获取新闻列表
			 */
			$scope.getNewsList = function(pageNo){
				var condition = {
						pageNo		: 1,		//当前页码
						pageSize		: 5,		//每页数据量
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
				//获取新闻列表
				$scope.getNewsList();
			};
			$document.ready($scope.initFunc);
		}
	};
});