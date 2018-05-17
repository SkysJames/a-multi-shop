package com.sky.business.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.system.dao.TypeDao;

/**
 * 类型Dao实现类
 * @author Sky James
 *
 */
@Service("typeDao")
public class TypeDaoImpl extends BaseDaoImpl implements TypeDao {

	@Override
	protected StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "sort asc";
		
		//类型的表名
		if(condition.containsKey("tableName") && StringUtils.isNotBlank((String)condition.get("tableName"))){
			String tableName = (String)condition.get("tableName");
			hqlBuffer.append(" and tableName = ? ");
			values.add(tableName);
		}
		
		//父类型ID
		if(condition.containsKey("parentId") && StringUtils.isNotBlank((String)condition.get("parentId"))){
			String parentId = (String)condition.get("parentId");
			hqlBuffer.append(" and parentId = ? ");
			values.add(parentId);
		}
		
		//关键字
		if(condition.containsKey("keywords") && StringUtils.isNotBlank((String)condition.get("keywords"))){
			String keywords = (String)condition.get("keywords");
			hqlBuffer.append(" and (name like ?) ");
			values.add("%" + keywords + "%");
		}
		
		hqlBuffer.append(" order by ").append(sort);
		
		return hqlBuffer;
	}

}
