package com.cabletech.business.workflow.fault.service.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.business.workflow.fault.dao.FaultBaseDao;
import com.cabletech.business.workflow.fault.service.FaultWorkflowService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 故障业务操作基类
 * 
 * @author 杨隽 2011-10-31 创建
 * @author 杨隽 2011-12-12 添加getUserPhone()方法
 * @author 杨隽 2012-02-08 添加getBaseDao()方法实现和getFaultBaseDao ()抽象方法
 * @author 杨隽 2012-02-08 提取获取故障业务单列表的公共方法
 * @author 杨隽 2012-02-22
 *         添加“故障派单草稿箱查询条件生成器KEY”常量、“故障派单已办工作查询条件生成器KEY”常量、“故障派单待取消工作查询条件生成器KEY
 *         ”常量、“故障派单已取消工作查询条件生成器KEY”常量和 “故障派单待删除故障查询条件生成器KEY”常量
 * @author 杨隽 2012-03-27 添加getOrgTel()方法
 * @author 杨隽 2012-03-27 添加“短信发送信息配置文件参数”常量
 * 
 * @param <T>
 * @param <PK>
 */
public abstract class FaultBaseServiceImpl<T, PK extends Serializable> extends
		BaseServiceImpl<T, PK> {
	// “查询条件生成器KEY”常量
	// “获取未派单故障告警单列表查询条件生成器KEY”
	public static final String UNDISPATCHED_FAULT_LIST_CONDITION_GENERATE_KEY = "unDispatchFaultListConditionGenerateImpl";
	// “获取故障告警单列表查询条件生成器KEY”
	public static final String FAULT_LIST_CONDITION_GENERATE_KEY = "faultListConditionGenerateImpl";
	// “获取待办故障派单列表查询条件生成器KEY”
	public static final String WAIT_HANDLED_DISPATCH_FAULT_LIST_CONDITION_GENERATE_KEY = "waitHandledDispatchFaultListConditionGenerateImpl";
	// “获取故障派单列表查询条件生成器KEY”
	public static final String DISPATCH_FAULT_LIST_CONDITION_GENERATE_KEY = "dispatchFaultListConditionGenerateImpl";
	// “故障派单草稿箱查询条件生成器KEY”
	public static final String DRAFT_CONDITION_GENERATE_KEY = "dispatchFaultDraftListConditionGenerateImpl";
	// “故障派单已办工作查询条件生成器KEY”
	public static final String HANDLED_CONDITION_GENERATE_KEY = "dispatchFaultHandledListConditionGenerateImpl";
	// “故障派单待取消工作查询条件生成器KEY”
	public static final String WAIT_CANCELED_CONDITION_GENERATE_KEY = "dispatchFaultWaitCanceledListConditionGenerateImpl";
	// “故障派单已取消工作查询条件生成器KEY”
	public static final String CANCELED_CONDITION_GENERATE_KEY = "dispatchFaultCanceledListConditionGenerateImpl";
	// “故障派单待删除故障查询条件生成器KEY”
	public static final String WAIT_DELETED_TASK_CONDITION_GENERATE_KEY = "dispatchFaultCanceledListConditionGenerateImpl";
	// 短信发送信息配置文件参数常量
	// 短信发送信息配置文件编号常量
	public static final String FAULT_XML_FILE_ID = "fault";
	// 故障派发短信发送信息编号
	public static final String SEND_TROUBLE_MSG_ID = "send_trouble";
	// 故障回复短信发送信息编号
	public static final String REPLY_TROUBLE_MSG_ID = "reply_trouble";
	// 故障回复验证不通过短信发送信息编号
	public static final String APPROVE_NOTPASSED_TROUBLE_MSG_ID = "approve_not_passed";
	// 故障回复转审短信发送信息编号
	public static final String APPROVE_TRANSFER_TROUBLE_MSG_ID = "transfer_approve";
	// 故障回复验证通过短信发送信息编号
	public static final String APPROVE_PASSED_TROUBLE_MSG_ID = "approve_passed";
	// 故障派单工作流业务处理服务
	@Resource(name = "faultWorkflowService")
	private FaultWorkflowService faultWorkflowManager;
	// 基础信息业务处理服务
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;
	// 故障查询条件生成器Map
	@Autowired
	protected Map<String, ConditionGenerate> conditionGenerateMap;

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
			if (StringUtils.isBlank(phone)) {
				phone = (String) userInfo.get("mobile");
			}
		}
		return phone;
	}

	public FaultWorkflowService getFaultWorkflowManager() {
		return faultWorkflowManager;
	}

	public void setFaultWorkflowManager(
			FaultWorkflowService faultWorkflowManager) {
		this.faultWorkflowManager = faultWorkflowManager;
	}

	/**
	 * 根据输入参数和查询条件生成器Key获取故障派单任务分页列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 输入参数
	 * @param key
	 *            String 查询条件生成器Key
	 * @return Page 故障派单任务分页列表
	 */
	@SuppressWarnings("rawtypes")
	protected Page getFaultList(FaultQueryParameter faultQueryParameter,
			String key) {
		ConditionGenerate conditionGenerate = conditionGenerateMap.get(key);
		conditionGenerate.setQuerySql(faultQueryParameter);
		conditionGenerate.setPage(faultQueryParameter.getPage());
		Page page = getFaultBaseDao().queryPageForSql(conditionGenerate);
		return page;
	}

	/**
	 * @return BaseDao<T, PK> 业务操作Dao
	 */
	@Override
	protected BaseDao<T, PK> getBaseDao() {
		// TODO Auto-generated method stub
		return getFaultBaseDao();
	}

	/**
	 * 获取工单处理操作Dao
	 * 
	 * @return FaultBaseDao<T, PK> 业务操作Dao
	 */
	protected abstract FaultBaseDao<T, PK> getFaultBaseDao();
}
