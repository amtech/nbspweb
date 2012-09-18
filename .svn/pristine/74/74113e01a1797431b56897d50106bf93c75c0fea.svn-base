package com.cabletech.business.sysmanager.service;

import java.util.List;
import java.util.Map;

/**
 * 周刚 控制信息業務接口 2012 -8-12
 * 
 */
public interface WorkorderControlInfoService {

	/**
	 * 获取所有的记录
	 * 
	 * @return list
	 */
	List<Map<String, Object>> getAllList();

	/**
	 * 修改状态是否在job中。
	 * 
	 * @param workorderId
	 *            String
	 */
	void updateSchedulerState(String workorderId);

	/**
	 * 获取工单执行人的电话 用户发短信
	 * 
	 * @param handlePersonId
	 *            String
	 * @return
	 */
	String getPhoneBYWorkId(String handlePersonId);

}
