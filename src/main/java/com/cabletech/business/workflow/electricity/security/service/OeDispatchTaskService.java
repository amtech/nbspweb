package com.cabletech.business.workflow.electricity.security.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;

/**
 * 断电告警派单业务操作接口
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
public interface OeDispatchTaskService {
	/**
	 * 根据断电告警派单编号读取断电告警派单详细信息
	 * 
	 * @param id
	 *            String 断电告警派单编号
	 * @return OeDispatchTask 断电告警派单详细信息
	 */
	OeDispatchTask viewOeDispatchTask(String id);

	/**
	 * 断电告警派单
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 输入的断电告警派单信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	void save(OeDispatchTask oeDispatchTask, UserInfo userInfo);

	/**
	 * 根据断电告警派单编号删除断电告警派单信息
	 * 
	 * @param id
	 *            String[] 断电告警派单编号数组
	 */
	void deleteOeDispatchTask(String[] id);
}
