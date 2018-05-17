package com.sky.business.shop.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.shop.dao.EvaluateDao;
import com.sky.util.CommonMethodUtil;

/**
 * 评价Dao实现类
 * @author Sky James
 *
 */
@Service("evaluateDao")
public class EvaluateDaoImpl extends BaseDaoImpl implements EvaluateDao {

	@Override
	public StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "createTime desc";
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//用户id
		if(condition.containsKey("userId") && StringUtils.isNotBlank((String)condition.get("userId"))){
			hqlBuffer.append(" and userId = ? ");
			values.add((String)condition.get("userId"));
		}
		
		//表名，如tb_shop
		if(condition.containsKey("tableName") && StringUtils.isNotBlank((String)condition.get("tableName"))){
			hqlBuffer.append(" and tableName = ? ");
			values.add((String)condition.get("tableName"));
		}
		
		//被评价对象
		if(condition.containsKey("objId") && StringUtils.isNotBlank((String)condition.get("objId"))){
			hqlBuffer.append(" and objId = ? ");
			values.add((String)condition.get("objId"));
		}
		
		//评分
		if(condition.containsKey("mark") && StringUtils.isNotBlank((String)condition.get("mark"))){
			hqlBuffer.append(" and mark = ? ");
			values.add((String)condition.get("mark"));
		}
		
		//评价状态
		if(condition.containsKey("status") && null==condition.get("status")){
			hqlBuffer.append(" and status = ? ");
			values.add(CommonMethodUtil.getIntegerByObject(condition.get("status")));
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
