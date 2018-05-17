package com.sky.business.shop.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.shop.entity.Evaluate;

/**
 * 评价Service接口
 * @author Sky James
 *
 */
public interface EvaluateService extends BaseService {
	
	/**
	 * 修改评价信息
	 * @param editObj
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editObj) throws Exception;
	
	/**
	 * 添加评价
	 * @param addObj
	 * @return
	 * @throws Exception
	 */
	public Evaluate add(Map<String,Object> addObj) throws Exception;
	
	/**
	 * 删除评价
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
}
