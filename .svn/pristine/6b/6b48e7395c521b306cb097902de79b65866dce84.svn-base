package com.cabletech.business.ah.excelreport.sum.impl;

import java.util.ArrayList;
import java.util.List;

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
 * 包含合计行Sheet按地市汇总
 * 
 * @author 杨隽 2012-06-28 创建
 * 
 */
@Component
public class SheetSumGenerateS0ShS1 extends BaseSheetSumGenerate {
	@Override
	public int getTableTdStr(Workbook xwb, AhExcelReportSheet sheetType,
			List<List<TableCellData>> rowDataList, AhExcelReportRecode record,
			int rowIndex) {
		Sheet sheet = xwb.getSheetAt(sheetType.getSheetNum());
		Row row;
		List<TableCellData> cellDataList = new ArrayList<TableCellData>();
		TableCellData regionCellData = new TableCellData();
		String regionName = (String) baseInfoProvider.getRegionService()
				.getRegionMap(record.getRegionId()).get("REGIONNAME");
		regionCellData.setWidth(100);
		regionCellData.setCellValue(regionName);
		regionCellData.setRowIndex(rowIndex);
		regionCellData.setColIndex(0);
		cellDataList.add(regionCellData);
		for (int i = DATA_START_ROW_INDEX; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (AutoGenerateUtils.isEmpty(row)) {
				continue;
			}
			if (!super.isSumRow(sheetType, row)) {
				continue;
			}
			int descCol = 0;
			for (int j = SUM_START_COL_INDEX; j < row.getLastCellNum(); j++) {
				if (super.isNotSumColumn(sheet, j)) {
					descCol++;
					continue;
				}
				TableCellData data = new TableCellData();
				int width = (int) (sheet.getColumnWidth(j) * AutoGenerateUtils.CHAR_WIDTH_PERCENT);
				String cellValue = AutoGenerateUtils.getCellValue(row, j);
				super.getTotalSumValueMap(sheet, cellValue, j);
				super.getJoinTotalSumValueMap(xwb, sheet, j);
				data.setWidth(width);
				data.setCellValue(cellValue);
				data.setRowIndex(rowIndex);
				data.setColIndex(j - SUM_START_COL_INDEX - descCol + 1);
				cellDataList.add(data);
			}
		}
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
		for (int i = sheetType.getRow(); i < DATA_START_ROW_INDEX; i++) {
			Row row = sheet.getRow(i);
			if (AutoGenerateUtils.isEmpty(row)) {
				continue;
			}
			List<TableCellData> cellDataList = new ArrayList<TableCellData>();
			TableCellData data = new TableCellData();
			if (i == sheetType.getRow()) {
				data.setRowSpan(rowSpan);
				data.setWidth(100);
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
}
