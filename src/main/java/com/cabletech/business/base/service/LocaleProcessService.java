package com.cabletech.business.base.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.base.model.LocaleProcess;

/**
 * 现场处理过程业务接口
 * 
 * @author 杨隽 2012-01-09 创建
 * 
 */
public interface LocaleProcessService {
	/**
	 * 根据业务编号获取现场处理过程信息列表
	 * 
	 * @param localeProcess
	 *            LocaleProcess 带有业务编号的现场处理过程实体
	 * @return List<Map<String, Object>> 现场处理过程信息列表
	 */
	List<Map<String, Object>> showLocaleProcessList(LocaleProcess localeProcess);

	/**
	 * 根据业务编号获取现场处理过程图片信息列表
	 * 
	 * @param localeProcess
	 *            LocaleProcess 带有业务编号的现场处理过程实体
	 * @return List<Map<String, Object>> 现场处理过程图片信息列表
	 */
	List<Map<String, Object>> showLocaleProcessPhotosList(
			LocaleProcess localeProcess);
}
