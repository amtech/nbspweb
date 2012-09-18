package com.cabletech.business.workflow.fault.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.business.workflow.fault.model.FaultDispatch;
import com.cabletech.business.workflow.fault.model.FaultReply;
import com.cabletech.business.workflow.fault.service.FaultAlertService;
import com.cabletech.business.workflow.fault.service.FaultDispatchService;
import com.cabletech.business.workflow.fault.service.FaultReplyService;
import com.cabletech.common.base.SysConstant;

/**
 * 故障回单处理Action
 * 
 * @author 杨隽 2011-10-26 创建
 * @author 杨隽 2011-10-27 修改“故障回单填写页面跳转”方法和“故障回单操作”方法
 * @author 杨隽 2012-07-18 添加系统定义的“是否选择”Map
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/fault/fault_reply_input.jsp"),
		@Result(name = "show", location = "/workflow/fault/fault_reply_show.jsp") })
@Action("/faultReplyAction")
public class FaultReplyAction extends FaultBaseAction<FaultReply, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 故障回单实体
	private FaultReply faultReply;
	// 故障告警单业务处理服务
	@Resource(name = "faultAlertServiceImpl")
	private FaultAlertService faultAlertManager;
	// 故障派单业务处理服务
	@Resource(name = "faultDispatchServiceImpl")
	private FaultDispatchService faultDispatchManager;
	// 故障派单回复业务处理服务
	@Resource(name = "faultReplyServiceImpl")
	private FaultReplyService faultReplyManager;

	/**
	 * 故障回单填写页面
	 * 
	 * @return String
	 */
	public String input() {
		FaultDispatch faultDispatch = faultDispatchManager
				.viewFaultDispatch(super.parameter.getId());
		FaultAlert faultAlert = faultAlertManager.viewFaultAlert(faultDispatch
				.getAlarmId());
		// 获取故障告警单的资源名称
		String resourceName = super.faultResourceManager.viewResourceInfo(
				faultAlert.getStationId()).getResourceName();
		faultAlert.setStationName(resourceName);
		String workflowTaskId = faultReply.getWorkflowTaskId();
		faultReply = faultReplyManager.getFaultReply(faultDispatch.getId());
		faultReply.setWorkflowTaskId(workflowTaskId);
		Map<String, String> whetherMap = SysConstant.getWhetherMap();
		super.getRequest().setAttribute("whetherMap", whetherMap);
		super.getRequest().setAttribute("fault_alert", faultAlert);
		super.getRequest().setAttribute("fault_dispatch", faultDispatch);
		return INPUT;
	}

	/**
	 * 故障回单操作
	 * 
	 * @return String
	 */
	public String reply() {
		UserInfo userInfo = super.getUser();
		FaultDispatch faultDispatch = faultDispatchManager
				.viewFaultDispatch(faultReply.getTaskId());
		FaultAlert faultAlert = faultAlertManager.viewFaultAlert(faultDispatch
				.getAlarmId());
		faultReplyManager.reply(faultReply, userInfo);
		faultReplyManager.doEomsReply(faultReply, faultAlert);
		super.addMessage("提示：故障回单成功！", WAIT_HANDLED_PAGE_URL,
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	@Override
	public FaultReply getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public FaultReply getFaultReply() {
		return faultReply;
	}

	public void setFaultReply(FaultReply faultReply) {
		this.faultReply = faultReply;
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
