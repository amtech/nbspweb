package com.cabletech.business.workflow.workorder.service;

import com.cabletech.business.workflow.workorder.model.WorkOrderRefuseConfirm;

/**
 * 通用工单拒签确认业务接口
 * 
 * @author 杨隽 2012-01-06 创建
 * 
 */
public interface WorkOrderRefuseConfirmService {
	/**
	 * 保存通用工单拒签确认信息
	 * 
	 * @param workOrderRefuseConfirm
	 *            WorkOrderRefuseConfirm 通用工单拒签确认实体
	 */
	void save(WorkOrderRefuseConfirm workOrderRefuseConfirm);
}
