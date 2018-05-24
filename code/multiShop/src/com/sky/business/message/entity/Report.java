package com.sky.business.message.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.sky.business.system.entity.User;
import com.sky.business.system.service.UserService;
import com.sky.util.BeanDefinedLocator;

/**
 * 举报表
 * @author xiefeiye
 *
 */
@Entity
@Table(name="tb_report")
public class Report implements Serializable {

	// Fields   
	@Id 
	@Column(name="ID", unique=true, nullable=false, length=36)
	private String id;
	
	@Column(name="OBJ_ID")
	private String objId;
	
	@Column(name="TABLE_NAME")
	private String tableName;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String userName;
	
    // Property accessors
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserName() {
		if(StringUtils.isNotBlank(this.userId)) {
			UserService userService = (UserService)BeanDefinedLocator.getInstance().getBean("userService");
			User u = userService.findByID(User.class, this.userId);
			userName = u.getName();
		}
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
