package com.cabletech.business.workflow.fault.condition;

import org.springframework.stereotype.Component;

import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;

/**
 * 故障派单已办工作查询条件生成器接口实现
 * 
 * @author 杨隽 2012-02-22 创建
 * 
 */
@Component
public class DispatchFaultHandledListConditionGenerateImpl extends
		BaseConditionGenerate {
	/**
	 * 变动连接表单数据信息查询条件sql缓冲区
	 */
	private StringBuffer joinDataCondition;

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
		joinDataCondition = new StringBuffer("");
		if (super.preSetParameter(parameter)) {
			parameter.setAlias("wa");
			super.getConditionSqlByParameter((FaultQueryParameter) parameter,
					super.businessDataCondition);
		}
		parameter.setAlias("jbpm");
		BusinessConditionUtils
				.getHandledCondition(parameter, joinDataCondition);
	}

	/**
	 * 获取排序条件sql语句
	 * 
	 * @return String 排序条件sql语句
	 */
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return " ORDER BY business_table.TROUBLE_TIME DESC,business_table.ID DESC ";
	}

	/**
	 * 获取变动连接表单数据信息的sql语句（与工作流处理人员视图、已办工作视图进行关联）
	 * 
	 * @return String 变动表单数据信息的sql语句
	 */
	@Override
	public String getJoinTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" JOIN ( ");
		sqlBuf.append(" SELECT du.DEALUSER,du.DEALGROUP,du.BZID ");
		sqlBuf.append(" FROM VIEW_JBPM_DEALUSERS du ");
		sqlBuf.append(" JOIN VIEW_JBPM_USERATTACHS jbpm ON du.BZID = jbpm.BZID ");
		sqlBuf.append(" WHERE 1=1 ");
		sqlBuf.append(joinDataCondition);
		sqlBuf.append(" ) join_table ON business_table.DISPATCH_ID=join_table.BZID ");
		return sqlBuf.toString();
	}
}
