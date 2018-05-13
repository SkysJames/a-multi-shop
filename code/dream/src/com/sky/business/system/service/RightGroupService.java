package com.sky.business.system.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.common.vo.Pager;
import com.sky.business.system.entity.RightGroup;

/**
 * 角色Service接口
 * @author Sky James
 *
 */
public interface RightGroupService extends BaseService {
	
	/**
	 * 根据一定的条件获取角色列表
	 * @param condition
	 * @return
	 */
	public Pager pagedList(Map<String, Object> condition) throws Exception;
	
	/**
	 * 修改角色信息
	 * @param editRightGroup
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editRightGroup) throws Exception;
	
	/**
	 * 添加角色
	 * @param rightGroup
	 * @return
	 * @throws Exception
	 */
	public RightGroup add(Map<String,Object> rightGroup) throws Exception;
	
	/**
	 * 删除角色
	 * @param rightGroupId
	 * @throws Exception
	 */
	public void delete(String rightGroupId) throws Exception;
	
}
