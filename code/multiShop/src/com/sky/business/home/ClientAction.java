package com.sky.business.home;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;

import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.Pager;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.oplog.entity.Oplog;
import com.sky.business.oplog.service.OplogService;
import com.sky.business.system.service.UserService;
import com.sky.business.visitor.service.VisitorService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.IpProcessUtil;

/**
 * 系统主页
 * @author Sky James
 *
 */
@InterceptorRefs({@InterceptorRef("defaultStack")})
public class ClientAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "oplogService")
	private OplogService oplogService;
	
	@Resource(name = "visitorService")
	private VisitorService visitorService;
	
	private LoginUser loginUser;
	
	private Integer type;
	
	private String code;
	
	//action
	/**
	 * 前端主页面
	 * @return
	 */
	@Action(value = "client-index", results = @Result(location = "/sky/client/business/core/index.jsp"), interceptorRefs = {@InterceptorRef("visitorStack")})
	public String clientIndex() {
		logger.info("进入前端主页面");
		return SUCCESS;
	}

	//Getters and Setters
	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}
	
	@Override
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	
	@Override
	public Pager getPager() {
		return pager;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

