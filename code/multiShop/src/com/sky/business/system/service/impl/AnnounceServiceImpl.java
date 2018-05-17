package com.sky.business.system.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.dao.AnnounceDao;
import com.sky.business.system.entity.Announce;
import com.sky.business.system.service.AnnounceService;
import com.sky.contants.CodeMescContants;
import com.sky.util.CommonMethodUtil;
import com.sky.util.DateUtil;

/**
 * 消息Service类
 * @author Sky James
 *
 */
@Service("announceService")
public class AnnounceServiceImpl extends BaseServiceImpl implements AnnounceService {

	@Resource(name = "announceDao")
	private AnnounceDao announceDao;
	
	@Override
	public void edit(Map<String,Object> editObj, LoginUser loginUser) throws Exception {
		//查找数据库中是否存在该消息
		Announce announce = this.findByID(Announce.class, (String)editObj.get("id"));
		if(announce == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editObj.containsKey("name")){
			announce.setName((String)editObj.get("name"));
		}
		if(editObj.containsKey("content")){
			announce.setContent((String)editObj.get("content"));
		}
		if(editObj.containsKey("status")){
			announce.setStatus(CommonMethodUtil.getIntegerByObject(editObj.get("status")));
		}
		if(editObj.containsKey("overTimeString")){
			String overTimeString = (String)editObj.get("overTimeString");
			announce.setOverTime(new Timestamp(DateUtil.convertStr2MilliTime(overTimeString)));
		}
		
		announce.setUpdateUser(loginUser.getUserId());
		announce.setUpdateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		this.update(announce);
	}
	
	@Override
	public Announce add(Map<String,Object> addObj, LoginUser loginUser) throws Exception {
		Announce announce = new Announce();
		announce.setId(UUID.randomUUID().toString());
		
		if(addObj.containsKey("shopId")){
			announce.setShopId((String)addObj.get("shopId"));
		}
		if(addObj.containsKey("name")){
			announce.setName((String)addObj.get("name"));
		}
		if(addObj.containsKey("content")){
			announce.setContent((String)addObj.get("content"));
		}
		if(addObj.containsKey("status")){
			announce.setStatus(CommonMethodUtil.getIntegerByObject(addObj.get("status")));
		}
		if(addObj.containsKey("overTimeString")){
			String overTimeString = (String)addObj.get("overTimeString");
			announce.setOverTime(new Timestamp(DateUtil.convertStr2MilliTime(overTimeString)));
		}
		
		Timestamp nowstamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
		announce.setCreateUser(loginUser.getUserId());
		announce.setCreateTime(nowstamp);
		announce.setUpdateUser(loginUser.getUserId());
		announce.setUpdateTime(nowstamp);
		
		this.save(announce);
		return announce;
		
	}
	
	@Override
	public void delete(String id) throws Exception {
		//查找数据库中是否存在，不存在则删除失败
		Announce announce = this.findByID(Announce.class, id);
		if(announce == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		this.delete(announce);
	}
	
}
