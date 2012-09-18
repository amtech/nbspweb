package com.cabletech.business.wplan.patrolitem.condition;

import org.springframework.stereotype.Service;

import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.wplan.patrolitem.condition.parameter.ItemQueryParameter;
import com.cabletech.business.wplan.patrolitem.model.PatrolItem;

/**
 * 巡检项编号和计划模板编号查询条件生成器接口实现
 * 
 * @author 杨隽 2012-02-14 创建
 * 
 */
@Service
public class ItemIdAndTemplateIdConditionGenerate extends BaseConditionGenerate {
	private StringBuffer joinDataCondition = new StringBuffer("");

	@Override
	public void setQuerySql(QueryParameter parameter) {
		// TODO Auto-generated method stub
		super.businessDataCondition = new StringBuffer("");
		joinDataCondition = new StringBuffer("");
		if (!QueryParameter.isNull(parameter)) {
			super.getConditionSqlByParameter((ItemQueryParameter) parameter,
					super.businessDataCondition);
		}
		parameter.setAlias("wpsi");
		parameter.setColumnName("state");
		parameter.setValue(PatrolItem.ITEM_START_USING_STATE);
		super.businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		String templateId = ((ItemQueryParameter) parameter).getTemplateId();
		parameter.setAlias("template");
		parameter.setColumnName("template_id");
		parameter.setValue(templateId);
		joinDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
	}

	/**
	 * 获取变动连接表单数据信息的sql语句
	 */
	@Override
	public String getJoinTableSql() {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" LEFT JOIN ( ");
		sqlBuf.append(" SELECT template.* ");
		sqlBuf.append(" FROM WPLAN_TEMPLATESUBITEM template ");
		sqlBuf.append(" WHERE 1=1 ");
		sqlBuf.append(joinDataCondition);
		sqlBuf.append(" ) join_table ");
		sqlBuf.append(" ON join_table.subitem_id = business_table.id ");
		return sqlBuf.toString();
	}

	/**
	 * 获取排序条件sql语句
	 */
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return " ORDER BY business_table.ITEM_ID ";
	}
}
