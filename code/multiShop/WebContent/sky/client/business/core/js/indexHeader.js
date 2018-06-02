angular.module('indexHeader',[])
.directive('indexHeader',function(){
	return {
		restrict:'E',
		scope : {
			indexAns		: "=",
		},
		templateUrl : $contextPath +"/sky/client/business/core/template/indexHeader.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, clientIndexHttpService){
			//系统名称
			$scope.systemName = $systemName;
			//系统logo
			$scope.systemLogo = $systemLogo;
			//当前登录用户
			$scope.currentUser = $currentUser;
			//搜索关键字
			$scope.keywords = "";
			
			/**
			 * 回到主页
			 */
			$scope.toIndexPage = function(){
				window.location.href = $contextPath;
			};
			
			/**
			 * 跳到搜索页面
			 */
			$scope.toSearchPage = function(){
				if($scope.keywords && $scope.keywords.length>0){
					window.location.href = $contextPath + "/sky/" + "?keywords=" + $scope.keywords;
				}
			};
			
		}
	};
});