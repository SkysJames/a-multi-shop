angular.module('shopSearchApp',["client-index.filter","client-index.httpService","indexHeader"].concat($commonDirectiveList).concat($directiveList))
.controller("shopSearchCtrl",['$timeout', '$interval', '$scope', '$document', 'clientIndexHttpService', 
function($timeout, $interval, $scope, $document, clientIndexHttpService){
	//店铺类型列表
	$scope.typeList = [];
	//二级店铺类型列表
	$scope.twoTypeList = [];
	//搜索条件对象
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 30,	//每页数据量
			pageCount	: 1,
			totalCount	: 0,
			isOver		: "0",	//未过期
			status		: common.shopContants.status.USING,	//状态启用
			shopType		: type,	//店铺类型
			keywords		: keywords,	//搜索关键字
	};
	//当前被选中的类型对象
	$scope.selectedType = {};
	//是否一级分类更多
	$scope.isOneTypeMore = false;
	//是否二级分类更多
	$scope.isTwoTypeMore = false;
	
	/**
	 * 跳转页面
	 */
	$scope.toPage = function(url, flag){
		common.toPage($contextPath + url, flag);
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
		if($scope.condition && $scope.condition.pageNo<$scope.condition.pageCount && !$scope.isLoadingShop){
			$scope.condition.pageNo++;
			$scope.pagedShopList(true);
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
			//初始化被选中的类型
			$scope.initSelectedType($scope.condition.shopType);
			//初始化店铺列表
			$scope.pagedShopList();
			
			$(".index-condition").fadeIn("slow");
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 获取店铺列表
	 */
	$scope.pagedShopList = function(isMore){
		$scope.isLoadingShop = true;
		clientIndexHttpService.pagedShopList($scope.condition)
		.then(function(response){
			var data = response.data;
			
			if(!isMore || !$scope.shopList || $scope.shopList.length==0){
				$scope.shopList = data.pager.resultList;
			}else{
				$scope.shopList = $scope.shopList.concat(data.pager.resultList);
			}
			
			$scope.condition.pageCount = data.pager.pageCount;
			$scope.condition.totalCount = data.pager.totalCount;
			$scope.isLoadingShop = false;
			
			$(".index-shop").fadeIn("slow");
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 选择一级类型
	 */
	$scope.selectOneType = function(oneType){
		if(oneType){
			$scope.twoTypeList = oneType.typetList;
			if($scope.twoTypeList && $scope.twoTypeList.length>0){
				$scope.selectedType = $scope.twoTypeList[0];
			}else{
				$scope.selectedType = {
						id			: oneType.id,
						parentId		: oneType.id,
						parentName	: oneType.name,
				};
			}
		}else{//全部
			$scope.selectedType = {};
			$scope.twoTypeList = [];
		}
		$scope.condition.shopType = $scope.selectedType.id;
		$scope.pagedShopList();
	};
	
	/**
	 * 选择二级类型
	 */
	$scope.selectTwoType = function(twoType){
		if(twoType){
			$scope.selectedType = twoType;
			$scope.condition.shopType = $scope.selectedType.id;
			$scope.pagedShopList();
		}
	};
	
	/**
	 * 初始化被选中的类型
	 */
	$scope.initSelectedType = function(type){
		$scope.condition.shopType = type;
		if(!type || type==""){
			$scope.selectedType = {};
			return;
		}
		
		for(var i=0; i<$scope.typeList.length; i++){
			var oneType = $scope.typeList[i];
			if(oneType.typetList && oneType.typetList.length>0){
				for(var j=0; j<oneType.typetList.length; j++){
					var twoType = oneType.typetList[j];
					if(twoType.id == type){
						$scope.twoTypeList = oneType.typetList;
						$scope.selectedType = twoType;
						return;
					}
				}
			}
		}
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//初始化公告消息列表
		$scope.getIndexAnsList();
		//初始化店铺类型列表
		$scope.getTypeList();
		
		//页面滚动事件
		$(window).scroll(function(){
			//判断是否滚动到底部
			if($(window).scrollTop() >= ($(document).height() - $(window).height())){
				$scope.loadMoreShop();
			}
		});
		
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
	
}]);