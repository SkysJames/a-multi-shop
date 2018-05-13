package com.sky.business.system.service;

import javax.servlet.ServletConfig;

import com.sky.business.common.service.BaseService;

/**
 * 系统启动service层
 * @author xiefeiye
 *
 */
public interface SystemLoaderService extends BaseService {
	/**
	 * 跟随系统启动的东西统一在这里定义
	 * @param servletConfig
	 */
	public void init(ServletConfig servletConfig);
	
	public void destroy();
}
