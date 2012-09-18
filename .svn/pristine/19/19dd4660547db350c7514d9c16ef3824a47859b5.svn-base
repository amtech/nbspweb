package com.cabletech.business.workflow.workorder.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderReply;

/**
 * 通用工单历时计算业务接口
 * 
 * @author 杨隽 2012-01-10 创建
 * @author 杨隽 2012-01-11 添加setCalculateUnit方法并修改calcuateTotalResult返回类型
 * 
 */
public interface WorkOrderCalcuateService {
	/**
	 * 设置通用工单历时的计算单位
	 * 
	 * @param unitType
	 *            int 通用工单历时的计算单位
	 */
	void setCalculateUnit(int unitType);

	/**
	 * 根据工单的工作流业务编号进行工单总体历时的计算
	 * 
	 * @param transferId
	 *            String 工单的工作流业务编号
	 * @return String 工单总体历时的计算结果
	 */
	String calcuateTotalResult(String transferId);

	/**
	 * 根据工单的工作流业务编号进行工单每步任务处理历时的计算
	 * 
	 * @param transferId
	 *            String 工单的工作流业务编号
	 * @return List<Map<String, Object>> 工单每步任务处理历时的计算结果
	 */
	List<Map<String, Object>> calculateEveryStepResult(String transferId);

	/**
	 * 进行工单的是否超时判断
	 * 
	 * @param workOrder
	 *            工单实体
	 * @param workOrderReply
	 *            工单回单实体
	 * @return boolean 工单的是否超时
	 */
	boolean isOverTime(WorkOrder workOrder, WorkOrderReply workOrderReply);
}
