package com.cabletech.business.ah.rating.dao;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 移动审核
 * 
 * @author wangt
 * 
 */
@SuppressWarnings("rawtypes")
@Repository
public class MobileExamDao extends BaseDao {

	/**
	 * 审核考核确认列表方法 根据flag来判断。
	 * 
	 * @param page
	 *            分页
	 * @param userInfo
	 *            用户实体
	 * @param flag
	 *            标记
	 * @return Page
	 */
	@SuppressWarnings("unchecked")
	public Page getUnExamList(Page page, UserInfo userInfo, String flag) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select mr.id,u.username,mr.table_id,u.sid,u.employee_num,r.regionname,FN_GETNAMEBYCODE(rf.BUSINESS_TYPE,'businesstype')")
				.append("  BUSINESS_TYPE,mr.YEAR_MONTH,u.orgname,mr.EXAM_ASSE_NUM,mr.SELF_ASSE_NUM,mr.IS_EXAM,")
				.append(" FN_GETNAMEBYCODE(u.jobinfo,'job_type') jobinfo,")
				.append(" (select max(flow_num) from AH_PERSONFLOW pf where pf.person_id=u.sid  ) maxflownum from AH_MONTHRESULT mr join view_userinfo ")
				.append(" u on mr.PERSON_ID=u.sid join view_region r on u.regionid=r.regionid   join AH_RATINGFORM rf")
				.append(" on rf.id=mr.TABLE_ID ");
		if (flag.equals("1")) {
			sql.append(" where exists(select 1 from AH_PERSONFLOW p where mr.FLOW_STATE=p.FLOW_NUM and p.flow_num='1'"
					+ " and mr.PERSON_ID=p.PERSON_ID and p.PROCESSER='"
					+ userInfo.getPersonId() + "')");
		} else if (flag.equals("2")) {
			sql.append(" where exists(select 1 from AH_PERSONFLOW p where mr.FLOW_STATE=p.FLOW_NUM and p.flow_num>1"
					+ " and mr.PERSON_ID=p.PERSON_ID and p.PROCESSER='"
					+ userInfo.getPersonId() + "')");
		} else if (flag.equals("3")) {
			sql.append("where mr.flow_state='-1' and u.orgid='"
					+ userInfo.getOrgId() + "'");
		}
		return super.getSQLPageAll(page, sql.toString());
	}

	/**
	 * 拼sql语句用户查询统计
	 * 
	 * @param parameters
	 *            条件
	 * @return String
	 */
	public String OrgSqlByparamters(Map<String, Object> parameters) {
		StringBuffer sql = new StringBuffer();
		String orgid = (String) parameters.get("orgId");
		String regionid = (String) parameters.get("regionId");
		if (StringUtils.isNotBlank(orgid)) {
			sql.append("and vu.orgid in (select id from view_org start with id = '");
			sql.append(orgid);
			sql.append("' connect by prior id=parentid) ");
		} else {
			if (StringUtils.isNotBlank(regionid)) {
				sql.append("and vu.regionid in(select regionid from view_region start with regionid = '");
				sql.append(regionid);
				sql.append("' connect by prior regionid=parentid) ");
			}
		}
		if (parameters.get("businessType") != null
				&& !"".equals(parameters.get("businessType"))) { // 专业
			sql.append("  and pf.BUSINESS_TYPE = '"
					+ parameters.get("businessType") + "' ");
		}

		if (parameters.get("status") != null
				&& !"".equals(parameters.get("status").toString())) {
			if ((parameters.get("status").toString()).equals("0")
					|| (parameters.get("status").toString()).endsWith("1")) {
				sql.append(" and mr.is_exam ='");
				sql.append(parameters.get("status").toString());
				sql.append("' ");
			} else {
				sql.append(" and mr.flow_state ='");
				sql.append(parameters.get("status").toString());
				sql.append("' ");
			}
		}

		if (parameters.get("postOffice") != null
				&& !"".equals(parameters.get("postOffice"))) { // 职位
			sql.append("  and  mr.position = '" + parameters.get("postOffice")
					+ "' ");
		}
		if (parameters.get("yearMonth") != null
				&& !"".equals(parameters.get("yearMonth"))) {// 考核期间
			sql.append("  and to_char(mr.YEAR_MONTH,'yyyy-MM') = '"
					+ parameters.get("yearMonth") + "' ");
		}
		return sql.toString();
	}

	/**
	 * 查询统计列表方法
	 * 
	 * @param page
	 *            Page<Map<String, Object>>
	 * @param parameters
	 *            Map<String, Object>
	 * @return Page
	 */
	@SuppressWarnings("unchecked")
	public Page<Map<String, Object>> getQueryAnalysisList(
			Page<Map<String, Object>> page, Map<String, Object> parameters) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select mr.id,mr.self_asse_num,vu.sid,mr.table_id,vu.username as NAME,  ")
				.append("FN_GETNAMEBYCODE(pf.BUSINESS_TYPE, 'businesstype')   as business,  ")
				.append(" vu.orgName as CONTRACTOR,  ")
				.append(" r.regionname as region,  ")
				.append(" FN_GETNAMEBYCODE(vu.jobinfo, 'job_type') as position, ")
				.append(" mr.exam_asse_num as EXAM_ASSE_NUM ,  to_char(mr.year_month,'yyyy-MM') as month, ")
				.append(" (select max(flow_num) from AH_PERSONFLOW pf where pf.person_id=vu.sid  ) maxflownum ")
				.append("	from AH_MONTHRESULT mr ")
				.append(" join view_userinfo vu ")
				.append(" on mr.person_id = vu.sid ")
				.append(" join view_region r ")
				.append(" on vu.regionid=r.regionid ")
				.append("	join AH_PERSONRATINGFORM prf ")
				.append("	on vu.sid = prf.person_id ")
				.append(" join AH_RATINGFORM pf ")
				.append(" on prf.table_id=pf.id ").append("where 1=1 ")
				.append(OrgSqlByparamters(parameters));
		this.logger.info(sql);
		return super.getSQLPageAll(page, sql.toString());
	}

	/**
	 * 已考核的人数
	 * 
	 * @param parameters
	 *            Map<String, Object>
	 * @return Integer
	 */
	public String getHasedCheckUserCount(Map<String, Object> parameters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct mr.person_id ")
				.append(" from AH_MONTHRESULT mr ")
				.append(" join view_userinfo vu ")
				.append(" on mr.person_id = vu.sid ")
				.append(" join view_region r ")
				.append(" on vu.regionid=r.regionid ")
				.append("	join AH_PERSONRATINGFORM prf ")
				.append("	on vu.sid = prf.person_id ")
				.append(" join AH_RATINGFORM pf ")
				.append(" on prf.table_id=pf.id ").append("where 1=1 ")
				.append(" and mr.is_exam!='1' ")
				.append(OrgSqlByparamters(parameters));
		return this.countNum(sql.toString());
	}

	/**
	 * 查询总列数
	 * 
	 * @param sql
	 *            sql语句
	 * @return
	 */
	public String countNum(String sql) {
		long count = this.jdbcTemplate.queryForList(sql.toString()).size();
		return String.valueOf(count);
	}

	/**
	 * 未考核的人数
	 * 
	 * @param parameters
	 *            Map<String, Object>
	 * @return String
	 */
	public String getNoneCheckUserCount(Map<String, Object> parameters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct mr.person_id ")
				.append(" from AH_MONTHRESULT mr ")
				.append(" join view_userinfo vu ")
				.append(" on mr.person_id = vu.sid ")
				.append(" join view_region r ")
				.append(" on vu.regionid=r.regionid ")
				.append("	join AH_PERSONRATINGFORM prf ")
				.append("	on vu.sid = prf.person_id ")
				.append(" join AH_RATINGFORM pf ")
				.append(" on prf.table_id=pf.id ").append("where 1=1 ")
				.append(" and mr.is_exam='1' ")
				.append(OrgSqlByparamters(parameters));
		return this.countNum(sql.toString());
	}

	/**
	 * 代维确认的人数
	 * 
	 * @param parameters
	 *            Map<String, Object>
	 * @return Integer
	 */
	public String getDaiweiOKCount(Map<String, Object> parameters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct mr.person_id ")
				.append(" from AH_MONTHRESULT mr ")
				.append(" join view_userinfo vu ")
				.append(" on mr.person_id = vu.sid ")
				.append(" join view_region r ")
				.append(" on vu.regionid=r.regionid ")
				.append("	join AH_PERSONRATINGFORM prf ")
				.append("	on vu.sid = prf.person_id ")
				.append(" join AH_RATINGFORM pf ")
				.append(" on prf.table_id=pf.id ").append("where 1=1 ")
				.append(" and mr.flow_state='-2' ")
				.append(OrgSqlByparamters(parameters));
		return this.countNum(sql.toString());
	}
}
