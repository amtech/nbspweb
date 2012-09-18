package com.cabletech.business.ah.rating.condition;

import org.springframework.stereotype.Service;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.common.util.Page;

/**
 * 考核表编号查询条件生成器接口实现
 * 
 * @author 杨隽 2012-06-26 创建
 * 
 */
@Service
public class RatingFormIdConditionGenerate implements ConditionGenerate {
	private StringBuffer businessDataCondition;
	private Page page;

	@Override
	public void setQuerySql(QueryParameter parameter) {
		businessDataCondition = new StringBuffer("");
		if (!QueryParameter.isNull(parameter)) {
			String tableId = parameter.getId();
			parameter.setAlias("t");
			parameter.setColumnName("TABLE_ID");
			parameter.setValue(tableId);
			businessDataCondition.append(ConditionGenerateUtils
					.getConditionEqualByAndLogicOperator(parameter));
		}
	}

	/**
	 * 获取变动连接表单数据信息的sql语句
	 */
	@Override
	public String getJoinTableSql() {
		return "";
	}

	/**
	 * 获取排序条件sql语句
	 */
	@Override
	public String getOrder() {
		return " ORDER BY business_table.ID ";
	}

	@Override
	public String getBusinessTableDataCondition() {
		return businessDataCondition.toString();
	}

	@Override
	public String getBusinessTableDataInCondition() {
		return "";
	}

	@Override
	public Page getPage() {
		return page;
	}

	@Override
	public void setPage(Page page) {
		this.page = page;
	}
}
