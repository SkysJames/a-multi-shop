package com.sky.business.system.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.common.vo.LoginUser;

/**
 * 收藏夹/历史记录Service接口
 * @author Sky James
 *
 */
public interface ProhistoryService extends BaseService {
	
	/**
	 * 保存或编辑 收藏夹/历史记录
	 * @param obj
	 * @throws Exception
	 */
	public void saveOrUpdate(Map<String,Object> obj) throws Exception;
	
	/**
	 * 删除收藏夹/历史记录
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
	/**
	 * 根据用户保存历史记录
	 * @param loginUser
	 * @param tableName
	 * @param objId
	 * @throws Exception
	 */
	public void saveHistoryByUser(LoginUser loginUser, String tableName, String objId) throws Exception;
	
}
