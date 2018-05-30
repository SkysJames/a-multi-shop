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
