package com.cabletech.business.workflow.electricity.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.workflow.electricity.security.dao.ElectricitySecurityBaseDao;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.service.OeDispatchTaskQueryService;
import com.cabletech.common.util.Page;

/**
 * 供电保障查询列表业务接口
 * 
 * @author 杨隽 2012-05-04 创建
 */
@Service
@Transactional(readOnly = true)
public class OeDispatchTaskQueryServiceImpl extends
		ElectricitySecurityBaseServiceImpl<OeDispatchTask, String> implements
		OeDispatchTaskQueryService {
	/**
	 * 断电告警派单信息DAO
	 */
	@Resource(name = "oeDispatchTaskDao")
	private ElectricitySecurityBaseDao<OeDispatchTask, String> oeDispatchTaskDao;

	/**
	 * 获取供电保障列表
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 供电保障查询实体
	 * @return Page 供电保障列表
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Page getList(OeDispatchTask oeDispatchTask) {
		// TODO Auto-generated method stub
		String key = DISPATCH_LIST_CONDITION_GENERATE_KEY;
		return super.getOeDispatchTaskList(oeDispatchTask, key);
	}

	@Override
	protected ElectricitySecurityBaseDao<OeDispatchTask, String> getElectricitySecurityBaseDao() {
		// TODO Auto-generated method stub
		return oeDispatchTaskDao;
	}

}
