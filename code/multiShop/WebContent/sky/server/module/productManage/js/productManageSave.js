angular.module('productManageSave',[])
.directive('productManageSave',function(){
	return {
		restrict:'E',
		scope : {
			product			: "=",
		},
		templateUrl : $contextPath +"/sky/server/module/productManage/template/productManageSave.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			/**
			 * 新增保存产品
			 */
			$scope.saveProduct = function(product){
				if(!$scope.isSaveProduct(product)){
					common.triggerFailMesg("请按格式规范填写");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveProduct(product)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedProductList();
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
			 * 编辑保存产品
			 */
			$scope.editProduct = function(product){
				if(!$scope.isSaveProduct(product)){
					common.triggerFailMesg("请按格式规范填写");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editProduct(product)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedProductList();
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
			 * 判断该产品对象是否符合保存的条件
			 */
			$scope.isSaveProduct = function(product){
				if(!product){
					return false;
				}
				
				//赋值商品的描述
				product.description = $scope.$parent.productDesEditor.html();
				
				//店铺用户，则赋值其店铺ID
				if($currentUser.shopId!=common.shopContants.shopSystem){
					product.shopId = $currentUser.shopId;
				}
				
				if(!product.name || product.name==""){
					return false;
				}else if(product.name.length>35){
					return false;
				}
				
				if(!product.shopId || product.shopId==""){
					return false;
				}
				
				if(!product.proType || product.proType==""){
					return false;
				}
				
				if(null==product.status || undefined==product.status){
					return false;
				}
				
				if(null==product.price || undefined==product.price){
					return false;
				}
				
				if(null==product.proStock || undefined==product.proStock){
					return false;
				}
				
				return true;
			};
			
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//初始化商品描述的kindeditor
				$scope.$parent.productDesEditor = serverCommon.initKindEditor("#productDesId");
			};
			$document.ready($scope.initFunc);
		}
	};
});