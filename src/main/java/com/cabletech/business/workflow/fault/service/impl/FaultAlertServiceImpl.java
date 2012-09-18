package com.cabletech.business.workflow.fault.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.resource.service.ResourceService;
import com.cabletech.business.workflow.fault.condition.parameter.FaultQueryParameter;
import com.cabletech.business.workflow.fault.dao.FaultBaseDao;
import com.cabletech.business.workflow.fault.model.FaultAlert;
import com.cabletech.business.workflow.fault.service.FaultAlertService;
import com.cabletech.common.util.Page;

/**
 * 故障告警业务操作
 * 
 * @author 杨隽 2011-10-26 创建
 * @author 杨隽 2011-10-27 添加“查看故障告警单”业务方法、“忽略故障告警单”业务方法
 * @author 杨隽 2012-02-07 添加getList()方法和getUnDispatchedList()方法
 * @author 杨隽 2012-02-08 修改getBaseDao()方法实现为getFaultBaseDao ()方法实现
 * @author 杨隽 2012-02-08 提取获取故障业务单列表的公共方法
 * @author 杨隽 2012-07-12 修改viewFaultAlert()方法（添加获取资源名称业务处理）
 * 
 */
@Service
public class FaultAlertServiceImpl extends
		FaultBaseServiceImpl<FaultAlert, String> implements FaultAlertService {
	// 故障告警单Dao操作
	@Resource(name = "faultAlertDao")
	private FaultBaseDao<FaultAlert, String> faultAlertDao;
	// 资源服务类
	@Resource(name = "resourceServiceImpl")
	private ResourceService resourceService;

	@Override
	protected FaultBaseDao<FaultAlert, String> getFaultBaseDao() {
		// TODO Auto-generated method stub
		return faultAlertDao;
	}

	/**
	 * 根据故障告警单编号查看故障告警单信息
	 * 
	 * @param id
	 *            String 故障告警单编号
	 * @return FaultAlert 故障告警单信息
	 */
	@Transactional(readOnly = true)
	public FaultAlert viewFaultAlert(String id) {
		// TODO Auto-generated method stub
		FaultAlert faultAlert = faultAlertDao.get(id);
		if (faultAlert != null) {
			String stationName = resourceService.getResourceName(
					faultAlert.getBusinessType(), faultAlert.getStationId(),
					faultAlert.getStationType());
			faultAlert.setStationName(stationName);
		}
		return faultAlert;
	}

	/**
	 * 忽略故障告警单
	 * 
	 * @param id
	 *            String 故障告警单编号
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@Transactional
	public void ignore(String id, UserInfo userInfo) {
		// TODO Auto-generated method stub
		FaultAlert faultAlert = faultAlertDao.get(id);
		faultAlert.setState(FaultAlert.IGNORED_STATE);
		faultAlert.setIgnoreTime(new Date());
		faultAlert.setHandler(userInfo.getPersonId());
		faultAlertDao.save(faultAlert);
	}

	/**
	 * 根据查询条件获取故障告警单分页列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件参数
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return Page 故障告警单分页列表
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getList(FaultQueryParameter faultQueryParameter,
			UserInfo userInfo) {
		// TODO Auto-generated method stub
		faultQueryParameter.setUser(userInfo);
		Page page = super.getFaultList(faultQueryParameter,
				FAULT_LIST_CONDITION_GENERATE_KEY);
		return page;
	}

	/**
	 * 根据查询条件获取未派单故障告警单分页列表
	 * 
	 * @param faultQueryParameter
	 *            FaultQueryParameter 查询条件参数
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 * @return Page 未派单故障告警单分页列表
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page getUnDispatchedList(FaultQueryParameter faultQueryParameter,
			UserInfo userInfo) {
		// TODO Auto-generated method stub
		faultQueryParameter.setUser(userInfo);
		Page page = super.getFaultList(faultQueryParameter,
				UNDISPATCHED_FAULT_LIST_CONDITION_GENERATE_KEY);
		return page;
	}
}
