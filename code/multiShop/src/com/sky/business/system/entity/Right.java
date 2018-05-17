package com.sky.business.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 权限
 * @author Sky James
 *
 */
@Entity
@Table(name="tb_right")
public class Right implements Serializable {

	// Fields    
	private String id;
	private String name;
	private String type;
	
	// Constructors
    /** default constructor */
    public Right() {
    }

    /** full constructor */
    public Right(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Property accessors
    @Id 
    @Column(name="ID", unique=true, nullable=false, length=36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="NAME", length=200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="TYPE", length=100)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
