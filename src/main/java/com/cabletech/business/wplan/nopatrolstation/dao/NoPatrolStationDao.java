package com.cabletech.business.wplan.nopatrolstation.dao;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.wplan.nopatrolstation.model.NoPatrolStation;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.SysConstant;

/**
 * 未巡检站点原因登记Dao
 * 
 * @author 杨隽 2012-07-23 创建
 * 
 */
@Repository
public class NoPatrolStationDao extends BaseDao<NoPatrolStation, String> {
	/**
	 * 获取根据查询条件获取未巡检站点原因登记列表的SQL语句
	 * 
	 * @param noPatrolStation
	 *            NoPatrolStation
	 * @return String
	 */
	public String getNoPatrolStationListSql(NoPatrolStation noPatrolStation) {
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT DISTINCT wn.*,vu.USERNAME AS RECORD_USERNAME, ");
		sqlBuf.append(" to_char(wn.RECORD_DATE,'yyyy-mm-dd hh24:mi:ss') AS RECORD_DATE_DIS, ");
		sqlBuf.append(" rz.ZYMC,wp.PLAN_NAME,dic.LABLE AS PROBLEM_TYPE_DIS ");
		sqlBuf.append(" FROM WPLAN_NOPATROLSTATION wn ");
		sqlBuf.append(" JOIN RES_ZDXX rz ON wn.RESOURCE_ID=rz.XTBH ");
		sqlBuf.append(" JOIN RES_MAINTENANCE rm ON rz.XTBH=rm.RS_ID ");
		sqlBuf.append(" JOIN WPLAN_PATROLINFO wp ON wn.PLAN_ID=wp.ID ");
		sqlBuf.append(" JOIN VIEW_USERINFO vu ON wn.RECORDER=vu.SID ");
		sqlBuf.append(" JOIN BASE_SYSDICTIONARY dic ");
		sqlBuf.append(" ON wn.PROBLEM_TYPE=dic.CODEVALUE ");
		sqlBuf.append(" AND dic.COLUMNTYPE='NOPATROLSTATION_PROBLEM_TYPE' ");
		sqlBuf.append(" WHERE 1=1 ");
		getUserCondition(noPatrolStation, sqlBuf);
		if (StringUtils.isNotBlank(noPatrolStation.getContractorId())) {
			sqlBuf.append(" AND rm.MAINTENANCE_ID='");
			sqlBuf.append(noPatrolStation.getContractorId());
			sqlBuf.append("' ");
		}
		if (StringUtils.isNotBlank(noPatrolStation.getProblemType())) {
			sqlBuf.append(" AND wn.PROBLEM_TYPE='");
			sqlBuf.append(noPatrolStation.getProblemType());
			sqlBuf.append("' ");
		}
		if (StringUtils.isNotBlank(noPatrolStation.getStartDate())) {
			sqlBuf.append(" AND wn.RECORD_DATE>=to_date('");
			sqlBuf.append(noPatrolStation.getStartDate());
			sqlBuf.append("','yyyy-mm-dd') ");
		}
		if (StringUtils.isNotBlank(noPatrolStation.getEndDate())) {
			sqlBuf.append(" AND wn.RECORD_DATE<to_date('");
			sqlBuf.append(noPatrolStation.getEndDate());
			sqlBuf.append("','yyyy-mm-dd')+1 ");
		}
		if (StringUtils.isNotBlank(noPatrolStation.getStationName())) {
			sqlBuf.append(" AND rz.ZYMC LIKE '%");
			sqlBuf.append(noPatrolStation.getStationName());
			sqlBuf.append("%' ");
		}
		if (StringUtils.isNotBlank(noPatrolStation.getPlanName())) {
			sqlBuf.append(" AND wp.PLAN_NAME LIKE '%");
			sqlBuf.append(noPatrolStation.getPlanName());
			sqlBuf.append("%' ");
		}
		if (StringUtils.isNotBlank(noPatrolStation.getRecorder())) {
			sqlBuf.append(" AND vu.USERNAME LIKE '%");
			sqlBuf.append(noPatrolStation.getRecorder());
			sqlBuf.append("%' ");
		}
		if (StringUtils.isNotBlank(noPatrolStation.getProcessState())) {
			sqlBuf.append(" AND wn.PROCESS_STATE='");
			sqlBuf.append(noPatrolStation.getProcessState());
			sqlBuf.append("' ");
		}
		if (NoPatrolStation.IS_CONFIRM_LIST.equals(noPatrolStation.getIfConfirm())) {
			sqlBuf.append(" AND wn.PROCESS_STATE='");
			sqlBuf.append(NoPatrolStation.NO_PROCESS_STATE);
			sqlBuf.append("' ");
		}
		if (StringUtils.isNotBlank(noPatrolStation.getResourceType())) {
			sqlBuf.append(" AND wn.RESOURCE_TYPE='");
			sqlBuf.append(noPatrolStation.getResourceType());
			sqlBuf.append("' ");
		}
		sqlBuf.append(" ORDER BY wn.RECORD_DATE DESC,wn.ID DESC ");
		return sqlBuf.toString();
	}

	/**
	 * 获取根据查询条件获取当前处于巡检状态的站点列表的SQL语句
	 * 
	 * @param noPatrolStation
	 *            NoPatrolStation
	 * @return String
	 */
	public String getStationListSql(NoPatrolStation noPatrolStation) {
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT wpr.*,rz.ZYMC,wp.PLAN_NAME,rz.ZDBH ");
		sqlBuf.append(" FROM WPLAN_PATROLRESOURCE wpr ");
		sqlBuf.append(" JOIN WPLAN_PATROLINFO wp ON wp.ID=wpr.PLAN_ID ");
		sqlBuf.append(" JOIN RES_ZDXX rz ON rz.XTBH=wpr.RESOURCE_ID ");
		sqlBuf.append(" JOIN RES_MAINTENANCE rm ON rz.XTBH=rm.RS_ID ");
		sqlBuf.append(" AND rm.RS_TYPe=wp.BUSINESS_TYPE ");
		sqlBuf.append(" WHERE 1=1 ");
		getUserCondition(noPatrolStation, sqlBuf);
		if (StringUtils.isNotBlank(noPatrolStation.getStartDate())) {
			sqlBuf.append(" AND wp.START_TIME>=to_date('");
			sqlBuf.append(noPatrolStation.getStartDate());
			sqlBuf.append("','yyyy-mm-dd') ");
		}
		if (StringUtils.isNotBlank(noPatrolStation.getEndDate())) {
			sqlBuf.append(" AND wp.START_TIME<to_date('");
			sqlBuf.append(noPatrolStation.getEndDate());
			sqlBuf.append("','yyyy-mm-dd')+1 ");
		}
		if (StringUtils.isNotBlank(noPatrolStation.getStationName())) {
			sqlBuf.append(" AND rz.ZYMC LIKE '%");
			sqlBuf.append(noPatrolStation.getStationName());
			sqlBuf.append("%' ");
		}
		if (StringUtils.isNotBlank(noPatrolStation.getPlanName())) {
			sqlBuf.append(" AND wp.PLAN_NAME LIKE '%");
			sqlBuf.append(noPatrolStation.getPlanName());
			sqlBuf.append("%' ");
		}
		if (StringUtils.isNotBlank(noPatrolStation.getResourceType())) {
			sqlBuf.append(" AND wp.BUSINESS_TYPE='");
			sqlBuf.append(noPatrolStation.getResourceType());
			sqlBuf.append("' ");
		}
		sqlBuf.append(" AND wp.PLAN_STATE='");
		sqlBuf.append(SysConstant.PASSED_STATE);
		sqlBuf.append("' ");
		sqlBuf.append(" AND wp.START_TIME<=sysdate ");
		sqlBuf.append(" AND wp.END_TIME>=sysdate ");
		return sqlBuf.toString();
	}

	/**
	 * 根据当前用户信息获取查询条件
	 * 
	 * @param noPatrolStation
	 *            NoPatrolStation
	 * @param sqlBuf
	 *            StringBuffer
	 */
	private void getUserCondition(NoPatrolStation noPatrolStation,
			StringBuffer sqlBuf) {
		UserInfo user = noPatrolStation.getUser();
		QueryParameter parameter = new QueryParameter();
		parameter.setAlias("rz");
		parameter.setValue(user.getRegionId());
		parameter.setColumnName("REGIONID");
		sqlBuf.append(BusinessConditionUtils.getRegionCondition(parameter));
		if (user.isContractor()) {
			sqlBuf.append(" AND rm.MAINTENANCE_ID='");
			sqlBuf.append(user.getOrgId());
			sqlBuf.append("' ");
		}
	}
}
