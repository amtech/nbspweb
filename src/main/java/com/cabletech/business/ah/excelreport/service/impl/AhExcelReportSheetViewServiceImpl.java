package com.cabletech.business.ah.excelreport.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.ah.excelreport.dao.AhExcelReportRecodeDao;
import com.cabletech.business.ah.excelreport.model.AhExcelReportRecode;
import com.cabletech.business.ah.excelreport.model.AhExcelReportSheet;
import com.cabletech.business.ah.excelreport.service.AhExcelReportSheetTypeService;
import com.cabletech.business.ah.excelreport.service.AhExcelReportSheetViewService;
import com.cabletech.business.ah.excelreport.sum.BaseSheetSumGenerate;
import com.cabletech.business.excel.AutoGenerateParameter;
import com.cabletech.business.excel.AutoGenerateUtils;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 查看地市每个月上传的excel报表中sheet工作表service实现
 * 
 * @author 杨隽 2012-06-27 创建
 * 
 */
@SuppressWarnings("unchecked")
@Service
@Transactional
public class AhExcelReportSheetViewServiceImpl extends
		BaseServiceImpl<String, String> implements
		AhExcelReportSheetViewService {
	/**
	 * 地市每个月上传的excel报表中sheet类型service
	 */
	@Resource(name = "ahExcelReportSheetTypeServiceImpl")
	private AhExcelReportSheetTypeService ahExcelReportSheetTypeService;
	/**
	 * 地市每个月上传的excel报表DAO
	 */
	@Resource(name = "ahExcelReportRecodeDao")
	private AhExcelReportRecodeDao ahExcelReportRecodeDao;

	@Override
	protected BaseDao<String, String> getBaseDao() {
		return null;
	}

	@Override
	public Map<String, Object> viewAhExcelReportSheet(AhExcelReportRecode report) {
		AhExcelReportSheet sheetType = ahExcelReportSheetTypeService
				.viewAhExcelReportSheet(report.getReportSheetType());
		AhExcelReportRecode record = ahExcelReportRecodeDao
				.viewahExcelReportRecode(report);
		if (record == null) {
			return new HashMap<String, Object>();
		}
		String path = record.getFileUrl();
		AutoGenerateParameter parameter = new AutoGenerateParameter();
		parameter.setSheetIndex(sheetType.getSheetNum());
		parameter.setStartRow(sheetType.getRow());
		parameter.setStartCol(sheetType.getCol());
		parameter.setDataStartRow(BaseSheetSumGenerate.DATA_START_ROW_INDEX);
		Map<String, Object> map = AutoGenerateUtils.autoGenerate(path,
				parameter);
		return map;
	}
}