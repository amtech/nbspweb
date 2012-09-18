package com.cabletech.business.ah.rating.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.ah.rating.model.RatingFormItemTemp;

/**
 * 考核表子项业务服务接口
 * 
 * @author 杨隽 2012-06-26 创建
 * 
 */
public interface RatingFormItemService {
	/**
	 * 保存考核表子项信息
	 * 
	 * @param itemId
	 *            String 考核表编号
	 * @param oneCell
	 *            RatingFormItemTemp 考核表导入数据信息
	 * @throws Exception
	 */
	public void save(RatingFormItemTemp oneCell, String itemId)
			throws Exception;

	/**
	 * 删除考核表子项
	 * 
	 * @param tableId
	 *            String 要删除的考核表子项的考核表项编号
	 */
	public void delete(String tableId);

	/**
	 * 根据考核表编号获取考核表子项数据列表
	 * 
	 * @param tableId
	 *            String 考核表编号
	 * @return List<Map<String,Object>> 考核表子项数据列表
	 */
	public List<Map<String, Object>> queryListByTableId(String tableId);
}
