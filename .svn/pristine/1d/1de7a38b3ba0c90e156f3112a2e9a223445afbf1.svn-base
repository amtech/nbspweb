package com.cabletech.business.base.dao;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.base.model.UserActionLog;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 用户日志
 * 
 * @author wangt
 * 
 */
@Repository
public class UserActionLogDao extends BaseDao<UserActionLog, String> {
	/**
	 * 保存日志
	 * 
	 * @param userActionLog
	 *            UserActionLog
	 */
	public void savelog(UserActionLog userActionLog) {
		String sql = "insert into base_useractionlog(id, personid, menuid, operate_time, class_method, method_desc, loginip,recordid,recordinfo)"
				+ " values (LPAD(seq_baselog_id.nextval,12,'0'),'"
				+ userActionLog.getPersonid()
				+ "','"
				+ userActionLog.getMenuid()
				+ "',sysdate,'"
				+ userActionLog.getClass_method()
				+ "','"
				+ userActionLog.getMethod_desc()
				+ "','"
				+ userActionLog.getLoginip()
				+ "','"
				+ userActionLog.getRecordid()
				+ "','"
				+ userActionLog.getRecord() + "')";
		logger.info("sql :" + sql);
		this.getJdbcTemplate().execute(sql);
	}

	/**
	 * 查询操作日志
	 * 
	 * @param userName
	 *            用户名
	 * @param startTime
	 *            开始时间
	 * @param endTiem
	 *            结束时间
	 * @param page
	 * @return List<UserActionLog>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void searchlog(String userName, String startTime, String endTiem,
			Page page) {
		String sql = "select t.id,to_char(t.operate_time,'yyyy-mm-dd hh24:mi:ss') as operate_time,t.loginip,t.recordid,t.method_desc,t.recordinfo,u.username as personid,t.menuid,t.class_method"
				+ " from base_useractionlog t "
				+ " left join view_userinfo u on t.personid = u.sid "
				+ " where 1=1";
		if (StringUtils.isNotBlank(userName)) {
			sql += " and u.username like '%" + userName + "%'";
		}
		if (StringUtils.isNotBlank(startTime)
				&& StringUtils.isNotBlank(endTiem)) {
			sql += " and t.operate_time between to_date('" + startTime
					+ "','yyyy-mm-dd hh24:mi:ss') and  to_date('" + endTiem
					+ "','yyyy-mm-dd hh24:mi:ss')";
		}
		sql += " order by t.operate_time desc";
		super.getSQLPageAll(page, sql);
	}

	/**
	 * 删除日志
	 * 
	 * @param ids
	 *            String
	 */
	public void dellogs(String ids) {
		String sql = "delete base_useractionlog t where t.id in (" + ids + ")";
		if (StringUtils.isNotBlank(ids)) {
			this.getJdbcTemplate().update(sql);
		}
	}

}
