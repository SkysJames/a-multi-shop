angular.module('productHeader',[])
.directive('productHeader',function(){
	return {
		restrict:'E',
		scope : {
			shopInfo		: "=",
		},
		templateUrl : $contextPath +"/sky/client/component/productHeader/template/productHeader.html",
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
					window.location.href = $contextPath + "/home/shop-index?shopId=" + $scope.shopInfo.id;
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
						tableName	: common.tableContants.TB_PRODUCT,
						userId		: $currentUser.userId,
						objId		: productId,
						href			: "/home/product-index?productId=" + productId,
				};
				
				clientIndexHttpService.saveOrUpdateProhistory(collectObj)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200"){
						//更新收藏列表
						$scope.clientTopScope.getCollectList(common.tableContants.TB_PRODUCT);
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
						$scope.clientTopScope.getCollectList(common.tableContants.TB_PRODUCT);
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