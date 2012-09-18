package com.cabletech.business.ah.excelreport.service;

import java.io.File;
import java.util.Map;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.excelreport.model.AhExcelReportRecode;
import com.cabletech.common.util.Page;

/**
 * 
 * 地市每个月上传的excel报表service
 * @author wj
 *
 */
public interface AhExcelReportRecodeService {

	/**
	 * 检查上传文件
	 * @param userInfo 用户
	 * @param reportFile 报表文件
	 * @param reportFileFileName 文件名
	 * @param reportDate 日期
	 * @return
	 */
	public String[] checkUploadFile(UserInfo userInfo,File reportFile,String reportFileFileName,String reportDate);
	
	/**
	 * 保存报表记录
	 * @param entity 实体
	 */
	public void saveReportRecode(AhExcelReportRecode entity);
	

	/**
	 * 检索报表记录列表
	 * @param parameters 查询参数
	 * @param page 分页
	 * @return
	 */
	public Page searchReportRecodes(Map<String, Object> parameters,Page page);
	
	/**
	 * 检索报表记录
	 * @param parameters 查询参数
	 *
	 */
	public Map<String, Object> searchReportRecode(Map<String, Object> parameters);
	
	/**
	 * 根据查询条件获取上传的月报表数据信息
	 * 
	 * @param report
	 *            AhExcelReportRecode 查询条件
	 * @return AhExcelReportRecode 上传的月报表数据信息
	 */
	public AhExcelReportRecode viewahExcelReportRecode(AhExcelReportRecode report);
}