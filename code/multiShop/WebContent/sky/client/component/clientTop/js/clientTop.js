angular.module('clientTop',[])
.directive('clientTop',function(){
	return {
		restrict:'E',
		scope : {
			tableName	: '@',
			keywords		: '=',
			selectedType	: '=',
			typeList		: '=',
			shopInfo		: '=',
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
			//当前登录面板的导航，login-登录面板，register-注册面板
			$scope.loginNav = "login";
			//登录对象
			$scope.loginObj = {};
			//注册对象
			$scope.registerObj = {};
			
			
			/**
			 * 获取登录面板的标题头
			 */
			$scope.getLoginHeader = function(nav){
				if(nav){
					if(nav == "login"){
						return "登录";
					}else if(nav == "register"){
						return  "注册";
					}
				}
			};
			
			/**
			 * 切换登录面板（登录面板/注册面板）
			 */
			$scope.triggerLoginPanel = function(nav){
				$scope.loginNav = nav;
				//登录对象
				$scope.loginObj = {};
				//注册对象
				$scope.registerObj = {};
			};
			
			/**
			 * 打开登录面板
			 */
			$scope.openLoginPanel = function(nav){
				$scope.triggerLoginPanel(nav);
				$('#loginWinId').modal("show");
			};
			
			/**
			 * 点击导航对象
			 */
			$scope.clickNav = function(nav, event){
				$scope.currentNav = nav;
			};
			
			/**
			 * 展开或收缩客服用户
			 */
			$scope.toggleServiceUser = function(item){
				item.isOpen = !item.isOpen;
			};
			
			/**
			 * 鼠标移过导航对象
			 */
			$scope.mouseNav = function(nav, isOver){
				if(nav=="more" && isOver){
					$(".ct-bottom-item-two").css("top", "-125px");
					$(".ct-bottom-item-two").fadeIn();
				}else{
					$(".ct-bottom-item-two").fadeOut();
				}
			}
			
			/**
			 * 鼠标移过客服
			 */
			$scope.mouseService = function(isOver){
				if(isOver){
					$(".ct-phone").addClass("ct-phone-show");
				}else{
					$(".ct-phone").removeClass("ct-phone-show");
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
			
			/**
			 * 跳到搜索页面
			 */
			$scope.toSearchPage = function(selectedType){
				if($scope.tableName && $scope.tableName=="tb_shop"){
					window.location.href = $contextPath + "/home/shop-search?keywords=" + ($scope.keywords?$scope.keywords:"") + "&type=" + selectedType.id;
				}
			};
			
			/**
			 * 点击自己则不消失，即停止冒泡事件
			 */
			//导航对象
			$(".ct-bottom-item").click(function(event){
				event.stopPropagation();
			});
			//客服
			$(".ct-fixed-item").click(function(event){
				event.stopPropagation();
			});
		}
	};
});