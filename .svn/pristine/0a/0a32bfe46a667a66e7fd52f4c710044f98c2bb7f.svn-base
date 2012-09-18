package com.cabletech.business.base.condition;


import org.apache.commons.lang.StringUtils;


/**
 * sql查询条件生成器工具类
 * 
 * @author 杨隽 2012-01-04 创建
 * @author 杨隽 2012-01-05 添加getConditionEqualByNoLogicOperator方法
 * @author 杨隽 2012-02-09 添加getWaitHandledCondition()方法和getHandledCondition()方法
 * @author 杨隽 2012-02-14 添加getRegionCondition()方法
 * @author 杨隽 2012-04-21 添加getOrgCondition()方法
 * @author 杨隽 2012-04-28 进行方法的参数个数重构
 * @author 杨隽 2012-05-09 添加IN运算符常量和getConditionInByAndLogicOperator()方法
 * 
 */
public class ConditionGenerateUtils {
	// 左小括号常量
	public static final String LEFT_PARENTHESIS = " ( ";
	// 右小括号常量
	public static final String RIGHT_PARENTHESIS = " ) ";
	// 逻辑运算符“AND”常量
	public static final String AND_LOGIC_OPERATOR = " AND ";
	// 逻辑运算符“OR”常量
	public static final String OR_LOGIC_OPERATOR = " OR ";
	// LIKE的默认值常量
	public static final String LIKE_DEFAULT_VALUE = "'%'";
	// 比较运算符“=”常量
	public static final String EQU_OPERATOR = "=";
	// 比较运算符“<>”常量
	public static final String NOT_EQU_OPERATOR = "<>";
	// 比较运算符“like”常量
	public static final String LIKE_OPERATOR = "like";
	// 比较运算符“>=”常量
	public static final String GE_OPERATOR = ">=";
	// 比较运算符“<=”常量
	public static final String LE_OPERATOR = "<=";
	// 比较运算符“>”常量
	public static final String GT_OPERATOR = ">";
	// 比较运算符“<”常量
	public static final String LT_OPERATOR = "<";
	// IN运算符常量
	public static final String IN_OPERATOR = "IN";
	// 空串前缀
	public static final String EMPTY_PREFIX = "";
	// 比较运算符LIKE值的前缀
	public static final String LIKE_PREFIX = "'%";
	// 比较运算符LIKE值的后缀
	public static final String LIKE_SUFFIX = "%'";
	// 比较运算符=的前缀和后缀
	public static final String EQU_PREFIX = "'";
	// 开始时间的时分秒值
	public static final String FIRST_DATE_HOUR = "00:00:00";
	// 结束时间的时分秒值
	public static final String LAST_DATE_HOUR = "23:59:59";
	// 日期模板
	public static final String DATE_TEMPLATE = "to_date('#input_date#','#date_pattern#')";
	// 日期的下一日模板
	public static final String NEXT_DATE_TEMPLATE = "to_date('#input_date#','#date_pattern#')+1";

	/**
	 * 根据输入获取sql查询条件
	 * 
	 * @param logicOperator
	 *            String 逻辑运算符
	 * @param parameter
	 *            QueryParameter 查询条件参数
	 * @return String 查询条件sql字符串
	 */
	public static String getCondition(String logicOperator,
			QueryParameter parameter) {
		StringBuffer buf = new StringBuffer("");
		if (StringUtils.isBlank(parameter.getValue())) {
			return buf.toString();
		}
		buf.append(" ");
		buf.append(logicOperator);
		buf.append(" ");
		if (StringUtils.isNotBlank(parameter.getAlias())) {
			buf.append(parameter.getAlias());
			buf.append(".");
		}
		buf.append(parameter.getColumnName());
		buf.append(" ");
		buf.append(parameter.getOperator());
		buf.append(" ");
		buf.append(parameter.getPrefix());
		buf.append(parameter.getValue());
		buf.append(parameter.getSuffix());
		buf.append(" ");
		return buf.toString();
	}

	/**
	 * 获取“逻辑与”条件下的“日期”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询条件参数
	 * @param dateTemplate
	 *            String 日期模板
	 * @param pattern
	 *            String 日期匹配格式字符串
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionDateByAndLogicOperator(
			QueryParameter parameter, String dateTemplate, String pattern) {
		parameter.setPrefix("");
		parameter.setSuffix("");
		if (StringUtils.isBlank(parameter.getValue())) {
			parameter.setOperator(EQU_OPERATOR);
			parameter.setValue("");
			return getCondition(AND_LOGIC_OPERATOR, parameter);
		}
		String time = dateTemplate.replaceAll("#input_date#",
				parameter.getValue());
		time = time.replaceAll("#date_pattern#", pattern);
		parameter.setValue(time);
		return getCondition(AND_LOGIC_OPERATOR, parameter);
	}

	/**
	 * 获取“逻辑或”条件下的“日期”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询条件参数
	 * @param dateTemplate
	 *            String 日期模板
	 * @param pattern
	 *            String 日期匹配格式字符串
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionDateByOrLogicOperator(
			QueryParameter parameter, String dateTemplate, String pattern) {
		parameter.setPrefix("");
		parameter.setSuffix("");
		if (StringUtils.isBlank(parameter.getValue())) {
			parameter.setOperator(EQU_OPERATOR);
			parameter.setValue("");
			return getCondition(OR_LOGIC_OPERATOR, parameter);
		}
		String time = dateTemplate.replaceAll("#input_date#",
				parameter.getValue());
		time = time.replaceAll("#date_pattern#", pattern);
		parameter.setValue(time);
		return getCondition(OR_LOGIC_OPERATOR, parameter);
	}

	/**
	 * 获取“列名等于判断”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionEqualByNoLogicOperator(
			QueryParameter parameter) {
		parameter.setOperator(EQU_OPERATOR);
		parameter.setPrefix(EQU_PREFIX);
		parameter.setSuffix(EQU_PREFIX);
		return getCondition("", parameter);
	}

	/**
	 * 获取“逻辑与”条件下的“列名等于判断”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionEqualByAndLogicOperator(
			QueryParameter parameter) {
		parameter.setOperator(EQU_OPERATOR);
		parameter.setPrefix(EQU_PREFIX);
		parameter.setSuffix(EQU_PREFIX);
		return getCondition(AND_LOGIC_OPERATOR, parameter);
	}

	/**
	 * 获取“逻辑与”条件下的“列名不等于判断”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionNotEqualByAndLogicOperator(
			QueryParameter parameter) {
		parameter.setOperator(NOT_EQU_OPERATOR);
		parameter.setPrefix(EQU_PREFIX);
		parameter.setSuffix(EQU_PREFIX);
		return getCondition(AND_LOGIC_OPERATOR, parameter);
	}

	/**
	 * 获取“逻辑与”条件下的“列名模糊查询”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionLikeByAndLogicOperator(
			QueryParameter parameter) {
		parameter.setOperator(LIKE_OPERATOR);
		parameter.setPrefix(LIKE_PREFIX);
		parameter.setSuffix(LIKE_SUFFIX);
		return getCondition(AND_LOGIC_OPERATOR, parameter);
	}

	/**
	 * 获取“逻辑与”条件下的“日期”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionDateByAndLogicOperator(
			QueryParameter parameter) {
		return getConditionDateByAndLogicOperator(parameter, DATE_TEMPLATE,
				"yyyy-mm-dd");
	}

	/**
	 * 获取“逻辑与”条件下的“日期（带时分秒）”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionDateTimeByAndLogicOperator(
			QueryParameter parameter) {
		return getConditionDateByAndLogicOperator(parameter, DATE_TEMPLATE,
				"yyyy-mm-dd hh24:mi:ss");
	}

	/**
	 * 获取“逻辑与”条件下的“日期的下一天”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionNextDateByAndLogicOperator(
			QueryParameter parameter) {
		return getConditionDateByAndLogicOperator(parameter,
				NEXT_DATE_TEMPLATE, "yyyy-mm-dd");
	}

	/**
	 * 获取“逻辑与”条件下的“日期的下一天（带时分秒）”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionNextDateTimeByAndLogicOperator(
			QueryParameter parameter) {
		return getConditionDateByAndLogicOperator(parameter,
				NEXT_DATE_TEMPLATE, "yyyy-mm-dd hh24:mi:ss");
	}

	/**
	 * 获取“逻辑或”条件下的“列名等于判断”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionEqualByOrLogicOperator(
			QueryParameter parameter) {
		parameter.setOperator(EQU_OPERATOR);
		parameter.setPrefix(EQU_PREFIX);
		parameter.setSuffix(EQU_PREFIX);
		return getCondition(OR_LOGIC_OPERATOR, parameter);
	}

	/**
	 * 获取“IN”条件下的“列名查询”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionInByAndLogicOperator(
			QueryParameter parameter) {
		parameter.setOperator(IN_OPERATOR);
		parameter.setPrefix(LEFT_PARENTHESIS);
		parameter.setSuffix(RIGHT_PARENTHESIS);
		return getCondition(AND_LOGIC_OPERATOR, parameter);
	}

	/**
	 * 获取“逻辑或”条件下的“列名模糊查询”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionLikeByOrLogicOperator(
			QueryParameter parameter) {
		parameter.setOperator(LIKE_OPERATOR);
		parameter.setPrefix(LIKE_PREFIX);
		parameter.setSuffix(LIKE_SUFFIX);
		return getCondition(OR_LOGIC_OPERATOR, parameter);
	}

	/**
	 * 获取“逻辑或”条件下的“日期”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionDateByOrLogicOperator(
			QueryParameter parameter) {
		return getConditionDateByOrLogicOperator(parameter, DATE_TEMPLATE,
				"yyyy-mm-dd");
	}

	/**
	 * 获取“逻辑或”条件下的“日期（带时分秒）”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionDateTimeByOrLogicOperator(
			QueryParameter parameter) {
		return getConditionDateByOrLogicOperator(parameter, DATE_TEMPLATE,
				"yyyy-mm-dd hh24:mi:ss");
	}

	/**
	 * 获取“逻辑或”条件下的“日期的下一天”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionNextDateByOrLogicOperator(
			QueryParameter parameter) {
		return getConditionDateByOrLogicOperator(parameter, NEXT_DATE_TEMPLATE,
				"yyyy-mm-dd");
	}

	/**
	 * 获取“逻辑或”条件下的“日期的下一天（带时分秒）”查询条件
	 * 
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return String 查询条件sql字符串
	 */
	public static String getConditionNextDateTimeByOrLogicOperator(
			QueryParameter parameter) {
		return getConditionDateByOrLogicOperator(parameter, NEXT_DATE_TEMPLATE,
				"yyyy-mm-dd hh24:mi:ss");
	}
}
