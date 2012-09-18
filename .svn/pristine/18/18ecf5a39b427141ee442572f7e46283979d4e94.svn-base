package com.cabletech.business.workflow.electricity.security.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.electricity.security.dao.ElectricitySecurityBaseDao;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.model.OeOutageAlarm;
import com.cabletech.business.workflow.electricity.security.service.OeDispatchTaskService;
import com.cabletech.common.base.SysConstant;

/**
 * 断电告警派单业务操作接口实现
 * 
 * @author 杨隽 2012-05-03 创建
 * @author 杨隽 2012-05-07 修改save()、view()方法
 * 
 */
@Service
@Transactional
public class OeDispatchTaskServiceImpl extends
		ElectricitySecurityBaseServiceImpl<OeDispatchTask, String> implements
		OeDispatchTaskService {
	/**
	 * 断电告警派单信息DAO
	 */
	@Resource(name = "oeDispatchTaskDao")
	private ElectricitySecurityBaseDao<OeDispatchTask, String> oeDispatchTaskDao;
	/**
	 * 断电告警信息DAO
	 */
	@Resource(name = "oeOutageAlarmDao")
	private ElectricitySecurityBaseDao<OeOutageAlarm, String> oeOutageAlarmDao;

	/**
	 * 根据断电告警派单编号读取断电告警派单详细信息
	 * 
	 * @param id
	 *            String 断电告警派单编号
	 * @return OeDispatchTask 断电告警派单详细信息
	 */
	@Transactional(readOnly = true)
	@Override
	public OeDispatchTask viewOeDispatchTask(String id) {
		OeDispatchTask oeDispatchTask = oeDispatchTaskDao.get(id);
		String orgName = super.getOrgName(oeDispatchTask.getMaintenanceId());
		oeDispatchTask.setOrgName(orgName);
		String createrName = super.getUserName(oeDispatchTask.getCreator());
		oeDispatchTask.setCreaterName(createrName);
		String resourceName = super.getResourceName(oeDispatchTask);
		oeDispatchTask.setStationName(resourceName);
		return oeDispatchTask;
	}

	/**
	 * 断电告警派单
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 输入的断电告警派单信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@Override
	public void save(OeDispatchTask oeDispatchTask, UserInfo userInfo) {
		String alarmId = saveOeOutageAlarm(oeDispatchTask);
		if (OeDispatchTask.IS_SUBMITED.equals(oeDispatchTask.getIsSubmited())) {
			oeDispatchTask.setState(OeDispatchTask.WAIT_SCHEDULED_STATE);
		} else {
			oeDispatchTask.setState(OeDispatchTask.WAIT_DISPATCHED_STATE);
		}
		if (StringUtils.isBlank(oeDispatchTask.getId())) {
			oeDispatchTask.setId(null);
		}
		oeDispatchTask.setAlarmId(alarmId);
		oeDispatchTask.setCreator(userInfo.getPersonId());
		oeDispatchTask.setCreateDate(new Date());
		oeDispatchTask.setCurrentProcessUserId(userInfo.getPersonId());
		oeDispatchTask.setNextProcessOrgId(oeDispatchTask.getMaintenanceId());
		String taskCode = super.commonOrderCodeService.generatorWorkOrderCode(
				userInfo.getRegionId(),
				SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C31,
				SysConstant.OE_DISPATCHTASK_FLOW_TYPE);
		if (StringUtils.isBlank(oeDispatchTask.getTaskCode())) {
			oeDispatchTask.setTaskCode(taskCode);
		}
		oeDispatchTaskDao.save(oeDispatchTask);
		if (OeDispatchTask.IS_SUBMITED.equals(oeDispatchTask.getIsSubmited())) {
			SmParameter smParameter = getSmParameter(oeDispatchTask);
			doWorkflowStart(oeDispatchTask, smParameter);
		}
	}

	/**
	 * 根据断电告警派单编号删除断电告警派单信息
	 * 
	 * @param id
	 *            String[] 断电告警派单编号数组
	 */
	@Override
	public void deleteOeDispatchTask(String[] id) {
		if (ArrayUtils.isEmpty(id)) {
			return;
		}
		for (int i = 0; i < id.length; i++) {
			oeDispatchTaskDao.delete(id[i]);
		}
	}

	/**
	 * 保存断电告警单信息
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 输入的断电告警派单信息
	 * @return String 断电告警单编号
	 */
	private String saveOeOutageAlarm(OeDispatchTask oeDispatchTask) {
		OeOutageAlarm oeOutageAlarm;
		if (StringUtils.isBlank(oeDispatchTask.getAlarmId())) {
			return "";
		}
		oeOutageAlarm = oeOutageAlarmDao.get(oeDispatchTask.getAlarmId());
		oeOutageAlarmDao.save(oeOutageAlarm);
		return oeOutageAlarm.getId();
	}

	/**
	 * 获取派单短信发送参数
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 输入的断电告警派单信息
	 * @return SmParameter 短信发送参数
	 */
	private SmParameter getSmParameter(OeDispatchTask oeDispatchTask) {
		String simId = super.getOrgTel(oeDispatchTask.getNextProcessOrgId());
		String[] contentParameters = new String[] { oeDispatchTask.getTitle() };
		SmParameter parameter = SmParameter
				.getInstance(ELECTRICTITY_XML_FILE_ID, SEND_MSG_ID, simId,
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
	 * 执行供电保障派单工作流
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 输入的断电告警派单信息
	 * @param smParameter
	 *            SmParameter 短信发送参数
	 */
	private void doWorkflowStart(OeDispatchTask oeDispatchTask,
			SmParameter smParameter) {
		ProMockPo taskPi = new ProMockPo();
		taskPi.setBzid(oeDispatchTask.getId());
		taskPi.setUserId(oeDispatchTask.getCurrentProcessUserId());
		String userName = super.getUserName(oeDispatchTask
				.getCurrentProcessUserId());
		taskPi.setUserName(userName);
		taskPi.setDealGroup(oeDispatchTask.getNextProcessOrgId());
		String orgName = super.getOrgName(oeDispatchTask.getNextProcessOrgId());
		taskPi.setDealGroupName(orgName);
		super.getElectricitySecurityWorkflowService().sendTaskTwoSteps(taskPi,
				smParameter);
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
