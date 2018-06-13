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
		controller : function($scope, $timeout, $interval, $filter, $document, clientIndexHttpService){
			//系统名称
			$scope.systemName = $systemName;
			//系统logo
			$scope.systemLogo = $systemLogo;
			//当前登录用户
			$scope.currentUser = $currentUser;
			//被收藏的对象
			$scope.collectedObj = null;
			
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
				if(window.history.length > 1){
					window.history.go(-1);
				}else{
					window.location.href = $contextPath;
				}
			};
			
			/**
			 * 跳到搜索页面
			 */
			$scope.toSearchPage = function(){
				if(shopAbout){
					common.triggerFailMesg("此店铺无商品可搜索");
					return;
				}
				
				if($scope.keywords){
					window.location.href = "#/indexPage/keywords/" + $scope.keywords;
				}else{
					window.location.href = "#/indexPage";
				}
			};
			
			/**
			 * 收藏/取消收藏
			 */
			$scope.toggleCollect = function(){
				if(!$scope.clientTopScope.isLogin()){
					return;
				}
				
				if($scope.collectedObj){
					$scope.deleteCollect();
				}else{
					$scope.addCollect();
				}
			};
			
			/**
			 * 收藏
			 */
			$scope.addCollect = function(){
				var collectObj = {
						type			: "2",//收藏类型
						tableName	: common.tableContants.TB_SHOP,
						userId		: $currentUser.userId,
						objId		: shopId,
						href			: "/home/shop-index?shopId=" + shopId,
				};
				
				clientIndexHttpService.saveOrUpdateProhistory(collectObj)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						//更新收藏列表
						$scope.clientTopScope.getCollectList(common.tableContants.TB_SHOP);
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 取消收藏
			 */
			$scope.deleteCollect = function(){
				clientIndexHttpService.deleteProhistory($scope.collectedObj)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						//更新收藏列表
						$scope.clientTopScope.getCollectList(common.tableContants.TB_SHOP);
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//定时任务加载获取顶部的作用域
				$scope.intervalTopScope = $interval(function(){
					if(angular.element($('#clientTopId')).scope()){
						//获取顶部页面的作用域
						$scope.clientTopScope = angular.element($('#clientTopId')).scope();
						//去掉定时任务
						$interval.cancel($scope.intervalTopScope);
					}
				},500);
				
			};
			$document.ready($scope.initFunc);
		}
	};
});