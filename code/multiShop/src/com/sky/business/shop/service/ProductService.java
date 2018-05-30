package com.sky.business.shop.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.shop.entity.Product;

/**
 * 产品Service接口
 * @author Sky James
 *
 */
public interface ProductService extends BaseService {
	
	/**
	 * 添加产品
	 * @param addObj
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	public Product add(Map<String,Object> addObj, LoginUser loginUser) throws Exception;
	
	/**
	 * 删除产品
	 * @param id
	 * @param loginUser
	 * @throws Exception
	 */
	public void delete(String id, LoginUser loginUser) throws Exception;
	
	/**
	 * 修改产品信息
	 * @param editObj
	 * @param loginUser
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editObj, LoginUser loginUser) throws Exception;
	
	/**
	 * 增加商品浏览量
	 * @param editObj
	 * @throws Exception
	 */
	public void addClickCount(String id) throws Exception;
	
}
