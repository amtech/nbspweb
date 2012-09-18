package com.cabletech.business.wplan.patrolitem.condition;

import org.springframework.stereotype.Service;

import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.wplan.patrolitem.condition.parameter.ItemQueryParameter;

/**
 * 巡检项查询条件生成器接口实现
 * 
 * @author 杨隽 2012-02-14 创建
 * 
 */
@Service
public class ItemConditionGenerate extends BaseConditionGenerate {
	@Override
	public void setQuerySql(QueryParameter parameter) {
		// TODO Auto-generated method stub
		super.businessDataCondition = new StringBuffer("");
		if (super.preSetParameter(parameter)) {
			super.getConditionSqlByParameter((ItemQueryParameter) parameter,
					super.businessDataCondition);
			parameter.setAlias("wpi");
			parameter.setColumnName("business_type");
			super.businessDataCondition.append(BusinessConditionUtils
					.getBusinessTypeCondition(parameter));
		}
	}

	/**
	 * 获取变动连接表单数据信息的sql语句
	 */
	@Override
	public String getJoinTableSql() {
		// TODO Auto-generated method stub
		return " ORDER BY business_table.ITEM_ID ";
	}

	/**
	 * 获取排序条件sql语句
	 */
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return "";
	}
}
