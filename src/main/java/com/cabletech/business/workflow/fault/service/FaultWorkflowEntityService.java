package com.cabletech.business.workflow.fault.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.workflow.common.service.WorkflowEntityManager;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.business.workflow.fault.dao.FaultBaseDao;
import com.cabletech.business.workflow.fault.model.FaultDispatch;

/**
 * 故障派单工作流业务服务
 * 
 * @author 杨隽 2011-11-08 创建
 * @author 杨隽 2011-11-29 添加getBusinessTitleColumn()方法和getAccessUrl()方法
 * @author 杨隽 2011-11-29 添加WAIT_HANDLE_LIST_URL常量和DISPATCH_TITLE_COLUMN_KEY常量
 * @author 杨隽 2012-02-09 实现getBusinessDataList()方法
 * 
 */
@Service
public class FaultWorkflowEntityService extends WorkflowEntityManager {
	// “待办列表跳转页面”常量
	public static final String WAIT_HANDLE_LIST_URL = "/workflow/faultDispatchAction!waitHandledList.action?parameter.isQuery=&businessType=";
	// “故障派单编号数据列名”常量
	public static final String DISPATCH_ID_COLUMN_KEY = "dispatch_id";
	// “故障派单标题数据列名”常量
	public static final String DISPATCH_TITLE_COLUMN_KEY = "trouble_title";
	// 故障派单查询条件生成器
	@Resource(name = "dispatchFaultListConditionGenerateImpl")
	private ConditionGenerate conditionGenerate;
	// 故障派单Dao
	@Resource(name = "faultDispatchDao")
	private FaultBaseDao<FaultDispatch, String> faultDispatchDao;

	/**
	 * 获取故障工作流待办列表访问地址
	 * 
	 * @return String 故障工作流待办列表访问地址
	 */
	@Override
	public String getAccessUrl() {
		// TODO Auto-generated method stub
		return WAIT_HANDLE_LIST_URL;
	}

	/**
	 * 获取故障业务数据的主键列名
	 * 
	 * @return String 故障业务数据的主键列名
	 */
	@Override
	public String getBusinessIdColumn() {
		// TODO Auto-generated method stub
		return DISPATCH_ID_COLUMN_KEY;
	}

	/**
	 * 获取故障业务数据的标题列名
	 * 
	 * @return String 故障业务数据的标题列名
	 */
	@Override
	public String getBusinessTitleColumn() {
		// TODO Auto-generated method stub
		return DISPATCH_TITLE_COLUMN_KEY;
	}

	/**
	 * 根据专业获取故障业务操作数据列表
	 * 
	 * @param businessType
	 *            String 专业编号
	 * @return List<Map<String, Object>> 故障业务操作数据列表
	 */
	@Override
	public List<Map<String, Object>> getBusinessDataList(String businessType) {
		// TODO Auto-generated method stub
		FaultQueryParameter parameter = new FaultQueryParameter();
		parameter.setBusinessType(businessType);
		conditionGenerate.setQuerySql(parameter);
		return faultDispatchDao.queryListForSql(conditionGenerate);
	}
	
	@Override
	public String getUrlSuffix(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return "";
	}
}
