package com.cabletech.business.base.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.base.dao.CommonBaseDao;
import com.cabletech.business.base.model.CodeSequence;
import com.cabletech.business.base.service.CodeSequenceService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 序号生成记录信息业务接口实现
 * 
 * @author 杨隽 2012-01-12 创建
 * 
 */
@Service
@Transactional
public class CodeSequenceServiceImpl extends
		BaseServiceImpl<CodeSequence, String> implements CodeSequenceService {
	@Resource(name = "codeSequenceDao")
	private CommonBaseDao<CodeSequence, String> codeSequenceDao;


	/* 
	 * 根据组织编号和年月获取序号生成记录信息
	 * (non-Javadoc)
	 * @see com.cabletech.business.base.service.CodeSequenceService#getCodeSequence(com.cabletech.business.base.model.CodeSequence)
	 */
	@Override
	public CodeSequence getCodeSequence(CodeSequence codeSequence) {
		// TODO Auto-generated method stub
		Criteria c = codeSequenceDao.createCriteria();
		c.add(Restrictions.eq("deptId", codeSequence.getDeptId()));
		c.add(Restrictions.eq("yearMonth", codeSequence.getYearMonth()));
		c.add(Restrictions.eq("tableName", codeSequence.getTableName()));
		CodeSequence codeSequenceEntity = (CodeSequence) c.uniqueResult();
		return codeSequenceEntity;
	}

	@Override
	public void save(CodeSequence codeSequence) {
		// TODO Auto-generated method stub
		codeSequenceDao.save(codeSequence);
	}

	@Override
	protected BaseDao<CodeSequence, String> getBaseDao() {
		// TODO Auto-generated method stub
		return codeSequenceDao;
	}
}
