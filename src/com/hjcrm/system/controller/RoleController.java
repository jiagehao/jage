package com.hjcrm.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcrm.publics.util.BaseController;
import com.hjcrm.system.entity.Role;
import com.hjcrm.system.service.IRoleService;

/**
 * 角色管理模块控制器
 * @author likang
 * @date   2018-1-20 上午9:02:44
 */
@Controller
public class RoleController extends BaseController{

    @Autowired
    private IRoleService roleService;

    /**
     * 根据部门ID，查询角色对应信息
     * @param request
     * @param deptid 部门ID
     * @return
     */
    @RequestMapping(value = "/role/queryRoleByDeptid.do",method = RequestMethod.GET)
    public @ResponseBody String queryRoleByDeptId(HttpServletRequest request,Long deptid){
        List<Role> list = roleService.queryRoleByDeptId(deptid);
        return jsonToPage(list);
    }
}