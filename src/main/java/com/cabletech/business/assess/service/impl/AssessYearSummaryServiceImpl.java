package com.cabletech.business.assess.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.ah.excelreport.exports.ExcelExportUtils;
import com.cabletech.business.assess.dao.AssessYearSummaryDao;
import com.cabletech.business.assess.service.AssessYearSummaryService;
import com.cabletech.business.excel.TableCellData;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 年考核汇总业务接口实现
 * 
 * @author 杨隽 2012-08-09 创建
 * 
 */
@Service
@Transactional(readOnly = true)
@SuppressWarnings("all")
public class AssessYearSummaryServiceImpl extends BaseServiceImpl implements
		AssessYearSummaryService {
	public static final String SUMMARY_TYPE = "summary";
	public static final String RANK_TYPE = "rank";
	@Resource(name = "assessYearSummaryDao")
	private AssessYearSummaryDao assessYearSummaryDao;

	@Override
	public Map<String, Object> getSummaryScoreDataMap(String yearMonth) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> tableItemList = assessYearSummaryDao
				.getTableItemList(yearMonth);
		if (CollectionUtils.isEmpty(tableItemList)) {
			return map;
		}
		int length = tableItemList.size();
		String[] rowNum = new String[length];
		String[] rowName = new String[length];
		for (int i = 0; i < length; i++) {
			rowNum[i] = (String) tableItemList.get(i).get("RN");
			rowName[i] = (String) tableItemList.get(i).get("ITEM_ID");
		}
		List<Map<String, Object>> resultList = assessYearSummaryDao
				.getYearSummaryDataList(yearMonth, rowNum, rowName);
		map.put("tableItemList", tableItemList);
		map.put("resultList", resultList);
		return map;
	}

	@Override
	public Map<String, Object> getSummaryScoreExcelMap(String yearMonth,
			String type) {
		Map<String, Object> tableMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = getSummaryScoreDataMap(yearMonth);
		if (MapUtils.isEmpty(dataMap)) {
			return tableMap;
		}
		List<List<TableCellData>> dataList = new ArrayList<List<TableCellData>>();
		List<Map<String, Object>> tableItemList = (List<Map<String, Object>>) dataMap
				.get("tableItemList");
		List<Map<String, Object>> resultList = (List<Map<String, Object>>) dataMap
				.get("resultList");
		writeSubjectTr(type, tableItemList, dataList);
		if (CollectionUtils.isNotEmpty(resultList)) {
			for (int i = 0; i < resultList.size(); i++) {
				Map<String, Object> map = resultList.get(i);
				List<TableCellData> rowList = new ArrayList<TableCellData>();
				int colIndex = 0;
				if (RANK_TYPE.equals(type)) {
					TableCellData firstCellData = new TableCellData();
					firstCellData.setRowIndex(0);
					firstCellData.setColIndex(colIndex);
					firstCellData.setCellValue((String) map.get("NO_"));
					rowList.add(firstCellData);
					colIndex++;
				}
				TableCellData secondCellData = new TableCellData();
				secondCellData.setRowIndex(0);
				secondCellData.setColIndex(colIndex);
				secondCellData.setCellValue((String) map.get("NAME"));
				rowList.add(secondCellData);
				colIndex++;
				if (CollectionUtils.isNotEmpty(tableItemList)) {
					for (int j = 0; j < tableItemList.size(); j++) {
						Map<String, Object> itemMap = tableItemList.get(j);
						String key = "ITEM_" + itemMap.get("ITEM_ID");
						TableCellData cellData = new TableCellData();
						cellData.setRowIndex(0);
						cellData.setColIndex(colIndex);
						cellData.setCellValue((String) map.get(key));
						rowList.add(cellData);
						colIndex++;
					}
				}
				TableCellData sixthCellData = new TableCellData();
				sixthCellData.setRowIndex(0);
				sixthCellData.setColIndex(colIndex);
				sixthCellData.setCellValue((String) map.get("APPERAL_NUM"));
				rowList.add(sixthCellData);
				colIndex++;
				TableCellData seventhCellData = new TableCellData();
				seventhCellData.setRowIndex(0);
				seventhCellData.setColIndex(colIndex);
				seventhCellData.setCellValue((String) map.get("SUM_"));
				rowList.add(seventhCellData);
				dataList.add(rowList);
			}
		}
		tableMap.put("dataList", dataList);
		return tableMap;
	}

	/**
	 * 写入考核导出标题行数据
	 * 
	 * @param type
	 *            String
	 * @param tableItemList
	 *            List<Map<String,Object>>
	 * @param dataList
	 *            List<List<TableCellData>>
	 */
	private void writeSubjectTr(String type,
			List<Map<String, Object>> tableItemList,
			List<List<TableCellData>> dataList) {
		List<TableCellData> subjectList = new ArrayList<TableCellData>();
		int colIndex = 0;
		if (RANK_TYPE.equals(type)) {
			TableCellData firstCellData = new TableCellData();
			firstCellData.setRowIndex(0);
			firstCellData.setColIndex(colIndex);
			firstCellData.setCellValue("排名");
			subjectList.add(firstCellData);
			colIndex++;
		}
		TableCellData secondCellData = new TableCellData();
		secondCellData.setRowIndex(0);
		secondCellData.setColIndex(colIndex);
		secondCellData.setCellValue("代维公司");
		subjectList.add(secondCellData);
		colIndex++;
		if (CollectionUtils.isNotEmpty(tableItemList)) {
			for (int i = 0; i < tableItemList.size(); i++) {
				TableCellData cellData = new TableCellData();
				cellData.setRowIndex(0);
				cellData.setColIndex(colIndex);
				cellData.setCellValue((String) tableItemList.get(i).get(
						"ITEM_NAME"));
				subjectList.add(cellData);
				colIndex++;
			}
		}
		TableCellData sixthCellData = new TableCellData();
		sixthCellData.setRowIndex(0);
		sixthCellData.setColIndex(colIndex);
		sixthCellData.setCellValue("申诉次数");
		subjectList.add(sixthCellData);
		colIndex++;
		TableCellData seventhCellData = new TableCellData();
		seventhCellData.setRowIndex(0);
		seventhCellData.setColIndex(colIndex);
		seventhCellData.setCellValue("年度考核成绩");
		subjectList.add(seventhCellData);
		dataList.add(subjectList);
	}

	@Override
	protected BaseDao getBaseDao() {
		return null;
	}
}
