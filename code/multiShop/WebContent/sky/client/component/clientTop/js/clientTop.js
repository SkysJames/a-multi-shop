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
			//当前登录用户
			$scope.currentUser = $currentUser;
			//微信二维码的url
			$scope.wechatPic = $wechatPic;
			
			//获取收藏夹列表
			
			//获取历史浏览记录列表
			
			/**
			 * 通过url打开页面
			 * isLocation true-本页面打开，false-新窗口打开
			 */
			$scope.toPage = function(url, isLocation){
				common.toPage($contextPath + url, isLocation);
			};
		}
	};
});