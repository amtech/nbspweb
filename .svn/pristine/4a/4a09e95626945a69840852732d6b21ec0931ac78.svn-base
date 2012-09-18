package com.cabletech.business.workflow.fault.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.fault.dao.FaultStatisticDao;
import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.business.workflow.fault.service.FaultStatisticService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 首页故障派单统计业务服务接口实现
 * 
 * @author 杨隽 2012-03-09 创建
 * 
 */
@Service
@SuppressWarnings("rawtypes")
public class FaultStatisticServiceImpl extends BaseServiceImpl implements
		FaultStatisticService {
	// 首页故障派单统计Dao
	@Resource(name = "faultStatisticDao")
	private FaultStatisticDao faultStatisticDao;

	/**
	 * 获取首页故障派单统计列表
	 * 
	 * @param userInfo
	 *            UserInfo 当前登录用户
	 * @return List<Map<String, Object>> 首页故障派单统计列表
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getOvertimeWorkOrderStatisticResultList(
			UserInfo userInfo) {
		// TODO Auto-generated method stub
		StringBuffer conditionBuffer = new StringBuffer("");
		getFaultCommonCondition(userInfo, conditionBuffer);
		if (userInfo.isProvinceMobile()) {
			return faultStatisticDao.getOvertimeFaultNumberByRegion(
					conditionBuffer.toString(), userInfo.getRegionId());
		}
		if (userInfo.isCityMobile()) {
			return faultStatisticDao.getOvertimeFaultNumberByOrg(
					conditionBuffer.toString(), userInfo.getRegionId(), "");
		}
		if (userInfo.isContractor()) {
			return faultStatisticDao.getOvertimeFaultNumberByOrg(
					conditionBuffer.toString(), userInfo.getRegionId(),
					userInfo.getOrgId());
		}
		return new ArrayList<Map<String, Object>>();
	}

	/**
	 * 将当前用户信息、系统日期和故障归档状态放到查询条件缓冲区中
	 * 
	 * @param userInfo
	 *            UserInfo 当前用户信息
	 * @param conditionBuffer
	 *            StringBuffer 查询条件缓冲区
	 */
	private void getFaultCommonCondition(UserInfo userInfo,
			StringBuffer conditionBuffer) {
		Calendar c = Calendar.getInstance();
		conditionBuffer.append(" and wa.business_type in ( ");
		conditionBuffer.append(getBusinessTypes(userInfo));
		conditionBuffer.append(" ) ");
		conditionBuffer.append(" and wt.send_time>=to_date('");
		conditionBuffer.append(c.get(Calendar.YEAR) + "-"
				+ (c.get(Calendar.MONTH) + 1));
		conditionBuffer.append("-01','yyyy-mm-dd') ");
		conditionBuffer.append(" and wt.send_time<add_months(to_date('");
		conditionBuffer.append(c.get(Calendar.YEAR) + "-"
				+ (c.get(Calendar.MONTH) + 1));
		conditionBuffer.append("-01','yyyy-mm-dd'),1) ");
		conditionBuffer.append(" wa.ignore_state='");
		conditionBuffer.append(FaultAlert.FINISHED_STATE);
		conditionBuffer.append("' ");
	}

	/**
	 * 根据当前登录用户获取用户管理的专业类型
	 * 
	 * @param userInfo
	 *            UserInfo 当前登录用户
	 * @return String 用户管理的专业类型
	 */
	private String getBusinessTypes(UserInfo userInfo) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> businessTypeList = userInfo
				.getBusinessTypes();
		if (CollectionUtils.isEmpty(businessTypeList)) {
			return "''";
		}
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < businessTypeList.size(); i++) {
			Map<String, Object> map = businessTypeList.get(i);
			buf.append("'");
			buf.append(map.get("CODEVALUE"));
			buf.append("'");
			if (i < businessTypeList.size() - 1) {
				buf.append(",");
			}
		}
		return buf.toString();
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}
}
