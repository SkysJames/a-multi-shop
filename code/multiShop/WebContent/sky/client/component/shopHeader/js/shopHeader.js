angular.module('shopHeader',[])
.directive('shopHeader',function(){
	return {
		restrict:'E',
		scope : {
			shopInfo		: "=",
			keywords		: "=",
			indexAns		: "=",
		},
		templateUrl : $contextPath +"/sky/client/component/shopHeader/template/shopHeader.html",
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
			
			/**
			 * 跳到搜索页面
			 */
			$scope.toSearchPage = function(){
				if($scope.keywords){
					window.location.href = "#/indexPage/keywords/" + $scope.keywords;
				}else{
					window.location.href = "#/indexPage";
				}
			};
			
		}
	};
});