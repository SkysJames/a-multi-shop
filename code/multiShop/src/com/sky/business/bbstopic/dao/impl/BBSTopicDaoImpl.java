package com.sky.business.bbstopic.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.bbstopic.dao.BBSTopicDao;
import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.contants.TopicContants;
import com.sky.util.DateUtil;

@Service("bbsTopicDao")
public class BBSTopicDaoImpl extends BaseDaoImpl implements BBSTopicDao {
	
	@Override
	public StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		
		if(condition.containsKey("parentTopicId") && StringUtils.isNotBlank((String)condition.get("parentTopicId"))) {
			String parentTopicId = (String) condition.get("parentTopicId");
			hqlBuffer.append(" and topicId = ? ");
			hqlBuffer.append(" and topicType = ? ");
			hqlBuffer.append(" and status = ? ");
			values.add(parentTopicId);
			values.add(TopicContants.TOPIC_TYPE_REPLY);
			values.add(TopicContants.TOPIC_STATUS_RECEIVED);
			
		} else {
			
			hqlBuffer.append(" and topicType = ? ");
			hqlBuffer.append(" and status = ? ");
			values.add(TopicContants.TOPIC_TYPE_MAIN);
			values.add(TopicContants.TOPIC_STATUS_RECEIVED);
			
			if(condition.containsKey("keywords") && StringUtils.isNotBlank((String)condition.get("keywords"))){
				String keywords = (String)condition.get("keywords");
				hqlBuffer.append(" and (topicName like ?) ");
				values.add("%" + keywords + "%");
			}
			
			//发帖时间 
			if(condition.containsKey("updateTimeA") && StringUtils.isNotBlank((String)condition.get("updateTimeA"))
					&& condition.containsKey("updateTimeZ") && StringUtils.isNotBlank((String)condition.get("updateTimeZ"))){
				Date updateTimeA = DateUtil.convertStr2Date((String)condition.get("updateTimeA"));
				Date updateTimeZ = DateUtil.convertStr2Date((String)condition.get("updateTimeZ"));
				hqlBuffer.append(" and createTime between ? and ? ");
				values.add(updateTimeA);
				values.add(updateTimeZ);
			}
			
		}
		
		hqlBuffer.append(" order by ").append("createTime desc");
		return hqlBuffer;
		
	}
}
