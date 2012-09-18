package com.cabletech.business.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.base.dao.UserOnlineLogDao;
import com.cabletech.business.base.model.UserOnlineLog;
import com.cabletech.business.base.service.UserOnlineLogService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 用户登录日志
 * 
 * @author zhaob
 * 
 */
@SuppressWarnings("all")
@Service
@Transactional
public class UserOnlineLogServiceImpl extends BaseServiceImpl implements
		UserOnlineLogService {
	@Resource(name = "userOnlineLogDao")
	private UserOnlineLogDao userOnlineLogDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cabletech.business.base.service.UserOnlineLogService#UpdateLogoutTime
	 * (java.lang.String)
	 */
	@Override
	@Transactional
	public void UpdateLogoutTime(String personid) {
		userOnlineLogDao.UpdateLogoutTime(personid);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly = true)
	public void searchlog(String userName, String startTime, String endTime,
			Page page) {
		userOnlineLogDao.searchlog(userName, startTime, endTime, page);
	}

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
		userOnlineLogDao.dellogs(temp);

	}

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return userOnlineLogDao;
	}

	@Transactional
	public void save(UserOnlineLog onlineLog) {
		userOnlineLogDao.save(onlineLog);
	}
}
