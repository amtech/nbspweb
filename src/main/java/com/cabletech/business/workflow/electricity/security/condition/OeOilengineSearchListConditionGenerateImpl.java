package com.cabletech.business.workflow.electricity.security.condition;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.common.base.SysConstant;

/**
 * 油机信息搜索生成器接口实现
 * 
 * @author 杨隽 2012-05-09 创建
 * 
 */
@Component
public class OeOilengineSearchListConditionGenerateImpl extends
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
		super.innerDataCondition = new StringBuffer("");
		if (QueryParameter.isNull(parameter)) {
			return;
		}
		OeDispatchTask oeDispatchTask = (OeDispatchTask) parameter.getEntity();
		if (!QueryParameter.isNull(oeDispatchTask)) {
			getResourceCondition(oeDispatchTask);
			getOilengineCondition(oeDispatchTask);
		}
	}

	/**
	 * 获取排序条件sql语句
	 * 
	 * @return String 排序条件sql语句
	 */
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return " ORDER BY business_table.DISTANCE,business_table.RATION_POWER DESC,business_table.STANDARD_OILWEAR,business_table.ID ";
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

	/**
	 * 获取油机查询条件
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 查询条件参数
	 */
	private void getOilengineCondition(OeDispatchTask oeDispatchTask) {
		QueryParameter parameter = new QueryParameter();
		String oilEngineId = oeDispatchTask.getOilengineId();
		if (StringUtils.isNotBlank(oilEngineId)) {
			String[] oilEngineIdArray = oilEngineId.split(",");
			if (!ArrayUtils.isEmpty(oilEngineIdArray)) {
				oilEngineId = "";
				for (int i = 0; i < oilEngineIdArray.length; i++) {
					oilEngineId += "'";
					oilEngineId += oilEngineIdArray[i];
					oilEngineId += "'";
					oilEngineId += ",";
				}
				oilEngineId = oilEngineId
						.substring(0, oilEngineId.length() - 1);
			}
		}
		parameter.setAlias("t_");
		parameter.setColumnName("ID");
		parameter.setValue(oilEngineId);
		super.businessDataCondition.append(ConditionGenerateUtils
				.getConditionInByAndLogicOperator(parameter));
	}

	/**
	 * 获取资源查询条件
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 查询条件参数
	 */
	private void getResourceCondition(OeDispatchTask oeDispatchTask) {
		QueryParameter parameter = new QueryParameter();
		super.innerDataCondition.append(" ON ");
		parameter.setAlias("res");
		parameter.setColumnName("TYPE");
		parameter.setValue(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C31);
		super.innerDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByNoLogicOperator(parameter));
		parameter.setAlias("res");
		parameter.setColumnName("ID");
		parameter.setValue(oeDispatchTask.getStationId());
		super.innerDataCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
	}
}
