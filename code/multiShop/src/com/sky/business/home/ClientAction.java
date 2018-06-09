package com.sky.business.home;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;

import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.Pager;
import com.sky.business.shop.dao.ProductDao;
import com.sky.business.shop.entity.Product;
import com.sky.business.shop.service.ProductService;

/**
 * 前端主页
 * @author Sky James
 *
 */
public class ClientAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "productService")
	private ProductService productService;
	
	@Resource(name = "productDao")
	private ProductDao productDao;

	private LoginUser loginUser;
	
	//搜索关键字
	private String keywords;
	//搜索类型ID
	private String type;
	//店铺ID
	private String shopId;
	//是否展示店铺信息
	private boolean shopAbout;
	
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

}

