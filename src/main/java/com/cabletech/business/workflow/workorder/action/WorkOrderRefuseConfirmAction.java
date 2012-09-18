package com.cabletech.business.workflow.workorder.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.workorder.model.WorkOrderRefuseConfirm;
import com.cabletech.business.workflow.workorder.service.WorkOrderRefuseConfirmService;
import com.cabletech.common.base.SysConstant;

/**
 * 通用工单拒签确认Action
 * 
 * @author 杨隽 2011-12-29 创建
 * @author 杨隽 2012-01-06 实现拒签确认功能
 * @author 杨隽 2012-01-11 修改页面跳转提示功能
 * @author 杨隽 2012-01-31 进行input方法的代码重构
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/workorder/workorder_refuse_confirm.jsp"),
		@Result(name = "list", location = "/workflow/workorder/workorder_refuse_confirm_list.jsp") })
@Action("/workorderRefuseConfirmAction")
public class WorkOrderRefuseConfirmAction extends
		WorkOrderBaseAction<WorkOrderRefuseConfirm, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 通用工单拒签确认实体
	private WorkOrderRefuseConfirm workOrderRefuseConfirm;
	// 通用工单拒签确认业务处理接口
	@Resource(name = "workOrderRefuseConfirmServiceImpl")
	private WorkOrderRefuseConfirmService workOrderRefuseConfirmService;

	/**
	 * 进入通用工单拒签确认页面
	 */
	public String input() {
		super.transferParameterToPage();
		super.setPageNoToRequest();
		return INPUT;
	}

	/**
	 * 执行通用工单拒签确认
	 * 
	 * @return
	 */
	public String save() {
		UserInfo user = super.getUser();
		workOrderRefuseConfirm.setRefuseConfirmUserId(user.getPersonId());
		workOrderRefuseConfirmService.save(workOrderRefuseConfirm);
		super.addMessage("工单退单审核成功!", super.getUrl(), SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 进入通用工单拒签确认记录列表页面
	 * 
	 * @return
	 */
	public String list() {
		super.setProcessHistoryMap();
		return LIST;
	}

	@Override
	public WorkOrderRefuseConfirm getModel() {
		return workOrderRefuseConfirm;
	}

	public WorkOrderRefuseConfirm getWorkOrderRefuseConfirm() {
		return workOrderRefuseConfirm;
	}

	public void setWorkOrderRefuseConfirm(
			WorkOrderRefuseConfirm workOrderRefuseConfirm) {
		this.workOrderRefuseConfirm = workOrderRefuseConfirm;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
