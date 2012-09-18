package com.cabletech.business.workflow.workorder.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.workflow.common.condition.StatisticQueryParameter;
import com.cabletech.business.workflow.workorder.dao.WorkOrderHandledOverTimeDao;
import com.cabletech.business.workflow.workorder.service.WorkOrderHandledOverTimeService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 超时工单查询业务处理接口
 * 
 * @author Administrator 2012-03-15 创建
 * @author 杨隽 2012-04-28 进行方法的参数个数重构
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class WorkOrderHandledOverTimeServiceImpl extends BaseServiceImpl
		implements WorkOrderHandledOverTimeService {
	// 超时工单查询Dao
	@Resource(name = "workOrderHandledOverTimeDao")
	private WorkOrderHandledOverTimeDao workOrderHandledOverTimeDao;

	/**
	 * 超时工单查询
	 * 
	 * @param page
	 *            Page<Map<String, Object>> 分页数据信息
	 * @param parameter
	 *            StatisticQueryParameter 查询条件参数
	 * @return Page 分页数据信息
	 */
	@Override
	@Transactional(readOnly = true)
	public Page list(Page<Map<String, Object>> page,
			StatisticQueryParameter parameter) {
		StringBuffer condition = new StringBuffer("");
		if (StringUtils.isNotBlank(parameter.getStartTime())) {
			condition.append(" and wo.create_date>=to_date('");
			condition.append(parameter.getStartTime());
			condition.append("','yyyy-mm-dd') ");
		}
		if (StringUtils.isNotBlank(parameter.getEndTime())) {
			condition.append(" and wo.create_date<to_date('");
			condition.append(parameter.getEndTime());
			condition.append("','yyyy-mm-dd')+1 ");
		}
		if (StringUtils.isNotBlank(parameter.getContractorId())) {
			condition.append(" and ui.orgid=any( ");
			condition.append(" select id from view_org  ");
			condition.append(" start with id='");
			condition.append(parameter.getContractorId());
			condition.append("'  ");
			condition.append(" connect by prior id=parentid ");
			condition.append(" ) ");
		} else if (parameter.getUser().isContractor()) {
			condition.append(" and ui.orgid=any( ");
			condition.append(" select id from view_org  ");
			condition.append(" start with id='");
			condition.append(parameter.getUser().getOrgId());
			condition.append("'  ");
			condition.append(" connect by prior id=parentid ");
			condition.append(" ) ");
		}
		if (StringUtils.isNotBlank(parameter.getOderType())) {
			condition.append(" and wo.task_type='");
			condition.append(parameter.getOderType());
			condition.append("' ");
		}
		if (parameter.getUser().isMobile()) {
			condition.append(" and ui.regionid=any( ");
			condition.append(" select regionid from view_region ");
			condition.append(" start with regionid='");
			condition.append(parameter.getUser().getRegionId());
			condition.append("' ");
			condition.append(" connect by prior regionid=parentid");
			condition.append(" ) ");
		}
		return workOrderHandledOverTimeDao.getList(page, condition.toString());
	}

	protected BaseDao getBaseDao() {
		return workOrderHandledOverTimeDao;
	}
}
