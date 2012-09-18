package com.cabletech.business.workflow.wmaintain.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainBaseDao;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainResultDao;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.model.WMaintainResult;
import com.cabletech.business.workflow.wmaintain.model.WMaintainSite;
import com.cabletech.business.workflow.wmaintain.service.WMaintainResultService;
import com.cabletech.common.util.DateUtil;

/**
 * 站点异常项及处理结果服务接口实现
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-18 添加deletePlanResult()方法
 * @author 杨隽 2012-04-26 添加getWMaintainResultListInGrid()方法
 * @author 杨隽 2012-07-10 添加saveWmaintainProcess()方法以支持隐患现场处理信息的保存
 * @author 杨隽 2012-07-10 修改getWMaintainResultList()方法的参数
 * 
 */
@Service
@Transactional
public class WMaintainResultServiceImpl extends
		WMaintainBaseServiceImpl<WMaintainResult, String> implements
		WMaintainResultService {
	// 站点异常项及处理结果Dao
	@Resource(name = "WMaintainResultDao")
	private WMaintainResultDao wMaintainResultDao;

	/**
	 * 保存制定维修作业计划中的站点异常项信息
	 * 
	 * @param plan
	 *            WMaintainPlan 制定的维修作业计划
	 * @param site
	 *            WMaintainSite 制定的维修作业计划中的站点信息
	 */
	@Override
	public void save(WMaintainPlan plan, WMaintainSite site) {
		// TODO Auto-generated method stub
		String[] rsId = plan.getStationId();
		String[] rsType = plan.getStationType();
		String[] patrolRecordId = plan.getPatrolRecordId();
		String[] patrolItemId = plan.getPatrolItemId();
		if (ArrayUtils.isEmpty(patrolItemId)) {
			return;
		}
		for (int i = 0; i < patrolItemId.length; i++) {
			boolean isNotSitePatrolItem = isNotSitePatrolItem(site, rsId[i],
					rsType[i]);
			if (isNotSitePatrolItem) {
				continue;
			}
			WMaintainResult result = new WMaintainResult();
			result.setMaintainId(site.getId());
			result.setItemId(patrolItemId[i]);
			result.setPatrolRecordId(patrolRecordId[i]);
			wMaintainResultDao.save(result);
		}
	}

	/**
	 * 保存隐患现场处理过程
	 * 
	 * @param plan
	 *            WMaintainPlan 隐患现场处理过程信息
	 */
	@Override
	public void saveWmaintainProcess(WMaintainPlan plan) {
		if (StringUtils.isBlank(plan.getResultId())) {
			return;
		}
		String[] resultId = plan.getResultId().split(",");
		String[] resultRecord = plan.getResultMaintainRecord().split(",");
		String[] result = plan.getResultMaintainResult().split(",");
		String[] date = plan.getResultMaintainDate().split(",");
		for (int i = 0; i < resultId.length; i++) {
			WMaintainResult wmaintainResult = wMaintainResultDao
					.get(resultId[i]);
			wmaintainResult.setMaintainDate(DateUtil.Str2UtilDate(date[i],
					"yyyy-MM-dd HH:mm:ss"));
			wmaintainResult.setMaintainRecord(resultRecord[i]);
			wmaintainResult.setMaintainResult(result[i]);
			wMaintainResultDao.save(wmaintainResult);
		}
	}

	/**
	 * 根据计划编号删除维修作业计划中的站点异常项信息
	 * 
	 * @param planId
	 *            String 计划编号
	 */
	public void deletePlanResult(String planId) {
		wMaintainResultDao.deleteResult(planId);
	}

	/**
	 * 根据计划编号获取维修作业计划中的站点异常项列表信息
	 * 
	 * @param planId
	 *            String 维修作业计划的编号
	 * @param type
	 *            String 查询类型
	 * @return List<Map<String, Object>> 维修作业计划中的站点异常项列表信息
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getWMaintainResultList(String planId,
			String type) {
		// TODO Auto-generated method stub
		String key = SITE_ID_CONDITION_GENERATE_KEY;
		QueryParameter parameter = new QueryParameter();
		if (StringUtils.isBlank(type)) {
			parameter.setAlias("ws");
		} else {
			parameter.setAlias("wp");
		}
		parameter.setId(planId);
		ConditionGenerate conditionGenerate = super.getConditionGenerate(key,
				parameter);
		return wMaintainResultDao.queryListForSql(conditionGenerate);
	}

	/**
	 * 根据计划编号获取维修作业计划中的站点异常项列表信息（用于在编辑页面中显示站点列表）
	 * 
	 * @param planId
	 *            String 维修作业计划的编号
	 * @return List<Map<String, Object>> 维修作业计划中的站点异常项列表信息
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getWMaintainResultListInGrid(String planId) {
		String key = TASK_ID_CONDITION_GENERATE_KEY;
		QueryParameter parameter = new QueryParameter();
		parameter.setId(planId);
		ConditionGenerate conditionGenerate = super.getConditionGenerate(key,
				parameter);
		List<Map<String, Object>> list = wMaintainResultDao
				.queryListForSql(conditionGenerate);
		List<Map<String, Object>> gridList = convertDataListToGridDataList(list);
		return gridList;
	}

	/**
	 * 判断是否为该站点的巡检项巡检结果信息
	 * 
	 * @param site
	 *            WMaintainSite 巡检站点
	 * @param rsId
	 *            String 巡检项站点编号
	 * @param rsType
	 *            String 巡检项站点类型
	 * @return boolean 是否为该站点的巡检项巡检结果信息
	 */
	private boolean isNotSitePatrolItem(WMaintainSite site, String rsId,
			String rsType) {
		if (StringUtils.isBlank(site.getRsId())) {
			return true;
		}
		if (StringUtils.isBlank(site.getRsType())) {
			return true;
		}
		if (!site.getRsId().equals(rsId)) {
			return true;
		}
		if (!site.getRsType().equals(rsType)) {
			return true;
		}
		return false;
	}

	/**
	 * 转换维修作业计划中的站点异常项列表信息到jqgrid中的站点异常项列表数据信息
	 * 
	 * @param list
	 *            List<Map<String, Object>> 维修作业计划中的站点异常项列表信息
	 * @return List<Map<String, Object>> 转换之后的维修作业计划中的站点异常项列表信息
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
		targetMap.put("ID", sourceMap.get("PATROLRECORD_ID"));
		targetMap.put("SUBITEM_ID", sourceMap.get("ITEM_ID"));
		targetMap.put("ADDRESS", sourceMap.get("ADDRESS"));
		targetMap.put("RESOURCE_TYPE", sourceMap.get("RS_TYPE"));
		targetMap.put("RESOURCE_ID", sourceMap.get("RS_ID"));
		targetMap.put("RES_",
				sourceMap.get("RS_TYPE") + "_" + sourceMap.get("RS_ID"));
		targetMap.put("SUBITEM_NAME", sourceMap.get("SUBITEM_NAME"));
		targetMap.put("SUBITEM_PATROL", sourceMap.get("SUBITEM_PATROL"));
		targetMap.put("RS_NAME", sourceMap.get("RS_NAME"));
		targetMap.put("PATROL_GROUP_ID", sourceMap.get("PATROL_GROUP"));
		targetMap.put("BUSINESS_TYPE", sourceMap.get("BUSINESS_TYPE"));
	}

	@Override
	protected WMaintainBaseDao<WMaintainResult, String> getWMaintainBaseDao() {
		// TODO Auto-generated method stub
		return wMaintainResultDao;
	}
}
