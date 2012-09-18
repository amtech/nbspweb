package com.cabletech.business.sysmanager.service.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cabletech.business.sysmanager.dao.TaskSmsValidityDao;
import com.cabletech.business.sysmanager.model.TaskSmsValidity;
import com.cabletech.business.sysmanager.service.TaskSmsValidityService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 
 * @author zg
 *
 */
@SuppressWarnings("unchecked")
@Service
@Transactional
public class TaskSmsValidityServiceImpl extends
BaseServiceImpl<TaskSmsValidity, String> implements TaskSmsValidityService,Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "taskSmsValidityDao")
	private TaskSmsValidityDao dao;
	

	/*  关闭
	 * @see com.cabletech.business.sysmanager.service.TaskSmsValidityService#shutdown(java.lang.String)
	 */
	@Transactional
	@Override
	public void shutdown(String id) {
		dao.shutDown(id);
	}

	/*  开启
	 * @see com.cabletech.business.sysmanager.service.TaskSmsValidityService#startup(java.lang.String)
	 */
	@Override
	public void startup(String id) {
		dao.startUp(id);
	}

	
	@Override
	protected BaseDao<TaskSmsValidity, String> getBaseDao() {
		return dao;
	}

	/*  列表展示
	 * @see com.cabletech.business.sysmanager.service.TaskSmsValidityService#getQueryList(com.cabletech.common.util.Page, java.util.Map)
	 */
	@Override
	public Page<Map<String, Object>> getQueryList(
			Page<Map<String, Object>> page, Map<String, Object> parameters) {
		return dao.getQueryList(page,parameters);
	}

	/*  获取有效性
	 * @see com.cabletech.business.sysmanager.service.TaskSmsValidityService#getValidityByType(java.lang.String, java.lang.String)
	 */
	@Override
	public String getValidityByType(String smsType, String workorderType) {
		 
		return dao.getValidityByType(smsType,workorderType);
	}

}
