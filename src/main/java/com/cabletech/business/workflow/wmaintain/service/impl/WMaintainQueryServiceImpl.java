package com.cabletech.business.workflow.wmaintain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainBaseDao;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainQueryService;
import com.cabletech.common.util.Page;

/**
 * 隐患维修作业计划任务列表业务处理接口实现
 * 
 * @author 王甜 2012-04-11 创建
 * @author 王甜 2012-04-17 补充实现的方法
 * 
 */
@Service
@Transactional(readOnly = true)
public class WMaintainQueryServiceImpl extends
		WMaintainBaseServiceImpl<WMaintainPlan, String> implements
		WMaintainQueryService {
	// 维修作业计划Dao
	@Resource(name = "WMaintainPlanDao")
	private WMaintainBaseDao<WMaintainPlan, String> wmaintainPlanDao;

	/**
	 * 根据查询条件获取查询工作列表分页数据
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件的维修作业计划实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 待办工作列表分页数据
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public Page getQueryList(WMaintainPlan plan, UserInfo userInfo) {
		QueryParameter parameter = setQueryParameter(plan, userInfo);
		ConditionGenerate conditionGenerate = getConditionGenerate(
				WMAINTAIN_LIST_CONDITION_GENERATE_KEY, parameter);
		conditionGenerate.setPage(plan.getPage());
		return wmaintainPlanDao.queryPageForSql(conditionGenerate);
	}

	@Override
	protected WMaintainBaseDao<WMaintainPlan, String> getWMaintainBaseDao() {
		// TODO Auto-generated method stub
		return wmaintainPlanDao;
	}

}
