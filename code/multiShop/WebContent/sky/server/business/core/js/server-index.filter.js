/**
 * 过滤器存放处
 */
angular.module('server-index.filter',[])

/**
 * 将日期时间格式化为yyyy-MM-dd hh24:mi:ss
 */
.filter('formatDate',function(){
	return function(date){
		return DateUtil.formatDate(date);
	};
})

/**
 * 显示性别字符串
 */
.filter('getSex',function(){
	return function(sex){
		if(sex){
			if(sex == "1"){
				return "男";
			}else if(sex == "2"){
				return "女";
			}
		}
		
		return "未知";
	};
})

/**
 * 是否为当前用户
 */
.filter('isCurrentUser',function(){
	return function(userId){
		if(userId && $currentUser && userId==$currentUser.userId){
			return true;
		}else{
			return false;
		}
	};
})

/**
 * 显示剩余可输入的字符长度
 */
.filter('getRemainLength',function(){
	return function(hasString, allLength){
		if(hasString && allLength){
			var remainLength = allLength - hasString.length;
			return (remainLength>0?remainLength:0);
		}else{
			return allLength;
		}
	};
})

/**
 * 获取图片url
 */
.filter('getPicUrl',function(){
	return function(picList){
		if(picList && picList.length>0){
			return picList[0];
		}else{
			return $contextPath + "/sky/common/core/img/no_pic.jpeg";
		}
	};
})

/**
 * 店铺名称的展示，可能系统类型/论坛类型/店铺类型
 */
.filter('showShopName',function(){
	return function(shop){
		var shopName = "";
		
		if(shop && shop.name){
			shopName = shop.name;
		}
		
		return shopName;
	};
})

/**
 * 角色名称的展示
 */
.filter('showRightgroup',function(){
	return function(rightgroupList){
		var rightgroupName = "";
		if(rightgroupList && rightgroupList.length>0){
			for(var i=0;i<rightgroupList.length;i++){
				rightgroupName += rightgroupList[i].name + ",";
			}
			if(rightgroupName.length > 0){
				rightgroupName = rightgroupName.substring(0, rightgroupName.length-1);
			}
		}
		return rightgroupName;
	};
})

/**
 * 店铺推荐状态
 */
.filter('showRecommend',function(){
	return function(recommend){
		if(1==recommend || "1"==recommend){
			return "推荐";
		}else{
			return "普通";
		}
		
	};
})

/**
 * 转换状态为字符串
 */
.filter('stringStatus',function(){
	return function(status){
		if(1==status || "1"==status){
			return "启用";
		}else{
			return "禁用";
		}
	};
})

/**
 * 转换商品状态为字符串
 */
.filter('stringProductStatus',function(){
	return function(status){
		if(2==status || "2"==status){
			return "已上架";
		}else if(1==status || "1"==status){
			return "已下架";
		}else{
			return "禁用";
		}
	};
})


/**
 * 转换店铺状态为字符串
 */
.filter('stringShopStatus',function(){
	return function(status){
		if(2==status || "2"==status){
			return "启用";
		}else if(1==status || "1"==status){
			return "待审批";
		}else{
			return "禁用";
		}
	};
})

/**
 * 转换访客状态为字符串
 */
.filter('stringVisitorStatus',function(){
	return function(status){
		if(1==status || "1"==status){
			return "允许";
		}else{
			return "禁止";
		}
	};
})

/**
 * 展示权限值
 * @returns
 */
.filter('showRightName',function(){
	return function(rightList){
		if(rightList && rightList.length>0){
			var rightName = rightList[0].name;
			
			for(var i=1;i<rightList.length;i++){
				rightName += "," + rightList[i].name;
			}
			
			return rightName;
		}else{
			return ' - ';
		}
	};
})

/**
 * 展示值
 * @returns
 */
.filter('showBlankValue',function(){
	return function(value){
		if(value === null || value === undefined || value == ""){
			return ' - ';
		}else{
			return value;
		}
	};
})
;
