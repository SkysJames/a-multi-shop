angular.module('shopListManage.module',['shopListManageSave'])
.controller("shopListManageCtrl",['$timeout', '$scope', '$rootScope', '$filter', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $filter, $document, serverIndexHttpService){
	//保存页面类型，null-不展示，'save'-添加，'edit'-编辑
	$scope.saveType = null;
	//店铺查询条件
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 10,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 1,		//总页码数
			moreStatus	: common.shopContants.status.INEXIST,	//大于此状态，即实际存在的店铺
			keywords		: "",
			shopType		: "",
			status		: "",
			recommend	: "",
			isOver		: "",
	};
	
	/**
	 * 切换面板，显示/隐藏编辑面板
	 */
	$scope.togglePanel = function(saveType, selectedObj){
		$scope.saveType = saveType;
		if(selectedObj){
			$scope.shopSave = _.cloneDeep(selectedObj);
			$("#overTimeId").val($scope.shopSave.overTimeString);
		}else{
			$scope.shopSave = null;
			$("#overTimeId").val("");
		}
	};
	
	/**
	 * 封装查询条件
	 */
	$scope.packageCondition = function(pageNo){
		//页码
		if(pageNo){
			$scope.condition.pageNo = pageNo;
		}else{
			$scope.condition.pageNo = 1;
		}
	};
	
	/**
	 * 获取店铺列表
	 */
	$scope.pagedShopList = function(pageNo){
		$scope.packageCondition(pageNo);
		
		//获取过滤条件字符串
		$scope.getFilterText();
		
		$scope.isLoadingShop = true;
		serverIndexHttpService.pagedShopList($scope.condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200" && data.pager){
				$scope.shopList = data.pager.resultList;
				
				$scope.condition.totalCount = data.pager.totalCount;
				$scope.condition.pageCount = data.pager.pageCount;
				$scope.isLoadingShop = false;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 获取过滤条件字符串
	 */
	$scope.getFilterText = function(){
		$scope.filterText = "";
		if($scope.condition){
			
			if(""!=$scope.condition.shopType){
				for(var i=0;i<$scope.typetList.length;i++){
					var s = $scope.typetList[i];
					if(s.id == $scope.condition.shopType){
						$scope.filterText += "店铺类型（" + s.name + "）+ ";
						break;
					}
				}
			}
			
			if(""!=$scope.condition.keywords){
				$scope.filterText += "模糊搜索（" + $scope.condition.keywords + "）+ "
			}
			if(""!=$scope.condition.status){
				$scope.filterText += "店铺状态（" + $filter("stringShopStatus")($scope.condition.status) + "）+ "
			}
			if(""!=$scope.condition.recommend){
				$scope.filterText += "店铺级别（" + $filter("showRecommend")($scope.condition.recommend) + "）+ "
			}
			if(""!=$scope.condition.isOver){
				$scope.filterText += "是否过期（" + ($scope.condition.isOver=="1"?"已过期":"未过期") + "）+ ";
			}
		}
		
		if($scope.filterText.length > 0){
			$scope.filterText = $scope.filterText.substring(0, $scope.filterText.length-2);
		}
	};
	
	/**
	 * 删除店铺
	 */
	$scope.deleteShop = function(shop){
		common.triggerAlertMesg("确定要删除店铺 " + shop.name + "？", "", function(){}, function(){
			serverIndexHttpService.deleteShop(shop)
			.then(function(response){
				var data = response.data;
				if(data && data.statusCode=="200"){
					common.triggerSuccessMesg(data.message);
					$scope.pagedShopList();
				}else{
					common.triggerFailMesg(data.message);
				}
			},function(err){
				console.log(err);
			});
		}, $scope);
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
				$scope.typetList = data.typetList;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//判断当前店铺是否有权限待在当前页面
		serverCommon.hasRightStay('shop_list');
		//改变当前导航指向
		serverCommon.navChange("#/shopList");
		//获取类型列表
		$scope.getTypetList();
		//获取店铺列表
		$scope.pagedShopList();
		
		//令提示可用
		$('[data-toggle="tooltip"]').tooltip();
	};
	$document.ready($scope.initFunc);
}]);
