package com.cabletech.business.desktop.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;

/**
 * 在线人员专有服务
 * @author zhaobi
 * @date 2012-8-14 
 */
public interface OnlineManService {
	
	/**
	 * 列举当前用户所在单位下的在线巡检人员信息树
	 * @param user  当前用户
	 * @return
	 */
	public List<Map<String, Object>> getOnlineManTreeList(UserInfo user);
	
	/**
	 * 获取巡检组在线人员图表
	 * @param user 当前用户
	 * @return
	 */
	public Map<String, Object> getPatrolGroupOnlineMan(UserInfo user);
	
	/**
	 * 获取代维的组织在线人员图表
	 * @param user 当前用户
	 * @return
	 */
	public Map<String, Object> getContractoridOnlineMan(UserInfo user);
	
	/**
	 * 区域的在线人员
	 * 
	 * @param user 当前用户
	 *            
	 */
	public Map<String, Object> getRegionOnlineMan(UserInfo user);
}
