package com.cabletech.business.workflow.fault.condition.parameter;

/**
 * 日期区域参数
 * 
 * @author 杨隽 2011-09-19
 * 
 */
public class DateParameterRange {
	// 日期区域中的开始值
	private String dateMin;
	// 日期区域中的结束值
	private String dateMax;

	/**
	 * 初始化日期区域对象
	 * 
	 * @param dateMin
	 *            String 日期最小值
	 * @param dateMax
	 *            String 日期最大值
	 */
	public DateParameterRange(String dateMin, String dateMax) {
		this.dateMax = dateMax;
		this.dateMin = dateMin;
	}

	public String getDateMin() {
		return dateMin;
	}

	public void setDateMin(String dateMin) {
		this.dateMin = dateMin;
	}

	public String getDateMax() {
		return dateMax;
	}

	public void setDateMax(String dateMax) {
		this.dateMax = dateMax;
	}
}
