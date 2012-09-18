package com.cabletech.business.satisfy.condition;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.satisfy.model.Satisfaction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 客户满意度评价查询条件生成器接口实现
 * 
 * @author 杨隽 2012-04-21 创建
 * @author 杨隽 2012-04-26 添加getBusinessTableDataInCondition()方法
 * 
 */
@Component
public class SatisfactionConditionGenerateImpl implements ConditionGenerate {
	// 页面分页器属性
	@SuppressWarnings("rawtypes")
	private Page page = new Page(SysConstant.DEFAULT_PAGE_SIZE);
	// 业务数据表查询条件存放缓冲区
	private StringBuffer businessDataCondition;

	/**
	 * 根据查询参数来设置查询的sql条件语句
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 */
	@Override
	public void setQuerySql(QueryParameter parameter) {
		businessDataCondition = new StringBuffer("");
		putQueryCondition(parameter);
	}

	/**
	 * 获取排序条件sql语句
	 * 
	 * @return String 排序条件sql语句
	 */
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return " ORDER BY business_table.REPLYTIME DESC,business_table.ID DESC ";
	}

	/**
	 * 获取变动连接表单数据信息的sql语句
	 * 
	 * @return String 变动连接表单数据信息的sql语句
	 */
	@Override
	public String getJoinTableSql() {
		// TODO Auto-generated method stub
		return "";
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
		return "";
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Page getPage() {
		// TODO Auto-generated method stub
		return page;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setPage(Page page) {
		// TODO Auto-generated method stub
		this.page = page;
	}

	/**
	 * 根据页面查询条件获取业务数据表查询条件组成sql
	 * 
	 * @param parameter
	 *            QueryParameter 页面查询条件
	 */
	private void putQueryCondition(QueryParameter parameter) {
		// TODO Auto-generated method stub
		Satisfaction satisfaction = (Satisfaction) parameter.getEntity();
		UserInfo user = parameter.getUser();
		parameter.setAlias("ws");
		parameter.setColumnName("TASK_TYPE");
		parameter.setValue(satisfaction.getTaskType());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setColumnName("SATISFACTION");
		parameter.setValue(satisfaction.getSatisfaction());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		putDateCondition(satisfaction);
		putRegionCondition(satisfaction, user);
		putOrgCondition(satisfaction, user);
	}

	/**
	 * 根据页面中的日期查询条件生成业务数据表的日期查询条件sql
	 * 
	 * @param satisfaction
	 *            Satisfaction 页面查询条件
	 */
	private void putDateCondition(Satisfaction satisfaction) {
		QueryParameter parameter = new QueryParameter();
		parameter.setAlias("ws");
		parameter.setColumnName("SENDTIME");
		parameter.setValue(satisfaction.getReplyTimeStart());
		parameter.setOperator(ConditionGenerateUtils.GE_OPERATOR);
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionDateByAndLogicOperator(parameter));
		parameter.setValue(satisfaction.getReplyTimeEnd());
		parameter.setOperator(ConditionGenerateUtils.LT_OPERATOR);
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionNextDateByAndLogicOperator(parameter));
	}

	/**
	 * 根据页面中的区域查询条件生成业务数据表的区域查询条件sql
	 * 
	 * @param satisfaction
	 *            Satisfaction 页面查询条件
	 * @param user
	 *            UserInfo 当前登录用户信息
	 */
	private void putRegionCondition(Satisfaction satisfaction, UserInfo user) {
		QueryParameter parameter = new QueryParameter();
		String regionId = "";
		if (!QueryParameter.isNull(user)) {
			regionId = user.getRegionId();
		}
		if (StringUtils.isNotBlank(satisfaction.getRegionId())) {
			regionId = satisfaction.getRegionId();
		}
		parameter.setAlias("vr");
		parameter.setColumnName("REGIONID");
		parameter.setValue(regionId);
		businessDataCondition.append(BusinessConditionUtils
				.getRegionCondition(parameter));
	}

	/**
	 * 根据页面中的组织查询条件生成业务数据表的组织查询条件sql
	 * 
	 * @param satisfaction
	 *            Satisfaction 页面查询条件
	 * @param user
	 *            UserInfo 当前登录用户信息
	 */
	private void putOrgCondition(Satisfaction satisfaction, UserInfo user) {
		QueryParameter parameter = new QueryParameter();
		String orgId = "";
		if (!QueryParameter.isNull(user) && user.isContractor()) {
			orgId = user.getOrgId();
		}
		if (StringUtils.isNotBlank(satisfaction.getOrgId())) {
			orgId = satisfaction.getOrgId();
		}
		parameter.setAlias("vp");
		parameter.setColumnName("ORGID");
		parameter.setValue(orgId);
		businessDataCondition.append(BusinessConditionUtils
				.getOrgCondition(parameter));
	}
}
