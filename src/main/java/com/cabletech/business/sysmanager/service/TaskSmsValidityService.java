package com.cabletech.business.sysmanager.service;

import java.util.Map;

import com.cabletech.common.util.Page;

/**
 * 接口 定制规则有效性
 * 
 * @author zg
 * 
 */
public interface TaskSmsValidityService {

	/**
	 * 查询列表
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            Map<String,Object>
	 * @return
	 */
	Page<Map<String, Object>> getQueryList(Page<Map<String, Object>> page,
			Map<String, Object> parameters);

	/**
	 * 关闭
	 * 
	 * @param id
	 *            String
	 */
	void shutdown(String id);

	/**
	 * 开启
	 * 
	 * @param id
	 *            String
	 */
	void startup(String id);

	/**
	 * 根据发送短信类型 工单类型获取有效性
	 * 
	 * @param smsType
	 *            发送短信类型
	 * @param workorderType
	 *            工单类型
	 * @return validity
	 */
	String getValidityByType(String smsType, String workorderType);

}
