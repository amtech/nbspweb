package com.cabletech.business.workflow.fault.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.common.util.Page;

/**
 * 故障告警业务操作接口
 * 
 * @author 杨隽 2012-02-07 创建
 * 
 */
public interface FaultAlertService {
	/**
	 * 根据故障告警单编号查看故障告警单信息
	 * 
	 * @param id
	 *            String 故障告警单编号
	 * @return FaultAlert 故障告警单信息
	 */
	FaultAlert viewFaultAlert(String id);

	/**
	 * 忽略故障告警单
	 * 
	 * @param id
	 *            String 故障告警单编号
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	void ignore(String id, UserInfo userInfo);

	/**
	 * 根据查询条件获取故障告警单分页列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件参数
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return Page 故障告警单分页列表
	 */
	@SuppressWarnings("rawtypes")
	Page getList(FaultQueryParameter faultQueryParameter, UserInfo userInfo);

	/**
	 * 根据查询条件获取未派单故障告警单分页列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件参数
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return Page 未派单故障告警单分页列表
	 */
	@SuppressWarnings("rawtypes")
	Page getUnDispatchedList(FaultQueryParameter faultQueryParameter,
			UserInfo userInfo);
}
