package com.cabletech.business.desktop.dao;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.cabletech.common.base.BaseDao;

/**
 * 代维资源配备 -- 统计DAO
 * 
 * @author wj
 * @return list
 */
@SuppressWarnings("rawtypes")
@Repository
public class ContractorResEquipDao extends BaseDao {

	/**
	 * 代维资源配备 -- 巡检人员数 维护资源数 车辆数 --按区域分组
	 * 
	 * @param regionId
	 *            String
	 * @return List<Map<String,Obje>>
	 */
	public List<Map<String, Object>> getContractorResEquipListByRegion(
			String regionId) {
		logger.info("代维资源配备  --按区域分组 sql:");
		StringBuffer sqlBuffer = new StringBuffer("");
		setContractorResEquipListByRegionSqlBuffer(regionId, sqlBuffer);
		String sql = sqlBuffer.toString();
		logger.info("代维资源配备  --按区域分组 sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 组装代维资源配备  --按区域分组sql
	 * @param regionId 
	 * @param sqlBuffer 
	 */
	private void setContractorResEquipListByRegionSqlBuffer(String regionId, StringBuffer sqlBuffer) {
		String variable = " from (select * from view_region  r where r.REGIONID=any(select regionid from view_region start with "
				+ " regionid='"
				+ regionId
				+ "' connect by prior regionid=parentid) and lv=3) r "
				+ " left join ";
		sqlBuffer.append("  SELECT RES.ID ,RES.NAME ,nvl(RES.RESNUM,0) as RESNUM ,nvl(PERSON.PERSONNUM,0) as PERSONNUM,nvl(CAR.CARNUM,0) as CARNUM,to_char(round(nvl(LINE.LINENUM/1000,0),2),'FM9999999999999990.00') as LINENUM FROM (");
		sqlBuffer.append(" select r.REGIONID as id,r.REGIONNAME as name,sum(rnum) as RESNUM ");
		sqlBuffer.append(variable);
		sqlBuffer.append(" (select count(rs.Pointid) rnum,rs.REGIONID from  pointinfo rs  group by rs.regionid) n on r.REGIONID=substr (n.REGIONID,0,4)||'00' group by  r.REGIONID,r.REGIONNAME ) RES ");
		sqlBuffer.append("  LEFT JOIN ( ");
		sqlBuffer.append(" select r.REGIONID as id,r.REGIONNAME as name,sum(rnum) PERSONNUM ");
		sqlBuffer.append(variable);
		sqlBuffer.append(" (select count(rs.id) rnum,rs.REGIONID from  view_patrolgroupperson rs where rs.OBJTYPE='MAN' group by rs.regionid) n on r.REGIONID=substr(n.REGIONID,0,4)||'00' group by  r.REGIONID,r.REGIONNAME) PERSON ON RES.ID = PERSON.ID LEFT JOIN ( ");
		sqlBuffer.append("  select r.REGIONID as id,r.REGIONNAME as name,sum(rnum) CARNUM ");
		sqlBuffer.append(variable);
		sqlBuffer.append(" (select count(rs.id) rnum,rs.REGIONID from  car_info rs  group by rs.regionid) n on r.REGIONID=substr(n.REGIONID,0,4)||'00' group by  r.REGIONID,r.REGIONNAME ) CAR ON RES.ID = CAR.ID ");
		sqlBuffer.append(" LEFT JOIN ( ");
		sqlBuffer.append(" select r.REGIONID as id,sum(rnum) LINENUM ");
		sqlBuffer.append(variable);
		sqlBuffer.append(" (select sum(t.shape.len) rnum,t.regionid from sublineinfo t group by t.regionid) n on r.REGIONID=substr(n.REGIONID,0,4)||'00' group by  r.REGIONID,r.REGIONNAME) LINE ON RES.ID = LINE.ID ");
		sqlBuffer.append(" ORDER BY RES.ID ");
	}

	/**
	 * 代维资源配备 -- 巡检人员数 维护资源数 车辆数 --按组织分组
	 * 
	 * @param regionId
	 *            String
	 * @param orgId
	 *            String
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> getContractorResEquipListByOrg(
			String regionId, String orgId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		setContractorResEquipeListByOrg(regionId, orgId, sqlBuffer);
		String sql = sqlBuffer.toString();
		logger.info("代维资源配备  --按组织分组 sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}
	/**
	 * 组装代维资源配备  --按组织分组 sql
	 * @param regionId 
	 * @param orgId 
	 * @param sqlBuffer 
	 */
	private void setContractorResEquipeListByOrg(String regionId, String orgId,
			StringBuffer sqlBuffer) {
		String variable = " where o.REGIONID=any (select regionid from region start with regionid='"
				+ regionId + "' connect by prior regionid=parentregionid) and o.ORGTYPE = '2' ";
		if (StringUtils.isNotBlank(orgId)) {
			variable += " and o.id ='" + orgId + "'";
		}
		variable += " group by o.ID,o.NAME ";
		sqlBuffer.append("SELECT RES.ID ,RES.NAME ,RES.RESNUM ,PERSON.PERSONNUM,CAR.CARNUM,to_char(round(nvl(LINE.LINENUM/1000,0),2),'FM9999999999999990.00') as LINENUM FROM (");
		sqlBuffer.append(" select o.ID,o.NAME,count(rm.rs_id) RESNUM from view_org o ");
		sqlBuffer.append(" left join res_maintenance rm on rm.maintenance_id = o.id ");
		sqlBuffer.append(variable);
		sqlBuffer.append(" ) RES LEFT JOIN( ");
		sqlBuffer.append(" select  o.ID,o.name,count(ods.id) as PERSONNUM from  view_org o ");
		sqlBuffer.append(" left join view_patrolgroupperson ods  on ods.orgid  = o.id and ods.objtype = 'MAN' ");
		sqlBuffer.append(variable);
		sqlBuffer.append(" ) PERSON ON RES.ID = PERSON.ID LEFT JOIN( ");
		sqlBuffer.append(" select o.ID,o.name,count(car.id) as CARNUM from view_org o ");
		sqlBuffer.append(" left join car_info car  on car.contractorid  = o.id ");
		sqlBuffer.append(variable);
		sqlBuffer.append(" ) CAR ON RES.ID = CAR.ID LEFT JOIN( ");
		sqlBuffer.append(" select o.ID,o.name,nvl(sum(line.shape.len),0) as LINENUM from view_org o ");
		sqlBuffer.append(" left join sublineinfo line   on line.contractorid  = o.id ");
		sqlBuffer.append(variable);
		sqlBuffer.append(" ) LINE ON RES.ID = LINE.ID order by ID ");
	}

	/**
	 * 代维人员数 --按区域分组
	 * 
	 * @param regionId
	 *            String
	 * @return List<Map<String,Obje>>
	 */
	public List<Map<String, Object>> getContractorPersonListByRegion(
			String regionId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select r.REGIONID as id,r.REGIONNAME as name,sum(rnum) PERSONNUM ");
		sqlBuffer
				.append(" from (select * from view_region  r where r.REGIONID=any(select regionid from view_region ");
		sqlBuffer.append(" start with  regionid='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("' connect by prior regionid=parentid) and lv=3) r ");
		sqlBuffer.append(" left join  (select count(rs.id) rnum,rs.REGIONID ");
		sqlBuffer
				.append(" from  view_orgdeptstaff rs where rs.ORGTYPE='2' and rs.OBJTYPE='STAFF' group by rs.regionid) n on r.REGIONID=substr(n.REGIONID,0,4)||'00' ");
		sqlBuffer.append(" group by  r.REGIONID,r.REGIONNAME ");
		sqlBuffer.append(" order by r.REGIONID ");
		String sql = sqlBuffer.toString();
		logger.info("代维人员数 --按区域分组 sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 代维人员数 --按组织分组
	 * 
	 * @param regionId
	 *            String
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> getContractorPersonListByOrg(
			String regionId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select  o.ID,o.name,count(ods.id) as PERSONNUM ");
		sqlBuffer.append(" from  view_org o ");
		sqlBuffer
				.append(" left join view_orgdeptstaff ods  on ods.orgid  = o.id and ods.objtype = 'STAFF' ");
		sqlBuffer.append(" where o.REGIONID=any ");
		sqlBuffer.append(" (select regionid from region start with regionid='");
		sqlBuffer.append(regionId);
		sqlBuffer.append("' connect by prior regionid=parentregionid) ");
		sqlBuffer.append(" and o.ORGTYPE = '2' ");
		sqlBuffer.append(" group by o.ID,o.NAME ");
		sqlBuffer.append(" order by o.ID ");
		String sql = sqlBuffer.toString();
		logger.info("代维人员数 --按组织分组 sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 代维人员数 --按巡检组分组
	 * 
	 * @param regionId
	 *            String
	 * @param orgId
	 *            String
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getContractorPersonListByPatrol(
			String regionId, String orgId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append(" select  o.ID,o.name,count(ods.ID) as PERSONNUM ");
		sqlBuffer.append(" from  view_patrolgroup o ");
		sqlBuffer.append(" left join view_patrolgroupperson ods  on ods.PARENTID  = o.id and ods.objtype = 'MAN' ");
		sqlBuffer.append(" where o.REGIONID=any ");
		sqlBuffer.append(" (select regionid from region start with regionid='"
				+ regionId + "' connect by prior regionid=parentregionid) ");
		sqlBuffer.append(" and o.ORGID = '");
		sqlBuffer.append(orgId + "' ");
		sqlBuffer.append(" group by o.ID,o.NAME ");
		sqlBuffer.append(" union ");
		sqlBuffer.append(" select 'nopatrol' as id,'非巡检人员' as name,count(rs.id) as PERSONNUM from view_orgdeptstaff rs where rs.OBJTYPE = 'STAFF' and rs.orgid = '");
		sqlBuffer.append(orgId + "' ");
		sqlBuffer.append(" and rs.id not in( ");
		sqlBuffer.append(" select vp.ID from view_patrolgroupperson vp where vp.objtype = 'MAN' and vp.ORGID = '");
		sqlBuffer.append(orgId + "') ");
		String sql = sqlBuffer.toString();
		logger.info("代维人员数 --按巡检组分组sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 获取在线人员根据区域
	 * 
	 * @param regionid
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getOnlineManByRegionID(String regionid) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select r.REGIONID,r.REGIONNAME,nvl(allmancount,0)allmancount,nvl(olmancount,0) olmancount ");
		sqlBuffer
				.append(" from view_region  r  left join (select count(distinct p.id) allmancount,count(distinct om.patrolman_id) olmancount, p.regionid from ");
		sqlBuffer
				.append(" view_patrolgroupperson p left join onlineman om on p.ID=om.patrolman_id and om.activetime between sysdate - 1/12 and sysdate where p.OBJTYPE='MAN' ");
		sqlBuffer
				.append(" group by p.regionid) d on r.REGIONID=substr(d.REGIONID,0,4)||'00' where  r.REGIONID=any(");
		sqlBuffer
				.append(" select regionid from view_region start with  regionid='"
						+ regionid
						+ "' connect by prior regionid=parentid) and lv=3 ");
		String sql = sqlBuffer.toString();
		logger.info("在线人员数 --按区域分组sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 获取区域组织在线人员
	 * 
	 * @param regionid
	 *            区域
	 * @return
	 */
	public List<Map<String, Object>> getOnlineManOrgByRegionID(String regionid) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select o.ID,o.NAME,nvl(allmancount,0)allmancount,nvl(olmancount,0) olmancount from view_org o ");
		sqlBuffer
				.append(" left join (select count(distinct p.id) allmancount,count(distinct om.patrolman_id) olmancount, p.orgid from view_patrolgroupperson p left join onlineman om ");
		sqlBuffer
				.append(" on p.ID=om.patrolman_id and om.activetime between sysdate - 1/12 and sysdate where p.OBJTYPE='MAN'  group by p.orgid) d");
		sqlBuffer
				.append(" on o.id=d.ORGID where o.orgtype='2' and o.REGIONID='"
						+ regionid + "'");
		String sql = sqlBuffer.toString();
		logger.info("组织在线人员数 --按区域分组sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

	/**
	 * 获取巡检组在线人员数根据组织ID
	 * 
	 * @param orgId
	 *            组织
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getOnlineManGroupByOrgID(String orgId) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select o.ID,o.NAME,nvl(allmancount,0)allmancount,nvl(olmancount,0) olmancount from view_patrolgroup o  ");
		sqlBuffer
				.append("  left join (select count(distinct p.id) allmancount,count(distinct om.patrolman_id) olmancount, p.PARENTID from view_patrolgroupperson p left join onlineman om  ");
		sqlBuffer
				.append(" on p.ID=om.patrolman_id and om.activetime between sysdate - 1/12 and sysdate where p.OBJTYPE='MAN'  group by p.PARENTID) d");
		sqlBuffer.append(" on o.id=d.PARENTID where o.ORGID='" + orgId + "'");
		String sql = sqlBuffer.toString();
		logger.info("巡检组在线人员数 --按巡检组分组sql:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		return list;
	}

}