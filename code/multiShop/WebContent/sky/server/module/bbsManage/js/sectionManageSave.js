var sectionSave = angular.module('sectionManageSave',[]);//定义模块
sectionSave.directive('sectionManageSave',function(){//自定义指令
	return {
		restrict:'E',
		scope : {
			section :"=",
		},
		templateUrl : $contextPath +"/sky/server/module/bbsManage/template/sectionManageSave.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			/**
			 * 新增保存版块
			 */
			$scope.saveSection = function(section){
				if(!$scope.isSaveSection(section)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveBbsSection(section)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedSectionList();
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
			 * 编辑保存版块
			 */
			$scope.editSection = function(section){
				if(!$scope.isSaveSection(section)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editBbsSection(section)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedSectionList();
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
			$scope.isSaveSection = function(section){
				if(!section){
					return false;
				}
				if(!section.name || section.name==""){
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
				$scope.section = null;
			};
			$document.ready($scope.initFunc);
			
		}
	};
});