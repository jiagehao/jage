package com.hjcrm.system.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.system.entity.Systemmessage;
import com.hjcrm.system.entity.Todaynote;
import com.hjcrm.system.service.ISystemMessageService;

@Service

public class SystemMessageImpl implements ISystemMessageService {

	@Autowired
	private IDataAccess<Systemmessage> messageDao;
	@Autowired
	private IDataAccess<Todaynote> noteDao;

	public List<Systemmessage> querySystemMessage(Long createUserId) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("createUserId", createUserId);
		List<Systemmessage> list = messageDao.queryByStatment("querySystemMessageByCreateUserId", param, null);
		return list;
	}

	public List<Todaynote> queryTodaynote(Long userId) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userid", userId);
		List<Todaynote> list = noteDao.queryByStatment("queryTodayNoteByUserId", param, null);
		return list;
	}

}
