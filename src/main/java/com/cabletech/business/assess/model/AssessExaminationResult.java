package com.cabletech.business.assess.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cabletech.common.base.BaseEntity;

/**
 * 考核/检查结果
 * 
 * @author wj 2012-07-31 创建
 * @author 杨隽 2012-08-03 添加“是否提交”属性
 * 
 */
public class AssessExaminationResult extends BaseEntity {
	// 提交标识
	public static final String IS_SUBMITED = "1";
	// 保存标识
	public static final String ISNOT_SUBMITED = "0";
	// 月度考核标志
	public static final String MONTH_ASSESS_ = "01";
	// 年度考核标志
	public static final String YEAR_ASSESS_ = "02";
	// 考核归档结束状态
	public static final String END_STATE = "3";
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 模版表ID
	 */
	private String tableId;
	/**
	 * 代维公司
	 */
	private String contractorId;
	/**
	 * 考核年/月份
	 */
	private Date appraiseMonth;
	/**
	 * 维护责任人
	 */
	private String principal;
	/**
	 * 站点id
	 */
	private String siteId;
	/**
	 * 检查日期
	 */
	private Date inspectionDate;
	/**
	 * 检查人员
	 */
	private String inspector;
	/**
	 * 评分
	 */
	private double score;
	/**
	 * 区域
	 */
	private String regionId;
	/**
	 * 状态
	 */
	private String state;
	// ---------------- 非持久化 -----------------------------------
	/**
	 * 是否提交标识
	 */
	private String isSubmited;
	/**
	 * 是否进入申诉流程
	 */
	private String apply;
	/**
	 * 模板表类型
	 */
	private String tableType;
	/**
	 * 考核明细编号
	 */
	private String[] itemId;
	/**
	 * 考核指标编号
	 */
	private String[] contentId;
	/**
	 * 指标完成值
	 */
	private Double[] indicatorsValue;
	/**
	 * 评分
	 */
	private Double[] itemScore;
	/**
	 * 评分说明
	 */
	private String[] ratingDesc;
	/**
	 * 考核指标明细列表
	 */
	private List<Map<String, Object>> detailList;
	/**
	 * 考核指标最深级别
	 */
	private int level;
	/**
	 * 工作流任务编号
	 */
	private String taskId;
	/**
	 * 审批结果
	 */
	private String approveResult;
	/**
	 * 审批意见
	 */
	private String approveRemark;

	/**
	 * @return the itemScore
	 */
	public Double[] getItemScore() {
		return itemScore;
	}

	/**
	 * @param itemScore
	 *            the scoreLs to set
	 */
	public void setItemScore(Double[] itemScore) {
		this.itemScore = itemScore;
	}

	/**
	 * @return the ratingDescLs
	 */
	public String[] getRatingDesc() {
		return ratingDesc;
	}

	/**
	 * @param ratingDescLs
	 *            the ratingDescLs to set
	 */
	public void setRatingDesc(String[] ratingDescLs) {
		this.ratingDesc = ratingDescLs;
	}

	/**
	 * @return the tableId
	 */
	public String getTableId() {
		return tableId;
	}

	/**
	 * @param tableId
	 *            the tableId to set
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	/**
	 * @return the contractorId
	 */
	public String getContractorId() {
		return contractorId;
	}

	/**
	 * @param contractorId
	 *            the contractorId to set
	 */
	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}

	/**
	 * @return the appraiseMonth
	 */
	public Date getAppraiseMonth() {
		return appraiseMonth;
	}

	/**
	 * @param appraiseMonth
	 *            the appraiseMonth to set
	 */
	public void setAppraiseMonth(Date appraiseMonth) {
		this.appraiseMonth = appraiseMonth;
	}

	/**
	 * @return the principal
	 */
	public String getPrincipal() {
		return principal;
	}

	/**
	 * @param principal
	 *            the principal to set
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	/**
	 * @return the siteId
	 */
	public String getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId
	 *            the siteId to set
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	/**
	 * @return the inspectionDate
	 */
	public Date getInspectionDate() {
		return inspectionDate;
	}

	/**
	 * @param inspectionDate
	 *            the inspectionDate to set
	 */
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	/**
	 * @return the inspector
	 */
	public String getInspector() {
		return inspector;
	}

	/**
	 * @param inspector
	 *            the inspector to set
	 */
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * @return the regionId
	 */
	public String getRegionId() {
		return regionId;
	}

	/**
	 * @param regionId
	 *            the regionId to set
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getIsSubmited() {
		return isSubmited;
	}

	public void setIsSubmited(String isSubmited) {
		this.isSubmited = isSubmited;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String[] getContentId() {
		return contentId;
	}

	public void setContentId(String[] contentId) {
		this.contentId = contentId;
	}

	public Double[] getIndicatorsValue() {
		return indicatorsValue;
	}

	public void setIndicatorsValue(Double[] indicatorsValue) {
		this.indicatorsValue = indicatorsValue;
	}

	public String[] getItemId() {
		return itemId;
	}

	public void setItemId(String[] itemId) {
		this.itemId = itemId;
	}

	public List<Map<String, Object>> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<Map<String, Object>> detailList) {
		this.detailList = detailList;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getApply() {
		return apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
