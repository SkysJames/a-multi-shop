package com.sky.business.system.service.impl;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.dao.RightGroupDao;
import com.sky.business.system.entity.RightGroup;
import com.sky.business.system.service.RightGroupService;
import com.sky.contants.CodeMescContants;

/**
 * 角色Service类
 * @author Sky James
 *
 */
@Service("rightGroupService")
public class RightGroupServiceImpl extends BaseServiceImpl implements RightGroupService {

	@Resource(name = "rightGroupDao")
	private RightGroupDao rightGroupDao;
	
	@Override
	public void edit(Map<String,Object> editObj) throws Exception {
		//查找数据库中是否存在该角色
		RightGroup rightGroup = this.findByID(RightGroup.class, (String)editObj.get("id"));
		if(rightGroup == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editObj.containsKey("name")){
			rightGroup.setName((String)editObj.get("name"));
		}
		if(editObj.containsKey("rights")){
			rightGroup.setRights((String)editObj.get("rights"));
		}
		
		this.update(rightGroup);
	}
	
	@Override
	public RightGroup add(Map<String,Object> addObj) throws Exception {
		RightGroup rightGroup = new RightGroup();
		rightGroup.setId(UUID.randomUUID().toString());
		
		if(addObj.containsKey("name")){
			rightGroup.setName((String)addObj.get("name"));
		}
		if(addObj.containsKey("rights")){
			rightGroup.setRights((String)addObj.get("rights"));
		}
		
		this.save(rightGroup);
		return rightGroup;
		
	}
	
	@Override
	public void delete(String id) throws Exception {
		//查找数据库中是否存在该角色
		RightGroup rightGroup = this.findByID(RightGroup.class, id);
		if(rightGroup == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		this.delete(rightGroup);
		
	}

}
