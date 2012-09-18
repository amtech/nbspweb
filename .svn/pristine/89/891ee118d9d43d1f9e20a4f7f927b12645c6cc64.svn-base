package com.cabletech.business.desktop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.desktop.dao.DesktopDao;
import com.cabletech.business.desktop.service.MyWorkService;
import com.cabletech.business.workflow.common.service.AbstractWorkflowWaitHandledService;
import com.cabletech.business.workflow.common.service.WorkflowWaitHandledService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 我的工作实现
 * 
 * @author Administrator
 * 
 */
@Service
@SuppressWarnings("rawtypes")
public class MyWorkServiceImpl extends BaseServiceImpl implements MyWorkService {
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;
	// 桌面服务Dao
	@Resource(name = "desktopDao")
	private DesktopDao desktopDao;
	// 待办工作流业务处理服务列表
	@Autowired
	private List<AbstractWorkflowWaitHandledService> workflowServiceList;
	@Resource(name = "workflowWaitHandledServiceImpl")
	private WorkflowWaitHandledService workflowWaitHandledService;

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取菜单
	 * 
	 * @param user
	 *            用户
	 * @param menuid
	 *            当前菜单
	 */
	@SuppressWarnings("deprecation")
	public List<Map<String, Object>> getMenuInfo(UserInfo user, String menuid) {
		// TODO Auto-generated method stub
		// 获取菜单下一级目录
		return baseInfoProvider.getMenuList(user.getUserId(), menuid, "");
	}

	/**
	 * 获取当前用户可以进行处理的待办工作信息数量
	 * 
	 * @param userInfo
	 *            用户
	 */
	@Transactional
	public Map<String, Object> getWaitHandledTasksNumber(UserInfo userInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取专业类型
		List<Map<String, Object>> businessTypeList = userInfo
				.getBusinessTypes();
		if (CollectionUtils.isEmpty(workflowServiceList)) {
			return map;
		}
		if (CollectionUtils.isEmpty(businessTypeList)) {
			return map;
		}
		for (int i = 0; i < workflowServiceList.size(); i++) {
			AbstractWorkflowWaitHandledService workflowService = workflowServiceList
					.get(i);
			workflowService.setBusinessTypeList(businessTypeList);
			workflowService.getOneWorkflowHandledTaskNumber(userInfo, map);
		}
		// map.putAll(workflowWaitHandledService
		// .getWorkflowWaitHandledNumberMap(userInfo));
		return map;
	}

	@Override
	@Transactional
	public List<Map<String, Object>> getWaitHandledTasksList(UserInfo userInfo,
			String type) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 获取专业类型
		List<Map<String, Object>> businessTypeList = userInfo
				.getBusinessTypes();
		if (CollectionUtils.isEmpty(workflowServiceList)) {
			return list;
		}
		if (CollectionUtils.isEmpty(businessTypeList)) {
			return list;
		}
		for (int i = 0; i < workflowServiceList.size(); i++) {
			AbstractWorkflowWaitHandledService workflowService = workflowServiceList
					.get(i);
			if (!type.equals(workflowService.getWorkflowType(workflowService
					.getProcessDefName()))) {
				continue;
			}
			workflowService.setBusinessTypeList(businessTypeList);
			List<Map<String, Object>> resultList = workflowService
					.getOneWorkflowHandledTaskList(
							workflowService.getBusinessTypeList(), userInfo);
			list.addAll(resultList);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getShortCuts(UserInfo user) {
		// TODO Auto-generated method stub
		return desktopDao.getShortCuts(user);
	}
}
