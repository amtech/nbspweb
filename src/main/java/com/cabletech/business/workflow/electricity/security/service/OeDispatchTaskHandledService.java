package com.cabletech.business.workflow.electricity.security.service;

import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.common.util.Page;

/**
 * 已办任务业务处理接口
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
public interface OeDispatchTaskHandledService {
	/**
	 * 获取已办任务列表
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 供电保障查询实体
	 * @return Page 供电保障的已办任务列表
	 */
	@SuppressWarnings("rawtypes")
	Page getHandledList(OeDispatchTask oeDispatchTask);
}
