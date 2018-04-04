package com.hjcrm.system.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Menu;
import com.hjcrm.system.service.IMenuService;

/**
 * @ClassName: MenuServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2018年4月3日
 * 
 */
@Service
public class MenuServiceImpl implements IMenuService {
	@Autowired
	IDataAccess<Menu> menuDao;

	public List<Menu> queryMenuAll(PageBean pageBean) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("queryparent", true);
		List<Menu> list = menuDao.queryByStatment("queryMenuAll", param, pageBean);
		for (int i = 0; i < list.size(); i++) {
			param.put("menuparaid", list.get(i).getMenuid());
			param.put("queryparent", null);
			List<Menu> list2 = menuDao.queryByStatment("queryMenuAll", param, pageBean);
			list.get(i).setChildren(list2);

		}
		return list;
	}

	public void addAndUpdate(Menu menu) {
		if (menu.getMenuid() != null && menu.getMenuid() != 0) {
			menu.setUpdate_id(UserContext.getLoginUser().getUserid());
			menu.setUpdate_time(new Timestamp(System.currentTimeMillis()));
			menuDao.update(menu);
		} else {
			menu.setCreate_id(UserContext.getLoginUser().getUserid());
			menu.setCreate_time(new Timestamp(System.currentTimeMillis()));
			menuDao.insert(menu);
		}

	}

	public void deletMenu(String ids) {
		if (StringUtils.isNotBlank(ids))
			menuDao.deleteByIds(Menu.class, ids);

	}

	public List<Menu> getMenuByROleId(Long roleid) {
		// TODO Auto-generated method stub
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("roleid", roleid);
		List<Menu> list = menuDao.queryByStatment("queryByRoleid", param, null);
		return list;
	}

	

}
