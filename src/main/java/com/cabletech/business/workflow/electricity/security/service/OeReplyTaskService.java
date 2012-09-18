package com.cabletech.business.workflow.electricity.security.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.security.model.OeReplyTask;

/**
 * 断电告警回单业务操作接口
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
public interface OeReplyTaskService {
	/**
	 * 断电告警回单
	 * 
	 * @param oeReplyTask
	 *            OeReplyTask 输入的断电告警派单回复信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	void save(OeReplyTask oeReplyTask, UserInfo userInfo);
}
