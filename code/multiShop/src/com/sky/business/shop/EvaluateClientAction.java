package com.sky.business.shop;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.shop.dao.EvaluateDao;
import com.sky.business.shop.entity.Evaluate;
import com.sky.business.shop.service.EvaluateService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.JsonUtil;

/**
 * 评论action
 * @author Sky James
 *
 */
@InterceptorRefs({@InterceptorRef("clientLoginStack")})
public class EvaluateClientAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "evaluateService")
	private EvaluateService evaluateService;
	
	@Resource(name = "evaluateDao")
	private EvaluateDao evaluateDao;
	
	
	/**
	 * 添加评论
	 * @return
	 */
	public String save(){
		try{
			Map<String,Object> evaluate = JsonUtil.getJsonToMap(conditionJson);
			evaluateService.add(evaluate);
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功添加评论");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 根据评论的ID删除
	 * @return
	 */
	public String delete(){
		try{
			Map<String,Object> evaluate = JsonUtil.getJsonToMap(conditionJson);
			evaluateService.delete((String)evaluate.get("id"));
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功删除评论");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
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
