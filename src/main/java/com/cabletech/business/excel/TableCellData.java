package com.cabletech.business.excel;

import java.io.Serializable;

/**
 * 表格数据信息内容
 * 
 * @author 杨隽 2012-06-29 创建
 * @author 杨隽 2012-07-09 添加行索引和列索引属性
 * @author 杨隽 2012-07-31 添加单元格级联数据内容和单元格列对应的输入隐藏域名称属性
 * 
 */
public class TableCellData implements Serializable {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 单元格所占行数
	 */
	private int rowSpan = 1;
	/**
	 * 单元格所占列数
	 */
	private int colSpan = 1;
	/**
	 * 单元格宽度
	 */
	private int width;
	/**
	 * 单元格数据内容
	 */
	private String cellValue = "";
	/**
	 * 单元格级联数据内容
	 */
	private String parentCellValue = "";
	/**
	 * 单元格列对应的输入隐藏域名称
	 */
	private String columnInputName = "";
	/**
	 * 行索引属性
	 */
	private int rowIndex;
	/**
	 * 列索引属性
	 */
	private int colIndex;
	/**
	 * 错误提示信息
	 */
	private String errorMessage = "";
	/**
	 * 单元格字体颜色
	 */
	private String cellColor = "black";

	public int getRowSpan() {
		return rowSpan;
	}

	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}

	public int getColSpan() {
		return colSpan;
	}

	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getCellValue() {
		return cellValue;
	}

	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColIndex() {
		return colIndex;
	}

	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}

	public String getParentCellValue() {
		return parentCellValue;
	}

	public void setParentCellValue(String parentCellValue) {
		this.parentCellValue = parentCellValue;
	}

	public String getColumnInputName() {
		return columnInputName;
	}

	public void setColumnInputName(String columnInputName) {
		this.columnInputName = columnInputName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getCellColor() {
		return cellColor;
	}

	public void setCellColor(String cellColor) {
		this.cellColor = cellColor;
	}
}
