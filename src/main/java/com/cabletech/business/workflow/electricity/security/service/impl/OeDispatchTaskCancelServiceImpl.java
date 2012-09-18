package com.cabletech.business.workflow.electricity.security.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.workflow.electricity.security.dao.ElectricitySecurityBaseDao;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.service.OeDispatchTaskCancelService;
import com.cabletech.common.util.Page;

/**
 * 取消任务业务处理接口实现
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional
public class OeDispatchTaskCancelServiceImpl extends
		ElectricitySecurityBaseServiceImpl implements
		OeDispatchTaskCancelService {
	/**
	 * 断电告警派单信息DAO
	 */
	@Resource(name = "oeDispatchTaskDao")
	private ElectricitySecurityBaseDao oeDispatchTaskDao;

	/**
	 * 获取待取消任务列表
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 供电保障查询实体
	 * @return Page 供电保障的待取消任务列表
	 */
	@Transactional(readOnly = true)
	@Override
	public Page getWaitCanceledList(OeDispatchTask oeDispatchTask) {
		String key = WAIT_CANCELED_CONDITION_GENERATE_KEY;
		return super.getOeDispatchTaskList(oeDispatchTask, key);
	}

	/**
	 * 获取已取消任务列表
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 供电保障查询实体
	 * @return Page 供电保障的已取消任务列表
	 */
	@Transactional(readOnly = true)
	@Override
	public Page getCanceledList(OeDispatchTask oeDispatchTask) {
		String key = CANCELED_CONDITION_GENERATE_KEY;
		return super.getOeDispatchTaskList(oeDispatchTask, key);
	}

	/**
	 * 取消任务
	 * 
	 * @param id
	 *            String[] 断电站点派单编号数组
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void cancelTask(String[] id) {
		if (ArrayUtils.isEmpty(id)) {
			return;
		}
		for (int i = 0; i < id.length; i++) {
			super.getElectricitySecurityWorkflowService().deleteTask(id[i]);
			OeDispatchTask oeDispatchTask = (OeDispatchTask) oeDispatchTaskDao
					.get(id[i]);
			oeDispatchTask.setState(OeDispatchTask.CANCELED_STATE);
			oeDispatchTaskDao.save(oeDispatchTask);
		}
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
