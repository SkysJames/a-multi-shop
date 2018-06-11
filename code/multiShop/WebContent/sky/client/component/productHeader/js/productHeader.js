angular.module('productHeader',[])
.directive('productHeader',function(){
	return {
		restrict:'E',
		scope : {
			shopInfo		: "=",
		},
		templateUrl : $contextPath +"/sky/client/component/productHeader/template/productHeader.html",
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
			 * 回到上一页
			 */
			$scope.toBackPage = function(){
				window.history.go(-1);
			};
			
		}
	};
});