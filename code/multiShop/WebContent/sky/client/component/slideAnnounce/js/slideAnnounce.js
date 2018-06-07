angular.module('slideAnnounce',[])
.directive('slideAnnounce',function(){
	return {
		restrict:'E',
		scope:{
			ansId		: '@',	//消息公告div的ID
			ansWinId		: '@',	//弹出模态框的ID
			indexAns		: "=",	//消息公告列表
		},
		templateUrl : $contextPath + '/sky/client/component/slideAnnounce/template/slideAnnounce.html',
		link:function(scope, element, attrs){
			
		},
		controller:function($scope, $sce, $element, $document, $interval, $timeout, clientIndexHttpService){
			$scope.sce = $sce;
			$scope.slideGroup = $('.slide-group');
			$scope.currentSlide = 0;
			$scope.slideTotal = 0;
			
			$scope.transition = function() {
				if ($scope.currentSlide === 0){
					$scope.slideGroup.css({'top':'0px'});
				} else {
					$scope.slideGroup.animate({
						'top': '-' + (20*$scope.currentSlide) + 'px'
					},'slow');
				}
			};
			
			$scope.updateIndex = function() {
				if($scope.ansId){
					$scope.slideGroup = $('#' + $scope.ansId + ' .slide-group');
				}
				
				if($scope.slideGroup[0]){
					$scope.slideTotal = ($scope.slideGroup[0].clientHeight)/20 - 1;
				}
			    if($scope.currentSlide >= $scope.slideTotal) {
			    		$scope.currentSlide = 0;
			    } else {
			    		$scope.currentSlide++;
			    }
			    
			    $timeout($scope.transition, 100);
			};
			
			$scope.openAnsWin = function(){
				$('#' + $scope.ansWinId).modal('show');
			};
			
			/**
			 * 初始化函数
			 */
			$scope.init = function(){
				$interval($scope.updateIndex, 3000);
			};
			$document.ready($scope.init);
			
		},
		
	};
})
.directive('slideAnnounceModel',function(){
	return {
		restrict:'E',
		scope:{
			ansWinId		: '@',	//弹出模态框的ID
			indexAns		: "=",	//消息公告列表
		},
		templateUrl : $contextPath + '/sky/client/component/slideAnnounce/template/slideAnnounceModel.html',
		link:function(scope, element, attrs){
			
		},
		controller:function($scope, $sce, $element, $document, $interval, $timeout, clientIndexHttpService){
			$scope.sce = $sce;
		},
		
	};
});