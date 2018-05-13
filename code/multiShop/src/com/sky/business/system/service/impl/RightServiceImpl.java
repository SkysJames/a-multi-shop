package com.sky.business.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.Pager;
import com.sky.business.system.dao.RightDao;
import com.sky.business.system.entity.Right;
import com.sky.business.system.service.RightService;
import com.sky.contants.EntityContants;
import com.sky.util.CommonMethodUtil;

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
	public Pager pagedList(Map<String, Object> condition) throws Exception {
		Integer pageNo = Pager.DEFAULT_CURRENT_PAGE;
		Integer pageSize = Pager.DEFAULT_PAGE_SIZE;
		
		if(condition.containsKey("pageNo") && condition.containsKey("pageSize")) {
			pageNo = CommonMethodUtil.getIntegerByObject(condition.get("pageNo"));
			pageSize = CommonMethodUtil.getIntegerByObject(condition.get("pageSize"));
		}
		
		return rightDao.pagedList(condition, pageNo, pageSize);
	}
	
	@Override
	public List<Right> getRightListByIds(String idStr) throws Exception {
		String[] rightIds = idStr.split(",");
		return this.findByPropertes(Right.class, "id", rightIds);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getTypeRightList() throws Exception {
		List<Right> allRight = findAll(Right.class, "id", true);
		List<Map<String, Object>> typeRightList = initTypeRightList();
		
		//遍历所有权限类型
		for(Map<String, Object> typeRight : typeRightList) {
			String typeId = (String) typeRight.get("id");
			List<Right> rightList = (List<Right>)typeRight.get("rightList");
			
			//遍历所有权限
			for(Right right : allRight) {
				if(typeId.equals(right.getType())) {
					rightList.add(right);
				}
			}
		}
		
		return typeRightList;
	}
	
	/**
	 * 初始化分类型的权限空列表
	 * @return
	 */
	private List<Map<String, Object>> initTypeRightList() {
		List<Map<String, Object>> typeRightList = new ArrayList<Map<String, Object>>();
		
		for(String type : EntityContants.RightContants.typeList) {
			Map<String, Object> typeRight = new HashMap<String, Object>();
			typeRight.put("id", type);
			typeRight.put("name", EntityContants.RightContants.typeMaps.get(type));
			typeRight.put("rightList", new ArrayList<Right>());
			typeRightList.add(typeRight);
		}
		
		return typeRightList;
	}

}
