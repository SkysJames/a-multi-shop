package com.sky.business.oplog.dao;

import java.util.Map;

import com.sky.business.common.dao.BaseDao;
import com.sky.business.common.vo.Pager;

/**
 * 操作日志Dao接口
 * @author Sky James
 *
 */
public interface OplogDao {
	
	/**
	 * 根据一定的条件获取日志列表
	 * @param condition
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager pagedList(final Map<String, Object> condition, int pageNo, int pageSize);
	
}
