package com.cabletech.business.workflow.electricity.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.workflow.electricity.security.dao.ElectricitySecurityBaseDao;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.service.OeDispatchTaskHandledService;
import com.cabletech.common.util.Page;

/**
 * 已办任务业务处理接口实现
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(readOnly = true)
public class OeDispatchTaskHandledServiceImpl extends
		ElectricitySecurityBaseServiceImpl implements
		OeDispatchTaskHandledService {
	/**
	 * 断电告警派单信息DAO
	 */
	@Resource(name = "oeDispatchTaskDao")
	private ElectricitySecurityBaseDao oeDispatchTaskDao;

	/**
	 * 获取已办任务列表
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 供电保障查询实体
	 * @return Page 供电保障的已办任务列表
	 */
	@Override
	public Page getHandledList(OeDispatchTask oeDispatchTask) {
		String key = HANDLED_CONDITION_GENERATE_KEY;
		return super.getOeDispatchTaskList(oeDispatchTask, key);
	}

	/**
	 * 获取业务处理操作Dao
	 * 
	 * @return ElectricitySafeBaseDao<T, PK> 业务操作Dao
	 */
	@Override
	protected ElectricitySecurityBaseDao getElectricitySecurityBaseDao() {
		return oeDispatchTaskDao;
	}
}
