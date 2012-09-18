package com.cabletech.business.wplan.plan.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.wplan.plan.model.Patrolinfo;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.DateUtil;
import com.cabletech.common.util.Page;

/**
 * 巡检计划信息Dao
 * 
 * @author zhaobi
 * @author 杨隽 2012-07-25 添加updateResourceNum()方法
 * 
 */
@Repository
public class PatrolinfoDao extends BaseDao<Patrolinfo, String> {

	/**
	 * 获取巡检信息SQL
	 * 
	 * @return
	 */
	private StringBuffer getPatrolinfoSql() {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append("select * from (select pi.id,PI.YEAR,PI.BUSINESS_TYPE,fn_getnamebycode(PI.BUSINESS_TYPE,'businesstype') BUSINESS_TYPENAME,pi.region_id,pi.region_id AS REGIONID,pi.plan_type,pi.plan_name,pi.start_time,pi.end_time,case when  pi.plan_state='02' then '待审核' when   pi.plan_state='03' then '已审核' when  pi.plan_state='04' then '未通过' else '未提交' end planstatename, pi.plan_state,");
		sqlBuffer.append(" nvl(w.count_n,0) as patrolcount, ");
		sqlBuffer
				.append(" r.regionname,p.name patrolgroupname,pi.patrol_group_id, ");
		sqlBuffer
				.append(" p.orgid,p.orgname,pi.CREATER,to_char(pi.CREATETIME,'yyyy-mm-dd hh24:mi:ss') as CREATETIME_DIS,pi.CREATETIME,u.username CREATERNAME,pa.approver,pa.result approvername  from WPLAN_PATROLINFO pi ");
		sqlBuffer
				.append(" left join (select wp.id,count(1) as count_n from WPLAN_PATROLINFO wp JOIN WPLAN_PATROLRESOURCE wpr on wp.id=wpr.plan_id group by wp.id) w on w.id=pi.id ");
		sqlBuffer
				.append(" left join  view_region r on pi.region_id=r.regionid ");
		sqlBuffer
				.append(" left join view_patrolgroup p on pi.patrol_group_id=p.id ");
		sqlBuffer
				.append(" left join view_userinfo u on pi.CREATER=u.sid left join wplan_patrolapprove pa on pi.id=pa.plan_id where 1=1 ");

		return sqlBuffer;
	}

	/**
	 * 获取待办任务流程SQL
	 * 
	 * @param sqlBuf
	 *            StringBuffer
	 * @param parameter
	 *            QueryParameter
	 * @return
	 */
	private StringBuffer getWaithHandledSql(StringBuffer sqlBuf,
			QueryParameter parameter) {
		sqlBuf.append(" JOIN ( ");
		sqlBuf.append(" SELECT jbpm.BZID,jbpm.TASKID, ");
		sqlBuf.append(" jbpm.TASKNAME AS PROINST_STATE,jbpm.RES AS PROCESS_URL ");
		sqlBuf.append(" FROM VIEW_JBPM_USERTASK jbpm ");
		sqlBuf.append(" WHERE 1=1 ");
		parameter.setAlias("jbpm");
		BusinessConditionUtils.getWaitHandledCondition(parameter, sqlBuf);
		sqlBuf.append(" ) join_table ON plan.id=join_table.BZID ");
		return sqlBuf;
	}

	/**
	 * 生成查询SQL语句
	 * 
	 * @param patrolinfo
	 *            Patrolinfo
	 * @param sqlbuf
	 *            StringBuffer
	 * @return
	 */
	private StringBuffer getQueryCondition(Patrolinfo patrolinfo,
			StringBuffer sqlbuf) {
		if (patrolinfo != null) {
			// 按主键类型
			if (StringUtils.isNotBlank(patrolinfo.getId())) {
				sqlbuf.append(" and PI.id ='" + patrolinfo.getId() + "'");
			}
			// 按专业类型
			if (StringUtils.isNotBlank(patrolinfo.getBusinesstype())) {
				sqlbuf.append(" and PI.BUSINESS_TYPE ='"
						+ patrolinfo.getBusinesstype() + "'");
			}
			// 按计划类型
			if (StringUtils.isNotBlank(patrolinfo.getPlantype())) {
				sqlbuf.append(" and PI.plan_type ='" + patrolinfo.getPlantype()
						+ "'");
			}
			// 按区域
			if (StringUtils.isNotBlank(patrolinfo.getRegionid())) {
				sqlbuf.append(" and PI.REGION_ID= any(select regionid from view_region start with regionid='"
						+ patrolinfo.getRegionid()
						+ "' connect by prior regionid=parentid)");
			}
			// 按巡检组
			if (StringUtils.isNotBlank(patrolinfo.getPatrolgroupid())) {
				sqlbuf.append(" and PI.PATROL_GROUP_ID ='"
						+ patrolinfo.getPatrolgroupid() + "'");
			}
			// 按计划名称
			if (StringUtils.isNotBlank(patrolinfo.getPlanname())) {
				sqlbuf.append(" and PI.PLAN_NAME LIKE '%"
						+ patrolinfo.getPlanname() + "%'");
			}
			// 按巡检状态
			if (StringUtils.isNotBlank(patrolinfo.getPlanstate())) {
				sqlbuf.append(" and PI.PLAN_state='"
						+ patrolinfo.getPlanstate() + "'");
			}
			// 按巡检公司查询
			if (StringUtils.isNotBlank(patrolinfo.getContractorid())) {
				sqlbuf.append(" and p.orgid=any(select id from view_org start with id='"
						+ patrolinfo.getContractorid()
						+ "' connect by prior id=parentid)");
			}
			// 按申请人查询
			if (StringUtils.isNotBlank(patrolinfo.getCreatername())) {
				sqlbuf.append(" and U.username LIKE '%"
						+ patrolinfo.getCreatername() + "%'");

			}
			// 按审核人查询
			if (StringUtils.isNotBlank(patrolinfo.getApprovername())) {
				sqlbuf.append(" and pa.result LIKE '%"
						+ patrolinfo.getApprovername() + "%'");

			}
			// 按统计时间
			if (StringUtils.isNotBlank(patrolinfo.getStarttime())) {
				// 添加查询的开始、结束时间在计划的时间段
				sqlbuf.append(" and PI.createtime>= date '"
						+ patrolinfo.getStarttime() + "'");
			}
			// 按统计时间
			if (StringUtils.isNotBlank(patrolinfo.getEndtime())) {
				// 添加查询的开始、结束时间在计划的时间段
				sqlbuf.append(" and PI.createtime<= to_date('"
						+ patrolinfo.getEndtime()
						+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
			}
		}
		sqlbuf.append(" ) plan");
		return sqlbuf;
	}

	/**
	 * 分页获取计划信息
	 * 
	 * @param patrolinfo
	 *            巡检计划信息
	 * @param page
	 *            分页器
	 */
	public Page queryPatrolinfo(Patrolinfo patrolinfo, Page page) {
		StringBuffer sql = getPatrolinfoSql();
		sql = getQueryCondition(patrolinfo, sql);
		sql.append(" order by plan_name,CREATETIME");
		logger.info("查询计划分页信息sql:" + sql);
		super.findSQLPage(page, sql.toString());
		return page;
	}

	/**
	 * 获取全部计划信息
	 * 
	 * @param patrolinfo
	 *            巡检计划信息
	 */
	public List<Map<String, Object>> queryPatrolinfoList(Patrolinfo patrolinfo) {
		StringBuffer sql = getPatrolinfoSql();
		sql = getQueryCondition(patrolinfo, sql);
		logger.info("获取全部计划信息sql:" + sql);
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 获取待办列表
	 * 
	 * @param patrolinfo
	 *            patrolinfo
	 * @param page
	 *            page
	 * @param parameter
	 *            parameter
	 * @return
	 */
	public Page queryWaithHandledList(Patrolinfo patrolinfo, Page page,
			QueryParameter parameter) {
		StringBuffer sql = getPatrolinfoSql();
		sql = getQueryCondition(patrolinfo, sql);
		sql = getWaithHandledSql(sql, parameter);
		logger.info("查询待办计划分页信息sql:" + sql);
		super.findSQLPage(page, sql.toString());
		return page;
	}

	/**
	 * 查询计划信息根据主键sql
	 * 
	 * @param planid
	 *            计划ID
	 * @return
	 */
	public Map<String, Object> queryPatrolinfoByID(String planid) {
		StringBuffer sql = getPatrolinfoSql();
		Patrolinfo patrolinfo = new Patrolinfo();
		patrolinfo.setId(planid);
		sql = getQueryCondition(patrolinfo, sql);
		logger.info("查询计划信息根据主键sql:" + sql);
		// 为了兼容老数据
		List<Map<String, Object>> list = super.jdbcTemplate.queryForList(sql
				.toString());
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据日期变更计划的维护资源数量
	 * 
	 * @param recordDate
	 *            Date
	 * @param rsId
	 *            String
	 */
	public void updateResourceNum(Date recordDate, String rsId) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" UPDATE WPLAN_PATROLINFO wp ");
		sql.append(" SET wp.MAINTAIN_RESOURCES_NUM=wp.MAINTAIN_RESOURCES_NUM-1 ");
		sql.append(" WHERE END_TIME>=to_date('");
		sql.append(DateUtil.UtilDate2Str(recordDate, "yyyy-MM-dd"));
		sql.append("','yyyy-mm-dd')  ");
		sql.append(" AND EXISTS( ");
		sql.append(" SELECT ID FROM WPLAN_PATROLRESOURCE wr  ");
		sql.append(" WHERE wp.ID=wr.PLAN_ID  ");
		sql.append(" AND wr.RESOURCE_ID='");
		sql.append(rsId);
		sql.append("' ");
		sql.append(" ) ");
		super.getJdbcTemplate().execute(sql.toString());
	}
}
