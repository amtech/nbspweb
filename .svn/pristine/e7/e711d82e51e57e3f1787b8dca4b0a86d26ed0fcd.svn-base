package com.cabletech.business.workflow.electricity.security.service;

import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.common.util.Page;

/**
 * 取消任务业务处理接口
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
public interface OeDispatchTaskCancelService {
	/**
	 * 获取待取消任务列表
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 供电保障查询实体
	 * @return Page 供电保障的待取消任务列表
	 */
	@SuppressWarnings("rawtypes")
	Page getWaitCanceledList(OeDispatchTask oeDispatchTask);

	/**
	 * 获取已取消任务列表
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 供电保障查询实体
	 * @return Page 供电保障的已取消任务列表
	 */
	@SuppressWarnings("rawtypes")
	Page getCanceledList(OeDispatchTask oeDispatchTask);

	/**
	 * 取消任务
	 * 
	 * @param id
	 *            String[] 断电站点派单编号数组
	 */
	void cancelTask(String[] id);
}
