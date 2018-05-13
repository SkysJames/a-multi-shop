angular.module('slideShow',[])
.directive('slideShow',function(){
	return {
		restrict:'E',
		scope : {
			slideList	: '=',
		},
		templateUrl : $contextPath +"/sky/client/component/slideShow/template/slideShow.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document){
			/**
			 * 初始化轮播图片
			 */
			$scope.initSlider = function(){
				var bannerSlider = new Slider($('#banner_tabs'), {
					time: 5000,
					delay: 400,
					event: 'hover',
					auto: true,
					mode: 'fade',
					controller: $('#bannerCtrl'),
					activeControllerCls: 'active'
				});
				$('#banner_tabs .flex-prev').click(function() {
					bannerSlider.prev();
				});
				$('#banner_tabs .flex-next').click(function() {
					bannerSlider.next();
				});
			};
			
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				$timeout($scope.initSlider, 1000);
			};
			$document.ready($scope.initFunc);
		}
	};
});