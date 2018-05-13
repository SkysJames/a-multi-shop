package com.sky.business.system.service;

import java.util.List;
import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.common.vo.Pager;
import com.sky.business.system.entity.Right;

/**
 * 权限Service接口
 * @author Sky James
 *
 */
public interface RightService extends BaseService {
	
	/**
	 * 根据一定的条件获取权限列表
	 * @param condition
	 * @return
	 */
	public Pager pagedList(Map<String, Object> condition) throws Exception;
	
	/**
	 * 根据权限id（多个以,分隔）获取权限列表
	 * @param idStr
	 * @return
	 * @throws Exception
	 */
	public List<Right> getRightListByIds(String idStr) throws Exception;
	
	/**
	 * 获取所有权限列表（已经分好类）
	 * 如：[{"name":"","rightList":[]}]
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTypeRightList() throws Exception;
	
}
