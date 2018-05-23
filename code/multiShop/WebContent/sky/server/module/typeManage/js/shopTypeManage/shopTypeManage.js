typeManageModule.controller("shopTypeManageCtrl", 
function($timeout, $scope, $filter, $document, $controller, serverIndexHttpService){
	//继承
	$controller("typeManageCtrl", {$scope : $scope});
	
	//查询条件
	$scope.condition = {
			tableName	: common.tableContants.TB_SHOP,
			parentId		: common.typetContants.rootParentId,
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//判断当前类型是否有权限待在当前页面
		serverCommon.hasRightStay('shop_type');
		
		//改变当前导航指向
		serverCommon.navChange("#/shopType");
		//获取类型列表
		$scope.getTypetList();
		//令提示可用
		$('[data-toggle="tooltip"]').tooltip();
	};
	$document.ready($scope.initFunc);
});
