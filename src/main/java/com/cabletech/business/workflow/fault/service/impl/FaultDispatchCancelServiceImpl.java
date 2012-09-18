package com.cabletech.business.workflow.fault.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.flowservice.util.WorkFlowServiceClient;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.business.workflow.fault.dao.FaultBaseDao;
import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.business.workflow.fault.model.FaultDispatch;
import com.cabletech.business.workflow.fault.service.FaultDispatchCancelService;
import com.cabletech.common.util.Page;

/**
 * 取消任务业务处理类
 * 
 * @author 杨隽 2012-02-22 创建
 * 
 */
@Service
public class FaultDispatchCancelServiceImpl extends
		FaultBaseServiceImpl<FaultDispatch, String> implements
		FaultDispatchCancelService {
	@Resource(name = "workFlowServiceClient")
	private WorkFlowServiceClient workflowClient;
	// 故障告警单Dao操作
	@Resource(name = "faultAlertDao")
	private FaultBaseDao<FaultAlert, String> faultAlertDao;
	// 故障派单Dao操作
	@Resource(name = "faultDispatchDao")
	private FaultBaseDao<FaultDispatch, String> faultDispatchDao;

	@Override
	protected FaultBaseDao<FaultDispatch, String> getFaultBaseDao() {
		// TODO Auto-generated method stub
		return faultDispatchDao;
	}

	/**
	 * 获取待取消任务列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件的故障派单实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 故障派单的待取消任务列表
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getWaitCanceledList(FaultQueryParameter faultQueryParameter,
			UserInfo userInfo) {
		// 使用DispatchFaultWaitCanceledListConditionGenerateImpl的条件生成器实例进行sql组装查询
		faultQueryParameter.setUser(userInfo);
		Page page = getFaultList(faultQueryParameter,
				WAIT_CANCELED_CONDITION_GENERATE_KEY);
		return page;
	}

	/**
	 * 获取已取消任务列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件的故障派单实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 故障派单的已取消任务列表
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getCanceledList(FaultQueryParameter faultQueryParameter,
			UserInfo userInfo) {
		// 使用DispatchFaultCanceledListConditionGenerateImpl的条件生成器实例进行sql组装查询
		faultQueryParameter.setUser(userInfo);
		Page page = getFaultList(faultQueryParameter,
				CANCELED_CONDITION_GENERATE_KEY);
		return page;
	}

	/**
	 * 取消任务
	 * 
	 * @param id
	 *            String 任务分发派单编号
	 */
	@Transactional
	public void cancelDispatch(String id) {
		workflowClient.deletePins(id);
		FaultDispatch faultDispatch = faultDispatchDao.get(id);
		FaultAlert faultAlert = faultAlertDao.get(faultDispatch.getAlarmId());
		faultAlert.setState(FaultAlert.CANCELED_STATE);
		faultAlertDao.save(faultAlert);
	}
}