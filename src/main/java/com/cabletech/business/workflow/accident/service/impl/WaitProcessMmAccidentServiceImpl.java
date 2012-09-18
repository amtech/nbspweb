package com.cabletech.business.workflow.accident.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.accident.dao.MmAccidentBaseDao;
import com.cabletech.business.workflow.accident.model.MmAccident;
import com.cabletech.business.workflow.accident.service.WaitProcessMmAccidentService;
import com.cabletech.common.util.Page;

/**
 * 隐患业务处理接口实现
 * 
 * @author 杨隽 2012-08-27 创建
 * 
 */
@SuppressWarnings("unchecked")
@Service
@Transactional(readOnly = true)
public class WaitProcessMmAccidentServiceImpl extends
		MmAccidentBaseServiceImpl<MmAccident, String> implements
		WaitProcessMmAccidentService {
	@Resource(name = "mmAccidentDao")
	private MmAccidentBaseDao<MmAccident, String> accidentDao;

	@SuppressWarnings("rawtypes")
	@Override
	public void getWaitProcessAccidentPage(MmAccident accident,
			UserInfo userInfo, Page page) {
		QueryParameter parameter = super.setQueryParameter(accident, userInfo);
		ConditionGenerate conditionGenerate = super
				.getConditionGenerate(
						MmAccidentBaseServiceImpl.WAIT_PROCESS_ACCIDENT_CONDITION_GENERATE_KEY,
						parameter);
		conditionGenerate.setPage(page);
		page = accidentDao.queryPageForSql(conditionGenerate);
	}

	@Override
	protected MmAccidentBaseDao<MmAccident, String> getAccidentBaseDao() {
		return accidentDao;
	}
}
