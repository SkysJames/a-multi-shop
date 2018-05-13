package com.sky.business.system.service.impl;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.Pager;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.dao.RightGroupDao;
import com.sky.business.system.entity.RightGroup;
import com.sky.business.system.service.RightGroupService;
import com.sky.contants.CodeMescContants;
import com.sky.util.CommonMethodUtil;

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
	public Pager pagedList(Map<String, Object> condition) throws Exception {
		Integer pageNo = Pager.DEFAULT_CURRENT_PAGE;
		Integer pageSize = Pager.DEFAULT_PAGE_SIZE;
		
		if(condition.containsKey("pageNo") && condition.containsKey("pageSize")) {
			pageNo = CommonMethodUtil.getIntegerByObject(condition.get("pageNo"));
			pageSize = CommonMethodUtil.getIntegerByObject(condition.get("pageSize"));
		}
		
		return rightGroupDao.pagedList(condition, pageNo, pageSize);
	}

	@Override
	public void edit(Map<String,Object> editRightGroup) throws Exception {
		//查找数据库中是否存在该角色
		RightGroup rightGroup = this.findByID(RightGroup.class, (String)editRightGroup.get("id"));
		if(rightGroup == null){
			throw new ServiceException(CodeMescContants.CodeContants.RIGHTGROUP_INEXIST, CodeMescContants.MessageContants.RIGHTGROUP_INEXIST);
		}
		
		if(editRightGroup.containsKey("name")){
			rightGroup.setName((String)editRightGroup.get("name"));
		}
		if(editRightGroup.containsKey("rights")){
			rightGroup.setRights((String)editRightGroup.get("rights"));
		}
		
		this.update(rightGroup);
	}
	
	@Override
	public RightGroup add(Map<String,Object> rightGroup) throws Exception {
		RightGroup newRightGroup = new RightGroup();
		newRightGroup.setId(UUID.randomUUID().toString());
		
		if(rightGroup.containsKey("name")){
			newRightGroup.setName((String)rightGroup.get("name"));
		}
		if(rightGroup.containsKey("rights")){
			newRightGroup.setRights((String)rightGroup.get("rights"));
		}
		
		this.save(newRightGroup);
		return newRightGroup;
		
	}
	
	@Override
	public void delete(String rightGroupId) throws Exception {
		//查找数据库中是否存在该角色
		RightGroup rightGroup = this.findByID(RightGroup.class, rightGroupId);
		if(rightGroup == null){
			throw new ServiceException(CodeMescContants.CodeContants.RIGHTGROUP_INEXIST, CodeMescContants.MessageContants.RIGHTGROUP_INEXIST);
		}
		
		this.delete(rightGroup);
		
	}

}
