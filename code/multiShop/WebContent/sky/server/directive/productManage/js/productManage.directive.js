angular.module('productManage',[])
.service('productManageService',function($http,$filter,$timeout){
	$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest';
	$http.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.put['X-Requested-With'] = 'XMLHttpRequest';
	
})
.directive('productManage',function(){
	return {
		restrict:'E',
		scope : {
			
		},
		templateUrl : $contextPath +"/sky/server/directive/productManage/template/productManage.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, $sce, serverIndexHttpService){
			$scope.sce = $sce;
			//当前面板
			$scope.$root.currentPanel = $scope.$root.panelContants.INDEX_PANEL;
			//产品类型列表
			$scope.proTypeList = common.productContants.proType;
			//图片面板ID
			$scope.imagePanelId = "productImagePanelId";
			//图片路径列表
			$scope.imagePathList = [];
			//保存的产品对象
			$scope.productSave = {};
			//产品查询条件
			$scope.condition = {
					pageNo		: 1,		//当前页码
					pageSize		: 10,	//每页数据量
					totalCount	: 0,		//数据总量
					pageCount	: 1,		//总页码数
					keywords		: "",
					updateTimeA	: "",
					updateTimeZ	: "",
					proType		: -1,
			};
			//日期对象
			$scope.rangeDate = {
					startDate	: null,	//开始时间
					endDate		: null,	//结束时间
			};
			//筛选条件的日期选项
			$scope.dateRangePickerOptions = defaultDateRangePickerOptions;
			$scope.dateRangePickerOptions.eventHandlers = {
				'hide.daterangepicker' : function(event, picker){
					if($scope.rangeDate.startDate==null || $scope.rangeDate.endDate==null){
						$("#searchDateRangeId").val("");
					}
				},
				'apply.daterangepicker' : function(event, picker){
					$timeout(function(){
						$scope.rangeDate.startDate = picker.startDate;
						$scope.rangeDate.endDate = picker.endDate;
						if($scope.rangeDate.startDate==null || $scope.rangeDate.endDate==null){
							common.triggerFailMesg('请选择搜索的时间段!','');
							return;
						}
						$scope.getProductList();
					}, 100);
				},
			};
			
			/**
			 * 显示日期选择器
			 */
			$scope.showSearchDateRange = function(){
				$("#searchDateRangeId").data("daterangepicker").show();
			};
			
			/**
			 * 查看显示图片面板
			 */
			$scope.showImagePanel = function(imagePathList){
				$scope.imagePathList = imagePathList;
				$("#" + $scope.imagePanelId).modal({
					backdrop : true,
					keyboard : false,
					show	 : true,
				});
				
				var scope = angular.element($('#' + $scope.imagePanelId)).scope();
				if(scope){
					$timeout(scope.initImage, 100);
				}
			};
			
			/**
			 * 切换面板
			 */
			$scope.triggerPanel = function(panelContant, selectedObj){
				//顶部标题的切换
				$scope.$root.triggerTitle(panelContant);
				
				switch(panelContant){
				case $scope.$root.panelContants.INDEX_PANEL:
					$scope.getProductList();
					break;
				case $scope.$root.panelContants.SAVE_PANEL:
					$scope.productSave = {};
					$scope.$root.editor.html('');
					break;
				case $scope.$root.panelContants.EDIT_PANEL:
					$scope.productSave = _.cloneDeep(selectedObj);
					$scope.$root.editor.html($scope.productSave.description);
					break;
				}
			};
			
			/**
			 * 清空查询条件-日期
			 */
			$scope.clearDate = function(){
				$scope.rangeDate.startDate = null;
				$scope.rangeDate.endDate = null;
				
				$scope.getProductList();
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
				
				//更新的开始结束时间
				if($scope.rangeDate.startDate && $scope.rangeDate.endDate){
					$scope.condition.updateTimeA = $scope.rangeDate.startDate.format(DateUtil.timeFormat);
					$scope.condition.updateTimeZ = $scope.rangeDate.endDate.format(DateUtil.timeFormat);
				}else{
					$scope.condition.updateTimeA = "";
					$scope.condition.updateTimeZ = "";
				}
			};
			
			/**
			 * 获取产品列表
			 */
			$scope.getProductList = function(pageNo){
				$scope.packageCondition(pageNo);
				
				$scope.isLoadingProduct = true;
				serverIndexHttpService.getProductList($scope.condition)
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
			 * 删除产品
			 */
			$scope.deleteProduct = function(product){
				common.triggerAlertMesg("确定要删除产品 " + product.name + "？", "", function(){}, function(){
					serverIndexHttpService.deleteProduct(product)
					.then(function(response){
						var data = response.data;
						if(data && data.statusCode=="200"){
							common.triggerSuccessMesg(data.message);
							$scope.getProductList();
						}else{
							common.triggerFailMesg(data.message);
						}
					},function(err){
						console.log(err);
					});
				}, $scope);
			};
			
			/**
			 * 初始化判断是否有该面板权限
			 */
			$scope.initHasRight = function(){
				if($scope.$root.currentUser.allRights.indexOf('product_manage') < 0){
					common.triggerFailMesg("该用户无此权限模块");
					$scope.$root.showDetailPanel($scope.$root.navObj.index);
				}
			};
			
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//初始化权限
				$scope.initHasRight();
				//获取产品列表
				$scope.getProductList();
				//初始化图片列表
				$scope.imageList = [];
				//初始化编辑框
				$scope.$root.initEidtor("#productCon");
			};
			$document.ready($scope.initFunc);
		}
	};
});