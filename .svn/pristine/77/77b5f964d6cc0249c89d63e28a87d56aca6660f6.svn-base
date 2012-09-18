package com.cabletech.business.monthcost.service;

import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.monthcost.model.MonthCheckCost;
import com.cabletech.business.monthcost.model.MonthCheckCostTemp;
import com.cabletech.common.util.Page;

/**
 * @author Administrator
 * 
 */
public interface MonthCheckCostService {
	/**
	 * 根据id获取对象
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	MonthCheckCost getEntityById(String id);

	/**
	 * 删除对象
	 * 
	 * @param id
	 *            String
	 */
	void deleteEntityById(String id);

	/**
	 * 保存对象
	 * 
	 * @param entity
	 *            MonthCheckCost
	 * @return
	 */
	MonthCheckCost saveEntity(MonthCheckCost entity);

	/**
	 * 查询列表
	 * 
	 * @param entity
	 *            MonthCheckCost
	 * @param page
	 *            Page
	 * @param user
	 *            UserInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page queryPage(MonthCheckCost entity, Page page, UserInfo user);

	/**
	 * 创建预览 根据文件上传的
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
	 * 導入數據
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
	 *            MonthCheckCostTemp
	 * @throws Exception
	 */
	public void save(Map<String, String> parameterMap,
			MonthCheckCostTemp oneCellTemp) throws Exception;
}
