package com.cabletech.business.assess.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.business.assess.service.AssessAppraiseService;
import com.cabletech.business.assess.service.AssessYearAppraiseWorkflowService;
import com.cabletech.business.workflow.common.model.CommonWorkflowResult;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;

/**
 * 考核评分确认管理
 * 
 * @author 杨隽 2012-08-06 创建
 */
@Namespace("/assess")
@Results({
		@Result(name = "input", location = "/assess/yearappraise/assess_year_appraise_confirm.jsp"),
		@Result(name = "list", location = "/assess/yearappraise/assess_year_appraise_confirm_list.jsp") })
@Action("/assessYearAppraiseConfirmAction")
public class AssessYearAppraiseConfirmAction extends
		BaseAction<AssessExaminationResult, String> {
	// 流程处理历史key
	public static final String PROCESS_HISTORY_MAP = "PROCESS_HISTORY_MAP";
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 考核/检查结果业务服务
	 */
	@Resource(name = "assessAppraiseServiceImpl")
	private AssessAppraiseService assessAppraiseService;
	@Resource(name = "assessYearAppraiseWorkflowService")
	private AssessYearAppraiseWorkflowService workflowService;
	/**
	 * 考核/检查结果表单数据
	 */
	private AssessExaminationResult assessExaminationResult = new AssessExaminationResult();

	/**
	 * 进入考核评分确认页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String input() {
		assessExaminationResult = assessAppraiseService.view(super
				.getParameter("id"));
		super.getRequest().setAttribute("tableType",
				AssessExaminationResult.YEAR_ASSESS_);
		super.getRequest().setAttribute("id", super.getParameter("id"));
		super.getRequest().setAttribute("taskId", super.getParameter("taskId"));
		return INPUT;
	}

	/**
	 * 确认考核评分
	 * 
	 * @return String
	 */
	public String save() {
		UserInfo user = super.getUser();
		assessAppraiseService.doWorkflow(assessExaminationResult, user);
		String url = "/assess/assessMonthAppraiseWaitHandledAction!list.action";
		if (AssessExaminationResult.IS_SUBMITED.equals(assessExaminationResult
				.getApply())) {
			url = "/assess/assessAppealFormAction!input.action?resultId="
					+ assessExaminationResult.getId() + "&cause="
					+ assessExaminationResult.getApproveRemark();
		}
		super.addMessage("年度考核评分确认成功！", url, SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 进入流程历史记录列表页面
	 * 
	 * @return
	 */
	public String list() {
		String id = super.getRequest().getParameter("id");
		Map<String, List<CommonWorkflowResult>> map = workflowService
				.getProcessHistoryList(id);
		super.getRequest().setAttribute(PROCESS_HISTORY_MAP, map);
		return LIST;
	}

	@Override
	public AssessExaminationResult getModel() {
		return assessExaminationResult;
	}

	@Override
	protected void prepareViewModel() {
	}

	@Override
	protected void prepareSaveModel() {
	}

	public AssessExaminationResult getAssessExaminationResult() {
		return assessExaminationResult;
	}

	public void setAssessExaminationResult(
			AssessExaminationResult assessExaminationResult) {
		this.assessExaminationResult = assessExaminationResult;
	}
}
