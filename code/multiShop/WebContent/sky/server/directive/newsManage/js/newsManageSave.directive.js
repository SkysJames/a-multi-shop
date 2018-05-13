angular.module('newsManageSave',[])
.directive('newsManageSave',function(){
	return {
		restrict:'E',
		scope : {
			news			: "=",
			getNewsList	: "&",
		},
		templateUrl : $contextPath +"/sky/server/directive/newsManage/template/newsManageSave.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService, newsManageService){
			//新闻类型列表
			$scope.newsTypeList = common.newsContants.newsType;
			
			/**
			 * 新增保存新闻
			 */
			$scope.saveNews = function(news){
				if(!$scope.isSaveNews(news)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				news.content = $scope.$root.editor.html();
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveNews(news)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.getNewsList();
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
			 * 编辑保存新闻
			 */
			$scope.editNews = function(news){
				if(!$scope.isSaveNews(news)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				news.content = $scope.$root.editor.html();
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editNews(news)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.getNewsList();
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
			 * 判断该新闻对象是否符合保存的条件
			 */
			$scope.isSaveNews = function(news){
				if(!news){
					return false;
				}
				
				if(!news.name || news.name==""){
					return false;
				}
				
				if(news.newsType==null || news.newsType==undefined){
					return false;
				}
				
				return true;
			};
		}
	};
});