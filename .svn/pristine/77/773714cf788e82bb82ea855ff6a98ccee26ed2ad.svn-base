package com.cabletech.business.assess.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cabletech.business.assess.model.AssessMentDetail;
import com.cabletech.common.base.BaseDao;

/**
 * 考核/检查明细Dao
 * 
 * @author 杨隽 2012-08-03 创建
 * 
 */
@Repository
public class AssessExaminationDetailDao extends
		BaseDao<AssessMentDetail, String> {
	/**
	 * 获取最大深度级别
	 * 
	 * @param map
	 *            表名
	 * @return int
	 */
	public int getMaxLvItem(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer("");
		sb.append("select nvl(max(lv),0) maxlv from (");
		sb.append(getTemplateContentSql(map) + ")");
		String sql = sb.toString();
		logger.debug("获取最大模版级别:" + sql);
		return super.getJdbcTemplate().queryForInt(sql);
	}

	/**
	 * 获取模板项内容
	 * 
	 * @param map
	 *            表格ID
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getTableItemContent(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer("select * from (");
		sb.append(getTemplateContentSql(map));
		sb.append(" order by cid,lv,itempath asc) c where c.childcount=0");
		String sql = sb.toString();
		logger.debug("获取模版内容项:" + sql);
		return super.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 根据考核表ID获取模版内容
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @return
	 */
	private String getTemplateContentSql(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select sys_connect_by_path(m.item_name, '>') itempath,m.id,m.table_id,nvl(m.item_id,'root') item_id,m.item_name,level lv,mc.id as cid,(select count(id) from MM_appraise_item pm where pm.item_id=m.id ) CHILDCOUNT, ");
		sb.append(" mc.name,mc.demand_desc,mc.evaluation_criterion,mc.benchmark_value,mc.challenge_value,mc.weight,mc.id contentid,mad.indicators_value,mad.score,mad.rating_desc from MM_appraise_item m ");
		sb.append(" left join MM_appraise_content mc on m.id=mc.item_id ");
		sb.append(" left join mm_assessmentdetails mad on mad.content_id=mc.id ");
		sb.append(" left join mm_examinationresults mer on mad.result_id=mer.id and m.table_id=mer.table_id ");
		sb.append(" where mer.id='");
		sb.append(map.get("id"));
		sb.append("' ");
		sb.append(" start with m.item_id is null connect by prior m.id=m.item_id ");
		return sb.toString();

	}

	/**
	 * 删除所有考核明细内容
	 * 
	 * @param resultId
	 *            String
	 */
	public void deleteAll(String resultId) {
		String sql = "delete from mm_assessmentdetails where RESULT_ID='"
				+ resultId + "' ";
		super.getJdbcTemplate().execute(sql);
	}
}