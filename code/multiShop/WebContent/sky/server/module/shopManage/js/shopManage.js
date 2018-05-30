angular.module('shopManage.module',[])
.controller("shopManageCtrl",['$timeout', '$scope', '$rootScope', '$filter', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $filter, $document, serverIndexHttpService){
	//是否为编辑状态
	$scope.isEdit = false;
	//图片面板的路径列表
	$scope.imagePathList = [];
	
	/**
	 * 切换编辑状态
	 */
	$scope.toggleEdit = function(isEdit){
		$scope.isEdit = isEdit;
		if(!$scope.isEdit){
			$scope.shopInfo = _.cloneDeep($scope.shopInfoBak);
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
				
				if($scope.shopInfo.service && $scope.shopInfo.service.length>0){
					$scope.shopInfo.serviceUserIds = $scope.shopInfo.service.split(",");
				}else{
					$scope.shopInfo.serviceUserIds = [];
				}
				
				$scope.shopInfoBak = _.cloneDeep($scope.shopInfo);//备份
				
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
			common.triggerFailMesg("请按照要求填写信息");
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
	};
	$document.ready($scope.initFunc);
}]);
