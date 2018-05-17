package com.sky.contants;

/**
 * 店铺实体类的属性常数
 * @author xiefeiye
 *
 */
public class ShopContants {
	/**
	 * 系统类型的店铺ID
	 */
	public static final String SHOP_SYSTEM = "system";
	/**
	 * 论坛类型的店铺ID，实际无此店铺
	 */
	public static final String SHOP_BBS = "bbs";
	
	/**
	 * 店铺状态
	 * @author xiefeiye
	 *
	 */
	public static final class Status {
		/**
		 * 店铺状态，禁用
		 */
		public static final Integer UNUSING = 0;
		/**
		 * 店铺状态，申请待验证
		 */
		public static final Integer REGISTER = 1;
		/**
		 * 店铺状态，启用
		 */
		public static final Integer USING = 2;
	}
	
}
