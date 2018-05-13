package com.sky.business.system;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.entity.User;
import com.sky.business.system.service.UserService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.JsonUtil;

/**
 * 用户管理
 * @author Sky James
 *
 */
@InterceptorRefs({@InterceptorRef("serverStack"),@InterceptorRef("baseStack")})
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "userService")
	private UserService userService;
	
	public String userId;
	
	public String oldPasswd;
	
	public String newPasswd;
	
	/**
	 * 用户列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		try{
			Map<String,Object> condition = JsonUtil.getJsonToMap(conditionJson);
			pager = userService.pagedList(condition);
			logger.info("查询用户列表");
			
			resultMap.put("pager", pager);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取用户列表");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.USER_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.USER_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 编辑用户
	 * @return
	 */
	public String edit(){
		try{
			LoginUser loginUser = super.sessionLoginUser();
			Map<String,Object> user = JsonUtil.getJsonToMap(conditionJson);
			userService.edit(user, loginUser);
			logger.info("编辑用户");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功修改用户");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.USER_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.USER_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 添加保存用户
	 * @return
	 */
	public String save(){
		try{
			Map<String,Object> user = JsonUtil.getJsonToMap(conditionJson);
			userService.add(user);
			logger.info("添加保存用户");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功添加保存用户");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.USER_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.USER_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 根据用户的ID删除用户
	 * @return
	 */
	public String delete(){
		try{
			LoginUser loginUser = super.sessionLoginUser();
			User user = JsonUtil.getJsonToJavaBean(conditionJson, User.class);
			userService.delete(user.getId(), loginUser);
			logger.info("删除用户");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功删除用户");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.USER_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.USER_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 获取用户总数量
	 * @return
	 */
	public String getTotalcount(){
		try{
			Integer userCount = userService.countAll(User.class);
			logger.info("获取用户总数量");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取用户总数量");
			resultMap.put("userCount", userCount);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.USER_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.USER_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 根据用户ID获取用户信息
	 * @return
	 * @throws Exception
	 */
	public String getUserById() throws Exception {
		try{
			User user = JsonUtil.getJsonToJavaBean(conditionJson, User.class);
			user = userService.findByID(User.class, userId);
			logger.info("根据ID获取用户信息");
			
			resultMap.put("user", user);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取用户信息");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.USER_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.USER_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 修改个人用户信息
	 * @return
	 * @throws Exception
	 */
	public String editPerson() throws Exception {
		try{
			LoginUser loginUser = sessionLoginUser();
			Map<String,Object> user = JsonUtil.getJsonToMap(conditionJson);
			loginUser = userService.editPerson(user, loginUser);
			session.setAttribute("loginUser", loginUser);//更新session的loginUser值
			logger.info("修改用户的个人信息");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功修改个人用户信息");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.USER_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.USER_ERROR);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 修改个人用户的密码
	 * @return
	 * @throws Exception
	 */
	public String editPersonPawd() throws Exception {
		try{
			userService.editPersonPawd(userId, oldPasswd, newPasswd);
//			session.invalidate();//清空session，重新登录
			logger.info("修改个人用户的密码");
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功修改密码");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.USER_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.USER_ERROR);
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOldPasswd() {
		return oldPasswd;
	}

	public void setOldPasswd(String oldPasswd) {
		this.oldPasswd = oldPasswd;
	}

	public String getNewPasswd() {
		return newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

}