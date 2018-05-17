package com.sky.contants;

/**
 * 代码和详细信息
 * @author Sky James
 *
 */
public final class CodeMescContants {
	
	/**
	 * 代码
	 * @author Sky James
	 *
	 */
	public static final class CodeContants {
		
		//100,200,300,400,500 http原有状态码
		public static final String ERROR_COMMON = "400";
		
		//1~99为通用状态码
		public static final String ERROR_SPREAD_NULL = "000";
		public static final String ERROR_EXIST = "001";
		public static final String ERROR_INEXIST = "002";
	}
	
	/**
	 * 信息
	 * @author Sky James
	 *
	 */
	public static final class MessageContants {
		//100,200,300,400,500 http原有状态码
		public static final String ERROR_COMMON = "后台报错";
		
		//通用状态
		public static final String ERROR_SPREAD_NULL = "传入空对象错误";
		public static final String ERROR_EXIST = "已存在错误";
		public static final String ERROR_INEXIST = "不存在错误";
		
	}
	
}
