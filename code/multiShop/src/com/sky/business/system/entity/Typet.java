package com.sky.business.system.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.sky.business.shop.entity.Shop;
import com.sky.business.shop.service.ShopService;
import com.sky.business.system.service.TypeService;
import com.sky.contants.TypetContants;
import com.sky.util.BeanDefinedLocator;

/**
 * 类型
 * @author Sky James
 *
 */
@Entity
@Table(name="tb_type")
public class Typet implements Serializable {

	// Fields    
	@Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
	private String id;
	
	@Column(name="SHOP_ID")
	private String shopId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="TABLE_NAME")
	private String tableName;
	
	@Column(name="PARENT_ID")
	private String parentId;
	
	@Column(name="SORT")
	private Integer sort;
	
	//店铺名称
	@Transient
	private String shopName;
	
	//父类型名称
	@Transient
	private String parentName;
	
	//子类型列表
	@Transient
	private List<Typet> typetList;
	
	
    // Property accessors
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getShopName() {
		if(StringUtils.isNotBlank(this.shopId)) {
			ShopService shopService = (ShopService)BeanDefinedLocator.getInstance().getBean("shopService");
			
			Shop shop = shopService.findByID(Shop.class, this.shopId);
			shopName = shop.getName();
		}
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getParentName() {
		if(!TypetContants.ROOT_PARENT_ID.equals(this.parentId)) {
			TypeService typeService = (TypeService)BeanDefinedLocator.getInstance().getBean("typeService");
			
			Typet parentTypet = typeService.findByID(Typet.class, this.parentId);
			parentName = parentTypet.getName();
		}
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<Typet> getTypetList() {
		if(TypetContants.ROOT_PARENT_ID.equals(this.parentId)) {
			TypeService typeService = (TypeService)BeanDefinedLocator.getInstance().getBean("typeService");
			
			typetList = typeService.findBy(Typet.class, "parentId", this.id, "sort", true);
		}
		return typetList;
	}

	public void setTypetList(List<Typet> typetList) {
		this.typetList = typetList;
	}

}
