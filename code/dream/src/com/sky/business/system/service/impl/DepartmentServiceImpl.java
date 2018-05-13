package com.sky.business.system.service.impl;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.service.impl.BaseServiceImpl;
import com.sky.business.common.vo.Pager;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.system.dao.DepartmentDao;
import com.sky.business.system.entity.Department;
import com.sky.business.system.service.DepartmentService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.CommonMethodUtil;

/**
 * 部门Service类
 * @author Sky James
 *
 */
@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl implements DepartmentService {

	@Resource(name = "departmentDao")
	private DepartmentDao departmentDao;
	
	@Override
	public Pager pagedList(Map<String, Object> condition) throws Exception {
		Integer pageNo = Pager.DEFAULT_CURRENT_PAGE;
		Integer pageSize = Pager.DEFAULT_PAGE_SIZE;
		
		if(condition.containsKey("pageNo") && condition.containsKey("pageSize")) {
			pageNo = CommonMethodUtil.getIntegerByObject(condition.get("pageNo"));
			pageSize = CommonMethodUtil.getIntegerByObject(condition.get("pageSize"));
		}
		
		return departmentDao.pagedList(condition, pageNo, pageSize);
	}

	@Override
	public void edit(Map<String,Object> editDepartment) throws Exception {
		//查找数据库中是否存在该部门
		Department department = this.findByID(Department.class, (String)editDepartment.get("id"));
		if(department == null){
			throw new ServiceException(CodeMescContants.CodeContants.DEPARTMENT_INEXIST, CodeMescContants.MessageContants.DEPARTMENT_INEXIST);
		}
		
		if(editDepartment.containsKey("parentId")){
			department.setParentId((String)editDepartment.get("parentId"));
		}
		if(editDepartment.containsKey("name")){
			department.setName((String)editDepartment.get("name"));
		}
		if(editDepartment.containsKey("remark")){
			department.setRemark((String)editDepartment.get("remark"));
		}
		
		this.update(department);
	}
	
	@Override
	public Department add(Map<String,Object> department) throws Exception {
		Department newDepartment = new Department();
		newDepartment.setId(UUID.randomUUID().toString());
		newDepartment.setParentId(EntityContants.DepartmentContants.PARENT_DEFAULT);
		
		if(department.containsKey("name")){
			newDepartment.setName((String)department.get("name"));
		}
		if(department.containsKey("remark")){
			newDepartment.setRemark((String)department.get("remark"));
		}
		
		this.save(newDepartment);
		return newDepartment;
		
	}
	
	@Override
	public void delete(String departmentId) throws Exception {
		//system部门不能删除
		if(EntityContants.DepartmentContants.DEPARTMENT_SYSTEM.equals(departmentId)) {
			throw new ServiceException(CodeMescContants.CodeContants.DEPARTMENT_INIT, CodeMescContants.MessageContants.DEPARTMENT_INIT);
		}
		
		//查找数据库中是否存在该部门
		Department department = this.findByID(Department.class, departmentId);
		if(department == null){
			throw new ServiceException(CodeMescContants.CodeContants.DEPARTMENT_INEXIST, CodeMescContants.MessageContants.DEPARTMENT_INEXIST);
		}
		
		this.delete(department);
	}

}
