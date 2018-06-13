package com.sky.business.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.system.dao.ProhistoryDao;
import com.sky.util.CommonMethodUtil;

/**
 * 收藏夹/历史记录Dao实现类
 * @author Sky James
 *
 */
@Service("prohistoryDao")
public class ProhistoryDaoImpl extends BaseDaoImpl implements ProhistoryDao {

	@Override
	public StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "updateTime desc";
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//类型，收藏夹类型/历史记录类型
		if(condition.containsKey("type") && StringUtils.isNotBlank((String)condition.get("type"))){
			Integer type = CommonMethodUtil.getIntegerByObject(condition.get("type"));
			hqlBuffer.append(" and type = ? ");
			values.add(type);
		}
		
		//对象ID
		if(condition.containsKey("objId") && StringUtils.isNotBlank((String)condition.get("objId"))){
			String objId = (String)condition.get("objId");
			hqlBuffer.append(" and objId = ? ");
			values.add(objId);
		}
		
		//用户ID
		if(condition.containsKey("userId") && StringUtils.isNotBlank((String)condition.get("userId"))){
			String userId = (String)condition.get("userId");
			hqlBuffer.append(" and userId = ? ");
			values.add(userId);
		}
		
		//表名，店铺/商品
		if(condition.containsKey("tableName") && StringUtils.isNotBlank((String)condition.get("tableName"))){
			String tableName = (String)condition.get("tableName");
			hqlBuffer.append(" and tableName = ? ");
			values.add(tableName);
		}
		
		hqlBuffer.append(" order by ").append(sort);
		
		return hqlBuffer;
	}

}
