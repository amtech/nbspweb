package com.cabletech.business.assess.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.assess.dao.AssessExaminationDao;
import com.cabletech.business.assess.service.AssessAppraiseWaitHandledService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 月度考核待办工作业务接口实现
 * 
 * @author 杨隽 2012-08-06 创建
 * 
 */
@Service
@Transactional(readOnly = true)
@SuppressWarnings("all")
public class AssessAppraiseWaitHandledServiceImpl extends BaseServiceImpl
		implements AssessAppraiseWaitHandledService {
	@Resource(name = "assessExaminationDao")
	private AssessExaminationDao assessExaminationDao;

	@Override
	public void queryResultPage(Map<String, String> parameter, Page page) {
		assessExaminationDao.queryPage(parameter, page);
	}

	@Override
	protected BaseDao getBaseDao() {
		return null;
	}
}
