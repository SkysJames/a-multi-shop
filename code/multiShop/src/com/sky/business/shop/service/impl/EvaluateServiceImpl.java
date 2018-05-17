package com.sky.business.shop.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.shop.dao.EvaluateDao;
import com.sky.business.shop.entity.Evaluate;
import com.sky.business.shop.service.EvaluateService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.FileContants;
import com.sky.util.CommonMethodUtil;

/**
 * 评价Service类
 * @author Sky James
 *
 */
@Service("evaluateService")
public class EvaluateServiceImpl extends BaseServiceImpl implements EvaluateService {

	@Resource(name = "evaluateDao")
	private EvaluateDao evaluateDao;
	
	@Override
	public void edit(Map<String,Object> editObj) throws Exception {
		//查找数据库中是否存在
		Evaluate evaluate = this.findByID(Evaluate.class, (String)editObj.get("id"));
		if(evaluate == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editObj.containsKey("userId")){
			evaluate.setUserId((String)editObj.get("userId"));
		}
		if(editObj.containsKey("objId")){
			evaluate.setObjId((String)editObj.get("objId"));
		}
		if(editObj.containsKey("tableName")){
			evaluate.setTableName((String)editObj.get("tableName"));
		}
		if(editObj.containsKey("mark")){
			evaluate.setMark(CommonMethodUtil.getBigDecimalByObject(editObj.get("mark")));
		}
		if(editObj.containsKey("content")){
			evaluate.setContent((String)editObj.get("content"));
		}
		if(editObj.containsKey("status")){
			evaluate.setStatus(CommonMethodUtil.getIntegerByObject(editObj.get("status")));
		}
		if(editObj.containsKey("picPathList")){
			//图片存放的目录
			String picPath = FileContants.EVALUATE_FILE + File.separator + evaluate.getId();
			//保存图片
			String picture = CommonMethodUtil.saveFiles((List<String>) editObj.get("picPathList"), picPath);
			
			evaluate.setPicture(picture);
		}
		
		evaluate.setCreatTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		this.update(evaluate);
	}
	
	@Override
	public Evaluate add(Map<String,Object> addObj) throws Exception {
		Evaluate evaluate = new Evaluate();
		evaluate.setId(UUID.randomUUID().toString());
		
		if(addObj.containsKey("userId")){
			evaluate.setUserId((String)addObj.get("userId"));
		}
		if(addObj.containsKey("objId")){
			evaluate.setObjId((String)addObj.get("objId"));
		}
		if(addObj.containsKey("tableName")){
			evaluate.setTableName((String)addObj.get("tableName"));
		}
		if(addObj.containsKey("mark")){
			evaluate.setMark(CommonMethodUtil.getBigDecimalByObject(addObj.get("mark")));
		}
		if(addObj.containsKey("content")){
			evaluate.setContent((String)addObj.get("content"));
		}
		if(addObj.containsKey("status")){
			evaluate.setStatus(CommonMethodUtil.getIntegerByObject(addObj.get("status")));
		}
		if(addObj.containsKey("picPathList")){
			//图片存放的目录
			String picPath = FileContants.EVALUATE_FILE + File.separator + evaluate.getId();
			//保存图片
			String picture = CommonMethodUtil.saveFiles((List<String>) addObj.get("picPathList"), picPath);
			
			evaluate.setPicture(picture);
		}
		
		evaluate.setCreatTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		this.save(evaluate);
		return evaluate;
		
	}
	
	@Override
	public void delete(String id) throws Exception {
		//查找数据库中是否存在
		Evaluate evaluate = this.findByID(Evaluate.class, id);
		if(evaluate == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		this.delete(evaluate);
	}

}
