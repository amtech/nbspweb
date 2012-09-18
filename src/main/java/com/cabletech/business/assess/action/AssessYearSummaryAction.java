package com.cabletech.business.assess.action;

import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.business.ah.excelreport.exports.ExcelExportUtils;
import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.business.assess.service.AssessYearSummaryService;
import com.cabletech.common.base.BaseAction;

/**
 * 年度考核汇总管理
 * 
 * @author 杨隽 2012-08-03 创建
 */
@Namespace("/assess")
@Results({
		@Result(name = "summary_query", location = "/assess/yearsummary/assess_year_summary_query.jsp"),
		@Result(name = "summary_list", location = "/assess/yearsummary/assess_year_summary_list.jsp"),
		@Result(name = "rank_query", location = "/assess/yearsummary/assess_year_rank_query.jsp"),
		@Result(name = "rank_list", location = "/assess/yearsummary/assess_year_rank_list.jsp") })
@Action("/assessYearSummaryAction")
public class AssessYearSummaryAction extends
		BaseAction<AssessExaminationResult, String> {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 汇总查询页面路径
	 */
	private static final String SUMMARY_QUERY = "summary_query";
	/**
	 * 汇总页面路径
	 */
	private static final String SUMMARY_LIST = "summary_list";
	/**
	 * 排名查询页面路径
	 */
	private static final String RANK_QUERY = "rank_query";
	/**
	 * 排名页面路径
	 */
	private static final String RANK_LIST = "rank_list";
	/**
	 * 年考核汇总业务服务
	 */
	@Resource(name = "assessYearSummaryServiceImpl")
	private AssessYearSummaryService assessYearSummaryService;

	/**
	 * 进入汇总列表查询页面
	 * 
	 * @return String
	 */
	public String summaryQuery() {
		return SUMMARY_QUERY;
	}

	/**
	 * 进入汇总列表页面
	 * 
	 * @return String
	 */
	public String summaryList() {
		String yearMonth = super.getParameter("yearmonth");
		Map<String, Object> dataMap = assessYearSummaryService
				.getSummaryScoreDataMap(yearMonth);
		super.getRequest().setAttribute("dataMap", dataMap);
		return SUMMARY_LIST;
	}

	/**
	 * 汇总列表数据导出
	 */
	public void summaryExport() {
		String title = super.getParameter("yearmonth");
		title += "年度考核汇总";
		String fileName = title + ".xls";
		String yearMonth = super.getParameter("yearmonth") + "-01-01";
		Map<String, Object> tableMap = assessYearSummaryService
				.getSummaryScoreExcelMap(yearMonth, "summary");
		tableMap.put("title", title);
		Workbook wb = ExcelExportUtils.exportReport(tableMap, title);
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
			logger.error("", ex);
		}
	}

	/**
	 * 进入排名列表查询页面
	 * 
	 * @return String
	 */
	public String rankQuery() {
		return RANK_QUERY;
	}

	/**
	 * 进入排名列表页面
	 * 
	 * @return String
	 */
	public String rankList() {
		String yearMonth = super.getParameter("yearmonth");
		Map<String, Object> dataMap = assessYearSummaryService
				.getSummaryScoreDataMap(yearMonth);
		super.getRequest().setAttribute("dataMap", dataMap);
		return RANK_LIST;
	}

	/**
	 * 排名列表数据导出
	 */
	public void rankExport() {
		String title = super.getParameter("yearmonth");
		title += "年度考核排名";
		String fileName = title + ".xls";
		String yearMonth = super.getParameter("yearmonth") + "-01-01";
		Map<String, Object> tableMap = assessYearSummaryService
				.getSummaryScoreExcelMap(yearMonth, "rank");
		tableMap.put("title", title);
		Workbook wb = ExcelExportUtils.exportReport(tableMap, title);
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
			logger.error("", ex);
		}
	}

	@Override
	public AssessExaminationResult getModel() {
		return null;
	}

	@Override
	protected void prepareViewModel() {
	}

	@Override
	protected void prepareSaveModel() {
	}
}
