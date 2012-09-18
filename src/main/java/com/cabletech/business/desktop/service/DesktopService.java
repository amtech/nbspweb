package com.cabletech.business.desktop.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;

/**
 * 首页桌面业务服务处理接口
 * 
 * @author 杨隽 2011-11-28
 * @author 杨隽 2011-12-13 添加代维单位人员图表显示方法
 * 
 */
public interface DesktopService {
	/**
	 * 设置登录用户信息
	 * 
	 * @param user
	 *            UserInfo 当前登录用户信息
	 */
	void setUserInfo(UserInfo user);

	/**
	 * 获取信息类型列表
	 * 
	 * @return List<Map<String, Object>> 信息类型列表
	 */
	List<Map<String, Object>> getInformationTypeList();

	/**
	 * 列举所有发布给当前用户查看的最近信息列表
	 * 
	 * @param noticeType
	 *            String 公告类型
	 * @param count
	 *            String 显示公告数量
	 * @return List<Map<String, Object>> 所有发布给当前用户查看的最近信息列表
	 */
	List<Map<String, Object>> getLatestNoticeList(String noticeType,
			String count);

	/**
	 * 代维资源配备 -- 统计列表 
	 * 
	 * @param loginUser 登录用户
	 * @return list
	 */
	 List<Map<String, Object>> getContractorResEquipList(UserInfo loginUser);	
	 
	 /**
	  * 超时故障派单和工单统计列表
	  * @param userInfo 
	  * @return
	  */
	 Map<String,Map<String,Object>> getOvertimeWorkOrderAndFaultNumberList(UserInfo userInfo);
	 
	/**
	 * 代维人员 -- 统计图表 
	 * 
	 * @param loginUser 登录用户
	 * @return list
	 */  
	 String getContractorPersonChartData(UserInfo loginUser);
	 
	/**
	 * 获取当月会议日期字串列表
	 * @return
	 */
	List<String> getMeetDateList();
	
	/**
	 * 获取当天会议数量
	 * @return
	 */
	int getTodayMeetNumber();
	
}