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
 * 评论action（访客访问）
 * @author Sky James
 *
 */
@InterceptorRefs({@InterceptorRef("visitorStack")})
public class EvaluateVisitAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "evaluateService")
	private EvaluateService evaluateService;
	
	@Resource(name = "evaluateDao")
	private EvaluateDao evaluateDao;
	
	
	/**
	 * 获取评论数量
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String count() throws Exception {
		try{
			Map<String,Object> condition = JsonUtil.getJsonToMap(conditionJson);
			Integer count = evaluateService.getCount(evaluateDao, Evaluate.class, condition);
			
			resultMap.put("count", count);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取评论数量");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 分页获取评论列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String paged() throws Exception {
		try{
			Map<String,Object> condition = JsonUtil.getJsonToMap(conditionJson);
			pager = evaluateService.pagedList(evaluateDao, Evaluate.class, condition);
			
			resultMap.put("pager", pager);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取评论列表");
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
