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
 * 展示值
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
