package com.sky.business.system.service;

import java.util.List;

import com.sky.business.common.service.BaseService;
import com.sky.business.system.entity.Right;

/**
 * 权限Service接口
 * @author Sky James
 *
 */
public interface RightService extends BaseService {
	
	/**
	 * 根据权限id（多个以,分隔）获取权限列表
	 * @param idStr
	 * @return
	 * @throws Exception
	 */
	public List<Right> getRightListByIds(String idStr) throws Exception;
	
}
