package com.cabletech.business.workflow.fault.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.business.workflow.fault.model.FaultDispatch;
import com.cabletech.common.util.Page;

/**
 * 故障派单业务操作接口
 * 
 * @author 杨隽 2012-02-07 创建
 * @author 杨隽 2012-02-22 添加deleteDispatch方法
 * 
 */
public interface FaultDispatchService {
	/**
	 * 根据故障派单编号读取故障派单详细信息
	 * 
	 * @param id
	 *            String 故障派单编号
	 * @return FaultDispatch 故障派单详细信息
	 */
	FaultDispatch viewFaultDispatch(String id);

	/**
	 * 故障派单
	 * 
	 * @param faultAlert
	 *            FaultAlert 故障告警单信息
	 * @param faultDispatch
	 *            FaultDispatch 输入的故障派单信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	void dispatch(FaultAlert faultAlert, FaultDispatch faultDispatch,
			UserInfo userInfo);

	/**
	 * 根据查询条件获取故障派单分页列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件参数
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return Page 故障派单分页列表
	 */
	@SuppressWarnings("rawtypes")
	Page getList(FaultQueryParameter faultQueryParameter, UserInfo userInfo);

	/**
	 * 根据查询条件获取待办故障派单分页列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件参数
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return Page 待办故障派单分页列表
	 */
	@SuppressWarnings("rawtypes")
	Page getWaitHandledList(FaultQueryParameter faultQueryParameter,
			UserInfo userInfo);

	/**
	 * 根据故障派单编号删除故障派单信息
	 * 
	 * @param id
	 *            String 故障派单编号
	 */
	void deleteDispatch(String id);
}
