package com.cabletech.business.wplan.plan.dao;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.DateUtil;
import com.cabletech.common.util.Page;

/**
 * 巡检分析Dao
 * 
 * @author Administrator
 * 
 */
@Repository
public class PatrolanalysisDao extends BaseDao<Patrolinfo, String> {
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 月类型
	 */
	private  static final String TIMETYPE_MONTH ="0";
	
	/**
	 * 季度类型
	 */
	private  static final String TIMETYPE_SEASON ="1";
	/**
	 * 年度类型
	 */
	private  static final String TIMETYPE_YEAR ="2";

	/**
	 * 获取所有巡检
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @return
	 */
	public Map<String, Object> getAllPatrolCount(Patrolinfo patrolinfo) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select count(id) PLANCOUNT,nvl(sum(patrolcount),0) PLANRESCOUNT,nvl(sum(nopatrolcount),0) PLANLOSTRESCOUNT,nvl(sum(endpatrolcount),0) PLANOVERRESCOUNT,");
		sqlBuffer
				.append(" case when nvl(sum(patrolcount),0)=0 then '--' else to_char(Round(TO_NUMBER((nvl(sum(endpatrolcount),0)/nvl(sum(patrolcount),0))*100),2),'9999990.99')||'%'end PLANRATE  from(");
		sqlBuffer.append(getAllPatrolSql(patrolinfo));
		sqlBuffer.append(getPatrolCondition(patrolinfo));
		sqlBuffer.append(" )");
		String sql = sqlBuffer.toString();
		logger.debug("获取所有巡检分析统计数据sql:" + sql);
		return this.getJdbcTemplate().queryForMap(sql);
	}

	/**
	 * 按指定条件获得巡检组统计信息分页列表
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getPatrolGroupPatrolInfo(Patrolinfo patrolinfo, Page page) {
		String sql = getPatrolGroupPatrolSQL(patrolinfo).toString();
		logger.debug("查询获得巡检组统计信息列表sql:" + sql);
		this.findSQLPage(page, sql);
		return page;
	}

	/**
	 * 获取巡检组巡检列表数据
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @return
	 */
	public List<Map<String, Object>> getPatrolGroupPatrolList(
			Patrolinfo patrolinfo) {
		String sql = getPatrolGroupPatrolSQL(patrolinfo).toString();
		logger.info("查询获得巡检组统计信息sql:" + sql);
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 获取巡检组巡检sql
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @return
	 */
	private StringBuffer getPatrolGroupPatrolSQL(Patrolinfo patrolinfo) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select orgid,orgname,patrol_group_id,patrolGROUPname,region_id,regionname,count(id) PLANCOUNT,nvl(sum(patrolcount),0) PLANRESCOUNT,nvl(sum(nopatrolcount),0) PLANLOSTRESCOUNT,nvl(sum(endpatrolcount),0) PLANOVERRESCOUNT,sum(EXCEPTIONCOUNT) EXCEPTIONCOUNT from(");
		sqlBuffer.append(getAllPatrolSql(patrolinfo));
		sqlBuffer.append(getPatrolCondition(patrolinfo));
		sqlBuffer
				.append(" ) group by orgid,orgname,patrol_group_id,patrolGROUPname,region_id,regionname order by orgid");
		return sqlBuffer;
	}

	/**
	 * 取得区县巡检情况（县区、代维公司、区县的站点数、计划巡检站点数、实际巡检站点数、巡检率）
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getPatrolRegionInfo(Patrolinfo patrolinfo, Page page) {
		String sql = getRegionPatrolSQL(patrolinfo).toString();
		logger.info("区域巡检情况的sql: " + sql);
		this.findSQLPage(page, sql);
		return page;

	}

	/**
	 * 取得区县巡检情况（县区、代维公司、区县的站点数、计划巡检站点数、实际巡检站点数、巡检率）
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @return
	 */
	public List<Map<String, Object>> getPatrolOrgList(Patrolinfo patrolinfo) {
		String sql = getRegionPatrolSQL(patrolinfo).toString();
		logger.info("区域巡检情况的图表sql: " + sql);
		return this.getJdbcTemplate().queryForList(sql);

	}

	/**
	 * 获取巡检组巡检sql
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @return
	 */
	private StringBuffer getRegionPatrolSQL(Patrolinfo patrolinfo) {
		StringBuffer sqlBuffer = new StringBuffer("");
	
		sqlBuffer
				.append(" select orgid,orgname,region_id,regionname,count(id) PLANCOUNT,sum(patrolcount) PLANRESCOUNT,sum(nopatrolcount)PLANLOSTRESCOUNT,sum(endpatrolcount) PLANOVERRESCOUNT,sum(EXCEPTIONCOUNT) EXCEPTIONCOUNT ");
		sqlBuffer
				.append(" ,(select count(1) from rs_resourcerecord_v v where v.regionid=region_id and v.type ='"
						+ patrolinfo.getBusinesstype() + "') rescount from(");
		sqlBuffer.append(getAllPatrolSql(patrolinfo));
		sqlBuffer.append(getPatrolCondition(patrolinfo));
		sqlBuffer
				.append(" ) group by region_id,regionname,orgid,orgname order by region_id");
		return sqlBuffer;
	}

	/**
	 * 获取所有巡检信息sql
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @return
	 */
	private StringBuffer getAllPatrolSql(Patrolinfo patrolinfo) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select t.*,(t.patrolcount-t.endpatrolcount) nopatrolcount ,FN_GET_EXCEPTION_STATION_COUNT(t.id,to_char(t.start_time,'yyyy-mm-dd hh24:mi:ss'),to_char(t.end_time,'yyyy-mm-dd hh24:mi:ss')) EXCEPTIONCOUNT ");
		sqlBuffer
				.append(" from((select tt.*, (select count(1) from WPLAN_PATROLRESOURCE pr where pr.plan_id=tt.id)  patrolcount,nvl(g.endpatrolcount,0) endpatrolcount from ");
		sqlBuffer
				.append(" (select distinct pi.id,pi.region_id,pi.year,pi.plan_type,pi.PLAN_state,PI.BUSINESS_TYPE,pi.plan_name,pi.start_time,pi.end_time,p.orgid,p.orgname,pi.PATROL_GROUP_ID,p.name patrolGROUPname,vr.regionname");
		sqlBuffer
				.append(" from WPLAN_PATROLINFO pi join WPLAN_PATROLRESOURCE pr on pr.plan_id=pi.id ");
		sqlBuffer.append("  join  view_region vr on pi.region_id=vr.regionid ");
		sqlBuffer
				.append("  join view_patrolgroup p on pi.patrol_group_id=p.id)tt ");
		sqlBuffer
				.append("  left join (select count(distinct r.id) endpatrolcount,r.plan_id from wplan_executeresult r ");
		sqlBuffer
				.append(" join WPLAN_PATROLRESOURCE pr  on pr.plan_id=r.plan_id and pr.resource_id=r.resource_id and pr.resource_type=r.resource_type ");
		sqlBuffer
				.append(" and r.end_time=(select max(end_time) from wplan_executeresult where plan_id=r.plan_id and resource_id=r.resource_id and resource_type=r.resource_type and patrol_group_id=r.patrol_group_id)");
		sqlBuffer
				.append(" group by r.plan_id) g on tt.id=g.plan_id))t  where 1=1 ");
		return sqlBuffer;
	}

	/**
	 * 取得统计计划相关条件
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @return
	 */
	private StringBuffer getPatrolCondition(Patrolinfo patrolinfo) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer sqlBuffer = new StringBuffer("");
		if (patrolinfo != null) {
			// 按专业类型
			if (StringUtils.isNotBlank(patrolinfo.getBusinesstype())) {
				sqlBuffer.append(" and  BUSINESS_TYPE ='"
						+ patrolinfo.getBusinesstype() + "'");
			}
			// 按区域
			if (StringUtils.isNotBlank(patrolinfo.getRegionid())) {
				sqlBuffer
						.append(" and REGION_ID= any(select regionid from view_region start with regionid='"
								+ patrolinfo.getRegionid()
								+ "' connect by prior regionid=parentid)");
			}
			// 按巡检公司查询
			if (StringUtils.isNotBlank(patrolinfo.getContractorid())) {
				sqlBuffer
						.append(" and orgid=any(select id from view_org start with id='"
								+ patrolinfo.getContractorid()
								+ "' connect by prior id=parentid)");
			}
			// 按巡检组
			if (StringUtils.isNotBlank(patrolinfo.getPatrolgroupid())) {
				sqlBuffer.append(" and PATROL_GROUP_ID ='"
						+ patrolinfo.getPatrolgroupid() + "'");
			}
			// 按巡检状态
			if (StringUtils.isNotBlank(patrolinfo.getPlanstate())) {
				sqlBuffer.append(" and PLAN_state='"
						+ patrolinfo.getPlanstate() + "'");
			}
			// 按统计开始时间
			if (StringUtils.isNotBlank(patrolinfo.getStarttime())) {
				if (StringUtils.isBlank(patrolinfo.getEndtime())) {
					patrolinfo.setEndtime("2099-12-31");
				}
				// 添加查询的开始、结束时间在计划的时间段
				sqlBuffer.append(" and (((date '" + patrolinfo.getStarttime()
						+ "' between start_time and end_time+1) " + " or (date '"
						+ patrolinfo.getEndtime()
						+ "' between start_time and end_time+1)) ");
			}
			// 统计结束时间
			if (StringUtils.isNotBlank(patrolinfo.getEndtime())) {
				if (StringUtils.isBlank(patrolinfo.getStarttime())) {
					patrolinfo.setStarttime("1900-01-01");
				}
				// 添加计划时间在查询的开始、结束时间段
				sqlBuffer.append(" or ((start_time " + " between date '"
						+ patrolinfo.getStarttime() + "' and date '"
						+ patrolinfo.getEndtime() + "'+1) " + " or (end_time "
						+ " between date '" + patrolinfo.getStarttime()
						+ "' and date '" + patrolinfo.getEndtime() + "'+1))) ");
			}// 按组合查询条件
			if (StringUtils.isNotBlank(patrolinfo.getCondition())) {
				sqlBuffer.append(patrolinfo.getCondition());
			}
		}
		return sqlBuffer;
	}

	/**
	 * 获取区域巡检率
	 * 
	 * @param regionid
	 *            区域ID
	 * @param busstype
	 *            专业类型
	 * @param timetype 时间类型
	 * @return
	 */
	public List<Map<String, Object>> getRegionPatrolRate(String regionid,
			String busstype,String timetype) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select r.REGIONID,r.REGIONNAME NAME,nvl(sum(d.rate),0) rate from ");
		sqlBuffer
				.append(" (select regionid,regionname from view_region where lv=3 start with regionid='"
						+ regionid + "' connect by prior regionid=parentid) r");
		sqlBuffer
				.append(" left join (select p.region_id,count(distinct pr.id) allplancount,count(distinct e.resource_id) YXCOUNT, ");
		sqlBuffer
				.append("  to_char(decode(count(distinct pr.id),null,0,0,0,100*count(distinct e.resource_id)/count(distinct pr.id)),'FM990.09') rate ");
		sqlBuffer
				.append(" from wplan_patrolinfo p left join wplan_patrolresource pr on p.id=pr.plan_id  left join wplan_executeresult e ");
		sqlBuffer
				.append(" on e.plan_id=pr.plan_id and e.resource_type=pr.resource_type and e.end_time is not null");
		sqlBuffer
				.append("  join view_region r on p.region_id=r.REGIONID where p.plan_state='03' and p.business_type='"
						+ busstype + "'");
		sqlBuffer
				.append("  AND p.REGION_ID=any(select regionid from region start with regionid='"
						+ regionid
						+ "' connect by prior regionid=parentregionid) ");
		sqlBuffer.append(getTimeTypeStr(timetype));
		sqlBuffer
				.append(" group by p.region_id,r.REGIONNAME,p.business_type order by r.REGIONNAME) d on r.REGIONID=substr(d.region_id,0,4)||'00' ");
		sqlBuffer
				.append(" group by r.REGIONID,r.REGIONNAME order by r.REGIONID");
		logger.info("getRegionPatrolRate: " + sqlBuffer.toString());
		return this.getJdbcTemplate().queryForList(sqlBuffer.toString());
	}

	/**
	 * 获取代维公司巡检率
	 * 
	 * @param regionid
	 *            区域ID
	 * @param busstype
	 *            专业类型
	 * @param timetype 时间类型           
	 * @return
	 */
	public List<Map<String, Object>> getContractoridPatrolRate(String regionid,
			String busstype,String timetype) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select m.ID,m.NAME,nvl(d.rate,0) rate from view_org m left join ");
		sqlBuffer
				.append(" (select count(distinct pr.id) allplancount,count(distinct e.resource_id) YXCOUNT,");
		sqlBuffer
				.append(" to_char(decode(count(distinct pr.id),null,0,0,0,100*count(distinct e.resource_id)/count(distinct pr.id)),'FM990.09') rate, ");
		sqlBuffer
				.append(" op.orgid from wplan_patrolinfo p left join wplan_patrolresource pr on p.id=pr.plan_id left join wplan_executeresult e on e.plan_id=pr.plan_id and e.resource_type=pr.resource_type and e.end_time is not null");
		sqlBuffer
				.append(" join  view_patrolgroup op on p.patrol_group_id=op.ID where p.plan_state='03' and p.REGION_ID=any (select regionid from region start with regionid='"
						+ regionid
						+ "' connect by prior regionid=parentregionid)");
		sqlBuffer.append(getTimeTypeStr(timetype));
		sqlBuffer
				.append(" and  p.business_type='"
						+ busstype
						+ "' group by op.orgid,op.orgname order by op.orgname) d on m.ID=d.orgid where 1=1");
		// sqlBuffer
		// .append(" and  e.end_time=(select max(end_time) from wplan_executeresult where plan_id=e.plan_id and resource_id=e.resource_id and resource_type=e.resource_type and patrol_group_id=e.patrol_group_id)");
		sqlBuffer.append(" and  m.REGIONID='" + regionid
				+ "' and m.ORGTYPE='2' order by m.ID");
		logger.info("代维巡检率 getContractoridPatrolRate: " + sqlBuffer.toString());
		return this.getJdbcTemplate().queryForList(sqlBuffer.toString());
	}

	/**
	 * 获取巡检组比率
	 * 
	 * @param orgid
	 *            组织ID
	 * @param busstype
	 *            busstype
	 * @param timetype 时间类型           
	 * @return
	 */
	public List<Map<String, Object>> getPatrolGroupPatrolRate(String orgid,
			String busstype,String timetype) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append("select m.ID,m.NAME,nvl(d.rate,0) rate from view_patrolgroup m left join ");
		sqlBuffer
				.append(" (select count(distinct pr.id) allplancount,count(distinct e.resource_id) YXCOUNT,");
		sqlBuffer
				.append(" to_char(decode(count(distinct pr.id),null,0,0,0,100*count(distinct e.resource_id)/count(distinct pr.id)),'FM990.09') rate,");
		sqlBuffer
				.append("  op.ID  from wplan_patrolinfo p left  join wplan_patrolresource pr on p.id=pr.plan_id  left join wplan_executeresult e on e.plan_id=pr.plan_id and e.resource_type=pr.resource_type and e.end_time is not null");
		sqlBuffer
				.append("  join  view_patrolgroup op on p.patrol_group_id=op.ID where p.plan_state='03' and p.REGION_ID=op.regionid");
		sqlBuffer.append(getTimeTypeStr(timetype));
		// sqlBuffer.append(" and  e.end_time=(select max(end_time) from wplan_executeresult where plan_id=e.plan_id and resource_id=e.resource_id and resource_type=e.resource_type and patrol_group_id=e.patrol_group_id)");
		sqlBuffer.append(" and  p.business_type='" + busstype
				+ "' group by op.id) d");
		sqlBuffer.append(" on m.ID=d.id where m.ORGID='" + orgid
				+ "' order by m.id ");

		logger.info("巡检组巡检率 getPatrolGroupPatrolRate: " + sqlBuffer.toString());
		return this.getJdbcTemplate().queryForList(sqlBuffer.toString());
	}

	/**
	 * 获取查询时间类型条件
	 * @param timetype 数据类型本月，本季度，本年度
	 * @return
	 */
	private StringBuffer getTimeTypeStr(String timetype){
		HashMap<String,String> map=new HashMap<String, String>();
		StringBuffer buf=new StringBuffer();
		if(TIMETYPE_MONTH.equals(timetype)){
			map=DateUtil.getMonthSlot();
		}else if(TIMETYPE_SEASON.equals(timetype)){
			map=DateUtil.getQuarterSlot();
		}else{
			map=DateUtil.getYearSlot();
		}
		// 添加查询的开始、结束时间在计划的时间段
		buf.append(" and (((date '"
				+ map.get("startTime")
				+ "' between p.start_time and p.end_time) "
				+ " or (date '"
				+ map.get("endTime")
				+ "' between p.start_time and p.end_time)) ");
		buf.append(" or ((p.start_time "
				+ " between date '"
				+map.get("startTime")
				+ "' and date '"
				+ map.get("endTime")
				+ "') "
				+ " or (p.end_time "
				+ " between date '"
				+ map.get("startTime")
				+ "' and date '"
				+ map.get("endTime") + "'))) ");
		return buf;
		
	}
}
