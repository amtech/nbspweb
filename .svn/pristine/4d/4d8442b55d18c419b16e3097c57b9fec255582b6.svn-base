package com.cabletech.business.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.base.dao.UserActionLogDao;
import com.cabletech.business.base.model.UserActionLog;
import com.cabletech.business.base.service.UserActionLogService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 用户操作日志服务
 * 
 * @author zhaob
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class UserActionLogServiceImpl extends BaseServiceImpl implements
		UserActionLogService {
	@Resource(name = "userActionLogDao")
	private UserActionLogDao userActionLogDao;

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return userActionLogDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cabletech.business.base.service.UserActionLogService#savelog(com.
	 * cabletech.business.base.model.UserActionLog)
	 */
	@Override
	@Transactional
	public void savelog(UserActionLog userActionLog) {
		userActionLogDao.savelog(userActionLog);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cabletech.business.base.service.UserActionLogService#searchlog(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public void searchlog(String userName, String startTime, String endTime,
			Page page) {
		userActionLogDao.searchlog(userName, startTime, endTime, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cabletech.business.base.service.UserActionLogService#dellogs(java
	 * .lang.String[])
	 */
	@Override
	@Transactional
	public void dellogs(String[] ids) {
		String temp = "";
		for (int i = 0; i < ids.length; i++) {
			if (i > 0) {
				temp = temp + ",";
			}
			temp = temp + "'" + ids[i] + "'";
		}
		userActionLogDao.dellogs(temp);
	}
}
