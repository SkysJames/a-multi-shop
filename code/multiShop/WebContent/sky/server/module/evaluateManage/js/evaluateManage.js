angular.module('evaluateManage.module',[])
.controller("evaluateManageCtrl",['$timeout', '$scope', '$rootScope', '$filter', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $filter, $document, serverIndexHttpService){
	//是否详细面板
	$scope.isDetail = false;
	//评论查询条件
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 10,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 1,		//总页码数
			tableName	: common.tableContants.TB_SHOP,
			objId		: ($currentUser.shopId==common.shopContants.shopSystem?"":$currentUser.shopId),
			status		: "",
			mark			: "",
			keywords		: "",
	};
	
	/**
	 * 切换面板，显示/隐藏详细面板
	 */
	$scope.togglePanel = function(selectedObj){
		selectedObj.isDetail = !selectedObj.isDetail;
		if($currentUser.shopId!=common.shopContants.shopSystem && 
				selectedObj && selectedObj.status==common.messageContants.status.NORECEIVE){
			selectedObj.status = common.messageContants.status.RECEIVED;
			$scope.editEvaluate(selectedObj);
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
	 * 获取评论列表
	 */
	$scope.pagedEvaluateList = function(pageNo){
		$scope.packageCondition(pageNo);
		
		$scope.isLoadingEvaluate = true;
		serverIndexHttpService.pagedEvaluateList($scope.condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200" && data.pager){
				$scope.evaluateList = data.pager.resultList;
				
				$scope.condition.totalCount = data.pager.totalCount;
				$scope.condition.pageCount = data.pager.pageCount;
				$scope.isLoadingEvaluate = false;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 保存评论
	 */
	$scope.editEvaluate = function(obj){
		serverIndexHttpService.editEvaluate(obj)
		.then(function(response){
			var data = response.data;
			
			//初始化未读评论的数量
			if(angular.element($('.server-index')).scope()){
				angular.element($('.server-index')).scope().initEvaluateCount();
			}
			
			if(data.statusCode!="200"){
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 删除评论
	 */
	$scope.deleteEvaluate = function(evaluate){
		common.triggerAlertMesg("确定要删除该评论？", "", function(){}, function(){
			serverIndexHttpService.deleteEvaluate(evaluate)
			.then(function(response){
				var data = response.data;
				if(data && data.statusCode=="200"){
					//初始化未读评论的数量
					if(angular.element($('.server-index')).scope()){
						angular.element($('.server-index')).scope().initEvaluateCount();
					}
					
					common.triggerSuccessMesg(data.message);
					$scope.pagedEvaluateList();
				}else{
					common.triggerFailMesg(data.message);
				}
			},function(err){
				console.log(err);
			});
		}, $scope);
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
		//判断当前评论是否有权限待在当前页面
		serverCommon.hasRightStay('shop_manage');
		//改变当前导航指向
		serverCommon.navChange("#/evaluate");
		//初始化当前登陆用户在当前页面是否为管理员权限
		$scope.isAdminRight = serverCommon.isAdminRight('shop_manage');
		
		if($scope.isAdminRight){
			//获取所有店铺
			$scope.getAllShopList();
		}
		
		//获取评论列表
		$scope.pagedEvaluateList();
		//令提示可用
		$('[data-toggle="tooltip"]').tooltip();
	};
	$document.ready($scope.initFunc);
}]);
