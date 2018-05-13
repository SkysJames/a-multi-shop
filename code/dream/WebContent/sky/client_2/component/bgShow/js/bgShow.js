angular.module('bgShow',[])
.directive('bgShow',function(){
	return {
		restrict:'E',
		scope : {
			bgUrl	: '=',
			headText	: '=',
			height	: '@',
		},
		templateUrl : $contextPath +"/sky/client/component/bgShow/template/bgShow.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document){
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				
			};
			$document.ready($scope.initFunc);
		}
	};
});