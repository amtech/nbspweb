package com.cabletech.business.workflow.fault.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.business.workflow.fault.dao.FaultBaseDao;
import com.cabletech.business.workflow.fault.model.FaultDispatch;
import com.cabletech.business.workflow.fault.service.FaultDispatchDraftService;
import com.cabletech.common.util.Page;

/**
 * 故障派单草稿箱业务处理接口实现
 * 
 * @author 杨隽 2012-02-22 创建
 * 
 */
@Service
public class FaultDispatchDraftServiceImpl extends
		FaultBaseServiceImpl<FaultDispatch, String> implements
		FaultDispatchDraftService {
	// 故障派单Dao操作
	@Resource(name = "faultDispatchDao")
	private FaultBaseDao<FaultDispatch, String> faultDispatchDao;

	@Override
	protected FaultBaseDao<FaultDispatch, String> getFaultBaseDao() {
		// TODO Auto-generated method stub
		return faultDispatchDao;
	}

	/**
	 * 获取草稿箱列表分页数据
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件的故障派单实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 草稿箱列表分页数据
	 */
	@Override
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getDraftList(FaultQueryParameter faultQueryParameter,
			UserInfo userInfo) {
		// TODO Auto-generated method stub
		// 使用DispatchFaultDraftListConditionGenerateImpl的条件生成器实例进行sql组装查询
		faultQueryParameter.setUser(userInfo);
		Page page = getFaultList(faultQueryParameter,
				DRAFT_CONDITION_GENERATE_KEY);
		return page;
	}
}
