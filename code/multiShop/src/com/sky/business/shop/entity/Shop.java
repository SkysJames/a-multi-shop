package com.sky.business.shop.entity;

import java.io.Serializable;
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
	
	
	/**
	 * 过期时间字符串
	 */
	@Transient
	private String overTimeString;
	
	/**
 	 * 图片path列表
 	 */
 	@Transient
 	private List<String> picPathList;
	
	
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
		if(StringUtils.isBlank(overTimeString)) {
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

}
