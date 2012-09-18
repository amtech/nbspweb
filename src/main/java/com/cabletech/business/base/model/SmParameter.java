package com.cabletech.business.base.model;

import java.util.Date;

import com.cabletech.common.base.BaseEntity;

/**
 * 短信发送参数实体
 * 
 * @author 杨隽 2012-03-26 创建
 * @author 杨隽 2012-03-28 添加“是否发送短信标志”参数
 * 
 */
public class SmParameter extends BaseEntity {
	/**
	 * 超时前预提醒
	 */
	public static final String CLOSED_DEADLINE_SMS_TYPE = "001";
	/**
	 * 超时提醒
	 */
	public static final String TIMEOUT_SMS_TYPE = "002";
	/**
	 * 超时后提醒
	 */
	public static final String TIMEOUT_UPGRADE_SMS_TYPE = "003";
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String workorderId;
	/**
	 * 
	 */
	private String businessType;
	/**
	 * 
	 */
	private String workorderType;
	/**
	 * 
	 */
	private String workorderTitle;
	/**
	 * 
	 */
	private Date handleLimit;
	/**
	 * 
	 */
	private String handlePersonId;
	/**
	 * 
	 */
	private String smsSendFlg = "0";
	// 发送短信的xml文件id编号
	private String xmlFileId;
	// 发送短信的目标手机号码
	private String simId;
	// 发送短信的xml文件中信息编号
	private String xmlMessageId;
	// 发送短信的xml文件信息中的参数数据
	private String[] contentParameters;
	// 是否发送短信标志
	private boolean isSentSm = true;
	/**
	 * 
	 */
	private boolean isWrittenDb = false;

	/**
	 * 初始化短信发送参数
	 * 
	 * @param xmlFileId
	 *            String 发送短信的xml文件id编号
	 * @param xmlMessageId
	 *            String 发送短信的xml文件中信息编号
	 * @param simId
	 *            String 发送短信的目标手机号码
	 * @param contentParameters
	 *            String[] 发送短信的xml文件信息中的参数数据
	 * @return SmParameter 短信发送参数
	 */
	public static SmParameter getInstance(String xmlFileId,
			String xmlMessageId, String simId, String[] contentParameters) {
		SmParameter parameter = new SmParameter();
		parameter.setContentParameters(contentParameters);
		parameter.setSimId(simId);
		parameter.setXmlFileId(xmlFileId);
		parameter.setXmlMessageId(xmlMessageId);
		return parameter;
	}

	public String getXmlFileId() {
		return xmlFileId;
	}

	public void setXmlFileId(String xmlFileId) {
		this.xmlFileId = xmlFileId;
	}

	public String getSimId() {
		return simId;
	}

	public void setSimId(String simId) {
		this.simId = simId;
	}

	public String getXmlMessageId() {
		return xmlMessageId;
	}

	public void setXmlMessageId(String xmlMessageId) {
		this.xmlMessageId = xmlMessageId;
	}

	public String[] getContentParameters() {
		return contentParameters;
	}

	public void setContentParameters(String[] contentParameters) {
		this.contentParameters = contentParameters;
	}

	public boolean isSentSm() {
		return isSentSm;
	}

	public void setSentSm(boolean isSentSm) {
		this.isSentSm = isSentSm;
	}

	public String getWorkorderId() {
		return workorderId;
	}

	public void setWorkorderId(String workorderId) {
		this.workorderId = workorderId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getWorkorderType() {
		return workorderType;
	}

	public void setWorkorderType(String workorderType) {
		this.workorderType = workorderType;
	}

	public String getWorkorderTitle() {
		return workorderTitle;
	}

	public void setWorkorderTitle(String workorderTitle) {
		this.workorderTitle = workorderTitle;
	}

	public Date getHandleLimit() {
		return handleLimit;
	}

	public void setHandleLimit(Date handleLimit) {
		this.handleLimit = handleLimit;
	}

	public String getHandlePersonId() {
		return handlePersonId;
	}

	public void setHandlePersonId(String handlePersonId) {
		this.handlePersonId = handlePersonId;
	}

	public String getSmsSendFlg() {
		return smsSendFlg;
	}

	public void setSmsSendFlg(String smsSendFlg) {
		this.smsSendFlg = smsSendFlg;
	}

	public boolean isWrittenDb() {
		return isWrittenDb;
	}

	public void setWrittenDb(boolean isWrittenDb) {
		this.isWrittenDb = isWrittenDb;
	}

	/**
	 * 判断短信参数传递对象是否为空
	 * 
	 * @param parameter
	 *            SmParameter
	 * @return
	 */
	public static boolean isNotEmpty(SmParameter parameter) {
		return parameter != null;
	}
}
