package com.cabletech.business.workflow.electricity.security.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.model.OeOutageAlarm;
import com.cabletech.common.util.Page;

/**
 * 断电告警业务操作接口
 * 
 * @author 杨隽 2012-05-03 创建
 * @author 杨隽 2012-05-07 添加cloneAlarmToDispatchTask()方法
 * 
 */
public interface OeOutageAlarmService {
	/**
	 * 根据断电告警单编号查看断电告警单信息
	 * 
	 * @param id
	 *            String 断电告警单编号
	 * @return OeOutageAlarm 断电告警单信息
	 */
	OeOutageAlarm viewOeOutageAlarm(String id);

	/**
	 * 复制断电告警单信息到断电告警发电信息中
	 * 
	 * @param oeOutageAlarm
	 *            OeOutageAlarm 断电告警单信息
	 * @return OeDispatchTask 断电告警发电信息
	 */
	OeDispatchTask cloneAlarmToDispatchTask(OeOutageAlarm oeOutageAlarm);

	/**
	 * 忽略断电告警单
	 * 
	 * @param id
	 *            String 断电告警单编号
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	void ignore(String id, UserInfo userInfo);

	/**
	 * 根据查询条件获取断电告警单分页列表
	 * 
	 * @param oeOutageAlarm
	 *            OeOutageAlarm 查询条件参数
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return Page 断电告警单分页列表
	 */
	@SuppressWarnings("rawtypes")
	Page getList(OeOutageAlarm oeOutageAlarm, UserInfo userInfo);

	/**
	 * 根据查询条件获取未派单断电告警单分页列表
	 * 
	 * @param oeOutageAlarm
	 *            OeOutageAlarm 查询条件参数
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return Page 未派单断电告警单分页列表
	 */
	@SuppressWarnings("rawtypes")
	Page getUnDispatchedList(OeOutageAlarm oeOutageAlarm, UserInfo userInfo);
}
