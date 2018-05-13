package com.sky.business.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.Pager;
import com.sky.business.common.vo.ServiceException;
import com.sky.contants.CodeMescContants;

/**
 * Action基础类
 * @author Sky James
 *
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;
	
	/**
	 * json Action Result Type
	 */
	public static final String JSON = "json";
	public static final String LOGIN = "login";
	public static final String RESULT_MAP = "result_map";
	
	protected static Logger logger = Logger.getRootLogger();
	
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	protected HttpSession session;
	
	protected Pager pager = new Pager();
	
	protected Map<String,Object> resultMap = new HashMap<String,Object>();
	
	//前端传来的条件，json字符串
	protected String conditionJson;
	
	private ActionProxy proxy;
	
	public BaseAction() {
		super();
		proxy = ActionContext.getContext().getActionInvocation().getProxy();
	}
	
	/**
	 * 获取当前Action的代理
	 * @return
	 */
	public ActionProxy getProxy() {
		return proxy;
	}
	
//	/**
//	 * 获取当前Action的命名空间
//	 * @return
//	 */
//	public String getNamespace(){
//		String namespace = proxy.getNamespace();
//		if ("/".equals(namespace)) namespace = "";
//
//		return namespace;
//	}
	
	protected void isNull(Object obj) throws Exception {
		if(obj == null){
			throw new ServiceException(CodeMescContants.CodeContants.NULL_ERROR,CodeMescContants.MessageContants.NULL_ERROR);
		}
	}
	
	protected LoginUser sessionLoginUser() {
		return (LoginUser) session.getAttribute("loginUser");
	}
	
	//Getters and Setters
	
	public void setServletResponse(HttpServletResponse resp) {
		this.resp = resp;
	}
	
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
		this.session = req.getSession();
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getConditionJson() {
		return conditionJson;
	}

	public void setConditionJson(String conditionJson) {
		this.conditionJson = conditionJson;
	}

}
