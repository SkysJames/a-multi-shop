package com.sky.business.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 部门
 * @author Sky James
 *
 */
@Entity
@Table(name="tb_department")
public class Department implements Serializable {

	// Fields    
	private String id;
	private String name;
	private String parentId;
	private String remark;
	
    // Property accessors
    @Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="NAME", length=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="PARENT_ID", length=36)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name="REMARK", length=4000)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
