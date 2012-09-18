package com.cabletech.business.base.condition;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseEntity;

/**
 * 查询参数类
 * 
 * @author 杨隽 2012-01-04 创建
 * @author 杨隽 2012-04-28 添加值的前缀和后缀属性
 * 
 */
public class QueryParameter extends BaseEntity {
	// 查询条件表单来源参数常量
	public static final String IS_QUERY_PARAMETER = "1";
	private static final long serialVersionUID = 1L;
	// 查询条件的实体对象
	private BaseEntity entity;
	// 查询条件的当前登录用户对象
	private UserInfo user;
	// 查询条件参数来源
	private String isQuery;
	// 表的别名
	private String alias;
	// 查询列名
	private String columnName;
	// 查询值
	private String value;
	// 查询比较运算符
	private String operator = ConditionGenerateUtils.EQU_OPERATOR;
	// 值的前缀
	private String prefix = "";
	// 值的后缀
	private String suffix = "";

	/**
	 * 构造方法
	 */
	public QueryParameter() {
	}

	public BaseEntity getEntity() {
		return entity;
	}

	public void setEntity(BaseEntity entity) {
		this.entity = entity;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * 清空参数数据
	 */
	public void clear() {
		// TODO Auto-generated method stub
		setColumnName("");
		setValue("");
		setOperator("");
		setAlias("");
	}

	/**
	 * 判断对象是否为NULL
	 * 
	 * @param parameter
	 *            Object 要进行判断的对象
	 * @return boolean 对象是否为NULL
	 */
	public static boolean isNull(Object parameter) {
		// TODO Auto-generated method stub
		return (parameter == null);
	}
}
