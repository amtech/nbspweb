package com.cabletech.business.workflow.accident.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.workflow.accident.dao.MmAccidentBaseDao;
import com.cabletech.business.workflow.accident.model.MmAccident;
import com.cabletech.business.workflow.accident.service.MmAccidentService;

/**
 * 隐患业务处理接口实现
 * 
 * @author 杨隽 2012-08-27 创建
 * 
 */
@SuppressWarnings("unchecked")
@Service
@Transactional
public class MmAccidentServiceImpl extends
		MmAccidentBaseServiceImpl<MmAccident, String> implements
		MmAccidentService {
	@Resource(name = "mmAccidentDao")
	private MmAccidentBaseDao<MmAccident, String> accidentDao;

	@Override
	@Transactional(readOnly = true)
	public MmAccident viewAccident(String id) {
		MmAccident accident = accidentDao.get(id);
		String userName = super.getUserName(accident.getCreater());
		accident.setCreaterName(userName);
		String patrolGroupName = super.getPatrolGroupName(accident
				.getPatrolGroupId());
		accident.setPatrolGroupName(patrolGroupName);
		String resourceName = super.getResourceName(accident.getResourceId());
		accident.setResourceName(resourceName);
		String accidentTypeName = super.getAccidentTypeName(accident
				.getAccidentType());
		accident.setAccidentTypeName(accidentTypeName);
		return accident;
	}

	@Override
	protected MmAccidentBaseDao<MmAccident, String> getAccidentBaseDao() {
		return accidentDao;
	}
}
