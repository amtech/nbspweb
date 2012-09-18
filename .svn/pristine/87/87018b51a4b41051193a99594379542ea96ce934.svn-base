package com.cabletech.business.workflow.workorder.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.workorder.model.WorkOrderSignFor;
import com.cabletech.business.workflow.workorder.service.WorkOrderSignForService;
import com.cabletech.common.base.SysConstant;

/**
 * 通用工单签收Action
 * 
 * @author 杨隽 2011-12-29 创建
 * @author 杨隽 2012-01-06 实现签收功能
 * @author 杨隽 2012-01-11 修改页面跳转提示功能
 * @author 杨隽 2012-01-31 进行input方法的代码重构
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/workorder/workorder_sign_for.jsp"),
		@Result(name = "list", location = "/workflow/workorder/workorder_sign_for_list.jsp") })
@Action("/workorderSignForAction")
public class WorkOrderSignForAction extends
		WorkOrderBaseAction<WorkOrderSignFor, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 通用工单签收实体
	private WorkOrderSignFor workorderSignFor;
	// 通用工单签收业务处理接口
	@Resource(name = "workOrderSignForServiceImpl")
	private WorkOrderSignForService workOrderSignForService;

	/**
	 * 进入通用工单签收页面
	 */
	public String input() {
		super.transferParameterToPage();
		super.setPageNoToRequest();
		return INPUT;
	}

	/**
	 * 执行通用工单签收
	 * 
	 * @return
	 */
	public String save() {
		UserInfo user = super.getUser();
		workorderSignFor.setSignForUserId(user.getPersonId());
		workOrderSignForService.save(workorderSignFor);
		if (SysConstant.PASS_WORKFLOW_TRANSTION.equals(workorderSignFor
				.getSignForResult())) {
			super.addMessage("工单签收成功!", super.getUrl(), SysConstant.SUCCESS);
		}
		if (SysConstant.REFUSE_WORKFLOW_TRANSITION.equals(workorderSignFor
				.getSignForResult())) {
			super.addMessage("工单拒签成功!", super.getUrl(), SysConstant.SUCCESS);
		}
		return SUCCESS;
	}

	/**
	 * 进入通用工单签收记录列表页面
	 * 
	 * @return
	 */
	public String list() {
		super.setProcessHistoryMap();
		return LIST;
	}

	@Override
	public WorkOrderSignFor getModel() {
		return workorderSignFor;
	}

	public WorkOrderSignFor getWorkorderSignFor() {
		return workorderSignFor;
	}

	public void setWorkorderSignFor(WorkOrderSignFor workorderSignFor) {
		this.workorderSignFor = workorderSignFor;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
