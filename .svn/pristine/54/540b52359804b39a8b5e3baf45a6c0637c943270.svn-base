package com.cabletech.business.base.service;

import java.util.List;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.util.Page;

/**
 * 用户基础信息服务
 * 
 * @author zhaobi
 * 
 */
public interface UserInfoService { 
	/**
	 * 根据人员名称搜索人员列表
	 * 
	 * @param userName
	 *            String
	 * @param user
	 *            UserInfo
	 * @param page
	 *            Page
	 */
	public void getUserInfoList(String userName, UserInfo user,
			Page page);

	/**
	 * 根据登录用户用户名获取用户信息
	 * 
	 * @param userid
	 *            String
	 * @return
	 */
	public UserInfo getUserInfoByUserId(String userid);

	/**
	 * 根据登录用户人员ID获取用户信息
	 * 
	 * @param personId
	 *            String
	 * @return
	 */
	public UserInfo getUserInfoByPersonId(String personId);

	/**
	 * 根据登录人员ID集合获取用户信息
	 * 
	 * @param ids
	 *            List<String>
	 * @return
	 */
	public List<UserInfo> getUserInfoByPersonIds(List<String> ids);
}
