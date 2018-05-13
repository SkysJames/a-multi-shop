package com.sky.business.system.dao;

import java.util.Map;

import com.sky.business.common.vo.Pager;

/**
 * 角色Dao接口
 * @author Sky James
 *
 */
public interface RightGroupDao {
	
	/**
	 * 根据一定的条件获取角色列表
	 * @param condition
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager pagedList(final Map<String, Object> condition, int pageNo, int pageSize);
	
}
