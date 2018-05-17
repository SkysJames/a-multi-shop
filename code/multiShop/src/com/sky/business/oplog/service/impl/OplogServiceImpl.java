package com.sky.business.oplog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.oplog.dao.OplogDao;
import com.sky.business.oplog.service.OplogService;

/**
 * 操作日志Service类
 * @author Sky James
 *
 */
@Service("oplogService")
public class OplogServiceImpl extends BaseServiceImpl implements OplogService {

	@Resource(name = "oplogDao")
	private OplogDao oplogDao;
	
}
