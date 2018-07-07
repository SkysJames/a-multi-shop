package com.sky.business.shop.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.shop.dao.ShopDao;
import com.sky.util.CommonMethodUtil;

/**
 * 店铺Dao实现类
 * @author Sky James
 *
 */
@Service("shopDao")
public class ShopDaoImpl extends BaseDaoImpl implements ShopDao {

	@Override
	public StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "popularity desc";
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//名称
		if(condition.containsKey("name") && StringUtils.isNotBlank((String)condition.get("name"))){
			hqlBuffer.append(" and name = ? ");
			values.add((String)condition.get("name"));
		}
		
		//是否推荐店铺
		if(condition.containsKey("recommend") && StringUtils.isNotBlank((String)condition.get("recommend"))){
			hqlBuffer.append(" and recommend = ? ");
			values.add(CommonMethodUtil.getIntegerByObject(condition.get("recommend")));
		}
		
		//比这个状态更大的
		if(condition.containsKey("moreStatus") && StringUtils.isNotBlank((String)condition.get("moreStatus"))){
			hqlBuffer.append(" and status > ? ");
			values.add(CommonMethodUtil.getIntegerByObject(condition.get("moreStatus")));
		}
		
		//店铺状态
		if(condition.containsKey("status") && StringUtils.isNotBlank((String)condition.get("status"))){
			hqlBuffer.append(" and status = ? ");
			values.add(CommonMethodUtil.getIntegerByObject(condition.get("status")));
		}
		
		//店铺类型
		if(condition.containsKey("shopType") && StringUtils.isNotBlank((String)condition.get("shopType"))){
			String shopType = (String)condition.get("shopType");
			hqlBuffer.append(" and shopType like ? ");
			values.add("%" + shopType + "%");
		}
		
		//关键字
		if(condition.containsKey("keywords") && StringUtils.isNotBlank((String)condition.get("keywords"))){
			String keywords = (String)condition.get("keywords");
			hqlBuffer.append(" and (name like ? or brief like ? or address like ?) ");
			values.add("%" + keywords + "%");
			values.add("%" + keywords + "%");
			values.add("%" + keywords + "%");
		}
		
		//是否过期时间
		if(condition.containsKey("isOver") && StringUtils.isNotBlank((String)condition.get("isOver"))){
			Integer isOver = CommonMethodUtil.getIntegerByObject(condition.get("isOver"));
			
			if(isOver > 0) {
				//过期
				hqlBuffer.append(" and overTime < now() ");
			}else {
				//未过期
				hqlBuffer.append(" and overTime > now() ");
			}
		}
		
		hqlBuffer.append(" order by ").append(sort);
		
		return hqlBuffer;
	}
	
}
