angular.module('shopTypeManageSave',[])
.directive('shopTypeManageSave',function(){
	return {
		restrict:'E',
		scope : {
			typet			: "=",
		},
		templateUrl : $contextPath +"/sky/server/module/typeManage/template/shopTypeManage/shopTypeManageSave.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			/**
			 * 新增保存用户
			 */
			$scope.saveTypet = function(typet){
				if(!$scope.isSaveTypet(typet)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.saveTypet(typet)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.getTypetList();
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
			 * 编辑保存用户
			 */
			$scope.editTypet = function(typet){
				if(!$scope.isSaveTypet(typet)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editTypet(typet)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.getTypetList();
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
			$scope.isSaveTypet = function(typet){
				if(!typet){
					return false;
				}
				
				typet.tableName = common.tableContants.TB_SHOP;
				
				if(!typet.name || typet.name==""){
					return false;
				}
				
				return true;
			};
			
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
			};
			$document.ready($scope.initFunc);
		}
	};
});