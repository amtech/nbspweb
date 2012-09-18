package com.cabletech.business.workflow.fault.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.workflow.fault.model.WTroubleEoms;
import com.cabletech.business.workflow.fault.service.WtroubleEomsService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 故障EOMS工单业务操作
 * 
 * @author 杨隽 2012-07-17 创建
 * 
 */
@Service
public class WtroubleEomsServiceImpl extends
		BaseServiceImpl<WTroubleEoms, String> implements WtroubleEomsService {
	// EOMS工单Dao操作
	@Resource(name = "wtroubleEomsDao")
	private BaseDao<WTroubleEoms, String> wtroubleEomsDao;

	@Override
	protected BaseDao<WTroubleEoms, String> getBaseDao() {
		return wtroubleEomsDao;
	}

	@Override
	@Transactional(readOnly = true)
	public WTroubleEoms viewEomsInfo(String eomsId) {
		return wtroubleEomsDao.get(eomsId);
	}
}
