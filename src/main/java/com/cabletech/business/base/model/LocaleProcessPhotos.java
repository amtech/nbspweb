package com.cabletech.business.base.model;

import com.cabletech.common.base.BaseEntity;

/**
 * 现场处理过程图片实体
 * 
 * @author 杨隽 2012-01-09 创建
 * 
 */
public class LocaleProcessPhotos extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 现场处理过程对应实体编号
	private String taskId;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String id) {
		this.taskId = id;
	}
}
