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
					if($scope.shopInfo){
						$(".ct-bottom-item-two").css("top", "-125px");
					}else{
						$(".ct-bottom-item-two").css("top", "-85px");
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
			
			//获取购物车列表
			
			
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
					}else{
						common.triggerFailMesg(data.message);
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
						$scope.isLoadingHisShop = false;
					}else{
						common.triggerFailMesg(data.message);
					}
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
						$scope.isLoadingHisProduct = false;
					}else{
						common.triggerFailMesg(data.message);
					}
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
						$scope.isLoadingAddShopEval = false;
						$('#evaluateWinId').modal("hide");
					}else{
						common.triggerFailMesg(data.message);
					}
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
						$scope.isLoadingAddFeedback = false;
						$('#feedbackWinId').modal("hide");
					}else{
						common.triggerFailMesg(data.message);
					}
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
				feedbackAdd.toUser = "admin";//接收用户ID
				feedbackAdd.status = "1";//已发送未接收
				
				if(!feedbackAdd.content || feedbackAdd.content==""){
					return false;
				}
				
				return true;
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
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//获取用户信息
				$scope.getUserInfo();
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