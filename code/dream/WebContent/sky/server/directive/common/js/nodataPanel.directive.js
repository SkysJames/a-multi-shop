angular.module('nodataPanel',[])
.directive('nodataPanel',function(){
	return {
		restrict : 'E',
		scope:{
			fontSize:'@',
		},
		templateUrl: $contextPath + '/sky/server/directive/common/template/nodataPanel.html',
		link:function(scope,element,attrs){
			
		},
		controller:function($scope,$element,$attrs){
			
		}
	}
});