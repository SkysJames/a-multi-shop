package com.sky.business.common.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 登录用户
 * @author Sky James
 *
 */
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String username;

    private String userPwd;
    
    private Integer userLevel;
    
    //登录IP
    private String userIp;
    
    //登录时间
    private Timestamp loginTime;

    public LoginUser() {
	}
    
    public LoginUser(String userId, String userIp, Timestamp loginTime) {
		super();
		this.userId = userId;
		this.userIp = userIp;
		this.loginTime = loginTime;
	}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	@Override
	public String toString() {
		return "LoginUser [userId=" + userId + ", username=" + username
				+ ", userPwd=" + userPwd + ", userIp=" + userIp
				+ ", loginTime=" + loginTime + "]";
	}

}