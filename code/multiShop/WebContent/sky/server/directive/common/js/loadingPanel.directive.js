angular.module('loadingPanel',[])
.directive('loadingPanel',function(){
	return {
		restrict : 'E',
		scope:{
			fontSize:'@',
		},
		templateUrl: $contextPath + '/sky/server/directive/common/template/loadingPanel.html',
		link:function(scope,element,attrs){
			
		},
		controller:function($scope,$element,$attrs){
			
		}
	}
});