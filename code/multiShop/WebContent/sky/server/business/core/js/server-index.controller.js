angular.module('serverIndexApp',
		["ngRoute","server-index.filter","server-index.httpService"].concat($moduleList).concat($commonDirectiveList).concat($directiveList)
)
.config(['$routeProvider', function($routeProvider){
	var defaultUrl = "/majorIndex";
	 
	//首页
	$routeProvider.when("/majorIndex",{
		templateUrl:$contextPath+'/sky/server/module/majorIndex/template/majorIndex.html',
	});
	
	//用户管理
	$routeProvider.when("/user",{
		templateUrl:$contextPath+'/sky/server/module/userManage/template/userManage.html',
	});
	
	//访客管理
	$routeProvider.when("/visitor",{
		templateUrl:$contextPath+'/sky/server/module/visitorManage/template/visitorManage.html',
	});
	
	//日志管理
	$routeProvider.when("/oplog",{
		templateUrl:$contextPath+'/sky/server/module/oplogManage/template/oplogManage.html',
	});
	
	$routeProvider.otherwise({
		redirectTo: defaultUrl
    });
	
	
}])
.controller("serverIndexCtrl",['$timeout', '$scope', '$rootScope', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $rootScope, $document, serverIndexHttpService){
	/**
	 * 初始化编辑框
	 */
	$rootScope.initEidtor = function(target){
		$rootScope.editor = KindEditor.create(target, {
			resizeType : 1,
			allowPreviewEmoticons : false,
			width : '100%',
			items : [
				'justifyleft', 'justifycenter', 'justifyright',
				'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'clearhtml', 'quickformat', 'selectall', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'table', 'hr', 'emoticons', 'pagebreak','source','fullscreen'
				]
		});
	};
	
	/**
	 * 显示个人信息面板
	 */
	$scope.showPersonPanel = function(){
		$("#personPanelId").modal({
			backdrop : true,
			keyboard : false,
			show	 : true,
		});
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		console.log($currentUser);
	};
	$document.ready($scope.initFunc);
	
}]);