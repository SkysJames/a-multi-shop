package com.sky.business.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.entity.Department;
import com.sky.business.system.entity.RightGroup;
import com.sky.business.system.service.RightGroupService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.JsonUtil;

/**
 * 角色管理
 * @author Sky James
 *
 */
@InterceptorRefs({@InterceptorRef("serverStack"),@InterceptorRef("baseStack")})
public class RightGroupAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "rightGroupService")
	private RightGroupService rightGroupService;
	
	/**
	 * 角色列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		try{
			Map<String,Object> condition = JsonUtil.getJsonToMap(conditionJson);
			pager = rightGroupService.pagedList(condition);
			logger.info("查询角色列表");
			
			resultMap.put("pager", pager);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取角色列表");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.RIGHTGROUP_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.RIGHTGROUP_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 编辑角色
	 * @return
	 */
	public String edit(){
		try{
			Map<String,Object> rightGroup = JsonUtil.getJsonToMap(conditionJson);
			rightGroupService.edit(rightGroup);
			logger.info("编辑角色");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功修改角色");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.RIGHTGROUP_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.RIGHTGROUP_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 添加保存角色
	 * @return
	 */
	public String save(){
		try{
			Map<String,Object> rightGroup = JsonUtil.getJsonToMap(conditionJson);
			rightGroupService.add(rightGroup);
			logger.info("添加保存角色");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功添加保存角色");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.RIGHTGROUP_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.RIGHTGROUP_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 根据角色的ID删除角色
	 * @return
	 */
	public String delete(){
		try{
			Map<String,Object> rightGroup = JsonUtil.getJsonToMap(conditionJson);
			rightGroupService.delete((String)rightGroup.get("id"));
			logger.info("删除角色");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功删除角色");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.RIGHTGROUP_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.RIGHTGROUP_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 获取角色总数量
	 * @return
	 */
	public String getTotalcount(){
		try{
			Integer rightGroupCount = rightGroupService.countAll(RightGroup.class);
			logger.info("获取角色总数量");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取角色总数量");
			resultMap.put("rightGroupCount", rightGroupCount);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.RIGHTGROUP_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.RIGHTGROUP_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 获取角色总列表
	 * @return
	 */
	public String getAllList(){
		try{
			List<RightGroup> rightGroupAll = rightGroupService.findAll(RightGroup.class, "name", true);
			logger.info("获取角色总列表");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取角色总列表");
			resultMap.put("rightGroupAll", rightGroupAll);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.RIGHTGROUP_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.RIGHTGROUP_ERROR);
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
