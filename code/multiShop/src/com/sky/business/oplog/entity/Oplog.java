package com.sky.business.oplog.entity;
// default package

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sky.business.system.entity.User;
import com.sky.business.system.service.UserService;
import com.sky.util.BeanDefinedLocator;

/**
 * Oplog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_oplog")
public class Oplog implements java.io.Serializable {


    // Fields    

	@Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
    private String id;
	
	@Column(name="USER_ID", length=36)
    private String userId;
	
	@Column(name="OP_TIME", nullable=false, length=19)
    private Timestamp opTime;
	
	@Column(name="OP_TYPE", nullable=false, length=50)
    private String opType;
	
	@Column(name="OP_DETAIL", length=4000)
    private String opDetail;
	
	@Column(name="OP_IP", nullable=false, length=40)
    private String opIp;
    
    /************** 非持久化字段 start **************/
	/**
	 * 用户名称
	 */
	@Transient
	private String userName;
	/************** 非持久化字段 end  **************/

    // Constructors

    /** default constructor */
    public Oplog() {
    }

	/** minimal constructor */
    public Oplog(String id, Timestamp opTime, String opType, String opIp) {
        this.id = id;
        this.opTime = opTime;
        this.opType = opType;
        this.opIp = opIp;
    }
    
    /** full constructor */
    public Oplog(String id, String userId, Timestamp opTime, String opType, String opDetail, String opIp) {
        this.id = id;
        this.userId = userId;
        this.opTime = opTime;
        this.opType = opType;
        this.opDetail = opDetail;
        this.opIp = opIp;
    }
    
    /**
	 * 记录用户登录的操作行为（用户日志）
	 * 
	 * @param userId
	 * @param opType
	 * @param opDetail
	 * @param opIp
	 * @return
	 */
	public static Oplog newOpUserInstance(String userId, String opType, String opDetail, String opIp) {
		Oplog opLog = new Oplog(UUID.randomUUID().toString(), userId, new Timestamp(new Date().getTime()), opType, opDetail, opIp);
		return opLog;
	}

	/**
	 * 记录访客的操作行为（访客日志）
	 * 
	 * @param opType
	 * @param opDetail
	 * @param opIp
	 * @return
	 */
	public static Oplog newOpNullUserInstance(String opType, String opDetail, String opIp) {
		Oplog opLog = new Oplog(UUID.randomUUID().toString(), null, new Timestamp(new Date().getTime()), opType, opDetail, opIp);
		return opLog;
	}
   
    // Property accessors
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public Timestamp getOpTime() {
        return this.opTime;
    }
    
    public void setOpTime(Timestamp opTime) {
        this.opTime = opTime;
    }
    
    public String getOpType() {
        return this.opType;
    }
    
    public void setOpType(String opType) {
        this.opType = opType;
    }
    
    public String getOpDetail() {
        return this.opDetail;
    }
    
    public void setOpDetail(String opDetail) {
        this.opDetail = opDetail;
    }
    
    public String getOpIp() {
        return this.opIp;
    }
    
    public void setOpIp(String opIp) {
        this.opIp = opIp;
    }

	public String getUserName() {
		try {
		    	if (null == userName) {
		    		UserService userService = (UserService)BeanDefinedLocator.getInstance().getBean("userService");
		    		
		    		if(null != userId){
		    			User user = userService.findByID(User.class, userId);
		    			userName = user.getName();
		    		}
			}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}