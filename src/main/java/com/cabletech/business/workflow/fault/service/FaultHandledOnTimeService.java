package com.cabletech.business.workflow.fault.service;

import java.util.Map;

import com.cabletech.business.workflow.common.condition.StatisticQueryParameter;
import com.cabletech.common.util.Page;

/**
 * 故障处理及时率统计接口
 * 
 * @author 赵璧 2012-03-15 创建
 * @author 杨隽 2012-04-28 进行方法的参数个数重构
 * 
 */
public interface FaultHandledOnTimeService {
	/**
	 * 获取故障处理及时列表
	 * 
	 * @param page
	 *            Page 分页信息数据
	 * @param parameter
	 *            StatisticQueryParameter 查询条件参数
	 * @return Page 分页信息数据
	 */
	@SuppressWarnings("rawtypes")
	Page list(Page<Map<String, Object>> page, StatisticQueryParameter parameter);
}
