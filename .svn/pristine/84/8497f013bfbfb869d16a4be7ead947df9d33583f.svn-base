package com.cabletech.business.analysis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.analysis.dao.TroubleLevelsOvertimeLengthDao;
import com.cabletech.business.analysis.service.TroubleLevelsOvertimeLengthService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 故障超时时长统计接口实现
 * 
 * @author 杨隽 2012-03-23 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class TroubleLevelsOvertimeLengthServiceImpl extends BaseServiceImpl
		implements TroubleLevelsOvertimeLengthService {
	@Resource(name = "troubleLevelsOvertimeLengthDao")
	private TroubleLevelsOvertimeLengthDao troubleLevelsOvertimeLengthDao;

	/**
	 * 故障超时时长统计（按组织）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTroubleLevelsMainOvertimeLengthList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = troubleLevelsOvertimeLengthDao
				.getTroubleLevelsOvertimeLengthListByOrg(parameters);
		return list;
	}

	/**
	 * 故障超时时长统计（按巡检组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTroubleLevelsSubOvertimeLengthList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = troubleLevelsOvertimeLengthDao
				.getTroubleLevelsOvertimeLengthListByPatrolGroup(parameters);
		return list;
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}
}
