package com.cabletech.business.workflow.electricity.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.workflow.common.service.WorkflowEntityManager;
import com.cabletech.business.workflow.electricity.security.dao.ElectricitySecurityBaseDao;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.common.base.SysConstant;

/**
 * 断电告警派单工作流业务服务
 * 
 * @author 杨隽 2012-05-03 创建
 * 
 */
@Service
public class ElectricitySecurityWorkflowEntityService extends
		WorkflowEntityManager {
	// “待办列表跳转页面”常量
	public static final String WAIT_HANDLE_LIST_URL = "/workflow/oeDispatchTaskWaitHandledAction!list.action?businessType=";
	// “断电告警派单编号数据列名”常量
	public static final String DISPATCH_ID_COLUMN_KEY = "ID";
	// “断电告警派单标题数据列名”常量
	public static final String DISPATCH_TITLE_COLUMN_KEY = "TASK_NAME";
	// 断电告警派单查询条件生成器
	@Resource(name = "oeDispatchListConditionGenerateImpl")
	private ConditionGenerate conditionGenerate;
	// 断电告警派单Dao
	@Resource(name = "oeDispatchTaskDao")
	private ElectricitySecurityBaseDao<OeDispatchTask, String> oeDispatchTaskDao;

	/**
	 * 获取断电告警工作流待办列表访问地址
	 * 
	 * @return String 断电告警工作流待办列表访问地址
	 */
	@Override
	public String getAccessUrl() {
		// TODO Auto-generated method stub
		return WAIT_HANDLE_LIST_URL;
	}

	/**
	 * 获取断电告警业务数据的主键列名
	 * 
	 * @return String 断电告警业务数据的主键列名
	 */
	@Override
	public String getBusinessIdColumn() {
		// TODO Auto-generated method stub
		return DISPATCH_ID_COLUMN_KEY;
	}

	/**
	 * 获取断电告警业务数据的标题列名
	 * 
	 * @return String 断电告警业务数据的标题列名
	 */
	@Override
	public String getBusinessTitleColumn() {
		// TODO Auto-generated method stub
		return DISPATCH_TITLE_COLUMN_KEY;
	}

	/**
	 * 根据专业获取断电告警业务操作数据列表
	 * 
	 * @param businessType
	 *            String 专业编号
	 * @return List<Map<String, Object>> 断电告警业务操作数据列表
	 */
	@Override
	public List<Map<String, Object>> getBusinessDataList(String businessType) {
		// TODO Auto-generated method stub
		if (SysConstant.DICTIONARY_FORMITEM_BUSINESSTYPE_C31
				.equals(businessType)) {
			QueryParameter parameter = new QueryParameter();
			OeDispatchTask oeDispatchTask = new OeDispatchTask();
			oeDispatchTask.setBusinessType(businessType);
			parameter.setEntity(oeDispatchTask);
			conditionGenerate.setQuerySql(parameter);
			return oeDispatchTaskDao.queryListForSql(conditionGenerate);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}
	
	@Override
	public String getUrlSuffix(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return "";
	}
}
