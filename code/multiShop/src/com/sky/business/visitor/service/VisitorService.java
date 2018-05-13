package com.sky.business.visitor.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.common.vo.Pager;
import com.sky.business.visitor.entity.Visitor;

/**
 * 访客Service接口
 * @author Sky James
 *
 */
public interface VisitorService extends BaseService {
	
	/**
	 * 根据一定的条件获取访客列表
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Pager pagedList(Map<String, Object> condition) throws Exception;
	
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
