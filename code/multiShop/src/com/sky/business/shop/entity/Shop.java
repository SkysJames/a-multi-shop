package com.sky.business.shop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.sky.business.system.entity.Typet;
import com.sky.business.system.entity.User;
import com.sky.business.system.service.TypeService;
import com.sky.business.system.service.UserService;
import com.sky.contants.RightGroupContants;
import com.sky.util.BeanDefinedLocator;
import com.sky.util.DateUtil;

/**
 * 店铺
 * @author Sky James
 *
 */
@Entity
@Table(name="tb_shop")
public class Shop implements Serializable {

	// Fields    
	@Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
	private String id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="POPULARITY")
	private Integer popularity;
	
	@Column(name="RECOMMEND")
	private Integer recommend;
	
	@Column(name="SHOP_TYPE")
	private String shopType;
	
	@Column(name="ADD_TIME")
	private Timestamp addTime;
	
	@Column(name="OVER_TIME")
	private Timestamp overTime;
	
	@Column(name="SERVICE")
	private String service;
	
	@Column(name="LOGO")
	private String logo;
	
	@Column(name="PICTURE")
	private String picture;
	
	@Column(name="PICTURE_HREF")
	private String pictureHref;
	
	@Column(name="BRIEF")
	private String brief;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="MARK")
	private BigDecimal mark;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="LONGITUDE")
	private Double longitude;
	
	@Column(name="LATITUDE")
	private Double latitude;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="WECHAT_PIC")
	private String wechatPic;
	
	@Column(name="REMARK")
	private String remark;
	
	
	/**
	 * 店铺类型名称
	 */
	@Transient
	private String shopTypeName;
	
	/**
	 * 过期时间字符串
	 */
	@Transient
	private String overTimeString;
	
	/**
	 * 店长
	 */
	@Transient
	private User shopKeeper;
	
	/**
 	 * logo图片path列表
 	 */
 	@Transient
 	private List<String> logoPathList;
	
 	/**
 	 * 图片path列表
 	 */
 	@Transient
 	private List<String> picPathList;
 	
 	/**
 	 * 图片链接列表
 	 */
 	@Transient
 	private List<String> picHrefList;
 	
 	/**
 	 * 微信二维码path列表
 	 */
 	@Transient
 	private List<String> wechatPathList;
 	
 	/**
 	 * 客服人员列表
 	 */
 	@Transient
	private List<User> serviceUserList;
	
	
    // Property accessors
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Timestamp getOverTime() {
		return overTime;
	}

	public void setOverTime(Timestamp overTime) {
		this.overTime = overTime;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getMark() {
		return mark;
	}

	public void setMark(BigDecimal mark) {
		this.mark = mark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOverTimeString() {
		if(this.overTime!=null && StringUtils.isBlank(overTimeString)) {
			try {
				overTimeString = DateUtil.convertDateStr(this.overTime.getTime(), DateUtil.DEFAULT_DATE_PATTERN);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return overTimeString;
	}

	public void setOverTimeString(String overTimeString) {
		this.overTimeString = overTimeString;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWechatPic() {
		return wechatPic;
	}

	public void setWechatPic(String wechatPic) {
		this.wechatPic = wechatPic;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<String> getPicPathList() {
		if(StringUtils.isNotBlank(picture) && null==picPathList){
			picPathList = Arrays.asList(picture.split(","));
		}
		return picPathList;
	}

	public void setPicPathList(List<String> picPathList) {
		this.picPathList = picPathList;
	}

	public String getShopTypeName() {
		if(StringUtils.isNotBlank(this.shopType)) {
			TypeService typeService = (TypeService)BeanDefinedLocator.getInstance().getBean("typeService");
			Typet typet = typeService.findByID(Typet.class, this.shopType);
			shopTypeName = typet.getName();
		}
		return shopTypeName;
	}

	public void setShopTypeName(String shopTypeName) {
		this.shopTypeName = shopTypeName;
	}

	public List<User> getServiceUserList() {
		if(StringUtils.isNotBlank(this.service)) {
			UserService userService = (UserService)BeanDefinedLocator.getInstance().getBean("userService");
			serviceUserList = userService.findByPropertes(User.class, "id", this.service.split(","));
		}
		return serviceUserList;
	}

	public void setServiceUserList(List<User> serviceUserList) {
		this.serviceUserList = serviceUserList;
	}

	public User getShopKeeper() {
		if(shopKeeper == null) {
			UserService userService = (UserService)BeanDefinedLocator.getInstance().getBean("userService");
			List<User> uList = userService.findBy(User.class, "shopId", id);
			
			for(User u : uList) {
				if(u.getRightgroups()!=null && u.getRightgroups().indexOf(RightGroupContants.RIGHT_GROUP_SHOPKEEPER) > -1) {
					shopKeeper = u;
					break;
				}
			}
		}
		return shopKeeper;
	}

	public void setShopKeeper(User shopKeeper) {
		this.shopKeeper = shopKeeper;
	}

	public List<String> getLogoPathList() {
		if(StringUtils.isNotBlank(logo) && null==logoPathList) {
			logoPathList = Arrays.asList(logo.split(","));
		}
		return logoPathList;
	}

	public void setLogoPathList(List<String> logoPathList) {
		this.logoPathList = logoPathList;
	}

	public List<String> getWechatPathList() {
		if(StringUtils.isNotBlank(wechatPic) && null==wechatPathList){
			wechatPathList = Arrays.asList(wechatPic.split(","));
		}
		return wechatPathList;
	}

	public void setWechatPathList(List<String> wechatPathList) {
		this.wechatPathList = wechatPathList;
	}

	public String getPictureHref() {
		return pictureHref;
	}

	public void setPictureHref(String pictureHref) {
		this.pictureHref = pictureHref;
	}

	public List<String> getPicHrefList() {
		picHrefList = new ArrayList<String>();
		if(StringUtils.isNotBlank(pictureHref)){
			picHrefList = Arrays.asList(pictureHref.split(","));
		}
		return picHrefList;
	}

	public void setPicHrefList(List<String> picHrefList) {
		this.picHrefList = picHrefList;
	}

}
