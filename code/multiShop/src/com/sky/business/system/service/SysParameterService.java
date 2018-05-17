package com.sky.business.system.service;

import com.sky.business.common.service.BaseService;

/**
 * 权限Service接口
 * @author Sky James
 *
 */
public interface SysParameterService extends BaseService {
	
	/**
	 * 获取系统参数（字符串类型）
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public String getStringValue(String name, String defaultValue);
	
	/**
	 * 获取系统参数（整数类型）
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public Integer getIntValue(String name, Integer defaultValue);
	
	/**
	 * 获取系统参数（布尔类型）
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public Boolean getBooleanValue(String name, Boolean defaultValue);

	/**
	 * 保存系统参数
	 * @param name
	 * @param value
	 */
	public void saveValue(String name, String value);
	
}
