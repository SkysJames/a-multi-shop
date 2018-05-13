angular.module('productDetail',[])
.directive('productDetail',function(){
	return {
		restrict:'E',
		scope : {
			currentItem	: "=",
		},
		templateUrl : $contextPath +"/sky/client/business/product/template/productDetail.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, $sce, clientIndexHttpService){
			$scope.sce = $sce;
			
			/**
			 * 获取产品列表
			 */
			$scope.getProductList = function(pageNo){
				var condition = {
						pageNo		: 1,		//当前页码
						pageSize		: 3,		//每页数据量
						proType		: -1,
				};
				
				$scope.isLoadingProduct = true;
				clientIndexHttpService.getProductList(condition)
				.then(function(response){
					var data = response.data;
					$scope.productList = data.pager.resultList;
					$scope.isLoadingProduct = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//获取产品列表
				$scope.getProductList();
			};
			$document.ready($scope.initFunc);
		}
	};
});