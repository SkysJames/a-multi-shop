package com.sky.business.system.entity;
// default package

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.sky.business.system.service.RightService;
import com.sky.util.BeanDefinedLocator;


/**
 * 角色
 * @author Sky James
 *
 */
@Entity
@Table(name="tb_rightgroup")
public class RightGroup  implements java.io.Serializable {


	// Fields    
	private String id;
	private String name;
	private String rights;
	
	// 权限列表
	private List<Right> rightList;

    // Constructors
    /** default constructor */
    public RightGroup() {
    }

    /** full constructor */
    public RightGroup(String id, String name, String rights) {
        this.id = id;
        this.name = name;
        this.rights = rights;
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
    
    @Column(name="NAME", length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="RIGHTS", length=4000)
    public String getRights() {
        return this.rights;
    }
    
    public void setRights(String rights) {
        this.rights = rights;
    }

    @Transient
	public List<Right> getRightList() {
	    	try {
		    	if (null == rightList && StringUtils.isNotBlank(rights)) {
		    		RightService rightService = (RightService)BeanDefinedLocator.getInstance().getBean("rightService");
				rightList = rightService.getRightListByIds(rights);
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