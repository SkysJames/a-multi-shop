angular.module('aboutPage.module',[])
.controller("aboutPageCtrl",['$timeout', '$interval', '$scope', '$sce', '$filter', '$document', "clientIndexHttpService", 
function($timeout, $interval, $scope, $sce, $filter, $document, clientIndexHttpService){
	$scope.sce = $sce;
	//根作用域
	$scope.shopIndexScope = angular.element($('#shopIndexId')).scope();
	//选中的导航
	$scope.selectedNav = "about";
	
	
	/**
	 * 跳转到手机端的百度地图界面
	 */
	$scope.toPhoneMap = function(){
		window.location.href = $contextPath + "/sky/client/business/phoneMap/phoneMap.html?" +
				"title=" + $scope.shopIndexScope.shopInfo.name + "&content=" + $scope.shopIndexScope.shopInfo.address +
				"&lng=" + $scope.shopIndexScope.shopInfo.longitude + "&lat=" + $scope.shopIndexScope.shopInfo.latitude;
	};
	
	/**
	 * 在地图上定位店铺位置
	 */
	$scope.mapShopPosition = function(){
		$scope.shopMap.centerAndZoom($scope.shopPoint, 14);
	};
	
	/**
	 * 初始化地图，店铺位置
	 */
	$scope.initMap = function(){
		if(!$scope.shopIndexScope.shopInfo.longitude || !$scope.shopIndexScope.shopInfo.latitude){
			$scope.shopIndexScope.shopInfo.longitude = $defaultBmaps[0];
			$scope.shopIndexScope.shopInfo.latitude = $defaultBmaps[1];
		}
		
		$scope.shopMap = new BMap.Map("shopMap");
		
		//添加可见的缩放控件
		$scope.shopMap.addControl(new BMap.NavigationControl());
		//添加当前地图的缩放比例
		$scope.shopMap.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT}));
		
		//地图滚动缩放
		$scope.shopMap.enableScrollWheelZoom();
		//地图滚动惯性缩放
		$scope.shopMap.enableContinuousZoom();
		//地图惯性拖拽
		$scope.shopMap.enableInertialDragging();
		
		//店铺的定位图标
		$scope.shopPoint = new BMap.Point($scope.shopIndexScope.shopInfo.longitude, $scope.shopIndexScope.shopInfo.latitude);
		$scope.shopMarker = new BMap.Marker($scope.shopPoint);
		$scope.shopMap.centerAndZoom($scope.shopPoint, 14);
		$scope.shopMap.addOverlay($scope.shopMarker);
		//图标的点击事件
		$scope.shopMarker.addEventListener("click", function(){
			var infoWindow = new BMap.InfoWindow($scope.shopIndexScope.shopInfo.address, {title:$scope.shopIndexScope.shopInfo.name});
			$scope.shopMap.openInfoWindow(infoWindow, $scope.shopPoint);
		});
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//定时任务加载地图
		$scope.intervalMap = $interval(function(){
			if($scope.shopIndexScope.shopInfo.id){
				//初始化地图
				$scope.initMap();
				//去掉定时任务
				$interval.cancel($scope.intervalMap);
			}
		},1000);
		
		//还原屏幕
		$(".sh-phone").removeClass("sh-phone-scroll");
		$(".shop-index-page").removeClass("shop-index-page-scroll");
		
		//手机端的详情面板滚动事件
		$(".sa-content").scroll(function(){
			var allHeight = $(".sa-content>.sa-for-scroll").height();
			
			//判断向下滚动是否大于0，且可扩大屏幕
			if($(".sa-content").scrollTop() > 0 
					&& allHeight > ($(".sa-content").height() + $(".sn-phone").height() + $(".sh-phone").height())){
				$(".sh-phone").addClass("sh-phone-scroll");
				$(".shop-index-page").addClass("shop-index-page-scroll");
			}
			
			//判断是否滚动到div的顶部
			if($(".sa-content").scrollTop() == 0){
				$(".sh-phone").removeClass("sh-phone-scroll");
				$(".shop-index-page").removeClass("shop-index-page-scroll");
			}
		});
	};
	$document.ready($scope.initFunc);
}]);
