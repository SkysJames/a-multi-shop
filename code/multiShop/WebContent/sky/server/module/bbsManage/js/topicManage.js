angular.module('sectionManage.module',['topicManageSave'])
.controller("topicManageCtrl",['$timeout', '$scope', '$rootScope', '$filter', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $filter, $document, serverIndexHttpService){
	
	$scope.parentTopic = null;//回帖的父帖对象
	
	
	//主帖查询条件
	$scope.condition = {
			pageNo		: 1,		//当前页码
			pageSize		: 10,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 1,		//总页码数
			keywords		: "",   //主帖模糊名称
	};
	
	//根据父贴查询条件
	$scope.children = {
			pageNo		: 1,		//当前页码
			pageSize		: 5,	//每页数据量
			totalCount	: 0,		//数据总量
			pageCount	: 1,		//总页码数
			parentTopicId	: "",		//父帖ID
	};
	
	
	//日期对象
	$scope.rangeDate = {
			startDate	: null,	//开始时间
			endDate		: null,	//结束时间
	};
	
	//主帖、回帖保存对象
	$scope.save = {
			masterid : $currentUser.userId,
			sectionId : "",
			topicName : "",
			content : "",  //正文
			
			topicType : "1",//帖子类型 1-主帖；2-回帖
			parentTopicId : "",//父帖ID
			topicMasterid : "",//父帖用户ID
			
	}
	/**
	 * 切换面板，显示/隐藏编辑面板
	 */
	$scope.togglePanel = function(saveType, selectedObj){
		$scope.saveType = saveType;
		if(saveType == 'edit'){ //主贴编辑

			$("#sectionId").attr("disabled",'disabled');
			$("#topicName").removeAttr("readonly");
			$scope.save = _.cloneDeep(selectedObj);
			$scope.topicContentEditor.html($scope.save.content);
		}else if(saveType == 'save'){ //主贴保存
			$("#sectionId").removeAttr("disabled");
			$("#topicName").removeAttr("readonly");
			$scope.save.topicName = "";
			$scope.topicContentEditor.html("");
			$scope.save.topicType = "1";//主帖
			serverIndexHttpService.findAllSection()
			.then(function(response){
				var data = response.data;
				if(data.statusCode=="200" && data.message){
					$scope.sectionList = data.resultList;
					$scope.save.sectionId = data.resultList[0].id;//默认选中
				}else{
					common.triggerFailMesg(data.message);
				}
			},function(err){
				console.log(err);
			});
		}else if(saveType == 'childrenSave'){//回帖保存
			$("#topicName").attr("readonly",'readonly');
			$("#sectionId").attr("disabled",'disabled');
			$scope.topicContentEditor.html("");
			$scope.save.topicName = $scope.parentTopic.topicName;//所属主贴名称
			$scope.save.sectionId = $scope.parentTopic.sectionId;//所属版块
			$scope.save.topicType = "2";//回帖
			$scope.save.parentTopicId = $scope.parentTopic.id;//所属主贴ID
			$scope.save.topicMasterid = $scope.parentTopic.masterid;//所属主贴用户ID
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
	 * 获取主帖列表
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
		
		if($scope.children.parentTopicId == topic.id){
			return; 
		}
 		
		if(pageNo){
			$scope.children.pageNo = pageNo;
		}else{
			$scope.children.pageNo = 1;
		}
		
		if(topic.id){//新增保存后刷新判断；只适合点击查询的情况
			$scope.parentTopic = topic;
			$scope.children.parentTopicId = topic.id;
		}
		
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
	 * 删除帖子
	 */
	$scope.deleteTopic = function(topic){
		common.triggerAlertMesg("确定要删除帖子："+ topic.topicName +"？", "", function(){}, function(){
			serverIndexHttpService.deleteTopic(topic)
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
		console.error('parent');
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
