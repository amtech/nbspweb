package com.cabletech.business.workflow.wmaintain.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.common.base.SysConstant;

/**
 * 制定维修作业计划Action
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-16 添加注释部分
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/wmaintain/wmaintainplan_input.jsp"),
		@Result(name = "view", location = "/workflow/wmaintain/wmaintainplan_view.jsp") })
@Action("/wmaintainCreatePlanAction")
public class WMaintainCreatePlanAction extends
		WMaintainBaseAction<WMaintainPlan, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 维修作业计划表单数据
	private WMaintainPlan plan;

	public WMaintainPlan getPlan() {
		return plan;
	}

	public void setPlan(WMaintainPlan plan) {
		this.plan = plan;
	}

	/**
	 * 进入维修作业计划制定页面
	 * 
	 * @return String
	 */
	public String input() {
		super.preSetInput();
		String id = super.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			plan = super.wMaintainCreatePlanService.view(id);
			List<Map<String, Object>> siteList = plan.getSiteList();
			String siteDataJson = super.convertObjToJsonStr(siteList);
			super.getRequest().setAttribute("siteDataJson", siteDataJson);
			List<Map<String, Object>> resultList = plan.getResultList();
			String resultDataJson = super.convertObjToJsonStr(resultList);
			super.getRequest().setAttribute("resultDataJson", resultDataJson);
		} else {
			plan = new WMaintainPlan();
		}
		return INPUT;
	}

	/**
	 * 执行维修作业计划制定
	 * 
	 * @return String
	 */
	public String save() {
		String id = plan.getId();
		String taskId = plan.getWorkflowTaskId();
		if (StringUtils.isBlank(plan.getId())) {
			plan.setId(null);
		}
		UserInfo userInfo = super.getUser();
		super.wMaintainCreatePlanService.save(plan, userInfo);
		String url = "";
		if (StringUtils.isNotBlank(taskId)) {
			url = WAIT_HANDLED_PAGE_URL;
		} else if (StringUtils.isBlank(id)) {
			url = NEW_INPUT_PAGE_URL;
		} else {
			url = DRAFT_PAGE_URL;
		}
		super.addMessage("制定维修作业计划成功！", url, SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 删除维修作业计划制定信息
	 * 
	 * @return String
	 */
	public String delete() {
		String[] id = super.getRequest().getParameterValues("id");
		String state = super.getParameter("state");
		super.wMaintainCreatePlanService.deletePlan(id);
		String url = "";
		if (WMaintainPlan.WMAINTAIN_PLAN_NOTSUBMITED_STATE.equals(state)) {
			url = DRAFT_PAGE_URL;
		} else {
			url = WAIT_DELETED_PAGE_URL;
		}
		url += businessType;
		super.addMessage("删除维修作业计划成功！", url, SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 查看维修作业计划制定信息
	 * 
	 * @return String
	 */
	public String view() {
		String id = super.getParameter("id");
		String showReturn = super.getParameter("showReturn");
		if (StringUtils.isBlank(showReturn)) {
			showReturn = "1";
		}
		WMaintainPlan plan = super.wMaintainCreatePlanService.view(id);
		super.getRequest().setAttribute("plan", plan);
		super.getRequest().setAttribute("showReturn", showReturn);
		return VIEW;
	}

	@Override
	public WMaintainPlan getModel() {
		// TODO Auto-generated method stub
		return plan;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub
	}
}
