package com.cabletech.business.wplan.patrolitem.model;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 巡检项EXCEL解析临时对象 非持久化
 * 
 * @author wangjie
 * @author 杨隽 2011-10-25 添加导入巡检项目的“值域范围”和“默认值”字段
 * @author 杨隽 2011-11-02 添加导入巡检项目的“异常值”字段
 */
public class PatrolItemTemp {
	private static final int MAX_PATROL_OBJ_NAME_LENGTH = 20;
	private static final int MAX_PATROL_ITEM_NAME_LENGTH = 30;
	private static final String VALUE_SCOPE_SEPR = ",";

	private String itemName;// ITEM_NAME VARCHAR2(100) Y 项目名称
	private String businessType;// BUSINESS_TYPE VARCHAR2(20) Y 专业类型
	private String partenId;// PARTEN_ID VARCHAR2(12) Y 上级编号
	private String regionId;// REMARK VARCHAR2(1000) Y 区域
	private String itemRemark;// ITEM_REMARK VARCHAR2(1000) Y 备注

	private String itemId;// ITEM_ID VARCHAR2(100) Y 巡检项目编号
	private String subitemName;// SUBITEM_NAME VARCHAR2(20) Y 子项名称
	private String inputType;// INPUT_TYPE VARCHAR2(12) Y 输入类型
	private String cycle;// VARCHAR2(3) Y
							// year：年，quarter：季，month：月份，week：周，custom：按需
	private String qualityStandard;// QUALITY_STANDARD VARCHAR2(2000) Y 维护指标说明
	private String remark;// REMARK VARCHAR2(1000) Y 备注
	private String state;// STATE VARCHAR2(3) Y 标记当前在用状态，1为在用，2为删除。

	private String cycleText;// ITEM_ID VARCHAR2(100) Y 巡检项目编号
	private String inputTypeText;// INPUT_TYPE VARCHAR2(12) Y 输入类型
	private String valueScope;// VALUE_SCOPE VARCHAR2(1024) 值域范围
	private String defaultValue;// DEFAULT_VALUE VARCHAR2(20) 默认值
	private String exceptionValue;// EXCEPTION_VALUE VARCHAR2(20) 异常值

	private boolean flag = true;//
	private String errorMsg = "";//

	public String getItemName() {
		return itemName;
	}

	/**
	 * 检查子项名称并给子项名称赋值
	 * 
	 * @param itemName
	 *            String
	 */
	public void setItemName(String itemName) {
		if (StringUtils.isBlank(itemName)) {
			flag = false;
			errorMsg += "维护对象不能为空!";
		}
		if (flag && itemName.length() > MAX_PATROL_OBJ_NAME_LENGTH) {
			flag = false;
			errorMsg += "维护对象超过20个汉字!";
		}
		this.itemName = itemName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getPartenId() {
		return partenId;
	}

	public void setPartenId(String partenId) {
		this.partenId = partenId;
	}

	public String getItemRemark() {
		return itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getSubitemName() {
		return subitemName;
	}

	/**
	 * 检查维护检测项目名称并给维护检测项目名称赋值
	 * 
	 * @param subitemName
	 *            String
	 */
	public void setSubitemName(String subitemName) {
		if (StringUtils.isBlank(subitemName)) {
			flag = false;
			errorMsg += "维护检测项目不能为空!";
		}
		if (flag && subitemName.length() > MAX_PATROL_ITEM_NAME_LENGTH) {
			flag = false;
			errorMsg += "维护检测项目名称超过30个汉字!";
		}
		this.subitemName = subitemName;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getQualityStandard() {
		return qualityStandard;
	}

	public void setQualityStandard(String qualityStandard) {
		this.qualityStandard = qualityStandard;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCycleText() {
		return cycleText;
	}

	/**
	 * 检查周期并给周期赋值
	 * 
	 * @param cycleText
	 *            String
	 */
	public void setCycleText(String cycleText) {
		if (StringUtils.isBlank(cycleText)) {
			flag = false;
			errorMsg += "周期不能为空!";
		}
		this.cycleText = cycleText;
		setCycle(getConstantValue(cycleText));
	}

	public String getInputTypeText() {
		return inputTypeText;
	}

	/**
	 * 检查输入类型并给输入类型赋值
	 * 
	 * @param inputTypeText
	 *            String
	 */
	public void setInputTypeText(String inputTypeText) {
		String value = "";
		if (StringUtils.isBlank(inputTypeText)) {
			flag = false;
			errorMsg += "输入类型不能为空!";
		} else {
			value = getConstantValue(inputTypeText);
			if (StringUtils.isBlank(value)) {
				flag = false;
				errorMsg += "输入类型不合法!";
			}
		}
		setInputType(value);
		this.inputTypeText = inputTypeText;
	}

	public String getValueScope() {
		return valueScope;
	}

	/**
	 * 检查值域范围并给值域范围赋值
	 * 
	 * @param valueScope
	 *            String
	 */
	public void setValueScope(String valueScope) {
		if ("单选".equals(getInputTypeText())) {
			if (StringUtils.isBlank(valueScope)) {
				flag = false;
				errorMsg += "值域范围不能为空!";
			}
			if (flag && valueScope.indexOf(VALUE_SCOPE_SEPR) == -1) {
				flag = false;
				errorMsg += "值域范围至少需要两项数据（数据之间以“,”分割）!";
			}
		}
		this.valueScope = valueScope;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * 检查默认值范围并给默认值赋值
	 * 
	 * @param defaultValue
	 *            String
	 */
	public void setDefaultValue(String defaultValue) {
		if ("单选".equals(getInputTypeText())) {
			if (flag && !valueInScope(defaultValue, getValueScope())) {
				flag = false;
				errorMsg += "默认值不在值域范围内!";
			}
		}
		this.defaultValue = defaultValue;
	}

	public String getExceptionValue() {
		return exceptionValue;
	}

	/**
	 * 检查异常状态范围并给异常状态赋值
	 * 
	 * @param exceptionValue
	 *            String
	 */
	public void setExceptionValue(String exceptionValue) {
		if ("单选".equals(getInputTypeText())) {
			if (flag && !valueInScope(exceptionValue, getValueScope())) {
				flag = false;
				errorMsg += "异常状态不在值域范围内!";
			}
		}
		this.exceptionValue = exceptionValue;
	}

	/**
	 * 判断值是否在值域范围内
	 * 
	 * @param value
	 *            String 值
	 * @param valueScope
	 *            String 值域范围
	 * @return boolean 值是否在值域范围内
	 */
	public static boolean valueInScope(String value, String valueScope) {
		// TODO Auto-generated method stub
		String[] values = valueScope.split(VALUE_SCOPE_SEPR);
		if (StringUtils.isBlank(value)) {
			return true;
		}
		return ArrayUtils.contains(values, value);
	}

	/**
	 * 获取输入文本对应的常量值
	 * 
	 * @param name
	 *            String 输入文本
	 * @return String 输入文本对应的常量值
	 */
	public static String getConstantValue(String name) {
		String v = "";
		if ("年".equals(name)) {
			v = "year";
		}
		if ("季".equals(name)) {
			v = "quarter";
		}
		if ("月".equals(name)) {
			v = "month";
		}
		if ("周".equals(name)) {
			v = "week";
		}
		if ("数值".equals(name)) {
			v = "NUM";
		}
		if ("文本".equals(name)) {
			v = "TEXT";
		}
		if ("单选".equals(name)) {
			v = "CHOOSE";
		}
		if ("多选".equals(name)) {
			v = "GROUP";
		}
		if ("蓄电池测试".equals(name)) {
			v = "BATTERY";
		}
		if ("天线测量".equals(name)) {
			v = "STATION_ANTENNA";
		}
		if ("天线信号测试".equals(name)) {
			v = "INDOOR_COVERAGE";
		}
		if ("WLAN天线信号测试".equals(name)) {
			v = "WLAN";
		}
		return v;
	}
}
