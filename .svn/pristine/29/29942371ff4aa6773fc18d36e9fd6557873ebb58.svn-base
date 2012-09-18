package com.cabletech.business.workflow.workorder.condition;

import org.springframework.stereotype.Component;

import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.QueryParameter;

/**
 * 通用工单已办工作查询条件生成器接口实现
 * 
 * @author 杨隽 2012-02-06 创建
 * @author 杨隽 2012-02-09 提取已办查询条件生成公共方法
 * 
 */
@Component
public class HandledTaskConditionGenerateImpl extends BaseConditionGenerate {
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
		parameter.setAlias("jbpm");
		BusinessConditionUtils.getHandledCondition(parameter,
				super.joinDataCondition);
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
		sqlBuf.append(super.joinDataCondition);
		sqlBuf.append(" ) join_table ON business_table.PID=join_table.BZID ");
		return sqlBuf.toString();
	}
}
