package com.hjcrm.system.service;

import java.util.List;

import com.hjcrm.publics.util.PageBean;
import com.hjcrm.system.entity.Menu;

public interface IMenuService {
	/**
	 * 查询所有菜单
	 */
	public List<Menu> queryMenuAll(PageBean pageBean);

	/**
	 * 菜单的增加和修改
	 * 
	 * @param menu
	 */
	public void addAndUpdate(Menu menu);

	/**
	 * 删除菜单
	 * 
	 * @param menu
	 */
	/**
	 * 2018年4月3日
	 * void
	 * 
	 */
	public void deletMenu(String ids);

	/**
	 * 根据roleId查询菜单权限
	 * 
	 * @param menu
	 */
	public List<Menu> getMenuByROleId(Long roleid);
}
