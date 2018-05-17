package com.sky.business.shop.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.shop.entity.Cart;
import com.sky.business.shop.service.CartService;
import com.sky.contants.CodeMescContants;
import com.sky.util.CommonMethodUtil;

/**
 * 购物车Service类
 * @author Sky James
 *
 */
@Service("cartService")
public class CartServiceImpl extends BaseServiceImpl implements CartService {

	@Resource(name = "cartService")
	private CartService cartService;
	
	@Override
	public void edit(Map<String,Object> editObj) throws Exception {
		//查找数据库中是否存在
		Cart cart = this.findByID(Cart.class, (String)editObj.get("id"));
		if(cart == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editObj.containsKey("productId")){
			cart.setProductId((String)editObj.get("productId"));
		}
		if(editObj.containsKey("userId")){
			cart.setUserId((String)editObj.get("userId"));
		}
		if(editObj.containsKey("proNum")){
			cart.setProNum(CommonMethodUtil.getIntegerByObject(editObj.get("proNum")));
		}
		if(editObj.containsKey("status")){
			cart.setStatus(CommonMethodUtil.getIntegerByObject(editObj.get("status")));
		}
		
		Timestamp nowstamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
		cart.setUpdateTime(nowstamp);
		
		this.update(cart);
	}
	
	@Override
	public Cart add(Map<String,Object> addObj) throws Exception {
		Cart cart = new Cart();
		cart.setId(UUID.randomUUID().toString());
		
		if(addObj.containsKey("productId")){
			cart.setProductId((String)addObj.get("productId"));
		}
		if(addObj.containsKey("userId")){
			cart.setUserId((String)addObj.get("userId"));
		}
		if(addObj.containsKey("proNum")){
			cart.setProNum(CommonMethodUtil.getIntegerByObject(addObj.get("proNum")));
		}
		if(addObj.containsKey("status")){
			cart.setStatus(CommonMethodUtil.getIntegerByObject(addObj.get("status")));
		}
		
		Timestamp nowstamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
		cart.setCreateTime(nowstamp);
		cart.setUpdateTime(nowstamp);
		
		this.save(cart);
		return cart;
		
	}
	
	@Override
	public void delete(String id) throws Exception {
		//查找数据库中是否存在
		Cart cart = this.findByID(Cart.class, id);
		if(cart == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		this.delete(cart);
	}

}
