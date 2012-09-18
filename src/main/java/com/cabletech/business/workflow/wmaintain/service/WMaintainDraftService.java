package com.cabletech.business.workflow.wmaintain.service;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.common.util.Page;

/**
 * 维修作业计划草稿箱业务处理接口
 * 
 * @author 王甜 2012-04-11 创建
 * 
 */
public interface WMaintainDraftService {

	/**
	 * 获取草稿箱列表分页数据
	 * 
	 * @param plan
	 *            WMaintainPlan 查询条件的维修作业计划实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 草稿箱列表分页数据
	 */
	@SuppressWarnings("rawtypes")
	Page getDraftList(WMaintainPlan plan, UserInfo userInfo);
}
