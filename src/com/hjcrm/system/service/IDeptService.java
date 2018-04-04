package com.hjcrm.system.service;

import java.util.List;

import com.hjcrm.publics.util.PageBean;
import com.hjcrm.system.entity.Dept;
import com.hjcrm.system.entity.Menu;

/**  
* @ClassName: IDeptService  
* @Description: TODO(部门管理服务接口)  
* @author Administrator  
* @date 2018年4月3日  
*    
*/  
public interface IDeptService {
	/**
	 * 查询所有部门
	 */
	public List<Dept> queryDeptAll();
}
