package com.cabletech.business.workflow.wmaintain.condition;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;

/**
 * 无线资源计划中存在问题站点查询条件生成器接口实现
 * 
 * @author 杨隽 2012-04-23 创建
 * @author 杨隽 2012-04-26 添加putNotMaintainResCondition()方法
 * 
 */
@Component
public class WmPlanResourceConditionGenerateImpl extends BaseConditionGenerate {
	/**
	 * 根据查询参数来设置查询的sql条件语句
	 * 
	 * @param parameter
	 *            QueryParameter 查询条件参数
	 */
	@Override
	public void setQuerySql(QueryParameter parameter) {
		// TODO Auto-generated method stub
		super.businessDataCondition = new StringBuffer("");
		parameter.setAlias("t_");
		super.putWplanQueryCondition(parameter);
		WMaintainPlan plan = (WMaintainPlan) parameter.getEntity();
		parameter.setColumnName("resource_type");
		parameter.setValue(plan.getResourceType());
		super.businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setColumnName("resource_id");
		parameter.setValue(plan.getResourceId());
		super.businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		putNotMaintainResCondition(plan);
	}

	/**
	 * 获取排序条件sql语句
	 * 
	 * @return String 排序条件sql语句
	 */
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return " ORDER BY business_table.ID ";
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
	 * 将不在维修计划中资源异常项的查询条件sql放到缓冲区中
	 * 
	 * @param plan
	 *            WMaintainPlan 维修计划查询实体
	 */
	private void putNotMaintainResCondition(WMaintainPlan plan) {
		String id = "-1";
		if (StringUtils.isNotBlank(plan.getId())) {
			id = plan.getId();
		}
		super.innerDataCondition = new StringBuffer("");
		super.innerDataCondition.append(" AND wp.ID<>'");
		super.innerDataCondition.append(id);
		super.innerDataCondition.append("' ");
	}
}
