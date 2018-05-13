package com.sky.business.news.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.Pager;
import com.sky.business.news.entity.News;

/**
 * 新闻Service接口
 * @author Sky James
 *
 */
public interface NewsService extends BaseService {

	/**
	 * 根据一定的条件获取新闻列表
	 * @param condition
	 * @return
	 */
	public Pager pagedList(Map<String, Object> condition) throws Exception;
	
	/**
	 * 添加新闻
	 * @param news
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	public News add(Map<String,Object> news, LoginUser loginUser) throws Exception;
	
	/**
	 * 删除新闻
	 * @param newsId
	 * @param loginUser
	 * @throws Exception
	 */
	public void delete(String newsId, LoginUser loginUser) throws Exception;
	
	/**
	 * 修改新闻信息
	 * @param editNews
	 * @param loginUser
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editNews, LoginUser loginUser) throws Exception;
	
}
