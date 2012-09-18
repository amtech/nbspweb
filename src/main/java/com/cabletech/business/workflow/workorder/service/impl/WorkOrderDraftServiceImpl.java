package com.cabletech.business.workflow.workorder.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.service.WorkOrderDraftService;
import com.cabletech.common.util.Page;

/**
 * 通用工单草稿箱业务处理接口实现
 * 
 * @author 杨隽 2012-01-04 创建
 * @author 杨隽 2012-01-09 设置草稿箱的工单状态默认条件
 * @author 杨隽 2012-02-07 修改getBaseDao()方法实现为getWorkOrderBaseDao ()方法实现
 * 
 */
@Service
public class WorkOrderDraftServiceImpl extends
		WorkOrderBaseServiceImpl<WorkOrder, String> implements
		WorkOrderDraftService {
	// 通用工单信息Dao
	@Resource(name = "workOrderDao")
	private WorkOrderBaseDao<WorkOrder, String> workOrderDao;

	@Override
	protected WorkOrderBaseDao<WorkOrder, String> getWorkOrderBaseDao() {
		// TODO Auto-generated method stub
		return workOrderDao;
	}

	/**
	 * 获取草稿箱列表分页数据
	 * 
	 * @param workOrder
	 *            WorkOrder 维修作业计划实体
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @return Page 草稿箱列表分页数据
	 */
	@Override
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getDraftList(WorkOrder workOrder, UserInfo userInfo) {
		// TODO Auto-generated method stub
		if (QueryParameter.isNull(workOrder)) {
			workOrder = new WorkOrder();
		}
		workOrder.setTaskState(WorkOrder.WORKORDER_NOT_SUBMITED_STATE);
		QueryParameter parameter = setQueryParameter(workOrder, userInfo);
		ConditionGenerate conditionGenerate = getConditionGenerate(
				DRAFT_CONDITION_GENERATE_KEY, parameter);
		conditionGenerate.setPage(workOrder.getPage());
		return workOrderDao.queryPageForSql(conditionGenerate);
	}
}
