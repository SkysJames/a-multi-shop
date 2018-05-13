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
		//1~99为通用状态码
		public static final String NULL_ERROR = "000";
		
		//登录
		public static final String LOGIN_ERROR = "601";
		public static final String LOGIN_USER_ERROR = "602";
		public static final String LOGIN_USER_STATIC = "603";
		
		//产品
		public static final String PRODUCT_ERROR = "701";
		public static final String PRODUCT_NULL = "702";
		public static final String PRODUCT_EXIST = "703";
		public static final String PRODUCT_DELETE = "704";
		
		//新闻
		public static final String NEWS_ERROR = "801";
		public static final String NEWS_NULL = "802";
		public static final String NEWS_EXIST = "803";
		public static final String NEWS_DELETE = "804";
		
		//用户
		public static final String USER_ERROR = "901";
		public static final String USER_INEXIST = "902";
		public static final String USER_RIGHT = "903";
		public static final String USER_EXIST = "904";
		public static final String USER_PASSWD = "905";
		
		
		//访客
		public static final String VISITOR_ERROR = "1001";
		public static final String VISITOR_INEXIST = "1002";
		
		//日志
		public static final String OPLOG_ERROR = "1101";
		
		//部门
		public static final String DEPARTMENT_ERROR = "1201";
		public static final String DEPARTMENT_INEXIST = "1202";
		public static final String DEPARTMENT_EXIST = "1203";
		public static final String DEPARTMENT_INIT = "1204";
		
		//角色
		public static final String RIGHTGROUP_ERROR = "1301";
		public static final String RIGHTGROUP_INEXIST = "1302";
		public static final String RIGHTGROUP_EXIST = "1303";
		
		//权限
		public static final String RIGHT_ERROR = "1401";
		public static final String RIGHT_INEXIST = "1402";
		public static final String RIGHT_EXIST = "1403";
		
	}
	
	/**
	 * 信息
	 * @author Sky James
	 *
	 */
	public static final class MessageContants {
		//通用状态
		public static final String NULL_ERROR = "传入空对象错误";
		
		//登录
		public static final String LOGIN_ERROR = "登录部分错误";
		public static final String LOGIN_USER_ERROR = "用户名或者密码错误";
		public static final String LOGIN_USER_STATIC = "该用户已被禁用";
		
		//产品
		public static final String PRODUCT_ERROR = "产品管理部分错误";
		public static final String PRODUCT_NULL = "不存在该产品";
		public static final String PRODUCT_EXIST = "存在名字相同的产品";
		public static final String PRODUCT_DELETE = "无权限删除该产品";
		
		//新闻
		public static final String NEWS_ERROR = "新闻管理部分错误";
		public static final String NEWS_NULL = "不存在该新闻";
		public static final String NEWS_EXIST = "存在名字相同的新闻";
		public static final String NEWS_DELETE = "无权限删除该新闻";
		
		//用户
		public static final String USER_ERROR = "用户管理部分错误";
		public static final String USER_INEXIST = "不存在该用户";
		public static final String USER_RIGHT = "该用户无权限操作";
		public static final String USER_EXIST = "该用户名已存在";
		public static final String USER_PASSWD = "密码错误";
		
		//访客
		public static final String VISITOR_ERROR = "访客管理部分错误";
		public static final String VISITOR_INEXIST = "不存在该访客";
		
		//日志
		public static final String OPLOG_ERROR = "日志管理部分错误";
		
		//部门
		public static final String DEPARTMENT_ERROR = "部门相关操作有误";
		public static final String DEPARTMENT_INEXIST = "不存在该部门";
		public static final String DEPARTMENT_EXIST = "该部门已经存在";
		public static final String DEPARTMENT_INIT = "初始部门不能删除";
		
		//角色
		public static final String RIGHTGROUP_ERROR = "角色相关操作有误";
		public static final String RIGHTGROUP_INEXIST = "不存在该角色";
		public static final String RIGHTGROUP_EXIST = "该角色已经存在";
		
		//权限
		public static final String RIGHT_ERROR = "权限相关操作有误";
		public static final String RIGHT_INEXIST = "不存在该权限";
		public static final String RIGHT_EXIST = "该权限已经存在";
		
	}
	
}
