package com.cabletech.business.workflow.workorder.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.workflow.workorder.model.WorkCommonOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.service.WorkCommonOrderService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 通用工单信息内容业务接口实现
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-01-04 添加getWorkCommonOrder方法
 * 
 */
@Service
@Transactional
public class WorkCommonOrderServiceImpl extends
		BaseServiceImpl<WorkCommonOrder, String> implements
		WorkCommonOrderService {
	// 通用工单信息内容Dao
	@Resource(name = "workCommonOrderDao")
	private BaseDao<WorkCommonOrder, String> workCommonOrderDao;

	@Override
	protected BaseDao<WorkCommonOrder, String> getBaseDao() {
		// TODO Auto-generated method stub
		return workCommonOrderDao;
	}

	/**
	 * 保存通用工单内容信息
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单信息
	 */
	@Override
	public void save(WorkOrder workOrder) {
		// TODO Auto-generated method stub
		WorkCommonOrder workCommonOrder = new WorkCommonOrder();
		if (StringUtils.isNotBlank(workOrder.getInfoId())) {
			workCommonOrder = workCommonOrderDao.get(workOrder.getInfoId());
		}
		workCommonOrder.setTaskName(workOrder.getTaskName());
		workCommonOrder.setTaskRequest(workOrder.getTaskRequest());
		workCommonOrderDao.save(workCommonOrder);
		workOrder.setInfoId(workCommonOrder.getId());
	}

	/**
	 * 删除通用工单内容信息
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单信息
	 */
	@Override
	public void delete(WorkOrder workOrder) {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(workOrder.getInfoId())) {
			workCommonOrderDao.delete(workOrder.getInfoId());
		}
	}

	/**
	 * 根据通用工单编号获取通用工单的内容信息
	 * 
	 * @param taskId
	 *            String 通用工单编号
	 * @return WorkCommonOrder 通用工单的内容信息
	 */
	@Override
	public WorkCommonOrder getWorkCommonOrder(String taskId) {
		// TODO Auto-generated method stub
		WorkCommonOrder workCommonOrder = workCommonOrderDao.findUniqueBy("id",
				taskId);
		return workCommonOrder;
	}
}
