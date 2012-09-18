package com.cabletech.business.workflow.wmaintain.condition;

import org.springframework.stereotype.Component;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;

/**
 * 隐患计划已取消查询条件生成器接口实现
 * 
 * @author 王甜 2012-04-17 创建
 * 
 */
@Component
public class WmaintainCanceledListConditionGenerateImpl extends
		BaseConditionGenerate {
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
		if (!QueryParameter.isNull(parameter)) {
			super.putQueryCondition(parameter);
		}
		super.putPlanStateCondition(WMaintainPlan.WMAINTAIN_CANCELED_STATE,
				true);
		UserInfo user = parameter.getUser();
		super.businessDataCondition.append(super.getSelfCreatedCondition(user));
	}

	/**
	 * 获取排序条件sql语句
	 * 
	 * @return String 排序条件sql语句
	 */
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return " ORDER BY business_table.CREATE_DATE DESC,business_table.ID DESC ";
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
}
