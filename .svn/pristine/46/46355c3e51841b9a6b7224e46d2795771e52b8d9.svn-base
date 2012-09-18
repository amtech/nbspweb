package com.cabletech.business.ah.excelreport.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.ah.excelreport.model.AhExcelReportRecode;
import com.cabletech.business.ah.excelreport.model.AhExcelReportSheet;
import com.cabletech.business.ah.excelreport.service.AhExcelReportSheetSumService;
import com.cabletech.business.ah.excelreport.service.AhExcelReportSheetTypeService;
import com.cabletech.business.ah.excelreport.sum.BaseSheetSumGenerate;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 汇总地市每个月上传的excel报表中sheet类型service实现
 * 
 * @author 杨隽 2012-06-28 创建
 * 
 */
@SuppressWarnings("unchecked")
@Service
@Transactional
public class AhExcelReportSheetSumServiceImpl extends
		BaseServiceImpl<String, String> implements AhExcelReportSheetSumService {
	/**
	 * 汇总Spring注入实体名称前缀
	 */
	public static final String SUM_CLASS_INSTANCENAME_PREFIX = "sheetSumGenerate";
	/**
	 * 汇总类型前缀
	 */
	public static final String SUM_TYPE_PREFIX = "S";
	/**
	 * sheet类型前缀
	 */
	public static final String SHEET_SUM_TYPE_PREFIX = "ShS";
	/**
	 * 地市每个月上传的excel报表中sheet类型service
	 */
	@Resource(name = "ahExcelReportSheetTypeServiceImpl")
	private AhExcelReportSheetTypeService ahExcelReportSheetTypeService;
	/**
	 * 汇总Spring注入实体Map
	 */
	@Autowired
	private Map<String, BaseSheetSumGenerate> sheetSumGenerateMap;

	@Override
	protected BaseDao<String, String> getBaseDao() {
		return null;
	}

	@Override
	public Map<String, Object> sumAhExcelReportSheet(AhExcelReportRecode report) {
		AhExcelReportSheet sheetType = ahExcelReportSheetTypeService
				.viewAhExcelReportSheet(report.getReportSheetType());
		String sumType = SUM_TYPE_PREFIX + report.getSumType();
		String sheetSumType = SHEET_SUM_TYPE_PREFIX + sheetType.getFlg();
		String key = SUM_CLASS_INSTANCENAME_PREFIX + sumType + sheetSumType;
		BaseSheetSumGenerate sheetSumGenerate = sheetSumGenerateMap.get(key);
		try {
			Map<String, Object> map = sheetSumGenerate.getTableMap(report,
					sheetType);
			return map;
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return new HashMap<String, Object>();
	}
}