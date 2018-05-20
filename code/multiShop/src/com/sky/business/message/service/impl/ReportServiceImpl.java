package com.sky.business.message.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.message.dao.ReportDao;
import com.sky.business.message.entity.Report;
import com.sky.business.message.service.ReportService;
import com.sky.contants.CodeMescContants;
import com.sky.util.CommonMethodUtil;

/**
 * 举报Service类
 * @author Sky James
 *
 */
@Service("reportService")
public class ReportServiceImpl extends BaseServiceImpl implements ReportService {

	@Resource(name = "reportDao")
	private ReportDao reportDao;
	
	@Override
	public void edit(Map<String,Object> editObj) throws Exception {
		//查找数据库中是否存在该消息
		Report report = this.findByID(Report.class, (String)editObj.get("id"));
		if(report == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		if(editObj.containsKey("status")){
			report.setStatus(CommonMethodUtil.getIntegerByObject(editObj.get("status")));
		}
		
		this.update(report);
	}
	
	@Override
	public Report add(Map<String,Object> addObj) throws Exception {
		Report report = new Report();
		report.setId(UUID.randomUUID().toString());
		
		if(addObj.containsKey("objId")){
			report.setObjId((String)addObj.get("objId"));
		}
		if(addObj.containsKey("tableName")){
			report.setTableName((String)addObj.get("tableName"));
		}
		if(addObj.containsKey("userId")){
			report.setUserId((String)addObj.get("userId"));
		}
		if(addObj.containsKey("content")){
			report.setContent((String)addObj.get("content"));
		}
		if(addObj.containsKey("status")){
			report.setStatus(CommonMethodUtil.getIntegerByObject(addObj.get("status")));
		}
		
		report.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		this.save(report);
		return report;
		
	}
	
	@Override
	public void delete(String id) throws Exception {
		//查找数据库中是否存在，不存在则删除失败
		Report report = this.findByID(Report.class, id);
		if(report == null){
			throw new ServiceException(CodeMescContants.CodeContants.ERROR_INEXIST, CodeMescContants.MessageContants.ERROR_INEXIST);
		}
		
		this.delete(report);
	}
	
}
