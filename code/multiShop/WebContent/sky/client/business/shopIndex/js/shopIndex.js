angular.module('shopIndexApp',
		["ngRoute","client-index.filter","client-index.httpService","indexPage.module","aboutPage.module","evaluatePage.module"]
		.concat($commonDirectiveList).concat($directiveList)
)
.config(['$routeProvider', function($routeProvider){
	var defaultUrl = "/indexPage";
	
	//判断是否首页为商家信息
	if(shopAbout){
		defaultUrl = "/aboutPage";
	}
	 
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
	
	//店铺商家页面
	$routeProvider.when("/aboutPage",{
		templateUrl	: $contextPath+'/sky/client/business/shopIndex/template/aboutPage.html',
	});
	
	//店铺评价页面
	$routeProvider.when("/evaluatePage",{
		templateUrl	: $contextPath+'/sky/client/business/shopIndex/template/evaluatePage.html',
	});
	
	$routeProvider.otherwise({
		redirectTo: defaultUrl
    });
	
}])
.controller("shopIndexCtrl",['$timeout', '$scope', '$document', 'clientIndexHttpService', 
function($timeout, $scope, $document, clientIndexHttpService){
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
	//图片面板ID
    $scope.imagePanelId = "imagePanelId";
    //图片路径列表
    $scope.imagePathList = [];
    //当前图片序号
    $scope.currentImgIndex = 0;
	
    
    /**
	 * 查看显示图片面板
	 */
	$scope.showImagePanel = function(imagePathList, imgIndex){
		if(undefined != imgIndex){
			$scope.currentImgIndex = imgIndex;
		}
		$scope.imagePathList = imagePathList;
		$("#" + $scope.imagePanelId).modal("show");
	  
		var scope = angular.element($('#' + $scope.imagePanelId)).scope();
		if(scope){
			$timeout(scope.initImage, 100);
		}
	};
	
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
				$("title").text($scope.shopInfo.name);
				clientIndexHttpService.addShopPopularity($scope.shopInfo.id);
			}else{
				common.triggerFailMesg("该店铺已不存在");
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