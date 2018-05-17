package com.sky.business.system.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.dao.ProhistoryDao;
import com.sky.business.system.entity.Prohistory;
import com.sky.business.system.service.ProhistoryService;
import com.sky.contants.CodeMescContants;
import com.sky.util.CommonMethodUtil;

/**
 * 收藏夹/历史记录Service类
 * @author Sky James
 *
 */
@Service("prohistoryService")
public class ProhistoryServiceImpl extends BaseServiceImpl implements ProhistoryService {

	@Resource(name = "prohistoryDao")
	private ProhistoryDao prohistoryDao;
	
	@Override
	public void saveOrUpdate(Map<String,Object> obj) throws Exception {
		List list = this.getList(Prohistory.class, obj);
		Prohistory prohistory = null;
		Timestamp nowstamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		//查找数据库中是否存在
		if(list.isEmpty()){
			prohistory = new Prohistory();
			prohistory.setId(UUID.randomUUID().toString());
			prohistory.setCreateTime(nowstamp);
		}else {
			prohistory = (Prohistory)list.get(0);
		}
		
		if(obj.containsKey("type")){
			prohistory.setType(CommonMethodUtil.getIntegerByObject(obj.get("type")));
		}
		if(obj.containsKey("objId")){
			prohistory.setObjId((String)obj.get("objId"));
		}
		if(obj.containsKey("tableName")){
			prohistory.setTableName((String)obj.get("tableName"));
		}
		if(obj.containsKey("userId")){
			prohistory.setUserId((String)obj.get("userId"));
		}
		if(obj.containsKey("href")){
			prohistory.setHref((String)obj.get("href"));
		}
		
		prohistory.setUpdateTime(nowstamp);
		
		this.update(prohistory);
	}
	
	@Override
	public void delete(String id) throws Exception {
		//查找数据库中是否存在，不存在则删除失败
		Prohistory prohistory = this.findByID(Prohistory.class, id);
		if(prohistory == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		this.delete(prohistory);
	}
	
}
