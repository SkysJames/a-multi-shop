angular.module('client-index.httpService',[])
.service('clientIndexHttpService',function($http,$filter){
	$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest';
	$http.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.put['X-Requested-With'] = 'XMLHttpRequest';
	
	/**
	 * 获取公告列表
	 */
	this.getAnnounceList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/announce-visit!list";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取类型列表
	 */
	this.getTypeList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/type-visit!list";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 分页获取店铺列表
	 */
	this.pagedShopList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/shop-visit!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 根据id获取店铺信息
	 */
	this.getShopById = function(shopId){
		var tempData={
				'shopId'		: shopId,
		};
		var url = $contextPath + "/shop/shop-visit!getShopById";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 添加店铺人气值
	 */
	this.addShopPopularity = function(shopId){
		var tempData={
				'shopId'		: shopId,
		};
		var url = $contextPath + "/shop/shop-visit!addPopularity";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取商品列表
	 */
	this.getProductList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/product-visit!list";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 分页获取商品列表
	 */
	this.pagedProductList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/product-visit!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 根据id获取商品信息
	 */
	this.getProductById = function(productId){
		var tempData={
				'productId'		: productId,
		};
		var url = $contextPath + "/shop/product-visit!getProductById";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 添加商品浏览量
	 */
	this.addProClickCount = function(productId){
		var tempData={
				'productId'		: productId,
		};
		var url = $contextPath + "/shop/product-visit!addClickCount";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	
});