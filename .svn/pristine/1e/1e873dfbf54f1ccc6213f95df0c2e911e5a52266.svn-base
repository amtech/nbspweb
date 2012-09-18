package com.cabletech.business.sysmanager.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import oracle.net.aso.h;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.sysmanager.dao.WorkorderControlInfoDao;
import com.cabletech.business.sysmanager.model.WorkorderControlInfo;
import com.cabletech.business.sysmanager.service.WorkorderControlInfoService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 *
 */
@SuppressWarnings("unchecked")
@Service
@Transactional
public class WorkorderControlInfoServiceImpl extends
BaseServiceImpl<WorkorderControlInfo, String> implements WorkorderControlInfoService ,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 */
	@Resource(name = "workorderControlInfoDao")
	private WorkorderControlInfoDao dao;
	
	@Override
	protected BaseDao<WorkorderControlInfo, String> getBaseDao() {
		return dao;
	} 
	@Transactional
	@Override
	public List<Map<String, Object>> getAllList() {
		return dao.selectAllOrders();
	}
	@Override
	public void updateSchedulerState(String workorderId) {
		  dao.updateSchedulerState(workorderId);
		
	}
	@Override
	public String getPhoneBYWorkId(String handlePersonId) {
		 
		return dao.getPhoneBYWorkId(handlePersonId);
	}

}
