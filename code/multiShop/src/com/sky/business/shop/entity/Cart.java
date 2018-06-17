package com.sky.business.shop.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.sky.business.shop.service.ProductService;
import com.sky.util.BeanDefinedLocator;

/**
 * 购物车
 * @author xiefeiye
 *
 */
@Entity
@Table(name="tb_cart")
public class Cart implements Serializable {

	// Fields    
	@Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
	private String id;
	
	@Column(name="PRODUCT_ID")
	private String productId;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="PRO_NUM")
	private Integer proNum;
	
	@Column(name="CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;
	
	@Column(name="STATUS")
	private Integer status;
	
	/**
 	 * 商品
 	 */
 	@Transient
 	private Product product; 
	
    // Property accessors
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getProNum() {
		return proNum;
	}

	public void setProNum(Integer proNum) {
		this.proNum = proNum;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Product getProduct() {
		if(StringUtils.isNotBlank(this.productId)) {
			ProductService productService = (ProductService)BeanDefinedLocator.getInstance().getBean("productService");
			product = productService.findByID(Product.class, this.productId);
		}
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
