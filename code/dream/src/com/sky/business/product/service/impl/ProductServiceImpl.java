package com.sky.business.product.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.Pager;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.product.dao.ProductDao;
import com.sky.business.product.entity.Product;
import com.sky.business.product.service.ProductService;
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
	public Pager pagedList(Map<String, Object> condition) throws Exception {
		Integer pageNo = Pager.DEFAULT_CURRENT_PAGE;
		Integer pageSize = Pager.DEFAULT_PAGE_SIZE;
		
		if(condition.containsKey("pageNo") && condition.containsKey("pageSize")) {
			pageNo = CommonMethodUtil.getIntegerByObject(condition.get("pageNo"));
			pageSize = CommonMethodUtil.getIntegerByObject(condition.get("pageSize"));
		}
		
		return productDao.pagedList(condition, pageNo, pageSize);
	}

	@Override
	public Product add(Map<String,Object> product, LoginUser loginUser) throws Exception {
		//查找数据库中是否名字相同的产品
		Product hasProduct = this.findByUnique(Product.class, "name", (String)product.get("name"));
		if(hasProduct != null){
			throw new ServiceException(CodeMescContants.CodeContants.PRODUCT_EXIST, CodeMescContants.MessageContants.PRODUCT_EXIST);
		}
		
		//当前时间
		Date now = Calendar.getInstance().getTime();
		//图片存放的目录
		String picPath = FileContants.PRODUCT_FILE + File.separator + (String)product.get("name");
		//保存图片
		String picture = CommonMethodUtil.saveFiles((List<String>) product.get("picPathList"), picPath);
		
		Product newProduct = new Product();
		newProduct.setId(UUID.randomUUID().toString());
		newProduct.setCreateTime(new Timestamp(now.getTime()));
		newProduct.setUpdateTime(new Timestamp(now.getTime()));
		newProduct.setCreateUser(loginUser.getUserId());
		newProduct.setUpdateUser(loginUser.getUserId());
		newProduct.setProType(CommonMethodUtil.getIntegerByObject(product.get("proType")));
		newProduct.setName((String) product.get("name"));
		newProduct.setDescription((String) product.get("description"));
		newProduct.setPicture(picture);
		
		this.save(newProduct);
		return newProduct;
	}
	
	@Override
	public void delete(String productId, LoginUser loginUser) throws Exception {
		//查找数据库中是否存在该产品
		Product product = this.findByID(Product.class, productId);
		if(product == null){
			throw new ServiceException(CodeMescContants.CodeContants.PRODUCT_NULL, CodeMescContants.MessageContants.PRODUCT_NULL);
		}
		
		this.delete(product);
		
	}

	@Override
	public void edit(Map<String,Object> editProduct, LoginUser loginUser) throws Exception {
		//查找数据库中是否存在该新闻
		Product product = this.findByID(Product.class, (String)editProduct.get("id"));
		if(product == null){
			throw new ServiceException(CodeMescContants.CodeContants.PRODUCT_NULL, CodeMescContants.MessageContants.PRODUCT_NULL);
		}
		
		//当前时间
		Date now = Calendar.getInstance().getTime();
		//图片存放的目录
		String picPath = FileContants.PRODUCT_FILE + File.separator + product.getName();
		//保存图片
		String picture = CommonMethodUtil.saveFiles((List<String>) editProduct.get("picPathList"), picPath);
		
		if(editProduct.containsKey("description")){
			product.setDescription((String)editProduct.get("description"));
		}
		if(editProduct.containsKey("name")){
			product.setName((String)editProduct.get("name"));
		}
		if(editProduct.containsKey("proType")){
			product.setProType(CommonMethodUtil.getIntegerByObject(editProduct.get("proType")));
		}
		
		product.setUpdateTime(new Timestamp(now.getTime()));
		product.setUpdateUser(loginUser.getUserId());
		product.setPicture(picture);
		
		this.update(product);
	}
	
}
