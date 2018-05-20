package com.sky.business.message.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.message.dao.ReportDao;
import com.sky.util.CommonMethodUtil;

/**
 * 举报Dao实现类
 * @author Sky James
 *
 */
@Service("reportDao")
public class ReportDaoImpl extends BaseDaoImpl implements ReportDao {

	@Override
	public StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "createTime desc";
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//对象ID
		if(condition.containsKey("objId") && StringUtils.isNotBlank((String)condition.get("objId"))){
			String objId = (String)condition.get("objId");
			hqlBuffer.append(" and objId = ? ");
			values.add(objId);
		}
		
		//表名
		if(condition.containsKey("tableName") && StringUtils.isNotBlank((String)condition.get("tableName"))){
			String tableName = (String)condition.get("tableName");
			hqlBuffer.append(" and tableName = ? ");
			values.add(tableName);
		}
		
		//用户id
		if(condition.containsKey("userId") && StringUtils.isNotBlank((String)condition.get("userId"))){
			String userId = (String)condition.get("userId");
			hqlBuffer.append(" and userId = ? ");
			values.add(userId);
		}
		
		//状态
		if(condition.containsKey("status") && null!=condition.get("status")){
			Integer status = CommonMethodUtil.getIntegerByObject(condition.get("status"));
			hqlBuffer.append(" and status = ? ");
			values.add(status);
		}
		
		//关键字
		if(condition.containsKey("keywords") && StringUtils.isNotBlank((String)condition.get("keywords"))){
			String keywords = (String)condition.get("keywords");
			hqlBuffer.append(" and (content like ?) ");
			values.add("%" + keywords + "%");
		}
		
		hqlBuffer.append(" order by ").append(sort);
		
		return hqlBuffer;
	}

}
