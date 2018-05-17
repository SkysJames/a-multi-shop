angular.module('loginApp',[])
.controller("loginCtrl",['$timeout', '$scope', '$document', function($timeout, $scope, $document,$http){
	/**
	 * 初始化函数
	 */
	var initFunc = function(){
//		//初始化浏览器大小监控，当浏览器的大小发生变化时调用
//		$(window).resize(function(){
//			$scope.$root.setMapLeftWrapperHeight();
//			$scope.$root.setLeftTaskListHeight();
//			$scope.$root.setDetailHeight();
//		});
	};
	$document.ready(initFunc);
	
	var conditionJson = JSON.stringify({
		name	: 'jjkk'
	});
	var tempData = {
			'conditionJson'		: conditionJson,
	};
	
	$.ajax({
        type: "POST",
        url: $contextPath + "/system/type!save",
        data: $.param(tempData,true),
        success: function (data) {
            console.log(data);
        }
    });
	
}]);