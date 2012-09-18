package com.cabletech.business.wplan.plan.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.resource.model.ResourceInfo;
import com.cabletech.business.wplan.plan.model.PatrolResource;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 巡检资源关系Dao
 * 
 * @author zhaobi
 * @author 杨隽 2012-07-25 添加去除已经退服的所属资源的查询条件
 * 
 */
@Repository
public class PatrolResourceDao extends BaseDao<PatrolResource, String> {

	/**
	 * 获取巡检组所属资源
	 * 
	 * @param businesstype
	 *            专业类型
	 * @param patrolgroupid
	 *            巡检组ID
	 * @return
	 */
	public List<Map<String, Object>> getPatrolResource(String businesstype,
			String patrolgroupid) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" SELECT DISTINCT RS.RS_ID,RS.RS_TYPE,nvl(zd.zymc,'    ') RS_NAME,zd.zdbh stationcode  ");
		sqlBuffer
				.append(" FROM RES_MAINTENANCE RS JOIN RES_ZDXX zd ON RS.RS_ID=zd.xtbh join res_resourcetype rt on zd.xtbh=rt.xtbh and rt.business_type=rs.rs_type ");
		sqlBuffer
				.append(" JOIN  VIEW_PATROLGROUP P  ON RS.PATROL_GROUP_ID=P.ID where 1=1 AND (zd.STATUS!='"
						+ ResourceInfo.DELETED_STATE
						+ "' or zd.STATUS is null ) ");
		// 根据巡检组分配
		if (StringUtils.isNotBlank(patrolgroupid)) {
			sqlBuffer.append(" and rs.patrol_group_id='" + patrolgroupid + "'");
		}
		// 根据专业类型分配
		if (StringUtils.isNotBlank(businesstype)) {
			sqlBuffer.append(" and rs.RS_TYPE='" + businesstype + "'");
		}
		sqlBuffer.append(" union SELECT '" + businesstype
				+ "' RS_ID, 'root' RS_TYPE,'站点' RS_NAME,'' STATIONCODE");
		sqlBuffer.append(" FROM dual ");
		sqlBuffer.append(" order by RS_NAME ");
		logger.info("获取巡检组所属资源sql:" + sqlBuffer.toString());
		return this.getJdbcTemplate().queryForList(sqlBuffer.toString());
	}

	/**
	 * 删除巡检计划资源
	 * 
	 * @param planid
	 *            String
	 */
	public void deleteResource(String planid) {
		String hql = "delete from PatrolResource pr where pr.planid=? ";
		this.batchHQLExecute(hql, planid);

	}

	/**
	 * 根据巡检计划获取维护资源
	 * 
	 * @param planid
	 *            计划ID
	 * @param page
	 *            Page
	 * @return
	 */
	public Page getPatrolResourceByPlanid(String planid, Page page) {
		String sql = getPatrolResourceSQL(planid).toString();
		logger.info("根据巡检计划获取维护资源分页sql:" + sql);
		return super.findSQLPage(page, sql);
	}

	/**
	 * 获取巡检资源根据计划
	 * 
	 * @param planid
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getPatrolResourceByPlanid(String planid) {
		String sql = getPatrolResourceSQL(planid).toString();
		logger.info("根据巡检计划获取维护资源sql:" + sql);
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 根据计划获取巡检资源sql
	 * 
	 * @param planid
	 *            String
	 * @return
	 */
	private StringBuffer getPatrolResourceSQL(String planid) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select pr.resource_id,pr.resource_type,rv.NAME,rv.STATIONCODE,rv.ADDRESS,fn_getnamebycode(pr.resource_type,'businesstype') resource_typename");
		sqlBuffer
				.append(" from wplan_patrolresource pr left join rs_resourcerecord_v rv on pr.resource_id=rv.ID and pr.resource_type=rv.TYPE ");
		sqlBuffer.append(" where pr.plan_id='" + planid + "'");
		return sqlBuffer;
	}

	/**
	 * 获取巡检资源信息
	 * 
	 * @param rid
	 *            巡检结果ID
	 * @return
	 */
	public Map<String, Object> getPatrolResourceInfo(String rid) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select distinct e.id,wp.plan_name,e.resource_type,e.arrive_time,e.end_time,e.resource_id,rv.NAME resource_name,e.patrol_group_id, ");
		sqlBuffer
				.append("	e.patrolman_id,p.NAME patrolgroupname,p.ORGNAME,pe.name patrolmanname,nvl(pe.mobile,pe.phone) phone ");
		sqlBuffer
				.append(" 	,fn_getnamebycode(e.resource_type,'BUSINESSTYPE') resource_typename from wplan_executeresult e left join rs_resourcerecord_v rv on rv.ID=e.resource_id and rv.TYPE=e.resource_type ");
		sqlBuffer
				.append(" left join view_patrolgroup p on  e.patrol_group_id=p.id ");
		sqlBuffer
				.append(" left join base_person pe on e.patrolman_id=pe.id left join wplan_patrolinfo wp ON e.PLAN_ID=wp.ID");
		sqlBuffer.append(" where e.id = '" + rid + "' ");
		String sql = sqlBuffer.toString();
		logger.info("获取巡检资源基本信息sql:" + sql);
		return this.getJdbcTemplate().queryForMap(sql);
	}

}
