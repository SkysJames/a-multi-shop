package com.sky.business.bbstopic.service;

import java.util.Map;


import com.sky.business.bbstopic.entity.Bbstopic;
import com.sky.business.common.service.BaseService;


public interface BbsTopicService extends BaseService{
	
	
	/**
	 * 编辑帖子
	 * @param editObj
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editObj) throws Exception;
	
	/**
	 * 新增贴子
	 * @param addObj
	 * @return
	 * @throws Exception
	 */
	public Bbstopic add(Map<String,Object> addObj) throws Exception;
	
	/**
	 * 删除帖子
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;

}
