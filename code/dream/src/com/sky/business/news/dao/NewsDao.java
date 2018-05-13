package com.sky.business.news.dao;

import java.util.Map;

import com.sky.business.common.vo.Pager;

/**
 * 新闻Dao接口
 * @author Sky James
 *
 */
public interface NewsDao {
	
	/**
	 * 根据一定的条件获取新闻列表
	 * @param condition
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager pagedList(final Map<String, Object> condition, int pageNo, int pageSize);
	
}
