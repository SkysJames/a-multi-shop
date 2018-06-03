angular.module('indexApp',["client-index.filter","client-index.httpService"].concat($commonDirectiveList).concat($directiveList))
.controller("indexCtrl",['$timeout', '$scope', '$document', 'clientIndexHttpService', 
function($timeout, $scope, $document, clientIndexHttpService){
	//系统轮播图片
	$scope.slideList = $systemPicture==""?clientCommon.demoSliders:$systemPicture.split(",");
	//系统轮播图片链接
	$scope.slideHrefList = $systemPictureHref==""?clientCommon.demoSliderHrefs:$systemPictureHref.split(",");
	//店铺类型列表（一级类型）
	$scope.oneTypeList = [];
	//店铺类型列表（二级类型）
	$scope.twoTypeList = [];
	//搜索关键字
	$scope.keywords = "";
	
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
			$scope.oneTypeList = data.list;
			
			//合并所有的二级店铺类型
			for(var i=0;i<$scope.oneTypeList.length;i++){
				if($scope.oneTypeList[i].typetList && $scope.oneTypeList[i].typetList.length>0){
					$scope.twoTypeList = $scope.twoTypeList.concat($scope.oneTypeList[i].typetList);
				}
			}
			
			//初始化第一个店铺类型的店铺列表
			if($scope.twoTypeList && $scope.twoTypeList.length>0){
				$scope.pagedShopList($scope.twoTypeList[0]);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 获取推荐店铺列表
	 */
	$scope.pagedReShopList = function(){
		var condition = {
				pageNo		: 1,		//当前页码
				pageSize		: 6,		//每页数据量
				recommend	: "1",	//推荐
				isOver		: "0",	//未过期
		};
		
		$scope.isLoadingReShop = true;
		clientIndexHttpService.pagedShopList(condition)
		.then(function(response){
			var data = response.data;
			$scope.reShopList = data.pager.resultList;
			$scope.isLoadingReShop = false;
			
			$(".index-recommend").fadeIn("slow");
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 获取店铺列表
	 */
	$scope.pagedShopList = function(typet){
		var condition = {
				pageNo		: 1,		//当前页码
				pageSize		: 6,		//每页数据量
				shopType		: typet.id,	//店铺类型
				isOver		: "0",	//未过期
		};
		
		$scope.isLoadingShop = true;
		clientIndexHttpService.pagedShopList(condition)
		.then(function(response){
			var data = response.data;
			typet.shopList = data.pager.resultList;
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
		//初始化推荐店铺列表
		$scope.pagedReShopList();
		//初始化类型列表
		$scope.getTypeList();
		
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