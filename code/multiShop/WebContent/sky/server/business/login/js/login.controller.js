angular.module('loginApp',[])
.controller("loginCtrl",['$timeout', '$scope', '$document', function($timeout, $scope, $document){
	/**
	 * 初始化函数
	 */
	var initFunc = function(){
		console.log("successfully");
//		//初始化浏览器大小监控，当浏览器的大小发生变化时调用
//		$(window).resize(function(){
//			$scope.$root.setMapLeftWrapperHeight();
//			$scope.$root.setLeftTaskListHeight();
//			$scope.$root.setDetailHeight();
//		});
	};
	$document.ready(initFunc);
	
}]);