package com.sky.business.common.dao.impl;


 
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.support.DaoSupport;

public abstract class Hibernate4DaoSupport extends DaoSupport {
	
	/**
	 * 日志工具对象
	 */
	protected final Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * 默认使用sessionFactory.getCurrentSession()获取事物上下文的会话
	 * 如果发现线程是无事物的时候使用sessionFactory.openSession()新建会话
	 * @return
	 */
	public Session getHibernateSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (Exception e) {
			log.warn(String.format("线程【%s】不存在事物开始新建会话", Thread.currentThread()));
			return sessionFactory.openSession();
		}
		
	}
	
	protected final void checkDaoConfig(){
	     if (this.sessionFactory == null)
	       throw new IllegalArgumentException("'sessionFactory' is required");
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
