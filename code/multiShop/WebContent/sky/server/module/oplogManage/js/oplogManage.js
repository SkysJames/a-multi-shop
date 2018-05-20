angular.module('oplogManage.module',[])
.controller("oplogManageCtrl",['$timeout', '$scope', '$rootScope', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $document, serverIndexHttpService){
	//日志查询条件
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 20,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 1,		//总页码数
			keywords		: "",
			opTimeA		: "",
			opTimeZ		: "",
			opType		: "",
	};
	//日期对象
	$scope.rangeDate = {
			startDate	: moment(thisToday+" 00:00:00"),	//开始时间
			endDate		: moment(thisToday+" 23:59:59"),	//结束时间
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
				$scope.pagedOplogList();
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
	 * 分页获取访客列表
	 */
	$scope.pagedOplogList = function(pageNo){
		$scope.condition.opTimeA = $scope.rangeDate.startDate.format(DateUtil.timeFormat);
		$scope.condition.opTimeZ = $scope.rangeDate.endDate.format(DateUtil.timeFormat);
		
		if(pageNo){
			$scope.condition.pageNo = pageNo;
		}else{
			$scope.condition.pageNo = 1;
		}
		
		//获取过滤条件字符串
		$scope.getFilterText();
		
		$scope.isLoadingOplog = true;
		serverIndexHttpService.pagedOplogList($scope.condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200" && data.pager){
				$scope.oplogList = data.pager.resultList;
				$scope.condition.totalCount = data.pager.totalCount;
				$scope.condition.pageCount = data.pager.pageCount;
				$scope.isLoadingOplog = false;
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
			if(""!=$scope.condition.opTimeA && ""!=$scope.condition.opTimeZ){
				$scope.filterText += "操作时间（" + $scope.condition.opTimeA + "~" + $scope.condition.opTimeZ + "）+ "
			}
			if(""!=$scope.condition.opType){
				$scope.filterText += "日志类型（" + $scope.condition.opType + "）+ "
			}
		}
		
		if($scope.filterText.length > 0){
			$scope.filterText = $scope.filterText.substring(0, $scope.filterText.length-2);
		}
		
	};
	
	/**
	 * 初始化日志类型列表
	 */
	$scope.initOpTypeList = function(){
		//日志类型列表
		$scope.opTypeList = [];
		for(key in $opTypeMap){
			$scope.opTypeList.push({
				key		: key,
				value	: $opTypeMap[key],
			});
		}
	};
	
	/**
	 * 初始化判断是否有该面板权限
	 */
	$scope.initHasRight = function(){
		console.log($rootScope.currentUser);
		if($rootScope.currentUser.allRights.indexOf('oplog_manage') < 0){
			common.triggerFailMesg("该用户无此权限模块");
			$rootScope.showDetailPanel($rootScope.navObj.index);
		}
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//初始化权限
//		$scope.initHasRight();
		//改变当前导航指向
		serverCommon.navChange("#/oplog");
		//初始化日志类型列表
		$scope.initOpTypeList();
		//获取日志列表
		$scope.pagedOplogList();
	};
	$document.ready($scope.initFunc);
}]);
