package com.sky.business.news.entity;
// default package

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
 * News entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_news")
public class News  implements java.io.Serializable {


    // Fields    

	@Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
	private String id;
	
	@Column(name="NAME", length=100)
	private String name;
	
	@Column(name="NEWS_TYPE")
	private Integer newsType;
	
	@Column(name="CONTENT", length=4000)
	private String content;
	
	@Column(name="PICTURE", length=4000)
	private String picture;
	
	@Column(name="CREATE_TIME", length=19)
    private Timestamp createTime;
	
	@Column(name="CREATE_USER", length=36)
    private String createUser;
	
	@Column(name="UPDATE_TIME", length=19)
    private Timestamp updateTime;
	
	@Column(name="UPDATE_USER", length=36)
    private String updateUser;
	
	
	/************** 非持久化字段 start **************/
	/**
	 * 图片path列表
	 */
	@Transient
	private List<String> picPathList;
	/************** 非持久化字段 end  **************/


    // Constructors

    /** default constructor */
    public News() {
    }

	/** minimal constructor */
    public News(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public News(String id, String name, Integer newsType, String content, String picture, Timestamp createTime, String createUser, Timestamp updateTime, String updateUser) {
        this.id = id;
        this.name = name;
        this.newsType = newsType;
        this.content = content;
        this.picture = picture;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
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
    
    public Integer getNewsType() {
        return this.newsType;
    }
    
    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
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