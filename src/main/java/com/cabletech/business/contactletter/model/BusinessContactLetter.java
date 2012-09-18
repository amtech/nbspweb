package com.cabletech.business.contactletter.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * @author zhougang 2012 07 20 业务联系函的实体bean
 */
public class BusinessContactLetter extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String documentType;// 类型
	private String title;// 主题
	private String documentNumber;// 文号
	private String releaseUserId;// 发布人
	private Date releaseTime;// 发布时间
	private String releaseContent;// 发布内容
	private String isAudit;// 是否审核
	private String auditUserId;// 审核人
	private String isEmergency;// 是否紧急
	private String status;// 状态
	private String fileInfo; // 附件
	private Date expirationTime;// 过期时限
	private String isSend;// 是否发短信息

	private Date beginDate;
	private Date endDate;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIsSend() {
		return isSend;
	}

	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	// 不保存至数据库中
	private String issendMessage;// 是否发送短信
	private String releaseUserName;
	private String auditUserName;
	private String releaseUserDept;
	private String issuerAreaUserNames;

	public String getIssuerAreaUserNames() {
		return issuerAreaUserNames;
	}

	public void setIssuerAreaUserNames(String issuerAreaUserNames) {
		this.issuerAreaUserNames = issuerAreaUserNames;
	}

	public String getReleaseUserName() {
		return releaseUserName;
	}

	public void setReleaseUserName(String releaseUserName) {
		this.releaseUserName = releaseUserName;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getReleaseUserDept() {
		return releaseUserDept;
	}

	public void setReleaseUserDept(String releaseUserDept) {
		this.releaseUserDept = releaseUserDept;
	}

	public String getIssendMessage() {
		return issendMessage;
	}

	public void setIssendMessage(String issendMessage) {
		this.issendMessage = issendMessage;
	}

	private String issuerAreaUserIds; // 发布范围 页面显示而已

	public String getIssuerAreaUserIds() {
		return issuerAreaUserIds;
	}

	public void setIssuerAreaUserIds(String issuerAreaUserIds) {
		this.issuerAreaUserIds = issuerAreaUserIds;
	}

	public String getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getReleaseUserId() {
		return releaseUserId;
	}

	public void setReleaseUserId(String releaseUserId) {
		this.releaseUserId = releaseUserId;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getReleaseContent() {
		return releaseContent;
	}

	public void setReleaseContent(String releaseContent) {
		this.releaseContent = releaseContent;
	}

	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	public String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getIsEmergency() {
		return isEmergency;
	}

	public void setIsEmergency(String isEmergency) {
		this.isEmergency = isEmergency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

}
