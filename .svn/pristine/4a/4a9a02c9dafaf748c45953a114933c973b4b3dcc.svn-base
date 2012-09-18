package com.cabletech.business.base.model;

import com.cabletech.common.base.BaseEntity;
import com.cabletech.common.util.Hanzi2PinyinUtil;

/**
 * 业务序号记录器
 * 
 * @author 杨隽 2012-01-12 创建
 * 
 */
public class CodeSequence extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 序号的填充最大长度
	public static final int PAD_STRING_LENGTH = 5;
	// 序号的填充字串
	public static final String PAD_STRING = "0";
	// 表名
	private String tableName;
	// 派单部门编号
	private String deptId;
	// 派单部门缩写
	private String deptShort;
	// 当前月份
	private String yearMonth;
	// 最大值
	private String maxId;
	// 模式
	private String pattern;
	// 备注
	private String remark;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptShort() {
		return deptShort;
	}

	public void setDeptShort(String deptShort) {
		this.deptShort = deptShort;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getMaxId() {
		return maxId;
	}

	public void setMaxId(String maxId) {
		this.maxId = maxId;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取生成的业务序号
	 * 
	 * @return
	 */
	public String getCodeSequenceString() {
		String code = Hanzi2PinyinUtil.getPinYinHeadChar(deptShort);
		code += yearMonth;
		code += maxId;
		return code;
	}
}
