package com.sky.business.bbstopic.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.bbstopic.dao.BbsSectionDao;
import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.util.DateUtil;

@Service("bbsSectionDao")
public class BbsSectionDaoImpl extends BaseDaoImpl implements BbsSectionDao{
	
	@Override
	public StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		
		//关键字
		if(condition.containsKey("keywords") && StringUtils.isNotBlank((String)condition.get("keywords"))){
			String keywords = (String)condition.get("keywords");
			hqlBuffer.append(" and (name like ?) ");
			values.add("%" + keywords + "%");
		}
		
		//创建时间
		if(condition.containsKey("updateTimeA") && StringUtils.isNotBlank((String)condition.get("updateTimeA"))
				&& condition.containsKey("updateTimeZ") && StringUtils.isNotBlank((String)condition.get("updateTimeZ"))){
			Date updateTimeA = DateUtil.convertStr2Date((String)condition.get("updateTimeA"));
			Date updateTimeZ = DateUtil.convertStr2Date((String)condition.get("updateTimeZ"));
			hqlBuffer.append(" and createTime between ? and ? ");
			values.add(updateTimeA);
			values.add(updateTimeZ);
		}
		
		hqlBuffer.append(" order by ").append("createTime asc");
		return hqlBuffer;
		
	}
}
