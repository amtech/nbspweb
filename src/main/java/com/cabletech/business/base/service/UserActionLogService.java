package com.cabletech.business.base.service;

import com.cabletech.business.base.model.UserActionLog;
import com.cabletech.common.util.Page;

/**
 * 用户操作日志
 * 
 * @author Administrator
 * 
 */
public interface UserActionLogService {
	/**
	 * 保存日志
	 * 
	 * @param userActionLog
	 *            UserActionLog
	 */
	public void savelog(UserActionLog userActionLog);

	/**
	 * 搜索日志
	 * 
	 * @param userName
	 *            用户名
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param page
	 *            Page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public void searchlog(String userName, String startTime, String endTime,
			Page page);

	/**
	 * 删除日志
	 * 
	 * @param ids
	 *            String[]
	 */
	public void dellogs(String[] ids);
}
