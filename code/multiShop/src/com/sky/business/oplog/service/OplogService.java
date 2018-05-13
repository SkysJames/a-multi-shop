package com.sky.business.oplog.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.common.vo.Pager;

/**
 * 操作日志Service接口
 * @author Sky James
 *
 */
public interface OplogService extends BaseService {

	/**
	 * 根据一定的条件获取日志列表
	 * @param condition
	 * @return
	 */
	public Pager pagedList(Map<String, Object> condition) throws Exception;
	
}
