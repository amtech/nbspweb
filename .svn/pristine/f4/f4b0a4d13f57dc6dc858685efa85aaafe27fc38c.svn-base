package com.cabletech.business.wplan.plan.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.wplan.plan.dao.PatrolApproveDao;
import com.cabletech.business.wplan.plan.dao.PatrolinfoDao;
import com.cabletech.business.wplan.plan.model.PatrolApprove;
import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.business.wplan.plan.service.PatrolApproveService;
import com.cabletech.business.wplan.plan.service.PatrolWorkflowService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.base.SysConstant;

/**
 * 巡检审批服务实现
 * 
 * @author zhaobi
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 */
@Service
@Transactional
public class PatrolApproveServiceImpl extends
		BaseServiceImpl<PatrolApprove, String> implements PatrolApproveService {
	/**
	 * 计划审批Dao
	 */
	@Resource(name = "patrolApproveDao")
	private PatrolApproveDao patrolApproveDao;
	/**
	 * 巡检工作流服务
	 */
	@Resource(name = "patrolWorkflowService")
	private PatrolWorkflowService patrolWorkflowService;
	/**
	 * 巡检计划服务;
	 */
	@Resource(name = "patrolinfoDao")
	private PatrolinfoDao patrolinfoDao;

	// 用户信息业务处理
	@Resource(name = "userInfoServiceImpl")
	private UserInfoService userInfoService;

	@Override
	@Transactional
	public void deleteApprove(String planid) {
		patrolApproveDao.deleteApprove(planid);

	}

	@Override
	protected BaseDao<PatrolApprove, String> getBaseDao() {
		// TODO Auto-generated method stub
		return patrolApproveDao;
	}

	/*
	 * 审批 (non-Javadoc)
	 * 
	 * @see
	 * com.cabletech.business.wplan.plan.service.PatrolApproveService#audit(
	 * com.cabletech.business.wplan.plan.model.PatrolApprove,
	 * com.cabletech.business.base.model.UserInfo)
	 */
	@Override
	@Transactional
	public void audit(PatrolApprove patrolapprove, UserInfo userinfo) {
		// TODO Auto-generated method stub
		Patrolinfo patrolinfo = patrolinfoDao.get(patrolapprove.getPlanid());
		SmParameter parameter = getSmParameter(patrolinfo, patrolapprove);
		// 执行工作流业务
		ProMockPo pro = doWorkflow(patrolapprove, userinfo, parameter);
		// 审核通过
		if (pro.isFlowOver()) {
			patrolinfo.setPlanstate(SysConstant.PASSED_STATE);
		} else {
			// 审核不通过
			patrolinfo.setPlanstate(SysConstant.NOT_PASSED_STATE);
		}
		patrolinfoDao.save(patrolinfo);
	}

	/**
	 * 获取发送短信的传递参数
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @param patrolapprove
	 *            PatrolApprove
	 * @return
	 */
	private SmParameter getSmParameter(Patrolinfo patrolinfo,
			PatrolApprove patrolapprove) {
		// TODO Auto-generated method stub
		String simId = getUserPhone(patrolinfo.getCreater());
		String msgId = "";
		String[] contentParameters = new String[] { patrolinfo.getPlanname() };
		if (SysConstant.PASS_WORKFLOW_TRANSTION.equals(patrolapprove
				.getResult())) {
			msgId = "approve_passed";
		} else {
			msgId = "approve_not_passed";
		}
		SmParameter parameter = SmParameter.getInstance("wplan", msgId, simId,
				contentParameters);
		return parameter;
	}

	/**
	 * 根据用户编号获取用户联系电话
	 * 
	 * @param userId
	 *            String
	 * @return
	 */
	private String getUserPhone(String userId) {
		// TODO Auto-generated method stub
		String phone = "";
		UserInfo user = userInfoService.getUserInfoByPersonId(userId);
		if (user != null) {
			phone = user.getPhone();
		}
		return phone;
	}

	/**
	 * 执行计划工作流
	 * 
	 * @param smParameter
	 *            SmParameter 短信发送参数
	 * @param userinfo
	 *            UserInfo 当前登录用户信息
	 * @param patrolapprove
	 *            PatrolApprove 巡检计划审核信息
	 */
	@Transactional
	private ProMockPo doWorkflow(PatrolApprove patrolapprove,
			UserInfo userinfo, SmParameter smParameter) {
		ProMockPo taskPi = new ProMockPo();
		// 工作流任务ID
		taskPi.setTaskId(patrolapprove.getId());
		taskPi.setBzid(patrolapprove.getPlanid());
		taskPi.setComment(patrolapprove.getRemark());
		taskPi.setUserId(userinfo.getPersonId());
		taskPi.setUserName(userinfo.getUserName());
		taskPi.setTransition(patrolapprove.getResult());
		return patrolWorkflowService.doTask(taskPi, smParameter);
	}

}
