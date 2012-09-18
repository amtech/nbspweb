package com.cabletech.business.workflow.workorder.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderTransfer;
import com.cabletech.business.workflow.workorder.service.WorkOrderTransferService;
import com.cabletech.common.base.SysConstant;

/**
 * 通用工单分发信息业务接口实现
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-01-04 添加getAcceptUserIds方法
 * @author 杨隽 2012-01-05 修改getAcceptUserIds方法为getAcceptUsers方法
 * @author 杨隽 2012-01-09 添加getList方法
 * @author 杨隽 2012-02-06 去除getWaitHandledList()方法
 * @author 杨隽 2012-02-07 修改getBaseDao()方法实现为getWorkOrderBaseDao()方法实现
 * @author 杨隽 2012-03-27 集成工单短信发送功能（添加getSmParameter方法并修改save方法和doWorkflow方法）
 * @author 杨隽 2012-05-04 更改使用公共的表单提交标识
 * 
 */
@Service
@Transactional
public class WorkOrderTransferServiceImpl extends
		WorkOrderBaseServiceImpl<WorkOrderTransfer, String> implements
		WorkOrderTransferService {
	// 受理人分隔符
	private static final String ACCEPT_USER_ID_SEPR = ",";
	// 通用工单分发信息Dao
	@Resource(name = "workOrderTransferDao")
	private WorkOrderBaseDao<WorkOrderTransfer, String> workOrderTransferDao;

	@Override
	protected WorkOrderBaseDao<WorkOrderTransfer, String> getWorkOrderBaseDao() {
		// TODO Auto-generated method stub
		return workOrderTransferDao;
	}

	/**
	 * 保存通用工单分发信息
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单实体
	 */
	@Override
	public void save(WorkOrder workOrder) {
		// TODO Auto-generated method stub
		String acceptUserIds = workOrder.getAcceptUserIds();
		if (StringUtils.isBlank(acceptUserIds)) {
			return;
		}
		String[] acceptUserIdArray = acceptUserIds.split(ACCEPT_USER_ID_SEPR);
		if (ArrayUtils.isEmpty(acceptUserIdArray)) {
			return;
		}
		for (String acceptUserId : acceptUserIdArray) {
			saveOneWorkOrderTransfer(workOrder, acceptUserId);
		}
	}

	/**
	 * 删除通用工单分发信息
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单实体
	 */
	@Override
	public void delete(WorkOrder workOrder) {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(workOrder.getId())) {
			StringBuffer hql = new StringBuffer("");
			hql.append(" DELETE FROM WorkOrderTransfer WHERE taskId='");
			hql.append(workOrder.getId());
			hql.append("' ");
			workOrderTransferDao.batchHQLExecute(hql.toString());
		}
	}

	/**
	 * 查看通用工单分发信息
	 * 
	 * @param id
	 *            String 工单信息编号
	 * @return WorkOrderTransfer 通用工单分发信息
	 */
	@Override
	@Transactional(readOnly = true)
	public WorkOrderTransfer view(String id) {
		return workOrderTransferDao.get(id);
	}

	/**
	 * 根据通用工单编号获取通用工单的受理人信息列表
	 * 
	 * @param taskId
	 *            String 通用工单编号
	 * @return String[] 通用工单的受理人信息列表
	 */
	@Override
	@Transactional(readOnly = true)
	public String[] getAcceptUsers(String taskId) {
		// TODO Auto-generated method stub
		QueryParameter parameter = new QueryParameter();
		parameter.setId(taskId);
		ConditionGenerate conditionGenerate = getConditionGenerate(
				TASK_ID_CONDITION_GENERATE_KEY, parameter);
		List<Map<String, Object>> list = workOrderTransferDao
				.queryListForSql(conditionGenerate);
		return readAcceptUsers(list);
	}

	/**
	 * 根据通用工单编号获取通用工单派发信息
	 * 
	 * @param id
	 *            String 通用工单编号
	 * @return List<Map<String,Object>> 通用工单派发信息
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getDispatchList(String id) {
		// TODO Auto-generated method stub
		String key = TASK_ID_CONDITION_GENERATE_KEY;
		QueryParameter parameter = new QueryParameter();
		parameter.setId(id);
		ConditionGenerate conditionGenerate = getConditionGenerate(key,
				parameter);
		return workOrderTransferDao.queryListForSql(conditionGenerate);
	}

	/**
	 * 保存工单的单个分发信息
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单实体
	 * @param acceptUserId
	 *            String 分发的受理人编号
	 */
	private void saveOneWorkOrderTransfer(WorkOrder workOrder,
			String acceptUserId) {
		WorkOrderTransfer workOrderTransfer = new WorkOrderTransfer();
		workOrderTransfer.setTaskId(workOrder.getId());
		workOrderTransfer.setSourcePrincipal(workOrder.getCreater());
		workOrderTransfer.setTargetPrincipal(acceptUserId);
		SmParameter rmiParameter = getSmParameter(workOrder, acceptUserId);
		if (SysConstant.FORM_IS_SUBMITED.equals(workOrder.getIsSubmited())) {
			workOrderTransfer.setTaskState(WorkOrder.WORKORDER_SIGN_FOR_STATE);
			workOrderTransferDao.save(workOrderTransfer);
			doWorkflow(workOrderTransfer, rmiParameter);
		} else {
			workOrderTransfer
					.setTaskState(WorkOrder.WORKORDER_NOT_SUBMITED_STATE);
			workOrderTransferDao.save(workOrderTransfer);
		}
	}

	/**
	 * 根据工单查询到的受理人列表获取通用工单的受理人信息列表
	 * 
	 * @param list
	 *            List<Map<String, Object>> 工单查询到的受理人列表
	 * @return String[] 通用工单的受理人信息列表
	 */
	private String[] readAcceptUsers(List<Map<String, Object>> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new String[] { "", "" };
		}
		String acceptUserIds = "";
		String acceptUserNames = "";
		for (Map<String, Object> map : list) {
			if (QueryParameter.isNull(map)) {
				continue;
			}
			acceptUserIds += map.get("TARGET_PRINCIPAL");
			acceptUserIds += ",";
			acceptUserNames += map.get("TARGET_PRINCIPAL_NAME");
			acceptUserNames += ",";
		}
		acceptUserIds = acceptUserIds.substring(0, acceptUserIds.length() - 1);
		acceptUserNames = acceptUserNames.substring(0,
				acceptUserNames.length() - 1);
		return new String[] { acceptUserIds, acceptUserNames };
	}

	/**
	 * 获取发送短信的传递参数
	 * 
	 * @param workOrder
	 *            WorkOrder 通用工单实体
	 * @param acceptUserId
	 *            String 受理人编号
	 * @return SmParameter 发送短信的传递参数
	 */
	private SmParameter getSmParameter(WorkOrder workOrder, String acceptUserId) {
		// TODO Auto-generated method stub
		String simId = super.getUserPhone(acceptUserId);
		String[] contentParameters = new String[] { workOrder.getTaskName() };
		SmParameter parameter = SmParameter.getInstance(WORKORDER_XML_FILE_ID,
				SEND_TASK_MSG_ID, simId, contentParameters);
		parameter.setWrittenDb(true);
		parameter.setBusinessType(workOrder.getBusinessType());
		parameter.setHandleLimit(workOrder.getOvertimeSet());
		parameter.setHandlePersonId(acceptUserId);
		parameter.setWorkorderId(workOrder.getId());
		parameter.setWorkorderTitle(workOrder.getTaskName());
		parameter.setWorkorderType(SysConstant.COMMON_ORDER_FLOW_TYPE);
		return parameter;
	}

	/**
	 * 完成分发工作流的业务处理
	 * 
	 * @param workOrderTransfer
	 *            WorkOrderTransfer 通用工单分发信息实体
	 * @param smParameter
	 *            SmParameter 发送短信的传递参数
	 */
	private void doWorkflow(WorkOrderTransfer workOrderTransfer,
			SmParameter smParameter) {
		ProMockPo taskPi = new ProMockPo();
		taskPi.setBzid(workOrderTransfer.getId());
		taskPi.setUserId(workOrderTransfer.getSourcePrincipal());
		String userName = super.getUserName(workOrderTransfer
				.getSourcePrincipal());
		if (StringUtils.isBlank(userName)) {
			return;
		}
		taskPi.setUserName(userName);
		taskPi.setDealUsers(workOrderTransfer.getTargetPrincipal());
		userName = super.getUserName(workOrderTransfer.getTargetPrincipal());
		if (StringUtils.isBlank(userName)) {
			return;
		}
		taskPi.setDealUsersName(userName);
		super.workOrderWorkflowService.sendTask(taskPi, smParameter);
	}
}
