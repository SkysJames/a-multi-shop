package com.sky.business.system.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.system.entity.RightGroup;

/**
 * 角色Service接口
 * @author Sky James
 *
 */
public interface RightGroupService extends BaseService {
	
	/**
	 * 修改角色信息
	 * @param editObj
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editObj) throws Exception;
	
	/**
	 * 添加角色
	 * @param addObj
	 * @return
	 * @throws Exception
	 */
	public RightGroup add(Map<String,Object> addObj) throws Exception;
	
	/**
	 * 删除角色
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
}
