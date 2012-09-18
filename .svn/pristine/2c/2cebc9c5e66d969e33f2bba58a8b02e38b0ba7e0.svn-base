package com.cabletech.business.workflow.wmaintain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainBaseDao;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainCreateReportService;
import com.cabletech.business.workflow.wmaintain.service.WMaintainResultService;
import com.cabletech.common.base.SysConstant;

/**
 * 填写维修作业计划维护报告服务接口实现
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-16 添加注释部分
 * @author 杨隽 2012-05-04 更改使用公共的表单提交标识
 * @author 杨隽 2012-07-10 修改save()方法以支持隐患现场处理信息的保存
 * 
 */
@Service
@Transactional
public class WMaintainCreateReportServiceImpl extends
		WMaintainBaseServiceImpl<WMaintainPlan, String> implements
		WMaintainCreateReportService {
	// 维修作业计划Dao
	@Resource(name = "WMaintainPlanDao")
	private WMaintainBaseDao<WMaintainPlan, String> wMaintainPlanDao;
	// 站点异常项及处理结果服务
	@Resource(name = "WMaintainResultServiceImpl")
	private WMaintainResultService wMaintainResultService;

	/**
	 * 填写维修作业计划维护报告
	 * 
	 * @param planReport
	 *            WMaintainPlan 维修作业计划维护报告表单数据
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@Override
	public void save(WMaintainPlan planReport, UserInfo userInfo) {
		// TODO Auto-generated method stub
		WMaintainPlan plan = writeReportInfo(planReport, userInfo);
		wMaintainPlanDao.save(plan);
		wMaintainResultService.saveWmaintainProcess(planReport);
		if (SysConstant.FORM_IS_SUBMITED.equals(plan.getIsSubmited())) {
			SmParameter smParameter = getSmParameter(plan);
			super.doWorkflow(plan, smParameter);
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
	 * 补充维修作业计划的表单数据信息
	 * 
	 * @param planReport
	 *            WMaintainPlan 维修作业计划维护报告表单数据
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return WMaintainPlan 维修作业计划信息数据
	 */
	private WMaintainPlan writeReportInfo(WMaintainPlan planReport,
			UserInfo userInfo) {
		WMaintainPlan plan = wMaintainPlanDao.get(planReport.getId());
		plan.setReport(planReport.getReport());
		plan.setAuditor(planReport.getAuditor());
		plan.setCreater(userInfo.getPersonId());
		plan.setWorkflowTransition(SysConstant.PASS_WORKFLOW_TRANSTION);
		plan.setWorkflowComment(planReport.getReport());
		plan.setCurrentProcessUserId(userInfo.getPersonId());
		plan.setNextProcessUserId(planReport.getAuditor());
		plan.setIsSubmited(planReport.getIsSubmited());
		if (SysConstant.FORM_IS_SUBMITED.equals(plan.getIsSubmited())) {
			plan.setPlanState(WMaintainPlan.WMAINTAIN_RECORD_SUBMITED_STATE);
		} else {
			plan.setPlanState(WMaintainPlan.WMAINTAIN_RECORD_NOTSUBMITED_STATE);
		}
		plan.setWorkflowTaskId(planReport.getWorkflowTaskId());
		return plan;
	}

	/**
	 * 获取短信发送数据参数
	 * 
	 * @param plan
	 *            WMaintainPlan 维修作业计划信息数据
	 * @return SmParameter 短信发送数据参数
	 */
	private SmParameter getSmParameter(WMaintainPlan plan) {
		SmParameter smParameter = new SmParameter();
		String simId = super.getUserPhone(plan.getNextProcessUserId());
		String[] contentParameters = new String[] { plan.getPlanName() };
		smParameter.setSimId(simId);
		smParameter.setContentParameters(contentParameters);
		smParameter.setXmlFileId(WMAINTAIN_XML_FILE_ID);
		smParameter.setXmlMessageId(CREATE_REPORT_SEND_MSG_ID);
		return smParameter;
	}
}
