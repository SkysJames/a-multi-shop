angular.module('shopIndexApp',["ngRoute","client-index.filter","client-index.httpService","indexPage.module"].concat($commonDirectiveList).concat($directiveList))
.config(['$routeProvider', function($routeProvider){
	var defaultUrl = "/indexPage";
	 
	//店铺首页
	$routeProvider.when("/indexPage",{
		templateUrl	: $contextPath+'/sky/client/business/shopIndex/template/indexPage.html',
	});
	//商品关键字搜索
	$routeProvider.when("/indexPage/keywords/:keywords",{
		templateUrl	: $contextPath+'/sky/client/business/shopIndex/template/indexPage.html',
	});
	//商品类型搜索
	$routeProvider.when("/indexPage/type/:type",{
		templateUrl	: $contextPath+'/sky/client/business/shopIndex/template/indexPage.html',
	});
	
	$routeProvider.otherwise({
		redirectTo: defaultUrl
    });
	
}])
.controller("shopIndexCtrl",['$timeout', '$scope', '$document', 'clientIndexHttpService', 
function($timeout, $scope, $document, clientIndexHttpService){
	//选中的导航
	$scope.selectedNav = "index";
	//店铺轮播图片
	$scope.slideList = clientCommon.demoSliders;
	//店铺轮播图片链接
	$scope.slideHrefList = clientCommon.demoSliderHrefs;
	//商品类型列表
	$scope.typeList = [];
	//搜索关键字
	$scope.keywords = "";
	//店铺信息
	$scope.shopInfo = {};
	
	
	/**
	 * 当前页面跳到指定位置
	 */
	$scope.scrollTo = function(target){
		common.scrollTo(target);
	};
	
	/**
	 * 获取店铺信息
	 */
	$scope.getShopInfo = function(shopId){
		if(!shopId || shopId==""){
			console.log("传入的店铺id为空");
			return;
		}
		
		clientIndexHttpService.getShopById(shopId)
		.then(function(response){
			$scope.shopInfo = response.data.shop;
			
			if($scope.shopInfo){
				$scope.slideList = $scope.shopInfo.picPathList;
				$scope.slideHrefList = $scope.shopInfo.picHrefList;
				
				//其他操作
			}
		});
	};
	
	/**
	 * 获取店铺公告消息
	 */
	$scope.getIndexAnsList = function() {
		var condition = {
				shopId	: shopId,
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
	 * 获取店铺的商品类型列表
	 */
	$scope.getTypeList = function(shopId){
		var condition = {
				tableName	: common.tableContants.TB_PRODUCT,	//商品表名
				parentId		: common.typetContants.rootParentId,//一级类别
				shopId		: shopId,//所属店铺ID
		};
		
		clientIndexHttpService.getTypeList(condition)
		.then(function(response){
			var data = response.data;
			$scope.typeList = data.list;
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
		//获取店铺信息
		$scope.getShopInfo(shopId);
		//初始化类型列表
		$scope.getTypeList();
	};
	$document.ready($scope.initFunc);
	
}]);