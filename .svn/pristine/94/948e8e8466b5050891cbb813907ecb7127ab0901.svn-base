package com.cabletech.business.workflow.wmaintain.service.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainBaseDao;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainWorkflowService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 隐患业务操作基类
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-16 添加“维修作业计划草稿箱查询条件生成器KEY”
 * @author 杨隽 2012-04-17 添加“获取待办维修作业计划列表查询条件生成器KEY”
 * @author 杨隽 2012-04-23 添加“无线资源计划中存在问题站点查询条件生成器KEY”
 * 
 * @param <T>
 * @param <PK>
 */
public abstract class WMaintainBaseServiceImpl<T, PK extends Serializable>
		extends BaseServiceImpl<T, PK> {
	// “查询条件生成器KEY”常量
	// “获取待办维修作业计划列表查询条件生成器KEY”
	public static final String WAIT_HANDLED_WMAINTAIN_LIST_CONDITION_GENERATE_KEY = "wmaintainWaitHandledListConditionGenerateImpl";
	// “获取维修作业计划列表查询条件生成器KEY”
	public static final String WMAINTAIN_LIST_CONDITION_GENERATE_KEY = "wmaintainQueryListConditionGenerateImpl";
	// “维修作业计划草稿箱查询条件生成器KEY”
	public static final String DRAFT_CONDITION_GENERATE_KEY = "wmaintainDraftListConditionGenerateImpl";
	// “维修作业计划已办工作查询条件生成器KEY”
	public static final String HANDLED_CONDITION_GENERATE_KEY = "wmaintainHandledListConditionGenerateImpl";
	// “维修作业计划待取消工作查询条件生成器KEY”
	public static final String WAIT_CANCELED_CONDITION_GENERATE_KEY = "wmaintainCancelListConditionGenerateImpl";
	// “维修作业计划已取消工作查询条件生成器KEY”
	public static final String CANCELED_CONDITION_GENERATE_KEY = "wmaintainCanceledListConditionGenerateImpl";
	// “维修作业计划待删除查询条件生成器KEY”
	public static final String WAIT_DELETED_TASK_CONDITION_GENERATE_KEY = "wmaintainCanceledListConditionGenerateImpl";
	// “维修作业计划编号查询条件生成器KEY”
	public static final String TASK_ID_CONDITION_GENERATE_KEY = "wmPlanIdConditionGenerateImpl";
	// “维修作业计划中计划维护站点编号查询条件生成器KEY”
	public static final String SITE_ID_CONDITION_GENERATE_KEY = "wmPlanSiteIdConditionGenerateImpl";
	// “无线资源计划中存在问题站点查询条件生成器KEY”
	public static final String WPLAN_RESOURCE_CONDITION_GENERATE_KEY = "wmPlanResourceConditionGenerateImpl";
	// 短信发送信息配置文件参数常量
	// 短信发送信息配置文件编号常量
	public static final String WMAINTAIN_XML_FILE_ID = "wmaintian";
	// 制定维修作业计划短信发送信息编号
	public static final String CREATE_PLAN_SEND_MSG_ID = "create_plan";
	// 维修作业计划审核不通过短信发送信息编号
	public static final String APPROVE_NOTPASSED_PLAN_MSG_ID = "approve_notpassed_plan";
	// 维修作业计划转审短信发送信息编号
	public static final String APPROVE_TRANSFER_PLAN_MSG_ID = "approve_transfer_plan";
	// 维修作业计划审核通过短信发送信息编号
	public static final String APPROVE_PASSED_PLAN_MSG_ID = "approve_passed_plan";
	// 填写维修作业计划报告短信发送信息编号
	public static final String CREATE_REPORT_SEND_MSG_ID = "create_report";
	// 维修作业计划报告审核不通过短信发送信息编号
	public static final String APPROVE_NOTPASSED_REPORT_MSG_ID = "approve_notpassed_report";
	// 维修作业计划报告转审短信发送信息编号
	public static final String APPROVE_TRANSFER_REPORT_MSG_ID = "approve_transfer_report";
	// 维修作业计划报告审核通过短信发送信息编号
	public static final String APPROVE_PASSED_REPORT_MSG_ID = "approve_passed_report";
	// 隐患派单工作流业务处理服务
	@Resource(name = "WMaintainWorkflowService")
	private WMaintainWorkflowService wMaintainWorkflowService;
	// 基础信息业务处理服务
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;
	// 用户信息业务处理
	@Resource(name = "userInfoServiceImpl")
	protected UserInfoService userInfoService;
	// 故障查询条件生成器Map
	@Autowired
	protected Map<String, ConditionGenerate> conditionGenerateMap;

	/**
	 * 获取查询条件生成器并将查询条件参数传递到其中
	 * 
	 * @param key
	 *            String 查询条件生成器Key
	 * @param parameter
	 *            QueryParameter 查询参数
	 * @return ConditionGenerate 查询条件生成器
	 */
	protected ConditionGenerate getConditionGenerate(String key,
			QueryParameter parameter) {
		ConditionGenerate conditionGenerate = conditionGenerateMap.get(key);
		conditionGenerate.setQuerySql(parameter);
		return conditionGenerate;
	}

	/**
	 * 根据用户的输入信息和登录信息设置查询条件参数
	 * 
	 * @param plan
	 *            WMaintainPlan 用户的输入信息的维修作业计划实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return QueryParameter 查询条件参数
	 */
	protected QueryParameter setQueryParameter(WMaintainPlan plan,
			UserInfo userInfo) {
		if (QueryParameter.isNull(plan)) {
			plan = new WMaintainPlan();
		}
		QueryParameter parameter = new QueryParameter();
		parameter.setUser(userInfo);
		parameter.setEntity(plan);
		return parameter;
	}

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
	 * 根据巡检组编号获取巡检组名称
	 * 
	 * @param patrolGroupId
	 *            String 巡检组编号
	 * @return String 巡检组名称
	 */
	@SuppressWarnings("deprecation")
	public String getPatrolGroupName(String patrolGroupId) {
		String patrolGroupName = "";
		Map<String, Object> patrolGroupInfo = baseInfoProvider
				.getPatrolmanMap(patrolGroupId);
		if (MapUtils.isNotEmpty(patrolGroupInfo)) {
			patrolGroupName = (String) patrolGroupInfo.get("patrolname");
		}
		return patrolGroupName;
	}

	/**
	 * 根据巡检组编号获取巡检组的组织名称
	 * 
	 * @param patrolGroupId
	 *            String 巡检组编号
	 * @return String 巡检组的组织名称
	 */
	@SuppressWarnings("deprecation")
	public String getPatrolGroupOrgName(String patrolGroupId) {
		String orgId = "";
		Map<String, Object> patrolGroupInfo = baseInfoProvider
				.getPatrolmanMap(patrolGroupId);
		if (MapUtils.isNotEmpty(patrolGroupInfo)) {
			orgId = (String) patrolGroupInfo.get("parentid");
		}
		return getOrgName(orgId);
	}

	/**
	 * 根据用户编号获取用户名称
	 * 
	 * @param userId
	 *            String 用户编号
	 * @return String 用户名称
	 */
	public String getUserName(String userId) {
		String userName = "";
		UserInfo user = userInfoService.getUserInfoByPersonId(userId);
		if (user != null) {
			userName = user.getUserName();
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
	public String getUserPhone(String userId) {
		// TODO Auto-generated method stub
		String phone = "";
		UserInfo user = userInfoService.getUserInfoByPersonId(userId);
		if (user != null) {
			phone = user.getPhone();
			if (StringUtils.isBlank(phone)) {
				phone = user.getMobile();
			}
		}
		return phone;
	}

	/**
	 * 
	 * @return WMaintainWorkflowService
	 */
	public WMaintainWorkflowService getwMaintainWorkflowService() {
		return wMaintainWorkflowService;
	}

	/**
	 * @param wMaintainWorkflowService
	 *            WMaintainWorkflowService
	 */
	public void setwMaintainWorkflowService(
			WMaintainWorkflowService wMaintainWorkflowService) {
		this.wMaintainWorkflowService = wMaintainWorkflowService;
	}

	/**
	 * 获取实体操作的Dao
	 */
	@Override
	protected BaseDao<T, PK> getBaseDao() {
		// TODO Auto-generated method stub
		return getWMaintainBaseDao();
	}

	/**
	 * 获取维修作业计划处理操作Dao
	 * 
	 * @return WMaintainBaseDao<T, PK> 维修作业计划处理操作Dao
	 */
	protected abstract WMaintainBaseDao<T, PK> getWMaintainBaseDao();

	/**
	 * 执行工作流步骤
	 * 
	 * @param plan
	 *            WMaintainPlan 维修作业计划信息数据
	 * @param smParameter
	 *            SmParameter 短信发送数据参数
	 */
	protected void doWorkflow(WMaintainPlan plan, SmParameter smParameter) {
		ProMockPo taskPi = new ProMockPo();
		String sendUserId = plan.getCurrentProcessUserId();
		String processUserId = plan.getNextProcessUserId();
		taskPi.setUserId(sendUserId);
		taskPi.setUserName(getUserName(sendUserId));
		taskPi.setTaskId(plan.getWorkflowTaskId());
		taskPi.setDealUsers(processUserId);
		taskPi.setDealUsersName(getUserName(processUserId));
		taskPi.setTransition(plan.getWorkflowTransition());
		taskPi.setComment(plan.getWorkflowComment());
		getwMaintainWorkflowService().doTask(taskPi, smParameter);
	}
}
