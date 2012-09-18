package com.cabletech.business.workflow.workorder.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.service.WorkOrderHandledService;
import com.cabletech.common.util.Page;

/**
 * 
 * 已办工作ACTION
 * 
 * @author 汪杰 2011-12-30 创建
 * @author 杨隽 2012-02-07 修改list()方法的实现并提取公共部分
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/workorder/workorder_handled_list.jsp") })
@Action("/workOrderHandledAction")
public class WorkOrderHandledAction extends
		WorkOrderBaseAction<WorkOrder, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 通用工单表单数据
	private WorkOrder workOrder = new WorkOrder();
	// 已办任务业务处理
	@Resource(name = "workOrderHandledServiceImpl")
	private WorkOrderHandledService workOrderHandledService;

	/**
	 * 进入通用工单已办列表页面
	 * 
	 * @return
	 */
	public String list() {
		super.getRequest().setAttribute("taskStates", super.getTaskStateMap());
		return LIST;
	}

	/**
	 * 通用工单已办列表
	 */
	@SuppressWarnings("rawtypes")
	public void listdata() {
		UserInfo userInfo = super.getUser();
		this.preSetListQuery(workOrder, userInfo);
		Page page = workOrderHandledService.getHandledList(workOrder);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	@Override
	public WorkOrder getModel() {
		return workOrder;
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}
}