package com.cabletech.business.workflow.workorder.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.workorder.model.WorkOrderReplyCheck;
import com.cabletech.business.workflow.workorder.service.WorkOrderReplyCheckService;
import com.cabletech.common.base.SysConstant;

/**
 * 通用工单回复验证Action
 * 
 * @author 杨隽 2011-12-29 创建
 * @author 杨隽 2012-01-06 实现回复验证功能
 * @author 杨隽 2012-01-11 修改页面跳转提示功能
 * @author 杨隽 2012-01-31 进行input方法的代码重构
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/workorder/workorder_reply_check.jsp"),
		@Result(name = "list", location = "/workflow/workorder/workorder_reply_check_list.jsp") })
@Action("/workorderReplyCheckAction")
public class WorkOrderReplyCheckAction extends
		WorkOrderBaseAction<WorkOrderReplyCheck, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 通用工单回复验证实体
	private WorkOrderReplyCheck workOrderReplyCheck;
	// 通用工单验证业务处理接口
	@Resource(name = "workOrderReplyCheckServiceImpl")
	private WorkOrderReplyCheckService workOrderReplyCheckService;

	/**
	 * 进入通用工单回复验证页面
	 */
	public String input() {
		super.transferParameterToPage();
		super.setPageNoToRequest();
		return INPUT;
	}

	/**
	 * 执行通用工单回复验证
	 * 
	 * @return
	 */
	public String save() {
		UserInfo user = super.getUser();
		workOrderReplyCheck.setCheckUserId(user.getPersonId());
		workOrderReplyCheckService.save(workOrderReplyCheck);
		super.addMessage("工单回单验证成功!", super.getUrl(), SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 进入通用工单回复验证记录列表页面
	 * 
	 * @return
	 */
	public String list() {
		super.setProcessHistoryMap();
		return LIST;
	}

	@Override
	public WorkOrderReplyCheck getModel() {
		return workOrderReplyCheck;
	}

	public WorkOrderReplyCheck getWorkOrderReplyCheck() {
		return workOrderReplyCheck;
	}

	public void setWorkOrderReplyCheck(WorkOrderReplyCheck workOrderReplyCheck) {
		this.workOrderReplyCheck = workOrderReplyCheck;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
