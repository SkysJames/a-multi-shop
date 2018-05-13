package com.sky.business.visitor.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.Pager;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.visitor.dao.VisitorDao;
import com.sky.business.visitor.entity.Visitor;
import com.sky.business.visitor.service.VisitorService;
import com.sky.contants.CodeMescContants;
import com.sky.util.CommonMethodUtil;

/**
 * 访客Service类
 * @author Sky James
 *
 */
@Service("visitorService")
public class VisitorServiceImpl extends BaseServiceImpl implements VisitorService {

	@Resource(name = "visitorDao")
	private VisitorDao visitorDao;
	
	@Override
	public Pager pagedList(Map<String, Object> condition) throws Exception {
		Integer pageNo = Pager.DEFAULT_CURRENT_PAGE;
		Integer pageSize = Pager.DEFAULT_PAGE_SIZE;
		
		if(condition.containsKey("pageNo") && condition.containsKey("pageSize")) {
			pageNo = CommonMethodUtil.getIntegerByObject(condition.get("pageNo"));
			pageSize = CommonMethodUtil.getIntegerByObject(condition.get("pageSize"));
		}
		
		return visitorDao.pagedList(condition, pageNo, pageSize);
	}

	@Override
	public void edit(Map<String,Object> editVisitor) throws Exception {
		Visitor visitor = this.findByID(Visitor.class, (String)editVisitor.get("id"));
		if(visitor == null){
			throw new ServiceException(CodeMescContants.CodeContants.VISITOR_INEXIST, CodeMescContants.MessageContants.VISITOR_INEXIST);
		}
		
		if(editVisitor.containsKey("remark")){
			visitor.setRemark((String)editVisitor.get("remark"));
		}
		if(editVisitor.containsKey("status")){
			visitor.setStatus(CommonMethodUtil.getIntegerByObject(editVisitor.get("status")));
		}
		
		this.update(visitor);
	}
	
	@Override
	public void delete(String id) throws Exception {
		Visitor visitor = this.findByID(Visitor.class, id);
		if(visitor == null){
			throw new ServiceException(CodeMescContants.CodeContants.VISITOR_INEXIST, CodeMescContants.MessageContants.VISITOR_INEXIST);
		}
		this.delete(visitor);
	}
	
}
