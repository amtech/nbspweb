package com.cabletech.business.workflow.electricity.security.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.resource.service.ResourceService;
import com.cabletech.business.workflow.electricity.security.dao.ElectricitySecurityBaseDao;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.service.ElectricitySecurityWorkflowService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 断电告警业务操作基类
 * 
 * @author 杨隽 2012-05-03 创建
 * @author 杨隽 2012-05-07 添加getResourceName()方法
 * 
 * @param <T>
 * @param <PK>
 */
public abstract class ElectricitySecurityBaseServiceImpl<T, PK extends Serializable>
		extends BaseServiceImpl<T, PK> {
	// “查询条件生成器KEY”常量
	// “获取未派单断电告警告警单列表查询条件生成器KEY”
	public static final String UNDISPATCHED_LIST_CONDITION_GENERATE_KEY = "";
	// “获取断电告警告警单列表查询条件生成器KEY”
	public static final String LIST_CONDITION_GENERATE_KEY = "oeAlarmListConditionGenerateImpl";
	// “获取待办断电告警派单列表查询条件生成器KEY”
	public static final String WAIT_HANDLED_DISPATCH_LIST_CONDITION_GENERATE_KEY = "oeDispatchWaitHandledListConditionGenerateImpl";//
	// “获取断电告警派单列表查询条件生成器KEY”
	public static final String DISPATCH_LIST_CONDITION_GENERATE_KEY = "oeDispatchListConditionGenerateImpl";//
	// “断电告警派单草稿箱查询条件生成器KEY”
	public static final String DRAFT_CONDITION_GENERATE_KEY = "oeDispatchDraftListConditionGenerateImpl";//
	// “断电告警派单已办工作查询条件生成器KEY”
	public static final String HANDLED_CONDITION_GENERATE_KEY = "oeDispatchHandledListConditionGenerateImpl";//
	// “断电告警派单待取消工作查询条件生成器KEY”
	public static final String WAIT_CANCELED_CONDITION_GENERATE_KEY = "oeDispatchWaitCanceledListConditionGenerateImpl";//
	// “断电告警派单已取消工作查询条件生成器KEY”
	public static final String CANCELED_CONDITION_GENERATE_KEY = "oeDispatchCanceledListConditionGenerateImpl";//
	// “断电告警派单待删除工作查询条件生成器KEY”
	public static final String WAIT_DELETED_TASK_CONDITION_GENERATE_KEY = "oeDispatchCanceledListConditionGenerateImpl";//
	// 短信发送信息配置文件参数常量
	// 短信发送信息配置文件编号常量
	public static final String ELECTRICTITY_XML_FILE_ID = "electrictity";
	// 断电告警派发短信发送信息编号
	public static final String SEND_MSG_ID = "send_dispatch";
	// 断电告警派发短信调度信息编号
	public static final String SCHEDULE_MSG_ID = "schedule_oilengine";
	// 断电告警回复短信发送信息编号
	public static final String REPLY_MSG_ID = "reply_task";
	// 断电告警回复验证不通过短信发送信息编号
	public static final String APPROVE_NOTPASSED_MSG_ID = "approve_not_passed";
	// 断电告警回复转审短信发送信息编号
	public static final String APPROVE_TRANSFER_MSG_ID = "transfer_approve";
	// 断电告警回复验证通过短信发送信息编号
	public static final String APPROVE_PASSED_MSG_ID = "approve_passed";
	// 断电告警查询条件生成器Map
	@Autowired
	protected Map<String, ConditionGenerate> conditionGenerateMap;
	// 断电告警派单工作流业务处理服务
	@Resource(name = "electricitySecurityWorkflowService")
	private ElectricitySecurityWorkflowService electricitySecurityWorkflowService;
	// 基础信息业务处理服务
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;
	// 资源业务处理服务
	@Resource(name = "resourceServiceImpl")
	private ResourceService resourceService;

	/**
	 * 根据组织编号获取组织名称
	 * 
	 * @param orgId
	 *            String 组织编号
	 * @return String 组织名称
	 */
	@SuppressWarnings("deprecation")
	public String getOrgName(String orgId) {
		String orgName = "";
		Map<String, Object> orgInfo = baseInfoProvider.getOrgMap(orgId);
		if (MapUtils.isNotEmpty(orgInfo)) {
			orgName = (String) orgInfo.get("ORGANIZENAME");
		}
		return orgName;
	}

	/**
	 * 根据组织编号获取组织联系人电话
	 * 
	 * @param orgId
	 *            String 组织编号
	 * @return String 组织联系人电话
	 */
	@SuppressWarnings("deprecation")
	public String getOrgTel(String orgId) {
		String orgName = "";
		Map<String, Object> orgInfo = baseInfoProvider.getOrgMap(orgId);
		if (MapUtils.isNotEmpty(orgInfo)) {
			orgName = (String) orgInfo.get("LINKMANTEL");
		}
		return orgName;
	}

	/**
	 * 根据用户编号获取用户名称
	 * 
	 * @param userId
	 *            String 用户编号
	 * @return String 用户名称
	 */
	@SuppressWarnings("deprecation")
	public String getUserName(String userId) {
		String userName = "";
		Map<String, Object> userInfo = baseInfoProvider.getUserMap(userId);
		if (MapUtils.isNotEmpty(userInfo)) {
			userName = (String) userInfo.get("USERNAME");
		}
		return userName;
	}

	/**
	 * 根据用户编号获取用户联系电话
	 * 
	 * @param userId
	 *            String 用户编号
	 * @return String 用户联系电话
	 */
	@SuppressWarnings("deprecation")
	public String getUserPhone(String userId) {
		// TODO Auto-generated method stub
		String phone = "";
		Map<String, Object> userInfo = baseInfoProvider.getUserMap(userId);
		if (MapUtils.isNotEmpty(userInfo)) {
			phone = (String) userInfo.get("phone");
		}
		return phone;
	}

	/**
	 * 根据油机编号获取油机负责人联系电话
	 * 
	 * @param oilengineId
	 *            String 油机编号
	 * @return String 油机负责人联系电话
	 */
	public String getOilEngineTel(String oilengineId) {
		// TODO Auto-generated method stub
		String phone = "";
		return phone;
	}

	/**
	 * 
	 * @return ElectricitySecurityWorkflowService
	 */
	public ElectricitySecurityWorkflowService getElectricitySecurityWorkflowService() {
		return electricitySecurityWorkflowService;
	}

	/**
	 * 
	 * @param electricitySafeWorkflowService
	 *            ElectricitySecurityWorkflowService
	 */
	public void setElectricitySecurityWorkflowService(
			ElectricitySecurityWorkflowService electricitySafeWorkflowService) {
		this.electricitySecurityWorkflowService = electricitySafeWorkflowService;
	}

	/**
	 * 根据断电派单的站点信息获取站点名称
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 断电派单
	 * @return String 站点名称
	 */
	protected String getResourceName(OeDispatchTask oeDispatchTask) {
		String resourceName = "";
		resourceName = resourceService.viewResourceInfo(
				oeDispatchTask.getStationId()).getResourceName();
		return resourceName;
	}

	/**
	 * 根据断电派单的站点信息获取站点地址
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 断电派单
	 * @return String 站点地址
	 */
	protected String getResourceAddress(OeDispatchTask oeDispatchTask) {
		String resourceAddress = "";
		String condition = " and mt.rs_id='" + oeDispatchTask.getStationId()
				+ "' ";
		condition += " and mt.rs_type='" + oeDispatchTask.getStationType()
				+ "' ";
		List<Map<String, Object>> list = resourceService
				.getResourcesListByBusinessType(
						SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C31,
						condition);
		if (CollectionUtils.isEmpty(list)) {
			return resourceAddress;
		}
		Map<String, Object> map = list.get(0);
		resourceAddress = (String) map.get("resource_address");
		return resourceAddress;
	}

	/**
	 * 执行工作流步骤
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 维修作业计划信息数据
	 * @param smParameter
	 *            SmParameter 短信发送数据参数
	 */
	protected void doWorkflow(OeDispatchTask oeDispatchTask,
			SmParameter smParameter) {
		ProMockPo taskPi = new ProMockPo();
		String sendUserId = oeDispatchTask.getCurrentProcessUserId();
		String processUserId = oeDispatchTask.getNextProcessUserId();
		String processOrgId = oeDispatchTask.getNextProcessOrgId();
		taskPi.setUserId(sendUserId);
		taskPi.setUserName(getUserName(sendUserId));
		taskPi.setTaskId(oeDispatchTask.getWorkflowTaskId());
		taskPi.setDealUsers(processUserId);
		taskPi.setDealUsersName(getUserName(processUserId));
		taskPi.setDealGroup(processOrgId);
		taskPi.setDealGroupName(getOrgName(processOrgId));
		taskPi.setTransition(oeDispatchTask.getWorkflowTransition());
		taskPi.setComment(oeDispatchTask.getWorkflowComment());
		getElectricitySecurityWorkflowService().doTask(taskPi, smParameter);
	}

	/**
	 * 根据输入参数和查询条件生成器Key获取断电告警派单任务分页列表
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask输入参数
	 * @param key
	 *            String 查询条件生成器Key
	 * @return Page 断电告警派单任务分页列表
	 */
	@SuppressWarnings("rawtypes")
	protected Page getOeDispatchTaskList(OeDispatchTask oeDispatchTask,
			String key) {
		ConditionGenerate conditionGenerate = conditionGenerateMap.get(key);
		QueryParameter parameter = new QueryParameter();
		parameter.setEntity(oeDispatchTask);
		parameter.setUser(oeDispatchTask.getLoginUser());
		conditionGenerate.setQuerySql(parameter);
		conditionGenerate.setPage(oeDispatchTask.getPage());
		Page page = getElectricitySecurityBaseDao().queryPageForSql(
				conditionGenerate);
		return page;
	}

	/**
	 * @return BaseDao<T, PK> 业务操作Dao
	 */
	@Override
	protected BaseDao<T, PK> getBaseDao() {
		// TODO Auto-generated method stub
		return getElectricitySecurityBaseDao();
	}

	/**
	 * 获取业务处理操作Dao
	 * 
	 * @return ElectricitySafeBaseDao<T, PK> 业务操作Dao
	 */
	protected abstract ElectricitySecurityBaseDao<T, PK> getElectricitySecurityBaseDao();
}
