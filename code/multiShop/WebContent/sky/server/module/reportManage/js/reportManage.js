angular.module('reportManage.module',[])
.controller("reportManageCtrl",['$timeout', '$scope', '$rootScope', '$filter', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $filter, $document, serverIndexHttpService){
	//是否详细面板
	$scope.isDetail = false;
	$scope.mesStatus = common.messageContants.status;
	//举报查询条件
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 10,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 1,		//总页码数
			status		: "",
			keywords		: "",
	};
	
	/**
	 * 切换面板，显示/隐藏详细面板
	 */
	$scope.togglePanel = function(selectedObj){
		selectedObj.isDetail = !selectedObj.isDetail;
		if(selectedObj && selectedObj.status==common.messageContants.status.NORECEIVE){
			selectedObj.status = common.messageContants.status.RECEIVED;
			$scope.editReport(selectedObj);
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
	 * 获取举报列表
	 */
	$scope.pagedReportList = function(pageNo){
		$scope.packageCondition(pageNo);
		
		$scope.isLoadingReport = true;
		serverIndexHttpService.pagedReportList($scope.condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200" && data.pager){
				$scope.reportList = data.pager.resultList;
				
				$scope.condition.totalCount = data.pager.totalCount;
				$scope.condition.pageCount = data.pager.pageCount;
				$scope.isLoadingReport = false;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 保存举报
	 */
	$scope.editReport = function(obj){
		serverIndexHttpService.editReport(obj)
		.then(function(response){
			var data = response.data;
			
			//初始化未读举报的数量
			if(angular.element($('.server-index')).scope()){
				angular.element($('.server-index')).scope().initReportCount();
			}
			
			if(data.statusCode!="200"){
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 删除举报
	 */
	$scope.deleteReport = function(report){
		common.triggerAlertMesg("确定要删除该举报？", "", function(){}, function(){
			serverIndexHttpService.deleteReport(report)
			.then(function(response){
				var data = response.data;
				if(data && data.statusCode=="200"){
					common.triggerSuccessMesg(data.message);
					$scope.pagedReportList();
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
		//判断是否有权限待在当前页面
		serverCommon.hasRightStay('report_manage');
		//改变当前导航指向
		serverCommon.navChange("#/report");
		//获取举报列表
		$scope.pagedReportList();
		//令提示可用
		$('[data-toggle="tooltip"]').tooltip();
	};
	$document.ready($scope.initFunc);
}]);
