package com.cabletech.business.wplan.patrolitem.condition;

import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.wplan.patrolitem.condition.parameter.ItemQueryParameter;
import com.cabletech.business.wplan.patrolitem.model.PatrolItem;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 巡检项查询条件生成器接口基类
 * 
 * @author 杨隽 2012-02-14 创建
 * @author 杨隽 2012-04-26 添加getBusinessTableDataInCondition()方法
 * 
 */
public abstract class BaseConditionGenerate implements ConditionGenerate {
	protected StringBuffer businessDataCondition = new StringBuffer("");
	@SuppressWarnings("rawtypes")
	protected Page page = new Page(SysConstant.DEFAULT_PAGE_SIZE);

	/**
	 * 获取业务表单查询条件sql语句
	 */
	@Override
	public String getBusinessTableDataCondition() {
		// TODO Auto-generated method stub
		return businessDataCondition.toString();
	}

	/**
	 * 获取业务表单内联查询条件sql语句
	 * 
	 * @return String 业务表单内联查询条件sql语句
	 */
	public String getBusinessTableDataInCondition() {
		return "";
	}

	/**
	 * 获取分页信息数据
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Page getPage() {
		// TODO Auto-generated method stub
		return page;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setPage(Page page) {
		// TODO Auto-generated method stub
		this.page = page;
	}

	/**
	 * 根据查询条件参数生成查询条件的条件sql
	 * 
	 * @param parameter
	 *            ItemQueryParameter 查询条件参数
	 * @param queryCondition
	 *            StringBuffer 查询条件sql存放缓冲区
	 */
	public void getConditionSqlByParameter(ItemQueryParameter parameter,
			StringBuffer queryCondition) {
		parameter.setAlias("wpsi");
		if (ItemQueryParameter.NO_SHOW_ALL.equals(parameter.getShowAll())) {
			parameter.setColumnName("state");
			parameter.setValue(PatrolItem.ITEM_START_USING_STATE);
			queryCondition.append(ConditionGenerateUtils
					.getConditionEqualByAndLogicOperator(parameter));
		}
		parameter.setColumnName("item_id");
		parameter.setValue(parameter.getItemId());
		queryCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setAlias("wpi");
		parameter.setColumnName("business_type");
		parameter.setValue(parameter.getBusinessType());
		queryCondition.append(ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter));
		parameter.setColumnName("regionid");
		parameter.setValue(parameter.getRegionId());
		queryCondition.append(BusinessConditionUtils
				.getRegionCondition(parameter));
	}

	/**
	 * 传入参数判断
	 * 
	 * @param parameter
	 *            QueryParameter 传入的参数
	 * @return boolean 传入参数是否为空
	 */
	protected boolean preSetParameter(QueryParameter parameter) {
		if (QueryParameter.isNull(parameter)) {
			return false;
		}
		if (!QueryParameter.IS_QUERY_PARAMETER.equals(parameter.getIsQuery())) {
			parameter.clear();
		}
		return true;
	}
}
