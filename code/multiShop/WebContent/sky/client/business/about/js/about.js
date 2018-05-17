angular.module('aboutApp',["client-index.filter","client-index.httpService"].concat($directiveList))
.controller("aboutCtrl",['$timeout', '$scope', '$document', 'clientIndexHttpService', 
function($timeout, $scope, $document, clientIndexHttpService){
	//页面导航
	$scope.clientNavs = common.clientNavs;
	//当前导航对象
	$scope.currentNav = $scope.clientNavs.b_about;
	//主背景图片url
	$scope.indexBgUrl = "sky/client/component/bgShow/img/about_bg.jpg";
	//当前背景图片url
	$scope.currentBgUrl = $scope.indexBgUrl;
	//关于我们是谁的图片列表
	$scope.whoImgs = [
		{
			imgUrl	: "sky/client/business/about/img/about_who_1.png",
			title1	: "我们沉浸于用户体验",
			title2	: "帮您挖掘和创新更大商机的可能",
		},
		{
			imgUrl	: "sky/client/business/about/img/about_who_2.png",
			title1	: "服务思维下的商业设计",
			title2	: "撇弃浮夸去直达共同的目标",
		},
		{
			imgUrl	: "sky/client/business/about/img/about_who_3.png",
			title1	: "精益合作和分享的态度",
			title2	: "让我们鼎力相助你的梦想",
		},
	];
	//关于服务优势的图片列表
	$scope.serviceImgs = [
		{
			imgUrl	: "sky/client/business/about/img/about_service_1.jpg",
			content	: "从创始至今，我们的业务方向始终不变，专注做好一件事才能更好的追求高品质，这也是荔枝存在的价值。在未来我们也是基于此信念而继续创造...",
		},
		{
			imgUrl	: "sky/client/business/about/img/about_service_2.jpg",
			content	: "在不断的创新和实践中总结出可持续和可信赖的设计流程，坚持与用户一起思考，用设计的方法发现问题、解决问题、输出设计方案，并实现客户产品和企业价值的提升...",
		},
		{
			imgUrl	: "sky/client/business/about/img/about_service_3.jpg",
			content	: "5年来积累了大量优秀的项目案例，并验证了设计的价值。在不断的探索中总结经验，以结果为导向，继续为更多客户提供优秀的设计...",
		},
	];
	//关于我们的团队的图片列表
	$scope.teamImgs = [
		{
			imgUrl	: "sky/client/business/about/img/about_team_1.jpg",
		},
		{
			imgUrl	: "sky/client/business/about/img/about_team_2.jpg",
		},
		{
			imgUrl	: "sky/client/business/about/img/about_team_3.jpg",
		},
		];
	
	/**
	 * 切换页面
	 */
	$scope.triggerPage = function(url){
		window.location.href = $contextPath + url;
	};
	
	/**
	 * 初始化函数
	 */
	$scope.initFunc = function(){
		
	};
	$document.ready($scope.initFunc);
	
}]);