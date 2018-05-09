package com.hjcrm.system.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Dept;
import com.hjcrm.system.entity.Menu;
import com.hjcrm.system.entity.Role;
import com.hjcrm.system.service.IRoleService;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IDataAccess<Role> roleDao;

	@Autowired
	private IDataAccess<Menu> menuDao;
	@Autowired
	private IDataAccess<Dept> deptDao;

	public List<Role> queryRoleByDeptId(Long deptid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deptid", deptid);
		List<Role> list = roleDao.queryByStatment("queryRoleByDeptId", params, null);
		return list;
	}

	public List<Role> queryAllRole(PageBean pageBean) {
		Role model = new Role();
		Map<String, Object> param = new HashMap<String, Object>();
		List<Role> list = roleDao.query(model, pageBean);
		for (Role role : list) {
			param.put("roleid", role.getRoleid());
			List<Menu> list2 = menuDao.queryByStatment("queryMenuByRoleid", param, pageBean);
			String menus = "";
			for (Menu menu : list2) {
				menus += "," + menu.getMenuname();
			}
			role.setMenuname(menus.substring(1));
			Dept dept = (Dept) deptDao.queryByIdentity(Dept.class, role.getDeptid());
			role.setDeptname(dept.getDeptname());
		}
		return list;

	}

	public void addOrUpdateRole(Role role) {
		if (role.getRoleid() != null && role.getRoleid() != 0) {
			role.setUpdate_id(UserContext.getLoginUser().getUserid());
			role.setUpdate_time(new Timestamp(System.currentTimeMillis()));
			roleDao.update(role);
		} else {
			role.setCreate_id(UserContext.getLoginUser().getUserid());
			role.setCreate_time(new Timestamp(System.currentTimeMillis()));
			roleDao.insert(role);
		}

	}
}