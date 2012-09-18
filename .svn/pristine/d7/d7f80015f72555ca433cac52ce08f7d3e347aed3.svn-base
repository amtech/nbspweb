package com.cabletech.business.wplan.plan.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.common.model.CommonWorkflowResult;
import com.cabletech.business.wplan.plan.model.PatrolApprove;
import com.cabletech.business.wplan.plan.model.PatrolTemplate;
import com.cabletech.business.wplan.plan.service.PatrolApproveService;
import com.cabletech.business.wplan.plan.service.PatrolTemplateService;
import com.cabletech.business.wplan.plan.service.PatrolWorkflowService;
import com.cabletech.business.wplan.plan.service.PatrolinfoService;
import com.cabletech.common.base.SysConstant;

/**
 * 巡检计划审批
 * 
 * @author zhaobi
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 */
@Namespace("/wplan")
@Results({
		@Result(name = "input", location = "/wplan/plan/patrolinfo_audit_input.jsp"),
		@Result(name = "show_audit_history_list", location = "/wplan/plan/patrolinfo_audithistory_list.jsp") })
@Action("/patrolinfoApproveAction")
public class PatrolinfoApproveAction extends
		PatrolinfoBaseAction<PatrolApprove, String> {

	/**
	 * 审批历史列表跳转页面名称
	 */
	private static final String SHOW_AUDIT_HISTORY_LIST = "show_audit_history_list";
	/**
	 * 巡检计划审批信息
	 */
	private PatrolApprove patrolapprove = new PatrolApprove();

	/**
	 * 巡检计划信息服务
	 */
	@Resource(name = "patrolinfoServiceImpl")
	private PatrolinfoService patrolinfoService;

	/**
	 * 巡检计划审批服务
	 */
	@Resource(name = "patrolApproveServiceImpl")
	private PatrolApproveService patrolApproveService;
	/**
	 * 巡检工作流服务
	 */
	@Resource(name = "patrolWorkflowService")
	private PatrolWorkflowService patrolWorkflowService;
	/**
	 * 计划模板服务
	 */
	@Resource(name = "patrolTemplateServiceImpl")
	private PatrolTemplateService patrolTemplateService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cabletech.common.base.BaseAction#input()
	 */
	@Override
	public String input() {
		try {
			// 计划ID
			String planid = this.getRequest().getParameter("planid");
			// 专业类型
			String businessType = this.getRequest().getParameter("type");
			String taskid = this.getRequest().getParameter("taskid");
			UserInfo user = super.getUser();
			// 编辑
			if (StringUtils.isNotBlank(planid)) {
				Map<String, Object> patrolinfo = patrolinfoService.view(planid);
				// 增加taskID
				patrolinfo.put("TASKID", taskid);
				PatrolTemplate template = patrolTemplateService.get(planid);

				if (template != null) {
					patrolinfo.put("TEMPLATEID", template.getTemplateid());
				}
				this.getRequest().setAttribute("patrolinfoMap", patrolinfo);
				businessType = patrolinfo.get("BUSINESS_TYPE").toString();

			}
			// 设置界面Tag
			this.setViewTag(businessType, user.getRegionId());
			return INPUT;
		} catch (Exception e) {
			logger.error("执行计划审批页面出错:", e);
			return ERROR;
		}
	}

	/**
	 * 故障回单审核操作
	 * 
	 * @return
	 */
	public String audit() {
		try {
			UserInfo userInfo = this.getUser();
			// 专业类型
			// 计划ID
			String business_type = this.getRequest().getParameter("type");
			patrolApproveService.audit(patrolapprove, userInfo);
			super.addMessage("提示：计划审核成功！", WAIT_HANDLED_PAGE_URL
					+ business_type, SysConstant.SUCCESS);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("执行计划审批页面出错:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 故障回单审核历史信息查看操作
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String showAuditHistoryList() {
		try {
			String planid = this.getRequest().getParameter("planid");
			Map<String, List<CommonWorkflowResult>> map = patrolWorkflowService
					.getProcessHistoryList(planid);
			super.sessionManager.put("PATROLINFO_AUDIT_HISTORY_MAP", map);
			return SHOW_AUDIT_HISTORY_LIST;
		} catch (Exception e) {
			logger.error("执行计划审批历史页面出错:" + e.getMessage());
			return ERROR;
		}
	}

	@Override
	public PatrolApprove getModel() {
		return patrolapprove;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		if (patrolapprove == null) {
			patrolapprove = new PatrolApprove();
		}

	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub

	}

}
