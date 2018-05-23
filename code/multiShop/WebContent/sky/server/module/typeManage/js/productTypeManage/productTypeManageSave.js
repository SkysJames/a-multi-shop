angular.module('productTypeManageSave',[])
.directive('productTypeManageSave',function(){
	return {
		restrict:'E',
		scope : {
			typet			: "=",
		},
		templateUrl : $contextPath +"/sky/server/module/typeManage/template/productTypeManage/productTypeManageSave.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			/**
			 * 新增保存用户
			 */
			$scope.saveTypet = function(typet){
				if(!$scope.isSaveTypet(typet)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveTypet(typet)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.getTypetList();
						$scope.$parent.togglePanel(null);
					}else{
						common.triggerFailMesg(data.message);
					}
					$scope.isLoadingSave = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 编辑保存用户
			 */
			$scope.editTypet = function(typet){
				if(!$scope.isSaveTypet(typet)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editTypet(typet)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.getTypetList();
						$scope.$parent.togglePanel(null);
					}else{
						common.triggerFailMesg(data.message);
					}
					$scope.isLoadingSave = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 判断该用户对象是否符合保存的条件
			 */
			$scope.isSaveTypet = function(typet){
				if(!typet){
					return false;
				}
				
				typet.tableName = common.tableContants.TB_PRODUCT;
				
				if($currentUser.shopId && common.shopContants.shopSystem!=$currentUser.shopId){
					typet.shopId = $currentUser.shopId;
				}
				
				if(!typet.shopId || typet.shopId==""){
					return false;
				}
				
				if(!typet.name || typet.name==""){
					return false;
				}
				
				return true;
			};
			
			/**
			 * 获取所有店铺
			 */
			$scope.getAllShopList = function(){
				serverIndexHttpService.getAllShopList()
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.shopAll){
						$scope.shopAll = data.shopAll;
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 初始化当前登陆用户的权限
			 */
			$scope.initCurrentRight = function(){
				//是否为管理员权限
				$scope.isAdminRight = false;
				if($currentUser.rightgroups && $currentUser.rightgroups.indexOf(common.rightGroupContants.adminRightgroup)>-1){
					$scope.isAdminRight = true;
				}
			};
			
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//初始化当前登陆用户的权限
				$scope.initCurrentRight();
				//获取所有店铺
				$scope.getAllShopList();
			};
			$document.ready($scope.initFunc);
		}
	};
});