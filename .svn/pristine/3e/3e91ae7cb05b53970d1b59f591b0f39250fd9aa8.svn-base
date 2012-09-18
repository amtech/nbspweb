package com.cabletech.business.workflow.electricity.security.condition;

import org.springframework.stereotype.Component;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;

/**
 * 供电保障派单待取消查询条件生成器接口实现
 * 
 * @author 杨隽 2012-05-04 创建
 * 
 */
@Component
public class OeDispatchWaitCanceledListConditionGenerateImpl extends
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
		super.putOeDispatchTaskStateCondition(
				OeDispatchTask.WAIT_DISPATCHED_STATE, false);
		super.putOeDispatchTaskStateCondition(OeDispatchTask.END_STATE, false);
		super.putOeDispatchTaskStateCondition(OeDispatchTask.CANCELED_STATE,
				false);
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
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" LEFT JOIN ( ");
		sqlBuf.append(" SELECT du.DEALUSER,du.DEALGROUP,du.BZID ");
		sqlBuf.append(" FROM VIEW_JBPM_DEALUSERS du ");
		sqlBuf.append(" WHERE 1=1 ");
		sqlBuf.append(joinDataCondition);
		sqlBuf.append(" ) join_table ON business_table.ID=join_table.BZID ");
		return sqlBuf.toString();
	}
}
