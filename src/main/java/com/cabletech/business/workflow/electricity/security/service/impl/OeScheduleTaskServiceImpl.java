package com.cabletech.business.workflow.electricity.security.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.workflow.electricity.oilengine.dao.OilEngineManageDao;
import com.cabletech.business.workflow.electricity.oilengine.model.OilEngine;
import com.cabletech.business.workflow.electricity.security.dao.ElectricitySecurityBaseDao;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.model.OeScheduleTask;
import com.cabletech.business.workflow.electricity.security.service.OeScheduleTaskService;
import com.cabletech.common.base.SysConstant;

/**
 * 断电告警调度业务操作接口实现
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
@Service
@Transactional
public class OeScheduleTaskServiceImpl extends
		ElectricitySecurityBaseServiceImpl<OeDispatchTask, String> implements
		OeScheduleTaskService {
	/**
	 * 断电告警派单信息DAO
	 */
	@Resource(name = "oeDispatchTaskDao")
	private ElectricitySecurityBaseDao<OeDispatchTask, String> oeDispatchTaskDao;
	/**
	 * 油机信息管理DAO
	 */
	@Resource(name = "oilEngineManageDao")
	private OilEngineManageDao oilEngineDao;

	/**
	 * 断电告警调度
	 * 
	 * @param oeScheduleTask
	 *            OeScheduleTask 输入的断电告警派单调度信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@Override
	public void save(OeScheduleTask oeScheduleTask, UserInfo userInfo) {
		OeDispatchTask oeDispatchTask = saveOeDispatchTask(oeScheduleTask,
				userInfo);
		saveOilEngineState(oeScheduleTask);
		SmParameter smParameter = getSmParameter(oeDispatchTask);
		super.doWorkflow(oeDispatchTask, smParameter);
	}

	/**
	 * 保存油机的调度信息
	 * 
	 * @param oeScheduleTask
	 *            OeScheduleTask 输入的断电告警派单调度信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return OeDispatchTask 断电告警派单调度信息
	 */
	private OeDispatchTask saveOeDispatchTask(OeScheduleTask oeScheduleTask,
			UserInfo userInfo) {
		String dispatchId = oeScheduleTask.getDispatchId();
		OeDispatchTask oeDispatchTask = oeDispatchTaskDao.get(dispatchId);
		oeDispatchTask.setOilengineId(oeScheduleTask.getOilEngineId());
		oeDispatchTask.setPreStationId(oeScheduleTask.getPreStationId());
		oeDispatchTask.setYardMan(userInfo.getPersonId());
		oeDispatchTask.setDispatchTime(new Date());
		oeDispatchTask.setDispatchRemark(oeScheduleTask.getDispatchRemark());
		oeDispatchTask.setState(OeDispatchTask.PROCESSING_STATE);
		oeDispatchTask.setCurrentProcessUserId(userInfo.getPersonId());
		oeDispatchTask.setNextProcessOrgId(oeDispatchTask.getMaintenanceId());
		oeDispatchTask.setWorkflowTaskId(oeScheduleTask.getWorkflowTaskId());
		oeDispatchTask
				.setWorkflowTransition(SysConstant.PASS_WORKFLOW_TRANSTION);
		oeDispatchTask.setWorkflowComment(oeScheduleTask.getDispatchRemark());
		oeDispatchTaskDao.save(oeDispatchTask);
		return oeDispatchTask;
	}

	/**
	 * 保存油机的调度状态
	 * 
	 * @param oeScheduleTask
	 *            OeScheduleTask 输入的断电告警派单调度信息
	 */
	private void saveOilEngineState(OeScheduleTask oeScheduleTask) {
		if (StringUtils.isBlank(oeScheduleTask.getOilEngineId())) {
			return;
		}
		OilEngine oilEngine = oilEngineDao.get(oeScheduleTask.getOilEngineId());
		oilEngine.setState(SysConstant.OILENGINEUSE_DISPATCHE);
		oilEngineDao.save(oilEngine);
	}

	/**
	 * 获取调度短信发送参数
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 输入的断电告警派单调度信息
	 * @return SmParameter 短信发送参数
	 */
	private SmParameter getSmParameter(OeDispatchTask oeDispatchTask) {
		String simId = super.getOilEngineTel(oeDispatchTask.getOilengineId());
		String[] contentParameters = new String[] { oeDispatchTask.getTitle() };
		SmParameter parameter = SmParameter.getInstance(
				ELECTRICTITY_XML_FILE_ID, SCHEDULE_MSG_ID, simId,
				contentParameters);
		parameter.setWrittenDb(true);
		parameter
				.setBusinessType(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C31);
		parameter.setHandleLimit(oeDispatchTask.getHandleLimit());
		parameter.setWorkorderId(oeDispatchTask.getId());
		parameter.setWorkorderTitle(oeDispatchTask.getTitle());
		parameter.setWorkorderType(SysConstant.OE_DISPATCHTASK_FLOW_TYPE);
		return parameter;
	}

	/**
	 * 获取业务处理操作Dao
	 * 
	 * @return ElectricitySafeBaseDao<T, PK> 业务操作Dao
	 */
	@Override
	protected ElectricitySecurityBaseDao<OeDispatchTask, String> getElectricitySecurityBaseDao() {
		return oeDispatchTaskDao;
	}
}
