package com.cabletech.business.workflow.workorder.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.base.model.ModuleCatalog;
import com.cabletech.business.base.model.SmParameter;
import com.cabletech.business.base.service.UploadFileService;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.dao.WorkOrderReplyDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderReply;
import com.cabletech.business.workflow.workorder.model.WorkOrderTransfer;
import com.cabletech.business.workflow.workorder.service.WorkOrderReplyService;
import com.cabletech.common.base.SysConstant;

/**
 * 通用工单回复业务接口实现
 * 
 * @author 杨隽 2012-01-06 创建
 * @author 杨隽 2012-01-09 添加getReplyList方法
 * @author 杨隽 2012-01-10 添加getLatestWorkOrderReply方法
 * @author 杨隽 2012-01-10 在save方法中添加上传附件的保存功能
 * @author 杨隽 2012-02-07 修改getBaseDao()方法实现为getWorkOrderBaseDao()方法实现
 * @author 杨隽 2012-03-27 集成工单短信发送功能（添加getSmParameter方法并修改save方法和doWorkflow方法）
 * @author 杨隽 2012-05-04 更改使用公共的表单提交标识
 * 
 */
@Service
@Transactional
public class WorkOrderReplyServiceImpl extends
		WorkOrderBaseServiceImpl<WorkOrderReply, String> implements
		WorkOrderReplyService {
	// 通用工单信息Dao
	@Resource(name = "workOrderDao")
	private WorkOrderBaseDao<WorkOrder, String> workOrderDao;
	// 通用工单回复信息Dao
	@Resource(name = "workOrderReplyDao")
	private WorkOrderReplyDao workOrderReplyDao;
	// 通用工单分发信息Dao
	@Resource(name = "workOrderTransferDao")
	private WorkOrderBaseDao<WorkOrderTransfer, String> workOrderTransferDao;
	// 上传附件业务处理
	@Resource(name = "uploadFileServiceImpl")
	private UploadFileService uploadFileService;

	@Override
	protected WorkOrderBaseDao<WorkOrderReply, String> getWorkOrderBaseDao() {
		// TODO Auto-generated method stub
		return workOrderReplyDao;
	}

	/**
	 * 保存通用工单回单信息
	 * 
	 * @param workOrderReply
	 *            WorkOrderReply 通用工单回单实体
	 * @throws RuntimeException
	 *             异常
	 */
	@Override
	public void save(WorkOrderReply workOrderReply) throws RuntimeException {
		// TODO Auto-generated method stub
		SmParameter smParameter = getSmParameter(workOrderReply);
		workOrderReplyDao.save(workOrderReply);
		if (SysConstant.FORM_IS_SUBMITED.equals(workOrderReply.getIsSubmited())) {
			String pId = workOrderReply.getTransferId();
			WorkOrderTransfer workOrderTransfer = workOrderTransferDao.get(pId);
			ProMockPo proMockPo = doWorkflow(workOrderReply, smParameter);
			String taskName = proMockPo.getTaskName();
			String taskState = WorkOrder.getTaskState(taskName);
			workOrderTransfer.setTaskState(taskState);
			workOrderTransferDao.save(workOrderTransfer);
		}
		// 保存上传附件信息
		try {
			uploadFileService.saveFiles(workOrderReply.getFileList(),
					ModuleCatalog.SENDTASK, "", workOrderReply.getId(),
					WorkOrder.WTASK_RECEIPT_KEY, workOrderReply.getCreater());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据通用工单的分发编号获取通用工单回单信息列表
	 * 
	 * @param id
	 *            String 通用工单的分发编号
	 * @return List<Map<String, Object>> 通用工单回单信息列表
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getReplyList(String id) {
		// TODO Auto-generated method stub
		QueryParameter parameter = new QueryParameter();
		parameter.setId(id);
		ConditionGenerate conditionGenerate = getConditionGenerate(
				TRANSFER_ID_CONDITION_GENERATE_KEY, parameter);
		return workOrderReplyDao.queryListForSql(conditionGenerate);
	}

	/**
	 * 根据通用工单的分发编号获取最近一条通用工单回单信息
	 * 
	 * @param id
	 *            String 通用工单的分发编号
	 * @return WorkOrderReply 最近一条通用工单回单信息
	 */
	@Override
	@Transactional(readOnly = true)
	public WorkOrderReply getLatestWorkOrderReply(String id) {
		// TODO Auto-generated method stub
		List<WorkOrderReply> list = workOrderReplyDao.find(
				" from WorkOrderReply where transferId=? ", id);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(list.size() - 1);
	}

	/**
	 * 获取发送短信的传递参数
	 * 
	 * @param workOrderReply
	 *            WorkOrderReply 通用工单回单实体
	 * @return SmParameter 发送短信的传递参数
	 */
	private SmParameter getSmParameter(WorkOrderReply workOrderReply) {
		// TODO Auto-generated method stub
		String taskId = workOrderReply.getTaskId();
		WorkOrder workOrder = workOrderDao.get(taskId);
		String simId = super.getUserPhone(workOrderReply.getReplyCheckUserId());
		String replyUserName = super.getUserName(workOrderReply.getCreater());
		String[] contentParameters = new String[] { workOrder.getTaskName(),
				replyUserName };
		SmParameter parameter = SmParameter.getInstance(WORKORDER_XML_FILE_ID,
				REPLY_TASK_MSG_ID, simId, contentParameters);
		parameter.setWrittenDb(true);
		parameter.setBusinessType(workOrder.getBusinessType());
		parameter.setHandleLimit(workOrder.getOvertimeSet());
		parameter.setHandlePersonId(workOrderReply.getReplyCheckUserId());
		parameter.setWorkorderId(workOrder.getId());
		parameter.setWorkorderTitle(workOrder.getTaskName());
		parameter.setWorkorderType(SysConstant.COMMON_ORDER_FLOW_TYPE);
		return parameter;
	}

	/**
	 * 完成回复工作流的业务处理
	 * 
	 * @param workOrderReply
	 *            WorkOrderReply 通用工单回单实体
	 * @param smParameter
	 *            SmParameter 发送短信的传递参数
	 * @return ProMockPo 工作流实体
	 */
	private ProMockPo doWorkflow(WorkOrderReply workOrderReply,
			SmParameter smParameter) {
		ProMockPo taskPi = new ProMockPo();
		taskPi.setUserId(workOrderReply.getCreater());
		taskPi.setDealUsers(workOrderReply.getReplyCheckUserId());
		taskPi.setTaskId(workOrderReply.getWorkflowTaskId());
		taskPi.setTransition(SysConstant.PASS_WORKFLOW_TRANSTION);
		String userName = super.getUserName(workOrderReply.getCreater());
		if (StringUtils.isNotBlank(userName)) {
			taskPi.setUserName(userName);
		}
		userName = super.getUserName(workOrderReply.getReplyCheckUserId());
		if (StringUtils.isNotBlank(userName)) {
			taskPi.setDealUsersName(userName);
		}
		return super.workOrderWorkflowService.doTask(taskPi, smParameter);
	}
}
