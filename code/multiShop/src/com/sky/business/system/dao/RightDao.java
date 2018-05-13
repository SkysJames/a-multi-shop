package com.sky.business.system.dao;

import java.util.Map;

import com.sky.business.common.vo.Pager;

/**
 * 权限Dao接口
 * @author Sky James
 *
 */
public interface RightDao {
	
	/**
	 * 根据一定的条件获取权限列表
	 * @param condition
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager pagedList(final Map<String, Object> condition, int pageNo, int pageSize);
	
}
