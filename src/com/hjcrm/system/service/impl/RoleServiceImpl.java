package com.hjcrm.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.system.entity.Role;
import com.hjcrm.system.service.IRoleService;

@Service
@Transactional(rollbackFor=Exception.class)
public class RoleServiceImpl implements IRoleService{

    @Autowired
    IDataAccess<Role> roleDao;
    public List<Role> queryRoleByDeptId(Long deptid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("deptid", deptid);
        List<Role> list = roleDao.queryByStatment("queryRoleByDeptId", params, null);
        return list;
    }
}