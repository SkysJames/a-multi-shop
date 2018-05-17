package com.sky.business.bbstopic.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 论坛帖子
 * @author Sky James
 *
 */
@Entity
@Table(name="tb_bbstopic")
public class Bbstopic implements Serializable {

	// Fields    
	@Id 
    @Column(name="ID", unique=true, nullable=false, length=32)
	private String id;
	
	@Column(name="SECTION_ID")
	private String sectionId;
	
	@Column(name="MASTERID")
	private String masterid;
	
	@Column(name="TOPIC_TYPE")
	private String topicType;
	
	@Column(name="TOPIC_ID")
	private String topicId;
	
	@Column(name="TOPIC_MASTERID")
	private String topicMasterid;
	
	@Column(name="TOPIC_NAME")
	private String topicName;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="REPLY_COUNT")
	private Integer replyCount;
	
	@Column(name="CLICK_COUNT")
	private Integer clickCount;
	
	@Column(name="LIKE_NUM")
	private Integer likeNum;
	
	@Column(name="CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name="STATUS")
	private Integer status;
	
    // Property accessors
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getTopicMasterid() {
		return topicMasterid;
	}

	public void setTopicMasterid(String topicMasterid) {
		this.topicMasterid = topicMasterid;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
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

}
