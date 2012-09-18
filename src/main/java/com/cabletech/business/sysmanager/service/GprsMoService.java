package com.cabletech.business.sysmanager.service;

import java.util.Map;

import com.cabletech.common.util.Page;

/**
 * GPRS日志查询
 * @author wangt
 *
 */
public interface GprsMoService {

	/**
	 * 查询日志列表
	 * @param page 
	 * @param parameters 
	 * @return
	 */
	Page<Map<String, Object>> getQueryList(Page<Map<String, Object>> page,
			Map<String, Object> parameters);

}
