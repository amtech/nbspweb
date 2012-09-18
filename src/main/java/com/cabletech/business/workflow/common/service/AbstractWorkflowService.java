package com.cabletech.business.workflow.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.base.service.SmSendService;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.flowservice.util.WorkFlowServiceClient;
import com.cabletech.business.workflow.common.model.CommonWorkflowResult;
import com.cabletech.common.base.SysConstant;

/**
 * 抽象工作流业务处理类
 * 
 * @author 杨隽 2011-11-07 创建
 * @author 杨隽 2011-11-08 修改获取工作流业务服务的方法
 * @author 杨隽 2011-11-29 添加获取工作流待办任务列表的方法
 * @author 杨隽 2012-01-09 修改包名并添加getProcessHistoryList方法和initMap抽象方法
 * @author 杨隽 2012-01-10 添加getWorkflowTasksTimeLength方法
 * @author 杨隽 2012-01-31 将处理待办列表数量的首页服务方法提取成待办列表数量工作流业务处理类
 * @author 杨隽 2012-02-08 将saveTask()方法改为sendTaskTwoSteps()方法
 * @author 杨隽 2012-02-24 修改doTask()方法以支持转审
 * @author 杨隽 2012-02-24 修改putProcessHistoryMap()方法以支持转审说明的输出
 * @author 杨隽 2012-03-26 集成工作流短信发送功能
 * 
 */
public abstract class AbstractWorkflowService {
	// 空工作流业务实体操作实现KEY
	public static final String EMPTY_WORKFLOW_ENTITY_MANAGER_KEY = "emptyWorkflowEntityManager";
	// 流程处理结果的key与value的Map
	protected Map<String, String> processResultMap = new HashMap<String, String>();
	// 日志服务
	private Logger logger = Logger.getLogger("AbstractWorkflowService");
	// 工作流webservice服务
	@Resource(name = "workFlowServiceClient")
	private WorkFlowServiceClient workflowClient;
	// 工作流业务实体操作实现Map
	@Autowired
	private Map<String, WorkflowEntityManager> businessManagerMap;
	// 短信发送服务
	@Resource(name = "smSendService")
	private SmSendService smSendService;

	/**
	 * 执行提交发送工作流
	 * 
	 * @param taskPi
	 *            ProMockPo 工作流传入参数
	 * @param smParameter
	 *            SmParameter 短信发送参数
	 * @return ProMockPo 工作流返回参数
	 */
	public ProMockPo sendTask(ProMockPo taskPi, SmParameter smParameter) {
		taskPi.setProcessName(getProcessDefName());
		ProMockPo result = workflowClient.startWorkFlow(taskPi);
		sendMessage(smParameter);
		logWorkflowInfo(result);
		return result;
	}

	/**
	 * 执行提交发送工作流（跳过工作流的第一个任务状态）
	 * 
	 * @param taskPi
	 *            ProMockPo 工作流传入参数
	 * @param smParameter
	 *            SmParameter 短信发送参数
	 * @return ProMockPo 工作流返回参数
	 */
	public ProMockPo sendTaskTwoSteps(ProMockPo taskPi, SmParameter smParameter) {
		taskPi.setProcessName(getProcessDefName());
		ProMockPo result = workflowClient.startProcesssignalFirst(taskPi);
		sendMessage(smParameter);
		logWorkflowInfo(result);
		return result;
	}

	/**
	 * 执行转派工作流
	 * 
	 * @param taskPi
	 *            ProMockPo 工作流传入参数
	 * @param smParameter
	 *            SmParameter 短信发送参数
	 * @return ProMockPo 工作流返回参数
	 */
	public ProMockPo transferTask(ProMockPo taskPi, SmParameter smParameter) {
		logger.info("正在进行转派......");
		ProMockPo result = workflowClient.transferTask(taskPi);
		sendMessage(smParameter);
		logWorkflowInfo(result);
		return result;
	}

	/**
	 * 完成工作流的当前任务
	 * 
	 * @param taskPi
	 *            ProMockPo 工作流传入参数
	 * @param smParameter
	 *            SmParameter 短信发送参数
	 * @return ProMockPo 工作流返回参数
	 */
	public ProMockPo doTask(ProMockPo taskPi, SmParameter smParameter) {
		logger.info("正在进行工作流任务......");
		ProMockPo result;
		if (SysConstant.TRANSFER_WORKFLOW_TRANSTION.equals(taskPi
				.getTransition())) {
			result = transferTask(taskPi, smParameter);
		} else {
			result = workflowClient.doExcution(taskPi);
			sendMessage(smParameter);
		}
		logWorkflowInfo(result);
		return result;
	}

	/**
	 * 根据用户编号、用户组编号和流程定义文件id获取待办列表
	 * 
	 * @param taskPi
	 *            ProMockPo 工作流传入参数
	 * @return List<ProMockPo> 待办工作流列表
	 */
	public List<ProMockPo> getWaitHandleList(ProMockPo taskPi) {
		String userId = taskPi.getDealUsers();
		String userGroupsId = taskPi.getDealGroup();
		String processDefName = getProcessDefName();
		taskPi.setProcessName(processDefName);
		List<ProMockPo> list = workflowClient.getActiveByUser(userId,
				userGroupsId, processDefName);
		return list;
	}

	/**
	 * 根据实体业务编号获取实体业务的工作流信息列表
	 * 
	 * @param taskId
	 *            String 实体业务编号
	 * @return List<ProMockPo> 已办工作流列表
	 */
	@SuppressWarnings("unchecked")
	public List<ProMockPo> getHandledProcessList(String taskId) {
		List<ProMockPo> list = workflowClient.getTaskHisByBzid(taskId);
		return list;
	}

	/**
	 * 根据实体编号收回实体业务的工作流
	 * 
	 * @param id
	 *            String 实体编号
	 */
	public void deleteTask(String id) {
		// TODO Auto-generated method stub
		workflowClient.deletePins(id);
	}

	/**
	 * 获取工作流对应的业务操作服务
	 * 
	 * @return WorkflowEntityManager 工作流对应的业务操作服务
	 */
	public WorkflowEntityManager getWorkflowBusinessMananger() {
		String businessManagerKey = getBusinessManagerKey();
		if (StringUtils.isBlank(businessManagerKey)) {
			return businessManagerMap.get(EMPTY_WORKFLOW_ENTITY_MANAGER_KEY);
		}
		if (!businessManagerMap.containsKey(businessManagerKey)) {
			return businessManagerMap.get(EMPTY_WORKFLOW_ENTITY_MANAGER_KEY);
		}
		WorkflowEntityManager businessManager = (WorkflowEntityManager) businessManagerMap
				.get(businessManagerKey);
		return businessManager;
	}

	/**
	 * 获取工作流处理历史MAP
	 * 
	 * @param id
	 *            String 工作流业务实体编号
	 * @return Map<String, List<CommonWorkflowResult>> 工作流处理历史MAP
	 */
	@Transactional(readOnly = true)
	public Map<String, List<CommonWorkflowResult>> getProcessHistoryList(
			String id) {
		// TODO Auto-generated method stub
		initMap();
		Map<String, List<CommonWorkflowResult>> map = new LinkedHashMap<String, List<CommonWorkflowResult>>();
		List<ProMockPo> list = getHandledProcessList(id);
		if (CollectionUtils.isEmpty(list)) {
			return map;
		}
		ProMockPo po;
		for (int i = 0; i < list.size(); i++) {
			po = (ProMockPo) list.get(i);
			if (po == null) {
				continue;
			}
			putProcessHistoryMap(po, map);
		}
		return map;
	}

	/**
	 * 根据工作流业务实体编号获取工作流的流转历时计算结果
	 * 
	 * @param id
	 *            String 工作流业务实体编号
	 * @return Map<String, Object> 工作流的流转历时计算结果
	 */
	public Map<String, Object> getWorkflowTasksTimeLength(String id) {
		return workflowClient.getFlowDurations(id);
	}

	/**
	 * 初始化流程历史说明记录
	 * 
	 */
	public abstract void initMap();

	/**
	 * 获取工作流流程定义文件的id
	 * 
	 * @return String 工作流流程定义文件的id
	 */
	public abstract String getProcessDefName();

	/**
	 * 获取工作流对应的业务操作服务KEY
	 * 
	 * @return String 工作流对应的业务操作服务KEY
	 */
	public abstract String getBusinessManagerKey();

	/**
	 * 根据流程历史步骤信息获取工作流步骤信息
	 * 
	 * @param po
	 *            ProMockPo 工作流传入参数
	 * @param map
	 *            Map<String, List<CommonWorkflowResult>> 存放工作流步骤信息的Map
	 */
	private void putProcessHistoryMap(ProMockPo po,
			Map<String, List<CommonWorkflowResult>> map) {
		// TODO Auto-generated method stub
		List<CommonWorkflowResult> list = new ArrayList<CommonWorkflowResult>();
		if (map.containsKey(po.getTaskName())) {
			list = map.get(po.getTaskName());
		}
		CommonWorkflowResult history = new CommonWorkflowResult();
		String key = po.getTaskName() + po.getTransition();
		String processResult = "";
		if (processResultMap.containsKey(key)) {
			processResult = (String) processResultMap.get(key);
		} else if (StringUtils.isNotBlank(po.getTransition())) {
			processResult = po.getTransition();
			String prefix = "";
			if (processResult.indexOf("(") != -1) {
				prefix = processResult.substring(0, processResult.indexOf("("));
			}
			String suffix = "";
			if (processResult.indexOf(")") != -1) {
				suffix = processResult
						.substring(processResult.indexOf(")") + 1);
			}
			processResult = prefix + suffix;
		}
		history.setProcessResult(processResult);
		history.setProcessComment(po.getComment());
		history.setProcessUser(po.getUserName());
		history.setProcessDate(po.getEndTime());
		list.add(history);
		map.put(po.getTaskName(), list);
	}

	/**
	 * 发送短信
	 * 
	 * @param smParameter
	 *            SmParameter 短信发送参数
	 */
	private void sendMessage(SmParameter smParameter) {
		if (!SmParameter.isNotEmpty(smParameter)) {
			return;
		}
		if (smParameter.isWrittenDb()) {
			smSendService.writeMessage(smParameter);
		}
		smSendService.sendMessageToJms(smParameter);
	}

	/**
	 * 日志输出工作流信息
	 * 
	 * @param result
	 *            ProMockPo 工作流业务实体
	 */
	private void logWorkflowInfo(ProMockPo result) {
		logger.info("流程实例编号:" + result.getPiId());
		if (StringUtils.isNotBlank(result.getTaskName())) {
			logger.info("进入" + result.getTaskName() + "状态");
		}
	}
}
