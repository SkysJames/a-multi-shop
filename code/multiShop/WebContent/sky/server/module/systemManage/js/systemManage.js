angular.module('systemManage.module',[])
.controller("systemManageCtrl",['$timeout', '$scope', '$rootScope', '$filter', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $filter, $document, serverIndexHttpService){
	//是否为编辑状态
	$scope.isEdit = false;
	//图片面板的路径列表
	$scope.imagePathList = [];
	
	/**
	 * 切换编辑状态
	 */
	$scope.toggleEdit = function(isEdit){
		$scope.isEdit = isEdit;
		if(!$scope.isEdit){
			$scope.systemInfo = _.cloneDeep($scope.systemInfoBak);
		}
	};
	
	/**
	 * 获取系统信息
	 */
	$scope.getSystemInfo = function(){
		$scope.isLoadingSystemInfo = true;
		serverIndexHttpService.getSystemInfo()
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200" && data.systemInfo){
				$scope.systemInfo = data.systemInfo;
				$scope.systemInfoBak = _.cloneDeep($scope.systemInfo);//备份
				
				$scope.isLoadingSystemInfo = false;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 保存系统信息
	 */
	$scope.saveSystemInfo = function(systemInfo){
		$scope.isLoadingSystemInfo = true;
		serverIndexHttpService.saveSystemInfo(systemInfo)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200"){
				common.triggerSuccessMesg(data.message);
				$scope.getSystemInfo();
				$scope.toggleEdit(false);
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 打开新窗口
	 */
	$scope.openNewPage = function(url){
		common.toPage(url);
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//判断当前用户是否有权限待在当前页面
		serverCommon.hasRightStay('system_manage');
		//改变当前导航指向
		serverCommon.navChange("#/system");
		//获取系统信息
		$scope.getSystemInfo();
		//令提示可用
		$('[data-toggle="tooltip"]').tooltip();
	};
	$document.ready($scope.initFunc);
}]);
