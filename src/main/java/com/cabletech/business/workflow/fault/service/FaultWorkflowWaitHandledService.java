package com.cabletech.business.workflow.fault.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.common.service.AbstractWorkflowService;
import com.cabletech.business.workflow.common.service.AbstractWorkflowWaitHandledService;

/**
 * 故障派单工作流待办列表数量业务处理
 * 
 * @author 杨隽 2012-02-07 创建
 * @author 杨隽 2012-02-09 实现setTaskPi()方法和setBusinessTypeList()方法
 * 
 */
@Service
public class FaultWorkflowWaitHandledService extends
		AbstractWorkflowWaitHandledService {
	// 故障工作流业务处理
	@Resource(name = "faultWorkflowService")
	private FaultWorkflowService faultWorkflowService;

	/**
	 * 获取故障工作流流程定义文件的id
	 * 
	 * @return String 故障工作流流程定义文件的id
	 */
	@Override
	public String getProcessDefName() {
		// TODO Auto-generated method stub
		return FaultWorkflowService.FAULT_WORKFLOW_NAME;
	}

	/**
	 * 获取故障工作流流程定义文件的中文流程说明
	 * 
	 * @return String 故障工作流流程定义文件的中文流程说明
	 */
	@Override
	public String getProcessDefineName() {
		// TODO Auto-generated method stub
		return FaultWorkflowService.FAULT_WORKFLOW_COMMENT;
	}

	/**
	 * 获取故障工作流对应的业务操作服务KEY
	 * 
	 * @return String 故障工作流对应的业务操作服务KEY
	 */
	@Override
	public String getBusinessManagerKey() {
		// TODO Auto-generated method stub
		return FaultWorkflowService.FAULT_DISPATCH_SERVICE_INST_NAME;
	}

	/**
	 * 设置故障待办任务的用户条件
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
	 * 获取故障工作流业务处理类
	 * 
	 * @return AbstractWorkflowService 故障工作流业务处理类
	 */
	@Override
	public AbstractWorkflowService getWorkflowService() {
		// TODO Auto-generated method stub
		return faultWorkflowService;
	}

	/**
	 * 设置故障资源专业类型列表
	 * 
	 * @param businessTypeList
	 *            List<Map<String, Object>> 故障资源专业类型列表
	 */
	@Override
	public void setBusinessTypeList(List<Map<String, Object>> businessTypeList) {
		// TODO Auto-generated method stub
		super.businessTypeList = businessTypeList;
	}
}
