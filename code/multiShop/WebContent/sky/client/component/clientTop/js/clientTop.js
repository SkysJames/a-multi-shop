angular.module('clientTop',[])
.directive('clientTop',function(){
	return {
		restrict:'E',
		scope : {
			currentNav	: '=',
		},
		templateUrl : $contextPath +"/sky/client/component/clientTop/template/clientTop.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document){
			//系统名称
			$scope.systemName = $systemName;
			//前端展示页面的导航
			$scope.clientNavs = common.clientNavs;
			//是否展开导航列表
			$scope.isShowNav = false;
			
			/**
			 * 当页面宽度小于760，展开/收起导航列表
			 */
			$scope.toggleNavbar = function(){
				$scope.isShowNav = !$scope.isShowNav;
			};
			
			/**
			 * 切换页面
			 */
			$scope.triggerPage = function(url){
				window.location.href = $contextPath + url;
			};
		}
	};
});