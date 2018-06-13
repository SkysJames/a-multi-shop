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
		controller : function($scope, $timeout, $filter, $document, clientIndexHttpService){
			//系统名称
			$scope.systemName = $systemName;
			//微信二维码的url
			$scope.wechatPic = $wechatPic;
			//当前导航
			$scope.currentNav = "index";
			//当前登录面板的导航，login-登录面板，register-注册面板
			$scope.loginNav = "login";
			//当前用户面板的导航，user-用户信息面板，password-密码面板
			$scope.userNav = "user";
			//登录对象
			$scope.loginObj = {};
			//注册对象
			$scope.registerObj = {};
			//当前登录的用户信息
			$scope.userInfo = null;
			//修改的密码对象;
			$scope.password = {};
			//错误信息
			$scope.errorMsg = "";
			//是否编辑用户信息
			$scope.isEditUser = false;
			
			
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
			 * 获取用户信息面板的标题头
			 */
			$scope.getUserHeader = function(nav){
				if(nav){
					if(nav == "user"){
						return "用户信息";
					}else if(nav == "password"){
						return  "密码修改";
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
				//错误信息
				$scope.errorMsg = "";
			};
			
			/**
			 * 切换我的面板（我的面板/密码面板）
			 */
			$scope.triggerUserPanel = function(nav, isEditUser){
				$scope.userNav = nav;
				$scope.isEditUser = isEditUser;
				//用户信息
				$scope.userInfo = _.cloneDeep($scope.userInfoBak);
				//修改的密码对象;
				$scope.passwd = {};
				//错误信息
				$scope.errorMsg = "";
			};
			
			/**
			 * 打开登录面板
			 */
			$scope.openLoginPanel = function(nav){
				$scope.triggerLoginPanel(nav);
				$('#loginWinId').modal("show");
			};
			
			/**
			 * 打开我的面板
			 */
			$scope.openMyPanel = function(){
				if($currentUser){
					$scope.triggerUserPanel("user");
					$('#userWinId').modal("show");
				}else{
					$scope.openLoginPanel("login");
				}
			};
			
			/**
			 * 获取用户信息
			 */
			$scope.getUserInfo = function(){
				//判断用户是否已登录
				if($currentUser){
					clientIndexHttpService.getUserById($currentUser.userId)
					.then(function(response){
						var data = response.data;
						if(data.statusCode=="200"){
							$scope.userInfo = data.user;
							$scope.userInfoBak = _.cloneDeep($scope.userInfo);
						}else{
							common.triggerFailMesg(data.message);
						}
					},function(err){
						console.log(err);
					});
				}
			};
			
			/**
			 * 编辑保存个人信息
			 */
			$scope.saveUserInfo = function(){
				clientIndexHttpService.editPerson($scope.userInfo)
				.then(function(response){
					var data = response.data;
					if(data.statusCode == "200"){
						common.triggerSuccessMesg(data.message);
						window.location.reload();
					}else{
						$scope.errorMsg = data.message;
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 修改保存密码
			 */
			$scope.savePassword = function(){
				if($scope.checkPassword()){
					clientIndexHttpService.editPersonPawd($scope.userInfo.id, $scope.password.oldPasswd, $scope.password.newPasswd)
					.then(function(response){
						var data = response.data;
						if(data.statusCode == "200"){
							common.triggerSuccessMesg(data.message);
							window.location.reload();
						}else{
							$scope.errorMsg = data.message;
						}
					},function(err){
						console.log(err);
					});
				}
			};
			
			/**
			 * 检查密码是否可保存
			 */
			$scope.checkPassword = function(){
				if($scope.password.oldPasswd != $scope.userInfo.passwd){
					$scope.errorMsg = "请输入正确的旧密码";
					return false;
				}
				
				if($scope.password.newPasswd.length < 8){
					$scope.errorMsg = "新密码的长度至少为8位";
					return false;
				}
				
				if($scope.password.newPasswd != $scope.password.confirmPasswd){
					$scope.errorMsg = "两次输入的密码有误";
					return false;
				}
				
				return true;
			};
			
			/**
			 * 登录
			 */
			$scope.login = function(){
				if(!$scope.loginObj.userId || $scope.loginObj.userId==""
					|| !$scope.loginObj.userPwd || $scope.loginObj.userPwd==""){
					$scope.errorMsg = "请输入用户名和密码";
					return;
				}
				
				clientIndexHttpService.login($scope.loginObj)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						common.triggerSuccessMesg(data.message);
						window.location.reload();
					}else{
						$scope.errorMsg = data.message;
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 退出
			 */
			$scope.logout = function(){
				clientIndexHttpService.logout()
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						common.triggerSuccessMesg(data.message);
						window.location.reload();
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
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
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//获取用户信息
				$scope.getUserInfo();
				
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
			};
			$document.ready($scope.initFunc);
		}
	};
});