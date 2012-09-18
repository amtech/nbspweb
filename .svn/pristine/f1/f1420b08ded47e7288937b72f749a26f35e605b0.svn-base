package com.cabletech.business.desktop.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.desktop.dao.LeavePersonStatisticDao;
import com.cabletech.business.desktop.service.LeavePersonStatisticService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 首页离职率统计业务服务接口实现
 * 
 * @author 杨隽 2012-03-14 创建
 * 
 */
@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LeavePersonStatisticServiceImpl extends BaseServiceImpl implements
		LeavePersonStatisticService {
	@Resource(name = "leavePersonStatisticDao")
	private LeavePersonStatisticDao leavePersonStatisticDao;

	/**
	 * 根据当前用户信息进行首页离职率统计
	 * 
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Map<String, List<String>> 首页离职率统计结果列表
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, List<String>> getLeavePersonStatisticResultMap(
			UserInfo userInfo) {
		// TODO Auto-generated method stub
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		getLeavePersonNumMap(userInfo, map);
		return map;
	}

	/**
	 * 离职数量
	 * @param userInfo 用户
	 * @param map 条件
	 */
	private void getLeavePersonNumMap(UserInfo userInfo,
			Map<String, List<String>> map) {
		Calendar c = Calendar.getInstance();
		for (int month = 0; month < 12; month++) {
			String yearMonth = c.get(Calendar.YEAR) + "-" + (month + 1);
			List<Map<String, Object>> allNumList = getAllNumList(userInfo,yearMonth);
			List<Map<String, Object>> leaveNumList = getLeaveNumList(userInfo,yearMonth);
			for (int i = 0; allNumList != null && i < allNumList.size(); i++) {
				Map<String, Object> allNumMap = allNumList.get(i);
				if (MapUtils.isEmpty(allNumMap)) {
					continue;
				}
				int allNum = Integer
						.parseInt((String) allNumMap.get("all_num"));
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
	 * 将查询结果列表转化成map
	 * @param leaveNumList 列表
	 * @param allNum 总数
	 * @param name 
	 * @param map 
	 * @param month 
	 */
	private void putLeavePersonResultToMap(
		List<Map<String, Object>> leaveNumList, int allNum, String name,Map<String, List<String>> map, int month) {
		List<String> numList = new ArrayList<String>();
		if (map.containsKey(name)) {
			numList = map.get(name);
		}
		for (int i = numList.size(); i < month; i++) {
			numList.add("0");
		}
		for (int j = 0; leaveNumList != null && j < leaveNumList.size(); j++) {
			Map<String, Object> leaveNumMap = leaveNumList.get(j);
			if (MapUtils.isNotEmpty(leaveNumMap)) {
				if (name.equals(leaveNumMap.get("gname"))) {
					int leaveNum = Integer.parseInt((String) leaveNumMap.get("leave_num"));
					DecimalFormat f = new DecimalFormat("#0.00");
					double rate = 100.0d * leaveNum / allNum;
					numList.add(f.format(rate));
				}
			} else {
				numList.add("0");
			}
		}
		if (CollectionUtils.isEmpty(leaveNumList))  numList.add("0");
		map.put(name, numList);
	}

	/**
	 * 获取离职人员数量列表
	 * @param userInfo 用户
	 * @param yearMonth 年月
	 * @return
	 */
	private List<Map<String, Object>> getLeaveNumList(UserInfo userInfo,
			String yearMonth) {
		List<Map<String, Object>> leaveNumList;
		if (userInfo.isProvinceMobile()) {
			leaveNumList = leavePersonStatisticDao
					.getLeavePersonNumberByRegion(userInfo, yearMonth);
		} else {
			leaveNumList = leavePersonStatisticDao.getLeavePersonNumberByOrg(
					userInfo, yearMonth);
		}
		return leaveNumList;
	}

	/**
	 * 获取所有数量列表
	 * @param userInfo 用户
	 * @param yearMonth 年月
	 * @return
	 */
	private List<Map<String, Object>> getAllNumList(UserInfo userInfo,
			String yearMonth) {
		List<Map<String, Object>> allNumList;
		if (userInfo.isProvinceMobile()) {
			allNumList = leavePersonStatisticDao
					.getAllPersonNumberGroupByRegion(userInfo, yearMonth);
		} else {
			allNumList = leavePersonStatisticDao.getAllPersonNumberGroupByOrg(
					userInfo, yearMonth);
		}
		return allNumList;
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
