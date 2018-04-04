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
import com.hjcrm.system.entity.Dept;
import com.hjcrm.system.entity.Menu;
import com.hjcrm.system.service.IDeptService;
import com.hjcrm.system.service.IMenuService;

/**
 * @ClassName: MenuServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2018年4月3日
 * 
 */
@Service
public class DeptServiceImpl implements IDeptService {
	@Autowired
	private IDataAccess<Dept> deptDao;
	public List<Dept> queryDeptAll() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		List<Dept> depts = deptDao.queryByStatment("queryDeptAll", param, null);
		return depts;
	}

}

	

	

