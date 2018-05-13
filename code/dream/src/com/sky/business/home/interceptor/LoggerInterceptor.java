package com.sky.business.home.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.oplog.entity.Oplog;
import com.sky.business.oplog.service.OplogService;
import com.sky.contants.EntityContants;
import com.sky.util.IpProcessUtil;

/**
 * 日志拦截器
 * @author Sky James
 *
 */
@Component
public class LoggerInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1777316199384840567L;

	private static final Logger logger = Logger.getLogger(ActionSupport.class);
	
	@Resource
	private OplogService oplogService;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		logger.info("日志拦截器拦截");
		String result = "error";
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String ip = IpProcessUtil.getIpAddr(request);
		
		LoginUser user = (LoginUser) request.getSession().getAttribute("loginUser");
		
		String actionName = invocation.getProxy().getActionName();
		String actionMethod = invocation.getProxy().getMethod();
		
		if(!isSaveLog(actionName, actionMethod)) {
			result = invocation.invoke();
			return result;
		}
		
		try {
			Oplog oplog = null;
			
			if(user != null){
				logger.info("IP: " + ip + ",用户: " + user.getUserId() + "  开始执行  Action: " + actionName + " 中的 " + actionMethod + " 方法");
				
				if(actionName.equals("oplog")){
					result = invocation.invoke();
					return result;
				}
				
				String opDetial = "IP地址:" + ip + "。" + user.getUsername() + 
						"（" + user.getUserId() + "）" + "在" + EntityContants.OplogContants.actionMaps.get(actionName) + "部分， " + 
						EntityContants.OplogContants.methodMaps.get(actionMethod);
				oplog = Oplog.newOpUserInstance(user.getUserId(), EntityContants.OplogContants.actionMaps.get(actionName), opDetial, ip);
			}else{
				logger.info("IP: " + ip + "  开始执行  Action: " + actionName + " 中的 " + actionMethod + " 方法");
				
				String opDetial = "IP地址:" + ip + "。" + "访客在" + EntityContants.OplogContants.actionMaps.get(actionName) + "部分， " + EntityContants.OplogContants.methodMaps.get(actionMethod);
				oplog = Oplog.newOpNullUserInstance(EntityContants.OplogContants.actionMaps.get(actionName), opDetial, ip);
			}
			
			oplogService.save(oplog);
			
			result = invocation.invoke();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		return result;
	}
	
	/**
	 * 判断是否保存到日志
	 * @param actionName
	 * @param actionMethod
	 * @return
	 */
	private boolean isSaveLog(String actionName, String actionMethod) {
		if(!EntityContants.OplogContants.actionMaps.containsKey(actionName)
				|| !EntityContants.OplogContants.methodMaps.containsKey(actionMethod)) {
			return false;
		}
		return true;
	}

}
