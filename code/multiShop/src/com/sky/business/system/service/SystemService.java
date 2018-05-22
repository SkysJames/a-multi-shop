package com.sky.business.system.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;

/**
 * 系统管理service层
 * @author xiefeiye
 *
 */
public interface SystemService extends BaseService {
	
	/**
	 * 获取系统的基本信息
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getSystemInfo() throws Exception;
	
	/**
	 * 保存系统的基本信息
	 * @param system
	 * @throws Exception
	 */
	public void saveSystemInfo(Map<String, Object> systemInfo) throws Exception;
	
}
