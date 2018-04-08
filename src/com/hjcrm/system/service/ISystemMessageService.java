package com.hjcrm.system.service;

import java.util.List;

import com.hjcrm.system.entity.Systemmessage;
import com.hjcrm.system.entity.Todaynote;

/**  
* @ClassName: IDeptService  
* @Description: TODO(部门管理服务接口)  
* @author Administrator  
* @date 2018年4月3日  
*    
*/  
public interface ISystemMessageService {
	/**
	 * 查询所有部门
	 */
	public List<Systemmessage> querySystemMessage(Long createUserId);
	public List<Todaynote> queryTodaynote(Long userId);
}
