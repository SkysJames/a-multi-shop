package com.sky.business.system.entity;
// default package

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.sky.business.shop.entity.Shop;
import com.sky.business.shop.service.ShopService;
import com.sky.business.system.service.RightGroupService;
import com.sky.business.system.service.RightService;
import com.sky.util.BeanDefinedLocator;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_user")
public class User implements java.io.Serializable {

    // Fields    
	@Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
	private String id;
	
	@Column(name="NAME", nullable=false, length=100)
	private String name;
	
	@Column(name="PASSWD", length=64)
	private String passwd;
	
	@Column(name="SEX", length=8)
	private String sex;
	
	@Column(name="BIRTHDATE")
	private String birthdate;
	
	@Column(name="SHOP_ID", length=36)
	private String shopId;
	
	@Column(name="RIGHTS")
	private String rights;
	
	@Column(name="RIGHTGROUPS")
	private String rightgroups;
	
	@Column(name="USER_STATUS", nullable=false)
	private Integer userStatus;
	
	@Column(name="LOGIN_STATUS", nullable=false)
	private Integer loginStatus;
	
	@Column(name="LOGIN_IP", length=40)
	private String loginIp;
	
	@Column(name="LOGIN_TIME", length=19)
	private Timestamp loginTime;
	
	@Column(name="LOGOUT_TIME", length=19)
	private Timestamp logoutTime;
	
	@Column(name="REMARK", length=4000)
	private String remark;
	
	@Column(name="CREATE_TIME", length=19)
	private Timestamp createTime;
	
	@Column(name="QQ", length=36)
	private String qq;
	
	@Column(name="WECHAT", length=36)
	private String wechat;
	
	@Column(name="TELEPHONE", length=36)
	private String telephone;
	
	@Column(name="WOPEN_ID", length=36)
	private String wopenId;
	
	//店铺
	@Transient
	private Shop shop;
	
	//该用户拥有的所有权限字符串（包括角色的权限）
	@Transient
	private String allRights;
	
	//角色列表
	@Transient
	private List<RightGroup> rightgroupList;
	
	//权限列表
	@Transient
	private List<Right> rightList;

	
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
    
    public String getPasswd() {
        return this.passwd;
    }
    
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
    public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getRightgroups() {
		return rightgroups;
	}

	public void setRightgroups(String rightgroups) {
		this.rightgroups = rightgroups;
	}

    public Integer getUserStatus() {
        return this.userStatus;
    }
    
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
    
    public Integer getLoginStatus() {
        return this.loginStatus;
    }
    
    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }
    
    public String getLoginIp() {
        return this.loginIp;
    }
    
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
    
    public Timestamp getLoginTime() {
        return this.loginTime;
    }
    
    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
    
    public Timestamp getLogoutTime() {
        return this.logoutTime;
    }
    
    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

	public String getWopenId() {
		return wopenId;
	}

	public void setWopenId(String wopenId) {
		this.wopenId = wopenId;
	}

	public String getAllRights() {
	    	try {
		    	if (null == allRights) {
		    		allRights = this.rights==null?"":this.rights;//将该用户拥有的权限赋予
		    		RightGroupService rightGroupService = (RightGroupService)BeanDefinedLocator.getInstance().getBean("rightGroupService");
		    		
		    		//将该用户拥有的角色的权限，一个一个的赋予
		    		String[] rgs = new String[0];
		    		if(StringUtils.isNotBlank(this.rightgroups)){
		    			rgs = this.rightgroups.split(",");
		    		}
		    		for(String rg : rgs){
		    			RightGroup rgObj = rightGroupService.findByID(RightGroup.class, rg);
		    			String[] rs = rgObj.getRights().split(",");
		    			for(String r : rs){
		    				if(allRights.indexOf(r) > -1){
		    					continue;
		    				}else{
		    					if(StringUtils.isNotBlank(allRights)){
		    						allRights += "," + r;
			    		    		}else{
			    		    			allRights = r;
			    		    		}
		    				}
		    			}
		    		}
		    	}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}				
		return allRights;
	}

	public void setAllRights(String allRights) {
		this.allRights = allRights;
	}

	public Shop getShop() {
		try {
		    	if (null == shop) {
		    		ShopService shopService = (ShopService)BeanDefinedLocator.getInstance().getBean("shopService");
		    		
		    		if(null != shopId){
		    			shop = shopService.findByID(Shop.class, shopId);
		    		}
			}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<RightGroup> getRightgroupList() {
		try {
		    	if (null == rightgroupList) {
		    		RightGroupService rightGroupService = (RightGroupService)BeanDefinedLocator.getInstance().getBean("rightGroupService");
		    		
		    		if(StringUtils.isNotBlank(this.rightgroups)){
		    			String[] rgs = this.rightgroups.split(",");
		    			rightgroupList = rightGroupService.findByPropertes(RightGroup.class, "id", rgs);
		    		}
			}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}			
		return rightgroupList;
	}

	public void setRightgroupList(List<RightGroup> rightgroupList) {
		this.rightgroupList = rightgroupList;
	}

	public List<Right> getRightList() {
		try {
		    	if (null == rightList) {
		    		RightService rightService = (RightService)BeanDefinedLocator.getInstance().getBean("rightService");
		    		
		    		if(StringUtils.isNotBlank(this.rights)){
		    			String[] rs = this.rights.split(",");
		    			rightList = rightService.findByPropertes(Right.class, "id", rs);
		    		}
			}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}			
		return rightList;
	}

	public void setRightList(List<Right> rightList) {
		this.rightList = rightList;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

}