package com.cabletech.business.ah.excelreport.sum.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.cabletech.business.ah.excelreport.model.AhExcelReportRecode;
import com.cabletech.business.ah.excelreport.model.AhExcelReportSheet;
import com.cabletech.business.ah.excelreport.sum.BaseSheetSumGenerate;
import com.cabletech.business.excel.AutoGenerateFactory;
import com.cabletech.business.excel.AutoGenerateUtils;
import com.cabletech.business.excel.TableCellData;

/**
 * 不包含合计行Sheet按地市汇总
 * 
 * @author 杨隽 2012-06-28 创建
 * 
 */
@Component
public class SheetSumGenerateS0ShS2 extends BaseSheetSumGenerate {
	@Override
	public int getTableTdStr(Workbook xwb, AhExcelReportSheet sheetType,
			List<List<TableCellData>> rowDataList, AhExcelReportRecode record,
			int rowIndex) {
		Sheet sheet = xwb.getSheetAt(sheetType.getSheetNum());
		Map<String, Double> map = getSumValueMap(xwb, sheet);
		List<TableCellData> cellDataList = new ArrayList<TableCellData>();
		TableCellData regionCellData = new TableCellData();
		String regionName = (String) baseInfoProvider.getRegionService()
				.getRegionMap(record.getRegionId()).get("REGIONNAME");
		regionCellData.setWidth(100);
		regionCellData.setCellValue(regionName);
		regionCellData.setRowIndex(rowIndex);
		regionCellData.setColIndex(0);
		cellDataList.add(regionCellData);
		cellDataList.addAll(generateTdString(sheet, map, rowIndex));
		super.wbList.add(xwb);
		rowDataList.add(cellDataList);
		return rowIndex + 1;
	}

	@Override
	public List<List<TableCellData>> getTableSubjectStr(
			AhExcelReportSheet sheetType, List<AhExcelReportRecode> list)
			throws Exception {
		List<List<TableCellData>> rowDataList = new ArrayList<List<TableCellData>>();
		AhExcelReportRecode record = list.get(0);
		String path = record.getFileUrl();
		Workbook xwb = AutoGenerateFactory.getWorkbook(path);
		Sheet sheet = xwb.getSheetAt(sheetType.getSheetNum());
		int rowSpan = DATA_START_ROW_INDEX - sheetType.getRow();
		Row row;
		for (int i = sheetType.getRow(); i < DATA_START_ROW_INDEX; i++) {
			row = sheet.getRow(i);
			if (AutoGenerateUtils.isEmpty(row)) {
				continue;
			}
			List<TableCellData> cellDataList = new ArrayList<TableCellData>();
			TableCellData data = new TableCellData();
			if (i == sheetType.getRow()) {
				data.setRowSpan(rowSpan);
				data.setCellValue("地市");
				data.setRowIndex(i - sheetType.getRow());
				data.setColIndex(0);
				cellDataList.add(data);
			}
			int descCol = 0;
			for (int j = SUM_START_COL_INDEX; j < row.getLastCellNum(); j++) {
				if (super.isNotSumColumn(sheet, j)) {
					descCol++;
					continue;
				}
				data = AutoGenerateUtils.getTdGenerateData(sheet, i, j);
				if (data != null) {
					data.setRowIndex(i - sheetType.getRow());
					data.setColIndex(j - SUM_START_COL_INDEX - descCol + 1);
					cellDataList.add(data);
				}
			}
			rowDataList.add(cellDataList);
		}
		return rowDataList;
	}

	/**
	 * 根据工作表sheet中的数值进行累加生成汇总的值Map
	 * 
	 * @param sheet
	 *            Sheet 工作表sheet
	 * @param xwb
	 *            Workbook 工作表
	 * @return Map<String, Double> 汇总的值Map
	 */
	private Map<String, Double> getSumValueMap(Workbook xwb, Sheet sheet) {
		Row firstRow = sheet.getRow(0);
		Row row;
		Map<String, Double> map = new HashMap<String, Double>();
		Row dataFirstRow = sheet.getRow(DATA_START_ROW_INDEX);
		for (int i = DATA_START_ROW_INDEX; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (AutoGenerateUtils.isEmpty(row)) {
				continue;
			}
			int index = 0;
			for (int j = SUM_START_COL_INDEX; j < row.getLastCellNum(); j++) {
				if (super.isNotSumColumn(sheet, j)) {
					continue;
				}
				String firstCellValue = AutoGenerateUtils.getCellValue(
						firstRow, j);
				String cellValue = AutoGenerateUtils.getCellValue(row, j);
				super.getTotalSumValueMap(sheet, cellValue, j);
				super.getJoinTotalSumValueMap(xwb, sheet, j);
				Double number = new Double(0);
				String key = Integer.toString(index);
				if (map.containsKey(key)) {
					number = map.get(key);
				}
				if (super.isDividedColumn(sheet, j)) {
					DecimalFormat f = super.getNumberFormat(dataFirstRow, j);
					Matcher matcher = DIVIDE_FOMULA_PATTERN
							.matcher(firstCellValue);
					if (matcher.find()) {
						double numerator = map.get(Integer.toString(Integer
								.parseInt(matcher.group(1))
								- SUM_START_COL_INDEX));
						double denominator = map.get(Integer.toString(Integer
								.parseInt(matcher.group(2))
								- SUM_START_COL_INDEX));
						if (denominator == 0) {
							number = 0d;
						} else {
							number = numerator / denominator;
						}
					}
					Matcher joinMatcher = JOIN_DIVIDE_FOMULA_PATTERN
							.matcher(firstCellValue);
					if (joinMatcher.find()) {
						double numerator = map.get(Integer.toString(Integer
								.parseInt(joinMatcher.group(1))
								- SUM_START_COL_INDEX));
						double denominator = super.joinTotalValueMap.get(key);
						if (denominator == 0) {
							number = 0d;
						} else {
							number = numerator / denominator;
						}
					}
				} else {
					number += Double.parseDouble(cellValue);
				}
				map.put(key, number);
				index++;
			}
		}
		return map;
	}

	/**
	 * 生成汇总后的单元格数据信息字串
	 * 
	 * @param sheet
	 *            Sheet 工作表sheet
	 * @param map
	 *            Map<String, Double> 汇总的值Map
	 * @param rowIndex
	 *            int
	 * @return List<TableCellData> 汇总后的单元格数据信息
	 */
	private List<TableCellData> generateTdString(Sheet sheet,
			Map<String, Double> map, int rowIndex) {
		List<TableCellData> cellDataList = new ArrayList<TableCellData>();
		Row firstRow = sheet.getRow(DATA_START_ROW_INDEX);
		Row dataFirstRow = sheet.getRow(DATA_START_ROW_INDEX);
		int index = 0;
		int descCol = 0;
		for (int j = SUM_START_COL_INDEX; j < firstRow.getLastCellNum(); j++) {
			if (super.isNotSumColumn(sheet, j)) {
				descCol++;
				continue;
			}
			TableCellData data = new TableCellData();
			int width = (int) (sheet.getColumnWidth(j) * AutoGenerateUtils.CHAR_WIDTH_PERCENT);
			String key = Integer.toString(index);
			DecimalFormat fmt = super.getNumberFormat(dataFirstRow, index
					+ SUM_START_COL_INDEX);
			Double number = map.get(key);
			String value = fmt.format(number);
			data.setWidth(width);
			data.setCellValue(value);
			data.setRowIndex(rowIndex);
			data.setColIndex(j - SUM_START_COL_INDEX - descCol + 1);
			cellDataList.add(data);
			index++;
		}
		return cellDataList;
	}
}
