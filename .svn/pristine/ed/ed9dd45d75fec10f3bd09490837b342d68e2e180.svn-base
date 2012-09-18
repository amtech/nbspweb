package com.cabletech.business.resource.dao;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.cabletech.business.resource.model.ResourceAllotForm;
import com.cabletech.business.resource.model.RsMaintenance;
import com.cabletech.common.base.BaseDao;

/**
 * 
 * @author wangj 资源分配DAO
 * 
 */
@Repository
public class ResourceAllotDao extends BaseDao<RsMaintenance, String> {

	/**
	 * 根据查询条件获取所有待分配的资源列表
	 * 
	 * @param form
	 *            ResourceAllotForm
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> queryResourceList(ResourceAllotForm form) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT DISTINCT t.xtbh, t.zymc, t.zdbh, t.ymc, t.jtgfmc, t.sx, t.dz, t.dlmc, t.zdlx, t.ywjb, t.cqxz, t.mphm, t.fwjg, t.zydw, t.cz, t.ssglq, t.regionid, t.bz, t.orgid, t.createdate,t.status, t.ZYMC, t.lon, t.lat,t.XTBH AS ID,");
		sql.append(" t.ZDBH||'-'||t.ZYMC||'-'||r.REGIONNAME AS NAME ");
		sql.append(" FROM RES_ZDXX t ");
		sql.append(" JOIN REGION r ON t.REGIONID=r.REGIONID ");
		sql.append(" LEFT JOIN RES_RESOURCETYPE rs_type ON t.XTBH=rs_type.XTBH ");
		sql.append(" LEFT JOIN RES_MAINTENANCE res_m ON res_m.RS_ID=t.XTBH ");
		sql.append(" AND RES_M.RS_TYPE=RS_TYPE.BUSINESS_TYPE ");
		sql.append(" WHERE (t.STATUS IS NULL OR t.STATUS<>'9') ");
		getResourceCondition(form, sql);
		getMaintenanceCondition(form, sql);
		logger.info("获取所有待分配资源：" + sql.toString());
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 根据查询条件获取所有待确认的资源列表
	 * 
	 * @param form
	 *            ResourceAllotForm
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> queryConfirmResourceList(
			ResourceAllotForm form) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT DISTINCT t.xtbh, t.zymc, t.zdbh, t.ymc, t.jtgfmc, t.sx, t.dz, t.dlmc, t.zdlx, t.ywjb, t.cqxz, t.mphm, t.fwjg, t.zydw, t.cz, t.ssglq, t.regionid, t.bz, t.orgid, t.createdate,t.status, t.ZYMC, t.lon, t.lat,t.XTBH AS ID,");
		sql.append(" vo.NAME AS ORG_NAME,vp.NAME AS PATROLGROUP_NAME, ");
		sql.append(" t.ZDBH||'-'||t.ZYMC||'-'||r.REGIONNAME AS NAME ");
		sql.append(" FROM RES_ZDXX t ");
		sql.append(" JOIN REGION r ON t.REGIONID=r.REGIONID ");
		sql.append(" LEFT JOIN RES_RESOURCETYPE rs_type ON t.XTBH=rs_type.XTBH ");
		sql.append(" LEFT JOIN RES_MAINTENANCE res_m ON res_m.RS_ID=t.XTBH ");
		sql.append(" AND RES_M.RS_TYPE=RS_TYPE.BUSINESS_TYPE ");
		sql.append(" LEFT JOIN VIEW_ORG vo ON vo.ID=res_m.MAINTENANCE_ID ");
		sql.append(" LEFT JOIN VIEW_PATROLGROUP vp ON vp.ID=res_m.PATROL_GROUP_ID ");
		sql.append(" WHERE (t.STATUS IS NULL OR t.STATUS<>'9') ");
		sql.append(" AND t.XTBH IN ( ");
		sql.append(arrToSql(form.getNewResources()));
		sql.append(" ) ");
		getResourceCondition(form, sql);
		getMaintenanceCondition(form, sql);
		logger.info("获取所有待确认资源：" + sql.toString());
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 添加维护关系
	 * 
	 * @param form
	 *            ResourceAllotForm
	 */
	public void insertRsMaintenance(ResourceAllotForm form) {
		if (ArrayUtils.isEmpty(form.getNewResources())) {
			return;
		}
		for (int i = 0; i < form.getNewResources().length; i++) {
			RsMaintenance rsMaintenance = new RsMaintenance();
			rsMaintenance.setMaintenanceId(form.getNewMaintenceId());
			rsMaintenance.setPatrolGroupId(form.getNewPatrolmanId());
			rsMaintenance.setRsId(form.getNewResources()[i]);
			rsMaintenance.setRsType(form.getResourceType());
			super.save(rsMaintenance);
		}
	}

	/**
	 * 变更维护关系
	 * 
	 * @param form
	 *            ResourceAllotForm
	 */
	public void updateRsMaintenance(ResourceAllotForm form) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" UPDATE RES_MAINTENANCE SET ");
		sql.append(" MAINTENANCE_ID='");
		sql.append(form.getNewMaintenceId());
		sql.append("', ");
		sql.append(" PATROL_GROUP_ID='");
		sql.append(form.getNewPatrolmanId());
		sql.append("' ");
		sql.append(" WHERE RS_ID IN (");
		sql.append(arrToSql(form.getNewResources()));
		sql.append(") ");
		sql.append(" AND RS_TYPE='");
		sql.append(form.getResourceType());
		sql.append("' ");
		sql.append(" AND MAINTENANCE_ID='");
		sql.append(form.getOldMaintenceId());
		sql.append("' ");
		if (StringUtils.isNotBlank(form.getOldPatrolmanId())) {
			if (form.isNoMaintenancedPatrolgroup()) {
				sql.append(" AND PATROL_GROUP_ID IS NULL ");
			} else {
				sql.append(" AND PATROL_GROUP_ID='");
				sql.append(form.getOldPatrolmanId());
				sql.append("' ");
			}
		}
		logger.info("更新维护关系sql:" + sql.toString());
		super.getJdbcTemplate().execute(sql.toString());
	}

	/**
	 * 删除维护关系
	 * 
	 * @param form
	 *            ResourceAllotForm
	 * @param isSingle
	 *            boolean
	 */
	public void deleteRsMaintenance(ResourceAllotForm form, boolean isSingle) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" DELETE FROM RES_MAINTENANCE ");
		sql.append(" WHERE RS_ID IN ( ");
		sql.append(arrToSql(form.getNewResources()));
		sql.append(" ) ");
		sql.append(" AND RS_TYPE='");
		sql.append(form.getResourceType());
		sql.append("' ");
		if (isSingle) {
			sql.append(" AND MAINTENANCE_ID='");
			sql.append(form.getOldMaintenceId());
			sql.append("' ");
		}
		logger.info("删除维护关系sql:" + sql.toString());
		super.getJdbcTemplate().execute(sql.toString());
	}

	/**
	 * 判断资源是否被分配过
	 * 
	 * @param parameter
	 *            ResourceAllotForm
	 * @return
	 */
	public List<Map<String, Object>> getAllotedSelfResources(
			ResourceAllotForm parameter) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT DISTINCT T.XTBH,T.ZYMC ");
		sql.append(" FROM RES_ZDXX T ");
		sql.append(" JOIN RES_RESOURCETYPE RS_TYPE ON T.XTBH=RS_TYPE.XTBH ");
		sql.append(" JOIN RES_MAINTENANCE RES_M ON RES_M.RS_ID=T.XTBH ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND T.XTBH IN ( ");
		sql.append(arrToSql(parameter.getNewResources()));
		sql.append(" ) ");
		sql.append(" AND RES_M.RS_TYPE='");
		sql.append(parameter.getResourceType());
		sql.append("' ");
		getMaintenanceCondition(parameter, sql);
		logger.info("获取所有分配过资源：" + sql.toString());
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 获取资源信息查询条件
	 * 
	 * @param form
	 *            ResourceAllotForm 检索条件
	 * @param sql
	 *            StringBuffer
	 */
	private void getResourceCondition(ResourceAllotForm form, StringBuffer sql) {
		if (StringUtils.isNotBlank(form.getRegionId())) {
			sql.append(" AND EXISTS (");
			sql.append(" SELECT REGIONID FROM REGION ");
			sql.append(" WHERE t.REGIONID=REGIONID ");
			sql.append(" START WITH REGIONID='");
			sql.append(form.getRegionId());
			sql.append("' ");
			sql.append(" CONNECT BY PRIOR REGIONID=PARENTREGIONID");
			sql.append(" ) ");
		}
		if (StringUtils.isNotBlank(form.getUserRegionId())) {
			sql.append(" AND EXISTS (");
			sql.append(" SELECT REGIONID FROM REGION ");
			sql.append(" WHERE t.REGIONID=REGIONID ");
			sql.append(" START WITH REGIONID='");
			sql.append(form.getUserRegionId());
			sql.append("' ");
			sql.append(" CONNECT BY PRIOR REGIONID=PARENTREGIONID");
			sql.append(" ) ");
		}
		if (StringUtils.isNotBlank(form.getResourceName())) {
			sql.append(" AND (( ");
			sql.append(" t.ZYMC LIKE '%");
			sql.append(form.getResourceName());
			sql.append("%') ");
			sql.append(" OR (");
			sql.append(" t.XTBH LIKE '%");
			sql.append(form.getResourceName());
			sql.append("%')) ");
		}
		if (StringUtils.isNotBlank(form.getResourceType())) {
			sql.append(" AND rs_type.BUSINESS_TYPE = '");
			sql.append(form.getResourceType());
			sql.append("' ");
		}
	}

	/**
	 * 获取资源的维护查询条件
	 * 
	 * @param form
	 *            ResourceAllotForm 检索条件
	 * @param sql
	 *            StringBuffer
	 */
	private void getMaintenanceCondition(ResourceAllotForm form,
			StringBuffer sql) {
		getContractorCondition(form, sql);
		getPatrolgroupCondition(form, sql);
	}

	/**
	 * 获取维护关系的代维公司查询条件
	 * 
	 * @param form
	 *            ResourceAllotForm 检索条件
	 * @param sql
	 *            StringBuffer
	 */
	private void getContractorCondition(ResourceAllotForm form, StringBuffer sql) {
		if (StringUtils.isBlank(form.getOldMaintenceId())) {
			return;
		}
		if (form.isNoMaintenancedContractor()) {
			sql.append(" AND res_m.MAINTENANCE_ID IS NULL ");
		} else {
			sql.append(" AND EXISTS (");
			sql.append(" SELECT ID  FROM VIEW_ORG ");
			sql.append(" WHERE res_m.MAINTENANCE_ID=ID ");
			sql.append(" START WITH ID='");
			sql.append(form.getOldMaintenceId());
			sql.append("' ");
			sql.append(" CONNECT BY PRIOR ID=PARENTID) ");
		}
	}

	/**
	 * 获取维护关系的维护组查询条件
	 * 
	 * @param form
	 *            ResourceAllotForm 检索条件
	 * @param sql
	 *            StringBuffer
	 */
	private void getPatrolgroupCondition(ResourceAllotForm form,
			StringBuffer sql) {
		if (StringUtils.isBlank(form.getOldPatrolmanId())) {
			return;
		}
		if (form.isNoMaintenancedPatrolgroup()) {
			sql.append(" AND res_m.PATROL_GROUP_ID IS NULL ");
		} else {
			sql.append(" AND res_m.PATROL_GROUP_ID = '");
			sql.append(form.getOldPatrolmanId());
			sql.append("' ");
		}
	}

	/**
	 * 私有方法 构造SQL，逗号分隔符
	 * 
	 * @param arr
	 *            字符串数组
	 * @return
	 */
	private String arrToSql(String[] arr) {
		StringBuffer sbu = new StringBuffer();
		String ret = "";
		if (arr == null)
			return "''";
		if (arr.length == 0)
			return "''";
		for (String s : arr) {
			sbu.append("'" + s + "',");
		}
		if (sbu.length() > 0) {
			ret = sbu.substring(0, sbu.length() - 1);
		}
		return ret;
	}
}