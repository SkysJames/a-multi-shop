package com.sky.business.system.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.system.dao.MessageDao;
import com.sky.util.CommonMethodUtil;
import com.sky.util.DateUtil;

/**
 * 消息Dao实现类
 * @author Sky James
 *
 */
@Service("messageDao")
public class MessageDaoImpl extends BaseDaoImpl implements MessageDao {

	@Override
	public StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "sendTime desc";
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//发送用户ID
		if(condition.containsKey("fromUser") && StringUtils.isNotBlank((String)condition.get("fromUser"))){
			String fromUser = (String)condition.get("fromUser");
			hqlBuffer.append(" and fromUser = ? ");
			values.add(fromUser);
		}
		
		//接收用户ID
		if(condition.containsKey("toUser") && StringUtils.isNotBlank((String)condition.get("toUser"))){
			String toUser = (String)condition.get("toUser");
			hqlBuffer.append(" and toUser = ? ");
			values.add(toUser);
		}
		
		//消息状态
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
		
		//登录时间
		if(condition.containsKey("sendTimeA") && StringUtils.isNotBlank((String)condition.get("sendTimeA"))
				&& condition.containsKey("sendTimeZ") && StringUtils.isNotBlank((String)condition.get("sendTimeZ"))){
			Date sendTimeA = DateUtil.convertStr2Date((String)condition.get("sendTimeA"));
			Date sendTimeZ = DateUtil.convertStr2Date((String)condition.get("sendTimeZ"));
			hqlBuffer.append(" and sendTime between ? and ? ");
			values.add(sendTimeA);
			values.add(sendTimeZ);
		}
		
		hqlBuffer.append(" order by ").append(sort);
		
		return hqlBuffer;
	}

}
