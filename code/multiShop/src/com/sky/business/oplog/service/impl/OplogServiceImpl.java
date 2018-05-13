package com.sky.business.oplog.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.Pager;
import com.sky.business.oplog.dao.OplogDao;
import com.sky.business.oplog.service.OplogService;
import com.sky.util.CommonMethodUtil;

/**
 * 操作日志Service类
 * @author Sky James
 *
 */
@Service("oplogService")
public class OplogServiceImpl extends BaseServiceImpl implements OplogService {

	@Resource(name = "oplogDao")
	private OplogDao oplogDao;
	
	@Override
	public Pager pagedList(Map<String, Object> condition) throws Exception {
		Integer pageNo = Pager.DEFAULT_CURRENT_PAGE;
		Integer pageSize = Pager.DEFAULT_PAGE_SIZE;
		
		if(condition.containsKey("pageNo") && condition.containsKey("pageSize")) {
			pageNo = CommonMethodUtil.getIntegerByObject(condition.get("pageNo"));
			pageSize = CommonMethodUtil.getIntegerByObject(condition.get("pageSize"));
		}
		
		return oplogDao.pagedList(condition, pageNo, pageSize);
	}
	
}
