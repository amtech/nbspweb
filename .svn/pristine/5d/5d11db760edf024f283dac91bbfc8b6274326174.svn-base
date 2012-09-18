package com.cabletech.business.workflow.workorder.service;

import com.cabletech.business.workflow.workorder.model.WorkCommonOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrder;

/**
 * 通用工单信息内容业务接口
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-01-04 添加getWorkCommonOrder方法
 * 
 */
public interface WorkCommonOrderService {
	/**
	 * 保存通用工单内容信息
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单信息
	 */
	void save(WorkOrder workOrder);

	/**
	 * 删除通用工单内容信息
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单信息
	 */
	void delete(WorkOrder workOrder);

	/**
	 * 根据通用工单编号获取通用工单的内容信息
	 * 
	 * @param taskId
	 *            String 通用工单编号
	 * @return WorkCommonOrder 通用工单的内容信息
	 */
	WorkCommonOrder getWorkCommonOrder(String taskId);
}
