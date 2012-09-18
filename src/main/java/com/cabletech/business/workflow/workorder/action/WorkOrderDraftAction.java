package com.cabletech.business.workflow.workorder.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.service.WorkOrderDraftService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 通用工单草稿箱列表ACTION
 * 
 * @author 杨隽 2012-01-04 创建
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/workorder/workorder_drafts_list.jsp") })
@Action("/workOrderDraftAction")
@Scope("prototype")
public class WorkOrderDraftAction extends BaseAction<WorkOrder, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 通用工单表单数据
	private WorkOrder workOrder = new WorkOrder();
	// 通用工单草稿箱业务处理
	@Resource(name = "workOrderDraftServiceImpl")
	private WorkOrderDraftService workOrderDraftService;

	public WorkOrder getModel() {
		return workOrder;
	}

	/**
	 * 进入通用工单草稿箱列表页面
	 * 
	 * @return
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 获取草稿箱数据
	 */
	@SuppressWarnings("rawtypes")
	public void listdata() {
		UserInfo userInfo = super.getUser();
		Page page = super.initPage();
		workOrder.setPage(page);
		page = workOrderDraftService.getDraftList(workOrder, userInfo);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}
}