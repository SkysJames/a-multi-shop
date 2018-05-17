package com.sky.business.common.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.sky.business.common.dao.BaseHibernateDao;
import com.sky.business.common.vo.Pager;

/**
 * hibernate4之后，spring31把HibernateDaoSupport去除，包括数据访问都不需要hibernatetemplate，
 * 这意味着原来的dao需要改写，直接使用hibernate的session和query接口
 * @author xiefeiye
 *
 */
public class BaseHibernateDaoImpl extends Hibernate4DaoSupport implements BaseHibernateDao {

	/**
	 * 创建持久化对象
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public void save(Object entity) {
		getHibernateSession().save(entity);
	}

	/**
	 * 持久化实体对象的变化
	 * 
	 * @param entity
	 */
	@Override
	public void update(Object entity) {
		getHibernateSession().saveOrUpdate(entity);
	}

	/**
	 * 创建持久化对象 持久化实体对象的变化
	 * 
	 * @param entity
	 */
	@Override
	public void saveOrUpdate(Object entity) {
		getHibernateSession().saveOrUpdate(entity);
	}

	/**
	 * 删除持久化对象
	 * 
	 * @param entity
	 */
	@Override
	public void delete(Object entity) {

		getHibernateSession().delete(entity);
	}

	/**
	 * 根据主键id获取实体对象
	 * 
	 * @param id
	 *            实体对象主键
	 * @return 目标实体对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T findByID(Class<T> entity, Serializable id) {
		if (id != null) {
			return (T) getHibernateSession().get(entity, id);
			/*
			 * .load(entity, id)
			 */// 如果数据库中没有对应的记录存在，应用get方法时，将返回null，而应用load方法时，将抛出异常.
		} else {
			return null;
		}
	}

	/**
	 * 根据ID删除对象.
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param id
	 */
	@Override
	public <T> void deleteByID(Class<T> entity, Serializable id) {
		Object obj = findByID(entity, id);
		if (obj != null) {
			delete(obj);
		}
	}

	/**
	 * 获取全部对象,带排序字段与升降序参数.
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            升序还是降序，true：升序 false：降序
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(Class<T> entityClass, String orderBy, boolean isAsc) {
		if (orderBy != null && orderBy.trim().length() > 0) {
			Assert.hasText(orderBy);
			if (isAsc)
				return this.findByCriteria(DetachedCriteria.forClass(entityClass).addOrder(Order.asc(orderBy)));
			else
				return this.findByCriteria(DetachedCriteria.forClass(entityClass).addOrder(Order.desc(orderBy)));
		} else {
			return this.findByCriteria(DetachedCriteria.forClass(entityClass));
		}
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = getHibernateSession().createQuery(hql);
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++)
				query.setParameter(i, values[i]);
		}
		return query;
	}

	@Override
	public Query createQuery(String hql) {
		return createQuery(hql, new Object[0]);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Query createQuery(Class clazz) {
		return createQuery("from " + clazz.getName());
	}

	@Override
	public <T> Criteria createCriteria(Class<T> entity, Criterion... criterions) {
		Criteria criteria = getHibernateSession().createCriteria(entity);
		if (criterions != null && criterions.length > 0) {
			for (Criterion c : criterions)
				criteria.add(c);
		}
		return criteria;
	}

	@Override
	public <T> Criteria createCriteria(Class<T> entityClass, String orderBy, boolean isAsc, Criterion... criterions) {
		Assert.hasText(orderBy);

		Criteria criteria = createCriteria(entityClass, criterions);

		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));

		return criteria;
	}

	/**
	 * 根据hql查询,直接使用HibernateTemplate的find函数.
	 * 
	 * @param values
	 *            可变参数,见{@link #createQuery(String,Object...)}
	 * @param hql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List find(String queryString) throws DataAccessException {
		return find(queryString, (Object[]) null);
	}

	@SuppressWarnings("rawtypes")
	public List find(String queryString, Object value) throws DataAccessException {
		return find(queryString, new Object[]{value});
	}

	@Override
	public Integer count(final String queryString, final Object... values) throws DataAccessException {
		Query queryObject = getHibernateSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		
		Integer count = ((Long) queryObject.iterate().next()).intValue();
		return count;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find(final String queryString, final Object... values) throws DataAccessException {
		Query queryObject = getHibernateSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findBy(Class<T> entity, String propertyName, Object value) {
		Assert.hasText(propertyName);
		return createCriteria(entity, Restrictions.eq(propertyName, value)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findBy(Class<T> entity, String propertyName, Object value, String orderBy, boolean isAsc) {
		Assert.hasText(propertyName);
		Assert.hasText(orderBy);
		return createCriteria(entity, orderBy, isAsc, Restrictions.eq(propertyName, value)).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByPropertes(Class<T> entity, String propertyName, Object[] values) {
		Assert.hasText(propertyName);
		return createCriteria(entity, Restrictions.in(propertyName, values)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findUniqueBy(Class<T> entityClass, String propertyName, Object value) {
		Assert.hasText(propertyName);

		Object result = createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
		return result == null ? null : (T) result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findByCriteria(DetachedCriteria criteria) throws DataAccessException {
		return findByCriteria(criteria, -1, -1);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findByCriteria(final DetachedCriteria criteria, final int firstResult, final int maxResults) throws DataAccessException {

		Assert.notNull(criteria, "DetachedCriteria must not be null");
		Criteria executableCriteria = criteria.getExecutableCriteria(this.getHibernateSession());
		if (firstResult >= 0) {
			executableCriteria.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			executableCriteria.setMaxResults(maxResults);
		}
		return executableCriteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findBySqlQuery(String sql, Class<T> entityClass) {
		return getHibernateSession().createSQLQuery(sql).addEntity(entityClass).list();
	}

	@Override
	public <T> int countAll(Class<T> entity) {
		String countQueryString = " select count (1) from " + entity.getName();
		List countlist = find(countQueryString);
		int totalCount = Integer.parseInt(((Long) countlist.get(0)).toString());
		return totalCount;
	}
	
	@Override
	public void flush() {
		getHibernateSession().flush();
	}

	@Override
	public int getTotalByCriteria(DetachedCriteria criteria) {
		Assert.notNull(criteria, "DetachedCriteria must not be null");
		Criteria executableCriteria = criteria.getExecutableCriteria(this.getHibernateSession());
		executableCriteria.setProjection(Projections.rowCount());
		return Integer.parseInt(executableCriteria.uniqueResult().toString());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void batchSave(List entitys) {

		Session session = getHibernateSession();

		/**
		 * Hibernate4后无需写 session.beginTransaction() transaction.commit();
		 */
		// Transaction transaction = session.beginTransaction();

		for (int index = 0; index < entitys.size(); index++) {

			session.save(entitys.get(index));

			/**
			 * 25的阀值需与<prop key="hibernate.jdbc.batch_size">25</prop>的设置一致
			 */
			if (index % 25 == 0) {
				/**
				 * 立即入库清除内存
				 */
				session.flush();
				session.clear();
			}
		}

		// transaction.commit();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void batchUpdate(List entitys) {

		Session session = getHibernateSession();

		/**
		 * Hibernate4后无需写 session.beginTransaction() transaction.commit();
		 */
		// Transaction transaction = session.beginTransaction();

		for (int index = 0; index < entitys.size(); index++) {

			session.update(entitys.get(index));

			/**
			 * 25的阀值需与<prop key="hibernate.jdbc.batch_size">25</prop>的设置一致
			 */
			if (index % 25 == 0) {
				/**
				 * 立即入库清除内存
				 */
				session.flush();
				session.clear();
			}
		}

		// transaction.commit();
	}
	
	@Override
	public Pager pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");

		// 查询总记录数
		String countQueryString = "select count(*) " + removeSelect(removeOrders(hql));
		List countlist = find(countQueryString, values);
		int totalCount = Integer.parseInt(((Long) countlist.get(0)).toString());

		Pager pager = new Pager(pageNo, pageSize, totalCount);
		int startIndex = pager.getStartIndex();

		Query query = createQuery(hql, values);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		// System.out.println("baseListSize==="+list.size());
		pager.setResultList(list);

		return pager;
	}

	@Override
	public Pager pagedQueryWithStartIndex(String hql, int startIndex, int limit, Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(startIndex >= 0, "startIndex should start from 0");

		// 查询总记录数
		String countQueryString = " select count (1) " + removeSelect(removeOrders(hql));
		List countlist = find(countQueryString, values);
		int totalCount = Integer.parseInt(((Long) countlist.get(0)).toString());
		int pageNo = (startIndex / limit) + 1;
		Pager pager = new Pager(pageNo, limit, totalCount);

		Query query = createQuery(hql, values);
		List list = query.setFirstResult(startIndex).setMaxResults(limit).list();
		// System.out.println("baseListSize==="+list.size());
		pager.setResultList(list);

		return pager;
	}
	
	@Override
	public List queryWithStartIndex(String hql, int startIndex, int limit, Object... values) {
		Query query = createQuery(hql, values);
		List list = query.setFirstResult(startIndex).setMaxResults(limit).list();
		return list;
	}
	
	@Override
	public int executeUpdate(String hql, Object... values) {
		Query query = getHibernateSession().createQuery(hql);
		if(values != null){
			for(int i = 0; i < values.length; i ++){
				query.setParameter(i, values[i]);
			}
		}
		return query.executeUpdate();
	}
	
	/**
	 * 去除hql的order by子句，用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 * @param hql
	 * @return
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 * @param hql
	 * @return
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}
}
