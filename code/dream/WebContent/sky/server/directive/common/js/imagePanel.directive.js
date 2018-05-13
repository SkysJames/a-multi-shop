angular.module('imagePanel',[])
.directive('imagePanel',function(){
	return {
		restrict:'E',
		scope : {
			imagePanelId		: "@",//该图片面板的ID
			imagePathList	: "=",//图片路径列表
		},
		templateUrl : $contextPath +"/sky/server/directive/common/template/imagePanel.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, serverIndexHttpService){
			//默认的图片
			$scope.defaultImage = {
					index	: -1,
					path		: $contextPath + "/sky/common/img/no_pic.jpeg",
			};
			//当前显示的图片
			$scope.currentImage = $scope.defaultImage;
			
			/**
			 * 切换图片，向左或右
			 */
			$scope.triggerImage = function(direction){
				//向右切换图片，还是向左切换图片
				if(direction && direction=="right"){
					if($scope.currentImage.index < $scope.imagePathList.length-1){
						$scope.currentImage.index++;
					}else{
						$scope.currentImage.index = 0;
					}
				}else{
					if($scope.currentImage.index > 0){
						$scope.currentImage.index--;
					}else{
						$scope.currentImage.index = $scope.imagePathList.length-1;
					}
				}
				//当前图片的路径赋值
				$scope.currentImage.path = $scope.imagePathList[$scope.currentImage.index];
			};
			
			/**
			 * 初始化图片
			 */
			$scope.initImage = function(){
				if($scope.imagePathList && $scope.imagePathList.length>0){
					$scope.currentImage.index = 0;
					$scope.currentImage.path = $scope.imagePathList[$scope.currentImage.index];
				}
			};
		}
	};
});