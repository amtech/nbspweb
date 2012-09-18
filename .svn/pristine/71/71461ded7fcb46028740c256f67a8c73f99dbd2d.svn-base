package com.cabletech.business.assess.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * 考核管理-申诉记录
 * 
 * @author wj 2012-08-02 创建
 * 
 */
public class AssessAppealForm extends BaseEntity{
	// 考核归档结束状态
	public static final String END_STATE = "1";
	/**
	 * 功能常量
	 */
	public static final String REGIONTYPE_COUNTY = "1";//区域类型 ---- 县级
	public static final String REGIONTYPE_CITY = "2";//区域类型 ---- 市级
	public static final String APPROVE_CHECK = "1";//复核
	public static final String APPROVE_CONFIRM = "4";//确认
	public static final String FLOW_OVER_STATE = "1";//完成状态
	public static final String FLOW_NAME_APPEALFORM = "appealform";//申诉流程 名称
	public static final String TABLETYPE_MONTH = "01";//表格类型月考核
	public static final String TABLETYPE_YEAR = "02";//表格类型年考核
	public static final String TABLETYPE_EXAMINATION = "03";//表格类型现场检查
	
	/**
	 * 申诉单号
	 */
	private String appealFormId; 
	/**
	 * 申诉考核结果表
	 */
	private String examResultId; 
	/**
	 * 申诉代维公司
	 */
	private String contractorId; 
	/**
	 * 申诉人
	 */
	private String complainant; 
	/**
	 * 申诉原因
	 */
	private String cause; 
	/**
	 * 申诉时间
	 */
	private Date appealTime; 
	/**
	 * 复核结果
	 */
	private String checkResult; 
	/**
	 * 复核人
	 */
	private String reviewer; 
	/**
	 * 复核时间
	 */
	private Date  checkTime; 

	/**
	 * 复核意见
	 */
	private String checkOpinion; 
	
	/**
	 * 申诉结果确认
	 */
	private String conformResult; 
	/**
	 * 确认意见
	 */
	private String opinion; 
	/**
	 * 审核状态
	 */
	private String auditingState;
	
	/**
	 * 评分
	 */
	private double score;

	
	//非持久化----------------
	/**
	 * 工作流
	 * 任务ID
	 * 非持久化
	 */
	private String taskId;
	/**
	 * 工作流
	 * 处理步骤
	 * 非持久化
	 */
	private String step;
	
	/**
	 * 工作流
	 * 办理结果
	 * 非持久化
	 */
	private String transition;
	/**
	 * 工作流
	 * 批注
	 * 非持久化
	 */
	private String comment;
	
	/**
	 * 考核结果调整原因
	 * 非持久化
	 */
	private String[] adjusstmentCauses;
	
	/**
	 * 考核结果调整分数
	 * 非持久化
	 */
	private String[] adjusstmentScores;
	
	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the adjusstmentCauses
	 */
	public String[] getAdjusstmentCauses() {
		return adjusstmentCauses;
	}
	/**
	 * @param adjusstmentCauses the adjusstmentCauses to set
	 */
	public void setAdjusstmentCauses(String[] adjusstmentCauses) {
		this.adjusstmentCauses = adjusstmentCauses;
	}
	/**
	 * @return the adjusstmentScores
	 */
	public String[] getAdjusstmentScores() {
		return adjusstmentScores;
	}
	/**
	 * @param adjusstmentScores the adjusstmentScores to set
	 */
	public void setAdjusstmentScores(String[] adjusstmentScores) {
		this.adjusstmentScores = adjusstmentScores;
	}

	/**
	 * @return the appealFormId
	 */
	public String getAppealFormId() {
		return appealFormId;
	}
	/**
	 * @param appealFormId the appealFormId to set
	 */
	public void setAppealFormId(String appealFormId) {
		this.appealFormId = appealFormId;
	}
	/**
	 * @return the examResultId
	 */
	public String getExamResultId() {
		return examResultId;
	}
	/**
	 * @param examResultId the examResultId to set
	 */
	public void setExamResultId(String examResultId) {
		this.examResultId = examResultId;
	}

	/**
	 * @return the contractorId
	 */
	public String getContractorId() {
		return contractorId;
	}
	/**
	 * @param contractorId the contractorId to set
	 */
	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}
	/**
	 * @return the complainant
	 */
	public String getComplainant() {
		return complainant;
	}
	/**
	 * @param complainant the complainant to set
	 */
	public void setComplainant(String complainant) {
		this.complainant = complainant;
	}
	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}
	/**
	 * @param cause the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}
	/**
	 * @return the appealTime
	 */
	public Date getAppealTime() {
		return appealTime;
	}
	/**
	 * @param appealTime the appealTime to set
	 */
	public void setAppealTime(Date appealTime) {
		this.appealTime = appealTime;
	}
	/**
	 * @return the checkResult
	 */
	public String getCheckResult() {
		return checkResult;
	}
	/**
	 * @param checkResult the checkResult to set
	 */
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	/**
	 * @return the reviewer
	 */
	public String getReviewer() {
		return reviewer;
	}
	/**
	 * @param reviewer the reviewer to set
	 */
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	/**
	 * @return the checkTime
	 */
	public Date getCheckTime() {
		return checkTime;
	}
	/**
	 * @param checkTime the checkTime to set
	 */
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	/**
	 * @return the conformResult
	 */
	public String getConformResult() {
		return conformResult;
	}
	/**
	 * @param conformResult the conformResult to set
	 */
	public void setConformResult(String conformResult) {
		this.conformResult = conformResult;
	}
	/**
	 * @return the opinion
	 */
	public String getOpinion() {
		return opinion;
	}
	/**
	 * @param opinion the opinion to set
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	/**
	 * @return the auditingState
	 */
	public String getAuditingState() {
		return auditingState;
	}
	/**
	 * @param auditingState the auditingState to set
	 */
	public void setAuditingState(String auditingState) {
		this.auditingState = auditingState;
	} 
	/**
	 * @return the transition
	 */
	public String getTransition() {
		return transition;
	}
	/**
	 * @param transition the transition to set
	 */
	public void setTransition(String transition) {
		this.transition = transition;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
	 * @return the step
	 */
	public String getStep() {
		return step;
	}
	/**
	 * @param step the step to set
	 */
	public void setStep(String step) {
		this.step = step;
	}
	
	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}
	/**
	 * @return the checkOpinion
	 */
	public String getCheckOpinion() {
		return checkOpinion;
	}
	/**
	 * @param checkOpinion the checkOpinion to set
	 */
	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}
}