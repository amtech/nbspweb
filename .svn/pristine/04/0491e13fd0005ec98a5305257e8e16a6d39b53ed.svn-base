package com.cabletech.business.workflow.workorder.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.service.WorkOrderWaitHandledService;
import com.cabletech.common.util.Page;

/**
 * 待办工作ACTION
 * 
 * @author 汪杰 2011-12-30 创建
 * @author 杨隽 2012-02-07 修改list()方法的实现并提取公共部分
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/workorder/workorder_waithandled_list.jsp") })
@Action("/workOrderWaitHandledAction")
public class WorkOrderWaitHandledAction extends
		WorkOrderBaseAction<WorkOrder, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 通用工单表单数据
	private WorkOrder workOrder = new WorkOrder();
	// 通用工单待办任务业务处理
	@Resource(name = "workOrderWaitHandledServiceImpl")
	private WorkOrderWaitHandledService workOrderWaitHandledService;

	public WorkOrder getModel() {
		return workOrder;
	}

	/**
	 * 进入通用工单待办工作列表页面
	 * 
	 * @return
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 待办工作列表数据
	 */
	@SuppressWarnings("rawtypes")
	public void listdata() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(workOrder, userInfo);
		Page page = workOrderWaitHandledService.getWaitHandledList(workOrder);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}
}