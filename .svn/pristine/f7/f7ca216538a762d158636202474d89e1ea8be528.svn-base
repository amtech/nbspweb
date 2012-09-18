package com.cabletech.business.ah.rating.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cabletech.business.ah.rating.model.ContractorSelfRating;
import com.cabletech.common.util.Page;

/**
 * 代维人员自评
 * @author wangt
 *
 */
public interface ContractorSelfRatingService {

	/**
	 * 自评列表 
	 * @param page 
	 * @param regionid 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page queryPage(Page page, String regionid);

	/**
	 * 获取评估项
	 * @param orgId 
	 * @param id 
	 * @return
	 */
	List<Map<String,Object>> getItems(String orgId, String id);

	/**
	 * 获取单个
	 * @param id 
	 * @return
	 */
	ContractorSelfRating getOne(String id);

	/**
	 * 保存月考核信息
	 * @param entity 
	 * @return
	 */
	void save(ContractorSelfRating entity);

	/**
	 * 获取指定人员信息
	 * @param personId 
	 * @param id 
	 * @return
	 */
	Map<String,Object> getPersonImform(String personId, String id);

	/**
	 * 流程处理
	 * @param request 
	 * @param ids 当前流程
	 * @param maxflownums 最大流程处理
	 * @param entity 
	 */
	void processData(HttpServletRequest request, String[] ids, String[] maxflownums, ContractorSelfRating entity);
}
