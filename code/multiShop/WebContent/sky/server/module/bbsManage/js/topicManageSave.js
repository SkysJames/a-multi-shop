var topicSave = angular.module('topicManageSave',[]);//定义模块
topicSave.directive('topicManageSave',function(){//自定义指令
	return {
		restrict:'E',
		scope : {
			topic :"=",
		},
		templateUrl : $contextPath +"/sky/server/module/bbsManage/template/topicManageSave.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			
			$scope.saveCondition = {
				toUser : $currentUser.userId,
				sectionId : "",
				topicName : "",
				content : "",
			};
			
			
			/**
			 * 新增保存版块
			 */
			$scope.saveTopic = function(topic){
				if(!$scope.isSaveTopic(topic)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveBbsTopic(topic)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedTopicList();
						$scope.$parent.togglePanel(null);
					}else{
						common.triggerFailMesg(data.message);
					}
					$scope.isLoadingSave = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 编辑保存帖子
			 */
			$scope.editTopic = function(section){
				if(!$scope.isSaveTopic(section)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editBbsSection(section)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedTopicList();
						$scope.$parent.togglePanel(null);
					}else{
						common.triggerFailMesg(data.message);
					}
					$scope.isLoadingSave = false;
				},function(err){
					console.log(err);
				});
			};
			
			/**
			 * 判断该用户对象是否符合保存的条件
			 */
			$scope.isSaveTopic = function(topic){
				if(!topic){
					return false;
				}
				if(!topic.topicName || topic.topicName==""){
					return false;
				}
				$scope.saveCondition = $scope.$parent.topicContentEditor.html();
				if(!$scope.saveCondition.content || $scope.saveCondition.content==""){
					return false;
				}
				return true;
			};
			
			/**
			 * 获取所有角色
			 */
			$scope.getAllRightGroupList = function(){
				serverIndexHttpService.getAllRightGroupList()
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.rightGroupAll){
						$scope.rightGroupAll = data.rightGroupAll;
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
				$scope.$parent.topicContentEditor = serverCommon.initKindEditor("#topicContent");
				serverIndexHttpService.findAllSection()
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						$scope.sectionList = data.resultList;
						$scope.saveCondition.sectionId = data.resultList[0].id;//默认选中
					}else{
						common.triggerFailMesg(data.message);
					}
				},function(err){
					console.log(err);
				});
			};
			$document.ready($scope.initFunc);
			
		}
	};
});