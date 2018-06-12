package com.sky.business.home;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import com.sky.business.shop.dao.ProductDao;
import com.sky.business.shop.entity.Product;
import com.sky.business.shop.service.ProductService;
import com.sky.business.system.service.UserService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.IpProcessUtil;

/**
 * 前端主页
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
	
	@Resource(name = "productService")
	private ProductService productService;
	
	@Resource(name = "productDao")
	private ProductDao productDao;

	//登录用户
	private LoginUser loginUser;
	
	//搜索关键字
	private String keywords;
	//搜索类型ID
	private String type;
	//店铺ID
	private String shopId;
	//是否展示店铺信息
	private boolean shopAbout;
	//商品ID
	private String productId;
	
	//action
	/**
	 * 登录
	 * @return
	 */
	@Action(value = "client-login")
	public String login() throws Exception {
		try {
			isNull(loginUser);
			loginUser.setUserIp(IpProcessUtil.getIpAddr(req));
			loginUser = userService.checkForLoginClient(loginUser);
			session.setAttribute("loginUser", loginUser);
			
			String opDetail = "IP地址:" + loginUser.getUserIp() + "。" + "用户" + loginUser.getUsername() + "（" + loginUser.getUserId() + "）登录前台系统";
			Oplog log = Oplog.newOpUserInstance(loginUser.getUserId(), EntityContants.OplogContants.actionMaps.get("client-login"), opDetail, loginUser.getUserIp());
			oplogService.save(log);
			logger.info("用户" + loginUser.getUsername() + "（" + loginUser.getUserId() + "）" + "登录前台系统");
			
			resultMap.put("loginUser", loginUser);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功登录平台");
		} catch (ServiceException e) {
			logger.error(e.getMessage());
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
	 * 退出
	 * @return
	 * @throws Exception
	 */
	@Action(value = "client-logout",interceptorRefs = {@InterceptorRef("clientLoginStack")})
	public String logout() throws Exception {
		session.invalidate();
		logger.info("退出系统");
		
		resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
		resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功退出平台");
		return RESULT_MAP;
	}
	
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
	
	/**
	 * 前端店铺首页
	 * @return
	 */
	@Action(value = "shop-index", results = @Result(location = "/sky/client/business/shopIndex/shopIndex.jsp", params = {"shopId","${shopId}","shopAbout","${shopAbout}"}), interceptorRefs = {@InterceptorRef("visitorStack")})
	public String shopIndex() {
		logger.info("进入前端店铺首页");
		shopAbout = false;
		
		if(StringUtils.isBlank(shopId)) {
			return ERROR;
		}
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("shopId", shopId);
		Integer count = productService.getCount(productDao, Product.class, condition);
		if(count==null || count==0) {
			shopAbout = true;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 前端商品详情页面
	 * @return
	 */
	@Action(value = "product-index", results = @Result(location = "/sky/client/business/productIndex/productIndex.jsp", params = {"productId","${productId}"}), interceptorRefs = {@InterceptorRef("visitorStack")})
	public String productIndex() {
		logger.info("进入前端商品详情页面");
		
		if(StringUtils.isBlank(productId)) {
			return ERROR;
		}
		
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

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public boolean isShopAbout() {
		return shopAbout;
	}

	public void setShopAbout(boolean shopAbout) {
		this.shopAbout = shopAbout;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}

