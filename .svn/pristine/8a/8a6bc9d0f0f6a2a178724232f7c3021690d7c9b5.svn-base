package com.cabletech.business.workflow.workorder.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderTransfer;

/**
 * 通用工单分发信息业务接口
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-01-04 添加getAcceptUserIds方法
 * @author 杨隽 2012-01-05 修改getAcceptUserIds方法为getAcceptUsers方法
 * @author 杨隽 2012-01-05 添加getWaitHandledList方法
 * @author 杨隽 2012-01-09 添加getDispatchList方法
 * @author 杨隽 2012-02-06 去除getWaitHandledList()方法
 * 
 */
public interface WorkOrderTransferService {
	/**
	 * 保存通用工单分发信息
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单实体
	 */
	void save(WorkOrder workOrder);

	/**
	 * 删除通用工单分发信息
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单实体
	 */
	void delete(WorkOrder workOrder);

	/**
	 * 查看通用工单分发信息
	 * 
	 * @param id
	 *            String 工单信息编号
	 * @return WorkOrderTransfer 通用工单分发信息
	 */
	WorkOrderTransfer view(String id);

	/**
	 * 根据通用工单编号获取通用工单的受理人信息列表
	 * 
	 * @param taskId
	 *            String 通用工单编号
	 * @return String[] 通用工单的受理人信息列表
	 */
	String[] getAcceptUsers(String taskId);

	/**
	 * 根据通用工单编号获取通用工单派发信息
	 * 
	 * @param id
	 *            String 通用工单编号
	 * @return List<Map<String,Object>> 通用工单派发信息
	 */
	List<Map<String, Object>> getDispatchList(String id);
}
