package com.cabletech.business.workflow.workorder.condition;

import org.springframework.stereotype.Component;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.workorder.model.WorkOrder;

/**
 * 待取消通用工单查询条件生成器接口实现
 * 
 * @author 杨隽 2012-02-06 创建
 * 
 */
@Component
public class CanceledTaskConditionGenerateImpl extends BaseConditionGenerate {
	/**
	 * 根据查询参数来设置查询的sql条件语句
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 */
	@Override
	public void setQuerySql(QueryParameter parameter) {
		// TODO Auto-generated method stub
		super.businessDataCondition = new StringBuffer("");
		super.joinDataCondition = new StringBuffer("");
		super.putQueryCondition(parameter);
		// 工单状态查询条件
		parameter.setAlias("wt");
		parameter.setColumnName("task_state");
		parameter.setValue(WorkOrder.WORKORDER_CANCELED_STATE);
		super.businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		// 当前用户查询条件
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
	 * @return String 变动表单数据信息的sql语句
	 */
	@Override
	public String getJoinTableSql() {
		// TODO Auto-generated method stub
		return "";
	}
}
