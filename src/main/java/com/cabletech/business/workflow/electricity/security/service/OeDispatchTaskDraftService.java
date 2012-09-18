package com.cabletech.business.workflow.electricity.security.service;

import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.common.util.Page;

/**
 * 供电保障草稿箱业务处理接口
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
public interface OeDispatchTaskDraftService {
	/**
	 * 获取草稿箱列表分页数据
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 供电保障派单查询实体
	 * @return Page 草稿箱列表分页数据
	 */
	@SuppressWarnings("rawtypes")
	Page getDraftList(OeDispatchTask oeDispatchTask);
}
