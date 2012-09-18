package com.cabletech.business.workflow.workorder.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.service.WorkOrderDispatchService;
import com.cabletech.business.workflow.workorder.service.WorkOrderTransferService;
import com.cabletech.business.workflow.workorder.service.WorkOrderWaitDeletedService;
import com.cabletech.common.base.SysConstant;

/**
 * 通用工单分发Action
 * 
 * @author 杨隽 2011-12-29 创建
 * @author 杨隽 2012-01-09 添加dispatchList方法
 * @author 杨隽 2012-01-11 修改页面跳转提示功能
 * @author 杨隽 2012-01-12 整理页面跳转提示功能
 * @author 杨隽 2012-05-04 更改使用公共的表单提交标识
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/workorder/workorder_input.jsp"),
		@Result(name = "transfer_input", location = "/workflow/workorder/workorder_transfer_input.jsp"),
		@Result(name = "view", location = "/workflow/workorder/workorder_view.jsp"),
		@Result(name = "view_all", location = "/workflow/workorder/workorder_view_all.jsp"),
		@Result(name = "dispatch_list", location = "/workflow/workorder/workorder_dispatch_list.jsp") })
@Action("/workorderDispatchAction")
public class WorkOrderDispatchAction extends
		WorkOrderBaseAction<WorkOrder, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 转派页面跳转标识
	private static final String TRANSFER_INPUT = "transfer_input";
	// 通用工单详细信息页面跳转标识
	private static final String VIEW_ALL = "view_all";
	// 派单信息列表页面跳转标识
	private static final String DISPATCH_LIST = "dispatch_list";
	// 通用工单表单数据
	private WorkOrder workOrder;
	// 通用工单业务处理接口
	@Resource(name = "workOrderDispatchServiceImpl")
	private WorkOrderDispatchService workOrderDispatchService;
	// 通用工单分发信息业务处理接口
	@Resource(name = "workOrderTransferServiceImpl")
	private WorkOrderTransferService workOrderTransferService;
	// 通用工单信息业务处理接口（待删除）
	@Resource(name = "workOrderWaitDeletedServiceImpl")
	private WorkOrderWaitDeletedService workOrderWaitDeletedService;

	@Override
	public String input() {
		String id = super.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			workOrder = workOrderDispatchService.view(id);
		}
		if (workOrder != null
				&& StringUtils.isNotBlank(workOrder.getParentId())) {
			super.getRequest().setAttribute("origin", "transfer");
		} else {
			super.getRequest().setAttribute("origin", "input");
		}
		super.setPageNoToRequest();
		return INPUT;
	}

	/**
	 * 转派工单输入页面
	 * 
	 * @return String
	 */
	public String transferInput() {
		String id = super.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			workOrder = workOrderDispatchService.view(id);
		}
		super.getRequest().setAttribute("origin", "transfer");
		super.setPageNoToRequest();
		return TRANSFER_INPUT;
	}

	/**
	 * 保存通用工单信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String save() {
		String id = workOrder.getId();
		UserInfo user = super.getUser();
		List<FileItem> list = (List<FileItem>) super.sessionManager
				.get("FILES");
		workOrder.setUser(user);
		workOrder.setFileList(list);
		workOrderDispatchService.save(workOrder);
		String message = getMessage();
		String url = getSavedUrl(id);
		super.addMessage(message, url, SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 删除通用工单信息
	 * 
	 * @return
	 */
	public String delete() {
		String workOrderId = super.getRequest().getParameter("id");
		workOrderWaitDeletedService.deletedWorkOrder(workOrderId);
		String pageNo = super.getRequest().getParameter("pageNo");
		StringBuffer url = new StringBuffer(DRAFT_PAGE_URL);
		url.append("&pageNo=");
		url.append(pageNo);
		super.addMessage("工单删除成功!", url.toString(), SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 工单派发信息列表显示
	 * 
	 * @return String
	 */
	public String dispatchList() {
		String id = super.getRequest().getParameter("id");
		List<Map<String, Object>> list = workOrderTransferService
				.getDispatchList(id);
		super.getRequest().setAttribute("list", list);
		return DISPATCH_LIST;
	}

	@Override
	public String view() {
		String id = super.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			workOrder = workOrderDispatchService.view(id);
		}
		return VIEW;
	}

	/**
	 * 工单整体信息显示
	 * 
	 * @return String
	 */
	public String viewAll() {
		String id = super.getRequest().getParameter("id");
		String pId = super.getRequest().getParameter("pId");
		String type = super.getParameter("type");
		if (StringUtils.isNotBlank(id)) {
			workOrder = workOrderDispatchService.view(id);
		}
		if (StringUtils.isNotBlank(type)) {
			super.getRequest().setAttribute("type", type);
		}else{
			super.getRequest().setAttribute("type", "");
		}
		super.getRequest().setAttribute("pid", pId);
		return VIEW_ALL;
	}

	@Override
	public WorkOrder getModel() {
		return workOrder;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
		if (StringUtils.isNotBlank(workOrder.getId())) {
			workOrder = workOrderDispatchService.view(workOrder.getId());
		}
	}

	@Override
	protected void prepareSaveModel() throws Exception {
		if (workOrder == null) {
			workOrder = new WorkOrder();
		}
	}

	/**
	 * 获取成功返回的提示信息
	 * 
	 * @return
	 */
	private String getMessage() {
		String message = "";
		if (SysConstant.FORM_IS_SUBMITED.equals(workOrder.getIsSubmited())) {
			message = "工单提交成功!";
			return message;
		}
		if (StringUtils.isNotBlank(workOrder.getParentId())) {
			message = "工单转派成功!";
			return message;
		}
		message = "工单保存成功!";
		return message;
	}

	/**
	 * 获取成功返回页面中“返回”按钮的跳转路径
	 * 
	 * @param id
	 *            String 工单编号
	 * @return String “返回”按钮的跳转路径
	 */
	private String getSavedUrl(String id) {
		StringBuffer url = new StringBuffer("");
		String pageNo = super.getRequest().getParameter("pageNo");
		if (StringUtils.isNotBlank(workOrder.getParentId())) {
			url.append(WAIT_HANDLED_PAGE_URL);
			url.append("&pageNo=");
			url.append(pageNo);
			return url.toString();
		}
		if (StringUtils.isBlank(id)) {
			url.append(NEW_INPUT_PAGE_URL);
			return url.toString();
		} else {
			url.append(DRAFT_PAGE_URL);
			url.append("&pageNo=");
			url.append(pageNo);
			return url.toString();
		}

	}
}
