package com.sky.business.shop;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.shop.dao.ShopDao;
import com.sky.business.shop.service.ShopService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.JsonUtil;

/**
 * 店铺active（前端登录）
 * @author Sky James
 *
 */
@InterceptorRefs({@InterceptorRef("clientLoginStack")})
public class ShopClientAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "shopService")
	private ShopService shopService;
	
	@Resource(name = "shopDao")
	private ShopDao shopDao;
	
	private String shopId;
	
	
	/**
	 * 店铺申请
	 * @return
	 */
	public String register(){
		try{
			LoginUser loginUser = super.sessionLoginUser();
			Map<String,Object> shop = JsonUtil.getJsonToMap(conditionJson);
			loginUser = shopService.register(shop, loginUser);
			session.setAttribute("loginUser", loginUser);//更新session的loginUser值
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功提交店铺申请");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
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

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

}
