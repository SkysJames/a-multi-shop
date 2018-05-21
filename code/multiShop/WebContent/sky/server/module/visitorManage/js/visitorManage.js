angular.module('visitorManage.module',[])
.controller("visitorManageCtrl",['$timeout', '$scope', '$rootScope', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $document, serverIndexHttpService){
	//是否展示保存页面
	$scope.saveShow = false;
	//保存的访客对象
	$scope.visitorSave = {};
	//访客查询条件
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 10,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 1,		//总页码数
			shopId		: $currentUser.shopId,
			keywords		: "",
			visitedTimeA	: "",
			visitedTimeZ	: "",
			status		: common.visitorContants.status.USING,
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
				$scope.pagedVisitorList();
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
	$scope.togglePanel = function(selectedObj){
		$scope.saveShow = !$scope.saveShow;
		if(selectedObj){
			$scope.visitorSave = _.cloneDeep(selectedObj);
		}
	};
	
	/**
	 * 清空查询条件-日期
	 */
	$scope.clearDate = function(){
		$scope.rangeDate.startDate = null;
		$scope.rangeDate.endDate = null;
		
		$scope.pagedVisitorList();
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
			$scope.condition.visitedTimeA = $scope.rangeDate.startDate.format(DateUtil.timeFormat);
			$scope.condition.visitedTimeZ = $scope.rangeDate.endDate.format(DateUtil.timeFormat);
		}else{
			$scope.condition.visitedTimeA = "";
			$scope.condition.visitedTimeZ = "";
		}
	};
	
	/**
	 * 分页获取访客列表
	 */
	$scope.pagedVisitorList = function(pageNo){
		$scope.packageCondition(pageNo);
		
		$scope.isLoadingVisitor = true;
		serverIndexHttpService.pagedVisitorList($scope.condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200" && data.pager){
				$scope.visitorList = data.pager.resultList;
				$scope.condition.totalCount = data.pager.totalCount;
				$scope.condition.pageCount = data.pager.pageCount;
				$scope.isLoadingVisitor = false;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 删除访客
	 */
	$scope.deleteVisitor = function(visitor){
		common.triggerAlertMesg("确定要删除访客 " + visitor.ip + "？", "", function(){}, function(){
			serverIndexHttpService.deleteVisitor(visitor)
			.then(function(response){
				var data = response.data;
				if(data && data.statusCode=="200"){
					common.triggerSuccessMesg(data.message);
					$scope.pagedVisitorList();
				}else{
					common.triggerFailMesg(data.message);
				}
			},function(err){
				console.log(err);
			});
		}, $scope);
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//判断当前用户是否有权限待在当前页面
		serverCommon.hasRightStay('visitor_manage');
		//获取访客列表
		$scope.pagedVisitorList();
	};
	$document.ready($scope.initFunc);
}]);
