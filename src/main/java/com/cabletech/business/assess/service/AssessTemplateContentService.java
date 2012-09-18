package com.cabletech.business.assess.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.assess.model.AssessTemplateContent;

/**
 * 考核模版内容
 * @author zhaobi 2012-7-31 创建 
 */
public interface AssessTemplateContentService {
	/**
	 * 保存模板内容
	 * @param content 模版内容
	 */
	public void save(AssessTemplateContent content);
	
	/**
	 * 获取模板详细内容
	 * @param content 模板内容
	 * @return
	 */
	public Map<String,Object> getTemplateContent(AssessTemplateContent content);
	
	/**
	 * 根据主键删除模版
	 * @param id 考核表ID
	 */
	public void delete(String id);
	
	/**
	 * 获取模板列
	 * @param map Map<String,Object>
	 */
	public List<Map<String,Object>> getTableItemList(Map<String,Object> map);
	
	/**
	 * 获取考核表最大列数
	 * @param map Map<String,Object>
	 * @return
	 */
	public int getMaxTableItem(Map<String, Object> map);
	
	/**
	 * 进行考核项目列表的合并
	 * @param itemMaxcount int
	 * @param itemlist List<Map<String, Object>>
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> processList(int itemMaxcount,
			List<Map<String, Object>> itemlist);
}
