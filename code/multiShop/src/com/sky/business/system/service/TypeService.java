package com.sky.business.system.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.system.entity.Typet;

/**
 * 类型Service接口
 * @author Sky James
 *
 */
public interface TypeService extends BaseService {
	
	/**
	 * 修改类型
	 * @param editObj
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editObj) throws Exception;
	
	/**
	 * 添加类型
	 * @param addObj
	 * @return
	 * @throws Exception
	 */
	public Typet add(Map<String,Object> addObj) throws Exception;
	
	/**
	 * 删除类型
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
}
