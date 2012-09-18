package com.cabletech.business.assess.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 现场检查-检查结果
 * 
 * @author wj 2012-07-31 创建
 * 
 */
@Repository
public class AssessExaminationDao extends
		BaseDao<AssessExaminationResult, String> {
	/**
	 * 根据条件获取可供选择的模板
	 * 
	 * @param parameter
	 *            查询参数
	 * @return List<Map<String, Object>> 查询数据信息
	 */
	public List<Map<String, Object>> queryAppraiseTables(
			Map<String, String> parameter) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from mm_appraise_table t where t.table_state is null ");
		if(StringUtils.isNotBlank(parameter.get("tableType"))){
			sb.append(" and t.table_type='" + parameter.get("tableType") + "' ");
		}
		if(StringUtils.isNotBlank(parameter.get("tableTypes"))){
			sb.append(" and t.table_type in (" + parameter.get("tableTypes") + ") ");
		}
		return this.getJdbcTemplate().queryForList(sb.toString());
	}

	/**
	 * 查询考核评分结果列表
	 * 
	 * @param parameter
	 *            Map<String,String>
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryResultList(
			Map<String, String> parameter) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" select t.id from mm_examinationresults t ");
		sql.append(" where 1=1 ");
		if (StringUtils.isNotBlank(parameter.get("id"))) {
			sql.append(" and t.id<>'");
			sql.append(parameter.get("id"));
			sql.append("' ");
		}
		if (StringUtils.isNotBlank(parameter.get("contractorId"))) {
			sql.append(" and t.contractor_id='");
			sql.append(parameter.get("contractorId"));
			sql.append("' ");
		}
		if (StringUtils.isNotBlank(parameter.get("regionId"))) {
			sql.append(" and t.region_id='");
			sql.append(parameter.get("regionId"));
			sql.append("' ");
		}
		if (StringUtils.isNotBlank(parameter.get("appraiseMonth"))) {
			sql.append(" and t.appraise_month=to_date('");
			sql.append(parameter.get("appraiseMonth"));
			sql.append("','yyyy-mm-dd') ");
		}
		if (StringUtils.isNotBlank(parameter.get("tableId"))) {
			sql.append(" and t.table_id='");
			sql.append(parameter.get("tableId"));
			sql.append("' ");
		}
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 查询待办工作列表
	 * 
	 * @param parameter
	 *            Map<String, String>
	 * @param page
	 *            Page
	 */
	@SuppressWarnings("all")
	public void queryPage(Map<String, String> parameter, Page page) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT MER.*,MAT.TABLE_NAME,JBPM.TASKID,JBPM.RES AS URL, ");
		sql.append(" TO_CHAR(MER.APPRAISE_MONTH,'yyyy-mm') AS YEAR_MONTH, ");
		sql.append(" TO_CHAR(MER.APPRAISE_MONTH,'yyyy') AS YEAR, ");
		sql.append(" TO_CHAR(SCORE,'FM990.09') AS SCORE_DIS, ");
		sql.append(" VO.NAME AS ORGNAME,VUC.USERNAME AS CREATER, ");
		sql.append(" TO_CHAR(MER.INSPECTION_DATE,'yyyy-mm-dd hh24:mi:ss') AS CREATE_DATE_DIS ");
		sql.append(" FROM MM_EXAMINATIONRESULTS MER ");
		sql.append(" JOIN MM_APPRAISE_TABLE MAT ON MER.TABLE_ID=MAT.ID ");
		sql.append(" JOIN VIEW_ORG VO ON VO.ID=MER.CONTRACTOR_ID ");
		sql.append(" JOIN VIEW_JBPM_USERTASK JBPM ON MER.ID=JBPM.BZID ");
		sql.append(" JOIN VIEW_USERINFO VUC ON VUC.SID=MER.INSPECTORS ");
		sql.append(" WHERE 1=1 ");
		getCondition(parameter, sql);
		if (StringUtils.isNotBlank(parameter.get("userId"))) {
			sql.append(" AND EXISTS( ");
			sql.append(" SELECT BR.ID FROM BASE_ROLE BR ");
			sql.append(" JOIN BASE_USERROLE BUR ON BR.ID=BUR.ROLEID ");
			sql.append(" JOIN VIEW_USERINFO VU ON VU.ID=BUR.USERID ");
			sql.append(" WHERE BR.ROLENAME=JBPM.GROUPID_ ");
			sql.append(" AND VU.SID='");
			sql.append(parameter.get("userId"));
			sql.append("' ");
			sql.append(" UNION ");
			sql.append(" SELECT VU.ID FROM VIEW_USERINFO VU ");
			sql.append(" WHERE JBPM.ASSIGNEE_=VU.SID ");
			sql.append(" AND VU.SID='");
			sql.append(parameter.get("userId"));
			sql.append("' ");
			sql.append(" ) ");
		}
		super.getSQLPageAll(page, sql.toString());
	}

	/**
	 * 查询成绩列表
	 * 
	 * @param parameter
	 *            Map<String, String>
	 * @param page
	 *            Page
	 */
	@SuppressWarnings("all")
	public void queryListPage(Map<String, String> parameter, Page page) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT MER.*,MAT.TABLE_NAME,DIC.LABLE AS BUSINESS_TYPE, ");
		sql.append(" TO_CHAR(MER.APPRAISE_MONTH,'yyyy-mm') AS YEAR_MONTH, ");
		sql.append(" TO_CHAR(MER.APPRAISE_MONTH,'yyyy') AS YEAR, ");
		sql.append(" TO_CHAR(SCORE,'FM990.09') AS SCORE_DIS, ");
		sql.append(" VO.NAME AS ORGNAME,VUC.USERNAME AS CREATER, ");
		sql.append(" TO_CHAR(MER.INSPECTION_DATE,'yyyy-mm-dd hh24:mi:ss') AS CREATE_DATE_DIS ");
		sql.append(" FROM MM_EXAMINATIONRESULTS MER ");
		sql.append(" JOIN MM_APPRAISE_TABLE MAT ON MER.TABLE_ID=MAT.ID ");
		sql.append(" JOIN VIEW_ORG VO ON VO.ID=MER.CONTRACTOR_ID ");
		sql.append(" JOIN VIEW_USERINFO VUC ON VUC.SID=MER.INSPECTORS ");
		sql.append(" JOIN BASE_SYSDICTIONARY DIC ");
		sql.append(" ON DIC.CODEVALUE=MAT.BUSINESS_TYPE AND DIC.COLUMNTYPE='BUSINESSTYPE' ");
		sql.append(" WHERE 1=1 ");
		getCondition(parameter, sql);
		super.getSQLPageAll(page, sql.toString());
	}

	/**
	 * 查询检查结果
	 * 
	 * @param parameters
	 *            查询参数
	 * @param page
	 *            分页参数
	 * @return page
	 */
	@SuppressWarnings("all")
	public Page queryResultPage(Map<String, String> parameters, Page page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select t.id,t.table_id,vo.orgname,mat.table_name,rs.zymc,FN_GETNAMEBYCODE(rs.zdlx,'ZDLX') lable,us.username,t.inspection_date,t.score from mm_examinationresults t ");
		sb.append(" join view_org vo on vo.id = t.contractor_id and vo.orgtype='2' ");
		sb.append(" join mm_appraise_table mat on mat.id = t.table_id ");
		sb.append(" left join res_zdxx rs on rs.xtbh = t.site_id ");
		sb.append(" left join view_userinfo us on us.sid = t.inspectors ");
		sb.append(" where 1=1 ");
		if (StringUtils.isNotBlank(parameters.get("tableType"))) {
			sb.append(" and mat.table_type = '" + parameters.get("tableType")
					+ "' ");
		}
		if (StringUtils.isNotBlank(parameters.get("regionId"))) {
			sb.append(" and t.region_id =any(select regionid from view_region start with  regionid='"
					+ parameters.get("regionId")
					+ "' connect by prior regionid=parentid)");
		}
		if (StringUtils.isNotBlank(parameters.get("orgId"))) {
			sb.append(" and t.contractor_id = '" + parameters.get("orgId")
					+ "' ");
		}
		if (StringUtils.isNotBlank(parameters.get("inspector"))) {
			sb.append(" and t.inspectors = '" + parameters.get("inspector")
					+ "' ");
		}
		if (StringUtils.isNotBlank(parameters.get("stationType"))) {
			sb.append(" and rs.zdlx = '" + parameters.get("stationType") + "' ");
		}
		if (StringUtils.isNotBlank(parameters.get("stationId"))) {
			sb.append(" and site_id = '" + parameters.get("stationId") + "' ");
		}
		if (StringUtils.isNotBlank(parameters.get("startTime"))
				&& StringUtils.isNotBlank(parameters.get("endTime"))) {
			sb.append(" and t.inspection_date between to_date('"
					+ parameters.get("startTime")
					+ "','yyyy-MM-dd') and to_date('"
					+ parameters.get("endTime") + "','yyyy-MM-dd') ");
		}
		sb.append(" order by t.inspection_date ");
		logger.debug("查询检查结果"+sb);
		
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 获取查询条件的sql
	 * 
	 * @param parameter
	 *            Map<String, String>
	 * @param sql
	 *            StringBuffer
	 */
	private void getCondition(Map<String, String> parameter, StringBuffer sql) {
		if (StringUtils.isNotBlank(parameter.get("tableType"))) {
			sql.append(" AND MAT.TABLE_TYPE='");
			sql.append(parameter.get("tableType"));
			sql.append("' ");
		}
		if (StringUtils.isNotBlank(parameter.get("businessType"))) {
			sql.append(" AND MAT.BUSINESS_TYPE='");
			sql.append(parameter.get("businessType"));
			sql.append("' ");
		}
		if (StringUtils.isNotBlank(parameter.get("orgId"))) {
			sql.append(" AND EXISTS(SELECT ID FROM VIEW_ORG WHERE ID=MER.CONTRACTOR_ID START WITH ID='");
			sql.append(parameter.get("orgId"));
			sql.append("' CONNECT BY PRIOR ID=PARENTID) ");
		}
		if (StringUtils.isNotBlank(parameter.get("appraiseMonth"))) {
			sql.append(" AND MER.APPRAISE_MONTH=TO_DATE('");
			sql.append(parameter.get("appraiseMonth"));
			sql.append("','yyyy-mm-dd') ");
		}
		if (StringUtils.isNotBlank(parameter.get("regionId"))) {
			sql.append(" AND EXISTS(SELECT REGIONID FROM VIEW_REGION VR WHERE REGIONID=MER.REGION_ID START WITH REGIONID='");
			sql.append(parameter.get("regionId"));
			sql.append("' CONNECT BY PRIOR REGIONID=PARENTID) ");
		}
	}
	
	/**
	 * 现场检查汇总 统计
	 * @param parameters 查询参数
	 * @return page
	 */
	public Map<String,Object> queryExaminationSummaryList(Map<String,String> parameters){
		StringBuffer sb = new StringBuffer();
		sb.append(" select cit.regionname as city,con.orgname as contractor,cou.regionname as county,ret.nub,ret.score ");
		sb.append(" from ( ");
		sb.append(" select  substr(zd.regionid,0,4)||'00' as city,  t.contractor_id, ");
		sb.append(" zd.regionid as county,  count(zd.xtbh) as nub,  abs(sum(t.score)) as score ");
		sb.append(" from mm_examinationresults t ");
		sb.append(" join mm_appraise_table tab on tab.id = t.table_id ");
		sb.append(" join res_zdxx zd on zd.xtbh = t.site_id ");
		sb.append(" where 1=1 ");
		if(StringUtils.isNotBlank(parameters.get("tableType"))){ 
			sb.append(" and tab.table_type = '"+parameters.get("tableType")+"' ");
		}
		if (StringUtils.isNotBlank(parameters.get("startTime"))&&StringUtils.isNotBlank(parameters.get("endTime"))) {
			sb.append(" and t.inspection_date between to_date('"+parameters.get("startTime")+"','yyyy-mm-dd') and to_date('"+parameters.get("endTime")+"','yyyy-mm-dd') ");
		}
		sb.append(" group by zd.regionid,t.contractor_id ");
		sb.append(" ) ret ");
		sb.append(" left join view_org con on con.id = ret.contractor_id ");
		sb.append(" left join view_region cit on cit.regionid = ret.city ");
		sb.append(" left join view_region cou on cou.regionid = ret.county ");
		sb.append(" where 1=1 ");
		if(StringUtils.isNotBlank(parameters.get("regionId"))){ 
			sb.append(" and ret.city = any(select regionid from view_region start with regionid='"+parameters.get("regionId")+"' connect by prior regionid=parentid) ");
		}
		sb.append(" order by ret.city,ret.contractor_id,ret.county ");
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(sb.toString());
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("sql", sb.toString());
		ret.put("result", list);
		return ret;
	}
}