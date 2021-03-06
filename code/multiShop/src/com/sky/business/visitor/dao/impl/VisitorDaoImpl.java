package com.sky.business.visitor.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
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
	public StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "visitedTime desc";
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//店铺ID
		if(condition.containsKey("shopId") && StringUtils.isNotBlank((String)condition.get("shopId"))){
			String shopId = (String)condition.get("shopId");
			hqlBuffer.append(" and shopId = ?");
			values.add(shopId);
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
	
}
