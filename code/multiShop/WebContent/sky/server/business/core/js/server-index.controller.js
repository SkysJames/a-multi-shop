angular.module('serverIndexApp',
		["ngRoute","server-index.filter","server-index.httpService"].concat($moduleList).concat($commonDirectiveList).concat($directiveList)
)
.config(['$routeProvider', function($routeProvider){
	var defaultUrl = "/majorIndex";
	 
	//首页
	$routeProvider.when("/majorIndex",{
		templateUrl:$contextPath+'/sky/server/module/majorIndex/template/majorIndex.html',
	});
	
	//系统管理
	$routeProvider.when("/system",{
		templateUrl:$contextPath+'/sky/server/module/systemManage/template/systemManage.html',
	});
	
	//公告管理
	$routeProvider.when("/announce",{
		templateUrl:$contextPath+'/sky/server/module/announceManage/template/announceManage.html',
	});
	
	//消息列表
	$routeProvider.when("/message",{
		templateUrl:$contextPath+'/sky/server/module/messageManage/template/messageManage.html',
	});
	
	//举报列表
	$routeProvider.when("/report",{
		templateUrl:$contextPath+'/sky/server/module/reportManage/template/reportManage.html',
	});
	
	//店铺列表
	$routeProvider.when("/shopList",{
		templateUrl:$contextPath+'/sky/server/module/shopManage/template/shopListManage.html',
	});
	
	//店铺类型
	$routeProvider.when("/shopType",{
		templateUrl:$contextPath+'/sky/server/module/typeManage/template/shopTypeManage/shopTypeManage.html',
	});
	
	//商品类型
	$routeProvider.when("/productType",{
		templateUrl:$contextPath+'/sky/server/module/typeManage/template/productTypeManage/productTypeManage.html',
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
	 * 初始化未读消息的数量
	 */
	$scope.initMessageCount = function(){
		var condition = {
				toUser		: $currentUser.userId,
				status		: common.messageContants.status.NORECEIVE,
		};
		serverIndexHttpService.getMessageCount(condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200"){
				$scope.messageCount = data.count;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 初始化未读举报的数量
	 */
	$scope.initReportCount = function(){
		var condition = {
				status		: common.messageContants.status.NORECEIVE,
		};
		serverIndexHttpService.getReportCount(condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200"){
				$scope.reportCount = data.count;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 初始化待审批店铺的数量
	 */
	$scope.initShopCount = function(){
		var condition = {
				status		: common.shopContants.status.NOAPPEOVE,
		};
		
		serverIndexHttpService.getShopCount(condition)
		.then(function(response){
			var data = response.data;
			if(data.statusCode=="200"){
				$scope.shopCount = data.count;
			}else{
				common.triggerFailMesg(data.message);
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		console.log($currentUser);
		//初始化未读消息的数量
		$scope.initMessageCount();
		//初始化未读举报的数量
		$scope.initReportCount();
		//初始化待审批店铺的数量
		$scope.initShopCount();
	};
	$document.ready($scope.initFunc);
	
}]);