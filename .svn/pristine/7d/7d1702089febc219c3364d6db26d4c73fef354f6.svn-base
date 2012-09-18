package com.cabletech.business.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.dao.UserInfoDao;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 登陆用户处理
 * 
 * @author wangt
 * 
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl implements
		UserInfoService {
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;
	@Resource(name = "userInfoDao")
	private UserInfoDao userInfoDao;

	@Override
	@Transactional(readOnly = true)
	public void getUserInfoList(String userName, UserInfo user,
			Page page){
		StringBuffer sql = new StringBuffer(
				" SELECT * FROM VIEW_USERINFO WHERE USERID IS NOT NULL ");
		if (StringUtils.isNotBlank(userName)) {
			sql.append(" AND USERNAME LIKE '%");
			sql.append(userName);
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
		userInfoDao.getSQLPageAll(page, sql.toString());
	}
	
	/**
	 * 根据登录用户用户名获取用户信息
	 * 
	 * @param userid
	 *            String
	 * @return
	 */
	@Transactional(readOnly = true)
	public UserInfo getUserInfoByUserId(String userid) {
		UserInfo user = baseInfoProvider.getLoginUserService()
				.getUserInfoByUserId(userid);
		return user;
	}

	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据登录用户人员ID获取用户信息
	 * 
	 * @param personId
	 *            String
	 */
	@Transactional(readOnly = true)
	public UserInfo getUserInfoByPersonId(String personId) {
		List<String> strlist = new ArrayList<String>();
		strlist.add(personId);
		List<UserInfo> user = baseInfoProvider.getLoginUserService()
				.getUserInfosByPersonIds(strlist);
		if (null != user && user.size() > 0) {
			return user.get(0);
		}

		return null;
	}

	/**
	 * 根据登录人员ID集合获取用户信息
	 * 
	 * @param ids
	 *            List<String>
	 */
	@Transactional(readOnly = true)
	public List<UserInfo> getUserInfoByPersonIds(List<String> ids) {
		List<UserInfo> userInfoList = baseInfoProvider.getLoginUserService()
				.getUserInfosByPersonIds(ids);
		return userInfoList;
	}

}
