angular.module('clientBottom',[])
.directive('clientBottom',function(){
	return {
		restrict:'E',
		scope : {
		},
		templateUrl : $contextPath +"/sky/client/component/clientBottom/template/clientBottom.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document){
			//系统名称
			$scope.systemName = $systemName;
			//系统logo
			$scope.systemLogo = $systemLogo;
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
			//微信公众号二维码的Url
			$scope.wechatPic = $wechatPic;
			//服务时间
			$scope.serviceTime = $serviceTime;
			
			/**
			 * 切换页面
			 */
			$scope.triggerPage = function(url){
				window.location.href = $contextPath + url;
			};
			
		}
	};
});