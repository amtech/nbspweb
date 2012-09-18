package com.cabletech.business.base.dao;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.base.model.UserOnlineLog;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 用户在线日志
 * 
 * @author zhaobi
 * 
 */
@Repository
public class UserOnlineLogDao extends BaseDao<UserOnlineLog, String> {

	/**
	 * 更新退出时间
	 * 
	 * @param personid
	 *            String
	 */
	public void UpdateLogoutTime(String personid) {
		String sql = "update base_useronlielog u set u.logout_time=sysdate";
		sql += " where  u.logout_time is null and u.PERSONID='" + personid
				+ "'";
		this.getJdbcTemplate().update(sql);
	}

	/**
	 * 查询在线日志
	 * 
	 * @param userName
	 *            用户名
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param page
	 *            Page
	 * @return void
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void searchlog(String userName, String startTime, String endTime,
			Page page) {
		String sql = "select t.id,TO_CHAR(decode(t.logout_time,null,'','','',(t.logout_time-t.login_time)*24),'FM99999990.099') AS online_time,to_char(t.login_time,'yyyy-mm-dd hh24:mi:ss') as login_time,to_char(t.logout_time,'yyyy-mm-dd hh24:mi:ss') as logout_time,t.ip,u.username as personid"
				+ " from base_useronlielog t"
				+ " left join view_userinfo u on t.personid = u.sid"
				+ " where 1=1";
		if (StringUtils.isNotBlank(userName)) {
			sql += " and u.username like '%" + userName + "%'";
		}
		if (StringUtils.isNotBlank(startTime)
				&& StringUtils.isNotBlank(endTime)) {
			sql += " and t.login_time between to_date('" + startTime
					+ "','yyyy-mm-dd hh24:mi:ss') and  to_date('" + endTime
					+ "','yyyy-mm-dd hh24:mi:ss')";
		}
		sql += " order by t.login_time desc";
		super.getSQLPageAll(page, sql);
	}

	/**
	 * 删除日志
	 * 
	 * @param ids
	 *            String
	 */
	public void dellogs(String ids) {
		String sql = "delete base_useronlielog t where t.id in (" + ids + ")";
		if (StringUtils.isNotBlank(ids)) {
			this.getJdbcTemplate().update(sql);
		}
	}

}