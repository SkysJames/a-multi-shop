angular.module('slideGroupNews.module',[])
.directive('slideGroupNews',function(){
	return {
		restrict:'E',
		scope:{
			ansId		: '@',	//列表div的ID
			ansWinId	: '@',	//弹出模态框的ID
		},
		templateUrl : $contextPath + '/sky/server/directive/common/template/slideGroupNews.html',
		link:function(scope, element, attrs){
			
		},
		controller:function($scope, $element, $document, $interval, $timeout, indexAppHttpService, indexAppCommonService,$attrs){
			$scope.slideGroup = $('.slide-group');
			$scope.slides = $('.slide');
			$scope.currentSlide = 0;
			$scope.slideTotal = 0;
			$scope.noticeFlag = true;
			
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
				$scope.noticeFlag = false;
				$('#' + $scope.ansWinId).modal('show');
			};
			
			$('#' + $scope.ansWinId).on('show.bs.modal', function () {
				$scope.getNoticeForWin();
			});
			
			$scope.plus = function(a,b){
				return a+b;
			};
			
			$scope.getNoticeForWin = function() {
				indexAppHttpService.getValidAnnounces()
				.then(function(response){
					$scope.indexAns = response.data.announces;
				});
			};
			
			$scope.getIndexAnsList = function() {
				indexAppHttpService.getValidAnnounces()
				.then(function(response){
					$scope.indexAns = response.data.announces;
					if($scope.indexAns.length>0){
						for ( var int = 0; int < $scope.indexAns.length; int++) {
							$scope.indexAns[int].seq = int+1;
						}
						$("#" + $scope.ansId).css({"display":"block"});
						$scope.slideGroup = $('.slide-group');
						$scope.slides = $('.slide');
					}
				});
			};
			
			/**
			 * 初始化函数
			 */
			$scope.init = function(){
				$scope.getIndexAnsList();
				$interval($scope.updateIndex, 3000);
				$interval($scope.getIndexAnsList, 10000);
				
			};
			$document.ready($scope.init);
			
		},
		
	};
});