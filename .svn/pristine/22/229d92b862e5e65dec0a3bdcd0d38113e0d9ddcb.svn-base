package com.cabletech.business.workflow.workorder.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.common.service.WorkflowEntityManager;
import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderTransfer;

/**
 * 通用工单工作流业务服务（用于首页显示）
 * 
 * @author 杨隽 2011-12-30 创建
 * @author 杨隽 2012-02-09 实现getBusinessDataList()方法
 * 
 */
@Service
public class WorkOrderWorkflowEntityService extends WorkflowEntityManager {
	// 通用工单工作流待办列表访问地址
	public static final String WAIT_HANDLE_LIST_URL = "/workflow/workOrderWaitHandledAction!list.action?rnd=";
	// 通用工单业务数据的主键列名
	public static final String WORKORDER_ID_COLUMN_KEY = "pid";
	// 通用工单业务数据的标题列名
	public static final String WORKORDER_TITLE_COLUMN_KEY = "task_name";
	// 通用工单查询条件生成器
	@Resource(name = "workOrderConditionGenerateImpl")
	private ConditionGenerate conditionGenerate;
	// 通用工单分发信息Dao
	@Resource(name = "workOrderTransferDao")
	private WorkOrderBaseDao<WorkOrderTransfer, String> workOrderTransferDao;

	/**
	 * 获取通用工单工作流待办列表访问地址
	 * 
	 * @return String 通用工单工作流待办列表访问地址
	 */
	@Override
	public String getAccessUrl() {
		// TODO Auto-generated method stub
		return WAIT_HANDLE_LIST_URL;
	}

	/**
	 * 获取通用工单业务数据的主键列名
	 * 
	 * @return String 通用工单业务数据的主键列名
	 */
	@Override
	public String getBusinessIdColumn() {
		// TODO Auto-generated method stub
		return WORKORDER_ID_COLUMN_KEY;
	}

	/**
	 * 获取通用工单业务数据的标题列名
	 * 
	 * @return String 通用工单业务数据的标题列名
	 */
	@Override
	public String getBusinessTitleColumn() {
		// TODO Auto-generated method stub
		return WORKORDER_TITLE_COLUMN_KEY;
	}

	/**
	 * 根据专业获取通用工单业务操作数据列表
	 * 
	 * @param businessType
	 *            String 专业编号
	 * @return List<Map<String, Object>> 通用工单业务操作数据列表
	 */
	@Override
	public List<Map<String, Object>> getBusinessDataList(String businessType) {
		// TODO Auto-generated method stub
		QueryParameter parameter = new QueryParameter();
		WorkOrder workOrder = new WorkOrder();
		workOrder.setBusinessType(businessType);
		parameter.setEntity(workOrder);
		conditionGenerate.setQuerySql(parameter);
		return workOrderTransferDao.queryListForSql(conditionGenerate);
	}

	@Override
	public String getUrlSuffix(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (String) map.get("id");
	}
}
