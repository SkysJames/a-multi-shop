package com.sky.business.product.dao;

import java.util.Map;

import com.sky.business.common.vo.Pager;

/**
 * 产品Dao接口
 * @author Sky James
 *
 */
public interface ProductDao {
	
	/**
	 * 根据一定的条件获取产品列表
	 * @param condition
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Pager pagedList(final Map<String, Object> condition, int pageNo, int pageSize) throws Exception;
	
}
