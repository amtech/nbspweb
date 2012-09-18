package com.cabletech.business.workflow.accident.condition;

import org.springframework.stereotype.Component;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.accident.model.MmAccident;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 待处理隐患查询条件生成器接口实现
 * 
 * @author 杨隽 2012-08-27 创建
 * 
 */
@Component
public class WaitProcessAccidentConditionGenerateImpl implements
		ConditionGenerate {
	/**
	 * 业务数据sql查询条件缓冲区
	 */
	private StringBuffer businessDataCondition;
	/**
	 * 缺省分页信息数据
	 */
	@SuppressWarnings("rawtypes")
	protected Page page = new Page(SysConstant.DEFAULT_PAGE_SIZE);

	/**
	 * 根据查询参数来设置查询的sql条件语句
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 */
	@Override
	public void setQuerySql(QueryParameter parameter) {
		// TODO Auto-generated method stub
		MmAccident accident = (MmAccident) parameter.getEntity();
		businessDataCondition = new StringBuffer("");
		parameter.setAlias("MAI");
		parameter.setColumnName("NAME");
		parameter.setValue(accident.getName());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionLikeByAndLogicOperator(parameter));
		parameter.setColumnName("PATROLGROUPID");
		parameter.setValue(accident.getPatrolGroupId());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setColumnName("CREATER");
		parameter.setValue(accident.getCreater());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionLikeByAndLogicOperator(parameter));
		parameter.setColumnName("BUSINESS_TYPE");
		parameter.setValue(accident.getBusinessType());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setColumnName("ACCIDENT_TYPE");
		parameter.setValue(accident.getAccidentType());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setColumnName("CREATE_TIME");
		parameter.setOperator(ConditionGenerateUtils.GE_OPERATOR);
		parameter.setValue(accident.getCreateTimeStart());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionDateByAndLogicOperator(parameter));
		parameter.setOperator(ConditionGenerateUtils.LT_OPERATOR);
		parameter.setValue(accident.getCreateTimeEnd());
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionNextDateByAndLogicOperator(parameter));
		parameter.setColumnName("STATUS");
		parameter.setValue(MmAccident.WAIT_PROCESSED_STATE);
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
	}

	/**
	 * 获取排序条件sql语句
	 * 
	 * @return String 排序条件sql语句
	 */
	@Override
	public String getOrder() {
		return " ORDER BY business_table.CREATE_TIME DESC,business_table.ID DESC ";
	}

	/**
	 * 获取变动连接表单数据信息的sql语句
	 * 
	 * @return String 变动表单数据信息的sql语句
	 */
	@Override
	public String getJoinTableSql() {
		StringBuffer sqlBuf = new StringBuffer("");
		return sqlBuf.toString();
	}

	@Override
	public String getBusinessTableDataCondition() {
		return businessDataCondition.toString();
	}

	@Override
	public String getBusinessTableDataInCondition() {
		// TODO Auto-generated method stub
		return "";
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Page getPage() {
		return page;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setPage(Page page) {
		this.page = page;
	}
}
