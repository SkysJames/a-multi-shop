angular.module('loadingPanel',[])
.directive('loadingPanel',function(){
	return {
		restrict : 'E',
		scope:{
			fontSize:'@',
		},
		templateUrl: $contextPath + '/sky/client/component/loadingPanel/template/loadingPanel.html',
		link:function(scope,element,attrs){
			
		},
		controller:function($scope,$element,$attrs){
			
		}
	}
});