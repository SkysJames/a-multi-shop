angular.module('contactApp',["client-index.filter","client-index.httpService"].concat($directiveList))
.controller("contactCtrl",['$timeout', '$scope', '$document', 'clientIndexHttpService', 
function($timeout, $scope, $document, clientIndexHttpService){
	//公司名称
	$scope.companyName = $companyName;
	//公司地址
	$scope.companyAddress = $companyAddress;
	//联系手机
	$scope.telephone = $telephone;
	//联系电话
	$scope.phone = $phone;
	//邮箱地址
	$scope.email = $email;
	//qq号码
	$scope.qq = $qq;
	//qq联系的Url
	$scope.qqContactUrl = common.qqContactUrl;
	//页面导航
	$scope.clientNavs = common.clientNavs;
	//当前导航对象
	$scope.currentNav = $scope.clientNavs.e_contact;
	//主背景图片url
	$scope.indexBgUrl = "sky/client/component/bgShow/img/contact_bg.jpg";
	//当前背景图片url
	$scope.currentBgUrl = $scope.indexBgUrl;
	//微信二维码url
	$scope.wechatUrl = common.wechatUrl;
	
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