angular.module('indexHeader',[])
.directive('indexHeader',function(){
	return {
		restrict:'E',
		scope : {
			keyType		: "@",
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
			 * 鼠标经过搜索关键字类型
			 */
			$scope.mouseKeyType = function(isOver){
				if(isOver){
					$(".ch-key-type>ul").fadeIn();
				}else{
					$(".ch-key-type>ul").fadeOut();
				}
			};
			
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
				var pathname = window.location.pathname;
				if($scope.keyType && $scope.keyType=="shop"){
					window.location.href = $contextPath + "/home/shop-search?keywords=" + $scope.keywords;
				}else if($scope.keyType && $scope.keyType=="product"){
					window.location.href = $contextPath + "/home/product-search?keywords=" + $scope.keywords;
				}
			};
			
		}
	};
});