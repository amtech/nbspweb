package com.cabletech.business.workflow.electricity.security.service;

import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.common.util.Page;

/**
 * 搜索油机信息业务操作接口
 * 
 * @author 杨隽 2012-05-09 创建
 * 
 */
public interface OeOilengineSearchService {
	/**
	 * 根据供电派单调度提供的站点编号、站点类型和油机编号获取油机信息列表
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 供电派单调度提供的站点编号、站点类型和油机编号
	 * @return List<Map<String,Object>> 油机信息列表
	 */
	@SuppressWarnings("rawtypes")
	Page getList(OeDispatchTask oeDispatchTask);
}
