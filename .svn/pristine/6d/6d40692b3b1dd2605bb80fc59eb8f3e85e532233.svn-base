package com.cabletech.business.ah.excelreport.sum;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.business.ah.excelreport.dao.AhExcelReportRecodeDao;
import com.cabletech.business.ah.excelreport.dao.AhExcelReportSheetTypeDao;
import com.cabletech.business.ah.excelreport.model.AhExcelReportRecode;
import com.cabletech.business.ah.excelreport.model.AhExcelReportSheet;
import com.cabletech.business.excel.AutoGenerateFactory;
import com.cabletech.business.excel.AutoGenerateUtils;
import com.cabletech.business.excel.TableCellData;

/**
 * Sheet汇总基类
 * 
 * @author 杨隽 2012-06-28 创建
 * 
 */
public abstract class BaseSheetSumGenerate {
	/**
	 * 除法公式标识符
	 */
	public static final String SUM_DIV_OPERATOR_CHAR = "%";
	/**
	 * 除法公式匹配表达式
	 */
	public static final Pattern DIVIDE_FOMULA_PATTERN = Pattern
			.compile("^\\%\\((\\d+)/(\\d+)\\)");
	/**
	 * 关联除法公式匹配表达式
	 */
	public static final Pattern JOIN_DIVIDE_FOMULA_PATTERN = Pattern
			.compile("^\\%\\((\\d+)/(\\d+)\\!(\\d+)\\)");
	/**
	 * “合计”的字串常量
	 */
	public static final String SUM_STRING_CONSTANT = "合计";
	/**
	 * 数据开始行的位置
	 */
	public static final int DATA_START_ROW_INDEX = 5;
	/**
	 * 合计开始列的位置
	 */
	public static final int SUM_START_COL_INDEX = 5;
	/**
	 * 不需进行汇总的列标记
	 */
	public static final int NOT_SUM_COL_FLAG = 0;
	/**
	 * 地市每个月上传的excel报表DAO
	 */
	@Resource(name = "ahExcelReportRecodeDao")
	private AhExcelReportRecodeDao ahExcelReportRecodeDao;
	/**
	 * 地市每个月上传的excel报表中sheet类型DAO
	 */
	@Resource(name = "ahExcelReportSheetTypeDao")
	private AhExcelReportSheetTypeDao ahExcelReportSheetTypeDao;
	/**
	 * 基础服务包
	 */
	@Resource(name = "baseInfoProvider")
	protected BaseInfoProvider baseInfoProvider;
	/**
	 * 合计总值Map
	 */
	protected Map<String, Double> totalValueMap;
	/**
	 * 关联合计总值Map
	 */
	protected Map<String, Double> joinTotalValueMap;
	/**
	 * 工作表列表
	 */
	protected List<Workbook> wbList;

	/**
	 * 根据查询条件获取汇总的Sheet数据信息
	 * 
	 * @param report
	 *            AhExcelReportRecode 查询条件
	 * @param sheetType
	 *            Sheet类型信息
	 * @return Map<String, Object> 汇总的Sheet数据信息
	 * @throws Exception
	 */
	public Map<String, Object> getTableMap(AhExcelReportRecode report,
			AhExcelReportSheet sheetType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<AhExcelReportRecode> list = ahExcelReportRecodeDao
				.queryAhExcelReportRecodeList(report);
		if (CollectionUtils.isEmpty(list)) {
			return map;
		}
		Workbook sampleWb = getSampleWorkbook(list);
		int colNum = getSumColNum(sampleWb, sheetType);
		int colSpan = getSumColSpan(report, sheetType, sampleWb);
		totalValueMap = new HashMap<String, Double>();
		joinTotalValueMap = new HashMap<String, Double>();
		wbList = new ArrayList<Workbook>();
		int tableWidth = AutoGenerateUtils.getTableWidth(
				sampleWb.getSheetAt(sheetType.getSheetNum()),
				DATA_START_ROW_INDEX);
		map.put("tableWidth", tableWidth);
		List<List<TableCellData>> rowDataList = new ArrayList<List<TableCellData>>();
		rowDataList.addAll(getTableSubjectStr(sheetType, list));
		int rowIndex = DATA_START_ROW_INDEX - sheetType.getRow();
		for (int i = 0; i < list.size(); i++) {
			AhExcelReportRecode record = list.get(i);
			rowIndex = getOneSheetTableStr(rowDataList, report.getSumType(),
					sheetType, record, rowIndex);
		}
		Sheet sheet = sampleWb.getSheetAt(sheetType.getSheetNum());
		rowDataList.add(getTotalTrString(sheet, colNum, colSpan, rowIndex));
		map.put("dataList", rowDataList);
		return map;
	}

	/**
	 * 获取汇总的table单元格信息
	 * 
	 * @param xwb
	 *            Workbook 工作表
	 * @param sheetType
	 *            AhExcelReportSheet sheet类型信息
	 * @param rowDataList
	 *            List<List<TableCellData>>
	 * @param record
	 *            AhExcelReportRecode
	 * @param rowIndex
	 *            int
	 * @return int
	 */
	public abstract int getTableTdStr(Workbook xwb,
			AhExcelReportSheet sheetType,
			List<List<TableCellData>> rowDataList, AhExcelReportRecode record,
			int rowIndex);

	/**
	 * 获取工作表sheet的标题行
	 * 
	 * @param sheetType
	 *            AhExcelReportSheet sheet类型信息
	 * @param list
	 *            List<AhExcelReportRecode> 汇总工作表列表
	 * @return List<List<TableCellData>> 工作表sheet的标题行
	 * @throws Exception
	 */
	public abstract List<List<TableCellData>> getTableSubjectStr(
			AhExcelReportSheet sheetType, List<AhExcelReportRecode> list)
			throws Exception;

	public AhExcelReportRecodeDao getAhExcelReportRecodeDao() {
		return ahExcelReportRecodeDao;
	}

	public void setAhExcelReportRecodeDao(
			AhExcelReportRecodeDao ahExcelReportRecodeDao) {
		this.ahExcelReportRecodeDao = ahExcelReportRecodeDao;
	}

	/**
	 * 判断是否为合计行
	 * 
	 * @param sheetType
	 *            AhExcelReportSheet sheet类型信息
	 * @param row
	 *            Row 当前读入行
	 * @return boolean 判断是否为合计行
	 */
	protected boolean isSumRow(AhExcelReportSheet sheetType, Row row) {
		return SUM_STRING_CONSTANT.equals(AutoGenerateUtils.getCellValue(row,
				sheetType.getCol()));
	}

	/**
	 * 判断该列是否进行汇总
	 * 
	 * @param sheet
	 *            Sheet 工作表sheet
	 * @param colIndex
	 *            int 列索引
	 * @return boolean 判断该列是否进行汇总
	 */
	protected boolean isNotSumColumn(Sheet sheet, int colIndex) {
		Row firstRow = sheet.getRow(0);
		String cellValue = AutoGenerateUtils.getCellValue(firstRow, colIndex);
		if (StringUtils.isBlank(cellValue)) {
			return false;
		}
		try {
			int value = (int) Double.parseDouble(cellValue);
			return value == NOT_SUM_COL_FLAG;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	/**
	 * 判断该列是否为除法运算列
	 * 
	 * @param sheet
	 *            Sheet 工作表sheet
	 * @param colIndex
	 *            int 列索引
	 * @return boolean 判断该列是否进行汇总
	 */
	protected boolean isDividedColumn(Sheet sheet, int colIndex) {
		Row firstRow = sheet.getRow(0);
		String cellValue = AutoGenerateUtils.getCellValue(firstRow, colIndex);
		if (StringUtils.isBlank(cellValue)) {
			return false;
		}
		Matcher matcher = DIVIDE_FOMULA_PATTERN.matcher(cellValue);
		Matcher joinMatcher = JOIN_DIVIDE_FOMULA_PATTERN.matcher(cellValue);
		return matcher.find() || joinMatcher.find();
	}

	/**
	 * 根据工作表sheet中单元格的数值和索引进行累加生成汇总的值Map
	 * 
	 * @param sheet
	 *            Sheet 工作表sheet
	 * @param cellValue
	 *            String 工作表sheet中单元格的数值
	 * @param index
	 *            int 工作表sheet中单元格的索引
	 */
	protected void getTotalSumValueMap(Sheet sheet, String cellValue, int index) {
		Double number = new Double(0);
		String key = Integer.toString(index);
		if (totalValueMap.containsKey(key)) {
			number = totalValueMap.get(key);
		}
		if (isDividedColumn(sheet, index)) {
			return;
		}
		if (StringUtils.isNotBlank(cellValue)) {
			number += Double.parseDouble(cellValue);
		}
		totalValueMap.put(key, number);
		return;
	}

	/**
	 * 根据工作表sheet中单元格的数值和索引进行累加生成关联汇总的值Map
	 * 
	 * @param wb
	 *            Workbook 工作表
	 * @param sheet
	 *            Sheet 工作表sheet
	 * @param colIndex
	 *            int 列索引
	 */
	protected void getJoinTotalSumValueMap(Workbook wb, Sheet sheet,
			int colIndex) {
		if (wbList.contains(wb)) {
			return;
		}
		Double number = new Double(0);
		Row firstRow = sheet.getRow(0);
		if (AutoGenerateUtils.isEmpty(firstRow)) {
			return;
		}
		String cellValue = AutoGenerateUtils.getCellValue(firstRow, colIndex);
		Matcher joinMatcher = JOIN_DIVIDE_FOMULA_PATTERN.matcher(cellValue);
		if (!joinMatcher.find()) {
			return;
		}
		int sheetIndex = Integer.parseInt(joinMatcher.group(2));
		int sheetColIndex = Integer.parseInt(joinMatcher.group(3));
		Sheet joinSheet = wb.getSheetAt(sheetIndex);
		AhExcelReportSheet sheetType = ahExcelReportSheetTypeDao.findUniqueBy(
				"sheetNum", sheetIndex);
		for (int i = DATA_START_ROW_INDEX; i < joinSheet
				.getPhysicalNumberOfRows(); i++) {
			Row row = joinSheet.getRow(i);
			if (!isSumRow(sheetType, row)) {
				continue;
			}
			String joinCellValue = AutoGenerateUtils.getCellValue(row,
					sheetColIndex);
			String key = Integer.toString(colIndex);
			if (joinTotalValueMap.containsKey(key)) {
				number = joinTotalValueMap.get(key);
			}
			if (StringUtils.isBlank(joinCellValue)) {
				continue;
			}
			number += Double.parseDouble(joinCellValue);
			joinTotalValueMap.put(key, number);
		}
		return;
	}

	/**
	 * 获取数据单元格的格式
	 * 
	 * @param dataFirstRow
	 *            Row 数据开始行
	 * @param cellIndex
	 *            int 列索引
	 * @return DecimalFormat 数据单元格的格式
	 */
	protected DecimalFormat getNumberFormat(Row dataFirstRow, int cellIndex) {
		DecimalFormat f = new DecimalFormat("#0.00");
		Cell cell = dataFirstRow.getCell(cellIndex);
		if (cell != null) {
			CellStyle style = cell.getCellStyle();
			String fmt = style.getDataFormatString();
			if (fmt.endsWith(AutoGenerateUtils.PERCENT_SUFFIX)) {
				f = new DecimalFormat(fmt);
			}
		}
		return f;
	}

	/**
	 * 获取工作表的样本数据
	 * 
	 * @param list
	 *            List<AhExcelReportRecode> 工作表数据列表
	 * @return AhExcelReportRecode 工作表的样本数据
	 * @throws Exception
	 */
	private Workbook getSampleWorkbook(List<AhExcelReportRecode> list)
			throws Exception {
		AhExcelReportRecode record = list.get(0);
		String path = record.getFileUrl();
		Workbook xwb = AutoGenerateFactory.getWorkbook(path);
		return xwb;
	}

	/**
	 * 获取包含合计行的sheet的列数
	 * 
	 * @param xwb
	 *            Workbook 工作表
	 * @param sheetType
	 *            AhExcelReportSheet sheet类型信息
	 * @return int 汇总的列数
	 */
	private int getSumColNum(Workbook xwb, AhExcelReportSheet sheetType) {
		Sheet sheet = xwb.getSheetAt(sheetType.getSheetNum());
		Row row = sheet.getRow(DATA_START_ROW_INDEX - 1);
		if (row == null) {
			return 0;
		}
		int colNum = 0;
		int startCol = SUM_START_COL_INDEX;
		for (int i = startCol; i < row.getLastCellNum(); i++) {
			String cellValue = AutoGenerateUtils.getCellValue(row, i);
			if (StringUtils.isNotBlank(cellValue)) {
				if (!isNotSumColumn(sheet, i)) {
					colNum++;
				}
			}
		}
		return colNum;
	}

	/**
	 * 获取汇总“合计”所占列数
	 * 
	 * @param report
	 *            AhExcelReportRecode 汇总数据信息
	 * @param sheetType
	 *            AhExcelReportSheet sheet类型信息
	 * @param xwb
	 *            Workbook 工作表
	 * @return int 汇总“合计”所占列数
	 */
	private int getSumColSpan(AhExcelReportRecode report,
			AhExcelReportSheet sheetType, Workbook xwb) {
		int colSpan = 0;
		if (AhExcelReportSheet.PROVINCE_SUM_TYPE.equals(report.getSumType())) {
			Sheet sheet = xwb.getSheetAt(sheetType.getSheetNum());
			for (int i = sheetType.getCol(); i < SUM_START_COL_INDEX; i++) {
				if (!isNotSumColumn(sheet, i)) {
					colSpan++;
				}
			}
		} else {
			colSpan = 1;
		}
		return colSpan;
	}

	/**
	 * 获取单个EXCEL表格数据的表格数据字串
	 * 
	 * @param rowDataList
	 *            List<List<TableCellData>>
	 * @param sumType
	 *            String 统计类型
	 * @param sheetType
	 *            AhExcelReportSheet sheet类型信息
	 * @param record
	 *            AhExcelReportRecode 汇总数据信息
	 * @param rowIndex
	 *            int
	 * @return int
	 * @throws Exception
	 */
	private int getOneSheetTableStr(List<List<TableCellData>> rowDataList,
			String sumType, AhExcelReportSheet sheetType,
			AhExcelReportRecode record, int rowIndex) throws Exception {
		String path = record.getFileUrl();
		Workbook xwb = AutoGenerateFactory.getWorkbook(path);
		return getTableTdStr(xwb, sheetType, rowDataList, record, rowIndex);
	}

	/**
	 * 生成汇总后表格的“合计”行数据
	 * 
	 * @param sheet
	 *            Sheet 工作表中的sheet
	 * @param colNum
	 *            int “合计”行的数据列数
	 * @param colSpan
	 *            int “合计”行的所在列宽
	 * @param rowIndex
	 *            int “合计”行的行索引
	 * @return List<TableCellData> 汇总后表格的“合计”行数据
	 */
	private List<TableCellData> getTotalTrString(Sheet sheet, int colNum,
			int colSpan, int rowIndex) {
		List<TableCellData> cellDataList = new ArrayList<TableCellData>();
		TableCellData sumCellData = new TableCellData();
		sumCellData.setColSpan(colSpan);
		sumCellData.setCellValue("合计");
		sumCellData.setRowIndex(rowIndex);
		sumCellData.setColIndex(0);
		cellDataList.add(sumCellData);
		for (int i = 0; i < colNum; i++) {
			sumCellData = new TableCellData();
			int cellIndex = i + SUM_START_COL_INDEX;
			if (isNotSumColumn(sheet, cellIndex)) {
				continue;
			}
			String value = getTotalValue(sheet, Integer.toString(cellIndex));
			sumCellData.setCellValue(value);
			sumCellData.setRowIndex(rowIndex);
			sumCellData.setColIndex(i + colSpan);
			cellDataList.add(sumCellData);
		}
		return cellDataList;
	}

	/**
	 * 获取汇总的数值
	 * 
	 * @param key
	 *            String 单元格索引
	 * @param sheet
	 *            Sheet 工作表sheet
	 * @return String 汇总的数值
	 */
	private String getTotalValue(Sheet sheet, String key) {
		Row firstRow = sheet.getRow(0);
		Row dataFirstRow = sheet.getRow(DATA_START_ROW_INDEX);
		int cellIndex = Integer.parseInt(key);
		String cellValue = AutoGenerateUtils.getCellValue(firstRow, cellIndex);
		String value = "";
		DecimalFormat f = getNumberFormat(dataFirstRow, cellIndex);
		Matcher matcher = DIVIDE_FOMULA_PATTERN.matcher(cellValue);
		if (matcher.find()) {
			double numerator = totalValueMap.get(matcher.group(1));
			double denominator = totalValueMap.get(matcher.group(2));
			if (denominator == 0) {
				value = f.format(0);
			} else {
				value = f.format(numerator / denominator);
			}
			return value;
		}
		Matcher joinMatcher = JOIN_DIVIDE_FOMULA_PATTERN.matcher(cellValue);
		if (joinMatcher.find()) {
			double numerator = totalValueMap.get(joinMatcher.group(1));
			double denominator = joinTotalValueMap.get(key);
			if (denominator == 0) {
				value = f.format(0);
			} else {
				value = f.format(numerator / denominator);
			}
			return value;
		}
		if (totalValueMap.containsKey(key)) {
			double number = totalValueMap.get(key);
			value = f.format(number);
		}
		return value;
	}
}
