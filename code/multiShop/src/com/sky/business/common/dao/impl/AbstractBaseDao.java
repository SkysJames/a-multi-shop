package com.sky.business.common.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sky.business.common.dao.BaseHibernateDao;
import com.sky.business.common.dao.BaseJdbcDao;
import com.sky.util.BeanDefinedLocator;

/**
 * 抽象的基础dao
 * @author xiefeiye
 *
 */
public abstract class AbstractBaseDao {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private BaseJdbcDao baseJdbcDao;
	
	private BaseHibernateDao baseHibernateDao;
	
	@PostConstruct
	private void init()  {
		baseJdbcDao = (BaseJdbcDao) BeanDefinedLocator.getInstance().getBean(BaseJdbcDao.class);
		baseHibernateDao = (BaseHibernateDao) BeanDefinedLocator.getInstance().getBean(BaseHibernateDao.class);
	}

	/**
	 * 封装查询语句hql
	 * @param hqlBuffer
	 * @param values
	 * @param condition
	 */
	protected StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		return hqlBuffer;
	}
	
	
	protected BaseHibernateDao getBaseHibernateDao() {
		return baseHibernateDao;
	}

	
	protected BaseJdbcDao getBaseJdbcDao() {
		return baseJdbcDao;
	}


	public void setBaseJdbcDao(BaseJdbcDaoImpl baseJdbcDao) {
		this.baseJdbcDao = baseJdbcDao;
	}


	public void setBaseHibernateDao(BaseHibernateDao baseHibernateDao) {
		this.baseHibernateDao = baseHibernateDao;
	}
}
