package com.cabletech.business.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 获取EXCEL文件工作表对象的工厂类
 * 
 * @author 杨隽 2012-06-27 创建
 * 
 */
public class AutoGenerateFactory {
	/**
	 * 根据文件名获取EXCEL工作表对象
	 * 
	 * @param fileName
	 *            String 文件名
	 * @return Workbook EXCEL工作表对象
	 * @throws Exception
	 */
	public static Workbook getWorkbook(String fileName) throws Exception {
		InputStream in = new FileInputStream(new File(fileName));
		String extName = getExtName(fileName);
		Workbook wb = null;
		if ("xls".equals(extName)) {
			wb = new HSSFWorkbook(in);
		}
		if ("xlsx".equals(extName)) {
			wb = new XSSFWorkbook(in);
		}
		in.close();
		return wb;
	}

	/**
	 * 获取文件的扩展名
	 * 
	 * @param fileName
	 *            String 文件名
	 * @return String 文件的扩展名
	 */
	private static String getExtName(String fileName) {
		int index = fileName.lastIndexOf(".") + 1;
		return fileName.substring(index);
	}
}
