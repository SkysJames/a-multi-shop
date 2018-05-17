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

/**
 * 评价
 * @author Sky James
 *
 */
@Entity
@Table(name="tb_evaluate")
public class Evaluate implements Serializable {

	// Fields    
	@Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
	private String id;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="OBJ_ID")
	private String objId;
	
	@Column(name="TABLE_NAME")
	private String tableName;
	
	@Column(name="MARK")
	private BigDecimal mark;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="CREATE_TIME")
	private Timestamp creatTime;
	
	@Column(name="PICTURE")
	private String picture;
	
	@Column(name="STATUS")
	private Integer status;
	
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public BigDecimal getMark() {
		return mark;
	}

	public void setMark(BigDecimal mark) {
		this.mark = mark;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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

}
