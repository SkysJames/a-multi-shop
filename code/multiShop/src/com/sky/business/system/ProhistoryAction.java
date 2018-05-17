package com.sky.business.system;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.dao.ProhistoryDao;
import com.sky.business.system.entity.Prohistory;
import com.sky.business.system.service.ProhistoryService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.JsonUtil;

/**
 * 收藏夹/历史记录action
 * @author Sky James
 *
 */
@InterceptorRefs({@InterceptorRef("serverStack"),@InterceptorRef("baseStack")})
public class ProhistoryAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "prohistoryService")
	private ProhistoryService prohistoryService;
	
	@Resource(name = "prohistoryDao")
	private ProhistoryDao prohistoryDao;
	
	/**
	 * 分页获取收藏夹/历史记录列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String paged() throws Exception {
		try{
			Map<String,Object> condition = JsonUtil.getJsonToMap(conditionJson);
			pager = prohistoryService.pagedList(prohistoryDao, Prohistory.class, condition);
			
			resultMap.put("pager", pager);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取收藏夹/历史记录列表");
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
	 * 根据收藏夹/历史记录的ID删除
	 * @return
	 */
	public String delete(){
		try{
			Map<String,Object> prohistory = JsonUtil.getJsonToMap(conditionJson);
			prohistoryService.delete((String)prohistory.get("id"));
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功删除收藏夹/历史记录");
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