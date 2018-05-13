package com.sky.business.product.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.common.vo.Pager;
import com.sky.business.product.dao.ProductDao;
import com.sky.util.CommonMethodUtil;
import com.sky.util.DateUtil;

/**
 * 产品Dao实现类
 * @author Sky James
 *
 */
@Service("productDao")
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {

	@Override
	protected StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "updateTime desc";
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//关键字
		if(condition.containsKey("keywords") && StringUtils.isNotBlank((String)condition.get("keywords"))){
			String keywords = (String)condition.get("keywords");
			hqlBuffer.append(" and (name like ? or description like ?) ");
			values.add("%" + keywords + "%");
			values.add("%" + keywords + "%");
		}
		
		//产品类型
		if(condition.containsKey("proType") && null!=condition.get("proType")){
			int proType = CommonMethodUtil.getIntegerByObject(condition.get("proType"));
			if(proType > -1) {
				hqlBuffer.append(" and proType = ? ");
				values.add(proType);
			}
		}

		//创建的用户
		if(condition.containsKey("createUser") && StringUtils.isNotBlank((String)condition.get("createUser"))){
			String createUser = (String) condition.get("createUser");
			hqlBuffer.append(" and createUser = ? ");
			values.add(createUser);
		}
		
		//更新的用户
		if(condition.containsKey("updateUser") && StringUtils.isNotBlank((String)condition.get("updateUser"))){
			String updateUser = (String) condition.get("updateUser");
			hqlBuffer.append(" and updateUser = ? ");
			values.add(updateUser);
		}
		
		//创建时间
		if(condition.containsKey("createTimeA") && StringUtils.isNotBlank((String)condition.get("createTimeA"))
				&& condition.containsKey("createTimeZ") && StringUtils.isNotBlank((String)condition.get("createTimeZ"))){
			Date createTimeA = DateUtil.convertStr2Date((String)condition.get("createTimeA"));
			Date createTimeZ = DateUtil.convertStr2Date((String)condition.get("createTimeZ"));
			hqlBuffer.append(" and createTime between ? and ? ");
			values.add(createTimeA);
			values.add(createTimeZ);
		}
		
		//更新时间
		if(condition.containsKey("updateTimeA") && StringUtils.isNotBlank((String)condition.get("updateTimeA"))
				&& condition.containsKey("updateTimeZ") && StringUtils.isNotBlank((String)condition.get("updateTimeZ"))){
			Date updateTimeA = DateUtil.convertStr2Date((String)condition.get("updateTimeA"));
			Date updateTimeZ = DateUtil.convertStr2Date((String)condition.get("updateTimeZ"));
			hqlBuffer.append(" and updateTime between ? and ? ");
			values.add(updateTimeA);
			values.add(updateTimeZ);
		}
		
		hqlBuffer.append(" order by ").append(sort);
		
		return hqlBuffer;
	}
	
	@Override
	public Pager pagedList(Map<String, Object> condition, int pageNo, int pageSize) throws Exception {
		StringBuffer hqlBuffer = new StringBuffer("from Product where 1=1");
		List<Object> values = new ArrayList<Object>();
		
		//封装hql语句
		hqlBuffer = this.getPackageHql(hqlBuffer, values, condition);
		
		log.info("产品数据，分页查询hql：" + hqlBuffer.toString() + ", 条件：" + values);
		
		Pager pager = this.getBaseHibernateDao().pagedQuery(hqlBuffer.toString(), pageNo, pageSize, values.toArray());
		
		return pager;
	}
	
}
