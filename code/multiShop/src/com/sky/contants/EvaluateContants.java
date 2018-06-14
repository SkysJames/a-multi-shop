package com.sky.contants;

/**
 * 评价实体类的属性常数
 * @author xiefeiye
 *
 */
public class EvaluateContants {
	
	/**
	 * 评价状态
	 * @author xiefeiye
	 *
	 */
	public static final class Status {
		/**
		 * 评价状态，未发送
		 */
		public static final Integer NOSEND = 0;
		/**
		 * 评价状态，已发送未接收
		 */
		public static final Integer NORECEIVE = 1;
		/**
		 * 评价状态，已接收
		 */
		public static final Integer RECEIVED = 2;
	}
	
}
