package com.sky.business.system.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.system.dao.AnnounceDao;
import com.sky.util.CommonMethodUtil;
import com.sky.util.DateUtil;

/**
 * 公告Dao实现类
 * @author Sky James
 *
 */
@Service("announceDao")
public class AnnounceDaoImpl extends BaseDaoImpl implements AnnounceDao {

	@Override
	protected StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "updateTime desc";
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//店铺ID
		if(condition.containsKey("shopId") && StringUtils.isNotBlank((String)condition.get("shopId"))){
			String shopId = (String)condition.get("shopId");
			hqlBuffer.append(" and shopId = ? ");
			values.add(shopId);
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
			hqlBuffer.append(" and (name like ? or content like ?) ");
			values.add("%" + keywords + "%");
			values.add("%" + keywords + "%");
		}
		
		//登录时间
		if(condition.containsKey("updateTimeA") && StringUtils.isNotBlank((String)condition.get("updateTimeA"))
				&& condition.containsKey("updateTimeZ") && StringUtils.isNotBlank((String)condition.get("updateTimeZ"))){
			Date updateTimeA = DateUtil.convertStr2Date((String)condition.get("updateTimeA"));
			Date updateTimeZ = DateUtil.convertStr2Date((String)condition.get("updateTimeZ"));
			hqlBuffer.append(" and updateTime between ? and ? ");
			values.add(updateTimeA);
			values.add(updateTimeZ);
		}
		
		//是否过期时间
		if(condition.containsKey("isOver") && null!=condition.get("isOver")){
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
