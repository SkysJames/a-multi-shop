package com.sky.business.system.service;

import java.util.Map;

import com.sky.business.common.service.BaseService;
import com.sky.business.common.vo.Pager;
import com.sky.business.system.entity.Department;

/**
 * 部门Service接口
 * @author Sky James
 *
 */
public interface DepartmentService extends BaseService {
	
	/**
	 * 根据一定的条件获取部门列表
	 * @param condition
	 * @return
	 */
	public Pager pagedList(Map<String, Object> condition) throws Exception;
	
	/**
	 * 修改部门信息
	 * @param editUser
	 * @throws Exception
	 */
	public void edit(Map<String,Object> editDepartment) throws Exception;
	
	/**
	 * 添加部门
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public Department add(Map<String,Object> department) throws Exception;
	
	/**
	 * 删除部门
	 * @param departmentId
	 * @throws Exception
	 */
	public void delete(String departmentId) throws Exception;
	
}
