package com.cabletech.business.excel;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 根据EXCEL自动生成HTML页面的业务工具
 * 
 * @author 杨隽 2012-06-27 创建
 * @author 杨隽 2012-07-31 修改获取单元格值的方法
 * 
 */
public class AutoGenerateUtils {
	/**
	 * 序号字串
	 */
	public static final String SEQ_STRING = "序号";
	/**
	 * 百分比后缀
	 */
	public static final String PERCENT_SUFFIX = "%";
	/**
	 * 字符宽度比
	 */
	public static final double CHAR_WIDTH_PERCENT = 8d / 256;
	/**
	 * 日志输出
	 */
	private static Logger logger = Logger.getLogger("AutoGenerateService");
	/**
	 * 日期格式化对象
	 */
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	/**
	 * 缺省的数值格式
	 */
	private static final DecimalFormat DEFAULT_NUMBER_FORMAT = new DecimalFormat(
			"#0.00");

	/**
	 * 根据文件名和sheet序号自动生成表格map
	 * 
	 * @param fileName
	 *            String 文件名
	 * @param parameter
	 *            AutoGenerateParameter 工作表位置参数信息
	 * @return Map<String, Object> 生成表格map
	 */
	public static Map<String, Object> autoGenerate(String fileName,
			AutoGenerateParameter parameter) {
		Workbook xwb = null;
		try {
			xwb = AutoGenerateFactory.getWorkbook(fileName);
		} catch (Exception e) {
			logger.error("", e);
		}
		Map<String, Object> map = getGenerateMap(xwb, parameter);
		return map;
	}

	/**
	 * 根据工作表sheet获取表格的宽度
	 * 
	 * @param sheet
	 *            Sheet 工作表sheet
	 * @param rowIndex
	 *            int 数据行索引
	 * @return int 表格的宽度
	 */
	public static int getTableWidth(Sheet sheet, int rowIndex) {
		Row row = sheet.getRow(rowIndex);
		int width = 0;
		for (int i = 0; i < row.getLastCellNum(); i++) {
			width += sheet.getColumnWidth(i);
		}
		width = (int) (width * CHAR_WIDTH_PERCENT);
		return width;
	}

	/**
	 * 根据当前行和单元格序号获取单元格内容
	 * 
	 * @param row
	 *            Row 当前行
	 * @param cellIndex
	 *            int 单元格序号
	 * @return String 单元格内容
	 */
	public static String getCellValue(Row row, int cellIndex) {
		if (row == null) {
			return "";
		}
		Cell cell = row.getCell(cellIndex);
		if (cell == null) {
			return "";
		}
		String cellValue = "";
		try {
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				cellValue = cell.getStringCellValue();
			}
			if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				cellValue = getNumberValue(cell);
			}
			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				cellValue = getNumberValue(cell);
			}
		} catch (Exception ex) {
			cellValue = "&nbsp;";
			logger.error("格式转换错误:");
		}
		return cellValue;
	}

	/**
	 * 判断行数据是否都为空
	 * 
	 * @param row
	 *            Row 指定行
	 * @return boolean 行数据是否都为空
	 */
	public static boolean isEmpty(Row row) {
		if (row == null) {
			return true;
		}
		int firstCellIndex = row.getFirstCellNum();
		int lastCellIndex = row.getLastCellNum();
		for (int i = firstCellIndex; i < lastCellIndex; i++) {
			if (StringUtils.isNotBlank(getCellValue(row, i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 生成单元格的数据
	 * 
	 * @param sheet
	 *            Sheet 工作表sheet
	 * @param rowIndex
	 *            int 行索引
	 * @param colIndex
	 *            int 列索引
	 * @return TableCellData
	 */
	public static TableCellData getTdGenerateData(Sheet sheet, int rowIndex,
			int colIndex) {
		Row row = sheet.getRow(rowIndex);
		int width = (int) (sheet.getColumnWidth(colIndex) * CHAR_WIDTH_PERCENT);
		CellRangeAddress range = getMergedRegion(sheet, rowIndex, colIndex);
		if (range != null) {
			if (rowIndex == range.getFirstRow()
					&& colIndex == range.getFirstColumn()) {
				int rowSpan = range.getLastRow() - range.getFirstRow();
				int colSpan = range.getLastColumn() - range.getFirstColumn();
				width = getMergeColumnWidth(sheet, range.getFirstColumn(),
						range.getLastColumn());
				TableCellData data = new TableCellData();
				data.setRowSpan(rowSpan + 1);
				data.setColSpan(colSpan + 1);
				data.setWidth(width);
				data.setCellValue(getCellValue(row, colIndex));
				return data;
			}
		} else {
			TableCellData data = new TableCellData();
			data.setWidth(width);
			data.setCellValue(getCellValue(row, colIndex));
			return data;
		}
		return null;
	}

	/**
	 * 根据工作表对象和sheet序号自动生成表格MAP
	 * 
	 * @param xwb
	 *            Workbook 工作表对象
	 * @param parameter
	 *            AutoGenerateParameter 工作表位置参数信息
	 * @return Map<String, Object> 生成表格MAP
	 */
	private static Map<String, Object> getGenerateMap(Workbook xwb,
			AutoGenerateParameter parameter) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (xwb == null) {
			return map;
		}
		Sheet sheet = xwb.getSheetAt(parameter.getSheetIndex());
		Row firstRow = sheet.getRow(parameter.getStartRow());
		Row row;
		int tableWidth = getTableWidth(sheet, parameter.getDataStartRow());
		int startColLastIndex = getStartColLastIndex(sheet,
				parameter.getStartRow(), parameter.getStartCol());
		map.put("tableWidth", tableWidth);
		List<List<TableCellData>> rowDataList = new ArrayList<List<TableCellData>>();
		for (int i = parameter.getStartRow(); i < sheet
				.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (isEmpty(row)) {
				continue;
			}
			List<TableCellData> cellDataList = new ArrayList<TableCellData>();
			String parentCellValue = "";
			for (int j = parameter.getStartCol(); j < row.getLastCellNum(); j++) {
				if (j <= startColLastIndex && i >= parameter.getDataStartRow()) {
					CellRangeAddress range = getMergedRegion(sheet, i, j);
					if (range != null) {
						String cellValue = getCellValue(
								sheet.getRow(range.getFirstRow()), j);
						if (StringUtils.isNotBlank(cellValue)) {
							parentCellValue += cellValue;
							parentCellValue += "_";
						}
					} else if (j == startColLastIndex) {
						parentCellValue += getCellValue(row, j);
					}
				}
				TableCellData data = getTdGenerateData(sheet, i, j);
				if (data != null) {
					data.setRowIndex(i - parameter.getStartRow());
					data.setColIndex(j - parameter.getStartCol());
					if (i > parameter.getStartRow()
							&& SEQ_STRING.equals(getCellValue(firstRow, j))) {
						if (getCellValue(row, j) != null) {
							try {
								int num = (int) Double
										.parseDouble(getCellValue(row, j));
								data.setCellValue(Integer.toString(num));
							} catch (Exception ex) {
							}
						}
					}
					data.setColumnInputName(getCellValue(firstRow, j));
					data.setParentCellValue(parentCellValue);
					cellDataList.add(data);
				}
			}
			rowDataList.add(cellDataList);
		}
		map.put("dataList", rowDataList);
		return map;
	}

	/**
	 * 获取标题的开始列所占列的最后一列的索引
	 * 
	 * @param rowIndex
	 *            int
	 * @param startCol
	 *            int
	 * @param sheet
	 *            Sheet
	 * @return int
	 */
	private static int getStartColLastIndex(Sheet sheet, int rowIndex,
			int startCol) {
		CellRangeAddress range = getMergedRegion(sheet, rowIndex, startCol);
		if (range == null) {
			return 0;
		}
		return range.getLastColumn();
	}

	/**
	 * 获取合并的单元格信息
	 * 
	 * @param sheet
	 *            Sheet 当前工作表sheet
	 * @param row
	 *            int 指定的单元格所在行
	 * @param column
	 *            int 指定的单元格所在列
	 * @return CellRangeAddress 合并的单元格信息
	 */
	private static CellRangeAddress getMergedRegion(Sheet sheet, int row,
			int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return ca;
				}
			}
		}
		return null;
	}

	/**
	 * 获取合并的单元格宽度
	 * 
	 * @param sheet
	 *            Sheet 工作表sheet
	 * @param firstColumn
	 *            int 合并单元格的开始列索引
	 * @param lastColumn
	 *            int 合并单元格的终止列索引
	 * @return int 合并的单元格宽度
	 */
	private static int getMergeColumnWidth(Sheet sheet, int firstColumn,
			int lastColumn) {
		int width = 0;
		for (int i = firstColumn; i <= lastColumn; i++) {
			width += sheet.getColumnWidth(i);
		}
		width = (int) (width * CHAR_WIDTH_PERCENT);
		return width;
	}

	/**
	 * 获取单元格的数据（数值类型、日期类型和公式类型）
	 * 
	 * @param cell
	 *            Cell
	 * @return String
	 */
	private static String getNumberValue(Cell cell) {
		CellStyle style = cell.getCellStyle();
		String cellValue;
		if (HSSFDateUtil.isCellDateFormatted(cell)) {
			cellValue = DATE_FORMAT.format(cell.getDateCellValue());
		} else {
			String fmt = style.getDataFormatString();
			double number = cell.getNumericCellValue();
			if (fmt.endsWith(PERCENT_SUFFIX)) {
				DecimalFormat numberFmt = new DecimalFormat(fmt);
				cellValue = numberFmt.format(number);
			} else {
				cellValue = DEFAULT_NUMBER_FORMAT.format(number);
			}
		}
		return cellValue;
	}
}
