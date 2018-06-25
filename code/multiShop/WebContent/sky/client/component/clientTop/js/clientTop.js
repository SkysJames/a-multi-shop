angular.module('clientTop',[])
.directive('clientTop',function(){
	return {
		restrict:'E',
		scope : {
			tableName	: '@',
			keywords		: '=',
			selectedType	: '=',
			typeList		: '=',//必传，若无则直接undifined的变量
			shopInfo		: '=',
		},
		templateUrl : $contextPath +"/sky/client/component/clientTop/template/clientTop.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, clientIndexHttpService){
			//是否为微信浏览器
			$scope.isWechat = $isWechat;
			//用户的默认初始密码
			$scope.defaultPasswd = $defaultPasswd;
			//系统名称
			$scope.systemName = $systemName;
			//微信二维码的url
			$scope.wechatPic = $wechatPic;
			//qq登录按钮图片url
			$scope.qqLoginBtn = $qqLoginBtn;
			//当前页面url
			$scope.currentUrl = $currentUrl;
			//当前导航（手机端底部）
			$scope.currentNav = "index";
			//当前登录面板的导航，login-登录面板，register-注册面板
			$scope.loginNav = "login";
			//当前用户面板的导航，user-用户信息面板，password-密码面板
			$scope.userNav = "user";
			//当前收藏面板的导航，shop-店铺面板，product-商品面板
			$scope.collectNav = "shop";
			//当前历史记录面板的导航，shop-店铺面板，product-商品面板
			$scope.historyNav = "shop";
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
			//历史浏览记录的店铺条件对象
			$scope.hisShopCondition = {
					pageNo		: 1,		//当前页码
					pageSize		: 5,		//每页数据量
					pageCount	: 1,
					totalCount	: 0,
					userId		: $currentUser?$currentUser.userId:"",
					type			: "1",//历史浏览记录类型
					tableName	: common.tableContants.TB_SHOP,//店铺
			};
			//历史浏览记录的商品条件对象
			$scope.hisProCondition = {
					pageNo		: 1,		//当前页码
					pageSize		: 5,		//每页数据量
					pageCount	: 1,
					totalCount	: 0,
					userId		: $currentUser?$currentUser.userId:"",
					type			: "1",//历史浏览记录类型
					tableName	: common.tableContants.TB_PRODUCT,//商品
			};
			//店铺评价的新增对象
			$scope.shopEvalAdd = {};
			//用户反馈的新增对象
			$scope.feedbackAdd = {};
			//店铺申请对象
			$scope.shopRegister = {};
			
			
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
				
				$("#birthdateId").val($scope.userInfo.birthdate);
			};
			
			/**
			 * 切换收藏面板（店铺面板/商品面板）
			 */
			$scope.triggerCollectPanel = function(nav){
				$scope.collectNav = nav;
			};
			
			/**
			 * 打开收藏面板
			 */
			$scope.openCollectPanel = function(){
				if($scope.isLogin()){
					$scope.triggerCollectPanel("shop");
					$('#collectWinId').modal("show");
				}
			};
			
			/**
			 * 切换历史记录面板（店铺面板/商品面板）
			 * 需要实时的
			 */
			$scope.triggerHistoryPanel = function(nav){
				$scope.historyNav = nav;
				
				if(nav == "shop"){
					$scope.pagedHisShopList();
				}else if(nav == "product"){
					$scope.pagedHisProList();
				}
			};
			
			/**
			 * 打开历史记录面板
			 */
			$scope.openHistoryPanel = function(){
				if($scope.isLogin()){
					$scope.triggerHistoryPanel("shop");
					$('#historyWinId').modal("show");
				}
			};
			
			/**
			 * 打开评价面板
			 */
			$scope.openEvaluatePanel = function(){
				if($scope.isLogin()){
					//添加的店铺评价对象
					$scope.shopEvalAdd = {};
					//错误信息
					$scope.errorMsg = "";
					$('#evaluateWinId').modal("show");
				}
			};
			
			/**
			 * 打开用户反馈面板
			 */
			$scope.openFeedbackPanel = function(){
				if($scope.isLogin()){
					//添加的反馈对象
					$scope.feedbackAdd = {};
					//错误信息
					$scope.errorMsg = "";
					$('#feedbackWinId').modal("show");
				}
			};
			
			/**
			 * 打开店铺申请面板
			 */
			$scope.openShopRegisterPanel = function(){
				if($scope.isLogin()){
					//店铺申请对象
					$scope.shopRegister = {};
					//错误信息
					$scope.errorMsg = "";
					$('#shopRegisterWinId').modal("show");
				}
			};
			
			/**
			 * 打开购物车面板
			 */
			$scope.openCartPanel = function(){
				if($scope.isLogin()){
					$('#cartWinId').modal("show");
				}
			};
			
			/**
			 * 打开登录面板
			 */
			$scope.openLoginPanel = function(nav){
				$scope.triggerLoginPanel(nav);
				$('#loginWinId').modal("show");
			};
			
			/**
			 * 判断是否已经登录
			 */
			$scope.isLogin = function(){
				if(!$currentUser){
					$scope.openLoginPanel("login");
					return false;
				}
				return true;
			};
			
			/**
			 * 打开我的面板
			 */
			$scope.openMyPanel = function(){
				if($scope.isLogin()){
					$scope.triggerUserPanel("user");
					$('#userWinId').modal("show");
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
			 * 保存个人信息
			 */
			$scope.saveUserInfo = function(){
				$scope.registerObj.birthdate = $("#registerBirthdateId").val();
				
				clientIndexHttpService.registerUser($scope.registerObj)
				.then(function(response){
					var data = response.data;
					if(data.statusCode == "200"){
						common.triggerSuccessMesg(data.message);
						window.location.reload();
					}else if(data.message){
						$scope.errorMsg = data.message;
					}else{
						window.location.reload();
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 编辑保存个人信息
			 */
			$scope.editUserInfo = function(){
				$scope.userInfo.birthdate = $("#birthdateId").val();
				
				clientIndexHttpService.editPerson($scope.userInfo)
				.then(function(response){
					var data = response.data;
					if(data.statusCode == "200"){
						common.triggerSuccessMesg(data.message);
						window.location.reload();
					}else if(data.message){
						$scope.errorMsg = data.message;
					}else{
						window.location.reload();
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
						}else if(data.message){
							$scope.errorMsg = data.message;
						}else{
							window.location.reload();
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
						window.location.reload();
					}else if(data.message){
						$scope.errorMsg = data.message;
					}else{
						window.location.reload();
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
						window.location.reload();
					}else if(data.message){
						common.triggerFailMesg(data.message);
					}else{
						window.location.reload();
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
					if($scope.shopInfo){
						$(".ct-bottom-item-two").css("top", "-165px");
					}else{
						$(".ct-bottom-item-two").css("top", "-125px");
					}
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
			
			/**
			 * 前端分页获取店铺收藏列表
			 */
			$scope.pagedShopCollect = function(pageNo){
				if(pageNo){
					$scope.collectShopPager.pageNo = pageNo;
				}else{
					$scope.collectShopPager.pageNo = 1;
				}
				
				$scope.collectShopPager.list = common.pagedList($scope.collectShopPager.pageNo, 
						$scope.collectShopPager.pageSize, $scope.collectShopPager.pageCount, $scope.collectShopListBak);
			};
			
			/**
			 * 前端分页获取商品收藏列表
			 */
			$scope.pagedProductCollect = function(pageNo){
				if(pageNo){
					$scope.collectProductPager.pageNo = pageNo;
				}else{
					$scope.collectProductPager.pageNo = 1;
				}
				
				$scope.collectProductPager.list = common.pagedList($scope.collectProductPager.pageNo, 
						$scope.collectProductPager.pageSize, $scope.collectProductPager.pageCount, $scope.collectProductListBak);
			};
			
			/**
			 * 获取收藏夹列表
			 */
			$scope.getCollectList = function(tableName){
				if(!$currentUser){
					return;
				}
				
				var condition = {
						userId		: $currentUser.userId,
						type			: "2",//收藏夹类型
						tableName	: tableName,
				};
				
				if(tableName==common.tableContants.TB_SHOP){
					$scope.isLoadingCollectedShop = true;
				}else if(tableName==common.tableContants.TB_PRODUCT){
					$scope.isLoadingCollectedProduct = true;
				}
				
				clientIndexHttpService.getProhistoryList(condition)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						if(tableName==common.tableContants.TB_SHOP){
							$scope.collectShopListBak = data.list;
							$scope.collectShopPager = common.getPagerObj(data.list, 5);
							$scope.isLoadingCollectedShop = false;
							
							//判断当前是否在已收藏的店铺页面
							if(angular.element($('#shopHeaderId')).scope()){
								angular.element($('#shopHeaderId')).scope().collectedObj = $filter("getCollectedObj")(shopId, $scope.collectShopListBak);
							}
						}else if(tableName==common.tableContants.TB_PRODUCT){
							$scope.collectProductListBak = data.list;
							$scope.collectProductPager = common.getPagerObj(data.list, 5);
							$scope.isLoadingCollectedProduct = false;
							
							//判断当前是否在已收藏的商品页面
							if(angular.element($('#productHeaderId')).scope()){
								angular.element($('#productHeaderId')).scope().collectedObj = $filter("getCollectedObj")(productId, $scope.collectProductListBak);
							}
						}
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 取消收藏，店铺/商品
			 */
			$scope.deleteCollect = function(collectedObj, tableName, event){
				event.stopPropagation();
				clientIndexHttpService.deleteProhistory(collectedObj)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						//更新收藏列表
						$scope.getCollectList(tableName);
					}else if(data.message){
						common.triggerFailMesg(data.message);
					}else{
						window.location.reload();
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 分页获取历史浏览记录的店铺列表
			 */
			$scope.pagedHisShopList = function(pageNo){
				if(!$currentUser){
					return;
				}
				
				if(pageNo){
					$scope.hisShopCondition.pageNo = pageNo;
				}else{
					$scope.hisShopCondition.pageNo = 1;
				}
				
				$scope.isLoadingHisShop = true;
				clientIndexHttpService.pagedProhistoryList($scope.hisShopCondition)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						$scope.hisShopList = data.pager.resultList;
						$scope.hisShopCondition.pageCount = data.pager.pageCount;
						$scope.hisShopCondition.totalCount = data.pager.totalCount;
					}else if(data.message){
						common.triggerFailMesg(data.message);
					}else{
						window.location.reload();
					}
					$scope.isLoadingHisShop = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 分页获取历史浏览记录的商品列表
			 */
			$scope.pagedHisProList = function(pageNo){
				if(!$currentUser){
					return;
				}
				
				if(pageNo){
					$scope.hisProCondition.pageNo = pageNo;
				}else{
					$scope.hisProCondition.pageNo = 1;
				}
				
				$scope.isLoadingHisProduct = true;
				clientIndexHttpService.pagedProhistoryList($scope.hisProCondition)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						$scope.hisProList = data.pager.resultList;
						$scope.hisProCondition.pageCount = data.pager.pageCount;
						$scope.hisProCondition.totalCount = data.pager.totalCount;
					}else if(data.message){
						common.triggerFailMesg(data.message);
					}else{
						window.location.reload();
					}
					$scope.isLoadingHisProduct = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 添加店铺评价
			 */
			$scope.addShopEvaluate = function(){
				if(!$currentUser){
					return;
				}
				
				if(!$scope.isSaveShopEvaluate($scope.shopEvalAdd)){
					$scope.errorMsg = "请按要求填写评价内容";
					return;
				}
				
				$scope.isLoadingAddShopEval = true;
				clientIndexHttpService.addEvaluate($scope.shopEvalAdd)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						common.triggerSuccessMesg(data.message);
						$('#evaluateWinId').modal("hide");
					}else if(data.message){
						$scope.errorMsg = data.message;
					}else{
						window.location.reload();
					}
					$scope.isLoadingAddShopEval = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 判断该对象是否符合保存的条件
			 */
			$scope.isSaveShopEvaluate = function(shopEvalAdd){
				if(!shopEvalAdd){
					return false;
				}
				
				shopEvalAdd.userId = $currentUser.userId;//评价用户ID
				shopEvalAdd.status = "1";//已发送未接收
				shopEvalAdd.tableName = common.tableContants.TB_SHOP;//店铺表名
				shopEvalAdd.objId = $scope.shopInfo.id;//被评价店铺ID
				
				if(!shopEvalAdd.mark || shopEvalAdd.mark==""){
					return false;
				}
				
				return true;
			};
			
			/**
			 * 添加反馈消息
			 */
			$scope.addFeedback = function(){
				if(!$currentUser){
					return;
				}
				
				if(!$scope.isSaveFeedback($scope.feedbackAdd)){
					$scope.errorMsg = "请按要求填写反馈内容";
					return;
				}
				
				$scope.isLoadingAddFeedback = true;
				clientIndexHttpService.addMessage($scope.feedbackAdd)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						common.triggerSuccessMesg(data.message);
						$('#feedbackWinId').modal("hide");
					}else if(data.message){
						$scope.errorMsg = data.message;
					}else{
						window.location.reload();
					}
					$scope.isLoadingAddFeedback = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 判断该对象是否符合保存的条件
			 */
			$scope.isSaveFeedback = function(feedbackAdd){
				if(!feedbackAdd){
					return false;
				}
				
				feedbackAdd.fromUser = $currentUser.userId;//发送用户ID
				feedbackAdd.toUser = common.userContants.userAdmin;//接收用户ID
				feedbackAdd.status = common.messageContants.status.NORECEIVE;//已发送未接收
				
				if(!feedbackAdd.content || feedbackAdd.content==""){
					return false;
				}
				
				return true;
			};
			
			/**
			 * 添加店铺申请
			 */
			$scope.addShopRegister = function(){
				if(!$currentUser){
					return;
				}
				
				if(!$scope.isSaveShopRegister($scope.shopRegister)){
					$scope.errorMsg = "请按要求填写店铺申请";
					return;
				}
				
				$scope.isLoadingShopRegister = true;
				clientIndexHttpService.registerShop($scope.shopRegister)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						common.triggerSuccessMesg(data.message);
						$('#shopRegisterWinId').modal("hide");
					}else if(data.message){
						$scope.errorMsg = data.message;
					}else{
						window.location.reload();
					}
					$scope.isLoadingShopRegister = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 判断该对象是否符合保存的条件
			 */
			$scope.isSaveShopRegister = function(shopRegister){
				if(!shopRegister){
					return false;
				}
				
				shopRegister.overTimeString = $("#overTimeId").val();//塞入过期时间
				shopRegister.status = common.shopContants.status.NOAPPEOVE;//申请待审批
				
				if(!shopRegister.name || shopRegister.name==""){
					return false;
				}else if(shopRegister.name.length>35){
					return false;
				}
				if(!shopRegister.remark || shopRegister.remark==""){
					return false;
				}else if(shopRegister.remark.length>50){
					return false;
				}
				if(!shopRegister.shopType || shopRegister.shopType==""){
					return false;
				}
				if(!shopRegister.overTimeString || shopRegister.overTimeString==""){
					return false;
				}
				if(!shopRegister.address || shopRegister.address==""){
					return false;
				}
				if(!shopRegister.phone || shopRegister.phone==""){
					return false;
				}
				
				return true;
			};
			
			/**
			 * 加入购物车的动画效果
			 */
			$scope.addCartAnimate = function(event){
				var offset = $("#bottomCartId").offset();
				console.log(event, $("#bottomCartId").offset());
				
				var flyer = $('<img class="my-flyer">'); 
		        flyer.fly({ 
		            start: { 
		                left: event.pageX, //开始位置（必填）#fly元素会被设置成position: fixed 
		                top: event.pageY //开始位置（必填） 
		            }, 
		            end: { 
		                left: offset.left+10, //结束位置（必填） 
		                top: offset.top+10, //结束位置（必填） 
		                width: 0, //结束时宽度 
		                height: 0 //结束时高度 
		            }, 
		            onEnd: function(){ //结束回调 
		                this.destroy(); //移除dom
		            }
		        });
			};
			
			/**
			 * 获取购物车列表
			 */
			$scope.getCartList = function(){
				if(!$currentUser){
					return;
				}
				
				var condition = {
						userId	: $currentUser.userId,
						status	: common.cartContants.status.UNSUBMIT,
				};
				
				$scope.isLoadingCart = true;
				clientIndexHttpService.getCartList(condition)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						$scope.cartList = data.list;
					}else if(data.message){
						common.triggerFailMesg(data.message);
					}else{
						window.location.reload();
					}
					$scope.isLoadingCart = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 添加购物车
			 */
			$scope.addCart = function(product, proNum, event){
				if(event){
					event.stopPropagation();
				}
				
				if(!$scope.isLogin() || !product){
					return;
				}
				
				//判断该商品是否已加在购物车上
				var cart = $filter("getCartInfo")(product, $scope.cartList);
				if(cart){
					$scope.editCartCount(cart, proNum, event);
					return;
				}
				
				cart = {
						productId	: product.id,
						userId		: $currentUser.userId,
						proNum		: proNum?proNum:1,
						status		: common.cartContants.status.UNSUBMIT,
				};
				
				$scope.isLoadingAddCart = true;
				clientIndexHttpService.addCart(cart)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						$scope.getCartList();
					}else if(data.message){
						common.triggerFailMesg(data.message);
					}else{
						window.location.reload();
					}
					$scope.isLoadingAddCart = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 添加/减少购物车商品数量
			 */
			$scope.editCartCount = function(cart, count, event){
				if(event){
					event.stopPropagation();
				}
				
				if(!$scope.isLogin() || !cart){
					return;
				}
				cart.proNum = cart.proNum + count;
				
				if(cart.proNum){
					$scope.editCart(cart);
				}else{
					$scope.deleteCart(cart);
				}
				
			};
			
			/**
			 * 编辑购物车
			 */
			$scope.editCart = function(cart){
				clientIndexHttpService.editCart(cart)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
//						$scope.getCartList();
					}else if(data.message){
						common.triggerFailMesg(data.message);
					}else{
						window.location.reload();
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 删除购物车
			 */
			$scope.deleteCart = function(cart){
				if(!$scope.isLogin()){
					return;
				}
				
				clientIndexHttpService.deleteCart(cart)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						for(var i=0,len=$scope.cartList.length; i<len; i++){
							if($scope.cartList[i].id == cart.id){
								$scope.cartList.splice(i, 1);
								break;
							}
						}
//						$scope.getCartList();
					}else if(data.message){
						common.triggerFailMesg(data.message);
					}else{
						window.location.reload();
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 清空购物车
			 */
			$scope.clearCart = function(){
				if(!$scope.isLogin()){
					return;
				}
				
				var condition = {
						userId	: $currentUser.userId,
						status	: common.cartContants.status.UNSUBMIT,
				};
				
				clientIndexHttpService.batchDeleteCart(condition)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						$scope.getCartList();
					}else if(data.message){
						common.triggerFailMesg(data.message);
					}else{
						window.location.reload();
					}
				},function(err){
					console.log(err);
				});
			};
			
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
			 * 初始化店铺类型列表
			 */
			$scope.initTypeList = function(){
				var condition = {
						tableName	: common.tableContants.TB_SHOP,	//店铺表名
						parentId		: common.typetContants.rootParentId,//一级类别
				};
				
				clientIndexHttpService.getTypeList(condition)
				.then(function(response){
					var data = response.data;
					$scope.typeList = data.list;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//初始化类型列表
				if(!$scope.typeList){
					$scope.initTypeList();
				}
				
				//获取用户信息
				$scope.getUserInfo();
				//获取购物车列表
				$scope.getCartList();
				//获取店铺的收藏列表
				$scope.getCollectList(common.tableContants.TB_SHOP);
				//获取商品的收藏列表
				$scope.getCollectList(common.tableContants.TB_PRODUCT);
				
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