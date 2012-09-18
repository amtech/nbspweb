package com.cabletech.business.assess.service;

import java.util.Map;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.model.AssessTemplate;

/**
 * 考核模板导入业务接口
 * 
 * @author 杨隽 2012-07-31 创建
 * 
 */
public interface AssessTemplateImportService {
	/**
	 * 根据上传的模板EXCEL文件获取考核模板的预览数据
	 * 
	 * @param assessTemplate
	 *            AssessTemplate
	 * @param filePath
	 *            String
	 * @return Map<String,Object>
	 */
	public Map<String, Object> createItemPreviewData(
			AssessTemplate assessTemplate, String filePath);

	/**
	 * 根据生成的模板数据表单和当前用户信息执行导入考核模板到数据库中
	 * 
	 * @param assessTemplate
	 *            AssessTemplate
	 * @param user
	 *            UserInfo
	 */
	public void importItemData(AssessTemplate assessTemplate, UserInfo user);
}
