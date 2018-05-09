package com.hjcrm.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcrm.publics.constants.JumpViewConstants;
import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Role;
import com.hjcrm.system.service.IRoleService;

/**
 * 角色管理模块控制器
 * 
 * @author likang
 * @date 2018-1-20 上午9:02:44
 */
@Controller
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;

	@RequestMapping(value = "/system/roleMang.do", method = RequestMethod.GET)
	public String main() {
		if (UserContext.getLoginUser() != null) {
			return JumpViewConstants.SYSTEM_ROLE_MANAGE;
		}

		return JumpViewConstants.SYSTEM_LOGIN;
	}

	/**
	 * 根据部门ID，查询角色对应信息
	 * 
	 * @param request
	 *            
	 * @param deptid
	 *            部门ID
	 * @return String 状态
	 * 
	 */
	@RequestMapping(value = "/role/queryRoleByDeptid.do", method = RequestMethod.GET)
	public @ResponseBody String queryRoleByDeptId(HttpServletRequest request, Long deptid) {
		List<Role> list = roleService.queryRoleByDeptId(deptid);
		return jsonToPage(list);
	}

	/**   
	 * 加载所有角色
	 *
	 * @Title queryAllRole   
	 * @param request
	 * @param currentPage
	 * @param pageSize
	 * @return String
	 */ 
	@RequestMapping(value = "/role/queryAllRole.do", method = RequestMethod.GET)
	public @ResponseBody String queryAllRole(HttpServletRequest request, Integer currentPage, Integer pageSize) {

		List<Role> list = roleService.queryAllRole(processPageBean(pageSize, currentPage));
		return jsonToPage(list);

	}

	/**   
	 *更新和添加角色
	 *
	 * @Title addOrUpdate   
	 * @param request
	 * @param role
	 * @return String
	 */ 
	@RequestMapping(value = "/role/addOrUpdateRole.do", method = RequestMethod.POST)
	@ResponseBody
	public String addOrUpdate(HttpServletRequest request, Role role) {
		if (role != null) {
			roleService.addOrUpdateRole(role);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;

	}
}