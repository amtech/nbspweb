package com.cabletech.business.ah.rating.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.ah.rating.model.RatingFormItemTemp;

/**
 * 考核表导入数据业务服务接口
 * 
 * @author 杨隽 2012-06-26 创建
 * 
 */
public interface RatingFormItemImportService {
	/**
	 * 根据要导入的Excel文件生成预览数据
	 * 
	 * @param filePath
	 *            String 导入的巡检报表文件路径信息
	 * @throws Exception
	 */
	public List<RatingFormItemTemp> createItemPreviewData(String filePath);

	/**
	 * 导入Excel数据
	 * 
	 * @param parameterMap
	 *            Map<String,Object>
	 *            导入Excel的参数：file_path->导入的巡检报表文件路径信息，business_type
	 *            ->专业类型，region_id->区域编号
	 * @return Map 导入的结果Map
	 */
	@SuppressWarnings("rawtypes")
	public Map importItemData(Map<String, Object> parameterMap);
}
