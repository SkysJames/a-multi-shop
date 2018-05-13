package com.sky.business.system.service.impl;

import javax.servlet.ServletConfig;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.system.service.SystemLoaderService;
import com.sky.util.StorageLocationUtil;

/**
 * 系统启动service服务层
 * @author xiefeiye
 *
 */
@Service("systemLoaderService")
public class SystemLoaderServiceImpl extends BaseServiceImpl implements SystemLoaderService {
	
	@Override
	public void init(ServletConfig servletConfig) {
		log.info("------正在清空临时目录的文件...");
		StorageLocationUtil.clearTemp();
		log.info("------清空临时目录的文件完成!");
	}

	@Override
	public void destroy() {
		
	}

}
