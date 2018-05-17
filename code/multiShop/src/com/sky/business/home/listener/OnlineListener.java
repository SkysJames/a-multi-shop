package com.sky.business.home.listener;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.sky.business.common.vo.LoginUser;
import com.sky.business.system.entity.User;
import com.sky.business.system.service.UserService;
import com.sky.business.visitor.entity.Visitor;
import com.sky.contants.UserContants;
import com.sky.util.BeanDefinedLocator;

public class OnlineListener implements HttpSessionListener {

	private static final Logger logger = Logger.getLogger(OnlineListener.class);
	
	private UserService userService;
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		if(userService == null){
			userService = (UserService)BeanDefinedLocator.getInstance().getBean("userService");
		}
		
		HttpSession session = event.getSession();
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		Visitor visitor = (Visitor) session.getAttribute("visitor");
		
		try {
			if(loginUser != null){
				logger.info("Ip地址：" + loginUser.getUserIp() + "。用户：" + loginUser.getUserId() + "退出登录");
				User user = userService.findByID(User.class, loginUser.getUserId());
				if(user != null){
					user.setLogoutTime(new Timestamp(new Date().getTime()));
					user.setLoginStatus(UserContants.LoginStatus.OFFLINE);
					userService.update(user);
				}
			}
			
			if(visitor != null){
				logger.info("Ip地址：" + visitor.getIp() + "。访客的session过期无效");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
