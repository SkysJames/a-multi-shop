angular.module('shopListManage.module',['shopListManageSave'])
.controller("shopListManageCtrl",['$timeout', '$scope', '$rootScope', '$filter', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $filter, $document, serverIndexHttpService){
	//当前用户
	$scope.currentUser = $currentUser;
	//保存页面类型，null-不展示，'save'-添加，'edit'-编辑
	$scope.saveType = null;
	//公告查询条件
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 10,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 1,		//总页码数
			shopId		: $currentUser.shopId==common.shopContants.shopSystem?"":$currentUser.shopId,
			keywords		: "",
			updateTimeA	: "",
			updateTimeZ	: "",
			status		: "",
			isOver		: "",
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
				$scope.pagedAnnounceList();
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
	 * 切换面板，显示/隐藏编辑面板
	 */
	$scope.togglePanel = function(saveType, selectedObj){
		$scope.saveType = saveType;
		if(selectedObj){
			$scope.announceSave = _.cloneDeep(selectedObj);
			$("#overTimeId").val($scope.announceSave.overTimeString);
		}else{
			$scope.announceSave = null;
			$("#overTimeId").val("");
		}
	};
	
	/**
	 * 清空查询条件-日期
	 */
	$scope.clearDate = function(){
		$scope.rangeDate.startDate = null;
		$scope.rangeDate.endDate = null;
		
		$scope.pagedAnnounceList();
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
		
		//更新开始结束时间
		if($scope.rangeDate.startDate && $scope.rangeDate.endDate){
			$scope.condition.updateTimeA = $scope.rangeDate.startDate.format(DateUtil.timeFormat);
			$scope.condition.updateTimeZ = $scope.rangeDate.endDate.format(DateUtil.timeFormat);
		}else{
			$scope.condition.updateTimeA = "";
			$scope.condition.updateTimeZ = "";
		}
	};
	
	/**
	 * 获取公告列表
	 */
	$scope.pagedAnnounceList = function(pageNo){
		$scope.packageCondition(pageNo);
		
		//获取过滤条件字符串
		$scope.getFilterText();
		
		$scope.isLoadingAnnounce = true;
		serverIndexHttpService.pagedAnnounceList($scope.condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200" && data.pager){
				$scope.announceList = data.pager.resultList;
				
				$scope.condition.totalCount = data.pager.totalCount;
				$scope.condition.pageCount = data.pager.pageCount;
				$scope.isLoadingAnnounce = false;
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
			if(""!=$scope.condition.keywords){
				$scope.filterText += "模糊搜索（" + $scope.condition.keywords + "）+ "
			}
			if(""!=$scope.condition.updateTimeA && ""!=$scope.condition.updateTimeZ){
				$scope.filterText += "更新时间（" + $scope.condition.updateTimeA + "~" + $scope.condition.updateTimeZ + "）+ "
			}
			if(""!=$scope.condition.opType){
				$scope.filterText += "公告状态（" + $filter("stringStatus")($scope.condition.status) + "）+ "
			}
		}
		
		if($scope.filterText.length > 0){
			$scope.filterText = $scope.filterText.substring(0, $scope.filterText.length-2);
		}
	};
	
	/**
	 * 删除公告
	 */
	$scope.deleteAnnounce = function(announce){
		common.triggerAlertMesg("确定要删除公告 " + announce.name + "？", "", function(){}, function(){
			serverIndexHttpService.deleteAnnounce(announce)
			.then(function(response){
				var data = response.data;
				if(data && data.statusCode=="200"){
					common.triggerSuccessMesg(data.message);
					$scope.pagedAnnounceList();
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
		//判断当前公告是否有权限待在当前页面
		serverCommon.hasRightStay('announce_manage');
		//改变当前导航指向
		serverCommon.navChange("#/announce");
		//初始化当前登陆用户在当前页面是否为管理员权限
		$scope.isAdminRight = serverCommon.isAdminRight('announce_manage');
		//获取公告列表
		$scope.pagedAnnounceList();
		
		if($scope.isAdminRight){
			//获取所有店铺
			$scope.getAllShopList();
		}
		
		//令提示可用
		$('[data-toggle="tooltip"]').tooltip();
	};
	$document.ready($scope.initFunc);
}]);
