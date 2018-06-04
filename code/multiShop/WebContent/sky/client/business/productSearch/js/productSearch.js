angular.module('productSearchApp',["client-index.filter","client-index.httpService","indexHeader"].concat($commonDirectiveList).concat($directiveList))
.controller("productSearchCtrl",['$timeout', '$scope', '$document', 'clientIndexHttpService', 
function($timeout, $scope, $document, clientIndexHttpService){
	//搜索条件对象
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 30,	//每页数据量
			pageCount	: 1,
			totalCount	: 0,
			isOver		: "0",	//未过期
			keywords		: keywords,	//搜索关键字
	};
	//当前被选中的类型对象
	$scope.selectedType = {};
	
	/**
	 * 当前页面跳到指定位置
	 */
	$scope.scrollTo = function(target){
		common.scrollTo(target);
	};
	
	/**
	 * 加载更多商品
	 */
	$scope.loadMoreProduct = function(){
		if($scope.condition && $scope.condition.pageNo<$scope.condition.pageCount){
			$scope.condition.pageNo++;
			$scope.pagedProductList(true);
		}
	};
	
	
	/**
	 * 获取系统公告消息
	 */
	$scope.getIndexAnsList = function() {
		var condition = {
				shopId	: common.shopContants.shopSystem,
				status	: common.announceContants.status.USING,
		};
		clientIndexHttpService.getAnnounceList(condition)
		.then(function(response){
			$scope.indexAns = response.data.list;
			if($scope.indexAns && $scope.indexAns.length>0){
				for ( var int = 0; int < $scope.indexAns.length; int++) {
					$scope.indexAns[int].seq = int+1;
				}
			}
		});
	};
	
	/**
	 * 获取店铺类型列表
	 */
	$scope.getTypeList = function(){
		var condition = {
				tableName	: common.tableContants.TB_SHOP,	//店铺表名
				parentId		: common.typetContants.rootParentId,//一级类别
		};
		
		clientIndexHttpService.getTypeList(condition)
		.then(function(response){
			var data = response.data;
			$scope.typeList = data.list;
			console.log($scope.typeList);
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 获取商品列表
	 */
	$scope.pagedProductList = function(isMore){
		$scope.isLoadingProduct = true;
		clientIndexHttpService.pagedProductList($scope.condition)
		.then(function(response){
			var data = response.data;
			
			if(!isMore || !$scope.productList || $scope.productList.length==0){
				$scope.productList = data.pager.resultList;
			}else{
				$scope.productList = $scope.productList.concat(data.pager.resultList);
			}
			
			$scope.condition.pageCount = data.pager.pageCount;
			$scope.condition.totalCount = data.pager.totalCount;
			$scope.isLoadingProduct = false;
			
			if($scope.productList && $scope.productList.length>0){
				$(".shop-product").fadeIn("slow");
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//初始化公告消息列表
		$scope.getIndexAnsList();
		//获取店铺类型
		$scope.getTypeList();
		//初始化商品列表
		$scope.pagedProductList();
		
		//页面滚动事件
		$(window).scroll(function(){
			//判断是否滚动到底部
			if($(window).scrollTop() + $(window).height() == $(document).height()){
				$scope.loadMoreProduct();
			}
		});
	};
	$document.ready($scope.initFunc);
	
}]);