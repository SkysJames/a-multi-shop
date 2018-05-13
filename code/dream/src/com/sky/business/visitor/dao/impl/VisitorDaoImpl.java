package com.sky.business.visitor.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.common.vo.Pager;
import com.sky.business.visitor.dao.VisitorDao;
import com.sky.util.CommonMethodUtil;
import com.sky.util.DateUtil;

/**
 * 访客Dao实现类
 * @author Sky James
 *
 */
@Service(value="visitorDao")
public class VisitorDaoImpl extends BaseDaoImpl implements VisitorDao {

	@Override
	protected StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "visitedTime desc";
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//关键字
		if(condition.containsKey("keywords") && StringUtils.isNotBlank((String)condition.get("keywords"))){
			String keywords = (String)condition.get("keywords");
			hqlBuffer.append(" and (ip like ? or remark like ?) ");
			values.add("%" + keywords + "%");
			values.add("%" + keywords + "%");
		}
		
		//状态
		if(condition.containsKey("status") && null != condition.get("status")){
			int status = CommonMethodUtil.getIntegerByObject(condition.get("status"));
			hqlBuffer.append(" and status = ? ");
			values.add(status);
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
		
		//访问时间
		if(condition.containsKey("visitedTimeA") && StringUtils.isNotBlank((String)condition.get("visitedTimeA"))
				&& condition.containsKey("visitedTimeZ") && StringUtils.isNotBlank((String)condition.get("visitedTimeZ"))){
			Date visitedTimeA = DateUtil.convertStr2Date((String)condition.get("visitedTimeA"));
			Date visitedTimeZ = DateUtil.convertStr2Date((String)condition.get("visitedTimeZ"));
			hqlBuffer.append(" and visitedTime between ? and ? ");
			values.add(visitedTimeA);
			values.add(visitedTimeZ);
		}
		
		hqlBuffer.append(" order by ").append(sort);
		
		return hqlBuffer;
	}
	
	@Override
	public Pager pagedList(Map<String, Object> condition, int pageNo, int pageSize) throws Exception {
		StringBuffer hqlBuffer = new StringBuffer("from Visitor where 1=1");
		List<Object> values = new ArrayList<Object>();
		
		//封装hql语句
		hqlBuffer = this.getPackageHql(hqlBuffer, values, condition);
		
		log.info("访客数据，分页查询hql：" + hqlBuffer.toString() + ", 条件：" + values);
		
		Pager pager = this.getBaseHibernateDao().pagedQuery(hqlBuffer.toString(), pageNo, pageSize, values.toArray());
		
		return pager;
	}
}
