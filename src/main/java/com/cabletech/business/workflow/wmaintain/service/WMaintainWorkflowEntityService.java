package com.cabletech.business.workflow.wmaintain.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.common.service.WorkflowEntityManager;
import com.cabletech.business.workflow.wmaintain.dao.WMaintainBaseDao;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;

/**
 * 隐患工作流业务服务（用于首页显示）
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-27 添加内容实现
 * 
 */
@Service
public class WMaintainWorkflowEntityService extends WorkflowEntityManager {
	// “待办列表跳转页面”常量
	public static final String WAIT_HANDLE_LIST_URL = "/workflow/wmaintainHandleAction!waitHandleList.action?businessType=";
	// “隐患维修作业计划编号数据列名”常量
	public static final String ID_COLUMN_KEY = "id";
	// “隐患维修作业计划名称数据列名”常量
	public static final String PLAN_NAME_COLUMN_KEY = "plan_name";
	// 隐患维修作业计划查询条件生成器
	@Resource(name = "wmaintainQueryListConditionGenerateImpl")
	private ConditionGenerate conditionGenerate;
	// 维修作业计划Dao
	@Resource(name = "WMaintainPlanDao")
	private WMaintainBaseDao<WMaintainPlan, String> wMaintainPlanDao;

	/**
	 * 获取隐患工作流待办列表访问地址
	 * 
	 * @return String 隐患工作流待办列表访问地址
	 */
	@Override
	public String getAccessUrl() {
		return WAIT_HANDLE_LIST_URL;
	}

	/**
	 * 获取隐患业务数据的主键列名
	 * 
	 * @return String 隐患业务数据的主键列名
	 */
	@Override
	public String getBusinessIdColumn() {
		return ID_COLUMN_KEY;
	}

	/**
	 * 获取隐患业务数据的标题列名
	 * 
	 * @return String 隐患业务数据的标题列名
	 */
	@Override
	public String getBusinessTitleColumn() {
		// TODO Auto-generated method stub
		return PLAN_NAME_COLUMN_KEY;
	}

	/**
	 * 根据专业获取隐患业务操作数据列表
	 * 
	 * @param businessType
	 *            String 专业编号
	 * @return List<Map<String, Object>> 隐患业务操作数据列表
	 */
	@Override
	public List<Map<String, Object>> getBusinessDataList(String businessType) {
		// TODO Auto-generated method stub
		WMaintainPlan plan = new WMaintainPlan();
		plan.setBusinessType(businessType);
		QueryParameter parameter = new QueryParameter();
		parameter.setEntity(plan);
		conditionGenerate.setQuerySql(parameter);
		return wMaintainPlanDao.queryListForSql(conditionGenerate);
	}
	
	@Override
	public String getUrlSuffix(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return "";
	}
}
