package com.cabletech.business.workflow.workorder.service;

import com.cabletech.business.workflow.workorder.model.WorkOrderReplyCheck;

/**
 * 通用工单回单验证业务接口
 * 
 * @author 杨隽 2012-01-06 创建
 * 
 */
public interface WorkOrderReplyCheckService {
	/**
	 * 保存通用工单回单验证信息
	 * 
	 * @param workOrderReplyCheck
	 *            WorkOrderReplyCheck 通用工单回单验证实体
	 */
	void save(WorkOrderReplyCheck workOrderReplyCheck);
}
