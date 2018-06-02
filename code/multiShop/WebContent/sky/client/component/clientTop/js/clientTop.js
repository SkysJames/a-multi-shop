angular.module('clientTop',[])
.directive('clientTop',function(){
	return {
		restrict:'E',
		scope : {
			tableName	: '@',
			typeList		: '=',
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
			//当前导航
			$scope.currentNav = "index";
			
			/**
			 * 点击导航对象
			 */
			$scope.clickNav = function(nav, event){
				$scope.currentNav = nav;
			};
			
			/**
			 * 鼠标移过导航对象
			 */
			$scope.mouseNav = function(nav, isOver){
				if(nav=="more" && isOver){
					$(".ct-bottom-item-two").css("top", "-84px");
					$(".ct-bottom-item-two").fadeIn();
				}else{
					$(".ct-bottom-item-two").fadeOut();
				}
			}
			
			//获取购物车列表
			
			//获取收藏夹列表
			
			//获取历史浏览记录列表
			
			/**
			 * 通过url打开页面
			 * isLocation true-本页面打开，false-新窗口打开
			 */
			$scope.toPage = function(url, isLocation){
				common.toPage($contextPath + url, isLocation);
			};
			
			//点击自己则不消失，即停止冒泡事件
			$(".ct-bottom-item-two").click(function(event){
				event.stopPropagation();
			});
		}
	};
});