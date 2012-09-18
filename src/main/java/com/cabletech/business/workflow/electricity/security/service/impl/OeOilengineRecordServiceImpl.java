package com.cabletech.business.workflow.electricity.security.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.electricity.security.dao.ElectricitySecurityBaseDao;
import com.cabletech.business.workflow.electricity.security.service.OeOilengineRecordService;

/**
 * 油机发电记录信息业务操作接口实现
 * 
 * @author 杨隽 2012-05-04 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(readOnly = true)
public class OeOilengineRecordServiceImpl extends
		ElectricitySecurityBaseServiceImpl implements OeOilengineRecordService {
	// 油机发电记录信息DAO
	@Resource(name = "oeOilengineRecordDao")
	private ElectricitySecurityBaseDao oeOilengineRecordDao;
	// 油机发电记录查询条件生成器
	@Resource(name = "oeOilengineRecordListConditionGenerateImpl")
	private ConditionGenerate conditionGenerate;

	/**
	 * 根据供电保障派单编号获取油机发电记录信息列表
	 * 
	 * @param dispatchId
	 *            String 供电保障派单编号
	 * @return List<Map<String,Object>> 油机发电记录信息列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getList(String dispatchId) {
		QueryParameter parameter = new QueryParameter();
		parameter.setId(dispatchId);
		conditionGenerate.setQuerySql(parameter);
		return oeOilengineRecordDao.queryListForSql(conditionGenerate);
	}

	@Override
	protected ElectricitySecurityBaseDao getElectricitySecurityBaseDao() {
		return oeOilengineRecordDao;
	}
}
