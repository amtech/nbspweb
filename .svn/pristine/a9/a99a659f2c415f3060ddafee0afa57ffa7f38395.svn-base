package com.cabletech.business.desktop.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;

/**
 * 我的工作服务
 * 
 * @author Administrator
 * 
 */
public interface MyWorkService {
	/**
	 * 获取菜单信息
	 * 
	 * @param user
	 *            用户
	 * @param menuid
	 *            菜单id
	 * @return
	 */
	public List<Map<String, Object>> getMenuInfo(UserInfo user, String menuid);

	/**
	 * 获取当前用户可以进行处理的待办工作信息数量
	 * 
	 * @param user
	 *            用户
	 * @return Map<String, Object> 当前用户可以进行处理的待办工作信息数量Map
	 */
	Map<String, Object> getWaitHandledTasksNumber(UserInfo user);

	/**
	 * 获取当前用户可以进行处理的待办工作信息列表
	 * 
	 * @param userInfo
	 *            用户
	 * @param type
	 *            String
	 * @return List<Map<String, Object>> 当前用户可以进行处理的待办工作信息列表
	 */
	List<Map<String, Object>> getWaitHandledTasksList(UserInfo userInfo,
			String type);

	/**
	 * 获取用户快捷方式
	 * 
	 * @param user
	 *            用户
	 * @return
	 */
	public List<Map<String, Object>> getShortCuts(UserInfo user);
}
