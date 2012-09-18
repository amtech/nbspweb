package com.cabletech.business.assess.service;

import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;

/**
 * 月度考核成绩生成业务接口
 * 
 * @author 杨隽 2012-08-08 创建
 * 
 */
public interface AssessScoreQueryService {
	/**
	 * 获取月度考核成绩生成列表
	 * 
	 * @param yearMonth
	 *            String
	 * @param queryType
	 *            String
	 * @param user
	 *            UserInfo
	 * @return Map<String,Object>
	 */
	public Map<String, Object> queryScoreList(String yearMonth,
			String queryType, UserInfo user);
}
