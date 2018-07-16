var topicSave = angular.module('topicManageSave',[]);//定义模块
topicSave.directive('topicManageSave',function(){//自定义指令
	return {
		restrict:'E',
		scope : {
		
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
			 * 新增保存帖子
			 */
			$scope.saveTopic = function(saveCondition){
				if(!$scope.isSaveTopic(saveCondition)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveBbsTopic(saveCondition)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedTopicList();
						if($scope.$parent.saveType.indexOf("children")==0){
							$scope.$parent.getChildrenTopicPaged($scope.$parent.children);
						}
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
			$scope.editTopic = function(saveCondition){
				if(!$scope.isSaveTopic(saveCondition)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editBbsTopic(saveCondition)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedTopicList();
						if($scope.$parent.saveType.indexOf("children")==0){
							$scope.$parent.getChildrenTopicPaged($scope.$parent.children);
						}
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
			$scope.isSaveTopic = function(saveCondition){
				saveCondition.content = $scope.$parent.topicContentEditor.html();
	
				if(saveCondition.topicName==""){
					return false;
				}
				if(saveCondition.content==""){
					return false;
				}
				if(saveCondition.sectionId==""){
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
						$scope.$parent.sectionList = data.resultList;
						$scope.$parent.save.sectionId = data.resultList[0].id;//默认选中
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