package com.cabletech.business.desktop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.desktop.dao.ContractorResEquipDao;
import com.cabletech.business.desktop.dao.DesktopDao;
import com.cabletech.business.desktop.service.OnlineManService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 在线人员服务实现
 * 
 * @author zhaobi
 * @date 2012-8-14
 */
@Service
@SuppressWarnings("rawtypes")
public class OnlineManServiceImpl extends BaseServiceImpl implements
		OnlineManService {
	// 桌面服务Dao
	@Resource(name = "desktopDao")
	private DesktopDao desktopDao;
	// 代维资源配备统计
	@Resource(name = "contractorResEquipDao")
	private ContractorResEquipDao contractorResEquipDao;
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;

	@Override
	@Transactional
	public List<Map<String, Object>> getOnlineManTreeList(UserInfo user) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = desktopDao.getOnlineManTreeList(user);
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public Map<String, Object> getPatrolGroupOnlineMan(UserInfo user) {
		// chart最后数据
		Map<String, Object> chartdate = new HashMap<String, Object>();
		// 图表中呈现数据
		List series = new ArrayList();
		List orgnamelist = new ArrayList();
		Map<String, Object> mapdate = new HashMap<String, Object>();
		// 组织ID
		String orgid = user.getOrgId();
		// 组织名称
		String orgname = user.getOrgName();
		// 作为PIE图名称
		orgnamelist.add(orgname);
		List<Map<String, Object>> grouplist = contractorResEquipDao
				.getOnlineManGroupByOrgID(orgid);
		// 在线人员总数
		int allcount = 0;
		// 在线人员数量
		int rlmancount = 0;
		// 饼内饼数据
		Map<String, Object> drilldownMap = new HashMap<String, Object>();
		List olmancountlist = new ArrayList();
		List groupnamelist = new ArrayList();
		if (null != grouplist && grouplist.size() > 0) {
			for (int j = 0; j < grouplist.size(); j++) {
				// 饼内饼名称
				groupnamelist.add(grouplist.get(j).get("NAME"));
				int olmancount = Integer.valueOf(grouplist.get(j)
						.get("OLMANCOUNT").toString());
				// 饼内饼数量
				olmancountlist.add(olmancount);
				// 饼数据
				allcount += Integer.valueOf(grouplist.get(j).get("ALLMANCOUNT")
						.toString());
				// 在线人员总数
				rlmancount += olmancount;

			}
			groupnamelist.add("非在线人员");
			olmancountlist.add(allcount - rlmancount);
			drilldownMap.put("name", orgname);
			drilldownMap.put("categories", groupnamelist);
			drilldownMap.put("data", olmancountlist);
			mapdate.put("y", allcount);
			mapdate.put("drilldown", drilldownMap);
			series.add(mapdate);
		}
		chartdate.put("categories", orgnamelist);
		chartdate.put("data", series);
		return chartdate;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public Map<String, Object> getContractoridOnlineMan(UserInfo user) {
		// 获取市移动用户所有代维
		List<Map<String, Object>> orglist = contractorResEquipDao
				.getOnlineManOrgByRegionID(user.getRegionId());
		// chart最后数据
		Map<String, Object> chartdate = new HashMap<String, Object>();
		// 图表中呈现数据
		List series = new ArrayList();
		if (null != orglist && orglist.size() > 0) {
			List orgnamelist = new ArrayList();
			for (int i = 0; i < orglist.size(); i++) {
				Map<String, Object> mapdate = new HashMap<String, Object>();
				// 组织ID
				String orgid = orglist.get(i).get("ID").toString();
				// 组织名称
				String orgname = orglist.get(i).get("NAME").toString();
				// 作为PIE图名称
				orgnamelist.add(orgname);
				List<Map<String, Object>> grouplist = contractorResEquipDao
						.getOnlineManGroupByOrgID(orgid);
				// 在线人员总数
				int allcount = 0;
				// 在线人员数量
				int rlmancount = 0;
				// 饼内饼数据
				Map<String, Object> drilldownMap = new HashMap<String, Object>();
				List olmancountlist = new ArrayList();
				List groupnamelist = new ArrayList();
				if (null != grouplist && grouplist.size() > 0) {

					for (int j = 0; j < grouplist.size(); j++) {
						// 饼内饼名称
						groupnamelist.add(grouplist.get(j).get("NAME")
								.toString());
						int olmancount = Integer.valueOf(grouplist.get(j)
								.get("OLMANCOUNT").toString());
						// 饼内饼数量
						olmancountlist.add(olmancount);
						// 饼数据
						allcount += Integer.valueOf(grouplist.get(j)
								.get("ALLMANCOUNT").toString());
						// 在线人员总数
						rlmancount += olmancount;
					}
				}
				groupnamelist.add("非在线人员");
				olmancountlist.add(allcount - rlmancount);
				drilldownMap.put("name", orgname);
				drilldownMap.put("categories", groupnamelist);
				drilldownMap.put("data", olmancountlist);
				mapdate.put("y", allcount);
				mapdate.put("drilldown", drilldownMap);
				series.add(mapdate);
			}
			chartdate.put("categories", orgnamelist);
			chartdate.put("data", series);
			return chartdate;

		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public Map<String, Object> getRegionOnlineMan(UserInfo user) {
		// 获取省用户所有市级区域
		List<Map<String, Object>> regionlist = baseInfoProvider
				.getRegionService().regionIteration(user.getRegionId(), "2");
		// chart最后数据
		Map<String, Object> chartdate = new HashMap<String, Object>();
		// 图表中呈现数据
		List series = new ArrayList();
		if (null != regionlist && regionlist.size() > 0) {
			List regionnamelist = new ArrayList();
			for (int i = 0; i < regionlist.size(); i++) {
				Map<String, Object> mapdate = new HashMap<String, Object>();
				// 区域ID
				String regionid = regionlist.get(i).get("REGIONID").toString();
				// 区域名称
				String regionname = regionlist.get(i).get("REGIONNAME")
						.toString();
				// 作为PIE图名称
				regionnamelist.add(regionname);
				List<Map<String, Object>> orglist = contractorResEquipDao
						.getOnlineManOrgByRegionID(regionid);
				// 在线人员总数
				int allcount = 0;
				// 在线人员数量
				int rlmancount = 0;
				// 饼内饼数据
				Map<String, Object> drilldownMap = new HashMap<String, Object>();
				List olmancountlist = new ArrayList();
				List orgnamelist = new ArrayList();
				if (null != orglist && orglist.size() > 0) {
					for (int j = 0; j < orglist.size(); j++) {
						// 饼内饼名称
						orgnamelist.add(orglist.get(j).get("NAME"));
						int olmancount = Integer.valueOf(orglist.get(j)
								.get("OLMANCOUNT").toString());
						// 饼内饼数量
						olmancountlist.add(olmancount);
						// 饼数据
						allcount += Integer.valueOf(orglist.get(j)
								.get("ALLMANCOUNT").toString());
						// 在线人员总数
						rlmancount += olmancount;

					}
				}
				orgnamelist.add("非在线人员");
				olmancountlist.add(allcount - rlmancount);
				drilldownMap.put("name", regionname);
				drilldownMap.put("categories", orgnamelist);
				drilldownMap.put("data", olmancountlist);
				mapdate.put("y", allcount);
				mapdate.put("drilldown", drilldownMap);
				series.add(mapdate);
			}
			chartdate.put("categories", regionnamelist);
			chartdate.put("data", series);
			return chartdate;

		}
		return null;
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
