package com.cabletech.business.workflow.fault.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.business.workflow.fault.dao.FaultBaseDao;
import com.cabletech.business.workflow.fault.model.FaultDispatch;
import com.cabletech.business.workflow.fault.service.FaultDispatchHandledService;
import com.cabletech.common.util.Page;

/**
 * 已办工作业务处理类
 * 
 * @author 杨隽 2012-02-22 创建
 * 
 */
@Service
public class FaultDispatchHandledServiceImpl extends
		FaultBaseServiceImpl<FaultDispatch, String> implements
		FaultDispatchHandledService {
	// 故障派单Dao操作
	@Resource(name = "faultDispatchDao")
	private FaultBaseDao<FaultDispatch, String> faultDispatchDao;

	@Override
	protected FaultBaseDao<FaultDispatch, String> getFaultBaseDao() {
		return faultDispatchDao;
	}

	/**
	 * 获取已办任务列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件的故障派单实体
	 * @param userInfo
	 *            UserInfo 当前登录用户
	 * @return Page 故障派单的已办任务列表
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getHandledList(FaultQueryParameter faultQueryParameter,
			UserInfo userInfo) {
		// 使用DispatchFaultHandledListConditionGenerateImpl的条件生成器实例进行sql组装查询
		faultQueryParameter.setUser(userInfo);
		Page page = getFaultList(faultQueryParameter,
				HANDLED_CONDITION_GENERATE_KEY);
		return page;
	}
}
