package com.cabletech.business.base.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.cabletech.common.base.BaseEntity;

/**
 * 上传文件实体
 * 
 * @author Administrator
 */
public class UploadFile extends BaseEntity {
	/**
	 * 文件ID
	 */
	private String fileId;
	/**
	 * 保存路径
	 */
	private String savePath;
	/**
	 * 文件类型
	 */
	private String fileType;
	/**
	 * 文件大小
	 */
	private long fileSize;
	/**
	 * 原名称
	 */
	private String originalName;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 种类
	 */
	private String catlog;

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @return the savePath
	 */
	public String getSavePath() {
		return savePath;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * @return the originalName
	 */
	public String getOriginalName() {
		return originalName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the catlog
	 */
	public String getCatlog() {
		return catlog;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * @param savePath
	 *            the savePath to set
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	/**
	 * @param fileType
	 *            the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @param fileSize
	 *            the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @param originalName
	 *            the originalName to set
	 */
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param catlog
	 *            the catlog to set
	 */
	public void setCatlog(String catlog) {
		this.catlog = catlog;
	}

	/* 重构Tosting方法
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("fileId", this.fileId)
				.append("fileType", this.fileType)
				.append("fileSize", this.fileSize)
				.append("originalName", this.originalName)
				.append("description", this.description)
				.append("catlog", this.catlog)
				.append("fileSize", this.fileSize).toString();

	}

}
