var typeManageModule = angular.module('typeManage.module',["productTypeManageSave","shopTypeManageSave"]);
typeManageModule.controller("typeManageCtrl",['$timeout', '$scope', '$filter', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $filter, $document, serverIndexHttpService){
	//保存页面类型，null-不展示，'save'-添加，'edit'-编辑
	$scope.saveType = null;
	
	/**
	 * 切换面板，显示/隐藏编辑面板
	 */
	$scope.togglePanel = function(saveType, selectedObj){
		$scope.saveType = saveType;
		if(selectedObj){
			$scope.typetSave = _.cloneDeep(selectedObj);
		}else{
			$scope.typetSave = null;
		}
	};
	
	/**
	 * 获取类型列表
	 */
	$scope.getTypetList = function(){
		$scope.isLoadingTypet = true;
		serverIndexHttpService.getTypetList($scope.condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200"){
				$scope.typetList = data.typetList;
				$scope.isLoadingTypet = false;
				
				//初始化树形结构展示
				$timeout(function(){
					serverCommon.initTreeView();
				},100);
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 删除类型
	 */
	$scope.deleteTypet = function(typet){
		common.triggerAlertMesg("确定要删除类型 " + typet.name + "？", "", function(){}, function(){
			serverIndexHttpService.deleteTypet(typet)
			.then(function(response){
				var data = response.data;
				if(data && data.statusCode=="200"){
					common.triggerSuccessMesg(data.message);
					$scope.pagedTypetList();
				}else{
					common.triggerFailMesg(data.message);
				}
			},function(err){
				console.log(err);
			});
		}, $scope);
	};
}]);
