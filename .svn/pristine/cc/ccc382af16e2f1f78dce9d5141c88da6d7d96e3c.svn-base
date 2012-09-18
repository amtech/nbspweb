package com.cabletech.business.assess.action;

import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.excelreport.exports.ExcelExportUtils;
import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.business.assess.service.AssessScoreQueryService;
import com.cabletech.business.assess.service.impl.AssessScoreQueryServiceImpl;
import com.cabletech.common.base.BaseAction;

/**
 * 月度考核成绩生成管理
 * 
 * @author 杨隽 2012-08-08 创建
 */
@Namespace("/assess")
@Results({
		@Result(name = "query", location = "/assess/monthappraise/assess_month_score_query.jsp"),
		@Result(name = "list", location = "/assess/monthappraise/assess_month_score_list.jsp") })
@Action("/assessMonthScoreQueryAction")
public class AssessMonthScoreQueryAction extends
		BaseAction<AssessExaminationResult, String> {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 生成成绩查询页面
	 */
	private static final String QUERY = "query";
	/**
	 * 月度考核成绩生成业务服务
	 */
	@Resource(name = "assessScoreQueryServiceImpl")
	private AssessScoreQueryService assessScoreQueryService;

	/**
	 * 进入考核成绩生成页面
	 * 
	 * @return String
	 */
	public String query() {
		return QUERY;
	}

	/**
	 * 考核成绩生成
	 */
	public String list() {
		UserInfo user = super.getUser();
		String queryType = super.getParameter("querytype");
		String yearMonth = super.getParameter("yearmonth");
		Map<String, Object> tableMap = assessScoreQueryService.queryScoreList(
				yearMonth, queryType, user);
		super.getRequest().getSession().setAttribute("tableMap", tableMap);
		return LIST;
	}

	/**
	 * 考核成绩导出
	 */
	@SuppressWarnings("unchecked")
	public void export() {
		Map<String, Object> tableMap = (Map<String, Object>) super.getRequest()
				.getSession().getAttribute("tableMap");
		String title = super.getParameter("yearmonth");
		if (AssessScoreQueryServiceImpl.BUSINESS_TYPE_QUERY.equals(super
				.getParameter("querytype"))) {
			title += "专业";
		}
		if (AssessScoreQueryServiceImpl.SUM_QUERY.equals(super
				.getParameter("querytype"))) {
			title += "综合";
		}
		title += "月度考核成绩";
		String fileName = title + ".xls";
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
