package com.cabletech.business.notice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oracle.sql.CLOB;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.notice.model.Notice;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.DateUtil;
import com.cabletech.common.util.Page;

/**
 * 公告日志Dao
 * 
 * @author wangt
 * 
 */
@Repository
public class NoticeDao extends BaseDao<Notice, String> {
	private static Logger logger = Logger.getLogger("NoticeDao");

	/**
	 * 保存
	 * 
	 * @param notice
	 *            实体
	 * @return
	 */
	public boolean saveNotice(Notice notice) {
		save(notice);
		return true;
	}

	/**
	 * 删除
	 * 
	 * @param notice
	 *            Notice实体
	 * @return
	 */
	public boolean removeNotice(Notice notice) {
		delete(notice);
		return true;
	}

	/**
	 * 获取单个Notice实体
	 * 
	 * @param id
	 *            系统编号
	 * @return
	 */
	public Notice findById(String id) {
		Notice notice = this.get(id);
		notice.setContentString(notice.getContent());
		return notice;
	}

	/**
	 * 查询公告信息
	 * 
	 * @param rootregionid
	 *            String
	 * @param regionid
	 *            String
	 * @param num
	 *            String
	 * @param type
	 *            String
	 * @return List<Notice>
	 */
	public List<Notice> queryNotices(String rootregionid, String regionid,
			String num, String type) {
		String sql = "select distinct ID,TITLE,ISISSUE,grade,type,issuedate,meet_time ";
		sql += " from (select * from NOTICE_CLOB where isissue='y' and type=? and is_canceled='0' and regionid in (?,?) order by issuedate desc) notice ";
		sql += " where rownum <= ?";
		logger.info("SQL:" + sql);
		return this.getJdbcTemplate().query(sql,
				new Object[] { type, rootregionid, regionid, num },
				new NoticeNoClobMapper());
	}

	/**
	 * 根据查询条件获取最新的信息列表
	 * 
	 * @param condition
	 *            condition
	 * @return
	 */
	public List<Map<String, Object>> getLatestNoticeList(String condition) {
		String sql = "select distinct id,title,type,FN_GETNAMEBYCODE(type,'INFORMATION') typename,issuedate,to_char(meet_time,'yyyy-MM-dd') as month ";
		sql += " from (select n.*,row_number() over(partition by type order by issuedate desc) as rowindex from NOTICE_CLOB n)a ";
		sql += "  left join notice_sendee s on a.id=s.notice_id where isissue='y' and is_canceled='0' ";
		sql += condition;
		sql += " order by issuedate desc ";
		logger.info("SQL:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<Map<String, Object>>();
		}
		return list;
	}

	/**
	 * 获取公告日志列表list
	 * 
	 * @param notice
	 *            Notice
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String,Object>> queryForList(Notice notice) {
		// TODO Auto-generated method stub
		String sql = getSql(notice);
		return getSQLALL(sql);
	}

	/**
	 * 分页查询列表
	 * 
	 * @param notice
	 *            Notice
	 * @param page
	 *            Page
	 * @return Page
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page queryForPage(Notice notice, Page page) {
		// TODO Auto-generated method stub
		String sql = getSql(notice);
		return getSQLPageAll(page, sql);
	}

	/**
	 * 获取查询sql字符串
	 * 
	 * @param notice
	 *            Notice
	 * @return String
	 */
	private String getSql(Notice notice) {
		String beginDate = DateUtil.UtilDate2Str(notice.getBeginDate(),
				"yyyy-MM-dd");
		String endDate = DateUtil.UtilDate2Str(notice.getEndDate(),
				"yyyy-MM-dd");
		String meetTime = DateUtil.UtilDate2Str(notice.getMeetTime(),
				"yyyy-MM-dd");
		String sql = "select  nc.id,title, content,grade,type,FN_GETNAMEBYCODE(type,'INFORMATION') typename,decode(isissue,'y','已发布','n','未发布') isissue ,"
				+ "meet_time,meet_end_time,meet_address,s.IS_READ as newflag, "
				+ " nvl(vu.username,nc.issueperson) as issueperson,issuedate,is_canceled from notice_clob nc "
				+ " left join view_userinfo vu on nc.issueperson=vu.sid ";
		if (StringUtils.isNotBlank(notice.getAcceptUserIds())) {
			sql += " left join notice_sendee s on nc.id=s.notice_id	";
		}
		sql += "where 1=1 ";
		if (StringUtils.isNotBlank(notice.getAcceptUserIds())) {
			StringBuffer buf = new StringBuffer("");
			buf.append(" and ( ");
			buf.append("   (type='");
			buf.append(Notice.NEWS_TYPE);
			buf.append("'  ) ");
			buf.append(" or (type='");
			buf.append(Notice.BULLETIN_TYPE);
			buf.append("' and s.person_id='");
			buf.append(notice.getAcceptUserIds());
			buf.append("') ");
			buf.append(" ) ");
			sql += buf.toString();
		}
		if (StringUtils.isNotBlank(notice.getRegionid())) {
			sql += " and exists(select regionid from view_region r where r.regionid=nc.regionid start with r.regionid='"
					+ notice.getRegionid()
					+ "' connect by prior r.parentid=r.regionid) ";
		}
		if (StringUtils.isNotBlank(beginDate)) {
			sql += " and issuedate >= to_date('" + beginDate.trim()
					+ " 00:00:00','yyyy/MM/dd hh24:mi:ss') ";
		}
		if (StringUtils.isNotBlank(notice.getTitle())) {
			sql += " and title like '%" + notice.getTitle() + "%'";
		}
		if (StringUtils.isNotBlank(endDate)) {
			sql += " and issuedate <= to_date('" + endDate.trim()
					+ " 23:59:59','yyyy/MM/dd hh24:mi:ss') ";
		}
		if (StringUtils.isNotBlank(notice.getIsissue())) {
			sql += " and isissue='" + notice.getIsissue() + "'";
		}
		if (StringUtils.isNotBlank(notice.getType())) {
			sql += " and type='" + notice.getType() + "'";
		}
		if (StringUtils.isNotBlank(notice.getGrade())) {
			sql += " and grade='" + notice.getGrade() + "'";
		}
		if (StringUtils.isNotBlank(meetTime)) {
			sql += " and meet_time>=to_date('" + meetTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
			sql += " and meet_time<=to_date('" + meetTime
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss') ";
			sql += " and meet_end_time>=to_date('" + meetTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
		}
		sql += " order by issuedate desc";
		logger.info("sql :" + sql);
		return sql;
	}

	/**
	 * 公告
	 * 
	 * @author wangt
	 * 
	 */
	protected class NoticeMapper implements RowMapper<Notice> {
		Notice notice = null;
		java.sql.Clob clob = null;

		@Override
		public Notice mapRow(ResultSet rst, int rowNum) throws SQLException {
			notice = new Notice();
			notice.setId(rst.getString("id"));
			notice.setMeetPerson(rst.getString("meet_person"));
			notice.setMeetAddress(rst.getString("meet_address"));
			notice.setMeetTime(rst.getTimestamp("meet_time"));
			notice.setMeetEndTime(rst.getTimestamp("meet_end_time"));
			notice.setTitle(rst.getString("title"));
			notice.setType(rst.getString("type"));
			notice.setTypename(rst.getString("typename"));
			notice.setIsissue(rst.getString("isissue"));
			notice.setIssueperson(rst.getString("issueperson"));
			notice.setIssuedate(rst.getTimestamp("issuedate"));
			notice.setIsCanceled(rst.getString("is_canceled"));
			// clob = rst.getClob("content");
			// notice.setContentString(ClobToString(clob));
			return notice;
		}
	}

	/**
	 * 公告
	 * 
	 * @author wangt
	 * 
	 */
	protected class NoticeNoClobMapper implements RowMapper<Notice> {
		Notice notice = null;
		CLOB clob = null;

		@Override
		public Notice mapRow(ResultSet rst, int rowNum) throws SQLException {
			notice = new Notice();
			notice.setId(rst.getString("id"));
			notice.setMeetTime(rst.getTimestamp("meet_time"));
			notice.setTitle(rst.getString("title"));
			notice.setIsissue(rst.getString("isissue"));
			notice.setGrade(rst.getString("grade"));
			notice.setType(rst.getString("type"));
			notice.setTypename(rst.getString("typename"));
			notice.setIssuedate(rst.getTimestamp("issuedate"));
			return notice;
		}
	}

}
