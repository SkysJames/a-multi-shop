package com.sky.business.home.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.sky.business.system.service.SystemLoaderService;
import com.sky.util.BeanDefinedLocator;

/**
 * 系统启动
 * @author xiefeiye
 *
 */
public class SystemLoaderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	SystemLoaderService systemLoaderService = BeanDefinedLocator.getInstance().getBean(SystemLoaderService.class);

	@Override
	public void destroy() {
		super.destroy();
		systemLoaderService.destroy();
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		systemLoaderService.init(servletConfig);
	}

}
