package com.sky.business.common.dao.impl;

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
