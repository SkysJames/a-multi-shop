package com.sky.util;

import com.sky.business.system.service.SysParameterService;

/**
 * 系统参数工具类
 * @author Sky James
 *
 */
public class SysParameterUtil {
	private static SysParameterService sysParameterService = (SysParameterService)BeanDefinedLocator.getInstance().getBean("sysParameterService");
	
	/**
	 * 获取系统参数（字符串类型）
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getStringValue(String name, String defaultValue) {
		return sysParameterService.getStringValue(name, defaultValue);
	}
	
	/**
	 * 获取系统参数（整数类型）
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static int getIntValue(String name, int defaultValue) {
		return sysParameterService.getIntValue(name, defaultValue);
	}
	
	/**
	 * 获取系统参数（布尔类型）
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBooleanValue(String name, boolean defaultValue) {
		return sysParameterService.getBooleanValue(name, defaultValue);
	}
	
	/**
	 * 保存或更新
	 * @param name
	 * @param value
	 */
	public static void saveStringValue(String name, String value) {
		sysParameterService.saveValue(name, value);
	}
	
	/**
	 * 保存或更新int
	 * @param name
	 * @param value
	 */
	public static void saveIntValue(String name, int value) {
		saveStringValue(name, String.valueOf(value));
	}
	
	/**
	 * 保存或更新boolean
	 * @param name
	 * @param value
	 */
	public static void saveBooleanValue(String name, boolean value) {
		saveStringValue(name, String.valueOf(value));
	}
}
