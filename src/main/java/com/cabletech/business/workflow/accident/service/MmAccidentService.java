package com.cabletech.business.workflow.accident.service;

import com.cabletech.business.workflow.accident.model.MmAccident;

/**
 * 隐患业务处理接口
 * 
 * @author 杨隽 2012-08-27 创建
 * 
 */
public interface MmAccidentService {
	/**
	 * 查看隐患信息
	 * 
	 * @param id
	 *            String
	 * @return MmAccident
	 */
	public MmAccident viewAccident(String id);
}
