package com.hjcrm.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcrm.publics.constants.JumpViewConstants;
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Dept;
import com.hjcrm.system.service.IDeptService;

/**
 * @ClassName: DeptController
 * @Description: TODO(部门的管理接口)
 * @author Administrator
 * @date 2018年4月3日
 * 
 */
@Controller
public class DeptController extends BaseController {

	@Autowired
	private IDeptService deptService;

	@RequestMapping(value = "/system/deptMang.do", method = RequestMethod.GET)
	public String main() {
		if (UserContext.getLoginUser() != null) {
			return JumpViewConstants.SYSTEM_MENU_MANAGE;
		}

		return JumpViewConstants.SYSTEM_LOGIN;

	}

	@RequestMapping(value = "/dept/queryDept.do", method = RequestMethod.GET)
	public @ResponseBody String melist(HttpServletRequest request, Integer currentPage, Integer pageSize) {

		List<Dept> depts = deptService.queryDeptAll();
		return jsonToPage(depts);

	}

}