package com.sky.business.bbstopic.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.bbstopic.dao.BbsSectionDao;
import com.sky.business.bbstopic.entity.Bbssection;
import com.sky.business.bbstopic.service.BbsTopicService;
import com.sky.business.bbstopic.service.BbsSectionService;
import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.message.dao.MessageDao;
import com.sky.business.message.entity.Message;
import com.sky.contants.CodeMescContants;
import com.sky.util.CommonMethodUtil;

@Service("bbsSectionService")
public class BbsSectionServiceImpl extends BaseServiceImpl implements BbsSectionService{
	
	@Resource(name = "bbsSectionDao")
	private BbsSectionDao bbsSectionDao;
	
	@Override
	public void edit(Map<String,Object> editObj) throws Exception {
		//查找数据库中是否存在该消息
		Bbssection section = this.findByID(Bbssection.class, (String)editObj.get("id"));
		if(section == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editObj.containsKey("name")){
			section.setName((String)editObj.get("name"));
		}
		if(editObj.containsKey("brief")){
			section.setBrief((String)editObj.get("brief"));
		}
		if(editObj.containsKey("headpic")){
			section.setHeadpic((String)editObj.get("headpic"));
		}
		
		this.update(section);
	}
	
	@Override
	public Bbssection add(Map<String,Object> addObj) throws Exception {
		Bbssection section = new Bbssection();
		section.setId(UUID.randomUUID().toString());
		
		if(addObj.containsKey("name")){
			section.setName((String)addObj.get("name"));
		}
		if(addObj.containsKey("brief")){
			section.setBrief((String)addObj.get("brief"));
		}
		if(addObj.containsKey("headpic")){
			section.setHeadpic((String)addObj.get("headpic"));
		}
		
		section.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		this.save(section);
		return section;
		
	}
	
	@Override
	public void delete(String id) throws Exception {
		//查找数据库中是否存在，不存在则删除失败
		Bbssection section = this.findByID(Bbssection.class, id);
		if(section == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		this.delete(section);
	}
}
