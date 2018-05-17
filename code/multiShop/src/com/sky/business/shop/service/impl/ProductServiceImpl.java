package com.sky.business.shop.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.shop.dao.ProductDao;
import com.sky.business.shop.entity.Product;
import com.sky.business.shop.service.ProductService;
import com.sky.business.system.service.UserService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.FileContants;
import com.sky.util.CommonMethodUtil;

/**
 * 产品Service类
 * @author Sky James
 *
 */
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	@Resource
	private UserService userService;
	
	@Resource(name = "productDao")
	private ProductDao productDao;
	
	@Override
	public Product add(Map<String,Object> addObj, LoginUser loginUser) throws Exception {
		Product product = new Product();
		product.setId(UUID.randomUUID().toString());
		
		if(addObj.containsKey("shopId")){
			product.setShopId((String)addObj.get("shopId"));
		}
		if(addObj.containsKey("name")){
			product.setName((String)addObj.get("name"));
		}
		if(addObj.containsKey("proType")){
			product.setProType((String)addObj.get("proType"));
		}
		if(addObj.containsKey("description")){
			product.setDescription((String)addObj.get("description"));
		}
		if(addObj.containsKey("price")){
			product.setPrice(CommonMethodUtil.getBigDecimalByObject(addObj.get("price")));
		}
		if(addObj.containsKey("proStock")){
			product.setProStock(CommonMethodUtil.getIntegerByObject(addObj.get("proStock")));
		}
		if(addObj.containsKey("status")){
			product.setStatus(CommonMethodUtil.getIntegerByObject(addObj.get("status")));
		}
		if(addObj.containsKey("picPathList")){
			//图片存放的目录
			String picPath = FileContants.PRODUCT_FILE + File.separator + (String)addObj.get("name");
			//保存图片
			String picture = CommonMethodUtil.saveFiles((List<String>) addObj.get("picPathList"), picPath);
			
			product.setPicture(picture);
		}
		
		//当前时间
		Timestamp nowstamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		product.setCreateTime(nowstamp);
		product.setUpdateTime(nowstamp);
		product.setCreateUser(loginUser.getUserId());
		product.setUpdateUser(loginUser.getUserId());
		
		this.save(product);
		return product;
	}
	
	@Override
	public void delete(String id, LoginUser loginUser) throws Exception {
		//查找数据库中是否存在该产品
		Product product = this.findByID(Product.class, id);
		if(product == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		this.delete(product);
	}

	@Override
	public void edit(Map<String,Object> editObj, LoginUser loginUser) throws Exception {
		//查找数据库中是否存在
		Product product = this.findByID(Product.class, (String)editObj.get("id"));
		if(product == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editObj.containsKey("shopId")){
			product.setShopId((String)editObj.get("shopId"));
		}
		if(editObj.containsKey("name")){
			product.setName((String)editObj.get("name"));
		}
		if(editObj.containsKey("proType")){
			product.setProType((String)editObj.get("proType"));
		}
		if(editObj.containsKey("description")){
			product.setDescription((String)editObj.get("description"));
		}
		if(editObj.containsKey("price")){
			product.setPrice(CommonMethodUtil.getBigDecimalByObject(editObj.get("price")));
		}
		if(editObj.containsKey("proStock")){
			product.setProStock(CommonMethodUtil.getIntegerByObject(editObj.get("proStock")));
		}
		if(editObj.containsKey("status")){
			product.setStatus(CommonMethodUtil.getIntegerByObject(editObj.get("status")));
		}
		if(editObj.containsKey("picPathList")) {
			//图片存放的目录
			String picPath = FileContants.PRODUCT_FILE + File.separator + (String)editObj.get("name");
			//保存图片
			String picture = CommonMethodUtil.saveFiles((List<String>) editObj.get("picPathList"), picPath);
			
			product.setPicture(picture);
		}
		
		//当前时间
		Timestamp nowstamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		product.setCreateTime(nowstamp);
		product.setUpdateTime(nowstamp);
		product.setCreateUser(loginUser.getUserId());
		product.setUpdateUser(loginUser.getUserId());
		
		this.update(product);
	}
	
	@Override
	public void editClickCount(Map<String,Object> editObj) throws Exception {
		//查找数据库中是否存在
		Product product = this.findByID(Product.class, (String)editObj.get("id"));
		if(product == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editObj.containsKey("clickCount")){
			product.setClickCount(CommonMethodUtil.getIntegerByObject(editObj.get("clickCount")));
		}
		
		this.update(product);
	}
	
}
