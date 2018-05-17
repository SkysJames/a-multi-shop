package com.sky.business.system.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.system.entity.Announce;

/**
 * 公告Service接口
 * @author Sky James
 *
 */
public interface AnnounceService extends BaseService {
	
	/**
	 * 修改公告
	 * @param editObj
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editObj, LoginUser loginUser) throws Exception;
	
	/**
	 * 添加公告
	 * @param addObj
	 * @return
	 * @throws Exception
	 */
	public Announce add(Map<String,Object> addObj, LoginUser loginUser) throws Exception;
	
	/**
	 * 删除公告
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
}
