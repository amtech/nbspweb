package com.cabletech.business.workflow.fault.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.common.util.Page;

/**
 * 故障单草稿箱业务处理接口
 * 
 * @author 杨隽 2012-02-22 创建
 * 
 */
public interface FaultDispatchDraftService {
	/**
	 * 获取草稿箱列表分页数据
	 * 
	 * @param parameter
	 *            FaultQueryParameter 查询条件的故障派单实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 草稿箱列表分页数据
	 */
	@SuppressWarnings("rawtypes")
	Page getDraftList(FaultQueryParameter parameter, UserInfo userInfo);
}
