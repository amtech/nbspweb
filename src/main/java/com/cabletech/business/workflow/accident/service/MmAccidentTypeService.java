package com.cabletech.business.workflow.accident.service;

import com.cabletech.business.workflow.accident.model.MmAccidentType;

/**
 * 隐患类型业务处理接口
 * 
 * @author 杨隽 2012-08-27 创建
 * 
 */
public interface MmAccidentTypeService {
	/**
	 * 查看隐患信息
	 * 
	 * @param id
	 *            String
	 * @return MmAccidentType
	 */
	public MmAccidentType viewAccidentType(String id);
}
