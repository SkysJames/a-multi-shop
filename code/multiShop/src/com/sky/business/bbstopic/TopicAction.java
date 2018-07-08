package com.sky.business.bbstopic;


import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.sky.business.bbstopic.dao.BBSTopicDao;
import com.sky.business.bbstopic.entity.Bbstopic;
import com.sky.business.bbstopic.service.BbsTopicService;
import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.ServiceException;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.JsonUtil;

@InterceptorRefs({@InterceptorRef("serverLoginStack")})
public class TopicAction extends BaseAction{
private static final long serialVersionUID = 1L;
	
	@Resource(name = "bbsTopicDao")
	private BBSTopicDao bbsTopicDao;
	
	@Resource(name = "bbsTopicService")
	private BbsTopicService bbsTopicService;
	
	/**
	 * 分页获取主帖列表
	 * @return
	 * @throws Exception
	 */
	public String paged() throws Exception {
		try{
			@SuppressWarnings("unchecked")
			Map<String,Object> condition = JsonUtil.getJsonToMap(conditionJson);

			pager = bbsTopicService.pagedList(bbsTopicDao, Bbstopic.class, condition);
			
			resultMap.put("pager", pager);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "论坛帖子分页数据获取成功");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}
	
	public String getChildrenTopicPaged() throws Exception {
		try{
			@SuppressWarnings("unchecked")
			Map<String,Object> condition = JsonUtil.getJsonToMap(conditionJson);

			pager = bbsTopicService.pagedList(bbsTopicDao, Bbstopic.class, condition);
			
			resultMap.put("pager", pager);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "论坛回帖分页数据获取成功");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}
	/**
	 * 根据帖子ID删除帖子
	 * @return
	 */
	public String delete(){
		try{
			Map<String,Object> message = JsonUtil.getJsonToMap(conditionJson);
			bbsTopicService.delete((String)message.get("id"));
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "帖子删除成功");
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
	 * 编辑帖子
	 * @return
	 */
	public String edit(){
		try{
			Map<String,Object> message = JsonUtil.getJsonToMap(conditionJson);
			bbsTopicService.edit(message);
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "帖子编辑成功");
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
	 * 添加帖子
	 * @return
	 */
	public String save(){
		try{
			Map<String,Object> message = JsonUtil.getJsonToMap(conditionJson);
			bbsTopicService.add(message);
			System.out.println(message);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "新增帖子成功");
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

