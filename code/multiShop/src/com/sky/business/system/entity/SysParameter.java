package com.sky.business.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统参数
 * @author Sky James
 *
 */
@Entity
@Table(name="sys_parameter")
public class SysParameter implements Serializable {

	// Fields    
	private String name;
	private String type;
	private String value;
	private Integer valueType;
	private String description;
	private Integer must;
	

    // Property accessors
	@Id
	@Column(name="NAME", unique=true, nullable=false, length=200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="TYPE", nullable=false, length=100)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="VALUE", length=4000)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name="VALUETYPE", nullable=false)
	public Integer getValueType() {
		return valueType;
	}

	public void setValueType(Integer valueType) {
		this.valueType = valueType;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="MUST", nullable=false)
	public Integer getMust() {
		return must;
	}

	public void setMust(Integer must) {
		this.must = must;
	}
	
}
