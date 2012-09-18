package com.cabletech.business.workflow.fault.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.business.workflow.fault.dao.FaultBaseDao;
import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.business.workflow.fault.model.FaultDispatch;
import com.cabletech.business.workflow.fault.service.FaultDispatchService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 故障派单业务操作
 * 
 * @author 杨隽 2011-10-27 创建
 * @author 杨隽 2011-10-31 修改继承的基类
 * @author 杨隽 2011-11-04 修改dispatch方法以支持手动派单
 * @author 杨隽 2011-11-29 修改doWorkflow()方法（将下步处理人的编号改为代维单位编号）
 * @author 杨隽 2011-12-12 添加获取“联系电话”属性的过程
 * @author 杨隽 2012-02-07 添加getList()方法和getWaitHandledList()方法
 * @author 杨隽 2012-02-08 修改getBaseDao()方法实现为getFaultBaseDao ()方法实现
 * @author 杨隽 2012-02-08 提取获取故障业务单列表的公共方法
 * @author 杨隽 2012-02-22 添加故障派单保存的业务功能
 * @author 杨隽 2012-02-22 添加deleteDispatch方法
 * @author 杨隽 2012-03-27
 *         集成工单短信发送功能（添加getSmParameter方法并修改dispatch方法和doWorkflow方法）
 * @author 杨隽 2012-05-04 更改使用公共的表单提交标识
 * 
 */
@Service
public class FaultDispatchServiceImpl extends
		FaultBaseServiceImpl<FaultDispatch, String> implements
		FaultDispatchService {
	// 故障告警单Dao操作
	@Resource(name = "faultAlertDao")
	private FaultBaseDao<FaultAlert, String> faultAlertDao;
	// 故障派单Dao操作
	@Resource(name = "faultDispatchDao")
	private FaultBaseDao<FaultDispatch, String> faultDispatchDao;

	@Override
	protected FaultBaseDao<FaultDispatch, String> getFaultBaseDao() {
		// TODO Auto-generated method stub
		return faultDispatchDao;
	}

	/**
	 * 根据故障派单编号读取故障派单详细信息
	 * 
	 * @param id
	 *            String 故障派单编号
	 * @return FaultDispatch 故障派单详细信息
	 */
	@Transactional(readOnly = true)
	public FaultDispatch viewFaultDispatch(String id) {
		FaultDispatch faultDispatch = faultDispatchDao.get(id);
		String orgName = super.getOrgName(faultDispatch.getMaintenanceId());
		String patrolGroupName = super.getPatrolGroupName(faultDispatch
				.getPatrolGroup());
		String creatorName = super.getUserName(faultDispatch.getCreater());
		String phone = super.getUserPhone(faultDispatch.getCreater());
		faultDispatch.setCreaterName(creatorName);
		faultDispatch.setMaintenanceName(orgName);
		faultDispatch.setPatrolGroupName(patrolGroupName);
		faultDispatch.setPhone(phone);
		return faultDispatch;
	}

	/**
	 * 故障派单
	 * 
	 * @param faultAlert
	 *            FaultAlert 故障告警单信息
	 * @param faultDispatch
	 *            FaultDispatch 输入的故障派单信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@Transactional
	public void dispatch(FaultAlert faultAlert, FaultDispatch faultDispatch,
			UserInfo userInfo) {
		// TODO Auto-generated method stub
		// 更新故障告警单的状态信息
		faultAlert.setState(FaultAlert.NOT_SUBMITED_STATE);
		if (SysConstant.FORM_IS_SUBMITED.equals(faultDispatch.getIsSubmited())) {
			faultAlert.setState(FaultAlert.DISPATCHED_STATE);
			faultDispatch.setState(FaultDispatch.PROCESSING_STATE);
		}
		if (StringUtils.isBlank(faultAlert.getId())) {
			faultAlertDao.getSession().save(faultAlert);
			faultDispatch.setAlarmId(faultAlert.getId());
		} else {
			faultAlertDao.save(faultAlert);
		}
		// 保存故障派单信息
		faultDispatch.setSendTime(new Date());
		faultDispatch.setCreater(userInfo.getPersonId());
		String taskCode = super.commonOrderCodeService.generatorWorkOrderCode(
				userInfo.getRegionId(), faultAlert.getBusinessType(),
				SysConstant.FAULT_FLOW_TYPE);
		if (StringUtils.isBlank(faultDispatch.getTaskCode())) {
			faultDispatch.setTaskCode(taskCode);
		}
		faultDispatchDao.save(faultDispatch);
		if (SysConstant.FORM_IS_SUBMITED.equals(faultDispatch.getIsSubmited())) {
			SmParameter parameter = getSmParameter(faultAlert, faultDispatch);
			// 执行工作流业务
			doWorkflow(faultDispatch, parameter);
		}
	}

	/**
	 * 根据查询条件获取故障派单分页列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件参数
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return Page 故障派单分页列表
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getList(FaultQueryParameter faultQueryParameter,
			UserInfo userInfo) {
		// TODO Auto-generated method stub
		faultQueryParameter.setUser(userInfo);
		Page page = getFaultList(faultQueryParameter,
				DISPATCH_FAULT_LIST_CONDITION_GENERATE_KEY);
		return page;
	}

	/**
	 * 根据查询条件获取待办故障派单分页列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件参数
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return Page 待办故障派单分页列表
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getWaitHandledList(FaultQueryParameter faultQueryParameter,
			UserInfo userInfo) {
		// TODO Auto-generated method stub
		faultQueryParameter.setUser(userInfo);
		Page page = getFaultList(faultQueryParameter,
				WAIT_HANDLED_DISPATCH_FAULT_LIST_CONDITION_GENERATE_KEY);
		return page;
	}

	/**
	 * 根据故障派单编号删除故障派单信息
	 * 
	 * @param id
	 *            String 故障派单编号
	 */
	@Override
	@Transactional
	public void deleteDispatch(String id) {
		// TODO Auto-generated method stub
		FaultDispatch faultDispatch = faultDispatchDao.get(id);
		FaultAlert faultAlert = faultAlertDao.get(faultDispatch.getAlarmId());
		faultAlert.setState(FaultAlert.WAIT_HANDLED_STATE);
		faultAlertDao.save(faultAlert);
		super.delete(id);
	}

	/**
	 * 执行故障派单工作流
	 * 
	 * @param faultDispatch
	 *            FaultDispatch 故障派单信息
	 * @param smParameter
	 *            SmParameter 短信发送传递参数
	 */
	@Transactional
	private void doWorkflow(FaultDispatch faultDispatch, SmParameter smParameter) {
		// TODO Auto-generated method stub
		ProMockPo pro = new ProMockPo();
		String sendUserId = faultDispatch.getCreater();
		String processMaintenanceId = faultDispatch.getMaintenanceId();
		pro.setBzid(faultDispatch.getId());
		pro.setUserId(sendUserId);
		pro.setUserName(super.getUserName(sendUserId));
		pro.setDealGroup(processMaintenanceId);
		pro.setDealGroupName(super.getOrgName(processMaintenanceId));
		super.getFaultWorkflowManager().sendTaskTwoSteps(pro, smParameter);
	}

	/**
	 * 获取短信发送传递参数
	 * 
	 * @param faultAlert
	 *            FaultAlert 故障告警单信息
	 * @param faultDispatch
	 *            FaultDispatch 故障派单信息
	 * @return SmParameter 短信发送传递参数
	 */
	private SmParameter getSmParameter(FaultAlert faultAlert,
			FaultDispatch faultDispatch) {
		// TODO Auto-generated method stub
		String simId = super.getOrgTel(faultDispatch.getMaintenanceId());
		String[] contentParameters = new String[] { faultAlert
				.getTroubleTitle() };
		SmParameter parameter = SmParameter.getInstance(FAULT_XML_FILE_ID,
				SEND_TROUBLE_MSG_ID, simId, contentParameters);
		parameter.setWrittenDb(true);
		parameter.setBusinessType(faultAlert.getBusinessType());
		parameter.setHandleLimit(faultDispatch.getDeadline());
		parameter.setWorkorderId(faultDispatch.getId());
		parameter.setWorkorderTitle(faultAlert.getTroubleTitle());
		parameter.setWorkorderType(SysConstant.FAULT_FLOW_TYPE);
		return parameter;
	}
}
