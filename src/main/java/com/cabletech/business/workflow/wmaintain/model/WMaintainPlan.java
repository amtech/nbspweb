package com.cabletech.business.workflow.wmaintain.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseEntity;
import com.cabletech.common.util.Page;

/**
 * 维修作业计划实体
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-16 添加区域编号页面查询属性、创建日期最小值属性和创建日期最大值属性
 * @author 杨隽 2012-04-23 添加无线资源巡检计划编号属性、资源站点编号属性和资源类型属性
 * @author 杨隽 2012-04-26 添加代维公司名称、工作流处理事件和工作流处理说明属性
 * @author 杨隽 2012-05-04 去除表单提交标识常量
 * @author 杨隽 2012-07-10 添加隐患现场处理记录数组属性
 * 
 */
public class WMaintainPlan extends BaseEntity {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 维修作业计划状态常量
	// 维修作业计划未提交状态
	public static final String WMAINTAIN_PLAN_NOTSUBMITED_STATE = "1";
	// 维修作业计划提交状态
	public static final String WMAINTAIN_PLAN_SUBMITED_STATE = "2";
	// 维修作业计划审核未通过状态
	public static final String WMAINTAIN_PLAN_NOTPASSED_STATE = "3";
	// 维修作业计划审核通过状态
	public static final String WMAINTAIN_PLAN_PASSED_STATE = "4";
	// 维修作业计划报告未提交状态
	public static final String WMAINTAIN_RECORD_NOTSUBMITED_STATE = "4";
	// 维修作业计划报告提交状态
	public static final String WMAINTAIN_RECORD_SUBMITED_STATE = "5";
	// 维修作业计划报告未通过状态
	public static final String WMAINTAIN_RECORD_NOTPASSED_STATE = "6";
	// 维修作业计划报告通过状态
	public static final String WMAINTAIN_END_STATE = "7";
	// 维修作业计划已取消状态
	public static final String WMAINTAIN_CANCELED_STATE = "8";
	// 系统编号
	private String id;
	// 计划名称
	private String planName;
	// 巡检组编号
	private String patrolGroup;
	// 专业类型
	private String businessType;
	// 开始时间
	private Date startDate;
	// 结束时间
	private Date endDate;
	// 制定人编号
	private String creater;
	// 制定时间
	private Date createDate;
	// 审核人编号
	private String auditor;
	// 计划状态
	private String planState;
	// 报告总结
	private String report;
	// 站点编号数组（用于页面输入）
	private String[] stationId;
	// 站点类型数组（用于页面输入）
	private String[] stationType;
	// 站点巡检记录编号数组（用于页面输入）
	private String[] patrolRecordId;
	// 站点巡检异常子项编号数组（用于页面输入）
	private String[] patrolItemId;
	// 审核结果（用于页面输入）
	private String approveResult;
	// 审核意见（用于页面输入）
	private String approveRemark;
	// 转审人（用于页面输入）
	private String transferApproverId;
	// 工作流流程任务编号（用于页面输入）
	private String workflowTaskId;
	// 是否提交审核（用于页面输入）
	private String isSubmited;
	// 当前处理人编号（用于后台工作流指定当前处理人）
	private String currentProcessUserId;
	// 下步处理人编号（用于后台工作流指定下步处理人）
	private String nextProcessUserId;
	// 工作流处理事件（用于后台工作流指定事件）
	private String workflowTransition;
	// 工作流处理说明（用于后台工作流指定处理说明）
	private String workflowComment;
	// 制定人名称（用于页面显示）
	private String createrName;
	// 审核人名称（用于页面显示）
	private String auditorName;
	// 巡检组名称（用于页面显示）
	private String patrolGroupName;
	// 代维公司名称（用于页面显示）
	private String orgName;
	// 维修作业计划中站点列表（用于页面显示）
	private List<Map<String, Object>> siteList;
	// 维修作业计划中站点异常项列表（用于页面显示）
	private List<Map<String, Object>> resultList;
	// 登录人员信息（用于页面查询）
	private UserInfo loginUser;
	// 页面信息（用于页面查询）
	@SuppressWarnings("rawtypes")
	private Page page;
	// 区域编号信息（用于页面查询）
	private String regionId;
	// 创建日期最小值（用于页面查询）
	private String createDateMin;
	// 创建日期最大值（用于页面查询）
	private String createDateMax;
	// 无线资源巡检计划编号（用于页面查询）
	private String wplanId;
	// 资源站点编号属性（用于页面查询）
	private String resourceId;
	// 资源类型属性（用于页面查询）
	private String resourceType;
	// 隐患现场处理记录数组（用于表单输入）
	private String resultId;
	private String resultMaintainResult;
	private String resultMaintainRecord;
	private String resultMaintainDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPatrolGroup() {
		return patrolGroup;
	}

	public void setPatrolGroup(String patrolGroup) {
		this.patrolGroup = patrolGroup;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getPlanState() {
		return planState;
	}

	public void setPlanState(String planState) {
		this.planState = planState;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String[] getStationId() {
		return stationId;
	}

	public void setStationId(String[] stationId) {
		this.stationId = stationId;
	}

	public String[] getStationType() {
		return stationType;
	}

	public void setStationType(String[] stationType) {
		this.stationType = stationType;
	}

	public String[] getPatrolRecordId() {
		return patrolRecordId;
	}

	public void setPatrolRecordId(String[] patrolRecordId) {
		this.patrolRecordId = patrolRecordId;
	}

	public String[] getPatrolItemId() {
		return patrolItemId;
	}

	public void setPatrolItemId(String[] patrolItemId) {
		this.patrolItemId = patrolItemId;
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

	public String getTransferApproverId() {
		return transferApproverId;
	}

	public void setTransferApproverId(String transferApproverId) {
		this.transferApproverId = transferApproverId;
	}

	public String getWorkflowTaskId() {
		return workflowTaskId;
	}

	public void setWorkflowTaskId(String taskId) {
		this.workflowTaskId = taskId;
	}

	public String getIsSubmited() {
		return isSubmited;
	}

	public void setIsSubmited(String isSubmited) {
		this.isSubmited = isSubmited;
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

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getPatrolGroupName() {
		return patrolGroupName;
	}

	public void setPatrolGroupName(String patrolGroupName) {
		this.patrolGroupName = patrolGroupName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<Map<String, Object>> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<Map<String, Object>> siteList) {
		this.siteList = siteList;
	}

	public List<Map<String, Object>> getResultList() {
		return resultList;
	}

	public void setResultList(List<Map<String, Object>> resultList) {
		this.resultList = resultList;
	}

	public UserInfo getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(UserInfo loginUser) {
		this.loginUser = loginUser;
	}

	@SuppressWarnings("rawtypes")
	public Page getPage() {
		return page;
	}

	@SuppressWarnings("rawtypes")
	public void setPage(Page page) {
		this.page = page;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
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

	public String getWplanId() {
		return wplanId;
	}

	public void setWplanId(String wplanId) {
		this.wplanId = wplanId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getResultMaintainResult() {
		return resultMaintainResult;
	}

	public void setResultMaintainResult(String resultMaintainResult) {
		this.resultMaintainResult = resultMaintainResult;
	}

	public String getResultMaintainRecord() {
		return resultMaintainRecord;
	}

	public void setResultMaintainRecord(String resultMaintainRecord) {
		this.resultMaintainRecord = resultMaintainRecord;
	}

	public String getResultMaintainDate() {
		return resultMaintainDate;
	}

	public void setResultMaintainDate(String resultMaintainDate) {
		this.resultMaintainDate = resultMaintainDate;
	}
}
