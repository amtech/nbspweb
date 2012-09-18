package com.cabletech.business.contactletter.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.baseinfo.business.util.SqlConstructor;
import com.cabletech.business.contactletter.constant.Constant4Bcl;
import com.cabletech.business.contactletter.model.BusinessContactLetter;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.DateUtil;
import com.cabletech.common.util.Page;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class BusinessContactLetterDao extends
		BaseDao<BusinessContactLetter, String> {
	private static Logger logger = Logger.getLogger("BusinessContactletterDao");

	/**
	 * 保存至数据库
	 * 
	 * @param businessContactletter
	 *            BusinessContactLetter
	 * @return
	 */
	public boolean saveBusinessContactletter(
			BusinessContactLetter businessContactletter) {
		save(businessContactletter);
		logger.info("保存成功!");
		return true;
	}

	/**
	 * 删除就是逻辑删除
	 * 
	 * @param businessContactletter
	 *            BusinessContactLetter
	 * @return
	 */
	public boolean deleteBusinessContactletter(
			BusinessContactLetter businessContactletter) {
		businessContactletter.setStatus(Constant4Bcl.DELETE);
		businessContactletter.setId(businessContactletter.getId());
		save(businessContactletter);
		logger.info("逻辑删除成功!");
		return true;

	}

	/**
	 * 获取对象
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	public BusinessContactLetter getById(String id) {
		BusinessContactLetter bcl = this.get(id);
		return bcl;
	}

	/**
	 * 内部类 查询使用
	 * 
	 * @author Administrator
	 * 
	 */
	protected class BusinessContactLetterMapper implements
			RowMapper<BusinessContactLetter> {
		BusinessContactLetter businessContactletter = null;
		java.sql.Clob clob = null;

		@Override
		public BusinessContactLetter mapRow(ResultSet rst, int rowNum)
				throws SQLException {
			businessContactletter = new BusinessContactLetter();
			businessContactletter.setId(rst.getString("id"));
			businessContactletter.setTitle(rst.getString("title"));
			businessContactletter
					.setDocumentType(rst.getString("documentType"));
			businessContactletter.setAuditUserId(rst.getString("auditUserId"));
			businessContactletter.setExpirationTime(rst
					.getTimestamp("expirationTime"));
			businessContactletter.setIsAudit(rst.getString("isAudit"));
			businessContactletter.setIsEmergency(rst.getString("isEmergency"));
			businessContactletter.setIssuerAreaUserIds(rst
					.getString("issuerAreaUserIds"));
			businessContactletter.setReleaseContent(rst
					.getString("releaseContent"));
			businessContactletter.setReleaseTime(rst
					.getTimestamp("releaseTime"));
			businessContactletter.setStatus(rst.getString("status"));
			return businessContactletter;
		}
	}

	/**
	 * 取出所有当前登录人需要审核的信息列表
	 * 
	 * @param entity
	 *            BusinessContactLetter
	 * @param page
	 *            page
	 * @param user
	 *            UserInfo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page queryPage4WaitHand(BusinessContactLetter entity, Page page,
			UserInfo user) {
		StringBuffer sql = getSql(entity, user);
		return getSQLPageAll(page, sql.toString());
	}

	/**
	 * 获取待办列表
	 * 
	 * @param entity
	 *            BusinessContactLetter
	 * @param user
	 *            UserInfo
	 * @return
	 */
	public List queryList4WaitHand(BusinessContactLetter entity, UserInfo user) {
		StringBuffer sql = getSql(entity, user);
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 获取查询的sql
	 * 
	 * @param entity
	 *            BusinessContactLetter
	 * @param user
	 *            UserInfo
	 * @return
	 */
	private StringBuffer getSql(BusinessContactLetter entity, UserInfo user) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select t.id, t.documentnumber,case t.type when '1' then '通告' when '2' then '通知' end type,t.isemergency dd,")
				.append("t.title, vu.username releaseusername,t.releasetime,t.expirationtime,sysdate,")
				.append(" t.status, case t.status when '0' then '已保存' when '1' then '待审核' ")
				.append(" when '2' then '已发布' when '3' then '驳回' when '9' then '已删除' end statusName ")
				.append(", case  t.isemergency when '1' then '紧急' when '0' then '普通' end isemergency, vjm.taskid  ")
				.append(" from Business_Contact_Letter t left join view_jbpm_usertask vjm on  vjm.bzid=t.id  left join  view_userinfo vu ")
				.append(" on vu.sid=t.releaseuserid where t.status not in (0,9) ");
		if (user != null) {
			sql.append(" and (vjm.assignee_='" + user.getPersonId()
					+ "' or vjm.userid_='" + user.getPersonId()
					+ "' or vjm.groupid_='" + user.getOrgId() + "') ");
		}
		String beginDate = DateUtil.UtilDate2Str(entity.getBeginDate(),
				"yyyy-MM-dd");
		String endDate = DateUtil.UtilDate2Str(entity.getEndDate(),
				"yyyy-MM-dd");

		if (StringUtils.isNotBlank(entity.getTitle())) {
			sql.append(" and t.title like '%" + entity.getTitle() + "%'");
		}
		if (StringUtils.isNotBlank(entity.getDocumentNumber())) {
			sql.append(" and t.documentNumber like '%"
					+ entity.getDocumentNumber() + "%'");
		}
		if (StringUtils.isNotBlank(endDate)) {
			sql.append(" and t.releaseTime <= to_date('" + endDate.trim()
					+ " 23:59:59','yyyy/MM/dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(beginDate)) {
			sql.append(" and t.releaseTime >= to_date('" + beginDate.trim()
					+ " 00:00:00','yyyy/MM/dd hh24:mi:ss') ");
		}

		sql.append(" order by dd ,t.id desc ");
		return sql;
	}
 
	/**
	 * 获取移动用户登录进去查询的sql when '9' then '已删除'
	 * 
	 * @return
	 */
	public StringBuffer getYdSqlStr() {
		StringBuffer sql_yd = new StringBuffer();
		sql_yd.append(
				" select distinct t.id, t.documentnumber,case t.type when '1' then '通告' when '2' then '通知' end type, t.isemergency dd,")
				.append("t.title,t.releaseuserid, vu.username RELEASEUSERNAME,t.releasetime,t.audituserid,t.expirationtime,")
				.append(" t.status, case t.status when '0' then '已保存' when '1' then '待审核' ")
				.append(" when '2' then '已发布' when '3' then '驳回'  end statusName ")
				.append("  ,case  t.isemergency when '1' then '紧急' when '0' then '普通' end isemergency ,")
				.append("  case  t.issend when '1' then '需要' when '0' then '不需要' end issend ")
				.append(" from Business_Contact_Letter t left join view_userinfo vu ")
				.append(" on vu.sid=t.releaseuserid left join view_region vr on vr.regionid = vu.regionid  where  t.status <>9 ");
		return sql_yd;
	}

	/**
	 * 获取代维用户登录进去查询使用的 sql when '9' then '已删除'
	 * 
	 * @param user
	 *            UserInfo
	 * @return sql
	 */
	public StringBuffer getDwSqlStr(UserInfo user) {
		StringBuffer sqlDw = new StringBuffer();
		sqlDw.append(
				" select distinct t.id, t.documentnumber,case t.type when '1' then '通告' when '2' then '通知' end type, t.isemergency dd,")
				.append("t.title,t.releaseuserid, vu.username RELEASEUSERNAME,t.releasetime,t.audituserid,t.expirationtime,")
				.append(" t.status,case t.status when '0' then '已保存' when '1' then '待审核' ")
				.append(" when '2' then '已发布' when '3' then '驳回' end statusName ")
				.append("  ,case  t.isemergency when '1' then '紧急' when '0' then '普通' end isemergency ,")
				.append("  case  t.issend when '1' then '需要' when '0' then '不需要' end issend ")
				.append(" from Business_Contact_Letter t left join view_userinfo vu")
				.append(" on vu.sid=t.releaseuserid  left join business_contact_letter_sendee e on ")
				.append("t.id=e.letterid where   t.status =2 and  e.objectid='"
						+ user.getPersonId() + "'");
		return sqlDw;
	}

	/**
	 * 为 移动用户 组装sql 查询的 最后sql
	 * 
	 * @param sqlYd
	 *            StringBuffer
	 * @param sqlDw
	 *            StringBuffer
	 * @param user
	 *            UserInfo
	 * @param entity
	 *            BusinessContactLetter
	 * @return
	 */
	public String getSql4yd(StringBuffer sqlYd, StringBuffer sqlDw,
			UserInfo user, BusinessContactLetter entity) {
		String beginDate = DateUtil.UtilDate2Str(entity.getBeginDate(),
				"yyyy-MM-dd");
		String endDate = DateUtil.UtilDate2Str(entity.getEndDate(),
				"yyyy-MM-dd");
		StringBuffer sql_union = new StringBuffer();
		sql_union.append("   union  ");
		sqlYd.append(SqlConstructor.regionIteration("vr", "regionid",
				user.getRegionId()));
		if (StringUtils.isNotBlank(beginDate)) {
			sqlYd.append(" and t.releaseTime >= to_date('" + beginDate.trim()
					+ " 00:00:00','yyyy/MM/dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(entity.getTitle())) {
			sqlYd.append(" and t.title like '%" + entity.getTitle() + "%'");
		}
		if (StringUtils.isNotBlank(entity.getDocumentNumber())) {
			sqlYd.append(" and t.documentNumber like '%"
					+ entity.getDocumentNumber() + "%'");
		}
		if (StringUtils.isNotBlank(endDate)) {
			sqlYd.append(" and t.releaseTime <= to_date('" + endDate.trim()
					+ " 23:59:59','yyyy/MM/dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(beginDate)) {
			sqlDw.append(" and t.releaseTime >= to_date('" + beginDate.trim()
					+ " 00:00:00','yyyy/MM/dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(entity.getTitle())) {
			sqlDw.append(" and t.title like '%" + entity.getTitle() + "%'");
		}
		if (StringUtils.isNotBlank(entity.getDocumentNumber())) {
			sqlDw.append(" and t.documentNumber like '%"
					+ entity.getDocumentNumber() + "%'");
		}
		if (StringUtils.isNotBlank(endDate)) {
			sqlDw.append(" and t.releaseTime <= to_date('" + endDate.trim()
					+ " 23:59:59','yyyy/MM/dd hh24:mi:ss') ");
		}

		return sqlYd.append(sql_union).append(sqlDw).toString();
	}

	/**
	 * 为代维用户登录进去 最后的 查询语句 包含 查询条件的
	 * 
	 * @param sqlDw
	 *            StringBuffer
	 * @param entity
	 *            BusinessContactLetter
	 * @return
	 */
	public String getSql4dw(StringBuffer sqlDw, BusinessContactLetter entity) {
		String beginDate = DateUtil.UtilDate2Str(entity.getBeginDate(),
				"yyyy-MM-dd");
		String endDate = DateUtil.UtilDate2Str(entity.getEndDate(),
				"yyyy-MM-dd");
		if (StringUtils.isNotBlank(beginDate)) {
			sqlDw.append(" and t.releaseTime >= to_date('" + beginDate.trim()
					+ " 00:00:00','yyyy/MM/dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(entity.getTitle())) {
			sqlDw.append(" and t.title like '%" + entity.getTitle() + "%'");
		}
		if (StringUtils.isNotBlank(entity.getDocumentNumber())) {
			sqlDw.append(" and t.documentNumber like '%"
					+ entity.getDocumentNumber() + "%'");
		}
		if (StringUtils.isNotBlank(endDate)) {
			sqlDw.append(" and t.releaseTime <= to_date('" + endDate.trim()
					+ " 23:59:59','yyyy/MM/dd hh24:mi:ss') ");
		}

		return sqlDw.toString();
	}

	/**
	 * 查询列表
	 * 
	 * @param entity
	 *            BusinessContactLetter
	 * @param page
	 *            Page
	 * @param user
	 *            UserInfo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page queryPage4Query(BusinessContactLetter entity, Page page,
			UserInfo user) {
		String sql = "";
		StringBuffer ydSqlStr = getYdSqlStr();
		StringBuffer dwSqlStr = getDwSqlStr(user);
		if (user.isMobile()) {// 如果是移动，则移动公司人 可以看到当前所在区域的 省可以看到当前所有底下市区的联系函
			sql = getSql4yd(ydSqlStr, dwSqlStr, user, entity);
		}
		if (user.isContractor()) {// 如果是代维人员 可以看到所有的派发到自己这的联系函
			sql = getSql4dw(dwSqlStr, entity);
		}

		sql += " order by dd desc";
		logger.info(sql);
		return getSQLPageAll(page, sql);
	}

	/**
	 * 从工作流中 取数据
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getALLAuditObject(String id) {
		String sql = "select * from  view_jbpm_usertask ut where ut.bzid=" + id;
		return getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 获取当前是否审核
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	public Integer checkInViewJBPM(String id) {
		String sql = "select count(*) from Business_Contact_Letter bcl,view_jbpm_usertask ut where bcl.id=ut.bzid and bcl.id="
				+ id;
		return getJdbcTemplate().queryForInt(sql);
	}

	/**
	 * 修改状态
	 * 
	 * @param id
	 *            String
	 * @param checkoff
	 *            String
	 */
	public void changeStatus(String id, String checkoff) {
		String sql = "update Business_Contact_Letter bcl set bcl.status='"
				+ checkoff + "' where bcl.id=" + id;
		getJdbcTemplate().execute(sql);
	}

}
