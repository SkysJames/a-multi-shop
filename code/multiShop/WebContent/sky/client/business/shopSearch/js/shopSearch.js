angular.module('shopSearchApp',["client-index.filter","client-index.httpService","indexHeader"].concat($commonDirectiveList).concat($directiveList))
.controller("shopSearchCtrl",['$timeout', '$scope', '$document', 'clientIndexHttpService', 
function($timeout, $scope, $document, clientIndexHttpService){
	//店铺类型列表
	$scope.typeList = [];
	//搜索条件对象
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 30,	//每页数据量
			isOver		: "0",	//未过期
			shopType		: type,	//店铺类型
			keywords		: keywords,	//店铺类型
	};
	
	/**
	 * 当前页面跳到指定位置
	 */
	$scope.scrollTo = function(target){
		common.scrollTo(target);
	};
	
	/**
	 * 加载更多店铺
	 */
	$scope.loadMoreShop = function(){
		//加载下一个店铺类型的店铺列表
		if($scope.twoTypeList && $scope.twoTypeList.length>0 && !$scope.isLoadingShop){
			for(var i=0; i<$scope.twoTypeList.length; i++){
				var twoType = $scope.twoTypeList[i];
				if(!twoType.shopList){
					$scope.pagedShopList(twoType);
					return;
				}
			}
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
			
			//初始化第一个店铺类型的店铺列表
			if($scope.twoTypeList && $scope.twoTypeList.length>0){
				$scope.pagedShopList($scope.twoTypeList[0]);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 获取店铺列表
	 */
	$scope.pagedShopList = function(){
		$scope.isLoadingShop = true;
		clientIndexHttpService.pagedShopList($scope.condition)
		.then(function(response){
			var data = response.data;
			$scope.shopList = data.pager.resultList;
			$scope.isLoadingShop = false;
			
			$(".index-shop").fadeIn("slow");
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
		//初始化店铺列表
		$scope.pagedShopList();
		
		//页面滚动事件
		$(window).scroll(function(){
			//判断是否滚动到底部
			if($(window).scrollTop() + $(window).height() == $(document).height()){
				$scope.loadMoreShop();
			}
		});
	};
	$document.ready($scope.initFunc);
	
}]);