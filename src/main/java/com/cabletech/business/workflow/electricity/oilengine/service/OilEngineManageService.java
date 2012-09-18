package com.cabletech.business.workflow.electricity.oilengine.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.oilengine.model.OilEngine;
import com.cabletech.common.util.Page;

/**
 * 油机管理业务接口
 * 
 * @author wangt
 * 
 */
@SuppressWarnings("rawtypes")
public interface OilEngineManageService {
	/**
	 * 查询
	 * 
	 * @param entity
	 *            条件
	 * @param userInfo 登录用户
	 * @return
	 */
	Page getOilEngineList(OilEngine entity, UserInfo userInfo);

	/**
	 * 根据id获取单个实体
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	OilEngine viewOilEngine(String id);

	/**
	 * 修改或保存
	 * 
	 * @param entity
	 *            实体实体
	 */
	void saveOilEngine(OilEngine entity);

	/**
	 * 删除
	 * 
	 * @param id
	 *            系统
	 */
	void deleteOilEngine(String[] id);

	/**
	 * 检查有机编码是否已存在
	 * 
	 * @param id
	 *            String
	 * @param codevalue
	 *            String
	 * @return
	 */
	long getCodeNumber(String id, String codevalue);

	/**
	 * 获取可分配滴油机列表
	 * 
	 * @param property_right
	 *            油机产权
	 * @param oilengine_code
	 *            编码
	 * @return
	 */
	List<Map<String, Object>> getOilEngine(String property_right,
			String oilengine_code);

	/**
	 * 油机分配
	 * 
	 * @param id
	 *            油机ids
	 * @param maintenanceId
	 *            维护单位
	 */
	void assEngine(String id, String maintenanceId);
}
