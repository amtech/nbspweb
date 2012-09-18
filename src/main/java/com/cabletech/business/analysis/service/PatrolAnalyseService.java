package com.cabletech.business.analysis.service;

import java.util.Map;

import com.cabletech.common.util.Page;

/**
 * 巡检统计分析接口
 * 
 * @author 杨隽 2012-07-27 创建
 * 
 */
public interface PatrolAnalyseService {
	/**
	 * 根据查询条件进行巡检统计
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param page
	 *            Page
	 */
	@SuppressWarnings("rawtypes")
	public void analyse(Map<String, Object> map, Page page);

	/**
	 * 进行查询统计的明细查看
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param page
	 *            Page
	 */
	@SuppressWarnings("rawtypes")
	public void analyseDetail(Map<String, Object> map, Page page);
}
