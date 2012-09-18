package com.cabletech.business.assess.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.model.AssessAppealForm;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.common.util.Page;

/**
 * 考核管理-申诉记录
 * 
 * @author wj 2012-08-02 创建
 * 
 */
public interface AssessAppealFormService {
	/**
	 * 保存
	 * 
	 * @param appealForm
	 *            实体类
	 * @param userInfo
	 *            当前登录用户
	 */
	public void doTask(AssessAppealForm appealForm, UserInfo userInfo);

	/**
	 * 查询申诉列表
	 * 
	 * @param parameters
	 *            查询参数
	 * @param page
	 *            分页参数
	 * @return page
	 */
	public Page queryAppealFormList(Map<String, String> parameters, Page page);

	/**
	 * 查询申诉
	 * 
	 * @param id
	 *            String 查询参数
	 * @return page
	 */
	public Map<String, Object> queryAppealForm(String id);

	/**
	 * 查询申诉待办列表
	 * 
	 * @param parameters
	 *            查询参数
	 * @param page
	 *            分页参数
	 * @return page
	 */
	public Page queryWaitHandledList(Map<String, String> parameters, Page page);

	/**
	 * 保存实体 并发起工作流
	 * 
	 * @param appealForm
	 *            AssessAppealForm
	 * @param userInfo
	 *            UserInfo
	 */
	public void startFlow(AssessAppealForm appealForm, UserInfo userInfo);

	/**
	 * 查询考核结果调整
	 * 
	 * @param appealFormId
	 *            String 查询参数
	 */
	public List<Map<String, Object>> queryAdjusstmentList(String appealFormId);

	/**
	 * 获取审批历史
	 * 
	 * @param bzId
	 *            String
	 */
	public List<ProMockPo> queryApproveHisList(String bzId);

	/**
	 * 获取能申诉的考核结果列表
	 * 
	 * @param parameter
	 *            Map<String,String>
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryCanAppealResultList(
			Map<String, String> parameter);
}