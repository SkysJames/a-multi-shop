angular.module('serverIndexApp',["server-index.filter","server-index.httpService"].concat($directiveList))
.controller("serverIndexCtrl",['$timeout', '$scope', '$document', 'serverIndexHttpService', 
function($timeout, $scope, $document, serverIndexHttpService){
	//导航对象ID
	$scope.$root.navObj = {
			//首页
			index	: {
				id	: "index",
			},
			
			//系统管理
			system	: {
				id			: "system",
				secondNav	: {
					//系统二级菜单，基本信息
					system_basic		: {
						id		: "system_basic",
						parent	: "system",
					},
					//系统二级菜单，关于我们
					system_about		: {
						id		: "system_about",
						parent	: "system",
					},
				},
			},
			
			//产品管理
			product	: {
				id	: "product",
			},
			
			//新闻管理
			news		: {
				id	: "news",
			},
			
			//访客管理
			visitor	: {
				id	: "visitor",
			},
			
			//用户管理
			user		: {
				id	: "user",
			},
			
			//日志管理
			oplog	: {
				id	: "oplog",
			},
	};
	$scope.$root.currentUser = null;//当前登录的用户
	$scope.$root.panelContants = common.panelContants;//面板类型常量
	$scope.$root.currentPanel = $scope.$root.panelContants.INDEX_PANEL;//右边的当前面板
	$scope.currentNav = $scope.$root.navObj.index;//默认当前导航：首页
	$scope.isHideLeft = false;//默认展开导航列表
	
	/**
	 * 初始化编辑框
	 */
	$scope.$root.initEidtor = function(target){
		$scope.$root.editor = KindEditor.create(target, {
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
	 * 顶部标题的切换
	 */
	$scope.$root.triggerTitle = function(panelContant){
		//判断是否当前面板
		if($scope.$root.isCurrentPanel($scope.$root.currentPanel, panelContant)){
			return;
		}
		
		//判断当前面板是否已包含了该切换的面板
		var index = $scope.$root.currentPanel.indexOf(panelContant);
		if(index > -1){
			var lastIndex = index + panelContant.length;
			$scope.$root.currentPanel = $scope.$root.currentPanel.substring(0, lastIndex);
		}else{
			$scope.$root.currentPanel += "," + panelContant;
		}
	};
	
	/**
	 * 判断lastPanel是否为当前面板
	 * 即判断currentPanel的最后一个,后面的字符串是否为lastPanel
	 */
	$scope.$root.isCurrentPanel = function(currentPanel, lastPanel){
		var lastIndex = currentPanel.lastIndexOf(",");
		if(currentPanel.substring(lastIndex+1, currentPanel.length) == lastPanel){
			return true;
		}
		return false;
	};
	
	/**
	 * 返回上一级面板
	 */
	$scope.$root.returnPanel = function(){
		var lastIndex = $scope.$root.currentPanel.lastIndexOf(",");
		if(lastIndex > -1){
			$scope.$root.currentPanel = $scope.$root.currentPanel.substring(0, lastIndex);
		}
	};
	
	/**
	 * 获取当前用户信息
	 */
	$scope.$root.getCurrentUser = function(){
		serverIndexHttpService.getUserById($currentUserId)
		.then(function(response){
			var data = response.data;
			if(data.user){
				$scope.$root.currentUser = data.user;
			}
		},function(err){
			console.log(err);
		});
	};
	
	/**
	 * 显示某一导航的详细信息
	 */
	$scope.$root.showDetailPanel = function(nav){
		if($scope.currentNav != nav){
			$scope.currentNav = nav;
		}
	};
	
	/**
	 * 展开/收缩 二级菜单
	 */
	$scope.toggleSecondNav = function(nav){
		nav.spreaded = !nav.spreaded;
	};
	
	/**
	 * 收缩或展示左边导航列表
	 */
	$scope.toggleDetailPanel = function(){
		$scope.isHideLeft = !$scope.isHideLeft;
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
		//运行初始化背景
		initBg();
		//获取登录用户的详细信息
		$scope.$root.getCurrentUser();
		
		//初始化浏览器大小监控，当浏览器的大小发生变化时调用
		$(window).resize(function(){
			initBgSize();
		});
	};
	$document.ready($scope.initFunc);
	
}]);