package com.cabletech.business.workflow.electricity.security.condition;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 查询条件生成器基类
 * 
 * @author 杨隽 2012-05-04 创建
 * @author 杨隽 2012-05-07
 *         补充putQueryCondition()、putUserCondition()、putDateCondition
 *         ()和putOeDispatchTaskStateCondition()方法内容
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
		parameter.setAlias("odt");
		parameter.setColumnName("CREATOR");
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
		conditionBuffer.append(innerDataCondition);
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
	 * 将公用的供电保障查询条件存放到查询条件中
	 * 
	 * @param parameter
	 *            QueryParameter 查询条件参数
	 */
	protected void putQueryCondition(QueryParameter parameter) {
		OeDispatchTask oeDispatchTask = (OeDispatchTask) parameter.getEntity();
		if (!QueryParameter.isNull(oeDispatchTask)) {
			parameter.setAlias("odt");
			parameter.setColumnName("TASK_NAME");
			parameter.setValue(oeDispatchTask.getTitle());
			businessDataCondition.append(ConditionGenerateUtils
					.getConditionLikeByAndLogicOperator(parameter));
			parameter.setColumnName("MAINTENANCE_ID");
			parameter.setValue(oeDispatchTask.getMaintenanceId());
			businessDataCondition.append(ConditionGenerateUtils
					.getConditionEqualByAndLogicOperator(parameter));
			parameter.setColumnName("STATE");
			parameter.setValue(oeDispatchTask.getState());
			businessDataCondition.append(ConditionGenerateUtils
					.getConditionEqualByAndLogicOperator(parameter));
			parameter.setAlias("res");
			parameter.setColumnName("NAME");
			parameter.setValue(oeDispatchTask.getStationName());
			businessDataCondition.append(ConditionGenerateUtils
					.getConditionLikeByAndLogicOperator(parameter));
			putDateCondition(parameter);
			putUserCondition(parameter);
		}
	}

	/**
	 * 将派单状态查询条件放到查询条件缓冲区中
	 * 
	 * @param value
	 *            String 派单状态值
	 * @param isEqual
	 *            boolean 是否进行等于判断
	 */
	protected void putOeDispatchTaskStateCondition(String value, boolean isEqual) {
		QueryParameter parameter = new QueryParameter();
		parameter.setAlias("odt");
		parameter.setColumnName("STATE");
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
			parameter.setAlias("vo");
			parameter.setColumnName("ID");
			parameter.setValue(userInfo.getOrgId());
			businessDataCondition.append(BusinessConditionUtils
					.getOrgCondition(parameter));
		}
		if (userInfo.isCityMobile()) {
			parameter.setAlias("vo");
			parameter.setColumnName("REGIONID");
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
		OeDispatchTask oeDispatchTask = (OeDispatchTask) parameter.getEntity();
		parameter.setAlias("odt");
		parameter.setColumnName("CREATE_DATE");
		parameter.setValue(oeDispatchTask.getCreateDateMin());
		parameter.setOperator(ConditionGenerateUtils.GE_OPERATOR);
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionDateByAndLogicOperator(parameter));
		parameter.setValue(oeDispatchTask.getCreateDateMax());
		parameter.setOperator(ConditionGenerateUtils.LT_OPERATOR);
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionNextDateByAndLogicOperator(parameter));
	}
}
