package com.sky.business.bbstopic.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.bbstopic.dao.BBSTopicDao;
import com.sky.business.bbstopic.dao.BbsSectionDao;
import com.sky.business.bbstopic.entity.Bbssection;
import com.sky.business.bbstopic.entity.Bbstopic;
import com.sky.business.bbstopic.service.BbsTopicService;
import com.sky.business.common.dao.BaseDao;
import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.Pager;
import com.sky.business.common.vo.ServiceException;
import com.sky.contants.CodeMescContants;
import com.sky.contants.TopicContants;

@Service("bbsTopicService")
public class BbsTopicServiceImpl extends BaseServiceImpl implements BbsTopicService{
	
	@Resource(name = "bbsTopicDao")
	private BBSTopicDao bbsTopicDao;
	
	@Override
	public void edit(Map<String,Object> editObj) throws Exception {
		//查找数据库中是否存在该消息
		Bbstopic bbstopic = this.findByID(Bbstopic.class, (String)editObj.get("id"));
		if(bbstopic == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editObj.containsKey("topicName")){//帖子标题
			bbstopic.setTopicName((String)editObj.get("topicName"));
		}
		if(editObj.containsKey("content")){//帖子内容
			bbstopic.setContent((String)editObj.get("content"));
		}
		this.update(bbstopic);
	}
	
	@Override
	public Bbstopic add(Map<String,Object> addObj) throws Exception {
		
		Bbstopic bbstopic = new Bbstopic();
		bbstopic.setId(UUID.randomUUID().toString());
		
		if(addObj.containsKey("sectionId")){//版块ID
			bbstopic.setSectionId((String)addObj.get("sectionId"));
		}
		
		if(addObj.containsKey("content")){//帖子内容
			bbstopic.setContent((String)addObj.get("content"));
		}
		
		if(addObj.containsKey("masterid")){//发帖人
			bbstopic.setMasterid((String)addObj.get("masterid"));
		}
		
		if(TopicContants.TOPIC_TYPE_MAIN == Integer.parseInt(addObj.get("topicType").toString())) {//主帖
			bbstopic.setTopicType(TopicContants.TOPIC_TYPE_MAIN);
			if(addObj.containsKey("topicName")){//帖子标题
				bbstopic.setTopicName((String)addObj.get("topicName"));
			}
			
		}else {
			bbstopic.setTopicType(TopicContants.TOPIC_TYPE_REPLY);
			if(addObj.containsKey("parentTopicId")){//父帖ID
				bbstopic.setTopicId((String)addObj.get("parentTopicId"));
			}
			
			if(addObj.containsKey("topicMasterid")){//父帖用户ID
				bbstopic.setTopicMasterid((String)addObj.get("topicMasterid"));
			}
		}
		bbstopic.setClickCount(TopicContants.TOPIC_DEFAULT_NUM);
		bbstopic.setLikeNum(TopicContants.TOPIC_DEFAULT_NUM);
		bbstopic.setReplyCount(TopicContants.TOPIC_DEFAULT_NUM);
		bbstopic.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		bbstopic.setStatus(TopicContants.TOPIC_STATUS_RECEIVED);
		
		this.save(bbstopic);
		return bbstopic;
		
	}
	
	@Override
	public void delete(String id) throws Exception {
		//查找数据库中是否存在，不存在则删除失败
		Bbstopic bbstopic = this.findByID(Bbstopic.class, id);
		if(bbstopic == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		this.delete(bbstopic);
	}
}
