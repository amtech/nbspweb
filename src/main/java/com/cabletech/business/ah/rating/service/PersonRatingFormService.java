package com.cabletech.business.ah.rating.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.ah.rating.model.PersonRatingForm;
import com.cabletech.common.util.Page;

/**
 * 人员考核表定义
 * @author wangt
 *
 */
public interface PersonRatingFormService {

	/**
	 * 查询列表的数据
	 * @param entity 条件信息
	 * @param page 分页信息
	 */
	Page queryPage(PersonRatingForm entity, Page page);

	/**
	 * 查询可分配人员
	 * @param regionid 
	 * @param jobtype 
	 * @return
	 */
	List<Map<String, Object>> getPersons(String regionid, String jobtype);

	/**
	 * 保存分配人员信息
	 * @param entity 
	 */
	void saveRelationShip(PersonRatingForm entity);

	/**
	 * 获取可分配人员
	 * @param regionId 
	 * @param jobtype 
	 * @param tableid 
	 * @return
	 */
	List<Map<String, Object>> getPersonsAssigned(String regionId, String jobtype, String tableid);

}
