package com.sky.business.shop.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.shop.dao.CartDao;
import com.sky.util.CommonMethodUtil;

/**
 * 购物车Dao实现类
 * @author Sky James
 *
 */
@Service("cartDao")
public class CartDaoImpl extends BaseDaoImpl implements CartDao {

	@Override
	public StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "updateTime desc";
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//商品id
		if(condition.containsKey("productId") && StringUtils.isNotBlank((String)condition.get("productId"))){
			hqlBuffer.append(" and productId = ? ");
			values.add((String)condition.get("productId"));
		}
		
		//用户id
		if(condition.containsKey("userId") && StringUtils.isNotBlank((String)condition.get("userId"))){
			hqlBuffer.append(" and userId = ? ");
			values.add((String)condition.get("userId"));
		}
		
		//购物车状态
		if(condition.containsKey("status") && null==condition.get("status")){
			hqlBuffer.append(" and status = ? ");
			values.add(CommonMethodUtil.getIntegerByObject(condition.get("status")));
		}
		
		hqlBuffer.append(" order by ").append(sort);
		
		return hqlBuffer;
	}
	
}
