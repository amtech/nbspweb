package com.cabletech.business.workflow.workorder.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.common.service.AbstractWorkflowService;
import com.cabletech.business.workflow.common.service.AbstractWorkflowWaitHandledService;
import com.cabletech.common.base.SysConstant;

/**
 * 通用工单工作流待办列表数量业务处理
 * 
 * @author 杨隽 2012-01-31 创建
 * @author 杨隽 2012-02-09 实现setTaskPi()方法和setBusinessTypeList()方法
 * 
 */
@Service
public class WorkOrderWorkflowWaitHandledService extends
		AbstractWorkflowWaitHandledService {
	// 通用工单工作流业务处理
	@Resource(name = "workOrderWorkflowService")
	private WorkOrderWorkflowService workOrderWorkflowService;

	/**
	 * 获取通用工单工作流流程定义文件的id
	 * 
	 * @return String 通用工单工作流流程定义文件的id
	 */
	@Override
	public String getProcessDefName() {
		// TODO Auto-generated method stub
		return WorkOrderWorkflowService.WORKORDER_WORKFLOW_NAME;
	}

	/**
	 * 获取通用工单工作流流程定义文件的中文流程说明
	 * 
	 * @return String 通用工单工作流流程定义文件的中文流程说明
	 */
	@Override
	public String getProcessDefineName() {
		// TODO Auto-generated method stub
		return WorkOrderWorkflowService.WORKORDER_WORKFLOW_COMMENT;
	}

	/**
	 * 获取通用工单工作流对应的业务操作服务KEY
	 * 
	 * @return String 通用工单工作流对应的业务操作服务KEY
	 */
	@Override
	public String getBusinessManagerKey() {
		// TODO Auto-generated method stub
		return WorkOrderWorkflowService.WORKORDER_SERVICE_INST_NAME;
	}

	/**
	 * 设置通用工单待办任务的用户条件
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
		taskPi.setDealUsers(userId);
	}

	/**
	 * 获取通用工单工作流业务处理类
	 * 
	 * @return AbstractWorkflowService 通用工单工作流业务处理类
	 */
	@Override
	public AbstractWorkflowService getWorkflowService() {
		// TODO Auto-generated method stub
		return workOrderWorkflowService;
	}

	/**
	 * 设置通用工单资源专业类型列表
	 * 
	 * @param businessTypeList
	 *            List<Map<String, Object>> 通用工单资源专业类型列表
	 */
	@Override
	public void setBusinessTypeList(List<Map<String, Object>> businessTypeList) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConstant.DICTIONARY_KEY_COLUMN,
				SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C20);
		map.put(SysConstant.DICTIONARY_VALUE_COLUMN, "通用工单");
		list.add(map);
		super.businessTypeList = new ArrayList<Map<String, Object>>();
		super.businessTypeList.addAll(businessTypeList);
		super.businessTypeList.addAll(list);
	}
}
