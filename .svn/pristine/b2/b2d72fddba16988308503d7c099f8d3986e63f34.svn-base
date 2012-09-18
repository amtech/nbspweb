package com.cabletech.business.workflow.electricity.security.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.electricity.security.dao.ElectricitySecurityBaseDao;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.service.OeOilengineSearchService;
import com.cabletech.common.util.Page;

/**
 * 搜索油机信息业务操作接口
 * 
 * @author 杨隽 2012-05-09 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(readOnly = true)
public class OeOilengineSearchServiceImpl extends
		ElectricitySecurityBaseServiceImpl implements OeOilengineSearchService {
	@Resource(name = "oeOilengineSearchListConditionGenerateImpl")
	private ConditionGenerate conditionGenerate;
	/**
	 * 搜索油机信息DAO
	 */
	@Resource(name = "oeOilengineSearchDao")
	private ElectricitySecurityBaseDao oeOilengineSearchDao;

	/**
	 * 根据供电派单调度提供的站点编号、站点类型和油机编号获取油机信息列表
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 供电派单调度提供的站点编号、站点类型和油机编号
	 * @return List<Map<String,Object>> 油机信息列表
	 */
	@Override
	public Page getList(OeDispatchTask oeDispatchTask) {
		QueryParameter parameter = new QueryParameter();
		parameter.setEntity(oeDispatchTask);
		conditionGenerate.setQuerySql(parameter);
		conditionGenerate.setPage(oeDispatchTask.getPage());
		Page page = oeOilengineSearchDao.queryPageForSql(conditionGenerate);
		return page;
	}

	@Override
	protected ElectricitySecurityBaseDao getElectricitySecurityBaseDao() {
		return oeOilengineSearchDao;
	}
}
