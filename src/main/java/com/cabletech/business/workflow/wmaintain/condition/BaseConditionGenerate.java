package com.cabletech.business.workflow.wmaintain.condition;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 查询条件生成器基类
 * 
 * @author 杨隽 2012-04-12 创建
 * @author 杨隽 2012-04-23 添加putWplanQueryCondition()方法
 * @author 杨隽 2012-04-26 添加getBusinessTableDataInCondition()方法
 * @author 杨隽 2012-04-27 添加putUserCondition()方法
 * @author 杨隽 2012-04-27 修改putQueryCondition()方法（修改“创建人”为模糊查询）
 * 
 */
public abstract class BaseConditionGenerate implements ConditionGenerate {
	// 主体业务数据查询条件缓冲区
	protected StringBuffer businessDataCondition = new StringBuffer("");
	// 级联基础表数据查询条件缓冲区
	protected StringBuffer joinDataCondition = new StringBuffer("");
	// 内部业务数据查询条件缓冲区
	protected StringBuffer innerDataCondition = new StringBuffer("");
	// 缺省页面配置
	@SuppressWarnings("rawtypes")
	protected Page page = new Page(SysConstant.DEFAULT_PAGE_SIZE);

	/**
	 * 获取当前用户创建的查询条件sql
	 * 
	 * @param user
	 *            UserInfo 当前用户信息
	 * @return String 当前用户创建的查询条件sql
	 */
	public String getSelfCreatedCondition(UserInfo user) {
		QueryParameter parameter = new QueryParameter();
		parameter.setAlias("wp");
		parameter.setColumnName("CREATER");
		parameter.setValue(user.getPersonId());
		return ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter);
	}

	/**
	 * 获取业务表单查询条件sql语句
	 * 
	 * @return String 业务表单查询条件sql语句
	 */
	@Override
	public String getBusinessTableDataCondition() {
		// TODO Auto-generated method stub
		return businessDataCondition.toString();
	}

	/**
	 * 获取业务表单内联查询条件sql语句
	 * 
	 * @return String 业务表单内联查询条件sql语句
	 */
	public String getBusinessTableDataInCondition() {
		StringBuffer conditionBuffer = new StringBuffer("");
		conditionBuffer.append(" AND NOT EXISTS ( ");
		conditionBuffer.append(" SELECT wr.PATROLRECORD_ID ");
		conditionBuffer.append(" FROM WMAINTAIN_RESULT wr ");
		conditionBuffer
				.append(" JOIN WMAINTAIN_SITE ws ON wr.MAINTAIN_ID=ws.ID ");
		conditionBuffer.append(" JOIN WMAINTAIN_PLAN wp ON wp.ID=ws.PLAN_ID ");
		conditionBuffer.append(" WHERE wpr.ID=wr.PATROLRECORD_ID ");
		conditionBuffer.append(innerDataCondition);
		conditionBuffer.append(" ) ");
		return conditionBuffer.toString();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Page getPage() {
		// TODO Auto-generated method stub
		return page;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * 将公用的维修作业计划查询条件存放到查询条件中
	 * 
	 * @param parameter
	 *            QueryParameter 查询条件参数
	 */
	protected void putQueryCondition(QueryParameter parameter) {
		WMaintainPlan plan = (WMaintainPlan) parameter.getEntity();
		if (!QueryParameter.isNull(plan)) {
			parameter.setAlias("vu");
			parameter.setColumnName("username");
			parameter.setValue(plan.getCreater());
			businessDataCondition.append(ConditionGenerateUtils
					.getConditionLikeByAndLogicOperator(parameter));
			putPlanCondition(parameter);
			putDateCondition(parameter);
			putRegionCondition(parameter);
			putUserCondition(parameter);
		}
	}

	/**
	 * 获取无线资源计划查询条件sql存放到缓冲区
	 * 
	 * @param parameter
	 *            QueryParameter 查询条件参数
	 */
	protected void putWplanQueryCondition(QueryParameter parameter) {
		WMaintainPlan plan = (WMaintainPlan) parameter.getEntity();
		parameter.setColumnName("start_time");
		parameter.setValue(plan.getCreateDateMin());
		parameter.setOperator(ConditionGenerateUtils.GE_OPERATOR);
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionDateByAndLogicOperator(parameter));
		parameter.setValue(plan.getCreateDateMax());
		parameter.setOperator(ConditionGenerateUtils.LT_OPERATOR);
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionNextDateByAndLogicOperator(parameter));
		parameter.setColumnName("plan_id");
		parameter.setValue(plan.getWplanId());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setColumnName("patrol_group_id");
		parameter.setValue(plan.getPatrolGroup());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setColumnName("business_type");
		parameter.setValue(plan.getBusinessType());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
	}

	/**
	 * 将计划状态查询条件放到查询条件缓冲区中
	 * 
	 * @param value
	 *            String 计划状态值
	 * @param isEqual
	 *            boolean 是否进行等于判断
	 */
	protected void putPlanStateCondition(String value, boolean isEqual) {
		QueryParameter parameter = new QueryParameter();
		parameter.setAlias("wp");
		parameter.setColumnName("PLAN_STATE");
		parameter.setValue(value);
		if (isEqual) {
			businessDataCondition.append(ConditionGenerateUtils
					.getConditionEqualByAndLogicOperator(parameter));
		} else {
			businessDataCondition.append(ConditionGenerateUtils
					.getConditionNotEqualByAndLogicOperator(parameter));
		}
	}

	/**
	 * 将计划查询条件放到查询条件缓冲区中
	 * 
	 * @param parameter
	 *            QueryParameter 查询条件参数
	 */
	private void putPlanCondition(QueryParameter parameter) {
		WMaintainPlan plan = (WMaintainPlan) parameter.getEntity();
		parameter.setAlias("wp");
		parameter.setColumnName("business_type");
		parameter.setValue(plan.getBusinessType());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		businessDataCondition.append(BusinessConditionUtils
				.getBusinessTypeCondition(parameter));
		parameter.setColumnName("plan_name");
		parameter.setValue(plan.getPlanName());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionLikeByAndLogicOperator(parameter));
		parameter.setColumnName("plan_state");
		parameter.setValue(plan.getPlanState());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
	}

	/**
	 * 将区域查询条件放到查询条件缓冲区中
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 */
	private void putRegionCondition(QueryParameter parameter) {
		WMaintainPlan plan = (WMaintainPlan) parameter.getEntity();
		parameter.setAlias("vp");
		parameter.setColumnName("regionid");
		parameter.setValue(plan.getRegionId());
		businessDataCondition.append(BusinessConditionUtils
				.getRegionCondition(parameter));
	}

	/**
	 * 将当前登录用户查询条件放到查询条件缓冲区中
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 */
	private void putUserCondition(QueryParameter parameter) {
		UserInfo userInfo = parameter.getUser();
		if (QueryParameter.isNull(userInfo)) {
			return;
		}
		if (userInfo.isCityContractor()) {
			parameter.setAlias("vp");
			parameter.setColumnName("parentid");
			parameter.setValue(userInfo.getOrgId());
			businessDataCondition.append(BusinessConditionUtils
					.getOrgCondition(parameter));
		}
		if (userInfo.isCityMobile()) {
			parameter.setAlias("vp");
			parameter.setColumnName("regionid");
			parameter.setValue(userInfo.getRegionId());
			businessDataCondition.append(BusinessConditionUtils
					.getRegionCondition(parameter));
		}
	}

	/**
	 * 将日期查询条件放到查询条件缓冲区中
	 * 
	 * @param parameter
	 *            QueryParameter 查询条件参数
	 */
	private void putDateCondition(QueryParameter parameter) {
		WMaintainPlan plan = (WMaintainPlan) parameter.getEntity();
		parameter.setColumnName("create_date");
		parameter.setValue(plan.getCreateDateMin());
		parameter.setOperator(ConditionGenerateUtils.GE_OPERATOR);
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionDateByAndLogicOperator(parameter));
		parameter.setColumnName("create_date");
		parameter.setValue(plan.getCreateDateMax());
		parameter.setOperator(ConditionGenerateUtils.LT_OPERATOR);
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionNextDateByAndLogicOperator(parameter));
	}
}
