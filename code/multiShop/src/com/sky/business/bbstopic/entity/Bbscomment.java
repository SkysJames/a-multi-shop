package com.sky.business.bbstopic.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 论坛评论
 * @author xiefeiye
 *
 */
@Entity
@Table(name="tb_bbscomment")
public class Bbscomment implements Serializable {

	// Fields    
	@Id 
    @Column(name="ID", unique=true, nullable=false, length=32)
	private String id;
	
	@Column(name="MASTERID")
	private String masterid;
	
	@Column(name="ASSISTANTID")
	private String assistantid;
	
	@Column(name="REPLY_ID")
	private String replyId;
	
	@Column(name="CONTENT")
	private String content;
	
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

	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getAssistantid() {
		return assistantid;
	}

	public void setAssistantid(String assistantid) {
		this.assistantid = assistantid;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
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

}
