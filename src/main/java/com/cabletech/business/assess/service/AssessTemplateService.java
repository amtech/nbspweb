package com.cabletech.business.assess.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.assess.model.AssessTemplate;
import com.cabletech.common.util.Page;

/**
 * 考核模版
 * @author zhaobi 2012-7-31 创建
 */
public interface AssessTemplateService {
	/**
	 * 分页查询考核模版
	 * @param template 模版信息
	 * @param page 分页
	 * @return
	 */
	public Page queryAssessTemplate(AssessTemplate template, Page page);
	
	/**
	 * 查询考核模板列表信息
	 * 
	 * @param template
	 *            AssessTemplate
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryAssessTemplate(AssessTemplate template);
	
	/**
	 * 获取模版信息
	 * @param id 模版ID
	 * @return
	 */
	public AssessTemplate getTemplate(String id);
	
	/**
	 * 保存模板
	 * @param template 模板信息
	 */
	public void save(AssessTemplate template);
	
	/**
	 * 根据主键批量逻辑删除模版
	 * @param id 主键
	 */
	public int del(String[] id);
}
