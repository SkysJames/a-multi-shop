package com.sky.business.shop.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.shop.dao.ProductDao;
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
	public StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "updateTime desc";
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//店铺ID
		if(condition.containsKey("shopId") && StringUtils.isNotBlank((String)condition.get("shopId"))){
			String shopId = (String) condition.get("shopId");
			hqlBuffer.append(" and shopId = ? ");
			values.add(shopId);
		}
		
		//商品状态
		if(condition.containsKey("status") && StringUtils.isNotBlank((String)condition.get("status"))){
			hqlBuffer.append(" and status = ? ");
			values.add(CommonMethodUtil.getIntegerByObject(condition.get("status")));
		}
		
		//关键字
		if(condition.containsKey("keywords") && StringUtils.isNotBlank((String)condition.get("keywords"))){
			String keywords = (String)condition.get("keywords");
			hqlBuffer.append(" and (name like ? or description like ? or brief like ?) ");
			values.add("%" + keywords + "%");
			values.add("%" + keywords + "%");
			values.add("%" + keywords + "%");
		}
		
		//产品类型
		if(condition.containsKey("proType") && StringUtils.isNotBlank((String)condition.get("proType"))){
			String proType = (String)condition.get("proType");
			hqlBuffer.append(" and proType like ? ");
			values.add("%" + proType + "%");
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
	
}
