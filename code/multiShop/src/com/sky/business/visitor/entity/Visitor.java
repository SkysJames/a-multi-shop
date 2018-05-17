package com.sky.business.visitor.entity;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Visitor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_visitor")
public class Visitor  implements java.io.Serializable {


    // Fields    

     private String id;
     private String ip;
     private String shopId;
     private Integer status;
     private String remark;
     private Integer visitedTimes;
     private Timestamp createTime;
     private Timestamp visitedTime;


    // Constructors

    /** default constructor */
    public Visitor() {
    }

	/** minimal constructor */
    public Visitor(String id, String ip, String shopId, Integer status, Integer visitedTimes) {
        this.id = id;
        this.ip = ip;
        this.shopId = shopId;
        this.status = status;
        this.visitedTimes = visitedTimes;
    }
    
    /** full constructor */
    public Visitor(String id, String ip, String shopId, Integer status, String remark, Integer visitedTimes, Timestamp createTime, Timestamp visitedTime) {
        this.id = id;
        this.ip = ip;
        this.shopId = shopId;
        this.status = status;
        this.remark = remark;
        this.visitedTimes = visitedTimes;
        this.createTime = createTime;
        this.visitedTime = visitedTime;
    }

   
    // Property accessors
    @Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="IP", nullable=false, length=40)
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    @Column(name="STATUS", nullable=false)
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Column(name="REMARK", length=4000)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="VISITED_TIMES", nullable=false)
    public Integer getVisitedTimes() {
        return this.visitedTimes;
    }
    
    public void setVisitedTimes(Integer visitedTimes) {
        this.visitedTimes = visitedTimes;
    }
    
    @Column(name="CREATE_TIME", length=19)
    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="VISITED_TIME", length=19)
    public Timestamp getVisitedTime() {
        return this.visitedTime;
    }
    
    public void setVisitedTime(Timestamp visitedTime) {
        this.visitedTime = visitedTime;
    }

    @Column(name="SHOP_ID", length=36)
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

}