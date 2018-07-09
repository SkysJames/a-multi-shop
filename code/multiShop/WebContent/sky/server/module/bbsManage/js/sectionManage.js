angular.module('topicManage.module',['sectionManageSave'])
.controller("sectionManageCtrl",['$timeout', '$scope', '$rootScope', '$filter', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $filter, $document, serverIndexHttpService){
	//是否详细面板
	$scope.isDetail = false;
	//消息查询条件
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 10,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 1,		//总页码数
			toUser		: $currentUser.userId,
			status		: "",
			keywords		: "",
	};
	//日期对象
	$scope.rangeDate = {
			startDate	: null,	//开始时间
			endDate		: null,	//结束时间
	}
	
	/**
	 * 切换面板，显示/隐藏编辑面板
	 */
	$scope.togglePanel = function(saveType, selectedObj){
		$scope.saveType = saveType;
		if(selectedObj){
			$scope.sectionSave = _.cloneDeep(selectedObj);
		}else{
			$scope.sectionSave = null;
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
	 * 清空查询条件-日期
	 */
	$scope.clearDate = function(){
		$scope.rangeDate.startDate = null;
		$scope.rangeDate.endDate = null;
		
		$scope.pagedSectionList();
	};
	
	
	/**
	 * 获取论坛版块列表
	 */
	$scope.pagedSectionList = function(pageNo){
		$scope.packageCondition(pageNo);
		
		$scope.isLoadingMessage = true;
		serverIndexHttpService.pagedSectionList($scope.condition)
		.then(function(response){

			var data = response.data;
			if(data.statusCode=="200" && data.pager){
				$scope.sectionList = data.pager.resultList;
				
				$scope.condition.totalCount = data.pager.totalCount;
				$scope.condition.pageCount = data.pager.pageCount;
				$scope.isLoadingMessage = false;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 删除消息
	 */
	$scope.deleteSection = function(section){
		common.triggerAlertMesg("确定要删除论坛版块："+ section.name +"？", "", function(){}, function(){
			serverIndexHttpService.deleteBbsSection(section)
			.then(function(response){
				var data = response.data;
				if(data && data.statusCode=="200"){
					common.triggerSuccessMesg(data.message);
					$scope.pagedSectionList();
				}else{
					common.triggerFailMesg(data.message);
				}
			},function(err){
				console.log(err);
			});
		}, $scope);
	};
	
	/**
	 * 显示日期选择器
	 */
	$scope.showSearchDateRange = function(){
		$("#searchDateRangeId").data("daterangepicker").show();
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//判断当前消息是否有权限待在当前页面
		serverCommon.hasRightStay('bbs_manage');
		//改变当前导航指向
		serverCommon.navChange("#/bbssection");
		//获取消息列表
		$scope.pagedSectionList();
		//令提示可用
		$('[data-toggle="tooltip"]').tooltip();
	};
	$document.ready($scope.initFunc);
}]);
