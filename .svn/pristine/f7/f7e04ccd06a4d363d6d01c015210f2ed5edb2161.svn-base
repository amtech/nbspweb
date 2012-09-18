package com.cabletech.business.assess.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.assess.dao.AssessExaminationDao;
import com.cabletech.business.assess.service.AssessAppraiseListService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 月度考核成绩查询业务接口实现
 * 
 * @author 杨隽 2012-08-07 创建
 * 
 */
@Service
@Transactional(readOnly = true)
@SuppressWarnings("all")
public class AssessAppraiseListServiceImpl extends BaseServiceImpl implements
		AssessAppraiseListService {
	@Resource(name = "assessExaminationDao")
	private AssessExaminationDao assessExaminationDao;

	@Override
	public void queryResultPage(Map<String, String> parameter, Page page) {
		assessExaminationDao.queryListPage(parameter, page);
	}

	@Override
	protected BaseDao getBaseDao() {
		return null;
	}
}
