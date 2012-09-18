package com.cabletech.business.workflow.workorder.service;

import com.cabletech.business.workflow.workorder.model.WorkOrder;

/**
 * 通用工单分发业务接口
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-01-04 去除list方法，添加view方法
 * 
 */
public interface WorkOrderDispatchService {
	/**
	 * 保存通用工单信息
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单实体
	 * @throws RuntimeException
	 *             异常
	 */
	void save(WorkOrder workOrder) throws RuntimeException;

	/**
	 * 查看通用工单信息
	 * 
	 * @param id
	 *            String 通用工单信息编号
	 * @return WorkOrder 通用工单实体
	 */
	WorkOrder view(String id);
}
