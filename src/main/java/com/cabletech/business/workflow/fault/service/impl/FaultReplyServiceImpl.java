package com.cabletech.business.workflow.fault.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.fault.dao.FaultBaseDao;
import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.business.workflow.fault.model.FaultDispatch;
import com.cabletech.business.workflow.fault.model.FaultReply;
import com.cabletech.business.workflow.fault.service.FaultReplyService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.config.GlobeConfigParameter;
import com.cabletech.common.util.DateUtil;
import com.cabletech.ems.internal.interfaces.ws.IFaultInnerWS;

/**
 * 故障回单业务操作
 * 
 * @author 杨隽 2011-10-27 创建
 * @author 杨隽 2011-10-31 修改继承的基类
 * @author 杨隽 2012-02-08 修改getBaseDao()方法实现为getFaultBaseDao ()方法实现
 * @author 杨隽 2012-03-27 集成工单短信发送功能（添加getSmParameter方法并修改reply方法和doWorkflow方法）
 * @author 杨隽 2012-07-18
 *         添加doEomsReply()、getEomsClearTime()和isWantedEomsInvoked()方法并实现“阶段回复”功能
 * 
 */
@Service
public class FaultReplyServiceImpl extends
		FaultBaseServiceImpl<FaultReply, String> implements FaultReplyService {
	// 故障告警单Dao操作
	@Resource(name = "faultAlertDao")
	private FaultBaseDao<FaultAlert, String> faultAlertDao;
	// 故障派单Dao操作
	@Resource(name = "faultDispatchDao")
	private FaultBaseDao<FaultDispatch, String> faultDispatchDao;
	// 故障回单Dao操作
	@Resource(name = "faultReplyDao")
	private FaultBaseDao<FaultReply, String> faultReplyDao;
	// 全局配置参数
	@Resource(name = "globeConfigParameter")
	private GlobeConfigParameter config;
	// EOMS调用接口
	@Resource(name = "faultInnerWsService")
	private IFaultInnerWS faultInnerWsService;

	@Override
	protected FaultBaseDao<FaultReply, String> getFaultBaseDao() {
		// TODO Auto-generated method stub
		return faultReplyDao;
	}

	/**
	 * 故障回单
	 * 
	 * @param faultReply
	 *            FaultReply 输入的故障回单信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@Transactional
	public void reply(FaultReply faultReply, UserInfo userInfo) {
		// TODO Auto-generated method stub
		// 获取故障派单信息
		FaultDispatch faultDispatch = faultDispatchDao.get(faultReply
				.getTaskId());
		// 保存故障回单
		faultReply.setCreator(userInfo.getPersonId());
		faultReply.setReplyTime(new Date());
		faultReply.setMaintenanceId(userInfo.getOrgId());
		if (StringUtils.isBlank(faultReply.getId())) {
			faultReplyDao.getSession().save(faultReply);
		} else {
			faultReplyDao.save(faultReply);
		}
		if (SysConstant.FORM_IS_SUBMITED.equals(faultReply.getIsSubmited())) {
			faultDispatch.setState(FaultDispatch.WAIT_APPROVED_STATE);
			faultDispatchDao.save(faultDispatch);
		}
		if (SysConstant.FORM_IS_SUBMITED.equals(faultReply.getIsSubmited())) {
			SmParameter parameter = getSmParameter(faultReply, faultDispatch);
			// 执行工作流业务
			doWorkflow(faultReply, faultDispatch, parameter);
		}
	}

	/**
	 * 根据故障派单编号获取故障回单信息
	 * 
	 * @param dispatchId
	 *            String 故障派单编号
	 * @return FaultReply 故障回单信息
	 */
	@Transactional(readOnly = true)
	public FaultReply getFaultReply(String dispatchId) {
		// TODO Auto-generated method stub
		FaultReply faultReply = faultReplyDao
				.findUniqueBy("taskId", dispatchId);
		FaultDispatch faultDispatch = faultDispatchDao.get(dispatchId);
		FaultAlert faultAlert = faultAlertDao.get(faultDispatch.getAlarmId());
		String clearTime = getEomsClearTime(faultAlert);
		if (faultReply == null) {
			faultReply = new FaultReply();
		}
		if (faultReply.getFaultClearTime() == null) {
			faultReply.setFaultClearTime(DateUtil.Str2UtilDate(clearTime,
					"yyyy-MM-dd HH:mm:ss"));
		}
		return faultReply;
	}

	@Override
	@Transactional(readOnly = true)
	public void doEomsReply(FaultReply faultReply, FaultAlert faultAlert) {
		if (!isWantedEomsInvoked(faultAlert)) {
			return;
		}
		String eomsId = faultAlert.getEomsId();
		try {
			if (SysConstant.FORM_IS_SUBMITED.equals(faultReply.getIsSubmited())) {
				faultInnerWsService.replyOrder(eomsId);
			} else {
				faultInnerWsService.timeoutOrder(eomsId);
			}
		} catch (Exception ex) {
			logger.error("", ex);
		}
	}

	/**
	 * 执行故障回单工作流
	 * 
	 * @param faultReply
	 *            FaultReply 故障回单信息
	 * @param faultDispatch
	 *            FaultDispatch 故障派单信息
	 * @param smParameter
	 *            SmParameter 短信发送传递参数
	 */
	private void doWorkflow(FaultReply faultReply, FaultDispatch faultDispatch,
			SmParameter smParameter) {
		// TODO Auto-generated method stub
		ProMockPo pro = new ProMockPo();
		String sendUserId = faultReply.getCreator();
		String processUserId = faultReply.getApprover();
		pro.setUserId(sendUserId);
		pro.setUserName(super.getUserName(sendUserId));
		pro.setTaskId(faultReply.getWorkflowTaskId());
		pro.setDealUsers(processUserId);
		pro.setDealUsersName(super.getUserName(processUserId));
		pro.setTransition("pass");
		super.getFaultWorkflowManager().doTask(pro, smParameter);
	}

	/**
	 * 获取短信发送传递参数
	 * 
	 * @param faultReply
	 *            FaultReply 故障回单信息
	 * @param faultDispatch
	 *            FaultDispatch 故障派单信息
	 * @return SmParameter 短信发送传递参数
	 */
	private SmParameter getSmParameter(FaultReply faultReply,
			FaultDispatch faultDispatch) {
		// TODO Auto-generated method stub
		String simId = super.getUserPhone(faultReply.getApprover());
		String alertId = faultDispatch.getAlarmId();
		FaultAlert faultAlert = faultAlertDao.get(alertId);
		String[] contentParameters = new String[] { faultAlert
				.getTroubleTitle() };
		SmParameter parameter = SmParameter.getInstance(FAULT_XML_FILE_ID,
				REPLY_TROUBLE_MSG_ID, simId, contentParameters);
		parameter.setWrittenDb(true);
		parameter.setBusinessType(faultAlert.getBusinessType());
		parameter.setHandleLimit(faultDispatch.getDeadline());
		parameter.setWorkorderId(faultDispatch.getId());
		parameter.setWorkorderTitle(faultAlert.getTroubleTitle());
		parameter.setWorkorderType(SysConstant.FAULT_FLOW_TYPE);
		return parameter;
	}

	/**
	 * 获取EOMS的故障消除时间
	 * 
	 * @param faultAlert
	 *            FaultAlert
	 * @return String
	 */
	private String getEomsClearTime(FaultAlert faultAlert) {
		if (!isWantedEomsInvoked(faultAlert)) {
			return "";
		}
		String clearTime = faultInnerWsService.checkAlarm(faultAlert
				.getEomsId());
		if (clearTime == null || FaultReply.ISNOT_CLEAR_FAULT.equals(clearTime)) {
			clearTime = "";
		}
		return clearTime;
	}

	/**
	 * 判断是否需要进行EOMS的调用
	 * 
	 * @param faultAlert
	 *            FaultAlert
	 * @return boolean
	 */
	private boolean isWantedEomsInvoked(FaultAlert faultAlert) {
		return config.isEomsSwitch() && faultAlert.isEomsFault();
	}
}
