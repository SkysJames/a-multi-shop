angular.module('server-index.httpService',[])
.service('serverIndexHttpService',function($http,$filter){
	$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest';
	$http.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	$http.defaults.headers.put['X-Requested-With'] = 'XMLHttpRequest';
	
	/**
	 * 获取系统信息
	 */
	this.getSystemInfo = function(){
		var tempData={};
		var url = $contextPath + "/system/system!getSystemInfo";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 保存系统信息
	 */
	this.saveSystemInfo = function(systemInfo){
		var conditionJson = JSON.stringify(systemInfo);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/system!saveSystemInfo";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取评论数量
	 */
	this.getEvaluateCount = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/evaluate!count";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 分页获取评论列表
	 */
	this.pagedEvaluateList = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/evaluate!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改评论
	 */
	this.editEvaluate = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/evaluate!edit";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除评论
	 */
	this.deleteEvaluate = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/evaluate!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取消息数量
	 */
	this.getMessageCount = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/message/message!count";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 分页获取消息列表
	 */
	this.pagedMessageList = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/message/message!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改消息
	 */
	this.editMessage = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/message/message!edit";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除消息
	 */
	this.deleteMessage = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/message/message!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 获取举报数量
	 */
	this.getReportCount = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/message/report!count";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 分页获取举报列表
	 */
	this.pagedReportList = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/message/report!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改举报
	 */
	this.editReport = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/message/report!edit";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除举报
	 */
	this.deleteReport = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/message/report!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 分页获取公告列表
	 */
	this.pagedAnnounceList = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/announce!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 新增公告
	 */
	this.saveAnnounce = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/announce!save";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改公告
	 */
	this.editAnnounce = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/announce!edit";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除公告
	 */
	this.deleteAnnounce = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/announce!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
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
	this.pagedUserList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/user!paged";
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
		var url = $contextPath + "/system/user!list";
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
	 * 获取类型列表
	 */
	this.getTypetList = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/type!list";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 新增类型
	 */
	this.saveTypet = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/type!save";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 修改类型
	 */
	this.editTypet = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/type!edit";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 删除类型
	 */
	this.deleteTypet = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/system/type!delete";
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
	 * 获取店铺息数量
	 */
	this.getShopCount = function(condition){
		var conditionJson = JSON.stringify(condition);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/shop/shop!count";
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
	
	/**
	 * 分页获取论坛版块列表
	 */
	this.pagedSectionList = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/bbstopic/section!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 论坛版块编辑
	 */
	this.editBbsSection = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/bbstopic/section!edit";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 论坛版块新增
	 */
	this.saveBbsSection = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/bbstopic/section!save";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 论坛版块删除
	 */
	this.deleteBbsSection = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/bbstopic/section!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	this.findAllSection = function(){
		var url = $contextPath + "/bbstopic/section!findAllSection";
		return $http({url : url, method : 'POST', data : null});		
	}
	
	/**
	 * 分页获取论坛主帖列表
	 */
	this.pagedTopicList = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/bbstopic/topic!paged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 分页获取论坛回帖列表
	 */
	this.getChildrenTopicPaged = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/bbstopic/topic!getChildrenTopicPaged";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 论坛帖子编辑
	 */
	this.editBbsTopic = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/bbstopic/topic!edit";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 论坛帖子新增
	 */
	this.saveBbsTopic = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/bbstopic/topic!save";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
	
	/**
	 * 论坛帖子删除
	 */
	this.deleteBbsTopic = function(obj){
		var conditionJson = JSON.stringify(obj);
		var tempData={
				'conditionJson'		: conditionJson,
		};
		var url = $contextPath + "/bbstopic/topic!delete";
		return $http({url : url, method : 'POST', data : $.param(tempData,true)});		
	};
});