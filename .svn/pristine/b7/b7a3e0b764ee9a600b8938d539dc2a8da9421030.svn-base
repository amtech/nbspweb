package com.cabletech.business.ah.rating.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.util.Page;

/**
 * 
 * 人员流程定义
 * 
 * @author wj
 * 
 */
public interface PersonFlowService {
	/**
	 * 保存
	 * 
	 * @param parameters
	 *            参数
	 * 
	 * @throws RuntimeException
	 *             异常
	 */
	void save(Map<String, Object> parameters) throws RuntimeException;

	/**
	 * 
	 * 获取考核人员列表
	 * 
	 * @param parameters
	 *            参数封装
	 * @return List
	 * 
	 */
	public String searchRatingPersons(Map<String, Object> parameters);

	/**
	 * 查询人员流程定义列表
	 * 
	 * @param parameters
	 *            参数封装
	 * @param page
	 *            分页
	 * @return List
	 */
	public Page searchPersonFlows(Map<String, Object> parameters, Page page);

	/**
	 * 根据人员ID获取办理人员
	 * 
	 * @param parameters
	 *            参数封装
	 * @return
	 */
	public List<Map<String, Object>> searchProcesserByPid(
			Map<String, Object> parameters);

	/**
	 * 
	 * 删除人员流程定义列表
	 * 
	 * @param personId
	 *            人员id
	 * 
	 */
	public void deletePersonFlows(String personId);

}
