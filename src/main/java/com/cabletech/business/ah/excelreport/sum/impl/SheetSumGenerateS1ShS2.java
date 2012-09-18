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
 * 不包含合计行Sheet按全部汇总
 * 
 * @author 杨隽 2012-06-28 创建
 * 
 */
@Component
public class SheetSumGenerateS1ShS2 extends BaseSheetSumGenerate {
	@Override
	public int getTableTdStr(Workbook xwb, AhExcelReportSheet sheetType,
			List<List<TableCellData>> rowDataList, AhExcelReportRecode record,
			int rowIndex) {
		Sheet sheet = xwb.getSheetAt(sheetType.getSheetNum());
		Row row;
		int currentRowIndex = rowIndex;
		for (int i = DATA_START_ROW_INDEX; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (AutoGenerateUtils.isEmpty(row)) {
				continue;
			}
			List<TableCellData> cellDataList = new ArrayList<TableCellData>();
			int descCol = 0;
			for (int j = sheetType.getCol(); j < row.getLastCellNum(); j++) {
				if (super.isNotSumColumn(sheet, j)) {
					descCol++;
					continue;
				}
				TableCellData data = AutoGenerateUtils.getTdGenerateData(sheet,
						i, j);
				if (data != null) {
					data.setRowIndex(currentRowIndex);
					data.setColIndex(j - sheetType.getCol() - descCol);
					cellDataList.add(data);
				}
				if (j >= SUM_START_COL_INDEX && !super.isNotSumColumn(sheet, j)) {
					String cellValue = AutoGenerateUtils.getCellValue(row, j);
					super.getTotalSumValueMap(sheet, cellValue, j);
					super.getJoinTotalSumValueMap(xwb, sheet, j);
				}
			}
			currentRowIndex++;
			rowDataList.add(cellDataList);
			super.wbList.add(xwb);
		}
		return currentRowIndex;
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
		for (int i = sheetType.getRow(); i < DATA_START_ROW_INDEX; i++) {
			Row row = sheet.getRow(i);
			if (AutoGenerateUtils.isEmpty(row)) {
				continue;
			}
			List<TableCellData> cellDataList = new ArrayList<TableCellData>();
			int descCol = 0;
			for (int j = sheetType.getCol(); j < row.getLastCellNum(); j++) {
				if (super.isNotSumColumn(sheet, j)) {
					descCol++;
					continue;
				}
				TableCellData data = AutoGenerateUtils.getTdGenerateData(sheet,
						i, j);
				if (data != null) {
					data.setRowIndex(i - sheetType.getRow());
					data.setColIndex(j - sheetType.getCol() - descCol);
					cellDataList.add(data);
				}
			}
			rowDataList.add(cellDataList);
		}
		return rowDataList;
	}
}
