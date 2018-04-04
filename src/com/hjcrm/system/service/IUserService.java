package com.hjcrm.system.service;

import java.util.List;

import com.hjcrm.publics.util.PageBean;
import com.hjcrm.system.entity.Menu;
import com.hjcrm.system.entity.User;

public interface IUserService {
	/*******
	 * 根据邮箱名查询用户是否存在
	 */
	public User queryUserByEmail(String email);

	/**
	 * 查询邮箱和密码是否匹配
	 */
	public boolean isEashPassword(String password, String email);

	/**
	 * 查询权限菜单
	 */
	public List<Menu> queryAllMenuByRoleId(Long roleId);

	/**
	 * 查询所有用户信息，支持分页
	 * 
	 * @param pageBean
	 * @return
	 */
	public List<User> queryAllUsers(PageBean pageBean);

	/**
	 * 插入一条用户
	 * 
	 * @param pageBean
	 * @return
	 */
	public void saveOrUpdate(User user);

	/**
	 * 删除用户
	 * 
	 * @param pageBean
	 * @return
	 */
	public void deleteUserByIds(String ids);

}
