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
    
    private String shopId;
    
    private String shopName;
    
    private Integer shopStatus;
    
    private String qq;
    
    private String wechat;
    
    private String telephone;
    
    //该用户拥有的所有角色
    private String rightgroups;
    
    //该用户拥有的所有权限（包括角色的权限）
    private String allRights;
    
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

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(Integer shopStatus) {
		this.shopStatus = shopStatus;
	}

	public String getAllRights() {
		return allRights;
	}

	public void setAllRights(String allRights) {
		this.allRights = allRights;
	}

	public String getRightgroups() {
		return rightgroups;
	}

	public void setRightgroups(String rightgroups) {
		this.rightgroups = rightgroups;
	}

}