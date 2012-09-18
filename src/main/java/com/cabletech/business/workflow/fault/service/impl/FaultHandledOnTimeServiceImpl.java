package com.cabletech.business.workflow.fault.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.workflow.common.condition.StatisticQueryParameter;
import com.cabletech.business.workflow.fault.dao.FaultHandledOnTimeDao;
import com.cabletech.business.workflow.fault.service.FaultHandledOnTimeService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 故障处理及时率统计
 * 
 * @author zhaobi
 * @author 杨隽 2012-04-28 进行方法的参数个数重构
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class FaultHandledOnTimeServiceImpl extends BaseServiceImpl implements
		FaultHandledOnTimeService {
	/**
	 * 故障处理及时Dao
	 */
	@Resource(name = "faultHandledOnTimeDao")
	private FaultHandledOnTimeDao faultHandledOnTimeDao;

	protected BaseDao getBaseDao() {
		return faultHandledOnTimeDao;
	}

	/**
	 * 获取故障处理及时列表
	 * 
	 * @param page
	 *            Page 分页信息数据
	 * @param parameter
	 *            StatisticQueryParameter 查询条件参数
	 * @return Page 分页信息数据
	 */
	@Override
	@Transactional(readOnly = true)
	public Page list(Page<Map<String, Object>> page,
			StatisticQueryParameter parameter) {
		StringBuffer condition = new StringBuffer("");
		if (StringUtils.isNotBlank(parameter.getStartTime())) {
			condition.append(" and wa.trouble_time>=to_date('");
			condition.append(parameter.getStartTime());
			condition.append("','yyyy-mm-dd') ");
		}
		if (StringUtils.isNotBlank(parameter.getEndTime())) {
			condition.append(" and wa.trouble_time<to_date('");
			condition.append(parameter.getEndTime());
			condition.append("','yyyy-mm-dd')+1 ");
		}
		if (StringUtils.isNotBlank(parameter.getContractorId())) {
			condition.append(" and ui.id=any( ");
			condition.append(" select id from view_org  ");
			condition.append(" start with id='");
			condition.append(parameter.getContractorId());
			condition.append("'  ");
			condition.append(" connect by prior id=parentid ");
			condition.append(" ) ");
		} else if (parameter.getUser().isContractor()) {
			condition.append(" and ui.id=any( ");
			condition.append(" select id from view_org  ");
			condition.append(" start with id='");
			condition.append(parameter.getUser().getOrgId());
			condition.append("'  ");
			condition.append(" connect by prior id=parentid ");
			condition.append(" ) ");
		}
		if (StringUtils.isNotBlank(parameter.getBusinessType())) {
			condition.append(" and wa.business_type='");
			condition.append(parameter.getBusinessType());
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
		return faultHandledOnTimeDao.getList(page, condition.toString());
	}

}
