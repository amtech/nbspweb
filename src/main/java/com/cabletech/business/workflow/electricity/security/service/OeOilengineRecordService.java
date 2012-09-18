package com.cabletech.business.workflow.electricity.security.service;

import java.util.List;
import java.util.Map;

/**
 * 油机发电记录信息业务操作接口
 * 
 * @author 杨隽 2012-05-04 创建
 * 
 */
public interface OeOilengineRecordService {
	/**
	 * 根据供电保障派单编号获取油机发电记录信息列表
	 * 
	 * @param dispatchId
	 *            String 供电保障派单编号
	 * @return List<Map<String,Object>> 油机发电记录信息列表
	 */
	List<Map<String, Object>> getList(String dispatchId);
}
