package com.sky.business.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.StoredProcedure;

import com.sky.business.common.vo.Pager;

/**
 * JDBC的底层dao
 * @author xiefeiye
 *
 */
public interface BaseJdbcDao {

	/**
	 * 根据id获取map对象数据
	 * @param tableName	表名称
	 * @param idName	id字段名
	 * @param id		id值
	 * @param select	返回的字段名，null则默认*
	 * @return
	 */
	public Map<String, Object> findUniqueById(String tableName, String idName, String id, String select);
	
	/**
	 * 通过jdbc获取数据集
	 * 
	 * @param sql
	 *            标准的sql语句
	 */
	public List getResultByJdbc(String sql);
	
	
	/**
	 * 查询结果集
	 * @param sql
	 * @param args 绑定变量
	 * @return
	 */
	public List getResultByJdbc(String sql, Object[] args);
	
	
	/**
	 * 执行sql语句（由于没有绑定变量，不建议使用）
	 * 
	 * @param sql
	 *            标准的sql语句
	 */
	public void ExecuteByJdbc(String sql);
	
	/**
	 * 执行insert、update、delete语句
	 * 
	 * @param sql
	 * @param bindedVariables 绑定变量
	 * <br/>
	 * <br/>
	 * .update(
     *   	"insert into t_actor (first_name, surname) values (?, ?) ", 
     *  		new Object[] {"Leonor", "Watling"});
     *  
     *   <br/>
     *   <br/>
     *  .update(
     *   	"update t_actor set weapon = ? where id = ? ", 
     *   		new Object[] {"Banjo", new Long(5276)});
     *   
     *    <br/>
     *    <br/>
     *  .update(
     *   	"delete from actor where id = ? ",
     *       	new Object[] {new Long.valueOf(actorId)});
	 * 
	 */
	public void update(String sql, Object[] bindedVariables);
	
	
	/**
	 * 统计记录数
	 * @param sql
	 * @param bindedVariables 绑定变量
	 * @return
	 * 
	 * .queryForInt(
	 * 	"select count(0) from t_actors where first_name = ?", new Object[]{"Joe"});
	 */
	public int queryForInt(String sql, Object[] bindedVariables);
	
	/**
	 * 获取对象
	 * @param sql
	 * @param requiredType
	 * @return
	 */
	public Object queryForObject(String sql, Class requiredType);
	
	/**
	 * 获取对象
	 * @param sql
	 * @param requiredType
	 * @param args 绑定参数
	 * @return
	 */
	public <T> T queryForObject(String sql, Class<T> requiredType, Object...args);
	
	
	/**
	 * 批量执行sql语句
	 * @param sqlList
	 * @return
	 */
	public boolean batchExecuteByJdbc(List<String> sqlList);   

	/**
	 * 查询从xx行到yy行的数据，使用sql
	 * @param sql sql语句
	 * @param startRowIndex 开始查询的行数
	 * @param pageSize 查询的最大数量
	 * @return
	 */
	public Pager getPagedResultWithStartRowIndexByJdbc(String sql, int startRowIndex, int pageSize);
	
	/**
	 * 查询从xx行到yy行的数据，使用sql
	 * @param sql sql语句
	 * @param startRowIndex 开始查询的行数
	 * @param pageSize 查询的最大数量
	 * @param bindedVariables 参数变量
	 * @return
	 */
	public Pager getPagedResultWithStartRowIndexByJdbc(String sql, int startRowIndex, int pageSize, List<Object> bindedVariables);
	
	/**
	 * 分页查询函数，使用sql.
	 * @param sql sql语句
	 * @param currentPage 当前页
	 * @param pageSize 每页记录数
	 * @return
	 */
	public Pager getPagedResultByJdbc(String sql, int currentPage, int pageSize);
	
	/**
	 * 分页查询函数，使用sql.
	 * @param sql 根据传入的sql语句得到一些基本分页信息
	 * @param currentPage 当前页
	 * @param pageSize 每页记录数
	 * @param bindedVariables 参数变量
	 * @return
	 */
	public Pager getPagedResultByJdbc(String sql, int currentPage, int pageSize, List<Object> bindedVariables);
	
	/**
	 * 通过jdbc获取数据集，并且只获取前面maxRows条数据
	 * @param sql
	 * @param maxRows
	 * @return
	 */
	public List getResultByJdbcWithMaxRows(String sql, int maxRows);
	
	
	/**
	 * 获得储存过程对象
	 * @param procName 存储过程名称
	 * @return
	 */	
	public StoredProcedure getStoredProcedure(String procName);
	   
	   /**
		 * 执行储存过程
		 * @param procName 存储过程名称
		 * @param SqlParameters 输入参数定义
		 * @param SqlOutParameters 输出参数定义
		 * @param param 输入参数值
		 * @return
		 */	
     public Map executeStoredProcedure(String procName,List SqlParameters,List SqlOutParameters,Map param);
     
	   /**
		 * 执行函数
		 * @param procName 函数名称
		 * @param param 输入参数值
		 * @return
		 */	    
     public String executeFunction(String procName, List param);
     
     
     /**
      * 是否输出sql
     * @return
     */
    public boolean isShowSql();
    
    public <T> List<T> query(String sql,RowMapper<T> rowMapper);
    
    public <T> List<T> query(String sql, Object[] params, RowMapper<T> rowMapper);

	/**
	 * 查询(可控制返回数量)
	 * @param sql
	 * @param params
	 * @param rowMapper
	 * @param startIdx 开始序号
	 * @param endIdx 结束序号
	 * @return
	 */
	<T> List<T> query(String sql, Object[] params, RowMapper<T> rowMapper, int startIdx, int endIdx);
	
}
