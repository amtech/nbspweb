package com.cabletech.business.assess.dao;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.cabletech.business.assess.model.AssessAppealForm;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.DateUtil;
import com.cabletech.common.util.Page;

/**
 * 考核管理-申诉记录
 * 
 * @author wj 2012-08-02 创建
 * 
 */
@Repository
@SuppressWarnings("all")
public class AssessAppealFormDao extends BaseDao {
	/**
	 * 查询申诉列表
	 * 
	 * @param parameters
	 *            查询参数
	 * @param page
	 *            分页参数
	 * @return page
	 */
	public Page queryAppealFormList(Map<String, String> parameters, Page page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select t.id,to_char(res.appraise_month,'yyyy')||'年'||to_char(res.appraise_month,'mm')||'月份' as appraisemonth,reg.regionname, ");
		sb.append(" tab.table_name,res.appraise_month,res.score,res.region_id,org.orgname,t.cause,us.username,to_char(t.APPEAL_TIME,'yyyy-mm-dd') as APPEAL_TIME from MM_APPEALFORM t ");
		sb.append(" join mm_examinationresults res on res.id = t.exam_result_id ");
		sb.append(" join mm_appraise_table tab on tab.id = res.table_id ");
		sb.append(" left join view_org org on t.contractor_id = org.id ");
		sb.append(" left join view_userinfo us on us.sid = t.complainant ");
		sb.append(" left join view_region reg on reg.regionid = res.region_id ");
		sb.append(" where 1=1 ");
		if (StringUtils.isNotBlank(parameters.get("appraiseMonth"))) {
			sb.append(" and res.appraise_month = to_date('"
					+ parameters.get("appraiseMonth") + "-01','yyyy-mm-dd') ");
		}
		if (StringUtils.isNotBlank(parameters.get("orgId"))) {
			sb.append(" and t.contractor_id = '" + parameters.get("orgId")
					+ "' ");
		}
		if (StringUtils.isNotBlank(parameters.get("regionId"))) {
			sb.append(" and res.region_id = any(select regionid from view_region start with regionid='"
					+ parameters.get("regionId")
					+ "' connect by prior regionid=parentid) ");
		}
		if (StringUtils.isNotBlank(parameters.get("tableId"))) {
			sb.append(" and res.table_id = '" + parameters.get("tableId")
					+ "' ");
		}
		sb.append(" order by t.APPEAL_TIME ");
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 查询申诉待办列表
	 * 
	 * @param parameters
	 *            查询参数
	 * @param page
	 *            分页参数
	 * @return page
	 */
	public Page queryWaitHandledList(Map<String, String> parameters, Page page) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT TAB.TABLE_NAME, ");
		sb.append(" TO_CHAR(RES.APPRAISE_MONTH,'YYYY')||'年'||TO_CHAR(RES.APPRAISE_MONTH,'MM')||'月份' AS APPRAISE_MONTH, ");
		sb.append(" RES.SCORE,REG.REGIONNAME,ORG.ORGNAME,T.CAUSE,US.USERNAME, ");
		sb.append(" TO_CHAR(T.APPEAL_TIME,'YYYY-MM-DD') AS APPEAL_TIME, ");
		sb.append(" TASK.* FROM MM_APPEALFORM T ");
		sb.append(" JOIN VIEW_JBPM_USERTASK TASK ON TASK.BZID = T.ID ");
		sb.append(" JOIN MM_EXAMINATIONRESULTS RES ON RES.ID = T.EXAM_RESULT_ID ");
		sb.append(" JOIN MM_APPRAISE_TABLE TAB ON TAB.ID = RES.TABLE_ID ");
		sb.append(" LEFT JOIN VIEW_ORG ORG ON T.CONTRACTOR_ID = ORG.ID ");
		sb.append(" LEFT JOIN VIEW_USERINFO US ON US.SID = T.COMPLAINANT ");
		sb.append(" LEFT JOIN VIEW_REGION REG ON REG.REGIONID = RES.REGION_ID ");
		sb.append(" WHERE 1=1 ");
		sb.append(" AND EXISTS( ");
		sb.append(" SELECT BR.ID FROM BASE_ROLE BR ");
		sb.append(" JOIN BASE_USERROLE BUR ON BR.ID=BUR.ROLEID ");
		sb.append(" JOIN VIEW_USERINFO VU ON VU.ID=BUR.USERID ");
		sb.append(" WHERE BR.ROLENAME=TASK.GROUPID_ ");
		sb.append(" AND VU.SID='" + parameters.get("userId") + "' ");
		sb.append(" UNION ");
		sb.append(" SELECT VU.ID FROM VIEW_USERINFO VU ");
		sb.append(" WHERE TASK.ASSIGNEE_=VU.SID ");
		sb.append(" AND VU.SID='" + parameters.get("userId") + "' ");
		sb.append(" ) ");
		return super.findSQLPage(page, sb.toString());
	}

	/**
	 * 查询申诉
	 * 
	 * @param id
	 *            String
	 * @return page
	 */
	public Map<String, Object> queryAppealForm(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select to_char(res.appraise_month,'yyyy')||'年'||to_char(res.appraise_month,'mm')||'月份' as appraisemonth, ");
		sb.append(" t.*,t.score as appealscore,tab.table_name,res.appraise_month,res.score,res.region_id,reg.regionname,org.orgname,us.username,vu.username as reviewername from MM_APPEALFORM t ");
		sb.append(" join mm_examinationresults res on res.id = t.exam_result_id ");
		sb.append(" join mm_appraise_table tab on tab.id = res.table_id ");
		sb.append(" left join view_org org on t.contractor_id = org.id ");
		sb.append(" left join view_userinfo us on us.sid = t.complainant ");
		sb.append(" left join view_region reg on reg.regionid = res.region_id ");
		sb.append(" left join view_userinfo vu on vu.sid = t.reviewer ");
		sb.append(" where t.id = '" + id + "' ");
		return jdbcTemplate.queryForMap(sb.toString());
	}

	/**
	 * 查询考核结果调整
	 * 
	 * @param appealFormId
	 *            String 查询参数
	 */
	public List<Map<String, Object>> queryAdjusstmentList(String appealFormId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from mm_resultadjusstment t where t.appealform_id = '"
				+ appealFormId + "' ");
		return jdbcTemplate.queryForList(sb.toString());
	}

	/**
	 * 删除考核结果调整
	 * 
	 * @param appealFormId
	 *            String 查询参数
	 */
	public void delAdjusstmentByAppealId(String appealFormId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" delete mm_resultadjusstment t where t.appealform_id = '"
				+ appealFormId + "' ");
		this.getJdbcTemplate().update(sb.toString());
	}

	/**
	 * 获取能申诉的月考核结果列表 （查询上个月的月考核列表）
	 * 
	 * @param parameter
	 *            Map<String,String>
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryCanAppealResultList(
			Map<String, String> parameter) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" select to_char(t.appraise_month,'yyyy')||'年'||to_char(t.appraise_month,'mm')||'月份' as appraisemonth, ");
		sql.append(" t.*,org.orgname,reg.regionname,tab.id as tableid,tab.table_name from mm_examinationresults t ");
		sql.append(" left join view_org org on t.contractor_id = org.id ");
		sql.append(" left join view_region reg on t.region_id = reg.regionid ");
		sql.append(" left join mm_appraise_table tab on tab.id = t.table_id ");
		sql.append(" where tab.table_type <>'"
				+ AssessAppealForm.TABLETYPE_EXAMINATION + "' ");
		sql.append(" and (t.appraise_month=to_date('"
				+ DateUtil.getPreviousMonth()
				+ "','yyyy-mm-dd') or tab.table_type = '"
				+ AssessAppealForm.TABLETYPE_YEAR + "') ");
		if (StringUtils.isNotBlank(parameter.get("orgId"))) {
			sql.append(" and t.contractor_id='");
			sql.append(parameter.get("orgId"));
			sql.append("' ");
		}
		// if (StringUtils.isNotBlank(parameter.get("regionId"))) {
		// sql.append(" and t.region_id='");
		// sql.append(parameter.get("regionId"));
		// sql.append("' ");
		// }
		return super.getSQLALL(sql.toString());
	}
}