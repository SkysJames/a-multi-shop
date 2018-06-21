package com.sky.contants;

/**
 * 用户常量
 * @author xiefeiye
 *
 */
public class UserContants {
	
	public static final String defaultPasswd = "123456aB";
	
	/**
	 * 系统用户ID
	 */
	public static final String USER_ADMIN = "admin";
	
	/**
	 * 用户状态
	 * @author Sky James
	 *
	 */
	public static final class UserStatus {
		/**
		 * 禁用状态
		 */
		public final static int UNUSING = 0;
		/**
		 * 启用状态
		 */
		public final static int USING = 1;
	}
	
	/**
	 * 登录状态
	 * @author Sky James
	 *
	 */
	public static final class LoginStatus {
		/**
		 * 离线状态
		 */
		public final static int OFFLINE = 0;
		/**
		 * 在线状态
		 */
		public final static int ONLINE = 1;
	}
	
}
