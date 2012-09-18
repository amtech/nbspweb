package com.cabletech.business.desktop.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseDao;

/**
 * 桌面处理Dao
 * 
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 */
@Repository
@SuppressWarnings("rawtypes")
public class DesktopDao extends BaseDao {

	protected final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 从数据库字典表中获取专业类型列表
	 * 
	 * @param type
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getDictionaryList(String type) {
		String sql = " SELECT CODEVALUE,LABLE FROM BASE_SYSDICTIONARY WHERE COLUMNTYPE='"
				+ type + "' order by sortnum ";
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<Map<String, Object>>();
		}
		return list;
	}

	/**
	 * 根据登录用户获取在线巡检人员列表
	 * 
	 * @param userInfo
	 *            用户信息
	 * @return
	 */
	public List<Map<String, Object>> getOnlineManTreeList(UserInfo userInfo) {
		StringBuffer sqlBuf = getSql(userInfo);
		logger.debug("获取在线人员列表：" + sqlBuf.toString());
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sqlBuf.toString());
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<Map<String, Object>>();
		}
		return list;
	}

	/**
	 * 根据登录用户组织在线巡检人员查询sql语句
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @return
	 */
	@SuppressWarnings("unused")
	public StringBuffer getSql(UserInfo userInfo) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("");
		if (userInfo.isMobile()) {
			getRegionSql(userInfo, sqlBuf);
			sqlBuf.append(" UNION ");
			getContractorSql(userInfo, sqlBuf);
		} else if (userInfo.isContractor()) {
			getContractorSql(userInfo, sqlBuf);
			sqlBuf.append(" UNION ");
			getPatrolmanSql(userInfo, sqlBuf);
			sqlBuf.append(" UNION ");
			 getPersonSql(userInfo, sqlBuf);
			// sqlBuf.append(" UNION ");
			// getSimSql(userInfo, sqlBuf);
		}
		return sqlBuf;
	}

	/**
	 * 根据登录用户获取用户所属区域的查询sql语句
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param sqlBuf
	 *            StringBuffer
	 */
	private void getRegionSql(UserInfo userInfo, StringBuffer sqlBuf) {
		// TODO Auto-generated method stub
		sqlBuf.append(" select r.REGIONID AS ID, r.REGIONNAME AS NAME, nvl(allmancount, 0) AS allmancount, nvl(olmancount, 0) AS olmancount, ");
		sqlBuf.append(" r.PARENTID ,'REGION' AS objtype ");
		sqlBuf.append(" FROM VIEW_REGION r left join (select count(distinct p.id) allmancount,count(distinct om.patrolman_id) olmancount, p.regionid from ");
		sqlBuf.append(" view_patrolgroupperson p left join onlineman om on p.ID=om.patrolman_id and om.activetime between sysdate - 1/12 and sysdate where p.OBJTYPE='MAN' ");
		sqlBuf.append(" and  p.REGIONID = (substr(p.REGIONID, 0, 4) || '00') ");
		sqlBuf.append(" group by p.regionid) d on r.REGIONID=substr(d.REGIONID,0,4)||'00' where  r.REGIONID=any(");
		sqlBuf.append(" select regionid from view_region start with  regionid='"
				+ userInfo.getRegionId()
				+ "' connect by prior regionid=parentid) and lv<=3 ");
	}

	/**
	 * 根据登录用户获取用户所属代维单位的查询sql语句
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param sqlBuf
	 *            StringBuffer
	 */
	private void getContractorSql(UserInfo userInfo, StringBuffer sqlBuf) {
		// TODO Auto-generated method stub
		sqlBuf.append(" select o.ID,o.NAME,nvl(allmancount,0)allmancount,nvl(olmancount,0) olmancount  ");
		if (userInfo.isProvinceContractor()) {
			sqlBuf.append(",o.PARENTID");
		} else {
			sqlBuf.append(",O.REGIONID PARENTID");
		}
		sqlBuf.append(" ,'ORG' AS objtype from view_org o left join (select count(distinct p.id) allmancount,count(distinct om.patrolman_id) olmancount, p.orgid from view_patrolgroupperson p left join onlineman om ");
		sqlBuf.append(" on p.ID=om.patrolman_id and om.activetime between sysdate - 1/12 and sysdate where p.OBJTYPE='MAN'  group by p.orgid) d");
		sqlBuf.append(" on o.id=d.ORGID where o.orgtype='2' ");
		if (userInfo.isProvinceContractor()) {
			sqlBuf.append(" and o.id= any(select id from view_org start with  id='"
					+ userInfo.getOrgId() + "' connect by prior id=parentid)");
		} else if (userInfo.isCityContractor()) {
			sqlBuf.append(" AND o.ID='");
			sqlBuf.append(userInfo.getOrgId());
			sqlBuf.append("' ");
		} else if(userInfo.isProvinceMobile()){
			sqlBuf.append(" and  o.REGIONID!='"
					+ userInfo.getRegionId()
					+ "'");
			sqlBuf.append(" and o.REGIONID=any(select regionid from view_region start with  regionid='"
					+ userInfo.getRegionId()
					+ "' connect by prior regionid=parentid)");
			
		}else {
			sqlBuf.append(" and o.REGIONID=any(select regionid from view_region start with  regionid='"
					+ userInfo.getRegionId()
					+ "' connect by prior regionid=parentid)");
		}
		sqlBuf.append(" and substr(o.regionid,5,6) ='00'  ");
	}

	/**
	 * 根据登录用户获取用户所管理巡检组的查询sql语句
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param sqlBuf
	 *            sqlBuf
	 */
	private void getPatrolmanSql(UserInfo userInfo, StringBuffer sqlBuf) {
		sqlBuf.append(" select o.ID,o.NAME,nvl(allmancount,0) allmancount,nvl(olmancount,0) olmancount,o.PARENTID,'GROUP' AS objtype  from view_patrolgroup o  ");
		sqlBuf.append("  left join (select count(distinct p.id) allmancount,count(distinct om.patrolman_id) olmancount,  p.ORGID,p.PARENTID from view_patrolgroupperson p left join onlineman om  ");
		sqlBuf.append(" on p.ID=om.patrolman_id and om.activetime between sysdate - 1/12 and sysdate where p.OBJTYPE='MAN'  group by  p.PARENTID,p.ORGID) d");
		sqlBuf.append(" on o.id=d.PARENTID where o.REGIONID=any(select regionid from region start with regionid='");
		sqlBuf.append(userInfo.getRegionId());
		sqlBuf.append("' connect by prior regionid=parentregionid) ");
		if (userInfo.isCityContractor()) {
			sqlBuf.append(" AND o.ORGID='");
			sqlBuf.append(userInfo.getOrgId());
			sqlBuf.append("' ");
		}
	}

	/**
	 * 根据登录用户获取用户所管理在线巡检人员的查询sql语句
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param sqlBuf
	 *            StringBuffer
	 */
	private void getPersonSql(UserInfo userInfo, StringBuffer sqlBuf) {
		// TODO Auto-generated method stub
		sqlBuf.append(" SELECT DISTINCT vpgp.ID AS id,vpgp.NAME, 1 allmancount,1 olmancount,");
		sqlBuf.append(" vpgp.PARENTID,'PATROLPERSON' AS objtype");
		sqlBuf.append(" FROM ONLINEMAN om ");
		sqlBuf.append(" JOIN VIEW_PATROLGROUPPERSON vpgp ON om.PATROLMAN_ID=vpgp.ID ");
		sqlBuf.append(" JOIN VIEW_ORGPATROLMAN vop ON vpgp.PARENTID=vop.ID ");
		sqlBuf.append(" JOIN VIEW_ORG vo ON vop.PARENTID=vo.ID");
		sqlBuf.append(" WHERE vpgp.OBJTYPE='MAN' ");
		 sqlBuf.append(" AND om.activetime>=sysdate-2/24 ");
		sqlBuf.append(" AND vo.REGIONID=any(select regionid from region start with regionid='");
		sqlBuf.append(userInfo.getRegionId());
		sqlBuf.append("' connect by prior regionid=parentregionid) ");
		if (userInfo.isCityContractor()) {
			sqlBuf.append(" AND vo.ID='");
			sqlBuf.append(userInfo.getOrgId());
			sqlBuf.append("' ");
		}
	}

	/**
	 * 根据登录用户获取用户所管理在线巡检人员sim卡号的查询sql语句
	 * 
	 * @param userInfo
	 *            UserInfo
	 * @param sqlBuf
	 *            StringBuffer
	 */
	private void getSimSql(UserInfo userInfo, StringBuffer sqlBuf) {
		// TODO Auto-generated method stub
		sqlBuf.append(" SELECT DISTINCT bti.TERMINALID AS id,bti.SIMNUMBER AS TEXT, ");
		sqlBuf.append(" bti.OWNERID AS PARENTID,'PATROLPERSON' AS objtype,6 AS lv, ");
		sqlBuf.append(" DECODE(om.ACTIVETIME,NULL,'0','','0', ");
		sqlBuf.append("   DECODE(SIGN(sysdate-om.ACTIVETIME-2/24),1,'0','1') ");
		sqlBuf.append(" ) AS status ");
		sqlBuf.append(" FROM ONLINEMAN om ");
		sqlBuf.append(" JOIN BASE_TERMINALINFO bti ON om.SIMID=bti.SIMNUMBER ");
		sqlBuf.append(" JOIN VIEW_ORGPATROLMAN vop ON bti.OWNERID=vop.ID ");
		sqlBuf.append(" JOIN VIEW_ORG vo ON vop.PARENTID=vo.ID");
		sqlBuf.append(" WHERE 1=1 ");
		// 必须代维人员为null才取设备
		sqlBuf.append(" and om.patrolman_id is null ");
		// sqlBuf.append(" AND om.activetime>=sysdate-2/24 ");
		sqlBuf.append(" AND vo.REGIONID=any(select regionid from region start with regionid='");
		sqlBuf.append(userInfo.getRegionId());
		sqlBuf.append("' connect by prior regionid=parentregionid) ");
		if (userInfo.isCityContractor()) {
			sqlBuf.append(" AND vo.ID='");
			sqlBuf.append(userInfo.getOrgId());
			sqlBuf.append("' ");
		}

	}

	/**
	 * 获取用户快捷方式
	 * 
	 * @param user
	 *            用户
	 * @return
	 */
	public List<Map<String, Object>> getShortCuts(UserInfo user) {
		String sql = "select m.* from BASE_USER_SHORTCUTS s, base_menu m where s.menu_id=m.id and s.user_id= '"
				+ user.getUserId() + "'";
		logger.debug("查询用户快捷方式：" + sql);
		return this.getJdbcTemplate().queryForList(sql);
	}
}
