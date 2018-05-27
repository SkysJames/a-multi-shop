angular.module('messageManage.module',[])
.controller("messageManageCtrl",['$timeout', '$scope', '$rootScope', '$filter', '$document', 'serverIndexHttpService', 
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
	
	/**
	 * 切换面板，显示/隐藏详细面板
	 */
	$scope.togglePanel = function(selectedObj){
		selectedObj.isDetail = !selectedObj.isDetail;
		if(selectedObj && selectedObj.status==common.messageContants.status.NORECEIVE){
			selectedObj.status = common.messageContants.status.RECEIVED;
			$scope.editMessage(selectedObj);
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
	 * 获取消息列表
	 */
	$scope.pagedMessageList = function(pageNo){
		$scope.packageCondition(pageNo);
		
		$scope.isLoadingMessage = true;
		serverIndexHttpService.pagedMessageList($scope.condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200" && data.pager){
				$scope.messageList = data.pager.resultList;
				
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
	 * 保存消息
	 */
	$scope.editMessage = function(obj){
		serverIndexHttpService.editMessage(obj)
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
	 * 删除消息
	 */
	$scope.deleteMessage = function(message){
		common.triggerAlertMesg("确定要删除该消息？", "", function(){}, function(){
			serverIndexHttpService.deleteMessage(message)
			.then(function(response){
				var data = response.data;
				if(data && data.statusCode=="200"){
					common.triggerSuccessMesg(data.message);
					$scope.pagedMessageList();
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
		//判断当前消息是否有权限待在当前页面
		serverCommon.hasRightStay('message_manage');
		//改变当前导航指向
		serverCommon.navChange("#/message");
		//获取消息列表
		$scope.pagedMessageList();
		//令提示可用
		$('[data-toggle="tooltip"]').tooltip();
	};
	$document.ready($scope.initFunc);
}]);
