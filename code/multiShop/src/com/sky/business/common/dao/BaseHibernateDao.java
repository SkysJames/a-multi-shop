package com.sky.business.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sky.business.common.vo.Pager;

/**
 * 泛型DAO的基类.
 * <p/>
 * 继承于spring框架的HibernateDaoSupport，封装了常用的CRUD操作，并对返回值做了泛型类型转换。
 * 
 * @see HibernateDaoSupport
 */
public interface BaseHibernateDao {

	/**
	 * 创建持久化对象
	 * 
	 * @param entity
	 * @return
	 */
	public void save(Object entity);

	/**
	 * 批量持久化对象
	 * 
	 * @param entitys
	 */
	public void batchSave(List entitys);

	/**
	 * 持久化实体对象的变化
	 * 
	 * @param entity
	 */
	public void update(Object entity);
	
	/**
	 * 批量持久化实体对象的变化
	 * 
	 * @param entitys
	 */
	public void batchUpdate(List entitys);

	/**
	 * 创建持久化对象 持久化实体对象的变化
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(Object entity);

	/**
	 * 删除持久化对象
	 * 
	 * @param entity
	 */
	public void delete(Object entity);

	/**
	 * 根据ID删除对象.
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param id
	 */
	public <T> void deleteByID(Class<T> entity, Serializable id);

	/**
	 * 根据主键id获取实体对象
	 * 
	 * @param id
	 *            实体对象主键
	 * @return 目标实体对象
	 */
	public <T> T findByID(Class<T> entity, Serializable id);

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
	public <T> List<T> findAll(Class<T> entityClass, String orderBy, boolean isAsc);

	/**
	 * 创建Query对象.
	 * 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * 留意可以连续设置,如下：
	 * 
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 
	 * 调用方式如下：
	 * 
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 * 
	 * @param values
	 *            可变参数.
	 */
	public Query createQuery(String hql, Object... values);

	/**
	 * 创建Query对象
	 * 
	 * @param hql
	 *            hql
	 * @return
	 */
	public Query createQuery(String hql);

	/**
	 * 创建Query对象
	 * 
	 * @param clazz
	 * @return
	 */
	public Query createQuery(Class clazz);

	/**
	 * 创建Criteria对象.
	 * 
	 * @param criterions
	 *            可变的Restrictions条件列表,见{@link #createQuery(String,Object...)}
	 * @param <T>
	 * @param entity
	 * @param criterions
	 * @return
	 */
	public <T> Criteria createCriteria(Class<T> entity, Criterion... criterions);

	/**
	 * 创建Criteria对象，带排序字段与升降序字段.
	 * 
	 * @see #createCriteria(Class,Criterion[])
	 * @param <T>
	 * @param entityClass
	 * @param orderBy
	 * @param isAsc
	 * @param criterions
	 * @return
	 */
	public <T> Criteria createCriteria(Class<T> entityClass, String orderBy, boolean isAsc, Criterion... criterions);

	/**
	 * 根据hql查询,直接使用HibernateTemplate的find函数.
	 * 
	 * @param values
	 *            可变参数,见{@link #createQuery(String,Object...)}
	 * @param hql
	 * @param values
	 * @return
	 */
	public List find(String hql, Object... values);

	/**
	 * 根据属性名和属性值查询对象.
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return 符合条件的对象列表
	 */
	public <T> List<T> findBy(Class<T> entity, String propertyName, Object value);

	/**
	 * 根据属性名和属性值查询对象,带排序参数.
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	public <T> List<T> findBy(Class<T> entity, String propertyName, Object value, String orderBy, boolean isAsc);
	
	/**
	 * 根据多个同一字段的不同值查询对象
	 * @param entity
	 * @param propertyName
	 * @param values
	 * @return
	 */
	public <T> List<T> findByPropertes(Class<T> entity, String propertyName, Object[] values);

	/**
	 * 根据属性名和属性值查询唯一对象.
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return 符合条件的唯一对象 or null if not found.
	 */
	public <T> T findUniqueBy(Class<T> entityClass, String propertyName, Object value);

	/**
	 * 提供使用DetachedCriteria对象进行数据查询的方法 add by jiangyj 2013.5.31
	 * 
	 * @return List
	 */
	public List findByCriteria(DetachedCriteria criteria);

	/**
	 * 提交缓存
	 */
	public void flush();

	public List findByCriteria(final DetachedCriteria criteria, final int firstResult, final int maxResults);

	<T> List<T> findBySqlQuery(String sql, Class<T> entityClass);
	
	/**
	 * 统计所有数据量
	 * @param entity
	 * @return
	 */
	public <T> int countAll(Class<T> entity);

	public int getTotalByCriteria(DetachedCriteria criteria);
	
	/**
	 * 分页查询函数，使用hql.
	 * 
	 * @param hql
	 *            查询语句
	 * @param pageNo
	 *            页号,从1开始.
	 * @param pageSize
	 *            页容量
	 * @param values
	 *            查询参数
	 * @return 含当前页数据和翻页定位信息的Page对象
	 */
	public Pager pagedQuery(String hql, int pageNo, int pageSize, Object... values);
	
	/**
	 * 分页查询函数2，使用hql.
	 * 
	 * @param hql
	 *            查询语句
	 * @param 开始记录号
	 *            页号,从1开始.
	 * @param 返回记录数限制
	 *            页容量
	 * @param values
	 *            查询参数
	 * @return 含当前页数据和翻页定位信息的Page对象
	 */
	public Pager pagedQueryWithStartIndex(String hql, int startIndex, int limit, Object... values);
	
	/**
	 * 分页查询
	 * @param hql
	 * @param startIndex	开始序号
	 * @param limit			每页数量
	 * @param values
	 * @return
	 */
	public List queryWithStartIndex(String hql, int startIndex, int limit, Object... values);
	
	/**
	 * 执行hql语句操作数据
	 * @param hql
	 * @param values
	 * @return
	 */
	public int executeUpdate(String hql, Object...values);
}
