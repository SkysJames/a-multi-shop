/**
 * 过滤器存放处
 */
angular.module('client-index.filter',[])

/**
 * 将日期时间格式化为yyyy-MM-dd hh24:mi:ss
 */
.filter('formatDate',function(){
	return function(date){
		return DateUtil.formatDate(date);
	};
})

/**
 * 返回图片的url
 */
.filter('getImgUrl',function(){
	return function(imgUrl){
		if(imgUrl){
			return imgUrl;
		}
		return $contextPath + "/sky/common/img/no_pic.jpeg";
	};
})

/**
 * 返回缩略的内容
 */
.filter('getSmallContent',function(){
	return function(content){
		if(content){
			var limit = 100;
			if(content.length > limit){
				return content.substring(0, limit) + "...";
			}else{
				return content;
			}
		}
		return "-";
	};
})

/**
 * 转换新闻类型为字符串
 */
.filter('stringNewsType',function(){
	return function(newsType){
		switch(newsType){
		case 0:
			return "公司新闻";
		case 1:
			return "行业动态";
		}
		return null;
	};
})

/**
 * 转换产品类型为字符串
 */
.filter('stringProType',function(){
	return function(proType){
		switch(proType){
		case 0:
			return "网页设计";
		case 1:
			return "管理系统";
		case 2:
			return "课程设计";
		case 3:
			return "微信小程序";
		}
		return null;
	};
})

/**
 * 转换访客状态为字符串
 */
.filter('stringVisitorStatus',function(){
	return function(status){
		if(common.visitorContants.status.USING == status){
			return "允许";
		}else{
			return "禁止";
		}
	};
})

/**
 * 转换用户状态为字符串
 */
.filter('stringUserStatus',function(){
	return function(status){
		if(common.userContants.userStatus.USING == status){
			return "启用";
		}else{
			return "禁用";
		}
	};
})

/**
 * 展示权限名
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