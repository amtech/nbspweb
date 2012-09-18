package com.cabletech.business.wplan.nopatrolstation.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.resource.dao.ResourceInfoDao;
import com.cabletech.business.resource.model.ResourceInfo;
import com.cabletech.business.wplan.nopatrolstation.dao.NoPatrolStationDao;
import com.cabletech.business.wplan.nopatrolstation.model.NoPatrolStation;
import com.cabletech.business.wplan.nopatrolstation.service.NoPatrolStationService;
import com.cabletech.business.wplan.plan.dao.PatrolinfoDao;
import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 未巡检站点原因登记业务接口实现
 * 
 * @author 杨隽 2012-07-23 创建
 * @author 杨隽 2012-07-25 在confirmNoPatrolStation()方法中添加站点退服和变更计划维护资源数量的业务处理
 * 
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class NoPatrolStationServiceImpl extends
		BaseServiceImpl<NoPatrolStation, String> implements
		NoPatrolStationService {
	/**
	 * 未巡检站点原因登记Dao
	 */
	@Resource(name = "noPatrolStationDao")
	private NoPatrolStationDao noPatrolStationDao;
	/**
	 * 巡检计划Dao
	 */
	@Resource(name = "patrolinfoDao")
	private PatrolinfoDao planDao;
	/**
	 * 资源Dao
	 */
	@Resource(name = "resourceInfoDao")
	private ResourceInfoDao resourceDao;
	/**
	 * 用户信息业务处理
	 */
	@Resource(name = "userInfoServiceImpl")
	protected UserInfoService userInfoService;

	@Override
	protected BaseDao<NoPatrolStation, String> getBaseDao() {
		return noPatrolStationDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly = true)
	public void queryNoPatrolStationPage(NoPatrolStation noPatrolStation,
			Page page) {
		String sql = noPatrolStationDao
				.getNoPatrolStationListSql(noPatrolStation);
		noPatrolStationDao.getSQLPageAll(page, sql);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly = true)
	public void queryStationPage(NoPatrolStation noPatrolStation, Page page) {
		String sql = noPatrolStationDao.getStationListSql(noPatrolStation);
		noPatrolStationDao.getSQLPageAll(page, sql);
	}

	@Override
	public void saveNoPatrolStation(NoPatrolStation noPatrolStation,
			UserInfo user) {
		noPatrolStation.setRecorder(user.getPersonId());
		noPatrolStation.setRecordDate(new Date());
		noPatrolStation.setProcessState(NoPatrolStation.NO_PROCESS_STATE);
		noPatrolStationDao.save(noPatrolStation);
	}

	@Override
	@Transactional(readOnly = true)
	public NoPatrolStation viewNoPatrolStation(String id) {
		NoPatrolStation noPatrolStation = noPatrolStationDao.get(id);
		UserInfo user = userInfoService.getUserInfoByPersonId(noPatrolStation
				.getRecorder());
		if (user != null) {
			noPatrolStation.setRecorderName(user.getUserName());
		}
		user = userInfoService.getUserInfoByPersonId(noPatrolStation
				.getManager());
		if (user != null) {
			noPatrolStation.setManagerName(user.getUserName());
		}
		Patrolinfo plan = planDao.get(noPatrolStation.getPlanId());
		if (plan != null) {
			noPatrolStation.setPlanName(plan.getPlanname());
		}
		ResourceInfo res = resourceDao.view(noPatrolStation.getResourceId());
		if (res != null) {
			noPatrolStation.setStationName(res.getResourceName());
		}
		return noPatrolStation;
	}

	@Override
	public void confirmNoPatrolStation(NoPatrolStation noPatrolStation,
			UserInfo user) {
		NoPatrolStation noPatrolStationTarget = noPatrolStationDao
				.get(noPatrolStation.getId());
		noPatrolStationTarget.setResult(noPatrolStation.getResult());
		noPatrolStationTarget.setRemark(noPatrolStation.getRemark());
		noPatrolStationTarget.setManager(user.getPersonId());
		noPatrolStationTarget.setProcessTime(new Date());
		noPatrolStationTarget.setProcessState(NoPatrolStation.PROCESSED_STATE);
		noPatrolStationDao.save(noPatrolStationTarget);
		if (NoPatrolStation.PASSED_AUDIT_RESULT.equals(noPatrolStationTarget.getResult())
				&& NoPatrolStation.IS_EXIT_NET_STATION
						.equals(noPatrolStationTarget.getProblemType())) {
			resourceDao.updateResourceState(
					noPatrolStationTarget.getResourceId(),
					ResourceInfo.EXIT_NET_STATE);
			planDao.updateResourceNum(noPatrolStationTarget.getRecordDate(),
					noPatrolStationTarget.getResourceId());
		}
	}
}
