package com.cabletech.business.analysis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.analysis.dao.TroubleLevelsTimeLengthDao;
import com.cabletech.business.analysis.service.TroubleLevelsTimeLengthService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 故障平均历时统计接口实现
 * 
 * @author 杨隽 2012-03-20 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class TroubleLevelsTimeLengthServiceImpl extends BaseServiceImpl
		implements TroubleLevelsTimeLengthService {
	@Resource(name = "troubleLevelsTimeLengthDao")
	private TroubleLevelsTimeLengthDao troubleLevelsTimeLengthDao;

	/**
	 * 故障平均历时统计（按组织）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTroubleLevelsMainTimeLengthList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = troubleLevelsTimeLengthDao
				.getTroubleLevelsTimeLengthListByOrg(parameters);
		return list;
	}

	/**
	 * 故障平均历时统计（按巡检组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTroubleLevelsSubTimeLengthList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = troubleLevelsTimeLengthDao
				.getTroubleLevelsTimeLengthListByPatrolGroup(parameters);
		return list;
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}
}
