package com.cabletech.business.workflow.workorder.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.service.WorkOrderCancelService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 
 * 取消任务ACTION
 * 
 * @author 汪杰 2011-12-30 创建
 * @author 杨隽 2012-02-07 修改list()方法的实现并提取公共部分
 * @author 杨隽 2012-02-07 去除reload指向
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "list", location = "/workflow/workorder/workorder_waitcanceled_list.jsp"),
		@Result(name = "canceledlist", location = "/workflow/workorder/workorder_canceled_list.jsp") })
@Action("/workOrderCancelAction")
public class WorkOrderCancelAction extends
		WorkOrderBaseAction<WorkOrder, String> {
	// 已取消工单列表跳转页面
	private static final String CANCELED_LIST = "canceledlist";
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 通用工单表单数据
	private WorkOrder workOrder = new WorkOrder();
	@Resource(name = "workOrderCancelServiceImpl")
	private WorkOrderCancelService workOrderCancelService;

	/**
	 * 
	 * 待取消任务列表
	 * 
	 * @author 汪杰2011-12-30 创建
	 * @return String
	 */
	public String waitCanceledList() {
		super.getRequest().setAttribute("taskStates", super.getTaskStateMap());
		return LIST;
	}

	/**
	 * 待取消任务数据
	 */
	@SuppressWarnings({ "rawtypes" })
	public void waitCanceledListdata() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(workOrder, userInfo);
		Page page = workOrderCancelService.getWaitCanceledList(workOrder);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 
	 * 已取消任务列表
	 * 
	 * @author 汪杰2011-12-30 创建
	 * @return String
	 */
	public String canceledList() {
		super.getRequest().setAttribute("taskStates", super.getTaskStateMap());
		return CANCELED_LIST;
	}

	/**
	 * 已取消工单信息数据
	 */
	@SuppressWarnings({ "rawtypes" })
	public void canceledListdata() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(workOrder, userInfo);
		Page page = workOrderCancelService.getCanceledList(workOrder);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 
	 * 取消任务
	 * 
	 * @author 汪杰2012-01-06 创建
	 * @return String
	 */
	public String cancel() {
		String workOrderId = super.getRequest().getParameter("workOrderId");
		workOrderCancelService.cancelTask(workOrderId);
		String url = super.getUrl(WAIT_CANCELED_PAGE_URL);
		super.addMessage("工单取消成功!", url, SysConstant.SUCCESS);
		return SUCCESS;
	}

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