package com.cabletech.business.ah.excelreport.action;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.business.ah.excelreport.exports.ExcelExportUtils;
import com.cabletech.business.ah.excelreport.model.AhExcelReportRecode;
import com.cabletech.business.ah.excelreport.service.AhExcelReportSheetTypeService;
import com.cabletech.business.ah.excelreport.service.AhExcelReportSheetViewService;
import com.cabletech.common.base.BaseAction;

/**
 * 
 * 查看地市每个月上传的excel报表action
 * 
 * @author 杨隽 2012-06-27 创建
 * @author 杨隽 2012-07-06 添加export()方法
 * 
 */
@Namespace("/ah")
@Results({
		@Result(name = "query", location = "/ah/excelreport/excel_report_view_query.jsp"),
		@Result(name = "list", location = "/ah/excelreport/excel_report_view_list.jsp") })
@Action("/ahExcelReportViewAction")
public class AhExcelReportViewAction extends
		BaseAction<AhExcelReportRecode, String> {
	/**
	 * 地市每个月上传的excel报表中sheet类型service
	 */
	@Resource(name = "ahExcelReportSheetTypeServiceImpl")
	private AhExcelReportSheetTypeService ahExcelReportSheetTypeService;
	/**
	 * 查看地市每个月上传的excel报表中sheet工作表service
	 */
	@Resource(name = "ahExcelReportSheetViewServiceImpl")
	private AhExcelReportSheetViewService ahExcelReportSheetViewService;
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 表单查询参数
	 */
	private AhExcelReportRecode report = new AhExcelReportRecode();

	/**
	 * 月表格查询表单
	 * 
	 * @return String
	 */
	public String query() {
		List<Map<String, Object>> sheetTypeList = ahExcelReportSheetTypeService
				.queryAllList();
		super.getRequest().setAttribute("sheetTypeList", sheetTypeList);
		return "query";
	}

	/**
	 * 月表格查询结果
	 * 
	 * @return String
	 */
	public String list() {
		Map<String, Object> tableMap = ahExcelReportSheetViewService
				.viewAhExcelReportSheet(report);
		String sheetName = ahExcelReportSheetTypeService
				.viewAhExcelReportSheet(report.getReportSheetType())
				.getSheetName();
		report.setSheetName(sheetName);
		super.getRequest().getSession().setAttribute("report", report);
		super.getRequest().getSession().setAttribute("tableMap", tableMap);
		return LIST;
	}

	/**
	 * 导出报表数据
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String export() {
		Map<String, Object> tableMap = (Map<String, Object>) super.getRequest()
				.getSession().getAttribute("tableMap");
		report = (AhExcelReportRecode) super.getRequest().getSession()
				.getAttribute("report");
		String title = ahExcelReportSheetTypeService.getSheetExportName(report);
		String fileName = title + ".xls";
		tableMap.put("title", title);
		Workbook wb = ExcelExportUtils.exportReport(tableMap,
				report.getSheetName());
		try {
			super.getResponse().reset();
			super.getResponse().setContentType(CONTENT_TYPE);
			super.getResponse().setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String(fileName.getBytes(), "iso-8859-1"));
			OutputStream out = super.getResponse().getOutputStream();
			wb.write(out);
		} catch (Exception ex) {
		}
		return null;
	}

	@Override
	public AhExcelReportRecode getModel() {
		return report;
	}

	public AhExcelReportRecode getReport() {
		return report;
	}

	public void setReport(AhExcelReportRecode report) {
		this.report = report;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}