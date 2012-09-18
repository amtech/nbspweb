package com.cabletech.business.desktop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.desktop.dao.LinePatrolAnalysisDao;
import com.cabletech.business.desktop.service.PatrolPlanSeivice;
import com.cabletech.business.wplan.plan.dao.PatrolanalysisDao;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.base.SysConstant;

/**
 * 获取巡检统计分析服务
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class PatrolPlanSeiviceImpl extends BaseServiceImpl implements
		PatrolPlanSeivice {
	/**
	 * 巡检分析信息DAO
	 */
	@Resource(name = "patrolanalysisDao")
	private PatrolanalysisDao patrolanalysisDao;
	/**
	 * 线路巡检分析
	 */
	@Resource(name = "linePatrolAnalysisDao")
	private LinePatrolAnalysisDao linePatrolAnalysisDao;
	/**
	 * 计算巡检率
	 * @param loginUser 此用户
	 * @param timetype 时间类型
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getPatrolRate(UserInfo loginUser,String timetype) {
		List<Map<String, Object>> bzTypesList = loginUser.getBusinessTypes();
		List namelist = new ArrayList();
		// chart最后数据
		Map<String, Object> chartdate = new HashMap<String, Object>();
		if (null != bzTypesList && bzTypesList.size() > 0) {
			// 图表中呈现数据
			List series = new ArrayList();
			for (int i = 0; i < bzTypesList.size(); i++) {
				String bustypstr = bzTypesList.get(i).get("CODEVALUE")
						.toString();
				Map<String, Object> map = new HashMap<String, Object>();
				if (SysConstant.usebtypeList().contains(bustypstr)) {
					List<Map<String, Object>> planrate = null;
					if (SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C30
							.equals(bustypstr)) {
						planrate = getLineRate(loginUser);
					} else {
						planrate = getWPlanRate(loginUser, bustypstr, timetype);
					}
					if (null != planrate && planrate.size() > 0) {
						List reslist = new ArrayList();
						for (int j = 0; j < planrate.size(); j++) {
							if (!namelist.contains(planrate.get(j).get("NAME"))) {
								namelist.add(planrate.get(j).get("NAME"));
							}
							reslist.add(Float.valueOf(planrate.get(j)
									.get("RATE").toString()));
						}
						map.put("name", bzTypesList.get(i).get("LABLE")
								.toString());
						map.put("data", reslist);
						series.add(map);
					}

				}
			}
			chartdate.put("xcategories", namelist);
			chartdate.put("series", series);
			return chartdate;
		}
		return null;
	}

	/**
	 * 获取线路巡检率
	 * 
	 * @param loginUser 
	 * @return
	 */
	private List<Map<String, Object>> getLineRate(UserInfo loginUser) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (loginUser.isProvinceMobile()) {// 省移动用户
			list = linePatrolAnalysisDao
					.getRegionLinePatrolResultRateList(loginUser.getRegionId());
		} else if (loginUser.isCityMobile()) {// 市移动用户
			list = linePatrolAnalysisDao
					.getContractorLinePatrolResultRateList(loginUser
							.getRegionId());
		} else if (loginUser.isContractor()) {// 代维用户
			list = linePatrolAnalysisDao
					.getPatrolGroupLinePatrolResultRateList(
							loginUser.getRegionId(), loginUser.getOrgId());
		} else {// 其它用户
			list = linePatrolAnalysisDao
					.getRegionLinePatrolResultRateList(loginUser.getRegionId());
		}
		return list;
	}

	/**
	 * 获取无线巡检率
	 * 
	 * @param user 
	 * @param bustypstr bustypstr
	 * @param timetype 时间类型
	 * @return
	 */
	private List<Map<String, Object>> getWPlanRate(UserInfo user,
			String bustypstr,String timetype) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (user.isProvinceMobile()) {// 省移动用户
			list = patrolanalysisDao.getRegionPatrolRate(user.getRegionId(),
					bustypstr, timetype);
		} else if (user.isCityMobile()) {// 市移动用户
			list = patrolanalysisDao.getContractoridPatrolRate(
					user.getRegionId(), bustypstr, timetype);
		} else if (user.isContractor()) {// 代维用户
			list = patrolanalysisDao.getPatrolGroupPatrolRate(user.getOrgId(),
					bustypstr, timetype);
		} else {// 其它用户
			list = patrolanalysisDao.getRegionPatrolRate(user.getRegionId(),
					bustypstr, timetype);
		}
		return list;
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
