package com.hjcrm.system.service;

import java.util.List;

import com.hjcrm.publics.util.PageBean;
import com.hjcrm.system.entity.Role;

/**
 * 角色管理接口
 * 
 * @author likang
 * @date 2018-1-20 上午9:06:04
 */
public interface IRoleService {

	/**
	 * 根据部门id查询角色
	 * 
	 * @Title queryRoleByDeptId
	 * @Description TODO(根据部门查询角色)
	 * @param deptid
	 * @return List<Role>
	 */
	public List<Role> queryRoleByDeptId(Long deptid);

	/**
	 * 查询所有角色
	 * 
	 * @Title queryAllRole
	 * @param pageBean
	 * @return List<Role>
	 */
	public List<Role> queryAllRole(PageBean pageBean);

	/**
	 * 更新和添加
	 * 
	 * @Title addOrUpdateRole
	 * @param role
	 * @return void
	 */
	public void addOrUpdateRole(Role role);
}