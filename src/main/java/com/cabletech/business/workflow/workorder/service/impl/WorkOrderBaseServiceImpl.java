package com.cabletech.business.workflow.workorder.service.impl;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.base.model.CodeSequence;
import com.cabletech.business.base.service.CodeSequenceService;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.service.WorkOrderWorkflowService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;
import com.cabletech.common.util.StringUtil;

/**
 * 通用工单分发业务接口实现基类
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-01-04 添加getConditionGenerate方法和setQueryParameter方法
 * @author 杨隽 2012-01-13 添加通用工单的业务序号生成generateCodeSequence方法
 * @author 杨隽 2012-02-07 添加getWorkOrderTransferList()方法
 * @author 杨隽 2012-02-07 添加getBaseDao()方法实现和getWorkOrderBaseDao()抽象方法
 * @author 杨隽 2012-02-08 添加“查询条件生成器KEY”常量
 * @author 杨隽 2012-03-27 添加“短信发送信息配置文件参数”常量
 * @author 杨隽 2012-03-27 添加getUserPhone()方法和getUserName()方法
 * 
 * @param <T>
 * @param <PK>
 */
public abstract class WorkOrderBaseServiceImpl<T, PK extends Serializable>
		extends BaseServiceImpl<T, PK> {
	// “查询条件生成器KEY”常量
	// “获取工单草稿箱列表查询条件生成器KEY”
	protected static final String DRAFT_CONDITION_GENERATE_KEY = "draftConditionGenerateImpl";
	// “获取待办工单列表查询条件生成器KEY”
	protected static final String WAIT_HANDLED_TASK_CONDITION_GENERATE_KEY = "waitHandledTaskConditionGenerateImpl";
	// “获取已办工单列表查询条件生成器KEY”
	protected static final String HANDLED_TASK_CONDITION_GENERATE_KEY = "handledTaskConditionGenerateImpl";
	// “获取待取消工单列表查询条件生成器KEY”
	protected static final String WAIT_CANCELED_TASK_CONDITION_GENERATE_KEY = "waitCanceledTaskConditionGenerateImpl";
	// “获取已取消工单列表查询条件生成器KEY”
	protected static final String CANCELED_TASK_CONDITION_GENERATE_KEY = "canceledTaskConditionGenerateImpl";
	// “获取待删除工单列表查询条件生成器KEY”
	protected static final String WAIT_DELETED_TASK_CONDITION_GENERATE_KEY = "canceledTaskConditionGenerateImpl";
	// “通用工单编号查询条件生成器KEY”
	protected static final String TASK_ID_CONDITION_GENERATE_KEY = "taskIdConditionGenerateImpl";
	// “通用工单分发编号查询条件生成器KEY”
	protected static final String TRANSFER_ID_CONDITION_GENERATE_KEY = "transferIdConditionGenerateImpl";
	// 短信发送信息配置文件参数常量
	// 短信发送信息配置文件编号常量
	public static final String WORKORDER_XML_FILE_ID = "workorder";
	// 工单派发短信发送信息编号
	public static final String SEND_TASK_MSG_ID = "send_task";
	// 工单拒签短信发送信息编号
	public static final String REFUSE_TASK_MSG_ID = "refuse_task";
	// 工单拒签确认不通过短信发送信息编号
	public static final String CONFIRM_NOTPASSED_TASK_MSG_ID = "confirm_not_passed";
	// 工单拒签确认转审短信发送信息编号
	public static final String CONFIRM_TRANSFER_TASK_MSG_ID = "transfer_confirm";
	// 工单拒签确认通过短信发送信息编号
	public static final String CONFIRM_PASSED_TASK_MSG_ID = "confirm_passed";
	// 工单回复短信发送信息编号
	public static final String REPLY_TASK_MSG_ID = "reply_task";
	// 工单回复验证不通过短信发送信息编号
	public static final String APPROVE_NOTPASSED_TASK_MSG_ID = "approve_not_passed";
	// 工单回复转审短信发送信息编号
	public static final String APPROVE_TRANSFER_TASK_MSG_ID = "transfer_approve";
	// 工单回复验证通过短信发送信息编号
	public static final String APPROVE_PASSED_TASK_MSG_ID = "approve_passed";
	// 通用工单工作流业务处理
	@Resource(name = "workOrderWorkflowService")
	protected WorkOrderWorkflowService workOrderWorkflowService;
	// 用户信息业务处理
	@Resource(name = "userInfoServiceImpl")
	private UserInfoService userInfoService;
	// 查询条件生成器业务处理Map
	@Autowired
	private Map<String, ConditionGenerate> conditionGenerateMap;
	// 序号生成记录信息业务处理接口
	@Resource(name = "codeSequenceServiceImpl")
	private CodeSequenceService codeSequenceService;

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
	 * @param workOrder
	 *            WorkOrder 用户的输入信息的工单实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return QueryParameter 查询条件参数
	 */
	protected QueryParameter setQueryParameter(WorkOrder workOrder,
			UserInfo userInfo) {
		QueryParameter parameter = new QueryParameter();
		parameter.setUser(userInfo);
		parameter.setEntity(workOrder);
		return parameter;
	}

	/**
	 * 生成业务序号
	 * 
	 * @param user
	 *            UserInfo 当前用户信息
	 * @return String 业务序号
	 */
	protected String generateCodeSequence(UserInfo user) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMM");
		CodeSequence codeSequence = new CodeSequence();
		codeSequence.setDeptId(user.getOrgId());
		codeSequence.setDeptShort(user.getOrgName());
		codeSequence.setYearMonth(df.format(date));
		codeSequence.setTableName(WorkOrder.WTASK_ORDER_KEY);
		CodeSequence codeSequenceEntity = codeSequenceService
				.getCodeSequence(codeSequence);
		if (codeSequenceEntity == null) {
			codeSequenceEntity = codeSequence;
			codeSequenceEntity.setMaxId("0");
		}
		int maxId = Integer.parseInt(codeSequenceEntity.getMaxId()) + 1;
		codeSequenceEntity.setMaxId(StringUtil.lpad(Integer.toString(maxId),
				CodeSequence.PAD_STRING_LENGTH, CodeSequence.PAD_STRING));
		codeSequenceService.save(codeSequenceEntity);
		return codeSequenceEntity.getCodeSequenceString();
	}

	/**
	 * 根据输入参数和查询条件生成器Key获取工单任务分页列表
	 * 
	 * @param workOrder
	 *            WorkOrder 输入参数
	 * @param key
	 *            String 查询条件生成器Key
	 * @return Page 工单任务分页列表
	 */
	@SuppressWarnings("rawtypes")
	protected Page getWorkOrderTransferList(WorkOrder workOrder, String key) {
		workOrder.setConditionGenerateKey(key);
		QueryParameter parameter = new QueryParameter();
		parameter.setEntity(workOrder);
		parameter.setUser(workOrder.getLoginUser());
		ConditionGenerate conditionGenerate = getConditionGenerate(
				workOrder.getConditionGenerateKey(), parameter);
		conditionGenerate.setPage(workOrder.getPage());
		Page page = getWorkOrderBaseDao().queryPageForSql(conditionGenerate);
		return page;
	}

	/**
	 * @return BaseDao<T, PK> 工单处理操作Dao
	 */
	@Override
	protected BaseDao<T, PK> getBaseDao() {
		// TODO Auto-generated method stub
		return getWorkOrderBaseDao();
	}

	/**
	 * 根据用户编号获取用户联系电话
	 * 
	 * @param userId
	 *            String 用户编号
	 * @return String 用户联系电话
	 */
	protected String getUserPhone(String userId) {
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
	 * 根据用户编号获取用户姓名
	 * 
	 * @param userId
	 *            String 用户编号
	 * @return String 用户姓名
	 */
	protected String getUserName(String userId) {
		String userName = "";
		UserInfo user = userInfoService.getUserInfoByPersonId(userId);
		if (user != null) {
			userName = user.getUserName();
		}
		return userName;
	}

	/**
	 * 获取工单处理操作Dao
	 * 
	 * @return WorkOrderBaseDao<T, PK> 工单处理操作Dao
	 */
	protected abstract WorkOrderBaseDao<T, PK> getWorkOrderBaseDao();
}
