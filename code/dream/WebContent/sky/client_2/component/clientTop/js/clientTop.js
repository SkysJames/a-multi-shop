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
			//系统副标题
			$scope.systemSubname = $systemSubname;
			//联系手机
			$scope.telephone = $telephone;
			//联系电话
			$scope.phone = $phone;
			//服务时间
			$scope.serviceTime = $serviceTime;
			//qq联系的Url
			$scope.qqContactUrl = common.qqContactUrl;
			//前端展示页面的导航
			$scope.clientNavs = common.clientNavs;
			//是否展开导航列表
			$scope.isShowNav = false;
			//微信二维码url
			$scope.wechatUrl = common.wechatUrl;
			
			/**
			 * 当前页面跳到指定位置
			 */
			$scope.scrollTo = function(target){
				common.scrollTo(target);
			};
			
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
			
			/**
			 * 链接
			 */
			$scope.hrefTo = function(url){
				window.location.href = url;
			};
			
		}
	};
});