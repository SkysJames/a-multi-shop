angular.module('productManageSave',[])
.directive('productManageSave',function(){
	return {
		restrict:'E',
		scope : {
			product			: "=",
			getProductList	: "&",
		},
		templateUrl : $contextPath +"/sky/server/directive/productManage/template/productManageSave.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService, productManageService){
			//产品类型列表
			$scope.proTypeList = common.productContants.proType;
			
			/**
			 * 新增保存产品
			 */
			$scope.saveProduct = function(product){
				if(!$scope.isSaveProduct(product)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				product.description = $scope.$root.editor.html();
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveProduct(product)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.getProductList();
						$scope.$root.returnPanel();
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
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				product.description = $scope.$root.editor.html();
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editProduct(product)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.getProductList();
						$scope.$root.returnPanel();
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
				
				if(!product.name || product.name==""){
					return false;
				}
				
				if(product.proType==null || product.proType==undefined){
					return false;
				}
				
				return true;
			};
		}
	};
});