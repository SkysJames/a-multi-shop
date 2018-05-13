package com.sky.business.product.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.Pager;
import com.sky.business.product.entity.Product;

/**
 * 产品Service接口
 * @author Sky James
 *
 */
public interface ProductService extends BaseService {
	
	/**
	 * 根据一定的条件获取产品列表
	 * @param condition
	 * @return
	 */
	public Pager pagedList(Map<String, Object> condition) throws Exception;
	
	/**
	 * 添加产品
	 * @param product
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	public Product add(Map<String,Object> product, LoginUser loginUser) throws Exception;
	
	/**
	 * 删除产品
	 * @param productId
	 * @param loginUser
	 * @throws Exception
	 */
	public void delete(String productId, LoginUser loginUser) throws Exception;
	
	/**
	 * 修改产品信息
	 * @param editProduct
	 * @param loginUser
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editProduct, LoginUser loginUser) throws Exception;
	
}
