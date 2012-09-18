package com.cabletech.business.desktop.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseDao;

/**
 * 首页巡检统计Dao
 * 
 * @author zhaobi
 * @date 2012-8-15
 */
@Repository
@SuppressWarnings("rawtypes")
public class PatrolStatisticDao extends BaseDao {

	/**
	 * 今日巡检统计分析主sql
	 * 
	 * @param user
	 *            登录用户
	 * @param sb
	 *            sql
	 * @return
	 */
	private void mainStatisticSql(UserInfo user, StringBuffer sb) {
		if (user.isProvinceMobile()) {
			sb.append("(select r.regionid ID,r.regionname name from view_region r where r.REGIONID=any(");
			sb.append("select regionid from view_region start with  regionid='");
			sb.append(user.getRegionId());
			sb.append("' connect by prior regionid=parentid) and lv=3) m");
		} else if (user.isCityMobile()) {
			sb.append("(select o.id,o.name from view_org o where o.orgtype='2' and o.regionid='");
			sb.append(user.getRegionId());
			sb.append("') m");
		} else if (user.isProvinceContractor()) {
			sb.append("(select o.id,o.name from view_org o where o.orgtype='2' and o.id=any(");
			sb.append("select id from view_org start with id='");
			sb.append(user.getOrgId());
			sb.append("' connect by prior id=parentid) and o.REGIONlv=3) m");
		} else if (user.isCityContractor()) {
			sb.append("(select om.id,om.NAME from view_orgpatrolman om where om.OBJTYPE='PATROLMAN' and om.id=any(select id from view_orgpatrolman start with  id='");
			sb.append(user.getOrgId());
			sb.append("' connect by prior id=parentid)) m");
		}

	}

	/**
	 * 今日巡检统计分析子sql
	 * 
	 * @param user
	 *            用户
	 * @param sb
	 *            sql
	 */
	private void subPatrolStatisticSql(UserInfo user, StringBuffer sb) {
		List<Map<String, Object>> maplist = user.getBusinessTypes();
		if (null != maplist && maplist.size() > 0) {
			for (int i = 0; i < maplist.size(); i++) {
				if (user.isProvinceMobile()) {
					sb.append(" left join (select  count(e.id) tj"
							+ maplist.get(i).get("CODEVALUE").toString()
							+ ",p.REGIONID ");
				} else if (user.isCityMobile()) {
					sb.append(" left join (select count(e.id) tj"
							+ maplist.get(i).get("CODEVALUE").toString()
							+ ",p.ORGID ");
				} else if (user.isProvinceContractor()) {
					sb.append(" left join (select count(e.id) tj"
							+ maplist.get(i).get("CODEVALUE").toString()
							+ ",p.ORGID ");
				} else if (user.isCityContractor()) {
					sb.append(" left join (select count(e.id) tj"
							+ maplist.get(i).get("CODEVALUE").toString()
							+ ",p.id ");
				}
				sb.append(" from wplan_executeresult e join view_orgpatrolman p on e.patrol_group_id=p.ID and p.objtype='PATROLMAN'");
				sb.append(" where e.resource_type='"
						+ maplist.get(i).get("CODEVALUE").toString()
						+ "'  AND (e.end_time between to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd') and to_date(to_char(sysdate+1,'yyyy-MM-dd'),'yyyy-MM-dd'))");
				if (user.isProvinceMobile()) {
					sb.append(" group by p.REGIONID) s" + maplist.get(i).get("CODEVALUE").toString()
							+ " on m.id=substr(s" + maplist.get(i).get("CODEVALUE").toString() + ".REGIONID,0,4)||'00' ");
				} else if (user.isCityMobile()) {
					sb.append(" group by p.ORGID) s" + maplist.get(i).get("CODEVALUE").toString() + " on m.id=s"
							+ maplist.get(i).get("CODEVALUE").toString()
							+ ".orgid ");
				} else if (user.isProvinceContractor()) {
					sb.append(" group by p.ORGID) s" + maplist.get(i).get("CODEVALUE").toString() + " on m.id=s"
							+ maplist.get(i).get("CODEVALUE").toString()
							+ ".orgid ");
				} else if (user.isCityContractor()) {
					sb.append(" group by p.id) s" + maplist.get(i).get("CODEVALUE").toString() + " on m.id=s"
							+ maplist.get(i).get("CODEVALUE").toString()
							+ ".id");
				}
			}
		}
	}

	/**
	 * 今日故障sql
	 * 
	 * @param user
	 *            当前用户
	 * @param sb
	 *            sql
	 */
	private void subWtroubleSql(UserInfo user, StringBuffer sb) {
		List<Map<String, Object>> maplist = user.getBusinessTypes();
		if (null != maplist && maplist.size() > 0) {
			for (int i = 0; i < maplist.size(); i++) {
				if (user.isProvinceMobile()) {
					sb.append(" left join (select count(distinct task_id) tj"
							+ maplist.get(i).get("CODEVALUE").toString()
							+ ",o.REGIONID ");
				} else {
					sb.append(" left join (select count(distinct task_id) tj"
							+ maplist.get(i).get("CODEVALUE").toString()
							+ ",o.id");
				}
				sb.append(" from wtrouble_reply r join wtrouble_sendtask st on st.id=r.task_id join wtrouble_alarm a on st.alarm_id=a.id ");
				sb.append(" join view_org o on r.maintenance_id=o.ID where a.business_type='"
						+ maplist.get(i).get("CODEVALUE").toString()
						+ "' and (r.reply_time between to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd') and to_date(to_char(sysdate+1,'yyyy-MM-dd'),'yyyy-MM-dd'))");
				if (user.isProvinceMobile()) {
					sb.append(" group by o.REGIONID) s"
							+ maplist.get(i).get("CODEVALUE").toString()
							+ " on m.id=substr(s"
							+ maplist.get(i).get("CODEVALUE").toString()
							+ ".REGIONID,0,4)||'00' ");
				} else {
					sb.append(" group by o.ID) s"
							+ maplist.get(i).get("CODEVALUE").toString()
							+ " on m.id=s"
							+ maplist.get(i).get("CODEVALUE").toString()
							+ ".id ");
				}
			}
		}
	}

	/**
	 * 获取今日巡检分析
	 * 
	 * @param user
	 *            当前用户
	 * @return
	 */
	public List<Map<String, Object>> getPatrolStatistic(UserInfo user) {
		StringBuffer sb = new StringBuffer(" select m.* ");
		List<Map<String, Object>> maplist = user.getBusinessTypes();
		if (null != maplist && maplist.size() > 0) {
			for (int i = 0; i < maplist.size(); i++) {
				sb.append(",nvl(s" + maplist.get(i).get("CODEVALUE").toString()
						+ ".tj" + maplist.get(i).get("CODEVALUE").toString()
						+ ",0) ");
				sb.append(" tj" + maplist.get(i).get("CODEVALUE").toString()
						+ "");
			}
		}
		sb.append(" from ");
		mainStatisticSql(user, sb);
		subPatrolStatisticSql(user, sb);
		String sql = sb.toString();
		logger.debug("今日巡检统计sql:" + sql);
		return this.getJdbcTemplate().queryForList(sql);

	}

	/**
	 * 获取今日故障分析
	 * 
	 * @param user
	 *            当前用户
	 * @return
	 */
	public List<Map<String, Object>> getWtrouble(UserInfo user) {
		StringBuffer sb = new StringBuffer(" select m.*  ");
		List<Map<String, Object>> maplist = user.getBusinessTypes();
		if (null != maplist && maplist.size() > 0) {
			for (int i = 0; i < maplist.size(); i++) {
				sb.append(",nvl(s" + maplist.get(i).get("CODEVALUE").toString()
						+ ".tj" + maplist.get(i).get("CODEVALUE").toString()
						+ ",0) ");
				sb.append(" tj" + maplist.get(i).get("CODEVALUE").toString()
						+ "");
			}
		}
		sb.append(" from ");
		mainStatisticSql(user, sb);
		subWtroubleSql(user, sb);
		String sql = sb.toString();
		logger.debug("今日故障sql:" + sql);
		return this.getJdbcTemplate().queryForList(sql);
	}
}
