package com.cabletech.business.wplan.plan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.wplan.plan.dao.PatrolanalysisDao;
import com.cabletech.business.wplan.plan.dao.PatrolinfoExecuteDao;
import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.business.wplan.plan.service.PatrolanalysisService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 巡检分析服务具体实现
 * 
 * @author Administrator
 * 
 */
@Service
@Transactional
public class PatrolanalysisServiceImpl extends
		BaseServiceImpl<Patrolinfo, String> implements PatrolanalysisService {
	/**
	 * 巡检执行信息DAO
	 */
	@Resource(name = "patrolinfoExecuteDao")
	private PatrolinfoExecuteDao patrolinfoExecuteDao;

	/**
	 * 巡检分析信息DAO
	 */
	@Resource(name = "patrolanalysisDao")
	private PatrolanalysisDao patrolanalysisDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cabletech.business.wplan.plan.service.PatrolanalysisService#
	 * getAllPatrolCount(com.cabletech.business.wplan.plan.model.Patrolinfo)
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getAllPatrolCount(Patrolinfo patrolinfo) {
		return patrolanalysisDao.getAllPatrolCount(patrolinfo);
	}

	@Override
	@Transactional(readOnly = true)
	public Page getPatrolGroupPatrolInfo(Patrolinfo patrolinfo, Page page) {
		// TODO Auto-generated method stub
		return patrolanalysisDao.getPatrolGroupPatrolInfo(patrolinfo, page);
	}

	/**
	 * 获取巡检组图表数据
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getPatrolGroupChart(Patrolinfo patrolinfo) {
		List<Map<String, Object>> patrolGroupList = patrolanalysisDao
				.getPatrolGroupPatrolList(patrolinfo);
		return getChartData(patrolGroupList, "PATROLGROUPNAME");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cabletech.business.wplan.plan.service.PatrolanalysisService#getPatrolInfo
	 * (com.cabletech.business.wplan.plan.model.Patrolinfo,
	 * com.cabletech.common.util.Page)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getPatrolInfo(Patrolinfo patrolinfo, Page page) {
		// TODO Auto-generated method stub
		return patrolinfoExecuteDao.getAllPatrolScheduleForSearch(patrolinfo,
				page);
	}

	/**
	 * 获取巡检组图表数据
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getPatrolOrgChart(Patrolinfo patrolinfo) {
		List<Map<String, Object>> orgList = patrolanalysisDao
				.getPatrolOrgList(patrolinfo);
		return getChartData(orgList, "ORGNAME");
	}

	/**
	 * 获取图表数据
	 * 
	 * @param list
	 *            List<Map<String,Object>>
	 * @param name
	 *            String
	 * @return
	 */
	private Map<String, Object> getChartData(List<Map<String, Object>> list,
			String name) {
		List<Map<String, Object>> orgList = list;
		// chart最后数据
		Map<String, Object> chartdate = null;
		if (null != orgList && orgList.size() > 0) {
			chartdate = new HashMap<String, Object>();
			// 巡检组名称
			String patrolnames = "'";
			// 已巡检资源数
			String alrescounts = "";
			// 未巡检资源数
			String norescounts = "";
			// 计划巡检数
			String rescounts = "";
			for (int i = 0; i < orgList.size(); i++) {
				patrolnames += orgList.get(i).get(name).toString() + "','";
				alrescounts += orgList.get(i).get("PLANOVERRESCOUNT")
						.toString()
						+ ",";
				norescounts += orgList.get(i).get("PLANLOSTRESCOUNT")
						.toString()
						+ ",";
				rescounts += orgList.get(i).get("PLANRESCOUNT").toString()
						+ ",";
			}
			if (patrolnames.length() != 1) {
				chartdate.put("PATROLNAMES",
						patrolnames.substring(0, patrolnames.length() - 2));
				chartdate.put("ALRESCOUNTS",
						alrescounts.substring(0, alrescounts.length() - 1));
				chartdate.put("NORESCOUNTS",
						norescounts.substring(0, norescounts.length() - 1));
				chartdate.put("RESCOUNTS",
						rescounts.substring(0, rescounts.length() - 1));
			}
		}
		return chartdate;
	}

	/**
	 * 获取region下巡检组列表
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @param page
	 *            Page
	 */
	@Override
	@Transactional(readOnly = true)
	public Page getRegionPatrolInfo(Patrolinfo patrolinfo, Page page) {
		// TODO Auto-generated method stub
		return patrolanalysisDao.getPatrolRegionInfo(patrolinfo, page);
	}

	@Override
	protected BaseDao<Patrolinfo, String> getBaseDao() {
		// TODO Auto-generated method stub
		return patrolanalysisDao;
	}

}
