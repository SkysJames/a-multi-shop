package com.sky.business.bbstopic.entity;
// default package

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;


/**
 * 论坛板块
 * @author xiefeiye
 *
 */
@Entity
@Table(name="tb_bbssection")
public class Bbssection  implements java.io.Serializable {


    // Fields
	@Id 
    @Column(name="ID", unique=true, nullable=false, length=32)
    private String id;
	
	@Column(name="NAME")
    private String name;
	
	@Column(name="BRIEF")
	private String brief;
	
	@Column(name="HEADPIC")
	private String headpic;
	
	@Column(name="CREATE_TIME", length=19)
    private Timestamp createTime;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getHeadpic() {
		return headpic;
	}

	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}