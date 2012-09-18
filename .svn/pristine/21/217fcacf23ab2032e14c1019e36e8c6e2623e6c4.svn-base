package com.cabletech.business.desktop.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseDao;

/**
 * 二级 首页 -- 统计DAO
 * 
 * @author wj
 */
@Repository
public class BaseWorkDao extends BaseDao {

	/**
	 * 维护持有终端数 -- 按区域分组
	 * 
	 * @param regionId
	 *            区域
	 * @return List
	 */
	public List<Map<String, Object>> getContractorTerminalListByRegion(
			String regionId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select r.REGIONID as id,r.REGIONNAME as name,sum(rnum) as TERMINALNUM ");
		sqlBuffer
				.append(" from (select * from view_region  r where r.REGIONID=any(select regionid ");
		sqlBuffer.append(" from view_region   start with  regionid='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("' connect by prior regionid=parentid) and lv=3) r ");
		sqlBuffer
				.append(" left join  (select count(rs.terminalid) rnum,rs.REGIONID ");
		sqlBuffer
				.append(" from  base_terminalinfo rs  group by rs.regionid) n on r.REGIONID=substr (n.REGIONID,0,4)||'00' ");
		sqlBuffer.append(" group by  r.REGIONID,r.REGIONNAME ");
		sqlBuffer.append(" order by r.REGIONID ");
		String sql = sqlBuffer.toString();
		logger.info("维护持有终端数 -- 按区域分组 sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 维护持有终端数 -- 按组织分组
	 * 
	 * @param regionId
	 *            区域
	 * @return List
	 */
	public List<Map<String, Object>> getContractorTerminalListByOrg(
			String regionId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select  o.ID,o.name,nvl(count(ods.terminalid),0) as TERMINALNUM ");
		sqlBuffer.append(" from  view_org o ");
		sqlBuffer
				.append(" left join base_terminalinfo ods  on ods.contractorid  = o.id ");
		sqlBuffer.append(" where o.REGIONID=any ");
		sqlBuffer.append(" (select regionid from region start with regionid='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("' connect by prior regionid=parentregionid) ");
		sqlBuffer.append(" and o.ORGTYPE = '2' ");
		sqlBuffer.append(" group by o.ID,o.NAME ");
		sqlBuffer.append(" order by o.ID ");
		String sql = sqlBuffer.toString();
		logger.info("维护持有终端数 -- 按组织分组 sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 维护持有终端数 -- 按巡检组分组
	 * 
	 * @param regionId
	 *            区域
	 * @param orgId
	 *            组织
	 * @return List
	 * 
	 */
	public List<Map<String, Object>> getContractorTerminalListByPatrol(
			String regionId, String orgId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select  o.ID,o.name,nvl(count(ods.terminalid),0) as TERMINALNUM ");
		sqlBuffer
				.append(" from  view_patrolgroup o  left join base_terminalinfo ods  on ods.ownerid  = o.id ");
		sqlBuffer
				.append(" where o.REGIONID=any  (select regionid from region start with regionid='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("' connect by prior regionid=parentregionid) ");
		sqlBuffer.append(" and o.ORGID = '" + orgId + "' ");
		sqlBuffer.append(" group by o.ID,o.NAME ");
		sqlBuffer.append(" order by o.ID ");
		String sql = sqlBuffer.toString();
		logger.info("维护持有终端数 -- 按巡检组分组 sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	// ---------------------------------------------------------------资质，证书到期提醒

	/**
	 * 到期资质 证书
	 * 
	 * @param regionId
	 *            区域
	 * @param orgId
	 *            组织
	 * @return List
	 * 
	 */
	public List<Map<String, Object>> getValidperiodedCertificatesList(
			String regionId, String orgId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select t.id,o.NAME AS orgname,t.certificatename,to_char(t.validperiod,'yyyy-mm-dd') as validperiod ");
		sqlBuffer.append(" from base_certificate t ");
		sqlBuffer
				.append(" left join view_orgdeptstaff o on t.objectid = o.ID ");
		sqlBuffer.append(" where t.validperiod <= sysdate ");
		// sqlBuffer.append(" where 1=1 ");
		sqlBuffer
				.append(" and exists( SELECT REGIONID FROM VIEW_REGION vr WHERE ");
		sqlBuffer.append(" vr.REGIONID=o.REGIONID START WITH ");
		sqlBuffer.append(" vr.REGIONID='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("' CONNECT BY PRIOR ");
		sqlBuffer.append(" vr.REGIONID=vr.PARENTID) ");
		if (StringUtils.isNotBlank(orgId)) {
			sqlBuffer.append(" and t.objectid = '" + orgId + "' ");
		}
		sqlBuffer.append(" order by t.objectid,t.validperiod ");
		String sql = sqlBuffer.toString();
		logger.info("到期资质 证书sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	// --------------------------------------------------- 维护的资源

	/**
	 * 资源数量 -- 按区域分组
	 * 
	 * @param regionId
	 *            区域
	 * @param pointtype
	 *            String
	 * @return List
	 */
	public List<Map<String, Object>> getResCountListByRegion(String regionId,
			String pointtype) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select r.REGIONNAME as name,nvl(sum(rnum),0) as num ");
		sqlBuffer
				.append(" from (select * from view_region  r where r.REGIONID=any(select regionid from view_region start with  regionid='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("' connect by prior regionid=parentid) and lv=3) r ");
		sqlBuffer
				.append(" left join  (select count(rs.Pointid) rnum,rs.REGIONID ");
		sqlBuffer.append(" from  pointinfo rs where rs.pointtype in (");
		sqlBuffer.append(StringToSqlCondition(pointtype));
		sqlBuffer
				.append(") group by rs.regionid) n on r.REGIONID=substr (n.REGIONID,0,4)||'00' ");
		sqlBuffer.append(" group by  r.REGIONID,r.REGIONNAME ");
		sqlBuffer.append(" order by r.REGIONID ");
		String sql = sqlBuffer.toString();
		logger.info("资源数量 -- 按区域分组 sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 线路公里数 -- 按区域分组
	 * 
	 * @param regionId
	 *            区域
	 * @return List
	 */
	public List<Map<String, Object>> getLineCountListByRegion(String regionId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select r.REGIONNAME as name,to_char(round(nvl(sum(rnum)/1000,0),2),'FM9999999999999990.00') as num ");
		sqlBuffer
				.append(" from (select * from view_region  r where r.REGIONID=any(select regionid from view_region start with  regionid='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("' connect by prior regionid=parentid) and lv=3) r ");
		sqlBuffer
				.append(" left join  (select sum(t.shape.len) rnum,t.regionid from sublineinfo t group by t.regionid) n on r.REGIONID=substr(n.REGIONID,0,4)||'00' ");
		sqlBuffer.append(" group by  r.REGIONID,r.REGIONNAME ");
		sqlBuffer.append(" order by r.REGIONID ");
		String sql = sqlBuffer.toString();
		logger.info("线路公里数 -- 按区域分组 sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 资源数量 -- 按组织分组
	 * 
	 * @param regionId
	 *            区域
	 * @param pointtype
	 *            String
	 * @return List
	 */
	public List<Map<String, Object>> getResCountListByOrg(String regionId,
			String pointtype) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select o.NAME,nvl(count(rm.rs_id),0) num ");
		sqlBuffer.append(" from view_org o ");
		sqlBuffer
				.append(" left join rees_maintenance rm on rm.maintenance_id = o.id and rm.rs_type in (");
		sqlBuffer.append(StringToSqlCondition(pointtype));
		sqlBuffer.append(") where o.regionid=any ");
		sqlBuffer.append(" (select regionid from region start with regionid='"
				+ regionId + "' connect by prior regionid=parentregionid) ");
		sqlBuffer.append(" and o.ORGTYPE = '2' ");
		sqlBuffer.append(" group by o.id,o.name ");
		sqlBuffer.append(" order by o.id ");
		String sql = sqlBuffer.toString();
		logger.info("资源数量 -- 按组织分组 sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 线路公里数 -- 按组织分组
	 * 
	 * @param regionId
	 *            区域
	 * @return List
	 */
	public List<Map<String, Object>> getLineCountListByOrg(String regionId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select o.name,to_char(round(nvl(sum(line.shape.len)/1000,0),2),'FM9999999999999990.00') as num ");
		sqlBuffer.append(" from view_org o ");
		sqlBuffer
				.append(" left join sublineinfo line   on line.contractorid  = o.id ");
		sqlBuffer.append(" where o.REGIONID=any ");
		sqlBuffer.append(" (select regionid from region start with regionid='"
				+ regionId + "' connect by prior regionid=parentregionid) ");
		sqlBuffer.append(" and o.ORGTYPE = '2' ");
		sqlBuffer.append(" group by o.ID,o.NAME ");
		sqlBuffer.append(" order by o.id ");
		String sql = sqlBuffer.toString();
		logger.info(" 线路公里数 -- 按组织分组 sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 资源数量 -- 按巡检组分组
	 * 
	 * @param regionId
	 *            区域
	 * @param orgId
	 *            String 组织编号
	 * @param pointtype
	 *            String 点类型
	 * @return List
	 */
	public List<Map<String, Object>> getResCountListByPatrol(String regionId,
			String orgId, String pointtype) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select o.NAME,nvl(count(rm.rs_id),0) num ");
		sqlBuffer.append(" from view_patrolgroup o ");
		sqlBuffer
				.append(" left join res_maintenance rm on rm.patrol_group_id = o.id and rm.rs_type in (");
		sqlBuffer.append(StringToSqlCondition(pointtype));
		sqlBuffer.append(") where o.regionid=any ");
		sqlBuffer.append(" (select regionid from region start with regionid='"
				+ regionId + "' connect by prior regionid=parentregionid)  ");
		sqlBuffer.append(" and o.PARENTID = '" + orgId + "' ");
		sqlBuffer.append(" group by o.id,o.name ");
		sqlBuffer.append(" order by o.id  ");
		String sql = sqlBuffer.toString();
		logger.info("资源数量 -- 按巡检组分组 sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 线路公里数 -- 按巡检组分组
	 * 
	 * @param regionId
	 *            区域
	 * @param orgId
	 *            String 组织编号
	 * @return List
	 */
	public List<Map<String, Object>> getLineCountListByPatrol(String regionId,
			String orgId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select o.NAME,to_char(round(nvl(sum(rm.shape.len)/1000,0),2),'FM9999999999999990.00') as num ");
		sqlBuffer.append(" from view_patrolgroup o ");
		sqlBuffer.append(" left join sublineinfo rm on rm.patrolid = o.id ");
		sqlBuffer.append(" where o.regionid=any ");
		sqlBuffer.append(" (select regionid from region start with regionid='"
				+ regionId + "' connect by prior regionid=parentregionid) ");
		sqlBuffer.append(" and o.PARENTID = '" + orgId + "' ");
		sqlBuffer.append(" group by o.id,o.name ");
		sqlBuffer.append(" order by o.id ");
		String sql = sqlBuffer.toString();
		logger.info(" 线路公里数 -- 按巡检组分组 sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 将字符串转化为sql语句的条件
	 * 
	 * @param str
	 *            String
	 * @return
	 */
	private String StringToSqlCondition(String str) {
		if (StringUtils.isBlank(str))
			return "''";
		StringBuffer sqlBuffer = new StringBuffer("");
		String[] conds = str.split(",");
		for (int i = 0; i < conds.length; i++) {
			if (i > 0) {
				sqlBuffer.append(",");
			}
			sqlBuffer.append("'" + conds[i] + "'");
		}
		return sqlBuffer.toString();
	}

	/**
	 * 获取截止到当前月份的代维单位人员总数（按区域分组统计）
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllPersonNumberGroupByRegion(
			UserInfo userInfo, String yearMonth) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select r.regionid as gid,r.regionname as gname, ");
		sqlBuffer.append(" to_char(count(*)) as all_num ");
		sqlBuffer.append(" from ( ");
		sqlBuffer.append(" select * from view_region  ");
		sqlBuffer.append(" where lv=3  ");
		sqlBuffer.append(" start with regionid='");
		sqlBuffer.append(userInfo.getRegionId());
		sqlBuffer.append("'  ");
		sqlBuffer.append(" connect by prior regionid=parentid ");
		sqlBuffer.append(" ) r join (");
		getAllPersonListSql(userInfo, yearMonth, sqlBuffer);
		sqlBuffer.append(" ) result_ on r.regionid=result_.regionid ");
		sqlBuffer.append(" group by r.regionid,r.regionname ");
		sqlBuffer.append(" order by r.regionid ");
		logger.info("获取截止到当前月份的代维单位人员总数（按区域分组统计）SQL:" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取截止到当前月份的代维单位人员总数（按组织分组统计）
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllPersonNumberGroupByOrg(
			UserInfo userInfo, String yearMonth) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select v.id as gid,v.name as gname, ");
		sqlBuffer.append(" to_char(count(*)) as all_num ");
		sqlBuffer.append(" from view_org v join ( ");
		getAllPersonListSql(userInfo, yearMonth, sqlBuffer);
		sqlBuffer.append(" ) result_ on v.id=result_.id ");
		sqlBuffer.append(" group by v.id,v.name ");
		sqlBuffer.append(" order by v.id ");
		logger.info("获取截止到当前月份的代维单位人员总数（按组织分组统计）SQL:" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取当前月份的离职代维单位人员总数（按区域分组统计）
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLeavePersonNumberByRegion(
			UserInfo userInfo, String yearMonth) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select r.regionid as gid,r.regionname as gname, ");
		sqlBuffer.append(" to_char(count(*)) as leave_num ");
		sqlBuffer.append(" from ( ");
		sqlBuffer.append(" select * from view_region  ");
		sqlBuffer.append(" where lv=3  ");
		sqlBuffer.append(" start with regionid='");
		sqlBuffer.append(userInfo.getRegionId());
		sqlBuffer.append("'  ");
		sqlBuffer.append(" connect by prior regionid=parentid ");
		sqlBuffer.append(" ) r join (");
		getLeavePersonListSql(userInfo, yearMonth, sqlBuffer);
		sqlBuffer.append(" ) result_ on r.regionid=result_.regionid ");
		sqlBuffer.append(" group by r.regionid,r.regionname ");
		sqlBuffer.append(" order by r.regionid ");
		logger.info("获取当前月份的离职代维单位人员总数（按区域分组统计）SQL:" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取当前月份的离职代维单位人员总数（按组织分组统计）
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLeavePersonNumberByOrg(
			UserInfo userInfo, String yearMonth) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select v.id as gid,v.name as gname, ");
		sqlBuffer.append(" to_char(count(*)) as leave_num ");
		sqlBuffer.append(" from view_org v join ( ");
		getLeavePersonListSql(userInfo, yearMonth, sqlBuffer);
		sqlBuffer.append(" ) result_ on v.id=result_.id ");
		sqlBuffer.append(" group by v.id,v.name ");
		sqlBuffer.append(" order by v.id ");
		logger.info("获取当前月份的离职代维单位人员总数（按组织分组统计）SQL:" + sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取所有当月在职人员列表的sql
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getAllPersonListSql(UserInfo userInfo, String yearMonth,
			StringBuffer sqlBuffer) {
		sqlBuffer.append(" select vo.regionid,vo.id,op.id as pid ");
		sqlBuffer.append(" from base_person op ");
		sqlBuffer.append(" join view_org vo on op.contractorid=vo.id ");
		sqlBuffer.append(" where 1=1 ");
		sqlBuffer.append(" and vo.orgtype='2' ");
		sqlBuffer.append(" and ( ");
		sqlBuffer.append(" op.leave_time is null ");
		sqlBuffer.append(" or ");
		sqlBuffer.append(" op.leave_time>=add_months(to_date('");
		sqlBuffer.append(yearMonth);
		sqlBuffer.append("-1','yyyy-mm-dd'),1) ");
		sqlBuffer.append(" ) ");
		sqlBuffer.append(" and ( ");
		sqlBuffer.append(" op.enter_time is null ");
		sqlBuffer.append(" or ");
		sqlBuffer.append(" op.enter_time<add_months(to_date('");
		sqlBuffer.append(yearMonth);
		sqlBuffer.append("-1','yyyy-mm-dd'),1) ");
		sqlBuffer.append(" ) ");
		sqlBuffer.append(getUserCondition(userInfo));
	}

	/**
	 * 获取所有当月离职人员列表的sql
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param yearMonth
	 *            String
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getLeavePersonListSql(UserInfo userInfo, String yearMonth,
			StringBuffer sqlBuffer) {
		sqlBuffer.append(" select vo.regionid,vo.id,op.id as pid ");
		sqlBuffer.append(" from base_person op ");
		sqlBuffer.append(" join view_org vo on op.contractorid=vo.id ");
		sqlBuffer.append(" where 1=1 ");
		sqlBuffer.append(" and vo.orgtype='2' ");
		sqlBuffer.append(" and op.leave_time>=to_date('");
		sqlBuffer.append(yearMonth);
		sqlBuffer.append("-1','yyyy-mm-dd') ");
		sqlBuffer.append(" and op.leave_time<add_months(to_date('");
		sqlBuffer.append(yearMonth);
		sqlBuffer.append("-1','yyyy-mm-dd'),1) ");
		sqlBuffer.append(getUserCondition(userInfo));
	}

	/**
	 * 获取用户查询条件
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @return
	 */
	private String getUserCondition(UserInfo userInfo) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		if (userInfo.isMobile()) {
			sqlBuffer.append(" and vo.regionid=any( ");
			sqlBuffer.append(" select r.regionid from view_region r ");
			sqlBuffer.append(" start with r.regionid='");
			sqlBuffer.append(userInfo.getRegionId());
			sqlBuffer.append("' ");
			sqlBuffer.append(" connect by prior r.regionid=r.parentid ");
			sqlBuffer.append(" ) ");
		}
		if (userInfo.isContractor()) {
			sqlBuffer.append(" and vo.id=any( ");
			sqlBuffer.append(" select v.id from view_org v ");
			sqlBuffer.append(" start with v.id='");
			sqlBuffer.append(userInfo.getOrgId());
			sqlBuffer.append("' ");
			sqlBuffer.append(" connect by prior v.id=v.parentid ");
			sqlBuffer.append(" ) ");
		}
		return sqlBuffer.toString();
	}

}