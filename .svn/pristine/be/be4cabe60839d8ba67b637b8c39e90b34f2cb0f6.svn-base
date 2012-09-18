package com.cabletech.business.workflow.wmaintain.condition;

import org.springframework.stereotype.Component;

import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.QueryParameter;

/**
 * 待办隐患维修作业计划查询条件生成器接口实现
 * 
 * @author 杨隽 2012-04-17 创建
 * 
 */
@Component
public class WmaintainWaitHandledListConditionGenerateImpl extends
		BaseConditionGenerate {
	// 变动连接表单数据信息的sql语句存放缓冲区
	private StringBuffer joinDataCondition = new StringBuffer("");

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
		joinDataCondition = new StringBuffer("");
		if (!QueryParameter.isNull(parameter)) {
			super.putQueryCondition(parameter);
		}
		parameter.setAlias("jbpm");
		BusinessConditionUtils.getWaitHandledCondition(parameter,
				joinDataCondition);
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
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" JOIN ( ");
		sqlBuf.append(" SELECT jbpm.BZID,jbpm.TASKID, ");
		sqlBuf.append(" jbpm.TASKNAME AS PROINST_STATE,jbpm.RES AS PROCESS_URL ");
		sqlBuf.append(" FROM VIEW_JBPM_USERTASK jbpm ");
		sqlBuf.append(" WHERE 1=1 ");
		sqlBuf.append(joinDataCondition);
		sqlBuf.append(" ) join_table ON business_table.ID=join_table.BZID ");
		return sqlBuf.toString();
	}
}
