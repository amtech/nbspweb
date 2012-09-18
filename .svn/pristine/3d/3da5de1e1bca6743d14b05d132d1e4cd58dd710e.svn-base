package com.cabletech.business.workflow.workorder.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.service.WorkOrderWaitDeletedService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 待删除工作ACTION
 * @author 汪杰 2011-12-30 创建
 * @author 杨隽 2012-02-07 修改list()方法的实现并提取公共部分
 * @author 杨隽 2012-02-07 去除reload指向
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/workorder/workorder_waitdeleted_list.jsp") })
@Action("/workOrderWaitDeletedAction")
public class WorkOrderWaitDeletedAction extends
		WorkOrderBaseAction<WorkOrder, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 通用工单表单数据
	private WorkOrder workOrder = new WorkOrder();
	@Resource(name = "workOrderWaitDeletedServiceImpl")
	private WorkOrderWaitDeletedService workOrderWaitDeletedService;

	/**
	 * 进入通用工单待删除列表页面
	 * 
	 * @return
	 */
	public String list() {
		super.getRequest().setAttribute("taskStates", super.getTaskStateMap());
		return LIST;
	}

	/**
	 * 待删除列表数据
	 */
	@SuppressWarnings("rawtypes")
	public void listdata() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(workOrder, userInfo);
		Page page = workOrderWaitDeletedService.getWaitDeletedList(workOrder);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 删除数据
	 * 
	 * @return String
	 */
	public String delete() {
		String workOrderId = this.getRequest().getParameter("workOrderId");
		String transferId = this.getRequest().getParameter("transferId");
		workOrderWaitDeletedService.deletedTransferAndWorkOrder(workOrderId,
				transferId);
		String url = super.getUrl(WAIT_DELETED_PAGE_URL);
		super.addMessage("工单删除成功!", url, SysConstant.SUCCESS);
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
