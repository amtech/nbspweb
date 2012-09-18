package com.cabletech.business.workflow.workorder.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.workorder.dao.WorkOrderStatisticDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.service.WorkOrderStatisticService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 首页工单统计业务服务接口实现
 * 
 * @author 杨隽 2012-03-09 创建
 * 
 */
@Service
@SuppressWarnings("rawtypes")
public class WorkOrderStatisticServiceImpl extends BaseServiceImpl implements
		WorkOrderStatisticService {
	@Resource(name = "workOrderStatisticDao")
	private WorkOrderStatisticDao workOrderStatisticDao;

	/**
	 * 根据当前用户信息进行首页工单统计
	 * 
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Map<String, List<String>> 首页工单统计结果列表
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getWorkOrderStatisticResultMap(
			UserInfo userInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> monthResultList = workOrderStatisticDao
				.getMonthResultList();
		String condition = getCondition(userInfo);
		List<Map<String, Object>> list = workOrderStatisticDao
				.getWorkOrderTotalNumberResultList(condition);
		mergeList(monthResultList, list, "TOTAL_NUM");
		list = workOrderStatisticDao.getWorkOrderTotalHourResultList(condition);
		mergeList(monthResultList, list, "TOTAL_HOUR");
		list = workOrderStatisticDao
				.getWorkOrderOvertimeNumberResultList(condition);
		mergeList(monthResultList, list, "OVERTIME_NUM");
		convertListToMap(monthResultList, map);
		return map;
	}

	/**
	 * 根据当前用户信息获取首页超时工单数量信息列表
	 * 
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return List<Map<String, Object>> 首页超时工单数量信息列表
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getOvertimeWorkOrderStatisticResultList(
			UserInfo userInfo) {
		// TODO Auto-generated method stub
		StringBuffer conditionBuffer = new StringBuffer("");
		getWorkOrderCommonCondition(conditionBuffer);
		if (userInfo.isProvinceMobile()) {
			return workOrderStatisticDao.getOvertimeWorkOrderNumberByRegion(
					conditionBuffer.toString(), userInfo.getRegionId());
		}
		if (userInfo.isCityMobile()) {
			return workOrderStatisticDao.getOvertimeWorkOrderNumberByOrg(
					conditionBuffer.toString(), userInfo.getRegionId(), "");
		}
		if (userInfo.isContractor()) {
			return workOrderStatisticDao.getOvertimeWorkOrderNumberByOrg(
					conditionBuffer.toString(), userInfo.getRegionId(),
					userInfo.getOrgId());
		}
		return new ArrayList<Map<String, Object>>();
	}

	/**
	 * 将系统日期和工单归档状态查询条件放入查询条件缓冲区中
	 * 
	 * @param conditionBuffer
	 *            StringBuffer 查询条件缓冲区
	 */
	private void getWorkOrderCommonCondition(StringBuffer conditionBuffer) {
		Calendar c = Calendar.getInstance();
		conditionBuffer.append(" and wo.create_date>=to_date('");
		conditionBuffer.append(c.get(Calendar.YEAR) + "-"
				+ (c.get(Calendar.MONTH) + 1));
		conditionBuffer.append("-01','yyyy-mm-dd') ");
		conditionBuffer.append(" and wo.create_date<add_months(to_date('");
		conditionBuffer.append(c.get(Calendar.YEAR) + "-"
				+ (c.get(Calendar.MONTH) + 1));
		conditionBuffer.append("-01','yyyy-mm-dd'),1) ");
		conditionBuffer.append(" and wt.task_state='");
		conditionBuffer.append(WorkOrder.WORKORDER_END_STATE);
		conditionBuffer.append("' ");
	}

	/**
	 * 将工单统计数量信息列表合并到月统计初始化结果列表的指定列数值中
	 * 
	 * @param monthResultList
	 *            List<Map<String, Object>> 月统计初始化结果列表
	 * @param numberList
	 *            List<Map<String, Object>> 工单统计数量信息列表
	 * @param column
	 *            String 指定列
	 */
	private void mergeList(List<Map<String, Object>> monthResultList,
			List<Map<String, Object>> numberList, String column) {
		// TODO Auto-generated method stub
		if (CollectionUtils.isEmpty(monthResultList)) {
			return;
		}
		if (CollectionUtils.isEmpty(numberList)) {
			return;
		}
		for (int i = 0; i < monthResultList.size(); i++) {
			Map<String, Object> monthMap = monthResultList.get(i);
			String month = (String) monthMap.get("m");
			for (int j = 0; j < numberList.size(); j++) {
				Map<String, Object> numberMap = numberList.get(j);
				if (MapUtils.isEmpty(numberMap)) {
					continue;
				}
				if (month.equals(numberMap.get("mo"))) {
					monthMap.put(column, (String) numberMap.get(column));
					break;
				}
			}
		}
	}

	/**
	 * 将工单统计结果列表中数值信息放入首页工单统计结果Map中
	 * 
	 * @param list
	 *            List<Map<String, Object>> 工单统计结果列表
	 * @param map
	 *            Map<String, List<String>> 首页工单统计结果Map
	 */
	private void convertListToMap(List<Map<String, Object>> list,
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		List<String> totalNumberList = new ArrayList<String>();
		List<String> totalHourList = new ArrayList<String>();
		List<String> overtimeNumberList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> numberMap = list.get(i);
			if (MapUtils.isEmpty(numberMap)) {
				continue;
			}
			totalNumberList.add((String) numberMap.get("TOTAL_NUM"));
			totalHourList.add((String) numberMap.get("TOTAL_HOUR"));
			overtimeNumberList.add((String) numberMap.get("OVERTIME_NUM"));
		}
		map.put("total_num", totalNumberList);
		map.put("total_hour", totalHourList);
		map.put("overtime_num", overtimeNumberList);
	}

	/**
	 * 根据当前用户信息获取查询条件
	 * 
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return String 查询条件
	 */
	private String getCondition(UserInfo userInfo) {
		// TODO Auto-generated method stub
		StringBuffer conditionBuffer = new StringBuffer("");
		getBaseCondition(conditionBuffer);
		if (userInfo.isMobile()) {
			String regionId = userInfo.getRegionId();
			conditionBuffer.append(" and exists( ");
			conditionBuffer.append(" select r.regionid ");
			conditionBuffer.append(" from view_region r ");
			conditionBuffer.append(" where r.regionid=vu.regionid ");
			conditionBuffer.append(" start with r.regionid='");
			conditionBuffer.append(regionId);
			conditionBuffer.append("' ");
			conditionBuffer.append(" connect by prior r.regionid=r.parentid ");
			conditionBuffer.append(" ) ");
		}
		if (userInfo.isContractor()) {
			String orgId = userInfo.getOrgId();
			conditionBuffer.append(" and exists( ");
			conditionBuffer.append(" select o.id ");
			conditionBuffer.append(" from view_org o ");
			conditionBuffer.append(" where o.id=vu.orgid ");
			conditionBuffer.append(" start with o.id='");
			conditionBuffer.append(orgId);
			conditionBuffer.append("' ");
			conditionBuffer.append(" connect by prior o.id=o.parentid ");
			conditionBuffer.append(" ) ");
		}
		return conditionBuffer.toString();
	}

	/**
	 * 获取公共的日期和工单状态查询条件sql
	 * 
	 * @param conditionBuffer
	 *            StringBuffer 存放sql查询的缓冲区
	 */
	private void getBaseCondition(StringBuffer conditionBuffer) {
		Calendar c = Calendar.getInstance();
		conditionBuffer.append(" and to_char(wo.create_date,'yyyy')='");
		conditionBuffer.append(c.get(Calendar.YEAR));
		conditionBuffer.append("'");
		conditionBuffer.append(" and wt.task_state='");
		conditionBuffer.append(WorkOrder.WORKORDER_END_STATE);
		conditionBuffer.append("' ");
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}
}
