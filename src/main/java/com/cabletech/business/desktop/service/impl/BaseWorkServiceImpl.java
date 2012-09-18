package com.cabletech.business.desktop.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.desktop.dao.BaseWorkDao;
import com.cabletech.business.desktop.service.BaseWorkService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.base.SysConstant;

/**
 * 基础模块服务
 * 
 * @author zhaobi
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class BaseWorkServiceImpl extends BaseServiceImpl implements
		BaseWorkService {
	@Resource(name = "baseWorkDao")
	private BaseWorkDao baseWorkDao;

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return baseWorkDao;
	}

	/**
	 * 到期资质 证书
	 * 
	 * @param loginUser
	 *            登录用户
	 * @return list
	 */
	@Transactional
	public List<Map<String, Object>> getValidperiodedCertificatesList(
			UserInfo loginUser) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (loginUser.isMobile()) {// 移动用户
			list = baseWorkDao.getValidperiodedCertificatesList(
					loginUser.getRegionId(), "");
		} else {// 其它用户
			list = baseWorkDao.getValidperiodedCertificatesList(
					loginUser.getRegionId(), loginUser.getOrgId());
		}
		logger.debug(" getValidperiodedCertificatesList : " + list);
		return list;
	}

	/**
	 * 维护持有终端数
	 * 
	 * @param loginUser
	 *            登录用户
	 * @return list
	 * 
	 */
	@Transactional
	private List<Map<String, Object>> getContractorTerminalList(
			UserInfo loginUser) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (loginUser.isProvinceMobile()) {// 省移动用户
			list = baseWorkDao.getContractorTerminalListByRegion(loginUser
					.getRegionId());
		} else if (loginUser.isCityMobile()) {// 市移动用户
			list = baseWorkDao.getContractorTerminalListByOrg(loginUser
					.getRegionId());
		} else if (loginUser.isContractor()) {// 代维用户
			list = baseWorkDao.getContractorTerminalListByPatrol(
					loginUser.getRegionId(), loginUser.getOrgId());
		} else {// 其它用户
			list = baseWorkDao.getContractorTerminalListByOrg(loginUser
					.getRegionId());
		}
		logger.debug(" getContractorPersonList : " + list);
		return list;
	}

	/**
	 * 资源数量
	 * 
	 * @param loginUser
	 *            登录用户
	 * @param bzType
	 *            专业类型
	 * @return List
	 */
	private List<Map<String, Object>> getResCountByUser(UserInfo loginUser,
			String bzType) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (loginUser.isProvinceMobile()) {// 省移动用户
			list = baseWorkDao.getResCountListByRegion(loginUser.getRegionId(),
					bzType);
		} else if (loginUser.isCityMobile()) {// 市移动用户
			list = baseWorkDao.getResCountListByOrg(loginUser.getRegionId(),
					bzType);
		} else if (loginUser.isContractor()) {// 代维用户
			list = baseWorkDao.getResCountListByPatrol(loginUser.getRegionId(),
					loginUser.getOrgId(), bzType);
		} else {// 其它用户
			list = baseWorkDao.getResCountListByOrg(loginUser.getRegionId(),
					bzType);
		}
		logger.debug(" getContractorPersonList : " + list);
		return list;
	}

	/**
	 * 线路公里数
	 * 
	 * @param loginUser
	 *            登录用户
	 * @return List
	 */
	private List<Map<String, Object>> getLineCountbyUser(UserInfo loginUser) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (loginUser.isProvinceMobile()) {// 省移动用户
			list = baseWorkDao
					.getLineCountListByRegion(loginUser.getRegionId());
		} else if (loginUser.isCityMobile()) {// 市移动用户
			list = baseWorkDao.getLineCountListByOrg(loginUser.getRegionId());
		} else if (loginUser.isContractor()) {// 代维用户
			list = baseWorkDao.getLineCountListByPatrol(
					loginUser.getRegionId(), loginUser.getOrgId());
		} else {// 其它用户
			list = baseWorkDao.getLineCountListByOrg(loginUser.getRegionId());
		}
		logger.debug(" getLineCountbyUser : " + list);
		return list;
	}

	/**
	 * 资源数量
	 * 
	 * @param loginUser
	 *            登录用户
	 * @return List
	 */
	@Transactional
	public Map<String, Object> getResLineCountList(UserInfo loginUser) {
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
					List<Map<String, Object>> resnum = null;
					// 专业为线路取线路数据
					if (SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C30
							.equals(bustypstr)) {
						resnum = getLineCountbyUser(loginUser);
					} else {
						// 其它取无线数据
						String resType = SysConstant
								.getResourceBusinessTypeMap().get(bustypstr);
						resnum = getResCountByUser(loginUser, resType);
					}
					if (null != resnum && resnum.size() > 0) {

						List reslist = new ArrayList();
						for (int j = 0; j < resnum.size(); j++) {
							if (!namelist.contains(resnum.get(j).get("NAME"))) {
								namelist.add(resnum.get(j).get("NAME"));
							}
							reslist.add(Float.valueOf(resnum.get(j).get("NUM")
									.toString()));
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

	/*
	 * 获取终端图表需要的数据
	 */
	@Override
	@Transactional
	public String getTerminalChartData(UserInfo loginUser) {
		List<Map<String, Object>> list = getContractorTerminalList(loginUser);
		String jsonStr = "";
		StringBuffer sb = new StringBuffer();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> temp = list.get(i);
				sb.append("[");
				sb.append("'" + temp.get("NAME") + "'");
				sb.append(",");
				sb.append(temp.get("TERMINALNUM"));
				sb.append("]");
				sb.append(",");
			}
		}
		if (sb.length() > 0) {
			jsonStr = sb.substring(0, sb.length() - 1);
		} else {
			jsonStr = "[]";
		}
		return "[" + jsonStr + "]";
	}

	/**
	 * 根据当前用户信息进行首页离职率统计
	 * 
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Map<String, List<String>> 首页离职率统计结果列表
	 */
	@Override
	@Transactional(readOnly = true)
	public List getLeavePersonStatisticResult(UserInfo userInfo) {
		List data = new ArrayList();

		// TODO Auto-generated method stub
		Map<String, List<Float>> map = new HashMap<String, List<Float>>();
		getLeavePersonNumMap(userInfo, map);
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map mapdata = new HashMap<String, Object>();
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			mapdata.put("name", key);
			Object val = entry.getValue();
			mapdata.put("data", val);

			data.add(mapdata);
		}

		return data;
	}

	/**
	 * 获取离职人员数量MAP
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param map
	 *            Map<String, List<Float>>
	 */
	private void getLeavePersonNumMap(UserInfo userInfo,
			Map<String, List<Float>> map) {
		Calendar c = Calendar.getInstance();
		for (int month = 0; month < 12; month++) {
			String yearMonth = c.get(Calendar.YEAR) + "-" + (month + 1);
			List<Map<String, Object>> allNumList = getAllNumList(userInfo,
					yearMonth);
			List<Map<String, Object>> leaveNumList = getLeaveNumList(userInfo,
					yearMonth);
			for (int i = 0; allNumList != null && i < allNumList.size(); i++) {
				Map<String, Object> allNumMap = allNumList.get(i);
				if (MapUtils.isEmpty(allNumMap)) {
					continue;
				}
				int allNum = Integer.parseInt((String) allNumMap.get("all_num"));
				if (allNum == 0) {
					continue;
				}
				String name = (String) allNumMap.get("gname");
				if (StringUtils.isBlank(name)) {
					continue;
				}
				putLeavePersonResultToMap(leaveNumList, allNum, name, map,month);
			}
		}
	}

	/**
	 * 离职人员结果
	 * 
	 * @param leaveNumList
	 *            leaveNumList
	 * @param allNum
	 *            allNum
	 * @param name
	 *            name
	 * @param map
	 *            map
	 * @param month
	 *            month
	 */
	private void putLeavePersonResultToMap(
			List<Map<String, Object>> leaveNumList, int allNum, String name,
			Map<String, List<Float>> map, int month) {
		List<Float> numList = new ArrayList<Float>();
		if (map.containsKey(name)) {
			numList = map.get(name);
		}
		for (int i = numList.size(); i < month; i++) {
			numList.add(0f);
		}
		for (int j = 0; leaveNumList != null && j < leaveNumList.size(); j++) {
			Map<String, Object> leaveNumMap = leaveNumList.get(j);
			if (MapUtils.isNotEmpty(leaveNumMap)) {
				if (name.equals(leaveNumMap.get("gname"))) {
					int leaveNum = Integer.parseInt((String) leaveNumMap.get("leave_num"));
					double rate = 100.0d * leaveNum / allNum;
					numList.add(new Float(rate));
				}
			} else {
				numList.add(0f);
			}
		}
		if (CollectionUtils.isEmpty(leaveNumList)) {
			numList.add(0f);
		}
		map.put(name, numList);
	}

	/**
	 * 获取离职人数
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @return
	 */
	private List<Map<String, Object>> getLeaveNumList(UserInfo userInfo,
			String yearMonth) {
		List<Map<String, Object>> leaveNumList;
		if (userInfo.isProvinceMobile()) {
			leaveNumList = baseWorkDao.getLeavePersonNumberByRegion(userInfo,
					yearMonth);
		} else {
			leaveNumList = baseWorkDao.getLeavePersonNumberByOrg(userInfo,
					yearMonth);
		}
		return leaveNumList;
	}

	/**
	 * 获取所有离职人员数据
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @return
	 */
	private List<Map<String, Object>> getAllNumList(UserInfo userInfo,
			String yearMonth) {
		List<Map<String, Object>> allNumList;
		if (userInfo.isProvinceMobile()) {
			allNumList = baseWorkDao.getAllPersonNumberGroupByRegion(userInfo,
					yearMonth);
		} else {
			allNumList = baseWorkDao.getAllPersonNumberGroupByOrg(userInfo,
					yearMonth);
		}
		return allNumList;
	}
}
