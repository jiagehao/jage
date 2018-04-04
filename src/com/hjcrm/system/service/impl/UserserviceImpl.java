package com.hjcrm.system.service.impl;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.MD5Tools;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Menu;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.IUserService;

@Service
public class UserserviceImpl implements IUserService {
	@Autowired
	private IDataAccess<User> userDao;
	@Autowired
	private IDataAccess<Menu> menuDao;

	public User queryUserByEmail(String email) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		List<User> list = userDao.queryByStatment("queryUserByEmail", params, null);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public boolean isEashPassword(String password, String email) {
		if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(password)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("email", email);
			params.put("password", MD5Tools.encode(password));
			List<User> list = userDao.queryByStatment("isEashPasswordAndEmail", params, null);
			if (list != null && list.size() > 0) {
				return true;
			}
			return false;
		}
		return false;
	}

	public List<Menu> queryAllMenuByRoleId(Long roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleid", roleId);
		params.put("queryparent", "true");// 是一个特殊标志，标志sql语句是查询一级菜单还是查询二级菜单，
											// 如果不为空，则代表只查询一级菜单，如果为空，则代表只查询二级菜单
		// 1:只查询一级菜单
		List<Menu> list = menuDao.queryByStatment("queryAllMenuByRoleId", params, null);
		// 2:循环一级菜单，查询一级菜单所有的二级菜单
		for (int i = 0; i < list.size(); i++) {
			Long menuId = list.get(i).getMenuid();// 一级菜单的ID，----将此ID，当做下一条sql的父ID
			params.clear();
			params.put("menuparaid", menuId);
			params.put("roleid", roleId);
			params.put("queryparent", null);
			List<Menu> list1 = menuDao.queryByStatment("queryAllMenuByRoleId", params, null);
			list.get(i).setChildren(list1);
		}
		return list;
	}

	public List<User> queryAllUsers(PageBean pageBean) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		List list = userDao.queryByStatment("queryUserAll", param, pageBean);
		return list;
	}
	
	public void saveOrUpdate(User user) {
		if (user != null) {
			if (user.getUserid() != null) {// 修改
				user.setUpdate_id(UserContext.getLoginUser().getUserid());
				user.setUpdate_time(new Timestamp(System.currentTimeMillis()));
				userDao.update(user);
			} else {// 增加
				user.setCreate_id(UserContext.getLoginUser().getUserid());
				user.setCreate_time(new Timestamp(System.currentTimeMillis()));
				userDao.insert(user);
			}
		}

	}

	public void deleteUserByIds(String ids) {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(ids)) {
			userDao.deleteByIds(User.class, ids);
		}

	}

}
