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

import com.sky.business.system.service.DepartmentService;
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
	private String id;
	private String name;
	private String passwd;
	private String departmentId;
	private String rights;
	private String rightgroups;
	private Integer userStatus;
	private Integer loginStatus;
	private String loginIp;
	private Timestamp loginTime;
	private Timestamp logoutTime;
	private String remark;
	private Timestamp createTime;
	
	//该用户拥有的所有权限（包括角色的权限）
	private String allRights;
	//部门名称
	private Department department;
	//角色列表
	private List<RightGroup> rightgroupList;
	//权限列表
	private List<Right> rightList;

    // Property accessors
    @Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="NAME", nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="PASSWD", length=64)
    public String getPasswd() {
        return this.passwd;
    }
    
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
    @Column(name="DEPARTMENT_ID", length=36)
    public String getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    
    @Column(name="RIGHTS", length=4000)
    public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	@Column(name="RIGHTGROUPS", length=4000)
	public String getRightgroups() {
		return rightgroups;
	}

	public void setRightgroups(String rightgroups) {
		this.rightgroups = rightgroups;
	}

	@Column(name="USER_STATUS", nullable=false)
    public Integer getUserStatus() {
        return this.userStatus;
    }
    
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
    
    @Column(name="LOGIN_STATUS", nullable=false)
    public Integer getLoginStatus() {
        return this.loginStatus;
    }
    
    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }
    
    @Column(name="LOGIN_IP", length=40)
    public String getLoginIp() {
        return this.loginIp;
    }
    
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
    
    @Column(name="LOGIN_TIME", length=19)
    public Timestamp getLoginTime() {
        return this.loginTime;
    }
    
    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
    
    @Column(name="LOGOUT_TIME", length=19)
    public Timestamp getLogoutTime() {
        return this.logoutTime;
    }
    
    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }
    
    @Column(name="REMARK", length=4000)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="CREATE_TIME", length=19)
    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Transient
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

	@Transient
	public Department getDepartment() {
		try {
		    	if (null == department) {
		    		DepartmentService departmentService = (DepartmentService)BeanDefinedLocator.getInstance().getBean("departmentService");
		    		
		    		if(null != departmentId){
		    			department = departmentService.findByID(Department.class, departmentId);
		    		}
			}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Transient
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

	@Transient
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

}