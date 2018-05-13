package com.sky.business.visitor.dao;

import java.util.Map;

import com.sky.business.common.dao.BaseDao;
import com.sky.business.common.vo.Pager;

/**
 * 访客Dao接口
 * @author Sky James
 *
 */
public interface VisitorDao extends BaseDao {
	
	/**
	 * 根据一定的条件获取访客列表
	 * @param condition
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Pager pagedList(final Map<String, Object> condition, int pageNo, int pageSize) throws Exception;
	
}
