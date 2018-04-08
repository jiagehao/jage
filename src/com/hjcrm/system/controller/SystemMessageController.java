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
import com.hjcrm.system.entity.Systemmessage;
import com.hjcrm.system.entity.Todaynote;
import com.hjcrm.system.service.IDeptService;
import com.hjcrm.system.service.ISystemMessageService;

/**
 * @ClassName: DeptController
 * @Description: TODO(部门的管理接口)
 * @author Administrator
 * @date 2018年4月3日
 * 
 */
@Controller
public class SystemMessageController extends BaseController {

	@Autowired
	private ISystemMessageService messageSercice;

	

	@RequestMapping(value = "/system/querySystemmessageSend.do", method = RequestMethod.GET)
	public @ResponseBody String melist(HttpServletRequest request, Integer currentPage, Integer pageSize,Long userid) {

		List<Systemmessage> systemmessages = messageSercice.querySystemMessage(userid);
		return jsonToPage(systemmessages);

	}
	@RequestMapping(value = "/system/queryTodaynote.do", method = RequestMethod.GET)
	public @ResponseBody String loadnote(HttpServletRequest request, Integer currentPage, Integer pageSize,Long userid) {
		
		List<Todaynote> notes = messageSercice.queryTodaynote(userid);
		return jsonToPage(notes);
		
	}

}