package com.cabletech.business.workflow.electricity.security.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.security.model.OeReplyApproveTask;

/**
 * 断电告警回单审核业务操作接口
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
public interface OeReplyApproveTaskService {
	/**
	 * 断电告警回单审核
	 * 
	 * @param oeReplyApproveTask
	 *            OeReplyApproveTask 输入的断电告警派单回复审核信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	void save(OeReplyApproveTask oeReplyApproveTask, UserInfo userInfo);
}
