package com.sky.util;

import javax.sql.DataSource;

import org.springframework.jdbc.object.StoredProcedure;

/**
 * 执行数据库存储过程
 * @author xiefeiye
 *
 */
public class TdrStoredProcedure extends StoredProcedure{
	
	public  TdrStoredProcedure(DataSource dataSource,String procName){
		
		super(dataSource,procName);
		
	}

}
