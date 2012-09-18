package com.cabletech.business.wplan.plan.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.resource.dao.ResourceInfoDao;
import com.cabletech.business.wplan.plan.dao.PatrolinfoDao;
import com.cabletech.business.wplan.plan.model.PatrolApprove;
import com.cabletech.business.wplan.plan.model.PatrolResource;
import com.cabletech.business.wplan.plan.model.PatrolTemplate;
import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.business.wplan.plan.service.PatrolApproveService;
import com.cabletech.business.wplan.plan.service.PatrolResourceService;
import com.cabletech.business.wplan.plan.service.PatrolTemplateService;
import com.cabletech.business.wplan.plan.service.PatrolWorkflowService;
import com.cabletech.business.wplan.plan.service.PatrolinfoService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 巡检计划服务
 * @author zhaobi
 * @author 杨隽 2012-07-25 在save()方法中添加维护资源数量的保存
 */
@Service
@Transactional
public class PatrolinfoServiceImpl extends BaseServiceImpl<Patrolinfo, String>
		implements PatrolinfoService {
	/**
	 * 资源Dao
	 */
	@Resource(name="resourceInfoDao")
	private ResourceInfoDao resourceDao;
	/**
	 * 巡检计划信息DAO
	 */
	@Resource(name = "patrolinfoDao")
	private PatrolinfoDao patrolinfoDao;
	/**
	 * 计划资源服务
	 */
	@Resource(name = "patrolResourceServiceImpl")
	private PatrolResourceService patrolResourceService;
	/**
	 * 计划模板服务
	 */
	@Resource(name = "patrolTemplateServiceImpl")
	private PatrolTemplateService patrolTemplateService;
	/**
	 * 计划审批服务
	 */
	@Resource(name = "patrolApproveServiceImpl")
	private PatrolApproveService patrolApproveService;
	

	/**
	 * 巡检工作流服务
	 */
	@Resource(name = "patrolWorkflowService")
	private PatrolWorkflowService patrolWorkflowService;
	
	// 用户信息业务处理
	@Resource(name = "userInfoServiceImpl")
	private UserInfoService userInfoService;
	
	@Override
	@Transactional
	public void delete(String planid) {
		// TODO Auto-generated method stub
		patrolinfoDao.delete(planid);
		patrolResourceService.deleteResource(planid);
		patrolTemplateService.deleteTemplate(planid);
		patrolApproveService.deleteApprove(planid);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String,Object> view(String id) {
		// TODO Auto-generated method stub
		return patrolinfoDao.queryPatrolinfoByID(id);
	}

	/**
	 * 保存
	 * @param patrolinfo 
	 */
	@Transactional
	public void save(Patrolinfo patrolinfo){
		patrolinfo.setMaintainResourcesNum(resourceDao
				.getResourceNum(patrolinfo.getPatrolgroupid(),patrolinfo.getBusinesstype()));
		if (SysConstant.FORM_IS_SUBMITED.equals(patrolinfo.getIssubmited())) {
			patrolinfo.setPlanstate(SysConstant.WAIT_AUDITING_STATE);
			patrolinfoDao.save(patrolinfo);
			SmParameter parameter = getSmParameter(patrolinfo);
			doWorkflow(patrolinfo,parameter);
		} else {
			patrolinfo.setPlanstate(SysConstant.WAIT_SUBMIT_STATE);
			patrolinfoDao.save(patrolinfo);
		}
		//保存计划模板关系
		savetemplate(patrolinfo);
		//保存计划资源关系
		saveresource(patrolinfo);
		//保存计划审批人关系
		saveapprove(patrolinfo);
	}
	@Override
	protected BaseDao<Patrolinfo, String> getBaseDao() {
		// TODO Auto-generated method stub
		return patrolinfoDao;
	}

	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolinfoService#savetemplate(com.cabletech.business.wplan.plan.model.Patrolinfo)
	 */
	@Override
	@Transactional
	public void savetemplate(Patrolinfo patrolinfo) {
		PatrolTemplate patrolTemplate=new PatrolTemplate();
		patrolTemplate.setPlanid(patrolinfo.getId());
		patrolTemplate.setTemplateid(patrolinfo.getPlantemplate());
		patrolTemplateService.save(patrolTemplate);
		
	}

	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolinfoService#saveresource(com.cabletech.business.wplan.plan.model.Patrolinfo)
	 */
	@Override
	@Transactional
	public void saveresource(Patrolinfo patrolinfo) {
		//删除计划巡检资源
		patrolResourceService.deleteResource(patrolinfo.getId());
		if (StringUtils.isNotBlank(patrolinfo.getResourceids())
				&& StringUtils.isNotBlank(patrolinfo.getResourcetypes())) {
			String[] rsIds = patrolinfo.getResourceids().split(",");
			String[] rsTypes = patrolinfo.getResourcetypes().split(",");
			for (int i = 0; i < rsIds.length; i++) {
				PatrolResource patrolresource=new PatrolResource();
				patrolresource.setPlanid(patrolinfo.getId());
				patrolresource.setResourceid(rsIds[i]);
				patrolresource.setResourcetype(rsTypes[i]);
				patrolResourceService.save(patrolresource);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.cabletech.business.wplan.plan.service.PatrolinfoService#saveapprove(com.cabletech.business.wplan.plan.model.Patrolinfo)
	 */
	@Override
	public void saveapprove(Patrolinfo patrolinfo) {
		//删除巡检审批人信息
		patrolApproveService.deleteApprove(patrolinfo.getId());
		PatrolApprove patrolapprove=new PatrolApprove();
		patrolapprove.setApprover(patrolinfo.getApprover());
		patrolapprove.setPlanid(patrolinfo.getId());
		patrolapprove.setResult(patrolinfo.getApprovername());
		patrolApproveService.save(patrolapprove);
		
	}

	/**
	 * 获取发送短信的传递参数
	 * @param patrolinfo 
	 */
	private SmParameter getSmParameter(Patrolinfo patrolinfo) {
		// TODO Auto-generated method stub
		String simId = getUserPhone(patrolinfo.getApprover());
		String[] contentParameters = new String[] {patrolinfo.getPlanname() };
		SmParameter parameter = SmParameter.getInstance("wplan",
				"submit_wplan", simId, contentParameters);
		return parameter;
	}
	
	/**
	 * 根据用户编号获取用户联系电话
	 * 
	 * @param userId 
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
	 * @param smParameter SmParameter 短信发送参数
	 * @param patrolinfo Patrolinfo 巡检计划信息
	 */
	@Transactional
	private void doWorkflow(Patrolinfo patrolinfo, SmParameter smParameter) {
		ProMockPo taskPi = new ProMockPo();
		taskPi.setBzid(patrolinfo.getId());
		taskPi.setUserId(patrolinfo.getCreater());
		taskPi.setUserName(patrolinfo.getCreatername());
		taskPi.setDealUsers(patrolinfo.getApprover());
		taskPi.setDealUsersName(patrolinfo.getApprovername());
		patrolWorkflowService.sendTaskTwoSteps(taskPi,smParameter);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly=true)
	public Page listByPage(Patrolinfo patrolinfo,Page page) {
		return patrolinfoDao.queryPatrolinfo(patrolinfo, page);
	}
	
	/**
	 * 查询待办工作列表
	 * @param patrolinfo 
	 * @param page 
	 * @param user 
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=true)
	public Page queryWaithHandledList(Patrolinfo patrolinfo,Page page,UserInfo user){		
		QueryParameter parameter=new  QueryParameter();
		parameter.setAlias("jbpm");
		parameter.setUser(user);
		return patrolinfoDao.queryWaithHandledList(patrolinfo,page,parameter);
	}

}
