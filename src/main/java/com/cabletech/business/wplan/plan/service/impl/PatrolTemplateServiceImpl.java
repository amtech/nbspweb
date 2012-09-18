package com.cabletech.business.wplan.plan.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.wplan.plan.dao.PatrolTemplateDao;
import com.cabletech.business.wplan.plan.model.PatrolTemplate;
import com.cabletech.business.wplan.plan.service.PatrolTemplateService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
/**
 * 计划模板关系服务
 * @author Administrator
 *
 */
@Service
@Transactional
public class PatrolTemplateServiceImpl extends BaseServiceImpl<PatrolTemplate, String> implements PatrolTemplateService {

	@Resource(name = "patrolTemplateDao")
	private PatrolTemplateDao patrolTemplateDao;
	@Override
	protected BaseDao<PatrolTemplate, String> getBaseDao() {
		// TODO Auto-generated method stub
		return patrolTemplateDao;
	}
	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolTemplateService#deleteTemplate(java.lang.String)
	 */
	@Override
	@Transactional
	public void deleteTemplate(String planid) {
		patrolTemplateDao.deleteTemplate(planid);
		
	}

}
