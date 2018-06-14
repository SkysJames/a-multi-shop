package com.sky.business.message;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.message.dao.MessageDao;
import com.sky.business.message.entity.Message;
import com.sky.business.message.service.MessageService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.JsonUtil;

/**
 * 消息action（前端登录）
 * @author Sky James
 *
 */
@InterceptorRefs({@InterceptorRef("clientLoginStack")})
public class MessageClientAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "messageService")
	private MessageService messageService;
	
	@Resource(name = "messageDao")
	private MessageDao messageDao;
	
	
	/**
	 * 添加消息
	 * @return
	 */
	public String save(){
		try{
			Map<String,Object> message = JsonUtil.getJsonToMap(conditionJson);
			messageService.add(message);
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功添加消息");
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