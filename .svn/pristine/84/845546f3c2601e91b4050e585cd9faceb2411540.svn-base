package com.cabletech.business.workflow.wmaintain.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainBaseDao;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainCancelService;
import com.cabletech.common.util.Page;

/**
 * 隐患维修作业计划取消任务列表业务处理接口实现
 * 
 * @author 王甜 2012-04-11 创建
 * @author 王甜 2012-04-17 补充实现的方法
 * @author 杨隽 2012-04-18 修改cancel()方法的参数
 * 
 */
@Service
@Transactional
public class WMaintainCancelServiceImpl extends
		WMaintainBaseServiceImpl<WMaintainPlan, String> implements
		WMaintainCancelService {
	// 维修作业计划Dao
	@Resource(name = "WMaintainPlanDao")
	private WMaintainBaseDao<WMaintainPlan, String> wmaintainPlanDao;

	/**
	 * 根据查询条件获取待取消工作列表分页数据
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件的维修作业计划实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 待办工作列表分页数据
	 */
	@Override
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getWaitCancelList(WMaintainPlan plan, UserInfo userInfo) {
		QueryParameter parameter = setQueryParameter(plan, userInfo);
		ConditionGenerate conditionGenerate = getConditionGenerate(
				WAIT_CANCELED_CONDITION_GENERATE_KEY, parameter);
		conditionGenerate.setPage(plan.getPage());
		return wmaintainPlanDao.queryPageForSql(conditionGenerate);
	}

	/**
	 * 根据查询条件获取已取消工作列表分页数据
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件的维修作业计划实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 待办工作列表分页数据
	 */
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly = true)
	public Page getCancedlList(WMaintainPlan plan, UserInfo userInfo) {
		QueryParameter parameter = setQueryParameter(plan, userInfo);
		ConditionGenerate conditionGenerate = getConditionGenerate(
				CANCELED_CONDITION_GENERATE_KEY, parameter);
		conditionGenerate.setPage(plan.getPage());
		return wmaintainPlanDao.queryPageForSql(conditionGenerate);
	}

	/**
	 * 取消隐患维修作业计划流程
	 * 
	 * @param id
	 *            String[] 隐患维修作业计划编号
	 */
	@Override
	public void cancel(String[] id) {
		// TODO Auto-generated method stub
		if (ArrayUtils.isEmpty(id)) {
			return;
		}
		for (int i = 0; i < id.length; i++) {
			super.getwMaintainWorkflowService().deleteTask(id[i]);
			WMaintainPlan plan = wmaintainPlanDao.get(id[i]);
			plan.setPlanState(WMaintainPlan.WMAINTAIN_CANCELED_STATE);
			wmaintainPlanDao.save(plan);
		}
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
