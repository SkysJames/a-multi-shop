package com.sky.business.system.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.common.vo.Pager;
import com.sky.business.system.dao.DepartmentDao;

/**
 * 部门Dao实现类
 * @author Sky James
 *
 */
@Service("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl implements DepartmentDao {

	@Override
	protected StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "name asc";
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//关键字
		if(condition.containsKey("keywords") && StringUtils.isNotBlank((String)condition.get("keywords"))){
			String keywords = (String)condition.get("keywords");
			hqlBuffer.append(" and (name like ? or remark like ?) ");
			values.add("%" + keywords + "%");
			values.add("%" + keywords + "%");
		}
		
		hqlBuffer.append(" order by ").append(sort);
		
		return hqlBuffer;
	}
	
	@Override
	public Pager pagedList(final Map<String, Object> condition, int pageNo, int pageSize) {
		StringBuffer hqlBuffer = new StringBuffer("from Department where 1=1");
		List<Object> values = new ArrayList<Object>();
		
		//封装hql语句
		hqlBuffer = this.getPackageHql(hqlBuffer, values, condition);
		
		log.info("部门信息，分页查询hql：" + hqlBuffer.toString() + ", 条件：" + values);
		
		Pager pager = this.getBaseHibernateDao().pagedQuery(hqlBuffer.toString(), pageNo, pageSize, values.toArray());
		
		return pager;
	}

}
