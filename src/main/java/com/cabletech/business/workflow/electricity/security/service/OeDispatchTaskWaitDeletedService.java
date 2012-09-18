package com.cabletech.business.workflow.electricity.security.service;

import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.common.util.Page;

/**
 * 待删除工作业务接口
 * 
 * @author 杨隽 2012-05-03 创建
 */
public interface OeDispatchTaskWaitDeletedService {
	/**
	 * 获取待删除业务列表
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 供电保障查询实体
	 * @return Page 供电保障的待删除任务列表
	 */
	@SuppressWarnings("rawtypes")
	public Page getWaitDeletedList(OeDispatchTask oeDispatchTask);
}
