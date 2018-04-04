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
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.IUserService;

@Controller
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/system/userMang.do", method = RequestMethod.GET)
	public String main() {
		if (UserContext.getLoginUser() != null) {
			return JumpViewConstants.SYSTEM_USER_MANAGE;
		}

		return JumpViewConstants.SYSTEM_LOGIN;

	}

	@RequestMapping(value = "/system/userlist.do", method = RequestMethod.GET)
	@ResponseBody
	public String userlist(HttpServletRequest request, Integer currentPage, Integer pageSize) {

		List<User> users = userService.queryAllUsers(processPageBean(pageSize, currentPage));
		return jsonToPage(users);

	}

	@RequestMapping(value = "/system/saveOrUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request, User user) {
		if (user != null) {
			userService.saveOrUpdate(user);
			return ReturnConstants.SUCCESS;// 处理成功
		}
		return ReturnConstants.PARAM_NULL;// 接收参数为空
	}

	@RequestMapping(value = "/system/deleteUser.do", method = RequestMethod.POST)
	public String deletUser(HttpServletRequest request, String ids) {
		if (org.apache.commons.lang.StringUtils.isNotBlank(ids)) {
			userService.deleteUserByIds(ids);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
}
