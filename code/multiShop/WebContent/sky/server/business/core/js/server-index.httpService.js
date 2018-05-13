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
		var url = $contextPath + "/system/user!getUserById.action";
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
		var url = $contextPath + "/system/user!editPerson.action";
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
		var url = $contextPath + "/system/user!editPersonPawd.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取用户列表
	 */
	this.getUserList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/user!list.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 新增用户
	 */
	this.saveUser = function(product){
		var conditionJson = JSON.stringify(product);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/user!save.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改用户
	 */
	this.editUser = function(product){
		var conditionJson = JSON.stringify(product);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/user!edit.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除用户
	 */
	this.deleteUser = function(product){
		var conditionJson = JSON.stringify(product);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/user!delete.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取部门列表
	 */
	this.getDepartmentList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/department!list.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取部门总列表
	 */
	this.getAllDepartmentList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/department!getAllList.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 新增部门
	 */
	this.saveDepartment = function(product){
		var conditionJson = JSON.stringify(product);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/department!save.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改部门
	 */
	this.editDepartment = function(product){
		var conditionJson = JSON.stringify(product);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/department!edit.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除部门
	 */
	this.deleteDepartment = function(product){
		var conditionJson = JSON.stringify(product);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/department!delete.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取权限列表
	 */
	this.getRightList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/right!list.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取所有权限列表（已经分好类）
	 */
	this.getTypeRightList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/right!getTypeRightList.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取角色列表
	 */
	this.getRightGroupList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/right-group!list.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取角色总列表
	 */
	this.getAllRightGroupList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/right-group!getAllList.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 新增角色
	 */
	this.saveRightGroup = function(product){
		var conditionJson = JSON.stringify(product);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/right-group!save.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改角色
	 */
	this.editRightGroup = function(product){
		var conditionJson = JSON.stringify(product);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/right-group!edit.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除角色
	 */
	this.deleteRightGroup = function(product){
		var conditionJson = JSON.stringify(product);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/right-group!delete.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取访客列表
	 */
	this.getVisitorList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/visitor/visitor!list.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改访客
	 */
	this.editVisitor = function(visitor){
		var conditionJson = JSON.stringify(visitor);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/visitor/visitor!edit.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除访客
	 */
	this.deleteVisitor = function(visitor){
		var conditionJson = JSON.stringify(visitor);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/visitor/visitor!delete.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取产品列表
	 */
	this.getProductList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/product/product!list.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 新增产品
	 */
	this.saveProduct = function(product){
		var conditionJson = JSON.stringify(product);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/product/product!save.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改产品
	 */
	this.editProduct = function(product){
		var conditionJson = JSON.stringify(product);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/product/product!edit.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除产品
	 */
	this.deleteProduct = function(product){
		var conditionJson = JSON.stringify(product);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/product/product!delete.action";
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
		var url = $contextPath + "/news/news!list.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 新增新闻
	 */
	this.saveNews = function(news){
		var conditionJson = JSON.stringify(news);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/news/news!save.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改新闻
	 */
	this.editNews = function(news){
		var conditionJson = JSON.stringify(news);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/news/news!edit.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除新闻
	 */
	this.deleteNews = function(news){
		var conditionJson = JSON.stringify(news);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/news/news!delete.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取日志列表
	 */
	this.getOplogList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/oplog/oplog!list.action";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
});