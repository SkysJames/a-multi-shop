package com.sky.business.visitor.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;

/**
 * 访客Service接口
 * @author Sky James
 *
 */
public interface VisitorService extends BaseService {
	
	/**
	 * 修改访客信息
	 * @param editVisitor
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editVisitor) throws Exception;
	
	/**
	 * 根据id删除访客数据
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
}
