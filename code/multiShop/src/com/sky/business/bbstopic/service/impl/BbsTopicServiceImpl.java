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
			bbstopic.setTopicName((String)editObj.get("content"));
		}
		if(editObj.containsKey("replyCount")){//回复数
			bbstopic.setTopicName((String)editObj.get("replyCount"));
		}
		if(editObj.containsKey("clickCount")){//点击数
			bbstopic.setTopicName((String)editObj.get("clickCount"));
		}
		if(editObj.containsKey("likeNum")){//点赞数
			bbstopic.setTopicName((String)editObj.get("likeNum"));
		}
		this.update(bbstopic);
	}
	
	@Override
	public Bbstopic add(Map<String,Object> addObj) throws Exception {
		Bbstopic bbstopic = new Bbstopic();
		bbstopic.setId(UUID.randomUUID().toString());
		bbstopic.setClickCount(TopicContants.TOPIC_DEFAULT_NUM);
		bbstopic.setLikeNum(TopicContants.TOPIC_DEFAULT_NUM);
		bbstopic.setReplyCount(TopicContants.TOPIC_DEFAULT_NUM);
		bbstopic.setTopicType(TopicContants.TOPIC_TYPE_MAIN);
		bbstopic.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		bbstopic.setStatus(TopicContants.TOPIC_STATUS_RECEIVED);
		
		if(addObj.containsKey("sectionId")){//版块ID
			bbstopic.setSectionId((String)addObj.get("sectionId"));
		}
		if(addObj.containsKey("topicName")){//帖子标题
			bbstopic.setTopicName((String)addObj.get("topicName"));
		}
		if(addObj.containsKey("content")){//帖子内容
			bbstopic.setTopicName((String)addObj.get("content"));
		}
		if(addObj.containsKey("toUser")){//帖子内容
			bbstopic.setTopicMasterid((String)addObj.get("toUser"));
		}
		
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
