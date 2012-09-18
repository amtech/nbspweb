package com.cabletech.business.base.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.base.dao.CommonBaseDao;
import com.cabletech.business.base.model.LocaleProcess;
import com.cabletech.business.base.model.LocaleProcessPhotos;
import com.cabletech.business.base.service.LocaleProcessService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 现场处理过程业务接口实现
 * 
 * @author 杨隽 2012-01-09 创建
 * 
 */
@Service
public class LocaleProcessServiceImpl extends
		BaseServiceImpl<LocaleProcess, String> implements LocaleProcessService {
	// 查询条件生成器业务处理Map
	@Autowired
	private Map<String, ConditionGenerate> conditionGenerateMap;
	// 现场处理过程Dao
	@Resource(name = "localeProcessDao")
	private CommonBaseDao<LocaleProcess, String> localeProcessDao;
	// 现场处理过程图片Dao
	@Resource(name = "localeProcessPhotosDao")
	private CommonBaseDao<LocaleProcessPhotos, String> localeProcessPhotosDao;

	@Override
	protected BaseDao<LocaleProcess, String> getBaseDao() {
		// TODO Auto-generated method stub
		return localeProcessDao;
	}

	/**
	 * 根据业务编号获取现场处理过程信息列表
	 * 
	 * @param localeProcess
	 *            LocaleProcess 带有业务编号的现场处理过程实体
	 * @return List<Map<String, Object>> 现场处理过程信息列表
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> showLocaleProcessList(
			LocaleProcess localeProcess) {
		localeProcess.setOrderColumn("p.process_time");
		String key = "localeConditionGenerate";
		QueryParameter parameter = new QueryParameter();
		parameter.setEntity(localeProcess);
		ConditionGenerate conditionGenerate = conditionGenerateMap.get(key);
		conditionGenerate.setQuerySql(parameter);
		return localeProcessDao.queryListForSql(conditionGenerate);
	}

	/**
	 * 根据业务编号获取现场处理过程图片信息列表
	 * 
	 * @param localeProcess
	 *            LocaleProcess 带有业务编号的现场处理过程实体
	 * @return List<Map<String, Object>> 现场处理过程图片信息列表
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> showLocaleProcessPhotosList(
			LocaleProcess localeProcess) {
		localeProcess.setOrderColumn("aao.upload_date");
		String key = "localePhotosConditionGenerate";
		QueryParameter parameter = new QueryParameter();
		parameter.setEntity(localeProcess);
		ConditionGenerate conditionGenerate = conditionGenerateMap.get(key);
		conditionGenerate.setQuerySql(parameter);
		return localeProcessPhotosDao.queryListForSql(conditionGenerate);
	}
}
