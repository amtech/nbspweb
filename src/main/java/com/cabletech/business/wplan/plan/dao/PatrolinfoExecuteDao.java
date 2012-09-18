package com.cabletech.business.wplan.plan.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.DateUtil;
import com.cabletech.common.util.Page;

/**
 * 计划执行Dao
 * 
 * @author zhaobi
 * 
 */
@Repository
public class PatrolinfoExecuteDao extends BaseDao<Patrolinfo, String> {

	/**
	 * 按指定条件获得巡检执行信息
	 * 
	 * @param patrolinfo
	 *            bean
	 * @param page
	 *            page
	 * @return
	 */
	public Page getAllPatrolScheduleForSearch(Patrolinfo patrolinfo, Page page) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select t.*,(t.patrolcount-t.endpatrolcount) nopatrolcount,case when t.patrolcount=0 then '--' else to_char(Round(TO_NUMBER((t.endpatrolcount/t.patrolcount)*100),2),'9999990.99')||'%' end PLANRATE,FN_GET_EXCEPTION_STATION_COUNT(t.id,to_char(t.start_time,'yyyy-mm-dd hh24:mi:ss'),to_char(t.end_time,'yyyy-mm-dd hh24:mi:ss')) EXCEPTIONCOUNT ");
		sqlBuffer
				.append(" ,fn_getnamebycode(BUSINESS_TYPE,'businesstype') BUSINESS_TYPENAME from((select tt.*, (select count(1) from WPLAN_PATROLRESOURCE pr where pr.plan_id=tt.id)  patrolcount,nvl(g.endpatrolcount,0) endpatrolcount from ");
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
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		if (patrolinfo != null) {
			// 按主键类型
			if (StringUtils.isNotBlank(patrolinfo.getId())) {
				sqlBuffer.append(" and id ='" + patrolinfo.getId() + "'");
			}
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
			// 按计划名称
			if (StringUtils.isNotBlank(patrolinfo.getPlanname())) {
				sqlBuffer.append(" and PLAN_NAME LIKE '%"
						+ patrolinfo.getPlanname() + "%'");
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
						+ "' between start_time and end_time) " + " or (date '"
						+ patrolinfo.getEndtime()
						+ "' between start_time and end_time)) ");

			}
			// 统计结束时间
			if (StringUtils.isNotBlank(patrolinfo.getEndtime())) {
				if (StringUtils.isBlank(patrolinfo.getStarttime())) {
					patrolinfo.setStarttime("1900-01-01");
				}
				// 添加计划时间在查询的开始、结束时间段
				sqlBuffer.append(" or ((start_time " + " between date '"
						+ patrolinfo.getStarttime() + "' and date '"
						+ patrolinfo.getEndtime() + "') " + " or (end_time "
						+ " between date '" + patrolinfo.getStarttime()
						+ "' and date '" + patrolinfo.getEndtime() + "'))) ");
			}
			// 按组合查询条件
			if (StringUtils.isNotBlank(patrolinfo.getCondition())) {
				sqlBuffer.append(patrolinfo.getCondition());
			}
		}
		sqlBuffer.append(" order by REGION_ID asc");
		logger.info("按指定条件获得巡检执行信息sql:" + sqlBuffer.toString());
		return super.findSQLPage(page, sqlBuffer.toString());
	}

	/**
	 * 获取所有未巡检资源明细
	 * 
	 * @param planid
	 *            计划ID
	 * @param page
	 *            page
	 * @return
	 */
	public Page getAllLostDetail(String planid, Page page) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select ps.*,rr.name rs_name,(select count(1) from RS_PATROLADDRESSINFO pl where pl.res_id=ps.resource_id and pl.res_type=ps.resource_type) ritemcount ");
		sqlBuffer
				.append(" ,fn_getnamebycode(ps.resource_type,'businesstype') resource_typename from WPLAN_PATROLRESOURCE ps left join rs_resourcerecord_v rr on ps.resource_id=rr.id and ps.resource_type=rr.type ");
		sqlBuffer
				.append(" where not exists(select 1 from WPLAN_EXECUTERESULT r where ps.plan_id=r.plan_id and ps.resource_type=r.resource_type and ps.resource_id=r.resource_id and patrol_group_id=r.patrol_group_id and r.end_time is not null) ");
		sqlBuffer.append(" and ps.plan_id='" + planid + "'");
		logger.info("获取所有未巡检资源明细sql:" + sqlBuffer.toString());
		return super.findSQLPage(page, sqlBuffer.toString());
	}

	/**
	 * 获取所有已巡检资源明细
	 * 
	 * @param planid
	 *            String
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getAllOverDetail(String planid, Page page) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" 	select p.plan_name,r.id,r.patrolman_id,r.resource_type,r.resource_id,rr.NAME rs_name,r.arrive_time,r.start_time,r.end_time,c.name patrolmanname,FN_GET_EXCEPTION_ITEM_RID(r.id) EXCEPTIONCOUNT ");
		sqlBuffer
				.append(" 	,fn_getnamebycode(r.resource_type,'businesstype') resource_typename from wplan_executeresult r join wplan_patrolinfo p on r.plan_id=p.id");
		sqlBuffer
				.append("  join wplan_patrolresource pr  on pr.plan_id=r.plan_id and pr.resource_id=r.resource_id and pr.resource_type=r.resource_type ");
		sqlBuffer
				.append("  join rs_resourcerecord_v rr on r.resource_type=rr.type and r.resource_id=rr.id ");
		sqlBuffer.append("  left join base_person c on c.id=r.patrolman_id ");
		sqlBuffer.append("  where  p.id='" + planid + "'");
		sqlBuffer
				.append(" and r.end_time=(select max(end_time) from wplan_executeresult where plan_id=r.plan_id and resource_id=r.resource_id and resource_type=r.resource_type and patrol_group_id=r.patrol_group_id) ");
		String sql = sqlBuffer.toString();
		logger.info("查询计划已巡检资源明细sql:" + sql);
		return super.findSQLPage(page, sql);
	}

	/**
	 * 获取已巡检RFID
	 * 
	 * @param rid
	 *            巡检执行结果
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getOverRFIDDetail(String rid, Page page) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append("	select rc.patrold_time,pa.address,rc.rfid,rc.lac||'/'||rc.ci LACCI ");
		sqlBuffer
				.append(" from WPLAN_RESOURCECHECK rc join RS_PATROLADDRESSINFO pa on pa.rfid=rc.rfid");
		sqlBuffer.append(" where rc.executeresult_id='" + rid + "'");
		String sql = sqlBuffer.toString();
		logger.info("查询已巡检RFID信息sql:" + sql);
		return super.findSQLPage(page, sqlBuffer.toString());

	}

	/**
	 * 获取未巡检RFID
	 * 
	 * @param rid
	 *            String
	 * @param resourceid
	 *            资源ID
	 * @param resourcetype
	 *            资源类型
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getLostRFIDDetail(String rid, String resourceid,
			String resourcetype, Page page) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append("	select pa.rfid,pa.address from RS_PATROLADDRESSINFO pa  ");
		sqlBuffer.append(" where pa.res_type='" + resourcetype + "' ");
		sqlBuffer.append(" and pa.res_id='" + resourceid + "' ");
		sqlBuffer
				.append(" and not exists(select 1 from wplan_resourcecheck rc where rc.rfid=pa.rfid and ");
		sqlBuffer.append(" rc.executeresult_id='" + rid + "')");
		String sql = sqlBuffer.toString();
		logger.info("查询未巡检RFID信息sql:" + sql);
		return super.findSQLPage(page, sqlBuffer.toString());

	}

	/**
	 * 获取巡检异常项总数
	 * 
	 * @param rid
	 *            String
	 * @return
	 */
	public Map<String, Object> getExceptionItemCount(String rid) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select FN_GET_EXCEPTION_ITEM_RID('" + rid
				+ "') exceptioncount from dual ");
		String sql = sqlBuffer.toString();
		logger.info("查询巡检异常项总数sql:" + sql);
		return this.jdbcTemplate.queryForMap(sql);
	}

	/**
	 * 获取巡检项巡检结果明细
	 * 
	 * @param rid
	 *            String
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getItemDetail(String rid, Page page) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append("  select pi.item_name,ps.subitem_name,pr.subitem_patrol, pr.exception_desc, ");
		sqlBuffer
				.append(" (case  when pr.subitem_patrol=ps.exception_value then 1 else 0 end) checked ");
		sqlBuffer
				.append("  from wplan_patrolitem pi left join  wplan_patrolsubitem ps on pi.id=ps.item_id left join  wplan_patrolrecord pr on pr.subitem_id=ps.id ");
		sqlBuffer.append("  where pr.executeresult_id= '" + rid + "' ");
		sqlBuffer.append("  order by checked desc");
		String sql = sqlBuffer.toString();
		logger.info("获取巡检项巡检结果明细sql:" + sql);
		return super.findSQLPage(page, sqlBuffer.toString());

	}

	/**
	 * 获取巡检项巡检结果明细 EXCEL导出使用 无分页
	 * 
	 * 后期优化
	 * 
	 * @param rid
	 *            String
	 * @return
	 */
	public List getItemDetailForExport(String rid) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append("  select pi.item_name,ps.subitem_name,pr.subitem_patrol, pr.exception_desc, ");
		sqlBuffer
				.append(" (case  when pr.subitem_patrol=ps.exception_value then '异常' else '正常' end) checked ");
		sqlBuffer
				.append("  from wplan_patrolitem pi left join  wplan_patrolsubitem ps on pi.id=ps.item_id left join  wplan_patrolrecord pr on pr.subitem_id=ps.id ");
		sqlBuffer.append("  where pr.executeresult_id= '" + rid + "' ");
		sqlBuffer.append("  order by checked desc");
		String sql = sqlBuffer.toString();
		logger.info("获取巡检项巡检结果明细sql:" + sql);
		return super.getJdbcTemplate().queryForList(sql);
	}
}
