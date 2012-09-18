package com.cabletech.business.workflow.fault.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.common.model.CommonWorkflowResult;
import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.business.workflow.fault.model.FaultAudit;
import com.cabletech.business.workflow.fault.model.FaultDispatch;
import com.cabletech.business.workflow.fault.model.FaultReply;
import com.cabletech.business.workflow.fault.service.FaultAlertService;
import com.cabletech.business.workflow.fault.service.FaultAuditService;
import com.cabletech.business.workflow.fault.service.FaultDispatchService;
import com.cabletech.business.workflow.fault.service.FaultReplyService;
import com.cabletech.business.workflow.fault.service.FaultWorkflowService;
import com.cabletech.common.base.SysConstant;

/**
 * 故障回单审核处理Action
 * 
 * @author 杨隽 2011-10-26 创建
 * @author 杨隽 2011-10-27 修改“故障回单审核页面跳转”方法和“故障回单审核操作”方法
 * @author 杨隽 2011-10-31 添加“故障回单审核历史信息查看操作”方法
 * @author 杨隽 2012-02-15 修改showAuditHistoryList()方法
 * @author 杨隽 2012-07-18 添加系统定义的“是否选择”Map
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/fault/fault_audit_input.jsp"),
		@Result(name = "show_audit_history_list", location = "/workflow/fault/fault_show_audit_history_list.jsp") })
@Action("/faultAuditAction")
public class FaultAuditAction extends FaultBaseAction<FaultAudit, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 故障派单回复验证历史列表跳转页面名称
	private static final String SHOW_AUDIT_HISTORY_LIST = "show_audit_history_list";
	// 流程处理历史key
	public static final String PROCESS_HISTORY_MAP = "FAULT_AUDIT_HISTORY_MAP";
	// 故障审核结果实体
	private FaultAudit faultAudit;
	// 故障告警单业务处理服务
	@Resource(name = "faultAlertServiceImpl")
	private FaultAlertService faultAlertManager;
	// 故障派单业务处理服务
	@Resource(name = "faultDispatchServiceImpl")
	private FaultDispatchService faultDispatchManager;
	// 故障派单回复业务处理服务
	@Resource(name = "faultReplyServiceImpl")
	private FaultReplyService faultReplyManager;
	// 故障派单回复验证业务处理服务
	@Resource(name = "faultAuditServiceImpl")
	private FaultAuditService faultAuditManager;
	// 故障派单工作流业务处理服务
	@Resource(name = "faultWorkflowService")
	private FaultWorkflowService faultWorkflowService;

	@Override
	public String input() throws Exception {
		FaultDispatch faultDispatch = faultDispatchManager
				.viewFaultDispatch(super.parameter.getId());
		FaultAlert faultAlert = faultAlertManager.viewFaultAlert(faultDispatch
				.getAlarmId());
		FaultReply faultReply = faultReplyManager.getFaultReply(super.parameter
				.getId());
		// 获取故障告警单的资源名称
		String resourceName = super.faultResourceManager.viewResourceInfo(
				faultAlert.getStationId()).getResourceName();
		faultAlert.setStationName(resourceName);
		Map<String, String> whetherMap = SysConstant.getWhetherMap();
		super.getRequest().setAttribute("whetherMap", whetherMap);
		super.getRequest().setAttribute("fault_alert", faultAlert);
		super.getRequest().setAttribute("fault_dispatch", faultDispatch);
		super.getRequest().setAttribute("fault_reply", faultReply);
		return INPUT;
	}

	/**
	 * 故障回单审核操作
	 * 
	 * @return String
	 */
	public String audit() {
		UserInfo userInfo = super.getUser();
		faultAuditManager.audit(faultAudit, userInfo);
		super.addMessage("提示：故障回单审核成功！", WAIT_HANDLED_PAGE_URL,
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 故障回单审核历史信息查看操作
	 * 
	 * @return String
	 */
	public String showAuditHistoryList() {
		Map<String, List<CommonWorkflowResult>> map = faultWorkflowService
				.getProcessHistoryList(super.parameter.getId());
		super.getRequest().setAttribute(PROCESS_HISTORY_MAP, map);
		return SHOW_AUDIT_HISTORY_LIST;
	}

	@Override
	public FaultAudit getModel() {
		return null;
	}

	public FaultAudit getFaultAudit() {
		return faultAudit;
	}

	public void setFaultAudit(FaultAudit faultAudit) {
		this.faultAudit = faultAudit;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
