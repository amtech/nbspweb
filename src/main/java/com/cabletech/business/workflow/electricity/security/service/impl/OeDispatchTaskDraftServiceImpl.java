package com.cabletech.business.workflow.electricity.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.workflow.electricity.security.dao.ElectricitySecurityBaseDao;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.service.OeDispatchTaskDraftService;
import com.cabletech.common.util.Page;

/**
 * 供电保障草稿箱业务处理接口实现
 * 
 * @author 杨隽 2012-05-03 创建
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(readOnly = true)
public class OeDispatchTaskDraftServiceImpl extends
		ElectricitySecurityBaseServiceImpl implements
		OeDispatchTaskDraftService {
	/**
	 * 断电告警派单信息DAO
	 */
	@Resource(name = "oeDispatchTaskDao")
	private ElectricitySecurityBaseDao oeDispatchTaskDao;

	/**
	 * 获取草稿箱列表分页数据
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 供电保障派单查询实体
	 * @return Page 草稿箱列表分页数据
	 */
	@Override
	public Page getDraftList(OeDispatchTask oeDispatchTask) {
		String key = DRAFT_CONDITION_GENERATE_KEY;
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
