package com.sky.business.message.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.message.entity.Message;

/**
 * 消息Service接口
 * @author Sky James
 *
 */
public interface MessageService extends BaseService {
	
	/**
	 * 修改消息
	 * @param editObj
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editObj) throws Exception;
	
	/**
	 * 添加消息
	 * @param addObj
	 * @return
	 * @throws Exception
	 */
	public Message add(Map<String,Object> addObj) throws Exception;
	
	/**
	 * 删除消息
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
}
