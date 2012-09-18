package com.cabletech.business.desktop.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cabletech.common.base.BaseDao;

/**
 * 线路计划巡检率dao
 * 
 * @author 杨隽 2012-03-12 创建
 * 
 */
@Repository
@SuppressWarnings("rawtypes")
public class LinePatrolAnalysisDao extends BaseDao {
	protected final Logger logger = Logger.getLogger("LinePatrolAnalysisDao");

	/**
	 * 获取省区域下的线路计划巡检率列表数据
	 * 
	 * @param regionId
	 *            String 区域编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRegionLinePatrolResultRateList(
			String regionId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select r.regionid,r.regionname name, ");
		sqlBuffer.append(" to_char(nvl( ");
		sqlBuffer.append(" decode(sum(result_.planpoint),0,0, ");
		sqlBuffer.append(" 100*sum(result_.factpoint)/sum(result_.planpoint) ");
		sqlBuffer.append(" ) ");
		sqlBuffer.append(" ,0),'FM990.09') as rate ");
		sqlBuffer.append(" from ( ");
		sqlBuffer.append(" select regionid,regionname,lv from view_region ");
		sqlBuffer.append(" where 1=1 start with regionid='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("' connect by prior regionid=parentid ");
		sqlBuffer.append(" ) r ");
		sqlBuffer.append(" left join (");
		sqlBuffer.append(getPlanPatrolResultSql(regionId));
		sqlBuffer.append(" ) result_ ");
		sqlBuffer.append(" on r.regionid=result_.regionid ");
		sqlBuffer.append(" where lv=3 ");
		sqlBuffer.append(" group by r.regionid,r.regionname ");
		sqlBuffer.append(" order by r.regionid ");
		logger.info("getregionline"+sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取区域下所有组织的线路计划巡检率列表数据
	 * 
	 * @param regionId
	 *            String 区域编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getContractorLinePatrolResultRateList(
			String regionId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select m.id,m.name, ");
		sqlBuffer.append(" to_char(nvl( ");
		sqlBuffer.append(" decode(sum(result_.planpoint),0,0, ");
		sqlBuffer.append(" 100*sum(result_.factpoint)/sum(result_.planpoint) ");
		sqlBuffer.append(" ) ");
		sqlBuffer.append(" ,0),'FM990.09') as rate ");
		sqlBuffer.append(" from view_org m ");
		sqlBuffer.append(" left join (");
		sqlBuffer.append(getPlanPatrolResultSql(regionId));
		sqlBuffer.append(" ) result_ ");
		sqlBuffer.append(" on result_.contractorid=m.id ");
		sqlBuffer.append(" where m.regionid='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("' and m.orgtype='2' ");
		sqlBuffer.append(" group by m.id,m.name ");
		sqlBuffer.append(" order by m.id ");
		logger.info("getcontractorline"+sqlBuffer.toString());
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取组织下所有巡检组的线路计划巡检率列表数据
	 * 
	 * @param regionId
	 *            String 区域编号
	 * @param orgId
	 *            String 组织编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPatrolGroupLinePatrolResultRateList(
			String regionId, String orgId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select m.id,m.name, ");
		sqlBuffer.append(" to_char(nvl( ");
		sqlBuffer.append(" decode(sum(result_.planpoint),0,0, ");
		sqlBuffer.append(" 100*sum(result_.factpoint)/sum(result_.planpoint) ");
		sqlBuffer.append(" ) ");
		sqlBuffer.append(" ,0),'FM990.09') as rate ");
		sqlBuffer.append(" from view_patrolgroup m ");
		sqlBuffer.append(" left join (");
		sqlBuffer.append(getPlanPatrolResultSql(regionId));
		sqlBuffer.append(" ) result_ ");
		sqlBuffer.append(" on m.id=result_.executorid ");
		sqlBuffer.append(" where m.orgid='");
		sqlBuffer.append(orgId);
		sqlBuffer.append("' ");
		sqlBuffer.append(" group by m.id,m.name ");
		sqlBuffer.append(" order by m.id ");
		return super.getSQLALL(sqlBuffer.toString());
	}

	/**
	 * 获取计划巡检执行信息的结果数据列表sql
	 * 
	 * @param regionId
	 *            String 区域编号
	 * @return
	 */
	private String getPlanPatrolResultSql(String regionId) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer("");
		getEndPlanPatrolResult(regionId, sqlBuffer);
		sqlBuffer.append(" union ");
		getCurrentPlanPatrolResult(regionId, sqlBuffer);
		return sqlBuffer.toString();
	}

	/**
	 * 根据区域编号获取当前月份的当前计划执行结果信息
	 * 
	 * @param regionId
	 *            String 区域编号
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getCurrentPlanPatrolResult(String regionId,
			StringBuffer sqlBuffer) {
		sqlBuffer.append(" select pcs.curplanid as planid,vo.regionid, ");
		sqlBuffer.append(" pcs.contractorid,pcs.executorid, ");
		sqlBuffer.append(" pcs.planpointtimes as planpoint, ");
		sqlBuffer.append(" pcs.actualpointtimes as factpoint ");
		sqlBuffer.append(" from plancurrent_stat pcs ");
		sqlBuffer.append(" join view_org vo on pcs.contractorid=vo.id ");
		sqlBuffer.append(" where 1=1 ");
		sqlBuffer.append(" and vo.regionid=any( ");
		sqlBuffer.append(" select regionid from region ");
		sqlBuffer.append(" start with regionid='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("' ");
		sqlBuffer.append(" connect by prior regionid=parentregionid ");
		sqlBuffer.append(" ) ");
		String yearMonth = getYearMonth();
		sqlBuffer.append(" and pcs.enddate>=to_date('");
		sqlBuffer.append(yearMonth);
		sqlBuffer.append("-01','yyyy-mm-dd') ");
		sqlBuffer.append(" and pcs.enddate<add_months(to_date('");
		sqlBuffer.append(yearMonth);
		sqlBuffer.append("-01','yyyy-mm-dd'),1) ");

	}

	/**
	 * 根据区域编号获取当前月份的到期计划执行结果信息
	 * 
	 * @param regionId
	 *            String 区域编号
	 * @param sqlBuffer
	 *            StringBuffer
	 */
	private void getEndPlanPatrolResult(String regionId, StringBuffer sqlBuffer) {
		sqlBuffer.append(" select ps.planid,vo.regionid,ps.contractorid, ");
		sqlBuffer.append(" ps.executorid,ps.planpoint,ps.factpoint ");
		sqlBuffer.append(" from plan_stat ps ");
		sqlBuffer.append(" join view_org vo on ps.contractorid=vo.id ");
		sqlBuffer.append(" where 1=1 ");
		sqlBuffer.append(" and vo.regionid=any( ");
		sqlBuffer.append(" select regionid from region ");
		sqlBuffer.append(" start with regionid='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("' ");
		sqlBuffer.append(" connect by prior regionid=parentregionid ");
		sqlBuffer.append(" ) ");
		String yearMonth = getYearMonth();
		sqlBuffer.append(" and ps.enddate>=to_date('");
		sqlBuffer.append(yearMonth);
		sqlBuffer.append("-01','yyyy-mm-dd') ");
		sqlBuffer.append(" and ps.enddate<add_months(to_date('");
		sqlBuffer.append(yearMonth);
		sqlBuffer.append("-01','yyyy-mm-dd'),1) ");
	}

	/**
	 * 获取当前的年月输入字串
	 * 
	 * @return
	 */
	private String getYearMonth() {
		Calendar c = Calendar.getInstance();
		String yearMonth = c.get(Calendar.YEAR) + "-"
				+ (c.get(Calendar.MONTH) + 1);
		return yearMonth;
	}
}
