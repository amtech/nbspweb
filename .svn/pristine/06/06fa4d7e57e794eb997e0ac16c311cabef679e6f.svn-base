package com.cabletech.business.base.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.dao.PatrolGroupDao;
import com.cabletech.business.base.model.PatrolGroup;
import com.cabletech.business.base.service.PatrolGroupService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 巡检组业务处理
 * 
 * @author wangt
 * 
 */
@SuppressWarnings("rawtypes")
@Service
public class PatrolGroupServiceImpl extends BaseServiceImpl implements
		PatrolGroupService {

	@Resource(name = "patrolGroupDao")
	private PatrolGroupDao patrolGroupDao;

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return patrolGroupDao;
	}

	@Override
	@Transactional(readOnly = true)
	public void getPatrolGroupList(String patrolGroupName, UserInfo user,
			Page page) {
		StringBuffer sql = new StringBuffer(
				" SELECT * FROM VIEW_PATROLGROUP WHERE 1=1 ");
		if (StringUtils.isNotBlank(patrolGroupName)) {
			sql.append(" AND NAME LIKE '%");
			sql.append(patrolGroupName);
			sql.append("%' ");
		}
		if (user.isCityMobile()) {
			sql.append(" AND REGIONID='");
			sql.append(user.getRegionId());
			sql.append("' ");
		}
		if (user.isContractor()) {
			sql.append(" AND ORGID='");
			sql.append(user.getOrgId());
			sql.append("' ");
		}
		patrolGroupDao.getSQLPageAll(page, sql.toString());
	}

	/**
	 * 根据组织ID获取巡检组
	 * 
	 * @param orgid
	 *            String
	 */
	@Deprecated
	@Transactional(readOnly = true)
	public List<PatrolGroup> getPatrolGroupByOrgid(String orgid) {
		List<PatrolGroup> PatrolGroupList = patrolGroupDao.findBy("orgid",
				orgid);
		return PatrolGroupList;
	}

	/**
	 * 根据人员ID获取巡检组
	 * 
	 * @param staffid
	 *            String
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getPatrolGroupByStaffid(String staffid) {
		List<Map<String, Object>> PatrolGroupList = patrolGroupDao
				.getPatrolGroupByStaffid(staffid);
		return PatrolGroupList;
	}

}
