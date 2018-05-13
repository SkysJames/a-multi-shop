angular.module('clientBottom',[])
.directive('clientBottom',function(){
	return {
		restrict:'E',
		scope : {
			currentNav	: '=',
		},
		templateUrl : $contextPath +"/sky/client/component/clientBottom/template/clientBottom.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document){
			//公司名称
			$scope.companyName = $companyName;
			//公司地址
			$scope.companyAddress = $companyAddress;
			//公司备案号
			$scope.companyRecord = $companyRecord;
			//版权有效期
			$scope.copyRight = $copyRight;
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
			//新闻类型map
			$scope.newsTypes = common.newsContants.newsType;
			//产品类型map
			$scope.proTypes = common.productContants.proType;
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
		}
	};
});