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
 * 前端主页
 * @author Sky James
 *
 */
public class ClientAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private LoginUser loginUser;
	
	//搜索关键字
	private String keywords;
	//搜索类型ID
	private String type;
	
	//action
	/**
	 * 前端店铺搜索页面
	 * @return
	 */
	@Action(value = "shop-search", results = @Result(location = "/sky/client/business/shopSearch/shopSearch.jsp", params = {"keywords","${keywords}","type","${type}"}), interceptorRefs = {@InterceptorRef("visitorStack")})
	public String shopSearch() {
		logger.info("进入前端店铺搜索页面");
		return SUCCESS;
	}
	
	/**
	 * 前端商品搜索页面
	 * @return
	 */
	@Action(value = "product-search", results = @Result(location = "/sky/client/business/productSearch/productSearch.jsp", params = {"keywords","${keywords}"}), interceptorRefs = {@InterceptorRef("visitorStack")})
	public String productSearch() {
		logger.info("进入前端商品搜索页面");
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

