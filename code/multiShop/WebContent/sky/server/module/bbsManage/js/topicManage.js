angular.module('sectionManage.module',['topicManageSave'])
.controller("topicManageCtrl",['$timeout', '$scope', '$rootScope', '$filter', '$document', 'serverIndexHttpService', 
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
	
	//根据父贴查询条件
	$scope.children = {
			pageNo		: 1,		//当前页码
			pageSize		: 5,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 1,		//总页码数
			parentTopicId	: null,		//总页码数
	};
	
	
	//日期对象
	$scope.rangeDate = {
			startDate	: null,	//开始时间
			endDate		: null,	//结束时间
	};
	
	/**
	 * 切换面板，显示/隐藏编辑面板
	 */
	$scope.togglePanel = function(saveType, selectedObj){
		$scope.saveType = saveType;
		if(selectedObj){
			$scope.topicSave = _.cloneDeep(selectedObj);
			$scope.topicContentEditor.html($scope.topicSave.content);
		}else{
			$scope.topicSave = null;
			$scope.topicContentEditor.html("");
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
		
		$scope.pagedTopicList();
	};
	
	
	/**
	 * 获取论坛版块列表
	 */
	$scope.pagedTopicList = function(pageNo){
		$scope.packageCondition(pageNo);
		
		$scope.isLoadingMain = true;
		serverIndexHttpService.pagedTopicList($scope.condition)
		.then(function(response){

			var data = response.data;
			if(data.statusCode=="200" && data.pager){
				$scope.resultList = data.pager.resultList;
				
				$scope.condition.totalCount = data.pager.totalCount;
				$scope.condition.pageCount = data.pager.pageCount;
				$scope.isLoadingMain = false;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 获取回帖列表
	 */
	$scope.getChildrenTopicPaged = function(topic,pageNo){
		
		if(!topic){
			return;
		}
		if(pageNo){
			$scope.children.pageNo = pageNo;
		}else{
			$scope.children.pageNo = 1;
		}
		$scope.children.parentTopicId = topic.id;
		$scope.parentTopicName = topic.topicName;
		$scope.isLoadingChildren = true;
		serverIndexHttpService.getChildrenTopicPaged($scope.children)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200" && data.pager){
				$scope.childrenList = data.pager.resultList;
				
				$scope.children.totalCount = data.pager.totalCount;
				$scope.children.pageCount = data.pager.pageCount;
				$scope.isLoadingChildren = false;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	}
	
	
	/**
	 * 保存消息
	 */
	$scope.editTopic = function(obj){
		serverIndexHttpService.editTopic(obj)
		.then(function(response){
			var data = response.data;
			
			//初始化未读消息的数量
			if(angular.element($('.server-index')).scope()){
				angular.element($('.server-index')).scope().initMessageCount();
			}
			
			if(data.statusCode!="200"){
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 删除帖子
	 */
	$scope.deleteTopic = function(section){
		common.triggerAlertMesg("确定要删除帖子："+ section.name +"？", "", function(){}, function(){
			serverIndexHttpService.deleteTopic(section)
			.then(function(response){
				var data = response.data;
				if(data && data.statusCode=="200"){
					common.triggerSuccessMesg(data.message);
					$scope.pagedTopicList();
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
		serverCommon.navChange("#/bbstopic");
		//获取消息列表
		$scope.pagedTopicList();
		//令提示可用
		$('[data-toggle="tooltip"]').tooltip();
		
	};
	$document.ready($scope.initFunc);
}]);
