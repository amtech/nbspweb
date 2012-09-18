package com.cabletech.business.workflow.workorder.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.workflow.workorder.model.WorkOrderReply;

/**
 * 通用工单回复业务接口
 * 
 * @author 杨隽 2012-01-06 创建
 * @author 杨隽 2012-01-09 添加getReplyList方法
 * @author 杨隽 2012-01-10 添加getLatestWorkOrderReply方法
 * 
 */
public interface WorkOrderReplyService {
	/**
	 * 保存通用工单回单信息
	 * 
	 * @param workOrderReply
	 *            WorkOrderReply 通用工单回单实体
	 * @throws RuntimeException
	 *             异常
	 */
	void save(WorkOrderReply workOrderReply) throws RuntimeException;

	/**
	 * 根据通用工单的分发编号获取通用工单回单信息列表
	 * 
	 * @param id
	 *            String 通用工单的分发编号
	 * @return List<Map<String, Object>> 通用工单回单信息列表
	 */
	List<Map<String, Object>> getReplyList(String id);

	/**
	 * 根据通用工单的分发编号获取最近一条通用工单回单信息
	 * 
	 * @param id
	 *            String 通用工单的分发编号
	 * @return WorkOrderReply 最近一条通用工单回单信息
	 */
	WorkOrderReply getLatestWorkOrderReply(String id);
}
