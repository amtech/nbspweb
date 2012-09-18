package com.cabletech.business.monthcost.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.monthcost.model.MonthTimesCost;
import com.cabletech.business.monthcost.model.MonthTimesCostTemp;
import com.cabletech.common.util.Page;

/**
 * 
 * @author Administrator
 * 
 */
public interface MonthTimesCostService {

	/**
	 * 列表
	 * 
	 * @param entity
	 *            MonthTimesCost
	 * @param page
	 *            Page
	 * @param user
	 *            UserInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page queryPage(MonthTimesCost entity, Page page, UserInfo user);

	/**
	 * 获取实体
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	MonthTimesCost getEntityById(String id);

	/**
	 * 删除数据
	 * 
	 * @param id
	 *            String
	 */
	void deleteEntityById(String id);

	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            MonthTimesCost
	 * @return
	 */
	MonthTimesCost saveEntity(MonthTimesCost entity);

	/**
	 * 创建预览数据
	 * 
	 * @param filePath
	 *            String
	 * @param user
	 *            UserInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List createItemPreviewData(String filePath, UserInfo user);

	/**
	 * 从页面保存数据
	 * 
	 * @param parameterMap
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Map importItemData(Map<String, String> parameterMap);

	/**
	 * 保存数据至数据库
	 * 
	 * @param parameterMap
	 *            Map<String, String>
	 * @param oneCellTemp
	 *            MonthTimesCostTemp
	 * @throws Exception
	 */
	public void save(Map<String, String> parameterMap,
			MonthTimesCostTemp oneCellTemp) throws Exception;
}
