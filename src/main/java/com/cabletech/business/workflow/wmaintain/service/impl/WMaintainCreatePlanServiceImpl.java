package com.cabletech.business.workflow.wmaintain.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainBaseDao;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainCreatePlanService;
import com.cabletech.business.workflow.wmaintain.service.WMaintainResultService;
import com.cabletech.business.workflow.wmaintain.service.WMaintainSiteService;
import com.cabletech.common.base.SysConstant;

/**
 * 制定维修作业计划服务接口实现
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-16 添加注释部分
 * @author 杨隽 2012-04-18 添加deletePlan()方法
 * @author 杨隽 2012-04-18 修改view()方法
 * @author 杨隽 2012-04-26 修改view()方法（添加获取计划中站点列表及站点异常项列表）
 * @author 杨隽 2012-05-04 更改使用公共的表单提交标识
 * 
 */
@Service
@Transactional
public class WMaintainCreatePlanServiceImpl extends
		WMaintainBaseServiceImpl<WMaintainPlan, String> implements
		WMaintainCreatePlanService {
	// 维修作业计划Dao
	@Resource(name = "WMaintainPlanDao")
	private WMaintainBaseDao<WMaintainPlan, String> wMaintainPlanDao;
	// 计划维护站点服务
	@Resource(name = "WMaintainSiteServiceImpl")
	private WMaintainSiteService wMaintainSiteService;
	// 站点异常项及处理结果服务
	@Resource(name = "WMaintainResultServiceImpl")
	private WMaintainResultService wMaintainResultService;

	/**
	 * 制定维修作业计划
	 * 
	 * @param plan
	 *            WMaintainPlan 维修作业计划表单数据
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@Override
	public void save(WMaintainPlan plan, UserInfo userInfo) {
		// TODO Auto-generated method stub
		writePlanInfo(plan, userInfo);
		if (StringUtils.isNotBlank(plan.getId())) {
			wMaintainResultService.deletePlanResult(plan.getId());
			wMaintainSiteService.deletePlanSite(plan.getId());
		}
		wMaintainPlanDao.save(plan);
		wMaintainSiteService.save(plan);
		if (SysConstant.FORM_IS_SUBMITED.equals(plan.getIsSubmited())) {
			SmParameter smParameter = getSmParameter(plan);
			if (StringUtils.isBlank(plan.getWorkflowTaskId())) {
				doWorkflowStart(plan, smParameter);
			} else {
				doWorkflowTask(plan, smParameter);
			}
		}
	}

	/**
	 * 查看维修作业计划详细信息
	 * 
	 * @param id
	 *            String 维修作业计划编号
	 * @return WMaintainPlan 维修作业计划详细信息数据
	 */
	@Override
	@Transactional(readOnly = true)
	public WMaintainPlan view(String id) {
		// TODO Auto-generated method stub
		WMaintainPlan plan = wMaintainPlanDao.get(id);
		plan.setPatrolGroupName(super.getPatrolGroupName(plan.getPatrolGroup()));
		plan.setAuditorName(super.getUserName(plan.getAuditor()));
		plan.setCreaterName(super.getUserName(plan.getCreater()));
		plan.setOrgName(super.getPatrolGroupOrgName(plan.getPatrolGroup()));
		List<Map<String, Object>> siteList = wMaintainSiteService
				.getWMaintainSiteListInGrid(id);
		plan.setSiteList(siteList);
		List<Map<String, Object>> resultList = wMaintainResultService
				.getWMaintainResultListInGrid(id);
		plan.setResultList(resultList);
		return plan;
	}

	/**
	 * 删除维修作业计划信息
	 * 
	 * @param id
	 *            String[] 维修作业计划编号
	 */
	public void deletePlan(String[] id) {
		if (ArrayUtils.isEmpty(id)) {
			return;
		}
		for (int i = 0; i < id.length; i++) {
			wMaintainResultService.deletePlanResult(id[i]);
			wMaintainSiteService.deletePlanSite(id[i]);
			wMaintainPlanDao.delete(id[i]);
		}
	}

	/**
	 * 获取维修作业计划处理操作Dao
	 * 
	 * @return WMaintainBaseDao<T, PK> 维修作业计划处理操作Dao
	 */
	@Override
	protected WMaintainBaseDao<WMaintainPlan, String> getWMaintainBaseDao() {
		// TODO Auto-generated method stub
		return wMaintainPlanDao;
	}

	/**
	 * 补充维修作业计划信息数据
	 * 
	 * @param plan
	 *            WMaintainPlan 维修作业计划表单数据
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	private void writePlanInfo(WMaintainPlan plan, UserInfo userInfo) {
		plan.setCreateDate(new Date());
		plan.setCreater(userInfo.getPersonId());
		plan.setCurrentProcessUserId(userInfo.getPersonId());
		plan.setNextProcessUserId(plan.getAuditor());
		if (SysConstant.FORM_IS_SUBMITED.equals(plan.getIsSubmited())) {
			plan.setPlanState(WMaintainPlan.WMAINTAIN_PLAN_SUBMITED_STATE);
		} else {
			plan.setPlanState(WMaintainPlan.WMAINTAIN_PLAN_NOTSUBMITED_STATE);
		}
	}

	/**
	 * 获取短信发送参数
	 * 
	 * @param plan
	 *            WMaintainPlan 维修作业计划信息
	 * @return SmParameter 短信发送参数
	 */
	private SmParameter getSmParameter(WMaintainPlan plan) {
		SmParameter smParameter = new SmParameter();
		String simId = super.getUserPhone(plan.getNextProcessUserId());
		String[] contentParameters = new String[] { plan.getPlanName() };
		smParameter.setSimId(simId);
		smParameter.setContentParameters(contentParameters);
		smParameter.setXmlFileId(WMAINTAIN_XML_FILE_ID);
		smParameter.setXmlMessageId(CREATE_PLAN_SEND_MSG_ID);
		return smParameter;
	}

	/**
	 * 执行制定维修作业计划工作流
	 * 
	 * @param plan
	 *            WMaintainPlan 维修作业计划信息
	 * @param smParameter
	 *            SmParameter 短信发送参数
	 */
	private void doWorkflowStart(WMaintainPlan plan, SmParameter smParameter) {
		// TODO Auto-generated method stub
		ProMockPo taskPi = new ProMockPo();
		setTaskPi(plan, taskPi);
		super.getwMaintainWorkflowService().sendTaskTwoSteps(taskPi,
				smParameter);
	}

	/**
	 * 执行修改维修作业计划工作流
	 * 
	 * @param plan
	 *            WMaintainPlan 维修作业计划信息
	 * @param smParameter
	 *            SmParameter 短信发送参数
	 */
	private void doWorkflowTask(WMaintainPlan plan, SmParameter smParameter) {
		// TODO Auto-generated method stub
		ProMockPo taskPi = new ProMockPo();
		taskPi.setTaskId(plan.getWorkflowTaskId());
		setTaskPi(plan, taskPi);
		taskPi.setTransition(SysConstant.PASS_WORKFLOW_TRANSTION);
		super.getwMaintainWorkflowService().doTask(taskPi, smParameter);
	}

	/**
	 * 将计划中信息放到工作流业务实体中
	 * 
	 * @param plan
	 *            WMaintainPlan 计划信息
	 * @param taskPi
	 *            ProMockPo 工作流业务实体
	 */
	private void setTaskPi(WMaintainPlan plan, ProMockPo taskPi) {
		String sendUserId = plan.getCurrentProcessUserId();
		String processUserId = plan.getNextProcessUserId();
		taskPi.setBzid(plan.getId());
		taskPi.setDealUsers(processUserId);
		String userName = super.getUserName(processUserId);
		taskPi.setDealUsersName(userName);
		taskPi.setUserId(sendUserId);
		userName = super.getUserName(sendUserId);
		taskPi.setUserName(userName);
	}
}
