package com.cabletech.business.workflow.electricity.security.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.common.service.AbstractWorkflowService;
import com.cabletech.business.workflow.common.service.AbstractWorkflowWaitHandledService;

/**
 * 断电告警派单工作流待办列表数量业务处理
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
@Service
public class ElectricitySecurityWorkflowWaitHandledService extends
		AbstractWorkflowWaitHandledService {
	// 断电告警工作流业务处理
	@Resource(name = "electricitySecurityWorkflowService")
	private ElectricitySecurityWorkflowService electricitySecurityWorkflowService;

	/**
	 * 获取断电告警工作流流程定义文件的id
	 * 
	 * @return String 断电告警工作流流程定义文件的id
	 */
	@Override
	public String getProcessDefName() {
		// TODO Auto-generated method stub
		return ElectricitySecurityWorkflowService.OE_WORKFLOW_NAME;
	}

	/**
	 * 获取断电告警工作流流程定义文件的中文流程说明
	 * 
	 * @return String 断电告警工作流流程定义文件的中文流程说明
	 */
	@Override
	public String getProcessDefineName() {
		// TODO Auto-generated method stub
		return ElectricitySecurityWorkflowService.OE_WORKFLOW_COMMENT;
	}

	/**
	 * 获取断电告警工作流对应的业务操作服务KEY
	 * 
	 * @return String 断电告警工作流对应的业务操作服务KEY
	 */
	@Override
	public String getBusinessManagerKey() {
		// TODO Auto-generated method stub
		return ElectricitySecurityWorkflowService.OE_DISPATCH_SERVICE_INST_NAME;
	}

	/**
	 * 设置断电告警待办任务的用户条件
	 * 
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @param taskPi
	 *            ProMockPo 工作流传入参数
	 */
	@Override
	public void setTaskPi(UserInfo userInfo, ProMockPo taskPi) {
		// TODO Auto-generated method stub
		String userId = userInfo.getPersonId();
		String orgId = userInfo.getOrgId();
		taskPi.setDealGroup(orgId);
		taskPi.setDealUsers(userId);
	}

	/**
	 * 获取断电告警工作流业务处理类
	 * 
	 * @return AbstractWorkflowService 断电告警工作流业务处理类
	 */
	@Override
	public AbstractWorkflowService getWorkflowService() {
		// TODO Auto-generated method stub
		return electricitySecurityWorkflowService;
	}

	/**
	 * 设置断电告警资源专业类型列表
	 * 
	 * @param businessTypeList
	 *            List<Map<String, Object>> 断电告警资源专业类型列表
	 */
	@Override
	public void setBusinessTypeList(List<Map<String, Object>> businessTypeList) {
		// TODO Auto-generated method stub
		super.businessTypeList = businessTypeList;
	}
}
