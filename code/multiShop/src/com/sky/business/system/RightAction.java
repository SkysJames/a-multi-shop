package com.sky.business.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.dao.RightDao;
import com.sky.business.system.entity.Right;
import com.sky.business.system.service.RightService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.JsonUtil;

/**
 * 权限管理
 * @author Sky James
 *
 */
@InterceptorRefs({@InterceptorRef("serverStack"),@InterceptorRef("baseStack")})
public class RightAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "rightService")
	private RightService rightService;
	
	@Resource(name = "rightDao")
	private RightDao rightDao;
	
	/**
	 * 权限列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String paged() throws Exception {
		try{
			Map<String,Object> condition = JsonUtil.getJsonToMap(conditionJson);
			pager = rightService.pagedList(rightDao, Right.class, condition);
			
			resultMap.put("pager", pager);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取权限列表");
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
	 * 获取所有权限列表
	 * @return
	 */
	public String getAllRightList(){
		try{
			List<Right> allRightList = rightService.findAll(Right.class, "id", true);
			
			resultMap.put("allRightList", allRightList);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取所有权限列表");
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
