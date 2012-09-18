package com.cabletech.business.workflow.wmaintain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainBaseDao;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainDraftService;
import com.cabletech.common.util.Page;

/**
 * 维修作业计划草稿箱业务处理接口实现
 * 
 * @author 王甜 2012-04-11 创建
 * @author 杨隽 2012-04-16 修改getDraftList()方法
 * 
 */
@Service
public class WMaintainDraftServiceImpl extends
		WMaintainBaseServiceImpl<WMaintainPlan, String> implements
		WMaintainDraftService {
	// 维修作业计划Dao
	@Resource(name = "WMaintainPlanDao")
	private WMaintainBaseDao<WMaintainPlan, String> wmaintainPlanDao;

	/**
	 * 获取草稿箱列表分页数据
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件的工单实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 草稿箱列表分页数据
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Page getDraftList(WMaintainPlan plan, UserInfo userInfo) {
		QueryParameter parameter = setQueryParameter(plan, userInfo);
		ConditionGenerate conditionGenerate = getConditionGenerate(
				DRAFT_CONDITION_GENERATE_KEY, parameter);
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
