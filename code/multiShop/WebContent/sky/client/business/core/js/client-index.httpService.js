angular.module('client-index.httpService',[])
.service('clientIndexHttpService',function($http,$filter){
	$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest';
	$http.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.put['X-Requested-With'] = 'XMLHttpRequest';
	
	/**
	 * 登录
	 */
	this.login = function(loginUser){
		var tempData={
				'loginUser.userId'		: loginUser.userId,
				'loginUser.userPwd'		: loginUser.userPwd,
		};
		var url = $contextPath + "/home/client-login";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 退出登录
	 */
	this.logout = function(){
		var tempData={};
		var url = $contextPath + "/home/client-logout";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 申请注册
	 */
	this.registerUser = function(user){
		var conditionJson = JSON.stringify(user);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/user-visit!register";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 忘记密码
	 */
	this.forgetPasswd = function(user){
		var conditionJson = JSON.stringify(user);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/user-visit!forgetPasswd";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 根据用户ID获取用户信息
	 */
	this.getUserById = function(userId){
		var tempData={};
		tempData.userId = userId;
		var url = $contextPath + "/system/user-client!getUserById";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改用户个人信息
	 */
	this.editPerson = function(user){
		var conditionJson = JSON.stringify(user);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/user-client!editPerson";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改密码
	 */
	this.editPersonPawd = function(userId, oldPasswd, newPasswd){
		var tempData={};
		tempData.userId = userId;
		tempData.oldPasswd = oldPasswd;
		tempData.newPasswd = newPasswd;
		var url = $contextPath + "/system/user-client!editPersonPawd";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 分页获取收藏夹/历史记录列表
	 */
	this.pagedProhistoryList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/prohistory-client!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取收藏夹/历史记录列表
	 */
	this.getProhistoryList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/prohistory-client!list";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 添加或编辑收藏夹/历史记录列表
	 */
	this.saveOrUpdateProhistory = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/prohistory-client!saveOrUpdate";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除收藏夹/历史记录列表
	 */
	this.deleteProhistory = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/prohistory-client!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
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
	 * 添加店铺申请
	 */
	this.registerShop = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/shop-client!register";
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
	
	/**
	 * 分页获取评论列表
	 */
	this.pagedEvaluateList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/evaluate-visit!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 添加评论
	 */
	this.addEvaluate = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/evaluate-client!save";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除评论
	 */
	this.deleteEvaluate = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/evaluate-client!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 添加消息
	 */
	this.addMessage = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/message/message-client!save";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取购物车列表
	 */
	this.getCartList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/cart-client!list";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 添加购物车
	 */
	this.addCart = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/cart-client!save";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 编辑购物车
	 */
	this.editCart = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/cart-client!edit";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除购物车
	 */
	this.deleteCart = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/cart-client!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 清空购物车
	 */
	this.batchDeleteCart = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/cart-client!batchDelete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
});