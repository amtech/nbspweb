package com.cabletech.business.wplan.plan.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cabletech.business.workflow.common.service.WorkflowEntityManager;
import com.cabletech.business.wplan.plan.dao.PatrolinfoDao;
import com.cabletech.business.wplan.plan.model.Patrolinfo;

/**
 * 无线计划工作流业务服务（用于首页显示）
 * 
 * @author 杨隽 2012-04-18 创建
 * 
 */
@Service
public class WplanWorkflowEntityService extends WorkflowEntityManager {
	// 无线计划工作流待办列表访问地址
	public static final String WAIT_HANDLE_LIST_URL = "";
	// 无线计划业务数据的主键列名
	public static final String WPLAN_ID_COLUMN_KEY = "ID";
	// 无线计划业务数据的标题列名
	public static final String WPLAN_TITLE_COLUMN_KEY = "PLAN_NAME";
	// 无线计划Dao
	@Resource(name = "patrolinfoDao")
	private PatrolinfoDao patrolinfoDao;

	/**
	 * 获取无线计划工作流待办列表访问地址
	 */
	@Override
	public String getAccessUrl() {
		// TODO Auto-generated method stub
		return WAIT_HANDLE_LIST_URL;
	}

	/**
	 * 获取无线计划业务数据的主键列名
	 */
	@Override
	public String getBusinessIdColumn() {
		// TODO Auto-generated method stub
		return WPLAN_ID_COLUMN_KEY;
	}

	/**
	 * 获取无线计划业务数据的标题列名
	 */
	@Override
	public String getBusinessTitleColumn() {
		// TODO Auto-generated method stub
		return WPLAN_TITLE_COLUMN_KEY;
	}


	/* (non-Javadoc)
	 * @see com.cabletech.business.workflow.common.service.WorkflowEntityManager#getBusinessDataList(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getBusinessDataList(String businessType) {
		// TODO Auto-generated method stub
		Patrolinfo patrolinfo = new Patrolinfo();
		patrolinfo.setBusinesstype(businessType);
		return patrolinfoDao.queryPatrolinfoList(patrolinfo);
	}
	
	@Override
	public String getUrlSuffix(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return "";
	}
}
