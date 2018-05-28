package com.sky.business.shop.entity;
// default package

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.sky.business.shop.service.ShopService;
import com.sky.business.system.entity.Typet;
import com.sky.business.system.service.TypeService;
import com.sky.util.BeanDefinedLocator;


/**
 * 商品
 * @author xiefeiye
 *
 */
@Entity
@Table(name="tb_product")
public class Product  implements java.io.Serializable {


    // Fields
	
	@Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
    private String id;
	
	@Column(name="NAME", length=100)
    private String name;
	
	@Column(name="SHOP_ID")
    private String shopId;
	
	@Column(name="CLICK_COUNT")
	private Integer clickCount;
	
	@Column(name="BRIEF")
	private String brief;
	
	@Column(name="PRO_TYPE")
	private String proType;
	
	@Column(name="DESCRIPTION", length=4000)
    private String description;
	
	@Column(name="PICTURE", length=4000)
    private String picture;
	
	@Column(name="PRICE")
	private BigDecimal price;
	
	@Column(name="PRO_STOCK")
	private Integer proStock;
	
	@Column(name="CREATE_TIME", length=19)
    private Timestamp createTime;
	
	@Column(name="CREATE_USER", length=36)
    private String createUser;
	
	@Column(name="UPDATE_TIME", length=19)
    private Timestamp updateTime;
	
	@Column(name="UPDATE_USER", length=36)
    private String updateUser;
	
	@Column(name="STATUS")
    private Integer status;

    /************** 非持久化字段 start **************/
	/**
 	 * 店铺名称
 	 */
 	@Transient
 	private String shopName;
 	
 	/**
	 * 商品类型名称
	 */
	@Transient
	private String proTypeName;
	
 	/**
 	 * 图片path列表
 	 */
 	@Transient
 	private List<String> picPathList;
 	/************** 非持久化字段 end  **************/
     

    // Constructors

    /** default constructor */
    public Product() {
    }

   
    // Property accessors
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getPicture() {
        return this.picture;
    }
    
    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public Timestamp getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getUpdateUser() {
        return this.updateUser;
    }
    
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getProStock() {
		return proStock;
	}

	public void setProStock(Integer proStock) {
		this.proStock = proStock;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<String> getPicPathList() {
		if(StringUtils.isNotBlank(picture) && null==picPathList){
			String[] pictures = picture.split(",");
			picPathList = Arrays.asList(pictures);
		}
		return picPathList;
	}

	public void setPicPathList(List<String> picPathList) {
		this.picPathList = picPathList;
	}

	public String getShopName() {
		if(StringUtils.isNotBlank(this.shopId)) {
			ShopService shopService = (ShopService)BeanDefinedLocator.getInstance().getBean("shopService");
			Shop s = shopService.findByID(Shop.class, this.shopId);
			shopName = s.getName();
		}
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getProTypeName() {
		if(StringUtils.isNotBlank(this.proType)) {
			TypeService typeService = (TypeService)BeanDefinedLocator.getInstance().getBean("typeService");
			Typet typet = typeService.findByID(Typet.class, this.proType);
			proTypeName = typet.getName();
		}
		return proTypeName;
	}

	public void setProTypeName(String proTypeName) {
		this.proTypeName = proTypeName;
	}
   
}