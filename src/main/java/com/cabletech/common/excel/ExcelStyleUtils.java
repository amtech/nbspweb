package com.cabletech.common.excel;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.dom4j.Element;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.excel.parameter.AlignmentParameter;
import com.cabletech.common.excel.parameter.FontParameter;
import com.cabletech.common.xmlparse.ParseXmlTools;

/**
 * Excel样式工具类
 * 
 * @author 杨隽 2012-02-15 创建
 * @author 杨隽 2012-02-16 添加setFontStyle方法
 * 
 */
public class ExcelStyleUtils {
	/**
	 * 设置工作表的头部和尾部信息
	 * 
	 * @param sheet
	 *            HSSFSheet 工作表信息
	 * @param userinfo
	 *            UserInfo 当前用户信息
	 */
	public static void setHeaderFooter(HSSFSheet sheet, UserInfo userinfo) {
		sheet.getHeader().setCenter(
				HSSFHeader.fontSize((short) 9) + "北京鑫干线线路巡检系统" + "       日期: "
						+ HSSFHeader.date());
		sheet.getFooter().setCenter(
				HSSFHeader.fontSize((short) 9) + "导出人："
						+ userinfo.getUserName() + "        第 "
						+ HSSFFooter.page() + " 页   共 " + HSSFFooter.numPages()
						+ " 页 ");
	}

	/**
	 * 获取工作表的Excel样式
	 * 
	 * @param workbook
	 *            HSSFWorkbook 工作表
	 * @return HSSFCellStyle 工作表的Excel样式
	 */
	public static HSSFCellStyle getExcelStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		return style;
	}

	/**
	 * 设置单元格的水平和垂直对齐方式
	 * 
	 * @param style
	 *            HSSFCellStyle 工作表的Excel样式
	 * @param parameter
	 *            AlignmentParameter 水平和垂直对齐方式传入参数
	 */
	public static void setAlignment(HSSFCellStyle style,
			AlignmentParameter parameter) {
		style.setVerticalAlignment(parameter.getvAlignment());
		style.setAlignment(parameter.getAlignment());
	}

	/**
	 * 设置单元格的字体格式
	 * 
	 * @param style
	 *            HSSFCellStyle 工作表的Excel样式
	 * @param workbook
	 *            HSSFWorkbook 工作表
	 * @param parameter
	 *            FontParameter 字体格式传入参数
	 */
	public static void setFont(HSSFCellStyle style, HSSFWorkbook workbook,
			FontParameter parameter) {
		HSSFFont font = workbook.createFont();
		font.setFontName(parameter.getFontName());
		font.setFontHeightInPoints(parameter.getFontSize());
		font.setBoldweight(parameter.getBoldWeight());
		font.setColor(parameter.getFontColor());
		font.setItalic(parameter.isFontItalic());
		font.setStrikeout(parameter.isFontStrikeout());
		style.setFont(font);
	}

	/**
	 * 设置单元格上边框线
	 * 
	 * @param style
	 *            HSSFCellStyle Excel样式
	 */
	public static void top(HSSFCellStyle style) {
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
	}

	/**
	 * 设置单元格下边框线
	 * 
	 * @param style
	 *            HSSFCellStyle Excel样式
	 */
	public static void bottom(HSSFCellStyle style) {
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
	}

	/**
	 * 设置单元格左边框线
	 * 
	 * @param style
	 *            HSSFCellStyle Excel样式
	 */
	public static void left(HSSFCellStyle style) {
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
	}

	/**
	 * 设置单元格右边框线
	 * 
	 * @param style
	 *            HSSFCellStyle Excel样式
	 */
	public static void right(HSSFCellStyle style) {
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
	}

	/**
	 * 设置单元格左上边框线
	 * 
	 * @param style
	 *            HSSFCellStyle Excel样式
	 */
	public static void leftTop(HSSFCellStyle style) {
		left(style);
		top(style);
	}

	/**
	 * 设置单元格左下边框线
	 * 
	 * @param style
	 *            HSSFCellStyle Excel样式
	 */
	public static void leftBottom(HSSFCellStyle style) {
		left(style);
		bottom(style);
	}

	/**
	 * 设置单元格右下边框线
	 * 
	 * @param style
	 *            HSSFCellStyle Excel样式
	 */
	public static void rightBottom(HSSFCellStyle style) {
		right(style);
		bottom(style);
	}

	/**
	 * 设置单元格边框线
	 * 
	 * @param style
	 *            HSSFCellStyle Excel样式
	 */
	public static void fourLine(HSSFCellStyle style) {
		left(style);
		right(style);
		bottom(style);
		top(style);
	}

	/**
	 * 设置Excel报表的标题样式
	 * 
	 * @param wb
	 *            HSSFWorkbook 工作表
	 * @return HSSFCellStyle Excel样式
	 */
	public static HSSFCellStyle getTitleStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		FontParameter fontParameter = new FontParameter();
		fontParameter.setFontSize((short) 18);
		fontParameter.setBoldWeight((short) 100000);
		AlignmentParameter parameter = new AlignmentParameter();
		setAlignment(style, parameter);
		setFont(style, wb, fontParameter);
		return style;
	}

	/**
	 * 设置Excel报表的数据列标题样式
	 * 
	 * @param wb
	 *            HSSFWorkbook 工作表
	 * @return HSSFCellStyle Excel样式
	 */
	public static HSSFCellStyle getSubjectStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		FontParameter fontParameter = new FontParameter();
		fontParameter.setBoldWeight((short) 100000);
		AlignmentParameter parameter = new AlignmentParameter();
		setAlignment(style, parameter);
		setFont(style, wb, fontParameter);
		style.setWrapText(true);
		fourLine(style);
		return style;
	}

	/**
	 * 设置Excel报表的数据单元格样式
	 * 
	 * @param wb
	 *            HSSFWorkbook 工作表
	 * @return HSSFCellStyle Excel样式
	 */
	public static HSSFCellStyle getCommonCellStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		FontParameter fontParameter = new FontParameter();
		fontParameter.setFontColor(HSSFColor.BLACK.index);
		AlignmentParameter parameter = new AlignmentParameter();
		parameter.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		setAlignment(style, parameter);
		setFont(style, wb, fontParameter);
		style.setWrapText(true);
		fourLine(style);
		return style;
	}

	/**
	 * 根据XML配置文件的<font>配置数据设置Excel表格的特定字体样式
	 * 
	 * @param style
	 *            HSSFCellStyle 设置的默认样式
	 * @param element
	 *            Element XML配置文件的<font>配置数据
	 * @param wb
	 *            HSSFWorkbook 工作表
	 */
	@SuppressWarnings("rawtypes")
	public static void setFontStyle(HSSFCellStyle style, Element element,
			HSSFWorkbook wb) {
		List fonts = element.elements(ParseXmlTools.FONT_ELEMENT_KEY);
		if (CollectionUtils.isEmpty(fonts)) {
			return;
		}
		Element fontElement = (Element) fonts.get(0);
		FontParameter fontParameter = new FontParameter();
		String fontColor = fontElement
				.attributeValue(ParseXmlTools.FONT_COLOR_ATTRIBUTE_KEY);
		fontParameter.setFontColor(Short.parseShort(fontColor));
		setFont(style, wb, fontParameter);
	}
}
