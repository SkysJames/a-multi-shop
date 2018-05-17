package com.sky.business.system.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
}
