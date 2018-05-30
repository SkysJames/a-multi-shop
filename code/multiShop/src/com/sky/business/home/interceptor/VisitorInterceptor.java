package com.sky.business.home.interceptor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sky.business.common.service.BaseService;
import com.sky.business.shop.entity.Shop;
import com.sky.business.visitor.entity.Visitor;
import com.sky.contants.ShopContants;
import com.sky.contants.VisitorContants;
import com.sky.util.IpProcessUtil;

/**
 * 访问拦截器
 * 对访问前台的Action进行拦截，查看是否存在该ip的session，若不存在则新建session存该ip
 * @author Sky James
 *
 */
@Component
public class VisitorInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 677089508318175138L;
	
	private static final Logger logger = Logger.getLogger(VisitorInterceptor.class);
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		logger.info("访客拦截器拦截");
		String result = "error";
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String ipAdress = IpProcessUtil.getIpAddr(request);
		
		//获取参数，判断是否访问某一店铺
		Map<String, Object> paramMap = invocation.getInvocationContext().getParameters();
		if(paramMap.containsKey("shopId")) {
			String[] shopIds = (String[])paramMap.get("shopId");
			String shopId = shopIds[0];
			
			//保存店铺类型的访客
			if(!this.saveVisitorByShop(shopId, session, ipAdress)) {
				return result;
			}
		}
		
		//保存系统类型的访客
		if(!this.saveVisitorByShop(ShopContants.SHOP_SYSTEM, session, ipAdress)) {
			return result;
		}
		
		result = invocation.invoke();
		
		return result;
	}
	
	/**
	 * 根据系统类型/不同店铺 保存访客
	 * @param session
	 * @param ipAdress
	 */
	private boolean saveVisitorByShop(String shopId, HttpSession session, String ipAdress) {
		//判断传入的shopId是否为空字符串
		if(StringUtils.isBlank(shopId)) {
			return false;
		}
		
		//判断是否存在该店铺
		Shop shop = baseService.findByID(Shop.class, shopId);
		if(shop == null) {
			return false;
		}
		
		//获取相应ip和店铺的访客
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("ip", ipAdress);
		condition.put("shopId", shopId);
		Visitor visitor = baseService.getUnique(Visitor.class, condition);
		
		try{
			//判断数据库中是否已存在该访客
			if(visitor != null){
				//判断该访客的状态是否可用
				if(visitor.getStatus() != VisitorContants.Status.USING){
					return false;
				}
				
				Visitor visitorSession = (Visitor)session.getAttribute(shopId + "_visitor");
				//判断是否已存在该符合ip地址的访客的session
				if(visitorSession==null || !visitorSession.getIp().equals(ipAdress)){
					visitor.setVisitedTime(new Timestamp(new Date().getTime()));
					visitor.setVisitedTimes(visitor.getVisitedTimes() + 1);
					
					baseService.update(visitor);
				}
			}else{
				visitor = new Visitor();
				visitor.setId(UUID.randomUUID().toString());
				visitor.setIp(ipAdress);
				visitor.setShopId(shopId);
				visitor.setStatus(VisitorContants.Status.USING);
				visitor.setCreateTime(new Timestamp(new Date().getTime()));
				visitor.setVisitedTime(new Timestamp(new Date().getTime()));
				visitor.setVisitedTimes(1);
				
				baseService.save(visitor);
			}
			session.setAttribute(shopId + "_visitor", visitor);
		}catch(Exception e){
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		return true;
	}
	
}
