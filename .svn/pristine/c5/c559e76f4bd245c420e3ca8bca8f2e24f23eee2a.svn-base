package com.cabletech.business.workflow.wmaintain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainBaseDao;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainHandleService;
import com.cabletech.common.util.Page;

/**
 * 隐患维修作业计划工作流列表业务处理接口实现
 * 
 * @author 王甜 2012-04-11 创建
 * @author 杨隽 2012-04-17 补充实现的方法
 * 
 */
@Service
@Transactional(readOnly = true)
public class WMaintainHandleServiceImpl extends
		WMaintainBaseServiceImpl<WMaintainPlan, String> implements
		WMaintainHandleService {
	// 维修作业计划Dao
	@Resource(name = "WMaintainPlanDao")
	private WMaintainBaseDao<WMaintainPlan, String> wmaintainPlanDao;

	/**
	 * 根据查询条件获取待办工作列表分页数据
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件的维修作业计划实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 待办工作列表分页数据
	 */
	@SuppressWarnings("rawtypes")
	public Page getWaitHandledList(WMaintainPlan plan, UserInfo userInfo) {
		QueryParameter parameter = setQueryParameter(plan, userInfo);
		ConditionGenerate conditionGenerate = getConditionGenerate(
				WAIT_HANDLED_WMAINTAIN_LIST_CONDITION_GENERATE_KEY, parameter);
		conditionGenerate.setPage(plan.getPage());
		return wmaintainPlanDao.queryPageForSql(conditionGenerate);
	}

	/**
	 * 根据查询条件获取已办工作列表分页数据
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件的维修作业计划实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 已办工作列表分页数据
	 */
	@SuppressWarnings("rawtypes")
	public Page getHandledList(WMaintainPlan plan, UserInfo userInfo) {
		QueryParameter parameter = setQueryParameter(plan, userInfo);
		ConditionGenerate conditionGenerate = getConditionGenerate(
				HANDLED_CONDITION_GENERATE_KEY, parameter);
		conditionGenerate.setPage(plan.getPage());
		return wmaintainPlanDao.queryPageForSql(conditionGenerate);
	}

	/**
	 * 获取维修作业计划处理操作Dao
	 * 
	 * @return WMaintainBaseDao<T, PK> 维修作业计划处理操作Dao
	 */
	@Override
	protected WMaintainBaseDao<WMaintainPlan, String> getWMaintainBaseDao() {
		// TODO Auto-generated method stub
		return wmaintainPlanDao;
	}
}
