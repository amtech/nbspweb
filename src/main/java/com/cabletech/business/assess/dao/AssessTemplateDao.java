package com.cabletech.business.assess.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.assess.model.AssessTemplate;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 模版-总体信息Dao
 * 
 * @author 杨隽 2012-07-31 创建
 * 
 */
@Repository
public class AssessTemplateDao extends BaseDao<AssessTemplate, String> {
	
	/**
	 * 分页获取考核模板信息
	 * @param template 模板
	 * @param page     分页参数
	 * @return
	 */
	public Page getAssessTemplate(AssessTemplate template,Page page){
		String sql = getSql(template);
		super.findSQLPage(page, sql.toString());
		return page;
	}
	
	/**
	 * 获取考核模板列表信息
	 * 
	 * @param template
	 *            AssessTemplate
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getAssessTemplate(AssessTemplate template) {
		String sql = getSql(template);
		return super.getSQLALL(sql);
	}

	/**
	 * 根据输入的模板参数信息获取sql语句
	 * 
	 * @param template
	 *            AssessTemplate
	 * @return String
	 */
	private String getSql(AssessTemplate template) {
		StringBuffer sb=new StringBuffer(" select m.*,to_char(m.create_date,'yyyy-mm-dd hh24:mi:ss') as create_date_dis,fn_getnamebycode(m.business_type,'businesstype') business_typename,fn_getnamebycode(m.table_type,'APPRAISE_TABLE_TYPE') TABLE_TYPENAME,u.username from MM_APPRAISE_TABLE M left join view_userinfo u on m.creater=u.sid   ");
		sb.append(" where m.table_state!='9' or m.table_state is null ");
		if (StringUtils.isNotBlank(template.getBusinessType())) {
			sb.append(" and m.BUSINESS_TYPE = '" + template.getBusinessType() + "'");
		}
		if (StringUtils.isNotBlank(template.getTableName())) {
			sb.append(" and m.TABLE_NAME like '%" + template.getTableName() + "%'");
		}
		if (StringUtils.isNotBlank(template.getTableType())) {
			sb.append(" and m.TABLE_TYPE= '" + template.getTableType() + "'");
		}
		if (StringUtils.isNotBlank(template.getTableState())) {
			sb.append(" and m.TABLE_STATE= '" + template.getTableState() + "'");
		}
		String sql = sb.toString() + " order by m.BUSINESS_TYPE";
		logger.debug("获取考核模板信息："+sql);
		return sql;
	}
	
	/**
	 * 删除模板
	 * @param ids 主键
	 */
	public int del(String ids){
		String sql = "update mm_appraise_table set TABLE_STATE='9' where id in(" + ids + ")";
		return super.getJdbcTemplate().update(sql);
	}
}
