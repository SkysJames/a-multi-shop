package com.sky.business.shop.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.shop.entity.Shop;

/**
 * 店铺Service接口
 * @author Sky James
 *
 */
public interface ShopService extends BaseService {
	
	/**
	 * 修改店铺信息
	 * @param editObj
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editObj) throws Exception;
	
	/**
	 * 添加店铺
	 * @param addObj
	 * @return
	 * @throws Exception
	 */
	public Shop add(Map<String,Object> addObj) throws Exception;
	
	/**
	 * 删除店铺
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
	/**
	 * 增加店铺人气值
	 * @param editObj
	 * @throws Exception
	 */
	public void addPopularity(String id) throws Exception;
	
}
