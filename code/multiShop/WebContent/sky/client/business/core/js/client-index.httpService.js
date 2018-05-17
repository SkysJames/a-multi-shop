angular.module('client-index.httpService',[])
.service('clientIndexHttpService',function($http,$filter){
	$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest';
	$http.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.put['X-Requested-With'] = 'XMLHttpRequest';
	
	/**
	 * 获取产品列表
	 */
	this.getProductList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/product/client-product!list.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	
	/**
	 * 获取新闻列表
	 */
	this.getNewsList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/news/client-news!list.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 根据ID获取产品详情
	 */
	this.getProductById = function(code){
		var conditionJson = JSON.stringify({id:code});
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/product/client-product!getProductById.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 根据ID获取新闻详情
	 */
	this.getNewsById = function(code){
		var conditionJson = JSON.stringify({id:code});
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/news/client-news!getNewsById.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
});