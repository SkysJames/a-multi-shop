package com.sky.business.common.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sky.business.common.dao.BaseDao;

@Service("baseDao")
public class BaseDaoImpl extends AbstractBaseDao implements BaseDao {
	
	@Override
	public void delete(Object entity) {
		this.getBaseHibernateDao().delete(entity);
	}

	@Override
	public <T> T findByID(Class<T> entity, Serializable id)  {
		return this.getBaseHibernateDao().findByID(entity, id);
	}
	
	@Override
	public <T> T findByUnique(Class<T> entity, String propertyName, Object value){
		return this.getBaseHibernateDao().findUniqueBy(entity, propertyName, value);
	}
	
	@Override
	public <T> List<T> findByPropertes(Class<T> entity, String propertyName, Object[] values)  {
		return this.getBaseHibernateDao().findByPropertes(entity, propertyName, values);
	}
	
	@Override
	public <T> List<T> findBy(Class<T> entity, String propertyName, Object value)  {
		return this.getBaseHibernateDao().findBy(entity, propertyName, value);
	}
	
	@Override
	public <T> List<T> findBy(Class<T> entity,  String propertyName, Object value, String orderBy, boolean isAsc)  {
		return this.getBaseHibernateDao().findBy(entity, propertyName, value, orderBy, isAsc);
	}
	
	@Override
	public <T> List<T> findAll(Class<T> entity, String orderBy, boolean isAsc)  {
		return this.getBaseHibernateDao().findAll(entity, orderBy, isAsc);
	}
	
	@Override
	public <T> int countAll(Class<T> entity) {
		return this.getBaseHibernateDao().countAll(entity);
	}

	@Override
	public void save(Object entity) {
		this.getBaseHibernateDao().save(entity);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		this.getBaseHibernateDao().saveOrUpdate(entity);

	}

	@Override
	public void update(Object entity)  {
		this.getBaseHibernateDao().update(entity);

	}

	@Override
	public void batchSave(List entitys) {
		this.getBaseHibernateDao().batchSave(entitys);
		
	}
	
	@Override
	public void batchUpdate(List entitys) {
		this.getBaseHibernateDao().batchUpdate(entitys);
	}

	@Override
	public <T> T queryWithSql(String sql, Class<T> requiredType, Object...args) {
		return this.getBaseJdbcDao().queryForObject(sql, requiredType, args);
	}
	
	@Override
	public void executeWithSql(String sql,Object...args){
		this.getBaseJdbcDao().update(sql, args);
	}

	@Override
	public List getPropertyByID(String tableName, String outputProperty, String inputProperty, Object inputValue) {
		StringBuffer sqlBuffer = new StringBuffer("select " + outputProperty + " from " + tableName 
				+ " where " + inputProperty + " = ?" );
		List<Object> values = new ArrayList<Object>();
		values.add(inputValue);
		return this.getBaseJdbcDao().getResultByJdbc(sqlBuffer.toString(), values.toArray());
	}
}
