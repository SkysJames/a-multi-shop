package com.sky.business.system.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.sky.util.DateUtil;

/**
 * 公告
 * @author Sky James
 *
 */
@Entity
@Table(name="tb_announce")
public class Announce implements Serializable {

	// Fields    
	@Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
	private String id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SHOP_ID")
	private String shopId;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name="CREATE_USER")
	private String createUser;
	
	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;
	
	@Column(name="UPDATE_USER")
	private String updateUser;
	
	@Column(name="OVER_TIME")
	private Timestamp overTime;
	
	@Column(name="STATUS")
	private Integer status;
	
	//过期时间字符串
	@Transient
	private String overTimeString;
	
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

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getOverTime() {
		return overTime;
	}

	public void setOverTime(Timestamp overTime) {
		this.overTime = overTime;
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

}
