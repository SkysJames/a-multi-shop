package com.sky.business.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.system.dao.RightDao;
import com.sky.business.system.entity.Right;
import com.sky.business.system.service.RightService;

/**
 * 权限Service类
 * @author Sky James
 *
 */
@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl implements RightService {

	@Resource(name = "rightDao")
	private RightDao rightDao;
	
	@Override
	public List<Right> getRightListByIds(String idStr) throws Exception {
		String[] rightIds = idStr.split(",");
		return this.findByPropertes(Right.class, "id", rightIds);
	}

}
