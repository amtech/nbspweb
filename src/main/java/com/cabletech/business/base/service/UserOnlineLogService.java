package com.cabletech.business.base.service;

import com.cabletech.business.base.model.UserOnlineLog;
import com.cabletech.common.util.Page;

/**
 * 用户登录时间
 * 
 * @author zhaob
 * 
 */
public interface UserOnlineLogService {
	/**
	 * 更新退出时间
	 * 
	 * @param personid
	 *            String
	 */
	public void UpdateLogoutTime(String personid);

	/**
	 * 查询在线日志
	 * 
	 * @param userName
	 *            用户名
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param page
	 *            Page
	 * @return List<Map<String, Object>>
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

	/**
	 * 保存操作
	 * 
	 * @param onlineLog
	 *            在线日志
	 */
	public void save(UserOnlineLog onlineLog);
}
