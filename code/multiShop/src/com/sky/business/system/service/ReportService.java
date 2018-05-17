package com.sky.business.system.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.system.entity.Report;

/**
 * 举报Service接口
 * @author Sky James
 *
 */
public interface ReportService extends BaseService {
	
	/**
	 * 修改举报信息
	 * @param editObj
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editObj) throws Exception;
	
	/**
	 * 添加举报信息
	 * @param addObj
	 * @return
	 * @throws Exception
	 */
	public Report add(Map<String,Object> addObj) throws Exception;
	
	/**
	 * 删除举报信息
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
}
