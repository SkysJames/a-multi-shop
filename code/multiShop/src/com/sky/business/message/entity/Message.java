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
 * 消息
 * @author Sky James
 *
 */
@Entity
@Table(name="tb_message")
public class Message implements Serializable {

	// Fields    
	@Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
	private String id;
	
	@Column(name="FROM_USER")
	private String fromUser;
	
	@Column(name="TO_USER")
	private String toUser;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="SEND_TIME")
	private Timestamp sendTime;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Column(name="HREF")
	private String href;
	
	@Transient
	private String fromUserName;
	
    // Property accessors
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getFromUserName() {
		if(StringUtils.isNotBlank(this.fromUser)) {
			UserService userService = (UserService)BeanDefinedLocator.getInstance().getBean("userService");
			User u = userService.findByID(User.class, this.fromUser);
			fromUserName = u.getName();
		}
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

}
