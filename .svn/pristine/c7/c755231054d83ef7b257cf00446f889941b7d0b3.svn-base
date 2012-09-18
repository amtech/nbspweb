package com.cabletech.business.assess.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.cabletech.business.assess.model.AssessAppealForm;
import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.business.assess.service.AssessAppealFormService;
import com.cabletech.business.assess.service.AssessAppraiseService;
import com.cabletech.business.assess.service.AssessTemplateContentService;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;
/**
 * 考核管理-申诉记录-待办工作
 * 
 * @author wj 2012-08-02 创建
 * 
 */
@Namespace("/assess")
@Results({
		@Result(name = "list", location = "/assess/appealform/assess_appealform_waithandled_list.jsp"),
		@Result(name = "check", location = "/assess/appealform/assess_appealform_waithandled_check.jsp"),
		@Result(name = "approve", location = "/assess/appealform/assess_appealform_waithandled_approve.jsp"),
		@Result(name = "confirm", location = "/assess/appealform/assess_appealform_waithandled_confirm.jsp"),
		@Result(name = "inputsecond", location = "/assess/examination/assess_examination_inputsecond.jsp"),
		@Result(name = "detail", location = "/assess/examination/assess_examination_details.jsp")
})
@Action("/assessAppealFormWaitHandledAction")
@SuppressWarnings("all")
public class AssessAppealFormWaitHandledAction extends BaseAction<AssessAppealForm, String> {

	private AssessAppealForm appealForm;

	@Resource(name = "assessAppealFormServiceImpl")
	private AssessAppealFormService assessAppealFormService;
	
	/**
	 * 考核/检查结果业务服务
	 */
	@Resource(name = "assessAppraiseServiceImpl")
	private AssessAppraiseService assessAppraiseService;
	/**
	 * 考核模版服务
	 */
	@Resource(name = "assessTemplateContentServiceImpl")
	private AssessTemplateContentService assessTemplateContentService;

	/**
	 * 列表 跳转
	 */
	public String list() {
		return LIST;
	}
	
	/**
	 * 列表 数据
	 */
	public void listData() {
		Page<Map<String, Object>> page = this.initPage();
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("userId", getUser().getPersonId());
		page = assessAppealFormService.queryWaitHandledList(parameters, page);
		setExcelParameter(page);
		convertObjToJson(page);
	}
	
	/**
	 * 办理 --审批
	 */
	public String doTask() {
		Map<String, Object> appealMap = assessAppealFormService.queryAppealForm(getRequest().getParameter("id"));
		List<Map<String,Object>> adjusstmentList = assessAppealFormService.queryAdjusstmentList(getRequest().getParameter("id"));
		List<ProMockPo> hisList = assessAppealFormService.queryApproveHisList(getRequest().getParameter("id"));
		boolean isHasHis = false;
		if(hisList!=null&&hisList.size()>0)isHasHis=true;
		appealMap.put("TASKID", getRequest().getParameter("taskId"));
		appealMap.put("STEP", getRequest().getParameter("step"));
		this.getRequest().setAttribute("appealMap", appealMap);
		this.getRequest().setAttribute("adjusstmentList", adjusstmentList);
		this.getRequest().setAttribute("hisList", hisList);
		this.getRequest().setAttribute("isHasHis", isHasHis);
		if(AssessAppealForm.APPROVE_CHECK.equals(getRequest().getParameter("step"))){//复核
			AssessExaminationResult assessExaminationResult = assessAppraiseService.view((String)appealMap.get("EXAM_RESULT_ID"));
			List<Map<String,Object>> newlist=assessExaminationResult.getDetailList();
			int maxItemCount=assessExaminationResult.getLevel();
			List<Map<String, Object>> list = assessTemplateContentService.processList(maxItemCount,newlist);
			this.getRequest().setAttribute("maxitemcount", maxItemCount);
			this.getRequest().setAttribute("templatecontent", list);
			return "check";
		}
		if(AssessAppealForm.APPROVE_CONFIRM.equals(getRequest().getParameter("step"))){//确认
			return "confirm";
		}
		return "approve";
	}	
	
	/**
	 * 保存 --审批
	 */
	public String saveApprove() {
		assessAppealFormService.doTask(this.getAppealForm(), this.getUser());
		super.addMessage("保存成功","/assess/assessAppealFormWaitHandledAction!list.action", SysConstant.SUCCESS);
		return SUCCESS;
	}
	

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	public AssessAppealForm getModel() {
		return null;
	}
	
	/**
	 * @return the appealForm
	 */
	public AssessAppealForm getAppealForm() {
		return appealForm;
	}

	/**
	 * @param appealForm the appealForm to set
	 */
	public void setAppealForm(AssessAppealForm appealForm) {
		this.appealForm = appealForm;
	}
}