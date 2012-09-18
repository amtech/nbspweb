package com.cabletech.business.sysmanager.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.sysmanager.model.RemindTimeConfigure;
import com.cabletech.common.util.Page;

/**
 * @author zg
 * 
 */
public interface RemindTimeConfigureService {

	/**
	 * 取列表
	 * 
	 * @param page
	 *            Page
	 * @param parameters
	 *            Map<String,Object>
	 * @return
	 */
	Page<RemindTimeConfigure> getQueryList(Page<RemindTimeConfigure> page,
			Map<String, Object> parameters);

	/**
	 * 获取实体对象
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	RemindTimeConfigure getEntityById(String id);

	/**
	 * 保存对象
	 * 
	 * @param remindTimeConfigure
	 *            RemindTimeConfigure
	 */
	void saveEntity(RemindTimeConfigure remindTimeConfigure);

	/**
	 * 根据id 删除对象
	 * 
	 * @param id
	 *            String
	 */
	void deleteEntityByid(String id);

	/**
	 * 根据工单类型 发送短信类型 专业类型判断数据库中是否有相同的数据存在
	 * 
	 * @param taskType
	 *            工单类型
	 * @param smsType
	 *            发送短信类型
	 * @param businessType
	 *            专业类型专业类型
	 * @return
	 */
	int checkNum(String taskType, String smsType, String businessType);

	/**
	 * 根据工单类型 专业类型 查询对象
	 * 
	 * @param workorderType
	 *            工单类型
	 * @param professionType
	 *            专业类型
	 * @return list
	 */
	List<Map<String, Object>> getObjectListByType(String workorderType,
			String professionType);

}
