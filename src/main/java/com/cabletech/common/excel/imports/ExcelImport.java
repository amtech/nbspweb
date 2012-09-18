package com.cabletech.common.excel.imports;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.Element;

import com.cabletech.common.excel.ExcelUtils;
import com.cabletech.common.util.ReflectionUtils;
import com.cabletech.common.xmlparse.ParseXmlTools;

/**
 * Excel导入公共功能抽象类
 * 
 * @author 杨隽 2012-02-13 创建
 * 
 */
public abstract class ExcelImport {
	private Logger logger = Logger.getLogger("ExcelImport");
	// XML配置文件的解析工具业务服务
	@Resource(name = "parseXmlTools")
	private ParseXmlTools parseXmlTools;
	// XML配置文件中<root>节点
	private Element root;
	// XML配置文件中<column>元素列表
	@SuppressWarnings("rawtypes")
	private List elements;
	// EXCEL工作表对象
	private HSSFWorkbook wb;
	// EXCEL工作表中数据表对象
	private HSSFSheet sheet;

	/**
	 * 根据要导入的Excel数据文件进行数据导入并完成数据校验：
	 * 
	 * @param filePath
	 *            String 要导入的Excel数据文件路径
	 * @throws Exception
	 */
	public void importExcelData(String filePath) {
		sheet = getSheet(filePath);
		root = parseXmlTools.getImportXmlElement(getImportXmlId());
		elements = root.elements(ParseXmlTools.COLUMN_ELEMENT_KEY);
		int lastRow = sheet.getPhysicalNumberOfRows();
		int size = lastRow - getImportDataRow();
		if (size <= 0) {
			return;
		}
		try {
			for (int i = getImportDataRow(); i < lastRow; i++) {
				Object oneCellTemp = getOneCellImportData(i);
				processData(oneCellTemp);
			}
		} catch (Exception ex) {
			logger.error("导入数据信息出错:", ex);
		}
	}

	/**
	 * 读取某一个单元行的对象数据信息
	 * 
	 * @param rowIndex
	 *            int 单元格导入行索引
	 * @return Object 单元行的对象数据信息
	 */
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public Object getOneCellImportData(int rowIndex) throws Exception {
		HSSFRow row;
		HSSFCell cell;
		String cellValue;
		row = sheet.getRow(rowIndex);
		String className = (String) root.attributeValue(ParseXmlTools.ENTITY_ATTRIBUTE_KEY);
		Class clazz = Class.forName(className);
		Object oneCellTemp = clazz.newInstance();
		for (int j = 0; j < elements.size(); j++) {
			Element element = (Element) elements.get(j);
			String colIndex = element.attributeValue(ParseXmlTools.COL_INDEX_ATTRIBUTE_KEY);
			String propertyName = element.attributeValue(ParseXmlTools.PROPERTY_NAME_ATTRIBUTE_KEY);
			cell = row.getCell(Short.parseShort(colIndex));
			if (ExcelUtils.isMergedRegion(sheet, cell)) {
				cellValue = ExcelUtils.getMergedRegionValue(sheet, cell);
			} else {
				cellValue = ExcelUtils.getCellStringValue(cell, wb);
			}
			ReflectionUtils.invokeSetterMethod(oneCellTemp, propertyName,cellValue);
		}
		return oneCellTemp;
	}

	/**
	 * 进行后期的数据处理
	 * 
	 * @param oneCellTemp
	 *            Object 导入Excel的数据
	 */
	public abstract void processData(Object oneCellTemp);

	/**
	 * 获取数据的开始行行索引
	 * 
	 * @return int 开始行行索引
	 */
	public abstract int getImportDataRow();

	/**
	 * 设置后期数据存放的List列表
	 * 
	 * @param lists
	 *            List... 后期数据存放的List列表
	 */
	@SuppressWarnings("rawtypes")
	public abstract void setLists(List... lists);

	/**
	 * 获取导入模板的Xml配置文件的根节点id
	 * 
	 * @return
	 */
	public abstract String getImportXmlId();

	/**
	 * 读取要导入的Excel数据文件的数据表数据
	 * 
	 * @param filePath
	 *            String 要导入的Excel数据文件
	 * @return HSSFSheet 要导入的Excel数据文件的数据表数据
	 */
	private HSSFSheet getSheet(String filePath) {
		wb = new HSSFWorkbook();
		try {
			InputStream in = new FileInputStream(filePath);
			wb = new HSSFWorkbook(in);
		} catch (Exception e) {
			logger.error("获取导入数据文件出错:", e);
		}
		HSSFSheet sheet = wb.getSheetAt(0);
		return sheet;
	}
}
