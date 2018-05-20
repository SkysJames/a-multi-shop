package com.sky.business.common.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.BaseDao;
import com.sky.business.common.service.BaseService;
import com.sky.business.common.vo.Pager;
import com.sky.util.CommonMethodUtil;

@Service("baseService")
public class BaseServiceImpl implements BaseService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	@Override
	public void save(Object entity)  {
		baseDao.save(entity);
	}

	@Override
	public void update(Object entity) {
		baseDao.update(entity);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(Object entity) {
		baseDao.delete(entity);
	}

	@Override
	public <T> T findByID(Class<T> entity, Serializable id)  {
		if (id != null) {
			return (T) baseDao.findByID(entity, id);
		} else {
			return null;
		}
	}
	
	@Override
	public <T> T findByUnique(Class<T> entity, String propertyName, Object value){
		return baseDao.findByUnique(entity, propertyName, value);
	}
	
	@Override
	public <T> List<T> findByPropertes(Class<T> entity, String propertyName, Object[] values) {
		return baseDao.findByPropertes(entity, propertyName, values);
	}
	
	@Override
	public <T> List<T> findBy(Class<T> entity, String propertyName, Object value){
		return baseDao.findBy(entity, propertyName, value);
	}
	
	@Override
	public <T> List<T> findBy(Class<T> entity,  String propertyName, Object value, String orderBy, boolean isAsc) {
		return baseDao.findBy(entity, propertyName, value, orderBy, isAsc);
	}

	@Override
	public <T> List<T> findAll(Class<T> entity, String orderBy, boolean isAsc) {
		return baseDao.findAll(entity, orderBy, isAsc);
	}
	
	@Override
	public <T> int countAll(Class<T> entity) {
		return baseDao.countAll(entity);
	}

	@Override
	public void batchSave(List entitys) {
		baseDao.batchSave(entitys);
	}
	
	@Override
	public void batchUpdate(List entitys) {
		baseDao.batchUpdate(entitys);
	}

	@Override
	public List getPropertyByID(String table, String outputProperty, String inputProperty, Object inputValue) {
		if(null!=table && null!=outputProperty && null!=inputProperty && null!=inputValue){
			return baseDao.getPropertyByID(table, outputProperty, inputProperty, inputValue);
		}else{
			return null;
		}
	}
	
	@Override
	public <T> T getUnique(Class<T> entity, final Map<String, Object> condition) {
		return baseDao.getUnique(entity, condition);
	}
	
	@Override
	public <T> Pager pagedList(BaseDao dao, Class<T> entity, Map<String, Object> condition) {
		Integer pageNo = Pager.DEFAULT_CURRENT_PAGE;
		Integer pageSize = Pager.DEFAULT_PAGE_SIZE;
		
		if(condition.containsKey("pageNo") && condition.containsKey("pageSize")) {
			pageNo = CommonMethodUtil.getIntegerByObject(condition.get("pageNo"));
			pageSize = CommonMethodUtil.getIntegerByObject(condition.get("pageSize"));
		}
		
		return baseDao.pagedList(dao, entity, condition, pageNo, pageSize);
	}
	
	@Override
	public <T> List getList(BaseDao dao, Class<T> entity, Map<String, Object> condition) {
		return baseDao.getList(dao, entity, condition);
	}
	
	@Override
	public <T> Integer getCount(BaseDao dao, Class<T> entity, final Map<String, Object> condition) {
		return baseDao.getCount(dao, entity, condition);
	}

}
