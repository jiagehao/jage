package com.hjcrm.system.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hjcrm.publics.constants.JumpViewConstants;
import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.util.ContextUtil;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Menu;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.IUserService;

/***
 * 登录功能
 * 
 * @author Administrator
 *
 */
@Controller
public class LoginController {
	// auth--auth2
	// 出现这种关键词，则代表请求是安全的，数据是安全的
	public static final String COOKIE_KEY = "auth_key";
	public static final String COOKIE_KEY_SEPARATE = "_#_";
	@Autowired
	private IUserService userService;

	/**
	 * 跳转登录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String toLogin(Model model) {
		// 判断用户是否登录，如果登录，则跳转主页面，如果没有登录，则跳转登录页面
		if (UserContext.getLoginUser() != null) {// 已经登录
			return "redirect:/main.do";
		}
		return JumpViewConstants.SYSTEM_LOGIN;// 登录页面
	}

	/**
	 * 主页面的跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String main(Model model) {
		// 判断用户是否登录，如果登录，则跳转主页面，如果没有登录，则跳转登录页面
		if (UserContext.getLoginUser() != null) {// 已经登录
			Long roleId = UserContext.getLoginUser().getRoleid();
			List<Menu> menus = userService.queryAllMenuByRoleId(roleId);

			model.addAttribute("menus", menus);
			return JumpViewConstants.SYSTEM_INDEX;
		}
		return JumpViewConstants.SYSTEM_LOGIN;// 登录页面
	}

	/**
	 * 用户登录功能
	 * 
	 * @param model
	 * @param email
	 *            邮箱名称
	 * @param password
	 *            密码
	 * @param sign
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(Model model, String email, String password, String sign, HttpServletRequest request,
			HttpServletResponse response) {

		if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(password)) {
			email = email + ContextUtil.getInitConfig("email_suffix");
			// 首先判断用户是否存在
			User user = userService.queryUserByEmail(email);
			if (user != null) {// 用户名存在,验证密码是否正确
				boolean eashPassword = userService.isEashPassword(password, email);
				if (eashPassword) {// 密码验证成功
					// 设置ssesion
					UserContext.setLoginUser(user);
					// 解决用户关闭客户端cookie失效的情况

					// 再服务器储存用户cookie数据，每次请求或获取数据可直接在服务器获取
					Cookie cookie = new Cookie(COOKIE_KEY,
							URLEncoder.encode(email) + COOKIE_KEY_SEPARATE + URLEncoder.encode(password));
					cookie.setMaxAge(0);// 开启就一直存在
										// 关闭即失效
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
					request.getSession(true).setAttribute("loginName", user.getUsername());
					request.getSession(true).setAttribute("ischange", user.getIschange());

					// 跳转主页面
					return "redirect:/main.do";

				} else {// 密码不匹配
					model.addAttribute("msg", ReturnConstants.PASSWORD_ERROR);
					return JumpViewConstants.SYSTEM_LOGIN;// 登录页面

				}
			} else {// 用户名不存在，返回状态1
				model.addAttribute("msg", ReturnConstants.USER_NOT_EXIST);
				return JumpViewConstants.SYSTEM_LOGIN;// 登录页面
			}
		}
		return ReturnConstants.PARAM_NULL;// 接受参数为空
	}

	
	/**  
	* @Title: logout  
	* @Description: TODO(退出功能)  
	* @param @param request
	* @param @param response
	* @param @return    参数  
	* @return String    返回类型  
	* @throws  
	*/  
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		UserContext.clearLoginUser();// 清除session中的用户信息
		// 清除服务器cookie
		Cookie cookieJSes = new Cookie("JSESSIONID", null);
		cookieJSes.setMaxAge(0);
		cookieJSes.setPath(request.getContextPath());
		response.addCookie(cookieJSes);

		// 清除客户端浏览器cookie
		Cookie cookie = new Cookie(COOKIE_KEY, null);
		cookie.setMaxAge(0);// 立即失效
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/main.do";
	}
}
