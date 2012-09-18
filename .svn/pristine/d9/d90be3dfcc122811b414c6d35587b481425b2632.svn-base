package com.cabletech.business.sysmanager.dao;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.sysmanager.model.GprsMo;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * GPRS日志查询
 * @author wangt
 *
 */
@SuppressWarnings("rawtypes")
@Repository
public class GprsMoDao extends BaseDao {

	/**
	 * 查询
	 * @param page 
	 * @param parameters 
	 * @return
	 */
	public Page<Map<String, Object>> getQueryList(
			Page<Map<String, Object>> page, Map<String, Object> parameters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select g.keyid,g.content,g.receive_time,p.NAME patrolname,o.ORGNAME orgname,r.regionname from gprs_mo g ")
				.append(" left join base_terminalinfo b on g.deviceid=b.terminalid left join view_patrolgroup p on b.ownerid= p.ID left join view_org o on p.ORGID = o.ID ")
				.append(" left join region r on o.REGIONID = r.REGIONID ")
				.append(" where 1=1 ")
				.append(getCondition(parameters));
		this.logger.info(sql);
		return super.getSQLPageAll(page, sql.toString());
	}

	/**
	 * 封装条件
	 * @param parameters 
	 * @return
	 */
	private String getCondition(Map<String, Object> parameters) {
		StringBuffer sql = new StringBuffer();
		String orgid = (String)parameters.get("orgid");
		String regionid = (String)parameters.get("regionid");
		String patrolid = (String)parameters.get("patrolid");
		if(StringUtils.isNotBlank(patrolid)){
			sql.append(" and b.ownerid = '");
			sql.append(patrolid);
			sql.append("' ");
		}else{
			if(StringUtils.isNotBlank(orgid)){
				sql.append(" and o.orgid in (select id from view_org start with id = '");
				sql.append(orgid);
				sql.append("' connect by prior id=parentid) ");
			}else{
				if(StringUtils.isNotBlank(regionid)){
					sql.append(" and o.regionid in(select regionid from view_region start with regionid = '");
					sql.append(regionid);
					sql.append("' connect by prior regionid=parentid) ");
				}
			}
		}
		
		if (parameters.get("begindate") != null
				&& !"".equals(parameters.get("begindate"))) { // 职位
			sql.append(" and g.receive_time >= to_date('"
					+ parameters.get("begindate") + "','yyyy-MM-dd HH24:mi:ss') ");
		}
		
		if (parameters.get("enddate") != null
				&& !"".equals(parameters.get("enddate"))) {// 考核期间
			sql.append(" and g.receive_time <= to_date('" + parameters.get("enddate")
					+ "','yyyy-MM-dd HH24:mi:ss') ");
		}
		
		return sql.toString();
	}

}
