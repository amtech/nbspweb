package com.cabletech.business.workflow.workorder.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;

import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderReply;
import com.cabletech.business.workflow.workorder.service.WorkOrderCalcuateService;
import com.cabletech.business.workflow.workorder.service.WorkOrderDispatchService;
import com.cabletech.business.workflow.workorder.service.WorkOrderReplyService;
import com.cabletech.business.workflow.workorder.service.impl.WorkOrderCalcuateServiceImpl;

/**
 * 通用工单历时计算Action
 * 
 * @author 杨隽 2012-01-11 创建
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "total_calculate_result", location = "/workflow/workorder/workorder_total_calculate_result.jsp"),
		@Result(name = "every_step_calculate_result", location = "/workflow/workorder/workorder_every_step_calculate_result.jsp") })
@Action("/workOrderCalculateTimeAction")
@Scope("prototype")
public class WorkOrderCalculateTimeAction extends
		WorkOrderBaseAction<WorkOrder, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 每步历时计算结果显示页面跳转
	private static final String EVERY_STEP_CALCULATE_RESULT = "every_step_calculate_result";
	// 总历时计算结果显示页面跳转
	private static final String TOTAL_CALCULATE_RESULT = "total_calculate_result";
	// 通用工单业务处理接口
	@Resource(name = "workOrderDispatchServiceImpl")
	private WorkOrderDispatchService workOrderDispatchService;
	// 通用工单回复业务处理接口
	@Resource(name = "workOrderReplyServiceImpl")
	private WorkOrderReplyService workOrderReplyService;
	// 通用工单验证业务处理接口
	@Resource(name = "workOrderCalcuateServiceImpl")
	private WorkOrderCalcuateService workOrderCalcuateService;

	/**
	 * 工单历时总体计算
	 * 
	 * @return String
	 */
	public String totalCalucate() {
		String id = super.getRequest().getParameter("id");
		String transferId = super.getRequest().getParameter("pId");
		getTotalResultCalculate(id, transferId);
		return TOTAL_CALCULATE_RESULT;
	}

	/**
	 * 每步工单历时计算
	 * 
	 * @return String
	 */
	public String everyStepCalculate() {
		String id = super.getRequest().getParameter("id");
		String transferId = super.getRequest().getParameter("pId");
		getTotalResultCalculate(id, transferId);
		List<Map<String, Object>> list = workOrderCalcuateService
				.calculateEveryStepResult(transferId);
		super.getRequest().setAttribute("every_step_result", list);
		return EVERY_STEP_CALCULATE_RESULT;
	}

	@Override
	public WorkOrder getModel() {
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	/**
	 * 进行总体历时计算
	 * 
	 * @param id
	 *            String 工单编号
	 * @param transferId
	 *            String 工单工作流业务实体编号
	 */
	private void getTotalResultCalculate(String id, String transferId) {
		WorkOrder workOrder = workOrderDispatchService.view(id);
		WorkOrderReply workOrderReply = workOrderReplyService
				.getLatestWorkOrderReply(transferId);
		boolean isOverTime = workOrderCalcuateService.isOverTime(workOrder,
				workOrderReply);
		if (isOverTime) {
			super.getRequest().setAttribute("is_over_time","是");
		} else {
			super.getRequest().setAttribute("is_over_time","否");
		}
		workOrderCalcuateService
				.setCalculateUnit(WorkOrderCalcuateServiceImpl.MINUTE_UNIT);
		String totalResult = workOrderCalcuateService
				.calcuateTotalResult(transferId);
		super.getRequest().setAttribute("total_result", totalResult);
	}
}
