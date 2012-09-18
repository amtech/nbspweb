package com.cabletech.business.assess.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.common.base.BaseDao;

/**
 * 月考核汇总Dao
 * 
 * @author zhaobi
 * @date 2012-8-8
 */
@Repository
public class AssessMonthSummaryDao extends BaseDao {
	/**
	 * 获取月汇总数据
	 * 
	 * @param map
	 *            条件
	 * @return
	 */
	public Map<String, Object> getMonthSummary(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer(
				"select r.*,o.orgname,o.REGIONID,o.regionname,t.business_type,fn_getnamebycode(t.business_type,'businesstype') businesstypename ");
		sb.append(" from MM_ExaminationResults  r left join MM_appraise_table t on r.table_id=t.id left join view_org o on r.contractor_id=o.ID where t.table_type='01' and r.state='"+AssessExaminationResult.END_STATE+"' ");
		if (StringUtils.isNotBlank(map.get("regionid").toString())) {
			sb.append(" and o.regionid=any(select regionid from view_region start with regionid='"
					+ map.get("regionid").toString()
					+ "' connect by prior regionid=parentid)");
		}
		if (StringUtils.isNotBlank(map.get("month").toString())) {
			sb.append(" and to_char(r.appraise_month,'yyyy-mm')='"
					+ map.get("month").toString() + "'");
		}
		if (StringUtils.isNotBlank(map.get("orgid").toString())) {
			sb.append(" and r.contractor_id='" + map.get("orgid").toString()
					+ "'");
		}
		sb.append(" order by o.REGIONID,o.regionname,o.orgname asc");
		String sql = sb.toString();
		logger.debug("月考核汇总sql:" + sql);
		List<Map<String, Object>> result = super.jdbcTemplate.queryForList(sql);
		Map<String,Object> queryDatas = new HashMap<String,Object>();
		queryDatas.put("sql", sql);
		queryDatas.put("result", result);
		return queryDatas;
	}

	/**
	 * 获取月度考核排名
	 * 
	 * @param map
	 *            条件
	 * @return
	 */
	public Map<String, Object> getMonthRank(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer(
				"select * from (select r.score,o.REGIONID,o.orgname,o.regionname, row_number() over(partition by regionname order by r.score desc) pm ");
		sb.append(" from MM_ExaminationResults r left join MM_appraise_table t on r.table_id=t.id left join view_org o on r.contractor_id=o.ID ");
		sb.append(" where t.table_type='01' and r.state='"+AssessExaminationResult.END_STATE+"' ");
		if (null!=map.get("businesstype")) {
			sb.append(" and t.business_type='"
					+ map.get("businesstype").toString() + "'");
		}
		if (StringUtils.isNotBlank(map.get("regionid").toString())) {
			sb.append(" and o.regionid=any(select regionid from view_region start with regionid='"
					+ map.get("regionid").toString()
					+ "' connect by prior regionid=parentid)");
		}
		if (StringUtils.isNotBlank(map.get("month").toString())) {
			sb.append(" and to_char(r.appraise_month,'yyyy-mm')='"
					+ map.get("month").toString() + "'");
		}
		sb.append(" group by o.regionname,o.orgname,r.score,o.REGIONID) order by regionid,pm ");
		String sql = sb.toString();
		logger.debug("月考核排名sql:" + sql);
		List<Map<String, Object>> result = super.jdbcTemplate.queryForList(sql);
		Map<String,Object> queryDatas = new HashMap<String,Object>();
		queryDatas.put("sql", sql);
		queryDatas.put("result", result);
		return queryDatas;

	}
}
