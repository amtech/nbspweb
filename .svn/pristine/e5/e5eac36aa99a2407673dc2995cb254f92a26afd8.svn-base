package com.cabletech.business.webservice.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.cabletech.baseinfo.base.DateUtil;
import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.webservice.interfaces.ExternalWebService;
import com.cabletech.business.webservice.service.BusinessService;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderRefuseConfirm;
import com.cabletech.business.workflow.workorder.model.WorkOrderReply;
import com.cabletech.business.workflow.workorder.model.WorkOrderReplyCheck;
import com.cabletech.business.workflow.workorder.model.WorkOrderSignFor;
import com.cabletech.business.workflow.workorder.model.WorkOrderTransfer;
import com.cabletech.business.workflow.workorder.service.WorkOrderDispatchService;
import com.cabletech.business.workflow.workorder.service.WorkOrderHandledService;
import com.cabletech.business.workflow.workorder.service.WorkOrderRefuseConfirmService;
import com.cabletech.business.workflow.workorder.service.WorkOrderReplyCheckService;
import com.cabletech.business.workflow.workorder.service.WorkOrderReplyService;
import com.cabletech.business.workflow.workorder.service.WorkOrderSignForService;
import com.cabletech.business.workflow.workorder.service.WorkOrderTransferService;
import com.cabletech.business.workflow.workorder.service.WorkOrderWaitHandledService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;
import com.cabletech.common.util.ReflectionUtils;
import com.cabletech.common.xmlparse.ParseXmlTools;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 业务处理方法接口（工单业务处理服务）
 * 
 * @author 杨隽 2012-08-16 创建
 * 
 */
@Component
public class WorkOrderWebServiceImpl implements BusinessService {
	/**
	 * 日志输出
	 */
	private Logger logger = Logger.getLogger("WorkOrderWebServiceImpl");
	/**
	 * 用户信息服务
	 */
	@Resource(name = "userInfoServiceImpl")
	private UserInfoService userInfoService;
	/**
	 * 工单待办服务
	 */
	@Resource(name = "workOrderWaitHandledServiceImpl")
	private WorkOrderWaitHandledService workOrderWaitHandledService;
	/**
	 * 工单已办服务
	 */
	@Resource(name = "workOrderHandledServiceImpl")
	private WorkOrderHandledService workOrderHandledService;
	/**
	 * 工单信息服务
	 */
	@Resource(name = "workOrderDispatchServiceImpl")
	private WorkOrderDispatchService workOrderDispatchService;
	/**
	 * 通用工单分发信息服务
	 */
	@Resource(name = "workOrderTransferServiceImpl")
	private WorkOrderTransferService workOrderTransferService;
	/**
	 * 工单签收信息服务
	 */
	@Resource(name = "workOrderSignForServiceImpl")
	private WorkOrderSignForService workOrderSignForService;
	/**
	 * 工单拒签确认信息服务
	 */
	@Resource(name = "workOrderRefuseConfirmServiceImpl")
	private WorkOrderRefuseConfirmService workOrderRefuseConfirmService;
	/**
	 * 工单回复信息服务
	 */
	@Resource(name = "workOrderReplyServiceImpl")
	private WorkOrderReplyService workOrderReplyService;
	/**
	 * 工单回单验证信息服务
	 */
	@Resource(name = "workOrderReplyCheckServiceImpl")
	private WorkOrderReplyCheckService workOrderReplyCheckService;
	/**
	 * 基础业务服务
	 */
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;

	@SuppressWarnings("unchecked")
	@Override
	public String doBusiness(String cmd, Map<String, Object> parameter,
			List<Element> props) {
		String methodName = getMethodName(cmd);
		Map<String, Object> valueMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(methodName)) {
			try {
				valueMap = (Map<String, Object>) ReflectionUtils.invokeMethod(
						this, methodName,
						new Class[] { Map.class, List.class }, new Object[] {
								parameter, props });
			} catch (Exception ex) {
				logger.error("接口异常：", ex);
				valueMap.put("code", ExternalWebService.EXCEPTION_CODE);
			}
		} else {
			valueMap.put("code", ExternalWebService.NO_CMD_ERROR_CODE);
		}
		valueMap.put("cmd", cmd);
		String datafomat = "yyyy-MM-dd HH:mm:ss";
		Gson gson = new GsonBuilder().setDateFormat(datafomat).create();
		String value = gson.toJson(valueMap);
		logger.info(cmd + "::" + value);
		return value;
	}

	/**
	 * 获取待办列表
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @return Map<String, Object>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> getWaitHandledTaskList(Map<String, Object> map,
			List<Element> props) {
		if (CollectionUtils.isEmpty(props)) {
			return null;
		}
		String[] parameters = getInputParameters(map, props);
		UserInfo user = userInfoService.getUserInfoByUserId(parameters[0]);
		Page page = new Page();
		page.setPageNo(Integer.parseInt(parameters[1]));
		page.setPageSize(Integer.parseInt(parameters[2]));
		WorkOrder workOrder = new WorkOrder();
		workOrder.setLoginUser(user);
		workOrder.setPage(page);
		page = workOrderWaitHandledService.getWaitHandledList(workOrder);
		List<Map<String, Object>> result = processPage(page.getResult());
		if (CollectionUtils.isNotEmpty(result)) {
			page.setResult(result);
		}
		Map<String, Object> valueMap = new HashMap<String, Object>();
		if (page.getTotalCount() == 0) {
			valueMap.put("code", ExternalWebService.NO_WAIT_HANDLED_LIST_CODE);
		} else {
			valueMap.put("code", ExternalWebService.SUCCESS_CODE);
			valueMap.put("value", page.getResult());
		}
		valueMap.put("total", page.getTotalCount());
		valueMap.put("totalpage", page.getTotalPages());
		return valueMap;
	}

	/**
	 * 获取已办列表
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @return Map<String, Object>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> getHandledTaskList(Map<String, Object> map,
			List<Element> props) {
		if (CollectionUtils.isEmpty(props)) {
			return null;
		}
		String[] parameters = getInputParameters(map, props);
		UserInfo user = userInfoService.getUserInfoByUserId(parameters[0]);
		Page page = new Page();
		page.setPageNo(Integer.parseInt(parameters[1]));
		page.setPageSize(Integer.parseInt(parameters[2]));
		WorkOrder workOrder = new WorkOrder();
		workOrder.setLoginUser(user);
		workOrder.setPage(page);
		page = workOrderHandledService.getHandledList(workOrder);
		List<Map<String, Object>> result = processPage(page.getResult());
		if (CollectionUtils.isNotEmpty(result)) {
			page.setResult(result);
		}
		Map<String, Object> valueMap = new HashMap<String, Object>();
		if (page.getTotalCount() == 0) {
			valueMap.put("code", ExternalWebService.NO_DATA_LIST_CODE);
		} else {
			valueMap.put("code", ExternalWebService.SUCCESS_CODE);
			valueMap.put("value", page.getResult());
		}
		valueMap.put("total", page.getTotalCount());
		valueMap.put("totalpage", page.getTotalPages());
		return valueMap;
	}

	/**
	 * 获取工单详细信息
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getOneTaskInfo(Map<String, Object> map,
			List<Element> props) {
		if (CollectionUtils.isEmpty(props)) {
			return null;
		}
		String[] parameters = getInputParameters(map, props);
		Map<String, Object> valueMap = new HashMap<String, Object>();
		if (StringUtils.isBlank(parameters[1])) {
			valueMap.put("code", ExternalWebService.PARAMETER_ERROR_CODE);
			return valueMap;
		}
		valueMap.put("code", ExternalWebService.SUCCESS_CODE);
		valueMap.put("id", parameters[1]);
		valueMap.put("pid", parameters[2]);
		valueMap.put("step", parameters[4]);
		String id = parameters[1];
		String pid = parameters[2];
		WorkOrder workOrder = workOrderDispatchService.view(id);
		WorkOrderTransfer workOrderTransfer = workOrderTransferService
				.view(pid);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		valueMap.put("proinstState",
				WorkOrder.getTaskStateName(workOrderTransfer.getTaskState()));
		valueMap.put("taskCode", workOrder.getTaskCode());
		valueMap.put("taskName", workOrder.getTaskName());
		String taskType = workOrder.getTaskType();
		taskType = (String) baseInfoProvider.getDicService()
				.getDicMap("TASK_CODE", taskType).get("LABLE");
		valueMap.put("taskTypeDis", taskType);
		String creator = workOrder.getCreater();
		creator = userInfoService.getUserInfoByPersonId(creator).getUserName();
		valueMap.put("createrName", creator);
		valueMap.put("createDateDis", df.format(workOrder.getCreateDate()));
		valueMap.put("taskDate", df.format(workOrder.getOvertimeSet()));
		valueMap.put("taskDetail", workOrder.getTaskRequest());
		return valueMap;
	}

	/**
	 * 执行工单的签收
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @return Map<String, Object>
	 */
	public Map<String, Object> signinTask(Map<String, Object> map,
			List<Element> props) {
		if (CollectionUtils.isEmpty(props)) {
			return null;
		}
		String[] parameters = getInputParameters(map, props);
		UserInfo user = userInfoService.getUserInfoByUserId(parameters[0]);
		Map<String, Object> valueMap = new HashMap<String, Object>();
		if (user == null) {
			valueMap.put("code", ExternalWebService.NO_USER_ERROR_CODE);
			return valueMap;
		}
		WorkOrderSignFor workorderSignFor = new WorkOrderSignFor();
		workorderSignFor.setSignForRemark(parameters[7]);
		workorderSignFor.setPatrolGroupId(parameters[5]);
		workorderSignFor.setSignForResult(parameters[6]);
		workorderSignFor.setSignForUserId(user.getPersonId());
		workorderSignFor.setTaskId(parameters[1]);
		workorderSignFor.setWorkflowBzId(parameters[2]);
		workorderSignFor.setWorkflowTaskId(parameters[3]);
		workOrderSignForService.save(workorderSignFor);
		valueMap.put("code", ExternalWebService.SUCCESS_CODE);
		return valueMap;
	}

	/**
	 * 执行工单的回复
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @return Map<String, Object>
	 */
	public Map<String, Object> replyTask(Map<String, Object> map,
			List<Element> props) {
		if (CollectionUtils.isEmpty(props)) {
			return null;
		}
		String[] parameters = getInputParameters(map, props);
		UserInfo user = userInfoService.getUserInfoByUserId(parameters[0]);
		Map<String, Object> valueMap = new HashMap<String, Object>();
		if (user == null) {
			valueMap.put("code", ExternalWebService.NO_USER_ERROR_CODE);
			return valueMap;
		}
		WorkOrder workOrder = workOrderDispatchService.view(parameters[1]);
		WorkOrderReply workOrderReply = new WorkOrderReply();
		workOrderReply.setCreateDate(new Date());
		workOrderReply.setCreater(user.getPersonId());
		workOrderReply.setIsSubmited(parameters[5]);
		workOrderReply.setRemark(parameters[6]);
		workOrderReply.setReplyCheckUserId(workOrder.getCreater());
		workOrderReply.setTaskId(parameters[1]);
		workOrderReply.setTransferId(parameters[2]);
		workOrderReply.setWorkflowTaskId(parameters[3]);
		workOrderReplyService.save(workOrderReply);
		valueMap.put("code", ExternalWebService.SUCCESS_CODE);
		valueMap.put("data", workOrderReply.getId());
		return valueMap;
	}

	/**
	 * 执行工单的转派
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @return Map<String, Object>
	 */
	public Map<String, Object> transferTask(Map<String, Object> map,
			List<Element> props) {
		if (CollectionUtils.isEmpty(props)) {
			return null;
		}
		String[] parameters = getInputParameters(map, props);
		UserInfo user = userInfoService.getUserInfoByUserId(parameters[0]);
		Map<String, Object> valueMap = new HashMap<String, Object>();
		if (user == null) {
			valueMap.put("code", ExternalWebService.NO_USER_ERROR_CODE);
			return valueMap;
		}
		WorkOrder workOrder = workOrderDispatchService.view(parameters[1]);
		workOrder.setCreateDate(new Date());
		workOrder.setCreater(user.getPersonId());
		workOrder.setAcceptUserIds(parameters[3]);
		workOrder.setTaskType(parameters[4]);
		workOrder.setOvertimeSet(DateUtil.Str2UtilDate(parameters[5],
				"yyyy-MM-dd HH:mm:ss"));
		workOrder.setTaskRequest(parameters[6]);
		workOrder.setTaskName(parameters[7]);
		workOrder.setParentId(parameters[1]);
		workOrder.setIsSubmited(SysConstant.FORM_IS_SUBMITED);
		workOrderDispatchService.save(workOrder);
		valueMap.put("code", ExternalWebService.SUCCESS_CODE);
		return valueMap;
	}

	/**
	 * 执行工单的拒签确认
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @return Map<String, Object>
	 */
	public Map<String, Object> refuseConfirmTask(Map<String, Object> map,
			List<Element> props) {
		if (CollectionUtils.isEmpty(props)) {
			return null;
		}
		String[] parameters = getInputParameters(map, props);
		UserInfo user = userInfoService.getUserInfoByUserId(parameters[0]);
		Map<String, Object> valueMap = new HashMap<String, Object>();
		if (user == null) {
			valueMap.put("code", ExternalWebService.NO_USER_ERROR_CODE);
			return valueMap;
		}
		WorkOrderRefuseConfirm workOrderRefuseConfirm = new WorkOrderRefuseConfirm();
		workOrderRefuseConfirm.setRefuseConfirmRemark(parameters[6]);
		workOrderRefuseConfirm.setRefuseConfirmResult(parameters[5]);
		workOrderRefuseConfirm.setRefuseConfirmUserId(user.getPersonId());
		workOrderRefuseConfirm.setTaskId(parameters[1]);
		workOrderRefuseConfirm.setTransferApprover(parameters[7]);
		workOrderRefuseConfirm.setWorkflowBzId(parameters[2]);
		workOrderRefuseConfirm.setWorkflowTaskId(parameters[3]);
		workOrderRefuseConfirmService.save(workOrderRefuseConfirm);
		valueMap.put("code", ExternalWebService.SUCCESS_CODE);
		return valueMap;
	}

	/**
	 * 执行工单的回单验证
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @return Map<String, Object>
	 */
	public Map<String, Object> checkTask(Map<String, Object> map,
			List<Element> props) {
		if (CollectionUtils.isEmpty(props)) {
			return null;
		}
		String[] parameters = getInputParameters(map, props);
		UserInfo user = userInfoService.getUserInfoByUserId(parameters[0]);
		Map<String, Object> valueMap = new HashMap<String, Object>();
		if (user == null) {
			valueMap.put("code", ExternalWebService.NO_USER_ERROR_CODE);
			return valueMap;
		}
		WorkOrderReplyCheck workOrderReplyCheck = new WorkOrderReplyCheck();
		workOrderReplyCheck.setCheckRemark(parameters[6]);
		workOrderReplyCheck.setCheckResult(parameters[5]);
		workOrderReplyCheck.setCheckUserId(user.getPersonId());
		workOrderReplyCheck.setTaskId(parameters[1]);
		workOrderReplyCheck.setTransferApprover(parameters[7]);
		workOrderReplyCheck.setWorkflowBzId(parameters[2]);
		workOrderReplyCheck.setWorkflowTaskId(parameters[3]);
		workOrderReplyCheckService.save(workOrderReplyCheck);
		valueMap.put("code", ExternalWebService.SUCCESS_CODE);
		return valueMap;
	}

	/**
	 * 获取工单类型列表
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getTaskTypeList(Map<String, Object> map,
			List<Element> props) {
		if (CollectionUtils.isEmpty(props)) {
			return null;
		}
		Map<String, Object> valueMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = baseInfoProvider.getDicService()
				.getDicList("dispatch_task");
		list = processPage(list);
		if (CollectionUtils.isEmpty(list)) {
			valueMap.put("code", ExternalWebService.NO_DATA_LIST_CODE);
		} else {
			valueMap.put("code", ExternalWebService.SUCCESS_CODE);
			valueMap.put("value", list);
		}
		valueMap.put("total", list.size());
		return valueMap;
	}

	/**
	 * 根据不同命令获取不同的调用方法名称
	 * 
	 * @param cmd
	 *            String
	 * @return String
	 */
	private String getMethodName(String cmd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("wlbtasklist", "getWaitHandledTaskList");
		map.put("wlbhandledtasklist", "getHandledTaskList");
		map.put("wlbtasktypelist", "getTaskTypeList");
		map.put("wlbtaskdetail", "getOneTaskInfo");
		map.put("wlbtaskdealsign", "signinTask");
		map.put("wlbtaskdealrequest", "replyTask");
		map.put("wlbtaskdealsend", "transferTask");
		map.put("wlbtaskdealrefuse", "refuseConfirmTask");
		map.put("wlbtaskdealcheck", "checkTask");
		return map.get(cmd);
	}

	/**
	 * 获取输入的参数信息
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @param props
	 *            List<Element>
	 * @return String[]
	 */
	private String[] getInputParameters(Map<String, Object> map,
			List<Element> props) {
		String[] parameters = new String[props.size()];
		for (int i = 0; i < props.size(); i++) {
			Element e = props.get(i);
			String key = e.attributeValue(ParseXmlTools.ID_ATTRBUTE_KEY);
			parameters[i] = (String) map.get(key);
		}
		return parameters;
	}

	/**
	 * 处理页面列表数据
	 * 
	 * @param list
	 *            List<Map<String, Object>>
	 * @return List<Map<String, Object>>
	 */
	private List<Map<String, Object>> processPage(List<Map<String, Object>> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> valueMap = new HashMap<String, Object>();
			Map<String, Object> map = list.get(i);
			Set<String> keySet = map.keySet();
			Iterator<String> it = keySet.iterator();
			if (it == null) {
				continue;
			}
			while (it.hasNext()) {
				String key = it.next();
				Object value = map.get(key);
				key = key.toLowerCase();
				char firstChar = key.charAt(0);
				key = WordUtils.capitalize(key, new char[] { '_' });
				key = firstChar + key.replaceAll("_", "").substring(1);
				valueMap.put(key, value);
			}
			result.add(valueMap);
		}
		return result;
	}
}
