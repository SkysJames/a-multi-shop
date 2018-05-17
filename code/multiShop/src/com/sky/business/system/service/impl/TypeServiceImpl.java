package com.sky.business.system.service.impl;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.dao.TypeDao;
import com.sky.business.system.entity.Typet;
import com.sky.business.system.service.TypeService;
import com.sky.contants.CodeMescContants;
import com.sky.util.CommonMethodUtil;

/**
 * 类型Service类
 * @author Sky James
 *
 */
@Service("typeService")
public class TypeServiceImpl extends BaseServiceImpl implements TypeService {

	@Resource(name = "typeDao")
	private TypeDao typeDao;
	
	@Override
	public void edit(Map<String,Object> editObj) throws Exception {
		//查找数据库中是否存在该角色
		Typet typet = this.findByID(Typet.class, (String)editObj.get("id"));
		if(typet == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editObj.containsKey("name")){
			typet.setName((String)editObj.get("name"));
		}
		if(editObj.containsKey("parentId")){
			typet.setParentId((String)editObj.get("parentId"));
		}
		if(editObj.containsKey("sort")){
			typet.setSort(CommonMethodUtil.getIntegerByObject(editObj.get("sort")));
		}
		
		this.update(typet);
	}
	
	@Override
	public Typet add(Map<String,Object> addObj) throws Exception {
		Typet typet = new Typet();
		typet.setId(UUID.randomUUID().toString());
		
		if(addObj.containsKey("tableName")){
			typet.setTableName((String)addObj.get("tableName"));
		}
		if(addObj.containsKey("name")){
			typet.setName((String)addObj.get("name"));
		}
		if(addObj.containsKey("parentId")){
			typet.setParentId((String)addObj.get("parentId"));
		}
		if(addObj.containsKey("sort")){
			typet.setSort(CommonMethodUtil.getIntegerByObject(addObj.get("sort")));
		}
		
		this.save(typet);
		return typet;
		
	}
	
	@Override
	public void delete(String id) throws Exception {
		//查找数据库中是否存在
		Typet typet = this.findByID(Typet.class, id);
		if(typet == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		//查看该类型下是否还有数据
		
		this.delete(typet);
		
	}
}
