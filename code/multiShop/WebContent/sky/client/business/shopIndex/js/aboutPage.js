angular.module('aboutPage.module',[])
.controller("aboutPageCtrl",['$timeout', '$scope', '$sce', '$filter', '$document', "clientIndexHttpService", 
function($timeout, $scope, $sce, $filter, $document, clientIndexHttpService){
	$scope.sce = $sce;
	//根作用域
	$scope.shopIndexScope = angular.element($('#shopIndexId')).scope();
	//选中的导航
	$scope.selectedNav = "about";
	
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		//还原屏幕
		$(".sh-phone").removeClass("sh-phone-scroll");
		$(".shop-index-page").removeClass("shop-index-page-scroll");
		
		//手机端的详情面板滚动事件
		$(".sa-content").scroll(function(){
			var allHeight = $(".sa-content>.sa-for-scroll").height();
			
			//判断向下滚动是否大于0，且可扩大屏幕
			if($(".sa-content").scrollTop() > 0 
					&& allHeight > ($(".sa-content").height() + $(".sn-phone").height() + $(".sh-phone").height())){
				$(".sh-phone").addClass("sh-phone-scroll");
				$(".shop-index-page").addClass("shop-index-page-scroll");
			}
			
			//判断是否滚动到div的顶部
			if($(".sa-content").scrollTop() == 0){
				$(".sh-phone").removeClass("sh-phone-scroll");
				$(".shop-index-page").removeClass("shop-index-page-scroll");
			}
		});
	};
	$document.ready($scope.initFunc);
}]);
