package com.cabletech.common.excel;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Excel的工具类
 * 
 * @author 杨隽 2012-02-13 创建
 * 
 */
public class ExcelUtils {
	// 日期格式字符串
	private static final String DATE_FORMAT = "yyyy/mm/dd";

	/**
	 * 判断该单元格是否为合并的单元格
	 * 
	 * @param sheet
	 *            HSSFSheet Excel工作表中的当前表格页
	 * @param cell
	 *            HSSFCell Excel工作表中的当前单元格
	 * @return boolean 该单元格是否为合并的单元格
	 */
	public static boolean isMergedRegion(HSSFSheet sheet, HSSFCell cell) {
		int sheetmergerCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetmergerCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstC = ca.getFirstColumn();
			int lastC = ca.getLastColumn();
			int firstR = ca.getFirstRow();
			int lastR = ca.getLastRow();
			if (cell == null) {
				continue;
			}
			if (cell.getColumnIndex() > lastC) {
				continue;
			}
			if (cell.getColumnIndex() < firstC) {
				continue;
			}
			if (cell.getRowIndex() > lastR) {
				continue;
			}
			if (cell.getRowIndex() < firstR) {
				continue;
			}
			return true;
		}
		return false;
	}

	/**
	 * 获取合并单元格中的数据信息
	 * 
	 * @param sheet
	 *            HSSFSheet Excel工作表中的当前表格页
	 * @param cell
	 *            HSSFCell Excel工作表中的当前单元格
	 * @return String 单元格的数据
	 */
	public static String getMergedRegionValue(HSSFSheet sheet, HSSFCell cell) {
		int sheetmergerCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetmergerCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstC = ca.getFirstColumn();
			int lastC = ca.getLastColumn();
			int firstR = ca.getFirstRow();
			int lastR = ca.getLastRow();
			if (cell == null) {
				continue;
			}
			if (cell.getColumnIndex() > lastC) {
				continue;
			}
			if (cell.getColumnIndex() < firstC) {
				continue;
			}
			if (cell.getRowIndex() > lastR) {
				continue;
			}
			if (cell.getRowIndex() < firstR) {
				continue;
			}
			HSSFRow fRow = sheet.getRow(firstR);
			HSSFCell fCell = fRow.getCell(firstC);
			if (fCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				return String.valueOf(fCell.getNumericCellValue());
			} else if (fCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(fCell.getBooleanCellValue());
			} else if (fCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				return fCell.getStringCellValue();
			} else if (fCell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
				return String.valueOf(fCell.getCellFormula());
			}
		}
		return "";
	}

	/**
	 * 获得单元格的数据
	 * 
	 * @param cell
	 *            HSSFCell Excel工作表中的当前单元格
	 * @param wb
	 *            HSSFWorkbook 当前Excel工作表
	 * @return String 单元格的数据
	 */
	public static String getCellStringValue(HSSFCell cell, HSSFWorkbook wb) {
		String cellValue = "";
		if (cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			cellValue = cell.getRichStringCellValue().toString();
			if (StringUtils.isBlank(cellValue)) {
				cellValue = "";
			}
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = HSSFDateUtil
						.getJavaDate(cell.getNumericCellValue());
				return new SimpleDateFormat("yyyy-MM-dd").format(date);
			} else {
				String style = wb.createDataFormat().getFormat(
						(short) cell.getCellStyle().getDataFormat());
				if (DATE_FORMAT.equals(style)) {
					Date date = HSSFDateUtil.getJavaDate(cell
							.getNumericCellValue());
					return new SimpleDateFormat("yyyy-MM-dd").format(date);
				} else {
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
			}
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = HSSFDateUtil
						.getJavaDate(cell.getNumericCellValue());
				return new SimpleDateFormat("yyyy-MM-dd").format(date);
			} else {
				String style = wb.createDataFormat().getFormat(
						(short) cell.getCellStyle().getDataFormat());
				if (DATE_FORMAT.equals(style)) {
					Date date = HSSFDateUtil.getJavaDate(cell
							.getNumericCellValue());
					return new SimpleDateFormat("yyyy-MM-dd").format(date);
				} else {
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
			}
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = "";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			break;
		default:
			break;
		}
		return cellValue.trim();
	}
}
