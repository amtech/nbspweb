package com.cabletech.business.workflow.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.contactletter.service.ContactLetterAuditService;
import com.cabletech.business.desktop.service.impl.DesktopServiceImpl;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.electricity.security.service.ElectricitySecurityWorkflowService;
import com.cabletech.business.workflow.fault.service.FaultWorkflowService;
import com.cabletech.business.workflow.workorder.service.WorkOrderWorkflowService;
import com.cabletech.business.wplan.plan.service.PatrolWorkflowService;
import com.cabletech.common.base.SysConstant;

/**
 * 抽象工作流待办列表数量业务处理类
 * 
 * @author 杨隽 2012-01-31 创建
 * @author 杨隽 2012-02-09 添加setBusinessTypeList()抽象方法
 * @author 杨隽 2012-04-27
 *         添加existBusinessTypeInMap()方法并修改processWaitHandledListNumber()方法
 * 
 */
public abstract class AbstractWorkflowWaitHandledService {
	// 空用户字符串
	public static final String EMPTY_USER = "";
	// 资源专业类型列表
	protected List<Map<String, Object>> businessTypeList;

	public List<Map<String, Object>> getBusinessTypeList() {
		return businessTypeList;
	}

	/**
	 * 获取一个工作流的待办工作信息列表
	 * 
	 * @param businessTypeList
	 *            专业类型列表
	 * @param userInfo
	 *            当前用户信息
	 * @return List<Map<String, Object>> 一个工作流的待办工作信息列表
	 */
	public List<Map<String, Object>> getOneWorkflowHandledTaskList(
			List<Map<String, Object>> businessTypeList, UserInfo userInfo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (CollectionUtils.isEmpty(businessTypeList)) {
			return list;
		}
		// 从工作流待办任务列表中获取待办工作列表
		List<ProMockPo> waitHandledTaskList = getProcessHandledTaskList(userInfo);
		// 将工作流待办任务列表和业务数据获取列表进行合并
		WorkflowEntityManager workflowEntityService = getWorkflowService()
				.getWorkflowBusinessMananger();
		workflowEntityService.setWaitHandledTaskList(waitHandledTaskList);
		for (int i = 0; i < businessTypeList.size(); i++) {
			Map<String, Object> businessTypeMap = businessTypeList.get(i);
			workflowEntityService.setBusinessTypeMap(businessTypeMap);
			List<Map<String, Object>> resultList = getOneBusinessTypeHandledTaskListInOneWorkflow(workflowEntityService);
			list.addAll(resultList);
		}
		return list;
	}

	/**
	 * 获取一个工作流的待办工作信息列表数量
	 * 
	 * @param userInfo
	 *            当前用户信息
	 * @param map
	 *            Map<String, Object> 一个工作流的待办工作信息列表数量Map
	 */
	public void getOneWorkflowHandledTaskNumber(UserInfo userInfo,
			Map<String, Object> map) {
		// 从工作流待办任务列表中获取待办工作列表
		List<ProMockPo> waitHandledTaskList = getProcessHandledTaskList(userInfo);
		getWorkflowService().getWorkflowBusinessMananger()
				.setWaitHandledTaskList(waitHandledTaskList);
		for (int j = 0; j < businessTypeList.size(); j++) {
			Map<String, Object> businessTypeMap = businessTypeList.get(j);
			getWorkflowService().getWorkflowBusinessMananger()
					.setBusinessTypeMap(businessTypeMap);
			getOneBusinessTypeHandledTaskNumberInOneWorkflow(map);
		}
	}

	/**
	 * 从工作流待办任务列表中获取待办工作列表
	 * 
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return List<ProMockPo> 待办工作列表
	 */
	public List<ProMockPo> getProcessHandledTaskList(UserInfo userInfo) {
		ProMockPo taskPi = new ProMockPo();
		setTaskPi(userInfo, taskPi);
		List<ProMockPo> waitHandledTaskList = getWorkflowService()
				.getWaitHandleList(taskPi);
		return waitHandledTaskList;
	}

	/**
	 * 获取一个专业下的一个工作流待办工作信息列表
	 * 
	 * @param workflowEntityService
	 *            WorkflowEntityManager 工作流业务实体服务
	 * @return List<Map<String, Object>> 一个专业下的一个工作流待办工作信息列表
	 */
	public List<Map<String, Object>> getOneBusinessTypeHandledTaskListInOneWorkflow(
			WorkflowEntityManager workflowEntityService) {
		List<Map<String, Object>> resultList = getWaitHandledTaskResultList(workflowEntityService);
		processWaitHandledList(workflowEntityService, resultList);
		return resultList;
	}

	/**
	 * 获取一个专业下的一个工作流待办工作信息数量
	 * 
	 * @param resultMap
	 *            Map<String, Object> 存放一个专业下的一个工作流待办工作信息数量的Map
	 */
	public void getOneBusinessTypeHandledTaskNumberInOneWorkflow(
			Map<String, Object> resultMap) {
		WorkflowEntityManager workflowEntityService = getWorkflowService()
				.getWorkflowBusinessMananger();
		// 获取专业类型列表
		List<Map<String, Object>> resultList = getWaitHandledTaskResultList(workflowEntityService);
		if (CollectionUtils.isEmpty(resultList)) {
			return;
		}
		processWaitHandledListNumber(workflowEntityService, resultMap,
				resultList);
	}

	/**
	 * 获取具体的工作流业务处理类
	 * 
	 * @return AbstractWorkflowService 具体的工作流业务处理类
	 */
	public abstract AbstractWorkflowService getWorkflowService();

	/**
	 * 获取工作流流程定义文件的id
	 * 
	 * @return String 工作流流程定义文件的id
	 */
	public abstract String getProcessDefName();

	/**
	 * 获取工作流流程定义文件的中文流程说明
	 * 
	 * @return String 工作流流程定义文件的中文流程说明
	 */
	public abstract String getProcessDefineName();

	/**
	 * 获取工作流对应的业务操作服务KEY
	 * 
	 * @return String 工作流对应的业务操作服务KEY
	 */
	public abstract String getBusinessManagerKey();

	/**
	 * 设置待办任务的用户条件
	 * 
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @param taskPi
	 *            ProMockPo 工作流传入参数
	 */
	public abstract void setTaskPi(UserInfo userInfo, ProMockPo taskPi);

	/**
	 * 设置资源专业类型列表
	 * 
	 * @param businessTypeList
	 *            List<Map<String, Object>> 资源专业类型列表
	 */
	public abstract void setBusinessTypeList(
			List<Map<String, Object>> businessTypeList);

	/**
	 * 根据专业类型获取专业对应的菜单名称
	 * 
	 * @param businessType
	 *            String 专业类型
	 * @return String 专业对应的菜单名称
	 */
	public static String getMapKey(String businessType) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C20, "通用模块");
		map.put(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C30, "传输线路维护");
		map.put(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C31, "基站维护");
		map.put(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C32, "综合覆盖维护");
		map.put(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C33, "铁塔维护");
		map.put(SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C34, "集客家客维护");
		return map.get(businessType);
	}

	/**
	 * 根据业务关键字获取工作流类型
	 * 
	 * @param businessKey
	 *            String
	 * @return String
	 */
	public String getWorkflowType(String businessKey) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(ElectricitySecurityWorkflowService.OE_WORKFLOW_NAME, "task");
		map.put(FaultWorkflowService.FAULT_WORKFLOW_NAME, "task");
		map.put(WorkOrderWorkflowService.WORKORDER_WORKFLOW_NAME, "task");
		map.put(PatrolWorkflowService.PATROL_WORKFLOW_NAME, "plan");
		map.put(ContactLetterAuditService.WORKFLOW_NAME, "letter");
		return map.get(businessKey);
	}

	/**
	 * 获取一个专业下的一个工作流待办工作列表
	 * 
	 * @param workflowEntityService
	 *            WorkflowEntityManager 工作流业务实体服务
	 * @return List<Map<String, Object>> 一个专业下的一个工作流待办工作列表
	 */
	private List<Map<String, Object>> getWaitHandledTaskResultList(
			WorkflowEntityManager workflowEntityService) {
		// 获取专业类型列表
		Map<String, Object> businessTypeMap = workflowEntityService
				.getBusinessTypeMap();
		if (MapUtils.isEmpty(businessTypeMap)) {
			return new ArrayList<Map<String, Object>>();
		}
		String businessType = (String) businessTypeMap
				.get(SysConstant.DICTIONARY_KEY_COLUMN);
		// 从工作流获取待办任务列表
		List<ProMockPo> waitHandledTaskList = workflowEntityService
				.getWaitHandledTaskList();
		// 获取业务数据列表
		List<Map<String, Object>> businessDataList = workflowEntityService
				.getBusinessDataList(businessType);
		// 进行待办任务列表和业务数据列表的合并
		List<Map<String, Object>> resultList = workflowEntityService.mergeList(
				waitHandledTaskList, businessDataList, getProcessDefName(),
				getProcessDefineName());
		return resultList;
	}

	/**
	 * 工作流待办工作列表数据后期处理（存放专业类型和访问地址）
	 * 
	 * @param workflowEntityService
	 *            WorkflowEntityManager 工作流业务实体服务
	 * @param resultList
	 *            List<Map<String, Object>> 存放工作流待办工作列表的临时数据缓冲区
	 */
	private void processWaitHandledList(
			WorkflowEntityManager workflowEntityService,
			List<Map<String, Object>> resultList) {
		// 获取专业类型列表
		Map<String, Object> businessTypeMap = workflowEntityService
				.getBusinessTypeMap();
		if (MapUtils.isEmpty(businessTypeMap)) {
			return;
		}
		if (CollectionUtils.isEmpty(resultList)) {
			return;
		}
		String businessTypeName = (String) businessTypeMap
				.get(SysConstant.DICTIONARY_VALUE_COLUMN);
		for (int i = 0; i < resultList.size(); i++) {
			resultList.get(i).put(SysConstant.BUSINESSTYPE_KEY,
					businessTypeName);
			resultList.get(i).put(DesktopServiceImpl.URL_KEY,
					workflowEntityService.getAccessUrl());
		}
	}

	/**
	 * 工作流待办工作列表数量数据后期处理
	 * 
	 * @param workflowEntityService
	 *            WorkflowEntityManager 工作流业务实体服务
	 * @param resultMap
	 *            Map<String, Object> 存放待办工作列表数量数据的Map
	 * @param resultList
	 *            List<Map<String, Object>> 存放工作流待办工作列表的临时数据缓冲区
	 */
	private void processWaitHandledListNumber(
			WorkflowEntityManager workflowEntityService,
			Map<String, Object> resultMap, List<Map<String, Object>> resultList) {
		Map<String, Object> businessTypeMap = workflowEntityService
				.getBusinessTypeMap();
		if (MapUtils.isEmpty(businessTypeMap)) {
			return;
		}
		String businessType = (String) businessTypeMap
				.get(SysConstant.DICTIONARY_KEY_COLUMN);
		String businessTypeName = (String) businessTypeMap
				.get(SysConstant.DICTIONARY_VALUE_COLUMN);
		Map<String, Object> map = getBusinessTypeValueMap(resultMap,
				businessType);
		Map<String, Object> numberMap = new HashMap<String, Object>();
		numberMap.put(DesktopServiceImpl.URL_KEY,
				workflowEntityService.getAccessUrl() + businessType);
		numberMap.put(DesktopServiceImpl.WAIT_HANDLED_NUMBER,
				Integer.toString(resultList.size()));
		map.put(getProcessDefineName(), numberMap);
		map.put(DesktopServiceImpl.BUSINESS_TYPE_NAME, businessTypeName);
		map.put(DesktopServiceImpl.BUSINESS_TYPE, businessType);
		resultMap
				.put(AbstractWorkflowWaitHandledService.getMapKey(businessType),
						map);
	}

	/**
	 * 判断结果map中是否包括了某专业类型的数据
	 * 
	 * @param resultMap
	 *            Map<String, Object> 结果map
	 * @param businessType
	 *            String 某专业类型
	 * @return Map<String, Object> 某专业类型的结果map数据
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getBusinessTypeValueMap(
			Map<String, Object> resultMap, String businessType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (MapUtils.isEmpty(resultMap)) {
			return map;
		}
		if (StringUtils.isBlank(businessType)) {
			return map;
		}
		Set<String> keySet = resultMap.keySet();
		Iterator<String> keyIt = keySet.iterator();
		while (keyIt.hasNext()) {
			String key = keyIt.next();
			Map<String, Object> valueMap = (Map<String, Object>) resultMap
					.get(key);
			if (MapUtils.isEmpty(valueMap)) {
				continue;
			}
			if (businessType.equals(valueMap
					.get(DesktopServiceImpl.BUSINESS_TYPE))) {
				return valueMap;
			}
		}
		return map;
	}
}
