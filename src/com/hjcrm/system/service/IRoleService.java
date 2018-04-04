package com.hjcrm.system.service;

import java.util.List;

import com.hjcrm.system.entity.Role;

/**
 * 角色管理接口
 * @author likang
 * @date   2018-1-20 上午9:06:04
 */
public interface IRoleService {
    /**
     * 根据部门id，查询对应的角色信息
     * @param deptid
     * @return
     */
    public List<Role> queryRoleByDeptId(Long deptid);
}