package com.cabletech.business.ah.excelreport.exports;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.excel.TableCellData;

/**
 * 报表导出工具类
 * 
 * @author 杨隽 2012-07-09 创建
 * @author 杨隽 2012-08-08 修改导出的方法传递参数
 * 
 */
public class ExcelExportUtils {
	/**
	 * 日志输出
	 */
	private static Logger logger = Logger.getLogger("ExcelExportUtils");

	/**
	 * 导出报表方法
	 * 
	 * @param tableMap
	 *            Map<String, Object>
	 * @param sheetName
	 *            String
	 * @return Workbook
	 */
	@SuppressWarnings("unchecked")
	public static Workbook exportReport(Map<String, Object> tableMap,
			String sheetName) {
		Workbook wb = new HSSFWorkbook();
		CellStyle style = getDefaultExcelStyle(wb);
		Sheet sheet = wb.createSheet(sheetName);
		createMergeRegion(sheet, tableMap);
		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(25f);
		Cell cell = titleRow.createCell(0);
		cell.setCellValue((String) tableMap.get("title"));
		cell.setCellStyle(getTitleExcelStyle(wb));
		List<List<TableCellData>> list = (List<List<TableCellData>>) tableMap
				.get("dataList");
		if (CollectionUtils.isEmpty(list)) {
			return wb;
		}
		for (int i = 0; i < list.size(); i++) {
			Row row = sheet.getRow(i + 1);
			if (row == null) {
				row = sheet.createRow(i + 1);
			}
			List<TableCellData> cellDataList = list.get(i);
			if (CollectionUtils.isEmpty(cellDataList)) {
				continue;
			}
			int colSpan = 0;
			for (int j = 0; j < cellDataList.size(); j++) {
				TableCellData data = cellDataList.get(j);
				cell = row.createCell(data.getColIndex());
				colSpan += data.getColSpan();
				cell.setCellStyle(style);
				cell.setCellValue(data.getCellValue());
				for (int k = 1; k < data.getColSpan(); k++) {
					cell = row.createCell(k + data.getColIndex());
					cell.setCellStyle(style);
				}
				for (int k = 1; k < data.getRowSpan(); k++) {
					int rowNo = k + data.getRowIndex() + 1;
					Row nextRow = sheet.getRow(rowNo);
					if (nextRow == null) {
						nextRow = sheet.createRow(rowNo);
					}
					Cell nextCell = nextRow.createCell(data.getColIndex());
					nextCell.setCellStyle(style);
				}
			}
		}
		return wb;
	}

	/**
	 * 获取Excel表格缺省样式
	 * 
	 * @param wb
	 *            Workbook
	 * @return CellStyle
	 */
	public static CellStyle getDefaultExcelStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("宋体");
		font.setItalic(false);
		font.setStrikeout(false);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setFont(font);
		style.setWrapText(true);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return style;
	}

	/**
	 * 获取Excel表格标题样式
	 * 
	 * @param wb
	 *            Workbook
	 * @return CellStyle
	 */
	public static CellStyle getTitleExcelStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 14);
		font.setFontName("宋体");
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setItalic(false);
		font.setStrikeout(false);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setFont(font);
		style.setWrapText(true);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return style;
	}

	/**
	 * 创建Excel表格的合并区域
	 * 
	 * @param sheet
	 *            Sheet
	 * @param tableMap
	 *            Map<String, Object>
	 */
	@SuppressWarnings({ "unchecked" })
	private static void createMergeRegion(Sheet sheet,
			Map<String, Object> tableMap) {
		List<List<TableCellData>> list = (List<List<TableCellData>>) tableMap
				.get("dataList");
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		int tableColSpan = 0;
		for (int i = 0; i < list.size(); i++) {
			List<TableCellData> cellDataList = list.get(i);
			if (CollectionUtils.isEmpty(cellDataList)) {
				continue;
			}
			for (int j = 0; j < cellDataList.size(); j++) {
				TableCellData data = cellDataList.get(j);
				if (i == 0) {
					tableColSpan += data.getColSpan();
				}
				int firstRow = data.getRowIndex() + 1;
				int lastRow = data.getRowIndex() + data.getRowSpan();
				int firstCol = data.getColIndex();
				int lastCol = data.getColIndex() + data.getColSpan() - 1;
				if (lastRow > firstRow || lastCol > firstCol) {
					logger.info("firstrow=" + firstRow + ":lastrow=" + lastRow
							+ ":firstcol=" + firstCol + ":lastcol=" + lastCol);
					sheet.addMergedRegion(new CellRangeAddress(firstRow,
							lastRow, firstCol, lastCol));
				}
			}
		}
		logger.info("tableColSpan=" + tableColSpan);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, tableColSpan - 1));
	}
}
