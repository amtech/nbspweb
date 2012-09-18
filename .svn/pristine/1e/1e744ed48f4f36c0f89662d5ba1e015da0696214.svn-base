package com.cabletech.business.workflow.workorder.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseEntity;
import com.cabletech.common.util.Page;

/**
 * 通用工单实体
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-01-04 添加“工单被取消状态”常量
 * @author 杨隽 2012-01-04 去除“工单状态”实体列
 * @author 杨隽 2012-01-09 添加“工单状态”查询使用的表单数据变量
 * @author 杨隽 2012-01-10 添加“工单上传附件”数据变量、工单派单附件类型常量和工单回单附件类型常量
 * @author 杨隽 2012-01-13 添加“创建人信息”数据变量
 * @author 杨隽 2012-01-16 添加“分页信息”数据变量
 * @author 杨隽 2012-02-07 添加“条件生成器业务服务的key值”数据变量和“登录用户信息”数据变量
 * @author 杨隽 2012-05-04 去除表单提交标识常量
 * 
 */
public class WorkOrder extends BaseEntity {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	// 工单派单附件类型
	public static final String WTASK_ORDER_KEY = "WTASK_ORDER";
	// 工单回单附件类型
	public static final String WTASK_RECEIPT_KEY = "WTASK_RECEIPT";
	// 工单未提交状态
	public static final String WORKORDER_NOT_SUBMITED_STATE = "0";
	// 工单签收状态
	public static final String WORKORDER_SIGN_FOR_STATE = "2";
	// 工单回复状态
	public static final String WORKORDER_REPLY_STATE = "3";
	// 工单验证状态
	public static final String WORKORDER_REPLY_CHECK_STATE = "4";
	// 工单结束状态
	public static final String WORKORDER_END_STATE = "5";
	// 工单被取消状态
	public static final String WORKORDER_CANCELED_STATE = "6";
	// 工单拒签状态
	public static final String WORKORDER_REFUSE_STATE = "a";
	// 工单拒签确认状态
	public static final String WORKORDER_REFUSE_CONFIRM_STATE = "b";
	// 父工单ID
	private String parentId;
	// 工单单号
	private String taskCode;
	// 工单标题
	private String taskName;
	// 工单类型
	private String taskType;
	// 工单详细信息编号
	private String infoId;
	// 处理时限
	private Date overtimeSet;
	// 建单日期
	private Date createDate;
	// 建单人
	private String creater;
	// 维护专业
	private String businessType;
	// 紧急程度
	private String emergencyLevel;
	// 受理时限
	private Date acceptanceLimit;
	// 上传附件列表
	private List<FileItem> fileList;
	// 工单任务要求（表单数据，不存储数据库）
	private String taskRequest;
	// 工单是否提交（表单数据，不存储数据库）
	private String isSubmited;
	// 工单受理人（表单数据，存储到子表中）
	private String acceptUserIds;
	// 工单受理人名称（显示数据，不存储到数据库）
	private String acceptUserNames;
	// 建单日期开始搜索查询条件（表单数据，不存储数据库）
	private String createDateMin;
	// 建单日期结束搜索查询条件（表单数据，不存储数据库）
	private String createDateMax;
	// 建单人名称（显示数据，不存储到数据库）
	private String createrName;
	// 受理人列表信息（引用数据，不存储到数据库）
	private List<String> acceptUserList = new ArrayList<String>();
	// 工单状态（表单数据，不存储数据库）
	private String taskState;
	// 创建人信息（会话数据，不存储数据库）
	private UserInfo user;
	// 分页信息数据（列表分页数据，不存储数据库）
	@SuppressWarnings("rawtypes")
	private Page page;
	// 条件生成器业务服务的key值（业务数据，不存储数据库）
	private String conditionGenerateKey;
	// 登录用户信息（业务数据，不存储数据库）
	private UserInfo loginUser;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public Date getOvertimeSet() {
		return overtimeSet;
	}

	public void setOvertimeSet(Date overtimeSet) {
		this.overtimeSet = overtimeSet;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String bussinesType) {
		this.businessType = bussinesType;
	}

	public List<FileItem> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileItem> fileList) {
		this.fileList = fileList;
	}

	public String getTaskRequest() {
		return taskRequest;
	}

	public void setTaskRequest(String taskRequest) {
		this.taskRequest = taskRequest;
	}

	public String getIsSubmited() {
		return isSubmited;
	}

	public void setIsSubmited(String isSubmited) {
		this.isSubmited = isSubmited;
	}

	public String getAcceptUserIds() {
		return acceptUserIds;
	}

	public void setAcceptUserIds(String acceptUserIds) {
		this.acceptUserIds = acceptUserIds;
	}

	public String getAcceptUserNames() {
		return acceptUserNames;
	}

	public void setAcceptUserNames(String acceptUserNames) {
		this.acceptUserNames = acceptUserNames;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public List<String> getAcceptUserList() {
		return acceptUserList;
	}

	public void setAcceptUserList(List<String> acceptUserList) {
		this.acceptUserList = acceptUserList;
	}

	public String getCreateDateMin() {
		return createDateMin;
	}

	public void setCreateDateMin(String createDateMin) {
		this.createDateMin = createDateMin;
	}

	public String getCreateDateMax() {
		return createDateMax;
	}

	public void setCreateDateMax(String createDateMax) {
		this.createDateMax = createDateMax;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public UserInfo getUser() {
		return user;
	}

	/**
	 * 
	 * @param user
	 *            UserInfo
	 */
	public void setUser(UserInfo user) {
		this.user = user;
		setCreater(user.getPersonId());
		setCreaterName(user.getUserName());
	}

	@SuppressWarnings("rawtypes")
	public Page getPage() {
		return page;
	}

	@SuppressWarnings("rawtypes")
	public void setPage(Page page) {
		this.page = page;
	}

	public String getConditionGenerateKey() {
		return conditionGenerateKey;
	}

	public void setConditionGenerateKey(String conditionGenerateKey) {
		this.conditionGenerateKey = conditionGenerateKey;
	}

	public UserInfo getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(UserInfo loginUser) {
		this.loginUser = loginUser;
	}

	/**
	 * 根据工作流的任务状态获取通用工单的状态业务数据
	 * 
	 * @param workflowTaskName
	 *            workflowTaskName 工作流的任务状态
	 * @return String 通用工单的状态业务数据
	 */
	public static String getTaskState(String workflowTaskName) {
		// TODO Auto-generated method stub
		Map<String, String> taskStateMap = initTaskStateMap();
		if (StringUtils.isNotBlank(workflowTaskName)) {
			return taskStateMap.get(workflowTaskName);
		} else {
			return WORKORDER_END_STATE;
		}
	}

	/**
	 * 初始化通用工单的状态业务数据map
	 * 
	 * @return String 通用工单的状态业务数据map
	 */
	private static Map<String, String> initTaskStateMap() {
		Map<String, String> taskStateMap = new HashMap<String, String>();
		taskStateMap.put("签收", WORKORDER_SIGN_FOR_STATE);
		taskStateMap.put("填写回单", WORKORDER_REPLY_STATE);
		taskStateMap.put("验证回单", WORKORDER_REPLY_CHECK_STATE);
		taskStateMap.put("退单审核", WORKORDER_REFUSE_CONFIRM_STATE);
		return taskStateMap;
	}

	/**
	 * 根据通用工单的状态业务数据获取工作流的任务状态
	 * 
	 * @param workflowTaskName
	 *            workflowTaskName 通用工单的状态业务数据
	 * @return String 工作流的任务状态
	 */
	public static String getTaskStateName(String workflowTaskName) {
		// TODO Auto-generated method stub
		Map<String, String> taskStateMap = initTaskStateNameMap();
		if (StringUtils.isNotBlank(workflowTaskName)) {
			return taskStateMap.get(workflowTaskName);
		} else {
			return WORKORDER_END_STATE;
		}
	}

	/**
	 * 初始化工作流的任务状态map
	 * 
	 * @return Map<String, String> 工作流的任务状态map
	 */
	private static Map<String, String> initTaskStateNameMap() {
		Map<String, String> taskStateMap = new HashMap<String, String>();
		taskStateMap.put(WORKORDER_SIGN_FOR_STATE, "签收");
		taskStateMap.put(WORKORDER_REPLY_STATE, "填写回单");
		taskStateMap.put(WORKORDER_REPLY_CHECK_STATE, "验证回单");
		taskStateMap.put(WORKORDER_REFUSE_CONFIRM_STATE, "退单审核");
		taskStateMap.put(WORKORDER_END_STATE, "已归档");
		return taskStateMap;
	}

	public String getEmergencyLevel() {
		return emergencyLevel;
	}

	public void setEmergencyLevel(String emergencyLevel) {
		this.emergencyLevel = emergencyLevel;
	}

	public Date getAcceptanceLimit() {
		return acceptanceLimit;
	}

	public void setAcceptanceLimit(Date acceptanceLimit) {
		this.acceptanceLimit = acceptanceLimit;
	}
}
