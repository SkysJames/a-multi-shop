package com.sky.business.system.dao;

import java.util.Map;

import com.sky.business.common.vo.Pager;

/**
 * 部门Dao接口
 * @author Sky James
 *
 */
public interface DepartmentDao {
	
	/**
	 * 根据一定的条件获取部门列表
	 * @param condition
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager pagedList(final Map<String, Object> condition, int pageNo, int pageSize);
	
}
