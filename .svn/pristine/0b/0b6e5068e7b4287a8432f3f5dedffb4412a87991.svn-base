package com.cabletech.business.assess.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.model.AssessExaminationResult;

/**
 * 月度考核业务接口
 * 
 * @author 杨隽 2012-08-03 创建
 * 
 */
public interface AssessAppraiseService {
	/**
	 * 保存月度考核结果实体
	 * 
	 * @param result
	 *            AssessExaminationResult
	 * @param user
	 *            UserInfo
	 */
	public void save(AssessExaminationResult result, UserInfo user);

	/**
	 * 根据编号查看月度考核信息
	 * 
	 * @param id
	 *            String
	 * @return AssessExaminationResult
	 */
	public AssessExaminationResult view(String id);

	/**
	 * 查询月度考核结果
	 * 
	 * @param parameter
	 *            Map<String, String>
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> queryResultList(
			Map<String, String> parameter);

	/**
	 * 执行月度考核的工作流处理
	 * 
	 * @param assessExaminationResult
	 *            AssessExaminationResult
	 * @param user
	 *            UserInfo
	 */
	public void doWorkflow(AssessExaminationResult assessExaminationResult,
			UserInfo user);
}
