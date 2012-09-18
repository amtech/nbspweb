package com.cabletech.business.monthcost.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator
 * 
 */
public interface MonthCostStaticService {

	/**
	 * 查询数据
	 * 
	 * @param type
	 *            String
	 * @param month
	 *            String
	 * @param year
	 *            String
	 * @param lab
	 *            String
	 * @return
	 */
	List<Map<String, Object>> getDataMap(String type, String month,
			String year, String lab);

	/**
	 * 总计数据
	 * 
	 * @param type
	 *            String
	 * @param month
	 *            String
	 * @param year
	 *            String
	 * @return
	 */
	List<Map<String, Object>> getTotalData(String type, String month,
			String year);

	/**
	 * 导出
	 * 
	 * @param type
	 *            String
	 * @param month
	 *            String
	 * @param year
	 *            String
	 * @param lab
	 *            String
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List getDetailForExport(String type, String month, String year, String lab);

	/**
	 * 小计
	 * 
	 * @param type
	 *            String
	 * @param month
	 *            String
	 * @param year
	 *            String
	 * @param lab
	 *            String
	 * @return
	 */
	List<Map<String, Object>> getLittleTotalMap(String type, String month,
			String year, String lab);

	/**
	 * 总计
	 * 
	 * @param type
	 *            String
	 * @param month
	 *            String
	 * @param year
	 *            String
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List getTotalDataList(String type, String month, String year);

	/**
	 * 总计数量
	 * 
	 * @param type
	 *            String
	 * @param month
	 *            String
	 * @param year
	 *            String
	 * @param lab
	 *            String
	 * @return
	 */
	Integer getCount4Total(String type, String month, String year, String lab);
}
