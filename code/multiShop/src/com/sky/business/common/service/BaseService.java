package com.sky.business.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.sky.business.common.vo.Pager;

/**
 * 基础数据处理服务
 * @author xiefeiye
 *
 */
public interface BaseService {
	
	/**
	 * 创建持久化对象
	 * 
	 * @param entity
	 * @return
	 */
	public void save(Object entity) ;
	
	/**
	 * 批量持久化对象
	 * @param entitys
	 */
	public void batchSave(List entitys);

	/**
	 * 持久化实体对象的变化
	 * 
	 * @param entity
	 */
	public void update(Object entity) ;
	
	/**
	 * 批量持久化实体对象的变化
	 * @param entitys
	 */
	public void batchUpdate(List entitys);

	/**
	 * 创建持久化对象 持久化实体对象的变化
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(Object entity) ;

	/**
	 * 删除持久化对象
	 * 
	 * @param entity
	 */
	public void delete(Object entity) ;

	/**
	 * 根据主键id获取实体对象
	 * 
	 * @param id
	 *            实体对象主键
	 * @return 目标实体对象
	 */
	public <T> T findByID(Class<T> entity, Serializable id) ;
	
	public <T> T findByUnique(Class<T> entity, String propertyName, Object value) ;
	
	/**
	 * 根据多个同一字段的不同值查询对象
	 * @param entity
	 * @param propertyName
	 * @param values
	 * @return
	 */
	public <T> List<T> findByPropertes(Class<T> entity, String propertyName, Object[] values) ;
	
	public <T> List<T> findBy(Class<T> entity, String propertyName, Object value);
	
	public <T> List<T> findBy(Class<T> entity,  String propertyName, Object value, String orderBy, boolean isAsc) ;
	
	public <T> List<T> findAll(Class<T> entity, String orderBy, boolean isAsc);
	
	/**
	 * 统计所有数据量
	 * @param entity
	 * @return
	 */
	public <T> int countAll(Class<T> entity);
	
	/**
	 * 根据相应的属性获取相应表的某一属性值列表
	 * @param tableName			数据库表名
	 * @param outputProperty	输出的属性名
	 * @param inputProperty		输入的属性名
	 * @param inputValue		输入的属性值
	 * @return
	 */
	public List getPropertyByID(String table, String outputProperty, String inputProperty, Object inputValue);
	
	/**
	 * 根据一定的条件分页获取列表
	 * @param condition
	 * @return
	 */
	public <T> Pager pagedList(Class<T> entity, Map<String, Object> condition) throws Exception;
	
	/**
	 * 根据一定的条件获取列表
	 * @param condition
	 * @return
	 */
	public <T> List getList(Class<T> entity, Map<String, Object> condition) throws Exception;
	
	/**
	 * 根据一定的条件获取数量
	 * @param condition
	 * @return
	 */
	public <T> Integer getCount(Class<T> entity, Map<String, Object> condition) throws Exception;
	
}
