angular.module('visitorManageSave',[])
.directive('visitorManageSave',function(){
	return {
		restrict:'E',
		scope : {
			visitor			: "=",
		},
		templateUrl : $contextPath +"/sky/server/module/visitorManage/template/visitorManageSave.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			/**
			 * 编辑保存访客
			 */
			$scope.editVisitor = function(visitor){
				if(!$scope.isSaveVisitor(visitor)){
					common.triggerFailMesg("*为必填项");
					return;
				}
				
				$scope.isLoadingSave = true;
				serverIndexHttpService.editVisitor(visitor)
				.then(function(response){
					var data = response.data;
					if(data.statusCode=="200" && data.message){
						common.triggerSuccessMesg(data.message);
						$scope.$parent.pagedVisitorList();
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
			 * 判断该访客对象是否符合保存的条件
			 */
			$scope.isSaveVisitor = function(visitor){
				if(!visitor){
					return false;
				}
				
				if(visitor.status==undefined || visitor.status==null){
					return false;
				}
				
				return true;
			};
		}
	};
});