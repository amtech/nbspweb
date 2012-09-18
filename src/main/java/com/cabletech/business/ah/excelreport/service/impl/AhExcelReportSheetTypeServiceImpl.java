package com.cabletech.business.ah.excelreport.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.ah.excelreport.dao.AhExcelReportSheetTypeDao;
import com.cabletech.business.ah.excelreport.model.AhExcelReportRecode;
import com.cabletech.business.ah.excelreport.model.AhExcelReportSheet;
import com.cabletech.business.ah.excelreport.service.AhExcelReportSheetTypeService;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 地市每个月上传的excel报表中sheet类型service实现
 * 
 * @author 杨隽 2012-06-27 创建
 * @author 杨隽 2012-07-09 添加getSheetExportName()方法
 * 
 */
@SuppressWarnings("unchecked")
@Service
@Transactional
public class AhExcelReportSheetTypeServiceImpl extends
		BaseServiceImpl<AhExcelReportSheet, String> implements
		AhExcelReportSheetTypeService {
	@Resource(name = "ahExcelReportSheetTypeDao")
	private AhExcelReportSheetTypeDao ahExcelReportSheetTypeDao;

	@Override
	protected AhExcelReportSheetTypeDao getBaseDao() {
		return ahExcelReportSheetTypeDao;
	}

	@Override
	public List<Map<String, Object>> queryAllList() {
		return ahExcelReportSheetTypeDao.queryListByCondition("");
	}

	@Override
	public List<Map<String, Object>> querySumSheetTypeList() {
		String condition = " and flg!=" + AhExcelReportSheet.SHEET_TYPE_ZERO;
		return ahExcelReportSheetTypeDao.queryListByCondition(condition);
	}

	@Override
	public AhExcelReportSheet viewAhExcelReportSheet(String id) {
		return ahExcelReportSheetTypeDao.get(id);
	}

	@Override
	public String getSheetExportName(AhExcelReportRecode report) {
		DateFormat df = new SimpleDateFormat("yyyy年MM月");
		String dateStr = df.format(report.getReportDate());
		String sheetName = report.getSheetName();
		String sumType = "";
		if (AhExcelReportSheet.CITY_SUM_TYPE.equals(report.getSumType())) {
			sumType = "按地市汇总";
		}
		if (AhExcelReportSheet.PROVINCE_SUM_TYPE.equals(report.getSumType())) {
			sumType = "按全部汇总";
		}
		String title = dateStr + sheetName + sumType;
		return title;
	}
}