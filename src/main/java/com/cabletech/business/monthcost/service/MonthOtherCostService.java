package com.cabletech.business.monthcost.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.monthcost.model.MonthOtherCost;
import com.cabletech.business.monthcost.model.MonthOtherCostTemp;
import com.cabletech.common.util.Page;

/**
 * @author Administrator
 * 
 */
public interface MonthOtherCostService {

	/**
	 * 查询列表方法
	 * 
	 * @param entity
	 *            MonthOtherCost
	 * @param page
	 *            Page
	 * @param user
	 *            UserInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page queryPage(MonthOtherCost entity, Page page, UserInfo user);

	/**
	 * 根据id 获取对象
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	MonthOtherCost getEntityById(String id);

	/**
	 * 根据id 删除对象
	 * 
	 * @param id
	 *            String
	 */
	void deleteEntityById(String id);

	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            MonthOtherCost
	 * @return
	 */
	MonthOtherCost saveEntity(MonthOtherCost entity);

	/**
	 * 创建预览
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
	 * 保存数据至数据库
	 * 
	 * @param parameterMap
	 *            Map<String, String>
	 * @param oneCellTemp
	 *            MonthOtherCostTemp
	 * @throws Exception
	 */
	public void save(Map<String, String> parameterMap,
			MonthOtherCostTemp oneCellTemp) throws Exception;

	/**
	 * @param parameterMap
	 *            Map<String, String>
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Map importItemData(Map<String, String> parameterMap);
}
