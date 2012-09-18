package com.cabletech.business.ah.rating.model;

import com.cabletech.common.base.BaseEntity;

/**
 * 处理记录考核对应表
 * @author wangt
 *
 */
public class ExamVerifyRecord extends BaseEntity {
	private String examverifyid;
	private String monthid;
	public String getExamverifyid() {
		return examverifyid;
	}
	public void setExamverifyid(String examverifyid) {
		this.examverifyid = examverifyid;
	}
	public String getMonthid() {
		return monthid;
	}
	public void setMonthid(String monthid) {
		this.monthid = monthid;
	}

}
