package com.sky.business.system;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.service.SystemService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.JsonUtil;

/**
 * 系统管理
 * @author Sky James
 *
 */
@InterceptorRefs({@InterceptorRef("serverLoginStack")})
public class SystemAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "systemService")
	private SystemService systemService;
	
	/**
	 * 获取系统信息
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getSystemInfo() throws Exception {
		try{
			Map<String, Object> systemInfo = systemService.getSystemInfo();
			
			resultMap.put("systemInfo", systemInfo);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取系统信息");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 保存系统信息
	 * @return
	 */
	public String saveSystemInfo(){
		try{
			Map<String,Object> systemInfo = JsonUtil.getJsonToMap(conditionJson);
			systemService.saveSystemInfo(systemInfo);
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功保存系统信息");
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