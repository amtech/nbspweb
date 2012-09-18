package com.cabletech.business.desktop.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;

/**
 * 基础工作服务
 * @author zhaobi
 *
 */
public interface BaseWorkService {
	
	/**
	 * 到期资质 证书
	 * 
	 * @param loginUser 登录用户
	 * @return list
	 */
	public List<Map<String, Object>> getValidperiodedCertificatesList(UserInfo loginUser);
	

	
	/**
	 * 获取设备图表数据
	 * @param loginUser 登录用户
	 * @return 数据json串
	 */
	public String getTerminalChartData(UserInfo loginUser);
	/**
	 * 资源数量 
	 * @param loginUser 登录用户
	 * @return List
	 */
	public Map<String, Object> getResLineCountList(UserInfo loginUser);
	
	/**
	 * 根据当前用户信息进行首页离职率统计
	 * 
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Map<String, List<String>> 首页离职率统计结果列表
	 */
	@SuppressWarnings("rawtypes")
	List getLeavePersonStatisticResult(UserInfo userInfo);
	
}
