package com.cabletech.business.assess.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.assess.model.AssessTemplateContent;
import com.cabletech.common.base.BaseDao;

/**
 * 模版-考核内容Dao
 * 
 * @author 杨隽 2012-07-31 创建
 * 
 */
@Repository
public class AssessTemplateContentDao extends
		BaseDao<AssessTemplateContent, String> {

	/**
	 * 获取模板详细内容
	 * 
	 * @param content
	 *            模板内容
	 * @return
	 */
	public Map<String, Object> getTemplateContent(AssessTemplateContent content) {
		StringBuffer sb = new StringBuffer(
				"select c.*,i.item_name,i.table_id from MM_APPRAISE_CONTENT c join MM_APPRAISE_ITEM i on c.item_id=i.id  where 1=1 ");
		if (StringUtils.isNotBlank(content.getItemId())) {
			sb.append(" and c.item_id = '" + content.getItemId() + "'");
		}
		if (StringUtils.isNotBlank(content.getId())) {
			sb.append(" and c.id = '" + content.getId() + "'");
		}
		String sql = sb.toString();
		logger.debug("获取模版详细内容:" + sql);
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql);
		if (CollectionUtils.isEmpty(list)) {
			return new HashMap<String, Object>();
		}
		return list.get(0);
	}
	
	/**
	 * 获取最大深度级别
	 * @param map 表名
	 * @return
	 */
	public int getMaxLvItem(Map<String, Object> map){
		StringBuffer sb=new StringBuffer("");
		sb.append("select nvl(max(lv),0) maxlv from (");
		sb.append(getTemplateContentSql(map)+")");
		String sql=sb.toString(); 
		logger.debug("获取最大模版级别:"+sql);
		return super.getJdbcTemplate().queryForInt(sql);
	}
	
	/**
	 * 获取模板项内容
	 * @param map 表格ID
	 * @return
	 */
	public List<Map<String,Object>> getTableItemContent(Map<String, Object> map){
		StringBuffer sb=new StringBuffer("select * from (");
		sb.append(getTemplateContentSql(map));
		sb.append(" order by cid,lv,itempath asc) c where c.childcount=0");
		String sql=sb.toString(); 
		logger.debug("获取模版内容项:"+sql);
		return super.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 根据考核表ID获取模版内容
	 * 
	 * @param map
	 *            Map<String, Object> 考核表ID
	 * @return String
	 */
	private String getTemplateContentSql(Map<String, Object> map){
		StringBuffer sb=new StringBuffer();
		sb.append(" select sys_connect_by_path(m.item_name, '>') itempath,m.id,m.table_id,nvl(m.item_id,'root') item_id,m.item_name,level lv,mc.id as cid,(select count(id) from MM_appraise_item pm where pm.item_id=m.id ) CHILDCOUNT, ");
		sb.append(" mc.name,mc.demand_desc,mc.evaluation_criterion,mc.benchmark_value,mc.challenge_value,mc.weight,mc.id contentid from MM_appraise_item m ");
		sb.append(" left join MM_appraise_content mc on m.id=mc.item_id ");
		sb.append(" where m.table_id='");
		sb.append(map.get("tableId")+"' ");
		sb.append(" start with m.item_id is null connect by prior m.id=m.item_id ");
		return sb.toString();

	}
	
}
