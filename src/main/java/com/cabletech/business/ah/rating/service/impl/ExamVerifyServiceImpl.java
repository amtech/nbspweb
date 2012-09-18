package com.cabletech.business.ah.rating.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.ah.rating.dao.ExamVerifyDao;
import com.cabletech.business.ah.rating.model.ExamVerify;
import com.cabletech.business.ah.rating.service.ExamVerifyService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 提交记录处理
 * @author wangt
 *
 */
@Service
@Transactional
public class ExamVerifyServiceImpl  extends
BaseServiceImpl<ExamVerify, String> implements ExamVerifyService{
	@Resource(name = "examVerifyDao")
	private ExamVerifyDao dao;

	@Override
	protected BaseDao<ExamVerify, String> getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
