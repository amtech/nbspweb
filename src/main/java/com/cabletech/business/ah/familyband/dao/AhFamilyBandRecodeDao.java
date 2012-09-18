package com.cabletech.business.ah.familyband.dao;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.ah.familyband.model.AhFamilyBandRecode;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 
 * 家庭宽带巡检登记表DAO
 * @author wj
 *
 */
@Repository
public class AhFamilyBandRecodeDao extends BaseDao<AhFamilyBandRecode, String> {

	/**
	 * 
	 * 按条件检索巡检记录
	 * @param parameters 参数封装
	 * @param page page
	 * @return List 
	 * 
	 */
	public Page searchRecods(Map<String,Object> parameters,Page page){
	
		return super.findSQLPage(page,getcondition(parameters));
	}
	
	/**
	 * 获取记录家庭宽带记录及隐患条数
	 * @param parameters 参数id
	 * @return
	 */
	public Map<String,Object> getRecode(Map<String,Object> parameters){
		
		return super.jdbcTemplate.queryForMap(getcondition(parameters));
	}
	
	/**
	 * 获取条件
	 * @param parameters 参数
	 * @return
	 */
	private String getcondition(Map<String,Object> parameters){
		String orgid = (String) parameters.get("orgid");
		String regionid = (String) parameters.get("regionid"); 
		String creatername = (String) parameters.get("creatername");
		String startTime = (String) parameters.get("starttime");
		String endTime = (String) parameters.get("endtime");
		StringBuffer sb = new StringBuffer();
		sb.append(" select t.id,o.orgname,o.id as orgid,u.username,t.patrolmanid,u.sid as userid,t.range ,t.usernum,to_char(t.starttime,'yyyy-MM-dd HH24:mi:ss') as starttime, ");
		sb.append(" to_char(t.endtime,'yyyy-MM-dd HH24:mi:ss') as endtime, ");
		sb.append(" (select count(1) from ah_familyband_trouble  where recodeid = t.id) as  troublenum, ");
		sb.append(" (select count(1) from ah_familyband_trouble  where recodeid = t.id and status !='1') as  unhandletroublenum ");
		sb.append(" from ah_familyband_recode t ");
		sb.append(" left join view_org o on t.contractorid = o.id ");
		sb.append(" left join view_userinfo u on t.patrolmanid = u.sid ");
		sb.append(" where 1 =1 ");
		if(StringUtils.isNotBlank(orgid)){
			sb.append("and t.contractorid in (select id from view_org start with id = '");
			sb.append(orgid);
			sb.append("' connect by prior id=parentid) ");
		}else{
			if(StringUtils.isNotBlank(regionid)){
				sb.append("and o.regionid in(select regionid from view_region start with regionid = '");
				sb.append(regionid);
				sb.append("' connect by prior regionid=parentid) ");
			}
		}
		if(StringUtils.isNotBlank(creatername)){
			sb.append(" and u.username like '%");
			sb.append(creatername);
			sb.append("%' ");
		}
		if(StringUtils.isNotBlank(startTime)){
			sb.append(" and t.starttime>=to_date('");
			sb.append(startTime);
			sb.append("','yyyy-MM-dd HH24:mi:ss') ");
		}
		if(StringUtils.isNotBlank(endTime)){
			sb.append(" and t.starttime<=to_date('");
			sb.append(endTime);
			sb.append("','yyyy-MM-dd HH24:mi:ss') ");
		}
		if(StringUtils.isNotBlank((String) parameters.get("id"))){
			sb.append("and t.id=  '");
			sb.append((String) parameters.get("id"));
			sb.append("' ");
		}
		sb.append(" order by t.contractorid,t.starttime ");
		return sb.toString();
	}

}