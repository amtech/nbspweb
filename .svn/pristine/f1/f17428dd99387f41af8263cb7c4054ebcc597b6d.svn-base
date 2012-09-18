package com.cabletech.business.workflow.workorder.condition;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.ConditionGenerateUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 查询条件生成器基类
 * 
 * @author 杨隽 2012-01-04 创建
 * @author 杨隽 2012-01-05 添加putQueryCondition方法
 * @author 杨隽 2012-01-09 修改putQueryCondition方法（在其中添加工单状态的判断条件）
 * @author 杨隽 2012-01-16 添加setPage方法
 * @author 杨隽 2012-02-06 将getCondition()方法改为getBusinessTableDataCondition ()方法
 * @author 杨隽 2012-04-26 添加getBusinessTableDataInCondition()方法
 * 
 */
public abstract class BaseConditionGenerate implements ConditionGenerate {
	/**
	 * 业务数据sql查询条件缓冲区
	 */
	protected StringBuffer businessDataCondition = new StringBuffer("");
	/**
	 * 变动连接表单数据信息查询条件sql缓冲区
	 */
	protected StringBuffer joinDataCondition = new StringBuffer("");
	/**
	 * 缺省分页信息数据
	 */
	@SuppressWarnings("rawtypes")
	protected Page page = new Page(SysConstant.DEFAULT_PAGE_SIZE);

	/**
	 * 获取当前用户创建的查询条件sql
	 * 
	 * @param user
	 *            UserInfo 当前用户信息
	 * @return String 当前用户创建的查询条件sql
	 */
	public String getSelfCreatedCondition(UserInfo user) {
		QueryParameter parameter = new QueryParameter();
		parameter.setAlias("wo");
		parameter.setColumnName("creater");
		parameter.setValue(user.getPersonId());
		return ConditionGenerateUtils
				.getConditionEqualByAndLogicOperator(parameter);
	}

	/**
	 * 获取业务表单查询条件sql语句
	 * 
	 * @return String 业务表单查询条件sql语句
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

	@Override
	@SuppressWarnings("rawtypes")
	public Page getPage() {
		// TODO Auto-generated method stub
		return page;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * 将公用的通用工单查询条件存放到查询条件中
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 */
	protected void putQueryCondition(QueryParameter parameter) {
		WorkOrder workOrder = (WorkOrder) parameter.getEntity();
		if (!QueryParameter.isNull(workOrder)) {
			parameter.setAlias("wo");
			parameter.setColumnName("bussines_type");
			if (SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C20
					.equals(workOrder.getBusinessType())) {
				businessDataCondition.append(" and wo.bussines_type is null ");
			} else {
				parameter.setValue(workOrder.getBusinessType());
				businessDataCondition.append(ConditionGenerateUtils
						.getConditionEqualByAndLogicOperator(parameter));
			}
			parameter.setColumnName("task_name");
			parameter.setValue(workOrder.getTaskName());
			businessDataCondition.append(ConditionGenerateUtils
					.getConditionLikeByAndLogicOperator(parameter));
			parameter.setColumnName("creater");
			parameter.setValue(workOrder.getCreater());
			businessDataCondition.append(ConditionGenerateUtils
					.getConditionEqualByAndLogicOperator(parameter));
			putDateCondition(parameter);
			parameter.setColumnName("task_type");
			parameter.setValue(workOrder.getTaskType());
			businessDataCondition.append(ConditionGenerateUtils
					.getConditionEqualByAndLogicOperator(parameter));
			parameter.setAlias("wt");
			parameter.setColumnName("task_state");
			parameter.setValue(workOrder.getTaskState());
			businessDataCondition.append(ConditionGenerateUtils
					.getConditionEqualByAndLogicOperator(parameter));
			parameter.setAlias("vu");
			parameter.setColumnName("username");
			parameter.setValue(workOrder.getCreaterName());
			businessDataCondition.append(ConditionGenerateUtils
					.getConditionLikeByAndLogicOperator(parameter));
		}
	}

	/**
	 * 将日期查询条件放到查询条件sql缓冲区中
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 */
	private void putDateCondition(QueryParameter parameter) {
		WorkOrder workOrder = (WorkOrder) parameter.getEntity();
		parameter.setColumnName("create_date");
		parameter.setValue(workOrder.getCreateDateMin());
		parameter.setOperator(ConditionGenerateUtils.GE_OPERATOR);
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionDateByAndLogicOperator(parameter));
		parameter.setColumnName("create_date");
		parameter.setValue(workOrder.getCreateDateMax());
		parameter.setOperator(ConditionGenerateUtils.LT_OPERATOR);
		businessDataCondition.append(ConditionGenerateUtils
				.getConditionNextDateByAndLogicOperator(parameter));
	}
}
