package com.cabletech.business.workflow.wmaintain.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainBaseDao;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainSiteDao;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.model.WMaintainSite;
import com.cabletech.business.workflow.wmaintain.service.WMaintainResultService;
import com.cabletech.business.workflow.wmaintain.service.WMaintainSiteService;

/**
 * 计划维护站点服务接口实现
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-18 添加deletePlanSite()方法
 * @author 杨隽 2012-04-19 添加getPlanSiteMaintainNumberMap()方法
 * @author 杨隽 2012-04-26 添加getWMaintainSiteListInGrid()方法
 * 
 */
@Service
@Transactional
public class WMaintainSiteServiceImpl extends
		WMaintainBaseServiceImpl<WMaintainSite, String> implements
		WMaintainSiteService {
	// 计划站点未进行维护状态标识
	private static final String SITE_RED_STATE = "RED";
	// 计划站点无法维护状态标识
	private static final String SITE_YELLOW_STATE = "YELLOW";
	// 计划维护站点Dao
	@Resource(name = "WMaintainSiteDao")
	private WMaintainSiteDao wMaintainSiteDao;
	// 站点异常项及处理结果服务
	@Resource(name = "WMaintainResultServiceImpl")
	private WMaintainResultService wMaintainResultService;

	/**
	 * 保存制定维修作业计划中的站点信息
	 * 
	 * @param plan
	 *            WMaintainPlan 制定的维修作业计划
	 */
	@Override
	public void save(WMaintainPlan plan) {
		// TODO Auto-generated method stub
		String planId = plan.getId();
		String[] rsId = plan.getStationId();
		String[] rsType = plan.getStationType();
		if (ArrayUtils.isEmpty(rsId)) {
			return;
		}
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < rsId.length; i++) {
			WMaintainSite site = new WMaintainSite();
			if (list.contains(rsId[i] + rsType[i])) {
				continue;
			}
			site.setPlanId(planId);
			site.setRsId(rsId[i]);
			site.setRsType(rsType[i]);
			list.add(rsId[i] + rsType[i]);
			wMaintainSiteDao.save(site);
			wMaintainResultService.save(plan, site);
		}
	}

	/**
	 * 根据计划编号删除维修作业计划中的站点信息
	 * 
	 * @param planId
	 *            String 维修作业计划的编号
	 */
	@Override
	public void deletePlanSite(String planId) {
		wMaintainSiteDao.deleteSite(planId);
	}

	/**
	 * 根据计划编号获取维修作业计划中的站点列表信息
	 * 
	 * @param planId
	 *            String 维修作业计划的编号
	 * @return List<Map<String, Object>> 维修作业计划中的站点列表信息
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getWMaintainSiteList(String planId) {
		// TODO Auto-generated method stub
		QueryParameter parameter = new QueryParameter();
		parameter.setId(planId);
		ConditionGenerate conditionGenerate = super.getConditionGenerate(
				TASK_ID_CONDITION_GENERATE_KEY, parameter);
		return wMaintainSiteDao.queryListForSql(conditionGenerate);
	}

	/**
	 * 根据计划编号获取维修作业计划中的站点不同维护状态的维护数量信息
	 * 
	 * @param planId
	 *            String 维修作业计划的编号
	 * @return Map<String,Object> 维修作业计划中的站点不同维护状态的维护数量信息
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getPlanSiteMaintainNumberMap(String planId) {
		String key = TASK_ID_CONDITION_GENERATE_KEY;
		QueryParameter parameter = new QueryParameter();
		parameter.setId(planId);
		ConditionGenerate conditionGenerate = super.getConditionGenerate(key,
				parameter);
		List<Map<String, Object>> list = wMaintainSiteDao
				.queryListForSql(conditionGenerate);
		if (CollectionUtils.isEmpty(list)) {
			return new HashMap<String, Object>();
		}
		return calculateSiteMaintainNumber(list);
	}

	/**
	 * 根据计划编号获取维修作业计划中的站点列表信息（用于在编辑页面中显示站点列表）
	 * 
	 * @param planId
	 *            String 维修作业计划的编号
	 * @return List<Map<String, Object>> 维修作业计划中的站点列表信息
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getWMaintainSiteListInGrid(String planId) {
		List<Map<String, Object>> list = getWMaintainSiteList(planId);
		List<Map<String, Object>> gridList = convertDataListToGridDataList(list);
		return gridList;
	}

	/**
	 * 根据计划维护站点数据列表计算计划维护站点不同维护状态的数量信息
	 * 
	 * @param list
	 *            List<Map<String,Object>> 计划维护站点数据列表
	 * @return Map<String, Object> 计划维护站点不同维护状态的数量信息
	 */
	private Map<String, Object> calculateSiteMaintainNumber(
			List<Map<String, Object>> list) {
		Map<String, Object> numberMap = new HashMap<String, Object>();
		int nonMaintainNumber = 0;
		int cannotMaintainNumber = 0;
		int maintainedNumber = 0;
		int allNumber = list.size();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> resultMap = list.get(i);
			String siteMaintainState = (String) resultMap
					.get("SITE_MAINTAIN_STATE");
			if (SITE_RED_STATE.equals(siteMaintainState)) {
				nonMaintainNumber++;
			} else if (SITE_YELLOW_STATE.equals(siteMaintainState)) {
				cannotMaintainNumber++;
			} else {
				maintainedNumber++;
			}
		}
		numberMap.put("total_num", Integer.toString(allNumber));
		numberMap.put("nonmaintain_num", Integer.toString(nonMaintainNumber));
		numberMap.put("remain_num", Integer.toString(cannotMaintainNumber));
		numberMap.put("maintain_num", Integer.toString(maintainedNumber));
		return numberMap;
	}

	/**
	 * 转换维修作业计划中的站点列表信息到jqgrid中的站点列表数据信息
	 * 
	 * @param list
	 *            List<Map<String, Object>> 维修作业计划中的站点列表信息
	 * @return List<Map<String, Object>> 转换之后的维修作业计划中的站点列表信息
	 */
	private List<Map<String, Object>> convertDataListToGridDataList(
			List<Map<String, Object>> list) {
		List<Map<String, Object>> gridList = new ArrayList<Map<String, Object>>();
		if (CollectionUtils.isEmpty(list)) {
			return gridList;
		}
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> sourceMap = list.get(i);
			if (MapUtils.isEmpty(sourceMap)) {
				continue;
			}
			Map<String, Object> targetMap = new HashMap<String, Object>();
			putGridDataToMap(sourceMap, targetMap);
			gridList.add(targetMap);
		}
		return gridList;
	}

	/**
	 * 将读取的源数据Map转换到新的目标数据Map
	 * 
	 * @param sourceMap
	 *            Map<String, Object> 源数据Map
	 * @param targetMap
	 *            Map<String, Object> 目标Map
	 */
	private void putGridDataToMap(Map<String, Object> sourceMap,
			Map<String, Object> targetMap) {
		targetMap.put("ID",
				sourceMap.get("RS_TYPE") + "_" + sourceMap.get("RS_ID"));
		targetMap.put("SUBITEM_ID", sourceMap.get("RS_ID"));
		targetMap.put("ADDRESS", sourceMap.get("ADDRESS"));
		targetMap.put("RESOURCE_TYPE", sourceMap.get("RS_TYPE"));
		targetMap.put("RESOURCE_ID", sourceMap.get("RS_ID"));
		targetMap.put("RES_", "root");
		targetMap.put("SUBITEM_NAME", sourceMap.get("RS_NAME"));
		targetMap.put("SUBITEM_PATROL", "");
		targetMap.put("RS_NAME", sourceMap.get("RS_NAME"));
		targetMap.put("PATROL_GROUP_ID", sourceMap.get("PATROL_GROUP"));
		targetMap.put("BUSINESS_TYPE", sourceMap.get("BUSINESS_TYPE"));
	}

	@Override
	protected WMaintainBaseDao<WMaintainSite, String> getWMaintainBaseDao() {
		// TODO Auto-generated method stub
		return wMaintainSiteDao;
	}
}
