package com.cabletech.business.assess.model;

import com.cabletech.common.base.BaseEntity;


/**
 * 现场检查-检查明细
 * 
 * @author wj 2012-07-31 创建
 * 
 */
public class AssessMentDetail extends BaseEntity {
	
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 考核结果id
	 */
	private String resultId;
	
	/**
	 * 考核内容id
	 */
	private String contentId;
	
	
	/**
	 * 指标完成值
	 */
	private double indicatorsValue;
	
	/**
	 * 评分
	 */
	private double score;
	
	/**
	 * 评分说明
	 */
	private String ratingDesc;

	/**
	 * @return the resultId
	 */
	public String getResultId() {
		return resultId;
	}

	/**
	 * @param resultId the resultId to set
	 */
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}

	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	/**
	 * @return the indicatorsValue
	 */
	public double getIndicatorsValue() {
		return indicatorsValue;
	}

	/**
	 * @param indicatorsValue the indicatorsValue to set
	 */
	public void setIndicatorsValue(double indicatorsValue) {
		this.indicatorsValue = indicatorsValue;
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
	 * @return the ratingDesc
	 */
	public String getRatingDesc() {
		return ratingDesc;
	}

	/**
	 * @param ratingDesc the ratingDesc to set
	 */
	public void setRatingDesc(String ratingDesc) {
		this.ratingDesc = ratingDesc;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}