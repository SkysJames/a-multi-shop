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
 * 获取关键字搜索类型
 */
.filter('getKeyType',function(){
	return function(tableName){
		if("tb_shop"==tableName){
			return "店铺";
		}else if("tb_product"==tableName){
			return "商品";
		}
		return "";
	};
})

/**
 * 获取类型的名称
 */
.filter('getTypeName',function(){
	return function(tableName){
		if("tb_shop"==tableName){
			return "店铺分类";
		}else if("tb_product"==tableName){
			return "商品分类";
		}
		return "";
	};
})

/**
 * 获取logo的url
 */
.filter('getLogoByLogoList',function(){
	return function(logoList){
		if(logoList && logoList.length>0){
			return logoList[0];
		}
		return $contextPath + "/sky/common/core/img/system_logo.png";
	};
})

/**
 * 获取图片的url
 */
.filter('getImgUrl',function(){
	return function(url){
		if(url && url.length>0){
			return url;
		}
		return $contextPath + "/sky/common/core/img/no_pic.jpeg";
	};
})

/**
 * 在url列表中获取第一个图片的url
 */
.filter('getImgByImgList',function(){
	return function(imgList){
		if(imgList && imgList.length>0){
			return imgList[0];
		}
		return $contextPath + "/sky/common/core/img/no_pic.jpeg";
	};
})

/**
 * 是否已被收藏
 * id - 被收藏的对象ID（店铺id或商品id）
 * list - 收藏总列表
 */
.filter('getCollectedObj',function(){
	return function(id, list){
		if(list && list.length>0){
			for(var i=0,len=list.length; i<len; i++){
				if(id == list[i].objId){
					return list[i];
				}
			}
		}
		return null;
	};
})

/**
 * 判断是否有权限显示
 */
.filter('hasRightShow',function(){
	return function(userId){
		if($currentUser && userId && $currentUser.userId == userId){
			return true;
		}
		return false;
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
