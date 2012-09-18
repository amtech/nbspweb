package com.cabletech.business.analysis.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.analysis.dao.TroubleLevelsNumberDao;
import com.cabletech.business.analysis.service.TroubleLevelsNumberService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 故障分级统计接口实现
 * 
 * @author 杨隽 2012-03-20 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class TroubleLevelsNumberServiceImpl extends BaseServiceImpl implements
		TroubleLevelsNumberService {
	@Resource(name = "troubleLevelsNumberDao")
	private TroubleLevelsNumberDao troubleLevelsNumberDao;

	/**
	 * 故障分级统计（按组织）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTroubleLevelsMainNumberList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = troubleLevelsNumberDao
				.getTroubleLevelsNumberListByOrg(parameters);
		return list;
	}

	/**
	 * 故障分级统计（按巡检组）
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTroubleLevelsSubNumberList(
			Map<String, String> parameters) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = troubleLevelsNumberDao
				.getTroubleLevelsNumberListByPatrolGroup(parameters);
		return list;
	}

	/**
	 * 按级别查询故障列表
	 * 
	 * @param parameters
	 *            Map<String, String>
	 * @param page
	 *            Page
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getTroubleListByLevel(Map<String, String> parameters, Page page) {
		return troubleLevelsNumberDao.getTroubleListByLevel(parameters, page);
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}
}
