package com.cabletech.business.assess.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.common.util.Page;

/**
 * 考核/检查结果业务接口
 * 
 * @author Administrator 2012-08-07
 * 
 */
public interface AssessExaminationService {
	/**
	 * 根据条件获取可供选择的模板
	 * 
	 * @param parameter 查询参数
	 * @return List<Map<String, Object>> 查询数据信息
	 */
	public List<Map<String, Object>> queryAppraiseTables(Map<String, String> parameter);
	
	
	/**
	 * 保存 检查结果实体
	 * 
	 * @param result
	 *            AssessExaminationResult
	 */
	public void save(AssessExaminationResult result);
	
	/**
	 * 根据编号查看考核/检查记录信息
	 * 
	 * @param id
	 *            String
	 * @return AssessExaminationResult
	 */
	public AssessExaminationResult view(String id);
	
	/**
	 * 查询检查结果
	 * @param parameters 查询参数
	 * @param page 分页参数
	 * @return page
	 */
	public Page queryResultList(Map<String,String> parameters, Page page);

	/**
	 * 查询考核/检查结果
	 * 
	 * @param parameter
	 *            Map<String, String>
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> queryResultList(
			Map<String, String> parameter);
	
	
	/**
	 * 现场检查汇总 统计
	 * @param parameters 查询参数
	 * @return page
	 */
	public Map<String,Object> queryExaminationSummaryList(Map<String,String> parameters);
	
	/**
	 * 查看站点信息
	 * @param resourceId  站点ID
	 * @return
	 */
	public String viewResourceInfo (String resourceId);
}
