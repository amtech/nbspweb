package com.cabletech.business.workflow.workorder.condition;

import org.springframework.stereotype.Component;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.QueryParameter;

/**
 * 草稿箱查询条件生成器接口实现
 * 
 * @author 杨隽 2012-01-04 创建
 * @author 杨隽 2012-01-05 提取公共的查询条件获取方法
 * @author 杨隽 2012-01-09 在setQuerySql方法中去除工单状态的查询条件
 * @author 杨隽 2012-02-06 添加getJoinTableSql()方法
 * 
 */
@Component
public class DraftConditionGenerateImpl extends BaseConditionGenerate {
	/**
	 * 根据查询参数来设置查询的sql条件语句
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 */
	@Override
	public void setQuerySql(QueryParameter parameter) {
		super.businessDataCondition = new StringBuffer("");
		// TODO Auto-generated method stub
		// 输入查询条件
		super.putQueryCondition(parameter);
		// 当前登录用户创建
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
	 * @return String 变动表单数据信息的sql语句
	 */
	@Override
	public String getJoinTableSql() {
		// TODO Auto-generated method stub
		return "";
	}
}
