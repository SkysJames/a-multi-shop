angular.module('productManage.module',['productManageSave'])
.controller("productManageCtrl",['$timeout', '$scope', '$rootScope', '$filter', '$document', '$sce', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $filter, $document, $sce, serverIndexHttpService){
	$scope.sce = $sce;
	//保存页面类型，null-不展示，'save'-添加，'edit'-编辑
	$scope.saveType = null;
	//图片面板ID
    $scope.imagePanelId = "productImagePanelId";
    //图片路径列表
    $scope.imagePathList = [];
	//店铺查询条件
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 10,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 1,		//总页码数
			shopId		: ($currentUser.shopId==common.shopContants.shopSystem?"":$currentUser.shopId),
			keywords		: "",
			proType		: "",
			status		: "",
	};
	
	/**
     * 查看显示图片面板
     */
    $scope.showImagePanel = function(imagePathList){
    		$scope.imagePathList = imagePathList;
    		$("#" + $scope.imagePanelId).modal({
    			backdrop : true,
    			keyboard : false,
    			show   : true,
    		});
      
    		var scope = angular.element($('#' + $scope.imagePanelId)).scope();
    		if(scope){
    			$timeout(scope.initImage, 100);
    		}
    };
	
	/**
	 * 切换面板，显示/隐藏编辑面板
	 */
	$scope.togglePanel = function(saveType, selectedObj){
		$scope.saveType = saveType;
		if(selectedObj){
			$scope.productSave = _.cloneDeep(selectedObj);
			$scope.productDesEditor.html($scope.productSave.description);
		}else{
			$scope.productSave = null;
			$scope.productDesEditor.html("");
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
	$scope.pagedProductList = function(pageNo){
		$scope.packageCondition(pageNo);
		
		//获取过滤条件字符串
		$scope.getFilterText();
		
		$scope.isLoadingProduct = true;
		serverIndexHttpService.pagedProductList($scope.condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200" && data.pager){
				$scope.productList = data.pager.resultList;
				
				$scope.condition.totalCount = data.pager.totalCount;
				$scope.condition.pageCount = data.pager.pageCount;
				$scope.isLoadingProduct = false;
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
			
			if($scope.isAdminRight && ""!=$scope.condition.shopId){
				for(var i=0;i<$scope.shopAll.length;i++){
					var s = $scope.shopAll[i];
					if(s.id == $scope.condition.shopId){
						$scope.filterText += "商品所属（" + s.name + "）+ ";
						break;
					}
				}
			}
			
			if(""!=$scope.condition.proType){
				for(var i=0;i<$scope.typetList.length;i++){
					var s = $scope.typetList[i];
					if(s.id == $scope.condition.proType){
						$scope.filterText += "商品类型（" + s.name + "）+ ";
						break;
					}
				}
			}
			
			if(""!=$scope.condition.keywords){
				$scope.filterText += "模糊搜索（" + $scope.condition.keywords + "）+ "
			}
			if(""!=$scope.condition.status){
				$scope.filterText += "商品状态（" + $filter("stringProductStatus")($scope.condition.status) + "）+ "
			}
		}
		
		if($scope.filterText.length > 0){
			$scope.filterText = $scope.filterText.substring(0, $scope.filterText.length-2);
		}
	};
	
	/**
	 * 删除店铺
	 */
	$scope.deleteProduct = function(product){
		common.triggerAlertMesg("确定要删除商品 " + product.name + "？", "", function(){}, function(){
			serverIndexHttpService.deleteProduct(product)
			.then(function(response){
				var data = response.data;
				if(data && data.statusCode=="200"){
					common.triggerSuccessMesg(data.message);
					$scope.pagedProductList();
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
				shopId		: ($currentUser.shopId==common.shopContants.shopSystem?"":$currentUser.shopId),
				tableName	: common.tableContants.TB_PRODUCT,
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
	 * 获取所有店铺
	 */
	$scope.getAllShopList = function(){
		serverIndexHttpService.getAllShopList()
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200" && data.shopAll){
				$scope.shopAll = data.shopAll;
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
		serverCommon.hasRightStay('product_manage');
		//改变当前导航指向
		serverCommon.navChange("#/product");
		//初始化当前登陆用户在当前页面是否为管理员权限
		$scope.isAdminRight = serverCommon.isAdminRight('product_manage');
		
		if($scope.isAdminRight){
			//获取所有店铺
			$scope.getAllShopList();
		}
		//获取类型列表
		$scope.getTypetList();
		//获取店铺列表
		$scope.pagedProductList();
		
		//令提示可用
		$('[data-toggle="tooltip"]').tooltip();
	};
	$document.ready($scope.initFunc);
}]);
