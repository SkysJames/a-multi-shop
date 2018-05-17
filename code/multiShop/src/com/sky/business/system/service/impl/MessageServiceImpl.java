package com.sky.business.system.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.dao.MessageDao;
import com.sky.business.system.entity.Message;
import com.sky.business.system.service.MessageService;
import com.sky.contants.CodeMescContants;
import com.sky.util.CommonMethodUtil;

/**
 * 消息Service类
 * @author Sky James
 *
 */
@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl implements MessageService {

	@Resource(name = "messageDao")
	private MessageDao messageDao;
	
	@Override
	public void edit(Map<String,Object> editObj) throws Exception {
		//查找数据库中是否存在该消息
		Message message = this.findByID(Message.class, (String)editObj.get("id"));
		if(message == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editObj.containsKey("content")){
			message.setContent((String)editObj.get("content"));
		}
		if(editObj.containsKey("status")){
			message.setStatus(CommonMethodUtil.getIntegerByObject(editObj.get("status")));
		}
		
		this.update(message);
	}
	
	@Override
	public Message add(Map<String,Object> addObj) throws Exception {
		Message message = new Message();
		message.setId(UUID.randomUUID().toString());
		
		if(addObj.containsKey("fromUser")){
			message.setFromUser((String)addObj.get("fromUser"));
		}
		if(addObj.containsKey("toUser")){
			message.setToUser((String)addObj.get("toUser"));
		}
		if(addObj.containsKey("content")){
			message.setContent((String)addObj.get("content"));
		}
		if(addObj.containsKey("status")){
			message.setStatus(CommonMethodUtil.getIntegerByObject(addObj.get("status")));
		}
		
		message.setSendTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		this.save(message);
		return message;
		
	}
	
	@Override
	public void delete(String id) throws Exception {
		//查找数据库中是否存在，不存在则删除失败
		Message message = this.findByID(Message.class, id);
		if(message == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		this.delete(message);
	}
	
}
