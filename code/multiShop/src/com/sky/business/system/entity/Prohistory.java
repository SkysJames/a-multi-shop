package com.sky.business.system.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sky.business.shop.entity.Product;
import com.sky.business.shop.entity.Shop;
import com.sky.business.shop.service.ProductService;
import com.sky.business.shop.service.ShopService;
import com.sky.util.BeanDefinedLocator;

/**
 * 历史浏览/收藏表
 * @author xiefeiye
 *
 */
@Entity
@Table(name="tb_prohistory")
public class Prohistory implements Serializable {

	// Fields    
	@Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
	private String id;
	
	@Column(name="TYPE")
	private Integer type;
	
	@Column(name="OBJ_ID")
	private String objId;
	
	@Column(name="TABLE_NAME")
	private String tableName;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;
	
	@Column(name="HREF")
	private String href;
	
	//店铺
	@Transient
	private Shop shop;
	
	//商品
	@Transient
	private Product product;
	
    // Property accessors
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Shop getShop() {
		try {
		    	if (null == shop) {
		    		ShopService shopService = (ShopService)BeanDefinedLocator.getInstance().getBean("shopService");
		    		
		    		if(tableName.equals("tb_shop")){
		    			shop = shopService.findByID(Shop.class, objId);
		    		}
			}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
		
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Product getProduct() {
		try {
		    	if (null == product) {
		    		ProductService productService = (ProductService)BeanDefinedLocator.getInstance().getBean("productService");
		    		
		    		if(tableName.equals("tb_product")){
		    			product = productService.findByID(Product.class, objId);
		    		}
			}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
