package com.cabletech.business.contactletter.service;

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
 * 业务联系函工作流待办列表数量业务处理
 * 
 * @author 杨隽 2012-04-18 创建
 * 
 */
@Service
public class ContactLetterWorkflowWaitHandledService extends
		AbstractWorkflowWaitHandledService {
	// 业务联系函工作流业务处理
	@Resource(name = "contactLetterAuditService")
	private ContactLetterAuditService contactLetterAuditService;

	/**
	 * 获取工作流流程定义文件的id
	 */
	@Override
	public String getProcessDefName() {
		// TODO Auto-generated method stub
		return ContactLetterAuditService.WORKFLOW_NAME;
	}

	/**
	 * 获取工作流流程定义文件的中文流程说明
	 */
	@Override
	public String getProcessDefineName() {
		// TODO Auto-generated method stub
		return ContactLetterAuditService.WORKFLOW_COMMENT;
	}

	/**
	 * 获取工作流对应的业务操作服务KEY
	 */
	@Override
	public String getBusinessManagerKey() {
		// TODO Auto-generated method stub
		return ContactLetterAuditService.SERVICE_INST_NAME;
	}

	@Override
	public void setTaskPi(UserInfo userInfo, ProMockPo taskPi) {
		// TODO Auto-generated method stub
		String userId = userInfo.getPersonId();
		taskPi.setDealUsers(userId);
	}

	@Override
	public AbstractWorkflowService getWorkflowService() {
		// TODO Auto-generated method stub
		return contactLetterAuditService;
	} 
	
	@Override
	public void setBusinessTypeList(List<Map<String, Object>> businessTypeList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConstant.DICTIONARY_KEY_COLUMN, "C23");
		map.put(SysConstant.DICTIONARY_VALUE_COLUMN, "业务联系函");
		list.add(map);
		super.businessTypeList = list;
	}
}
