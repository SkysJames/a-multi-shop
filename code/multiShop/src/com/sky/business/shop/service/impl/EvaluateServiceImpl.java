package com.sky.business.shop.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.shop.dao.EvaluateDao;
import com.sky.business.shop.entity.Evaluate;
import com.sky.business.shop.entity.Shop;
import com.sky.business.shop.service.EvaluateService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EvaluateContants;
import com.sky.contants.FileContants;
import com.sky.contants.TableContants;
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
	
	@SuppressWarnings("unchecked")
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
		
		this.update(evaluate);
	}
	
	@Override
	public Evaluate add(Map<String,Object> addObj) throws Exception {
		//判断是否可以对该对象添加评价
		if(!this.isAdd(addObj)) {
			return null;
		}
		
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
		
		evaluate.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		this.save(evaluate);
		
		if(evaluate.getStatus()!=null && evaluate.getStatus()>EvaluateContants.Status.NOSEND) {
			//统计评分
			this.statMark(evaluate);
		}
		
		return evaluate;
	}
	
	@Override
	public void statMark(Evaluate eval) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		String objId = eval.getObjId();
		String tableName = eval.getTableName();
		
		condition.put("clientStatus", EvaluateContants.Status.NOSEND.toString());//已发送状态的评价
		condition.put("objId", objId);
		condition.put("tableName", tableName);
		
		if(TableContants.TABLE_SHOP.equals(tableName) && StringUtils.isNotBlank(objId)) {
			Shop shop = this.findByID(Shop.class, objId);
			
			if(shop == null) {
				throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, "店铺评分统计" + CodeMescContants.MessageContants.ERROR_INEXIST);
			}
			
			List<Evaluate> evaluateList = evaluateDao.getList(evaluateDao, Evaluate.class, condition);
			Integer length = evaluateList.size();
			BigDecimal allMark = new BigDecimal(0);
			for(Evaluate e : evaluateList) {
				allMark = allMark.add(e.getMark());
			}
			//统计的平均评价
			BigDecimal avMark = allMark.divide(new BigDecimal(length), 1, BigDecimal.ROUND_HALF_UP);
			
			shop.setMark(avMark);
			this.save(shop);
		}
		
	}
	
	@Override
	public void delete(String id) throws Exception {
		//查找数据库中是否存在
		Evaluate evaluate = this.findByID(Evaluate.class, id);
		if(evaluate == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		this.delete(evaluate);
		
		if(evaluate.getStatus()!=null && evaluate.getStatus()>EvaluateContants.Status.NOSEND) {
			//统计评分
			this.statMark(evaluate);
		}
	}
	
	/**
	 * 判断是否可以添加
	 * @param addObj
	 * @return
	 */
	private boolean isAdd(Map<String,Object> addObj) throws Exception {
		if(!addObj.containsKey("userId")){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_COMMON, "添加店铺评分：传入的用户ID不能为空");
		}
		if(!addObj.containsKey("objId")){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_COMMON, "添加店铺评分：传入的对象ID不能为空");
		}
		if(!addObj.containsKey("tableName")){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_COMMON, "添加店铺评分：传入的表名不能为空");
		}
		
		String userId = (String) addObj.get("userId");
		String objId = (String) addObj.get("objId");
		String tableName = (String) addObj.get("tableName");
		
		if(evaluateDao.isEvaluated(userId, objId, tableName)) {
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_COMMON, "每个用户每天只能对同一家店铺评价一次");
		}
		
		return true;
	}

}
