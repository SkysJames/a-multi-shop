package com.sky.business.shop.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.shop.entity.Cart;

/**
 * 购物车Service接口
 * @author Sky James
 *
 */
public interface CartService extends BaseService {
	
	/**
	 * 修改购物车信息
	 * @param editObj
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editObj) throws Exception;
	
	/**
	 * 添加购物车
	 * @param addObj
	 * @return
	 * @throws Exception
	 */
	public Cart add(Map<String,Object> addObj) throws Exception;
	
	/**
	 * 删除购物车
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
}
