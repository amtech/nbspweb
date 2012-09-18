package com.cabletech.business.ah.rating.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.ah.rating.dao.ExamVerifyRecordDao;
import com.cabletech.business.ah.rating.model.ExamVerifyRecord;
import com.cabletech.business.ah.rating.service.ExamVerifyRecordService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 处理记录考核对应表
 * @author wangt
 *
 */
@Service
@Transactional
public class ExamVerifyRecordServiceImpl extends
BaseServiceImpl<ExamVerifyRecord, String> implements ExamVerifyRecordService {
	@Resource(name = "examVerifyRecordDao")
	private ExamVerifyRecordDao dao;
	@Override
	protected BaseDao<ExamVerifyRecord, String> getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
