package com.cabletech.business.workflow.electricity.security.model;

import java.util.Date;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseEntity;
import com.cabletech.common.util.Page;

/**
 * 油机调度任务实体
 * 
 * @author 杨隽 2012-05-03 创建
 * @author 杨隽 2012-05-07 添加createDateMin、createDateMax属性
 * 
 */
public class OeDispatchTask extends BaseEntity {
	// 供电保障派单提交标识
	public static final String IS_SUBMITED = "1";
	// 供电保障派单保存标识
	public static final String ISNOT_SUBMITED = "0";
	// 断电告警派单待派单状态
	public static final String WAIT_DISPATCHED_STATE = "1";
	// 断电告警派单待调度状态
	public static final String WAIT_SCHEDULED_STATE = "2";
	// 断电告警派单处理中状态
	public static final String PROCESSING_STATE = "3";
	// 断电告警派单回单审核状态
	public static final String WAIT_APPROVED_STATE = "4";
	// 断电告警派单处理结束状态
	public static final String END_STATE = "5";
	// 断电告警派单已取消状态
	public static final String CANCELED_STATE = "6";
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	// 供电派单标题
	private String title;
	// 断电站点编号
	private String stationId;
	// 站点类型
	private String stationType;
	// 站点地址
	private String stationAddress;
	// 断电原因
	private String blackoutReason;
	// 断电时间
	private Date blackoutTime;
	// 维护单位编号
	private String maintenanceId;
	// 派单人编号
	private String creator;
	// 派单时间
	private Date createDate;
	// 原来维护点编号
	private String preStationId;
	// 调度油机编号
	private String oilengineId;
	// 调度人编号
	private Date dispatchTime;
	// 调度时间
	private String yardMan;
	// 调度说明
	private String dispatchRemark;
	// 是否断站
	private String offStation;
	// 任务单状态
	private String state;
	// 断电告警单编号
	private String alarmId;
	// 回单内容
	private String replyContent;
	// 回单人编号
	private String replyPerson;
	// 回单时间
	private Date replyDate;
	// 工单编号
	private String taskCode;
	// 处理时限
	private Date handleLimit;
	// 受理时限
	private Date acceptLimit;
	// 服务区域
	private String serviceRegion;
	// 回单审核结果（表单数据，不存储数据库）
	private String approveResult;
	// 回单审核意见（表单数据，不存储数据库）
	private String approveRemark;
	// 回单转审人编号（表单数据，不存储数据库）
	private String transferApprover;
	// 专业类型
	private String businessType;
	// 是否提交（表单数据，不存储数据库）
	private String isSubmited;
	// 断电站点名称（用于页面显示）
	private String stationName;
	// 维护单位名称（用于页面显示）
	private String orgName;
	// 创建人名称（用于页面显示）
	private String createrName;
	// 调度人名称（用于页面显示）
	private String yardManName;
	// 回单人名称（用于页面显示）
	private String replyPersonName;
	// 当前处理人编号（用于后台工作流指定当前处理人）
	private String currentProcessUserId;
	// 下步处理人编号（用于后台工作流指定下步处理人）
	private String nextProcessUserId;
	// 下步处理组织编号（用于后台工作流指定下步处理组织）
	private String nextProcessOrgId;
	// 工作流处理事件（用于后台工作流指定事件）
	private String workflowTransition;
	// 工作流处理说明（用于后台工作流指定处理说明）
	private String workflowComment;
	// 供电保障业务工作流编号（用于后台工作流）
	private String workflowTaskId;
	// 分页数据信息（用于页面查询）
	@SuppressWarnings("rawtypes")
	private Page page;
	// 创建时间开始比较日期（用于页面查询）
	private String createDateMin;
	// 创建时间结束比较日期（用于页面查询）
	private String createDateMax;
	// 当前用户信息（用于页面查询）
	private UserInfo loginUser;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	public String getStationAddress() {
		return stationAddress;
	}

	public void setStationAddress(String stationAddress) {
		this.stationAddress = stationAddress;
	}

	public String getBlackoutReason() {
		return blackoutReason;
	}

	public void setBlackoutReason(String blackoutReason) {
		this.blackoutReason = blackoutReason;
	}

	public Date getBlackoutTime() {
		return blackoutTime;
	}

	public void setBlackoutTime(Date blackoutTime) {
		this.blackoutTime = blackoutTime;
	}

	public String getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(String maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getPreStationId() {
		return preStationId;
	}

	public void setPreStationId(String preStationId) {
		this.preStationId = preStationId;
	}

	public String getOilengineId() {
		return oilengineId;
	}

	public void setOilengineId(String oilengineId) {
		this.oilengineId = oilengineId;
	}

	public Date getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	public String getYardMan() {
		return yardMan;
	}

	public void setYardMan(String yardMan) {
		this.yardMan = yardMan;
	}

	public String getDispatchRemark() {
		return dispatchRemark;
	}

	public void setDispatchRemark(String dispatchRemark) {
		this.dispatchRemark = dispatchRemark;
	}

	public String getOffStation() {
		return offStation;
	}

	public void setOffStation(String offStation) {
		this.offStation = offStation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyPerson() {
		return replyPerson;
	}

	public void setReplyPerson(String replyPerson) {
		this.replyPerson = replyPerson;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getApproveResult() {
		return approveResult;
	}

	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}

	public String getApproveRemark() {
		return approveRemark;
	}

	public void setApproveRemark(String approveRemark) {
		this.approveRemark = approveRemark;
	}

	public String getTransferApprover() {
		return transferApprover;
	}

	public void setTransferApprover(String transferApprover) {
		this.transferApprover = transferApprover;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getIsSubmited() {
		return isSubmited;
	}

	public void setIsSubmited(String isSubmited) {
		this.isSubmited = isSubmited;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getCreaterName() {
		return createrName;
	}

	public String getYardManName() {
		return yardManName;
	}

	public void setYardManName(String yardManName) {
		this.yardManName = yardManName;
	}

	public String getReplyPersonName() {
		return replyPersonName;
	}

	public void setReplyPersonName(String replyPersonName) {
		this.replyPersonName = replyPersonName;
	}

	public String getCurrentProcessUserId() {
		return currentProcessUserId;
	}

	public void setCurrentProcessUserId(String currentProcessUserId) {
		this.currentProcessUserId = currentProcessUserId;
	}

	public String getNextProcessUserId() {
		return nextProcessUserId;
	}

	public void setNextProcessUserId(String nextProcessUserId) {
		this.nextProcessUserId = nextProcessUserId;
	}

	public String getNextProcessOrgId() {
		return nextProcessOrgId;
	}

	public void setNextProcessOrgId(String nextProcessOrgId) {
		this.nextProcessOrgId = nextProcessOrgId;
	}

	public String getWorkflowTransition() {
		return workflowTransition;
	}

	public void setWorkflowTransition(String workflowTransition) {
		this.workflowTransition = workflowTransition;
	}

	public String getWorkflowComment() {
		return workflowComment;
	}

	public void setWorkflowComment(String workflowComment) {
		this.workflowComment = workflowComment;
	}

	public String getWorkflowTaskId() {
		return workflowTaskId;
	}

	public void setWorkflowTaskId(String workflowTaskId) {
		this.workflowTaskId = workflowTaskId;
	}

	@SuppressWarnings("rawtypes")
	public void setPage(Page page) {
		this.page = page;
	}

	@SuppressWarnings("rawtypes")
	public Page getPage() {
		return page;
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

	public void setLoginUser(UserInfo loginUser) {
		this.loginUser = loginUser;
	}

	public UserInfo getLoginUser() {
		return loginUser;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public Date getHandleLimit() {
		return handleLimit;
	}

	public void setHandleLimit(Date handleLimit) {
		this.handleLimit = handleLimit;
	}

	public Date getAcceptLimit() {
		return acceptLimit;
	}

	public void setAcceptLimit(Date acceptLimit) {
		this.acceptLimit = acceptLimit;
	}

	public String getServiceRegion() {
		return serviceRegion;
	}

	public void setServiceRegion(String serviceRegion) {
		this.serviceRegion = serviceRegion;
	}
}
