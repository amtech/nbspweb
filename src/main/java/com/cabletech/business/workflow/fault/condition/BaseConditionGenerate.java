package com.cabletech.business.workflow.fault.condition;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 故障派单查询条件生成器接口基类
 * 
 * @author 杨隽 2012-02-08 创建
 * @author 杨隽 2012-02-09 添加getConditionSqlByParameter()方法
 * @author 杨隽 2012-04-26 添加getBusinessTableDataInCondition()方法
 * 
 */
public abstract class BaseConditionGenerate implements ConditionGenerate {
	// 业务数据sql查询条件缓冲区
	protected StringBuffer businessDataCondition = new StringBuffer("");
	// 缺省页面分页参数
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
		parameter.setAlias("t");
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
		StringBuffer conditionBuffer = new StringBuffer("");
		conditionBuffer.append(businessDataCondition.toString());
		return conditionBuffer.toString();
	}

	/**
	 * 获取业务表单内联查询条件sql语句
	 * 
	 * @return String 业务表单内联查询条件sql语句
	 */
	public String getBusinessTableDataInCondition() {
		return "";
	}

	/**
	 * 获取分页信息数据
	 * 
	 * @return Page 分页信息数据
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public Page getPage() {
		// TODO Auto-generated method stub
		return page;
	}

	/**
	 * 设置分页信息数据
	 * 
	 * @param page
	 *            Page 分页信息数据
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * 根据查询条件参数生成查询条件的条件sql
	 * 
	 * @param parameter
	 *            FaultQueryParameter 查询条件参数
	 * @param queryCondition
	 *            StringBuffer 查询条件sql存放缓冲区
	 */
	public void getConditionSqlByParameter(FaultQueryParameter parameter,
			StringBuffer queryCondition) {
		parameter.setColumnName("BUSINESS_TYPE");
		parameter.setValue(parameter.getBusinessType());
		queryCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		queryCondition.append(BusinessConditionUtils
				.getBusinessTypeCondition(parameter));
		parameter.setColumnName("TROUBLE_TITLE");
		parameter.setValue(parameter.getTroubleTitle());
		queryCondition.append(ConditionGenerateUtils
				.getConditionLikeByAndLogicOperator(parameter));
		parameter.setColumnName("EOMS_ID");
		parameter.setValue(parameter.getEomsId());
		queryCondition.append(ConditionGenerateUtils
				.getConditionLikeByAndLogicOperator(parameter));
		parameter.setColumnName("ADDRESS");
		parameter.setValue(parameter.getAddress());
		queryCondition.append(ConditionGenerateUtils
				.getConditionLikeByAndLogicOperator(parameter));
		parameter.setColumnName("STATION_ID");
		parameter.setValue(parameter.getStationId());
		queryCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setColumnName("IS_INSTANCY");
		parameter.setValue(parameter.getIsInstancy());
		queryCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setColumnName("FIND_TYPE");
		parameter.setValue(parameter.getFindType());
		queryCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		putDateCondition(parameter, queryCondition);
		putRegionCondition(parameter, queryCondition);
		parameter.setAlias("rr");
		parameter.setColumnName("NAME");
		parameter.setValue(parameter.getResourceName());
		queryCondition.append(ConditionGenerateUtils
				.getConditionLikeByAndLogicOperator(parameter));
	}

	/**
	 * 将区域查询条件放到查询条件sql存放缓冲区
	 * 
	 * @param parameter
	 *            FaultQueryParameter 查询条件参数
	 * @param queryCondition
	 *            StringBuffer 查询条件sql存放缓冲区
	 */
	private void putRegionCondition(FaultQueryParameter parameter,
			StringBuffer queryCondition) {
		if (!QueryParameter.isNull(parameter.getUser())) {
			String regionId = parameter.getUser().getRegionId();
			parameter.setAlias("rr");
			parameter.setColumnName("REGIONID");
			parameter.setValue(regionId);
			queryCondition.append(BusinessConditionUtils
					.getRegionCondition(parameter));
		}
	}

	/**
	 * 将日期查询条件放到查询条件sql存放缓冲区
	 * 
	 * @param parameter
	 *            FaultQueryParameter 查询条件参数
	 * @param queryCondition
	 *            StringBuffer 查询条件sql存放缓冲区
	 */
	private void putDateCondition(FaultQueryParameter parameter,
			StringBuffer queryCondition) {
		parameter.setColumnName("TROUBLE_TIME");
		parameter.setValue(parameter.getTroubleTimeStart());
		parameter.setOperator(ConditionGenerateUtils.GE_OPERATOR);
		queryCondition.append(ConditionGenerateUtils
				.getConditionDateByAndLogicOperator(parameter));
		parameter.setColumnName("TROUBLE_TIME");
		parameter.setValue(parameter.getTroubleTimeEnd());
		parameter.setOperator(ConditionGenerateUtils.LT_OPERATOR);
		queryCondition.append(ConditionGenerateUtils
				.getConditionNextDateByAndLogicOperator(parameter));
	}

	/**
	 * 传入参数判断
	 * 
	 * @param parameter
	 *            QueryParameter 传入的参数
	 * @return boolean 传入参数是否为空
	 */
	protected boolean preSetParameter(QueryParameter parameter) {
		if (QueryParameter.isNull(parameter)) {
			return false;
		}
		if (!QueryParameter.IS_QUERY_PARAMETER.equals(parameter.getIsQuery())) {
			parameter.clear();
		}
		return true;
	}
}
