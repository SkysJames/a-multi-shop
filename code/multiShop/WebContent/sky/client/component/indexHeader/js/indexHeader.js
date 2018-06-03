angular.module('indexHeader',[])
.directive('indexHeader',function(){
	return {
		restrict:'E',
		scope : {
			keywords		: "=",
			indexAns		: "=",
		},
		templateUrl : $contextPath +"/sky/client/component/indexHeader/template/indexHeader.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, clientIndexHttpService){
			//系统名称
			$scope.systemName = $systemName;
			//系统logo
			$scope.systemLogo = $systemLogo;
			//当前登录用户
			$scope.currentUser = $currentUser;
			
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
//					console.log($contextPath + "/home/shop-search?keywords=" + $scope.keywords);
					window.location.href = $contextPath + "/home/shop-search?keywords=" + $scope.keywords;
				}
			};
			
		}
	};
});