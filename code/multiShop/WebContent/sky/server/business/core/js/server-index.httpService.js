angular.module('server-index.httpService',[])
.service('serverIndexHttpService',function($http,$filter){
	$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest';
	$http.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.put['X-Requested-With'] = 'XMLHttpRequest';
	
	/**
	 * 根据用户ID获取用户信息
	 */
	this.getUserById = function(userId){
		var tempData={};
		tempData.userId = userId;
		var url = $contextPath + "/system/user!getUserById";
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
		var url = $contextPath + "/system/user!editPerson";
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
		var url = $contextPath + "/system/user!editPersonPawd";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 分页获取用户列表
	 */
	this.pagedUserList = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/user!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 新增用户
	 */
	this.saveUser = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/user!save";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改用户
	 */
	this.editUser = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/user!edit";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除用户
	 */
	this.deleteUser = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/user!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取所有的店铺列表
	 */
	this.getAllShopList = function(condition){
		var tempData={};
		var url = $contextPath + "/shop/shop!getAllList";
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
		var url = $contextPath + "/shop/shop!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 根据店铺ID获取店铺信息
	 */
	this.getShopById = function(shopId){
		var tempData={};
		tempData.shopId = shopId;
		var url = $contextPath + "/shop/shop!getShopById";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 新增店铺
	 */
	this.saveShop = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/shop!save";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改店铺
	 */
	this.editShop = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/shop!edit";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除店铺
	 */
	this.deleteShop = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/shop!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取所有权限列表
	 */
	this.getAllRightList = function(condition){
		var tempData={};
		var url = $contextPath + "/system/right!getAllList";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取所有角色列表
	 */
	this.getAllRightGroupList = function(condition){
		var tempData={};
		var url = $contextPath + "/system/right-group!getAllList";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 新增角色
	 */
	this.saveRightGroup = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/right-group!save";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改角色
	 */
	this.editRightGroup = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/right-group!edit";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除角色
	 */
	this.deleteRightGroup = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/right-group!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 分页获取访客列表
	 */
	this.pagedVisitorList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/visitor/visitor!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改访客
	 */
	this.editVisitor = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/visitor/visitor!edit";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除访客
	 */
	this.deleteVisitor = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/visitor/visitor!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取产品列表
	 */
	this.pagedProductList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/product!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 新增产品
	 */
	this.saveProduct = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/product!save";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改产品
	 */
	this.editProduct = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/product!edit";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除产品
	 */
	this.deleteProduct = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/product!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 分页获取日志列表
	 */
	this.pagedOplogList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/oplog/oplog!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
});