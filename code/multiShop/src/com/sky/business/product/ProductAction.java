package com.sky.business.product;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.product.entity.Product;
import com.sky.business.product.service.ProductService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.JsonUtil;

/**
 * 产品管理
 * @author Sky James
 *
 */
@InterceptorRefs({@InterceptorRef("serverStack"),@InterceptorRef("baseStack")})
public class ProductAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "productService")
	private ProductService productService;
	
	/**
	 * 产品列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		try{
			Map<String,Object> condition = JsonUtil.getJsonToMap(conditionJson);
			pager = productService.pagedList(condition);
			logger.info("查询产品列表");
			
			resultMap.put("pager", pager);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取产品列表");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.PRODUCT_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.PRODUCT_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 编辑产品
	 * @return
	 */
	public String edit(){
		try{
			LoginUser loginUser = super.sessionLoginUser();
			Map<String,Object> product = JsonUtil.getJsonToMap(conditionJson);
			productService.edit(product, loginUser);
			logger.info("编辑产品");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功修改产品");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.PRODUCT_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.PRODUCT_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 添加保存产品
	 * @return
	 */
	public String save(){
		try{
			LoginUser loginUser = super.sessionLoginUser();
			Map<String,Object> product = JsonUtil.getJsonToMap(conditionJson);
			Product savedProduct = productService.add(product, loginUser);
			logger.info("添加保存产品");
			
			resultMap.put("savedProduct", savedProduct);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功添加保存产品");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.PRODUCT_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.PRODUCT_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 根据产品的ID删除产品
	 * @return
	 */
	public String delete(){
		try{
			LoginUser loginUser = super.sessionLoginUser();
			Map<String,Object> product = JsonUtil.getJsonToMap(conditionJson);
			productService.delete((String)product.get("id"), loginUser);
			logger.info("删除产品");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功删除产品");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.PRODUCT_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.PRODUCT_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 获取产品总数量
	 * @return
	 */
	public String getTotalcount(){
		try{
			Integer productCount = productService.countAll(Product.class);
			logger.info("获取产品总数量");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取产品总数量");
			resultMap.put("productCount", productCount);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.PRODUCT_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.PRODUCT_ERROR);
		}
		return RESULT_MAP;
	}

	//Getters and Setters
	
	@Override
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	
	@Override
	public String getConditionJson() {
		return conditionJson;
	}
	
	@Override
	public void setConditionJson(String conditionJson) {
		this.conditionJson = conditionJson;
	}

}
