package com.cabletech.business.workflow.workorder.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.model.ModuleCatalog;
import com.cabletech.business.base.service.UploadFileService;
import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.model.WorkCommonOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.service.WorkCommonOrderService;
import com.cabletech.business.workflow.workorder.service.WorkOrderDispatchService;
import com.cabletech.business.workflow.workorder.service.WorkOrderTransferService;
import com.cabletech.common.base.SysConstant;

/**
 * 通用工单分发业务接口实现
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-01-04 去除list方法，添加view方法
 * @author 杨隽 2012-01-10 在save方法中添加上传附件的保存功能
 * @author 杨隽 2012-01-10 在save方法中添加生成工单序号的功能
 * @author 杨隽 2012-02-07 修改getBaseDao()方法实现为getWorkOrderBaseDao ()方法实现
 * 
 */
@Service
@Transactional
public class WorkOrderDispatchServiceImpl extends
		WorkOrderBaseServiceImpl<WorkOrder, String> implements
		WorkOrderDispatchService {
	// 通用工单信息Dao
	@Resource(name = "workOrderDao")
	private WorkOrderBaseDao<WorkOrder, String> workOrderDao;
	// 通用工单内容信息业务处理
	@Resource(name = "workCommonOrderServiceImpl")
	private WorkCommonOrderService workCommonOrderService;
	// 通用工单分发信息业务处理
	@Resource(name = "workOrderTransferServiceImpl")
	private WorkOrderTransferService workOrderTransferService;
	// 上传附件业务处理
	@Resource(name = "uploadFileServiceImpl")
	private UploadFileService uploadFileService;

	@Override
	protected WorkOrderBaseDao<WorkOrder, String> getWorkOrderBaseDao() {
		// TODO Auto-generated method stub
		return workOrderDao;
	}

	/**
	 * 保存通用工单信息
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单实体
	 * @throws RuntimeException
	 *             异常
	 */
	@Override
	public void save(WorkOrder workOrder) throws RuntimeException {
		// TODO Auto-generated method stub
		// 保存通用工单内容信息
		workCommonOrderService.save(workOrder);
		// 保存通用工单
		if (StringUtils.isBlank(workOrder.getId())) {
			workOrder.setId(null);
		}
		if (StringUtils.isBlank(workOrder.getTaskCode())) {
			UserInfo user = workOrder.getUser();
			String taskCode = super.commonOrderCodeService
					.generatorWorkOrderCode(user.getRegionId(),
							workOrder.getBusinessType(),
							SysConstant.COMMON_ORDER_FLOW_TYPE);
			workOrder.setTaskCode(taskCode);
		}
		workOrder.setCreateDate(new Date());
		workOrderDao.save(workOrder);
		// 删除之前的通用工单分发信息
		workOrderTransferService.delete(workOrder);
		// 保存通用工单分发信息
		workOrderTransferService.save(workOrder);
		// 保存上传附件信息
		try {
			uploadFileService.saveFiles(workOrder.getFileList(),
					ModuleCatalog.SENDTASK, "", workOrder.getId(),
					WorkOrder.WTASK_ORDER_KEY, workOrder.getCreater());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查看通用工单信息
	 * 
	 * @param id
	 *            String 通用工单信息编号
	 * @return WorkOrder 通用工单实体
	 */
	@Override
	public WorkOrder view(String id) {
		// TODO Auto-generated method stub
		WorkOrder workOrder = workOrderDao.get(id);
		if (workOrder != null) {
			WorkCommonOrder workCommonOrder = workCommonOrderService
					.getWorkCommonOrder(workOrder.getInfoId());
			workOrder.setTaskRequest(workCommonOrder.getTaskRequest());
			String[] acceptUsers = workOrderTransferService.getAcceptUsers(id);
			workOrder.setAcceptUserIds(acceptUsers[0]);
			workOrder.setAcceptUserNames(acceptUsers[1]);
		}
		return workOrder;
	}
}
