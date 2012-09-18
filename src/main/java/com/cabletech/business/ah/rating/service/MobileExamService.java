package com.cabletech.business.ah.rating.service;

import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.util.Page;


/**
 * @author 周刚 
 *
 */
public interface MobileExamService {

	/**
	 * 获取待审核列表
	 * 
	 * @param page page
	 * @param userInfo userInfo
	 * @param flag flag
	 * @return
	 */
	public Page<Map<String, Object>> getPageList(
			Page<Map<String, Object>> page, UserInfo userInfo, String flag);

	/**
	 * 查询统计分析
	 * 
	 * @param page page
	 * @param parameters parameters
	 * @return
	 */
	public Page<Map<String, Object>> getQueryAnalysisList(
			Page<Map<String, Object>> page, Map<String, Object> parameters);

	/**
	 * 统计所有已审核用户数量
	 * 
	 * @param parameters parameters
	 * @return
	 */
	public String getHasedCheckUserCount(Map<String, Object> parameters);

	/**
	 * 统计所有待审核用户数量
	 * 
	 * @param parameters parameters
	 * @return
	 */
	public String getNoneCheckUserCount(Map<String, Object> parameters);

	/**
	 * 统计所有代维确认的用户数量
	 * 
	 * @param parameters parameters
	 * @return
	 */
	public String getDaiweiOKCount(Map<String, Object> parameters);

}
