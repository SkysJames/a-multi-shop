package com.sky.business.bbstopic.service;

import java.util.Map;

import com.sky.business.bbstopic.entity.Bbssection;
import com.sky.business.common.service.BaseService;

public interface BbsSectionService extends BaseService{
	
	/**
	 * 修改版块
	 * @param editObj
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editObj) throws Exception;
	
	/**
	 * 添加版块
	 * @param addObj
	 * @return
	 * @throws Exception
	 */
	public Bbssection add(Map<String,Object> addObj) throws Exception;
	
	/**
	 * 删除版块
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
}
