angular.module('shopManage.module',[])
.controller("shopManageCtrl",['$timeout', '$scope', '$sce', '$filter', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $sce, $filter, $document, serverIndexHttpService){
	$scope.sce = $sce;
	//是否为编辑状态
	$scope.isEdit = false;
	//图片面板的路径列表
	$scope.imagePathList = [];
	
	/**
	 * 切换编辑状态
	 */
	$scope.toggleEdit = function(isEdit){
		$scope.isEdit = isEdit;
		if(!$scope.isEdit){//不可编辑状态
			$scope.shopInfo = _.cloneDeep($scope.shopInfoBak);
			$scope.toggleMapClick(false);
		}else{//编辑状态
			$scope.toggleMapClick(true);
		}
	};
	
	/**
	 * 切换地图是否可点击
	 */
	$scope.toggleMapClick = function(isClick){
		//判断地图是否存在
		if($scope.addressMap){
			if(isClick){
				//关闭地图打开的信息窗口
				$scope.addressMap.closeInfoWindow();
				//地图滚动缩放
				$scope.addressMap.enableScrollWheelZoom();
				//地图滚动惯性缩放
				$scope.addressMap.enableContinuousZoom();
				//地图惯性拖拽
				$scope.addressMap.enableInertialDragging();
				//添加城市列表搜索
				$scope.addressMap.addControl($scope.cityControl);
				//添加地图的点击事件
				$scope.addressMap.addEventListener("click",function(e){
					$timeout(function(){
						$scope.shopInfo.longitude = e.point.lng;
						$scope.shopInfo.latitude = e.point.lat;
						
						//删除原来的定位
						$scope.addressMap.removeOverlay($scope.addressMarker);
						//创建新的定位
						$scope.addressPoint = new BMap.Point($scope.shopInfo.longitude, $scope.shopInfo.latitude);
						$scope.addressMarker = new BMap.Marker($scope.addressPoint);
						$scope.addressMap.addOverlay($scope.addressMarker);
					}, 100);
				});
			}else{
				if(!$scope.shopInfo.longitude || !$scope.shopInfo.latitude){
					$scope.shopInfo.longitude = $defaultBmaps[0];
					$scope.shopInfo.latitude = $defaultBmaps[1];
				}
				
				//地图滚动缩放
				$scope.addressMap.disableScrollWheelZoom();
				//地图滚动惯性缩放
				$scope.addressMap.disableContinuousZoom();
				//地图惯性拖拽
				$scope.addressMap.disableInertialDragging();
				//添加城市列表搜索
				$scope.addressMap.removeControl($scope.cityControl);
				//移除地图的点击事件
				$scope.addressMap.removeEventListener("click");
				
				//店铺的定位图标
				$scope.addressPoint = new BMap.Point($scope.shopInfo.longitude, $scope.shopInfo.latitude);
				$scope.addressMarker = new BMap.Marker($scope.addressPoint);
				$scope.addressMap.centerAndZoom($scope.addressPoint, 18);
				$scope.addressMap.addOverlay($scope.addressMarker);
				//图标的点击事件
				$scope.addressMarker.addEventListener("click", function(){
					var infoWindow = new BMap.InfoWindow($scope.shopInfo.address, {title:$scope.shopInfo.name});
					$scope.addressMap.openInfoWindow(infoWindow, $scope.addressPoint);
				});
			}
		}
		
	};
	
	/**
	 * 获取店铺信息
	 */
	$scope.getShopInfo = function(){
		$scope.isLoadingShopInfo = true;
		serverIndexHttpService.getShopById($currentUser.shopId)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200"){
				$scope.shopInfo = data.shop;
				$scope.shopDesEditor.html($scope.shopInfo.description);
				
				if($scope.shopInfo.service && $scope.shopInfo.service.length>0){
					$scope.shopInfo.serviceUserIds = $scope.shopInfo.service.split(",");
				}else{
					$scope.shopInfo.serviceUserIds = [];
				}
				
				//备份
				$scope.shopInfoBak = _.cloneDeep($scope.shopInfo);
				//初始化店铺地址地图
				$scope.initMap();
				
				$scope.isLoadingShopInfo = false;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 保存店铺信息
	 */
	$scope.saveShopInfo = function(shopInfo){
		if(!$scope.isSaveShop(shopInfo)){
			common.triggerFailMesg("请按格式规范填写");
			return;
		}
		
		$scope.isLoadingShopInfo = true;
		serverIndexHttpService.editShop(shopInfo)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200"){
				common.triggerSuccessMesg(data.message);
				$scope.getShopInfo();
				$scope.toggleEdit(false);
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 判断该店铺对象是否符合保存的条件
	 */
	$scope.isSaveShop = function(shop){
		if(!shop){
			return false;
		}
		
		//赋值店铺的描述
		shop.description = $scope.shopDesEditor.html();
		
		//店铺轮播图片的链接
		if(shop.picPathList){
			shop.picHrefList.length = shop.picPathList.length;
		}
		
		//店铺的客服人员
		if(shop.serviceUserIds){
			var service = "";
			for(var i=0;i<shop.serviceUserIds.length;i++){
				service += shop.serviceUserIds[i] + ",";
			}
			if(service.length>0){
				service = service.substring(0, service.length-1);
			}
			shop.service = service;
		}
		
		if(!shop.name || shop.name==""){
			return false;
		}else if(shop.name.length>35){
			return false;
		}
		if(!shop.shopType || shop.shopType==""){
			return false;
		}
		if(!shop.phone || shop.phone==""){
			return false;
		}
		
		if(shop.brief && shop.brief.length>250){
			return false;
		}
		
		return true;
	};
	
	/**
	 * 获取类型列表
	 */
	$scope.getTypetList = function(){
		var condition = {
				tableName	: common.tableContants.TB_SHOP,
				parentId		: common.typetContants.rootParentId,
		};
		serverIndexHttpService.getTypetList(condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200"){
				$scope.typetList = data.list;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 获取本店用户列表
	 */
	$scope.getUserList = function(){
		var condition = {
				shopId		: $currentUser.shopId,
		};
		serverIndexHttpService.getUserList(condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200"){
				$scope.userList = data.list;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 打开新窗口
	 */
	$scope.openNewPage = function(url){
		common.toPage(url);
	};
	
	/**
	 * 初始化地址地图
	 */
	$scope.initMap = function(){
		$scope.addressMap = new BMap.Map("addressMap");
		
		//添加可见的缩放控件
		$scope.addressMap.addControl(new BMap.NavigationControl());
		//添加当前地图的缩放比例
		$scope.addressMap.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT}));
		
		//城市列表控件
		$scope.cityControl = new BMap.CityListControl({
		    anchor: BMAP_ANCHOR_TOP_RIGHT,
		    offset: new BMap.Size(10, 20),
		})
		
		//初始化原无点击事件的地图
		$scope.toggleMapClick(false);
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//判断当前用户是否有权限待在当前页面
		serverCommon.hasRightStay('shop_basic');
		//改变当前导航指向
		serverCommon.navChange("#/shopBasic");
		//获取类型列表
		$scope.getTypetList();
		//获取用户列表
		$scope.getUserList();
		//获取店铺信息
		$scope.getShopInfo();
		//令提示可用
		$('[data-toggle="tooltip"]').tooltip();
		//初始化店铺描述的kindeditor
		$scope.shopDesEditor = serverCommon.initKindEditor("#shopDesId");
	};
	$document.ready($scope.initFunc);
}]);
