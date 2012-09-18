package com.cabletech.business.sysmanager.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.sysmanager.dao.RemindTimeConfigureDao;
import com.cabletech.business.sysmanager.model.RemindTimeConfigure;
import com.cabletech.business.sysmanager.service.RemindTimeConfigureService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * @author zg
 * 
 */
@SuppressWarnings("unchecked")
@Service
@Transactional
public class RemindTimeConfigureServiceImpl extends
		BaseServiceImpl<RemindTimeConfigure, String> implements
		RemindTimeConfigureService,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "remindTimeConfigureDao")
	private RemindTimeConfigureDao dao;

	@Transactional
	@Override
	protected BaseDao<RemindTimeConfigure, String> getBaseDao() {
		return dao;
	}

	@Transactional
	@Override
	public Page<RemindTimeConfigure> getQueryList(
			Page<RemindTimeConfigure> page, Map<String, Object> parameters) {
		return dao.getQueryList(page, parameters);
	}

	@Transactional
	@Override
	public RemindTimeConfigure getEntityById(String id) {
		return dao.getEntityById(id);
	}

	@Transactional
	@Override
	public void saveEntity(RemindTimeConfigure remindTimeConfigure) {
		dao.saveEntity(remindTimeConfigure);

	}

	@Transactional
	@Override
	public void deleteEntityByid(String id) {
		dao.deleteEntityByid(id);
	}

	@Transactional
	@Override
	public int checkNum(String taskType, String smsType, String businessType) {

		return dao.checkNum(taskType, smsType, businessType);
	}

	@Override
	public List<Map<String, Object>> getObjectListByType(String workorderType,
			String professionType) {
		return dao.getListByType(workorderType,professionType);
	}

 

}
