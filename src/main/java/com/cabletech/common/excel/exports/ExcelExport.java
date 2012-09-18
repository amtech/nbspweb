package com.cabletech.common.excel.exports;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.dom4j.Element;
import org.springframework.util.CollectionUtils;

import com.cabletech.common.excel.ExcelStyleUtils;
import com.cabletech.common.xmlparse.ParseXmlTools;

/**
 * Excel导出公共功能抽象类
 * 
 * @author 杨隽 2012-02-15 创建
 * @author 杨隽 2012-02-16 修改writeCellData方法支持获取数据的后期处理并添加processDataValue抽象方法
 * @author 杨隽 2012-02-16 添加“XML配置文件中<constant>元素列表”变量
 * @author 杨隽 2012-02-16 添加“XML配置文件中<style>元素列表”变量
 * @author 杨隽 2012-02-16
 *         添加“EXCEL工作表默认标题样式”、“EXCEL工作表默认数据列标题样式”、“EXCEL工作表默认数据单元格样式”变量
 * @author 杨隽 2012-02-16 添加“EXCEL工作表特定样式集合Map”变量
 * @author 杨隽 2012-02-16 提取initWorkbook()方法
 * @author 杨隽 2012-02-16
 *         添加initTableMap方法、setConstant方法、setStyles方法和getCellStyle方法
 * @author 杨隽 2012-02-24 去除getWorkbook()方法，并添加outExcelFile()方法
 * 
 */
public abstract class ExcelExport {
	private Logger logger = Logger.getLogger("ExportExport");
	// 获取数据对象属性值的方法名称
	private static final String GET_VALUE_METHOD_NAME = "getValue";
	// 标题所在行索引
	private static final int TITLE_ROW_INDEX = 0;
	// 标题设置列索引
	private static final int TITLE_CELL_INDEX = 0;
	// 数据标题列所在行索引
	private static final int DATA_SUBJECT_ROW_INDEX = 1;
	// 数据导入开始行索引
	private static final int DATA_START_ROW_INDEX = 2;
	// XML配置文件的解析工具业务服务
	@Resource(name = "parseXmlTools")
	private ParseXmlTools parseXmlTools;
	// XML配置文件中<root>节点
	private Element root;
	// XML配置文件中<column>元素列表
	@SuppressWarnings("rawtypes")
	private List elements;
	// XML配置文件中<constant>元素列表
	private Element constant;
	// XML配置文件中<style>元素列表
	@SuppressWarnings("rawtypes")
	private List styles;
	// EXCEL工作表对象
	private HSSFWorkbook workbook;
	// EXCEL工作表中数据表对象
	private HSSFSheet sheet;
	// EXCEL工作表特定样式集合Map
	private Map<String, HSSFCellStyle> styleMap;
	// EXCEL工作表默认标题样式
	private HSSFCellStyle defaultTitleStyle;
	// EXCEL工作表默认数据列标题样式
	private HSSFCellStyle defaultSubjectStyle;
	// EXCEL工作表默认数据单元格样式
	private HSSFCellStyle defaultCellStyle;

	/**
	 * 根据数据列表导出Excel
	 * 
	 * @param list
	 *            List 输入的数据列表
	 */
	@SuppressWarnings("rawtypes")
	public void exportExcelData(List list) {
		root = parseXmlTools.getImportXmlElement(getExportXmlId());
		elements = root.elements(ParseXmlTools.COLUMN_ELEMENT_KEY);
		setConstant();
		if (CollectionUtils.isEmpty(elements)) {
			return;
		}
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		initWorkbook();
		writeTitle(elements.size());
		writeSubject();
		writeCellData(list);
	}

	/**
	 * 初始化数据值表
	 * 
	 * @return Map<String, String> 数据值表
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, String> initTableMap() {
		Map<String, String> map = new HashMap<String, String>();
		if (constant == null) {
			return map;
		}
		List elements = constant.elements(ParseXmlTools.PROP_ELEMENT_KEY);
		if (CollectionUtils.isEmpty(elements)) {
			return map;
		}
		for (int i = 0; i < elements.size(); i++) {
			Element element = (Element) elements.get(i);
			String key = element.attributeValue(ParseXmlTools.ID_ATTRBUTE_KEY);
			String value = element.getTextTrim();
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 向响应输出流中输出Excel文件
	 * 
	 * @param out
	 *            OutputStream 响应输出流
	 * @throws IOException
	 */
	public void outExcelFile(OutputStream out) throws IOException {
		workbook.write(out);
	}

	/**
	 * 设置导出文件使用的常量配置节点
	 */
	private void setConstant() {
		if (!CollectionUtils.isEmpty(root
				.elements(ParseXmlTools.CONSTANT_ELEMENT_KEY))) {
			constant = (Element) root.elements(
					ParseXmlTools.CONSTANT_ELEMENT_KEY).get(0);
		}
	}

	/**
	 * 初始化工作表对象并设置默认的标题、数据列标题和单元格样式
	 */
	private void initWorkbook() {
		workbook = new HSSFWorkbook();
		defaultTitleStyle = ExcelStyleUtils.getTitleStyle(workbook);
		defaultSubjectStyle = ExcelStyleUtils.getSubjectStyle(workbook);
		defaultCellStyle = ExcelStyleUtils.getCommonCellStyle(workbook);
		setStyles();
	}

	/**
	 * 设置Excel表格的特定样式集合Map
	 */
	private void setStyles() {
		styleMap = new HashMap<String, HSSFCellStyle>();
		styles = root.elements(ParseXmlTools.STYLE_ELEMENT_KEY);
		if (CollectionUtils.isEmpty(styles)) {
			return;
		}
		for (int i = 0; i < styles.size(); i++) {
			HSSFCellStyle style = ExcelStyleUtils.getCommonCellStyle(workbook);
			Element element = (Element) styles.get(i);
			String key = (String) element
					.attributeValue(ParseXmlTools.ID_ATTRBUTE_KEY);
			ExcelStyleUtils.setFontStyle(style, element, workbook);
			styleMap.put(key, style);
		}
	}

	/**
	 * 写入Excel工作表的标题
	 * 
	 * @param size
	 *            int 标题所占列数
	 */
	@SuppressWarnings("deprecation")
	private void writeTitle(int size) {
		// TODO Auto-generated method stub
		String title = root
				.attributeValue(ParseXmlTools.EXCEL_TITLE_ATTRIBUTE_KEY);
		sheet = workbook.createSheet(title);
		sheet.addMergedRegion(new Region(TITLE_ROW_INDEX,
				(short) TITLE_CELL_INDEX, TITLE_ROW_INDEX,
				(short) (TITLE_CELL_INDEX + size - 1)));
		HSSFRow row = sheet.createRow(TITLE_ROW_INDEX);
		HSSFCell cell = row.createCell(TITLE_CELL_INDEX);
		HSSFCellStyle style = getCellStyle(root, defaultTitleStyle);
		cell.setCellStyle(style);
		cell.setCellValue(title);
	}

	/**
	 * 写入Excel工作表数据列标题
	 */
	private void writeSubject() {
		// TODO Auto-generated method stub
		HSSFRow row = sheet.createRow(DATA_SUBJECT_ROW_INDEX);
		for (int i = 0; i < elements.size(); i++) {
			Element element = (Element) elements.get(i);
			String colIndex = element
					.attributeValue(ParseXmlTools.COL_INDEX_ATTRIBUTE_KEY);
			String subject = element
					.attributeValue(ParseXmlTools.SUBJECT_ATTRIBUTE_KEY);
			int columnIndex = Integer.parseInt(colIndex);
			sheet.autoSizeColumn(columnIndex);
			HSSFCell cell = row.createCell(columnIndex);
			HSSFCellStyle style = getStyle(element, defaultSubjectStyle,
					ParseXmlTools.SUBJECT_STYLE_ID_ATTRIBUTE_KEY);
			cell.setCellStyle(style);
			cell.setCellValue(subject);
		}
	}

	/**
	 * 写入Excel工作表的数据信息
	 * 
	 * @param list
	 *            List 输入的数据信息
	 */
	@SuppressWarnings("rawtypes")
	private void writeCellData(List list) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row = sheet.createRow(DATA_START_ROW_INDEX + i);
			Object object = list.get(i);
			for (int j = 0; j < elements.size(); j++) {
				Element element = (Element) elements.get(j);
				String methodName = element
						.attributeValue(ParseXmlTools.DATA_METHOD_NAME_ATTRIBUTE_KEY);
				String value = getValue(object, element);
				value = processDataValue(value, methodName);
				HSSFCell cell = getCell(row, element);
				HSSFCellStyle style = getCellStyle(element, defaultCellStyle);
				cell.setCellStyle(style);
				cell.setCellValue(value);
			}
		}
	}

	/**
	 * 根据数据对象和配置文件中的数据属性配置信息读取数据的属性值
	 * 
	 * @param object
	 *            Object 数据对象
	 * @param element
	 *            Element 配置文件中的数据属性配置信息
	 * @return String 数据的属性值
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String getValue(Object object, Element element) {
		// TODO Auto-generated method stub
		String propertyName = element
				.attributeValue(ParseXmlTools.PROPERTY_NAME_ATTRIBUTE_KEY);
		String className = root
				.attributeValue(ParseXmlTools.ENTITY_ATTRIBUTE_KEY);
		Object value = "";
		try {
			Class clazz = Class.forName(className);
			Method method = clazz.getMethod(GET_VALUE_METHOD_NAME,
					Object.class, String.class);
			value = method.invoke(null, object, propertyName);
		} catch (Exception ex) {
			logger.error("读取数据的属性值出错:", ex);
		}
		return value.toString();
	}

	/**
	 * 根据配置文件中的数据属性配置信息设置数据属性单元格的格式
	 * 
	 * @param row
	 *            HSSFRow 数据所在行信息
	 * @param element
	 *            Element 配置文件中的数据属性配置信息
	 * @return HSSFCell 数据属性单元格
	 */
	private HSSFCell getCell(HSSFRow row, Element element) {
		String colIndex = element
				.attributeValue(ParseXmlTools.COL_INDEX_ATTRIBUTE_KEY);
		int columnIndex = Integer.parseInt(colIndex);
		sheet.autoSizeColumn(columnIndex);
		HSSFCell cell = row.createCell(columnIndex);
		return cell;
	}

	/**
	 * 根据XML配置元素的style-id指定样式编号获取特定的样式，默认为缺省样式
	 * 
	 * @param element
	 *            Element XML配置元素
	 * @param defaultStyle
	 *            HSSFCellStyle 缺省样式
	 * @return HSSFCellStyle 单元格的样式
	 */
	private HSSFCellStyle getCellStyle(Element element,
			HSSFCellStyle defaultStyle) {
		return getStyle(element, defaultStyle,
				ParseXmlTools.STYLE_ID_ATTRIBUTE_KEY);
	}

	/**
	 * 根据XML配置元素的指定样式属性名字所指定样式编号获取特定的样式，默认为缺省样式
	 * 
	 * @param element
	 *            Element XML配置元素
	 * @param defaultStyle
	 *            HSSFCellStyle 缺省样式
	 * @param styleIdKey
	 *            String XML配置样式的样式属性名字
	 * @return HSSFCellStyle 单元格的样式
	 */
	private HSSFCellStyle getStyle(Element element, HSSFCellStyle defaultStyle,
			String styleIdKey) {
		HSSFCellStyle style = defaultStyle;
		String styleId = element.attributeValue(styleIdKey);
		if (StringUtils.isNotBlank(styleId)) {
			style = styleMap.get(styleId);
		}
		return style;
	}

	/**
	 * 获取导出Excel配置XML文件的id
	 * 
	 * @return String 导出Excel配置XML文件的id
	 */
	public abstract String getExportXmlId();

	/**
	 * 进行获取后的数据后期处理（使用XML文件中配置的数据转换方法将数据库中存放的属性KEY值转换成输出Excel文件中的VALUE值）
	 * 
	 * @param value
	 *            String 获取后的数据
	 * @param methodName
	 *            String 获取数据处理的调用方法名称
	 * @return String 后期处理后的数据
	 */
	public abstract String processDataValue(String value, String methodName);
}
