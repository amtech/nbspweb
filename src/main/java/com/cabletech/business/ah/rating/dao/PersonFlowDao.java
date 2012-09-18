package com.cabletech.business.ah.rating.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 
 * 人员流程定义
 * 
 * @author wj
 * @param <T>
 * @param <PK>
 */
@Repository
public class PersonFlowDao<T, PK extends Serializable> extends BaseDao<T, PK> {

	/**
	 * 
	 * 获取考核人员列表
	 * 
	 * @param parameters
	 *            参数封装
	 * @return List
	 * 
	 */
	public List<Map<String, Object>> searchRatingPersons(
			Map<String, Object> parameters) {
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" select u.sid,u.username from view_userinfo u where u.orgtype = '2' ");
		sqlBuf.append(" and u.sid not in(select distinct f.person_id from ah_personflow f ) ");
		sqlBuf.append(getSearchCondition(parameters));
		sqlBuf.append(" order by u.username");
		return this.jdbcTemplate.queryForList(sqlBuf.toString());
	}

	/**
	 * 
	 * 删除人员流程定义列表
	 * 
	 * @param personId
	 *            人员ID
	 * 
	 */
	public void deletePersonFlows(String personId) {
		String deleteSql = " delete ah_personflow t where t.person_id = '"
				+ personId + "' ";
		getJdbcTemplate().update(deleteSql);
	}

	/**
	 * 查询单个人员流程定义
	 * 
	 * @param parameters
	 *            参数封装
	 * @return Map
	 */
	public List<Map<String, Object>> searchPersonFlow(
			Map<String, Object> parameters) {
		String querySql = this.getPersonFlowsSql(parameters);
		return this.jdbcTemplate.queryForList(querySql);
	}

	/**
	 * 查询人员流程定义列表
	 * 
	 * @param parameters
	 *            参数封装
	 * @param page
	 *            分页信息
	 * @return Page 分页列表数据
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page searchPersonFlows(Map<String, Object> parameters, Page page) {
		String querySql = this.getPersonFlowsSql(parameters);
		return super.findSQLPage(page, querySql);
	}

	/**
	 * 查询人员流程定义语句
	 * 
	 * @param parameters
	 *            参数封装
	 * @return List
	 */
	private String getPersonFlowsSql(Map<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select r.person_id,r.processer,u.regionid,u.orgid,u.username,u.JOBINFO ");
		sb.append(" from (select wm_concat('index'||t.flow_num||':'||t.username) as processer,person_id ");
		sb.append(" from (select ap.*,vu.username from ah_personflow ap ");
		sb.append(" left join view_userinfo vu on ap.processer  = vu.sid) t  group by t.person_id) r ");
		sb.append(" left join view_userinfo u on r.person_id = u.sid where 1 = 1");
		sb.append(getSearchCondition(parameters));
		sb.append(" order by r.person_id ");
		return sb.toString();
	}

	/**
	 * 获取查询条件
	 * 
	 * @param parameters
	 *            参数封装
	 * @return List
	 */
	private String getSearchCondition(Map<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		String regionId = (String) parameters.get("regionId");
		String orgId = (String) parameters.get("orgId");
		String businessType = (String) parameters.get("businessType");
		String postOffice = (String) parameters.get("postOffice");
		String personName = (String) parameters.get("personName");
		if (StringUtils.isNotBlank(postOffice)) {
			sb.append(" and u.JOBINFO = '" + postOffice + "' ");
		}
		if (StringUtils.isNotBlank(regionId)) {
			sb.append(" and u.regionid in(select regionid from base_region start with regionid= '"
					+ regionId + "' connect by prior regionid=parentid) ");
		}
		if (StringUtils.isNotBlank(orgId)) {
			sb.append(" and u.orgid in(select id from base_organize start with id= '"
					+ orgId + "' connect by prior id=parentid) ");
		}
		if (StringUtils.isNotBlank(businessType)) {
			sb.append(" and u.orgid in (select r.contractorid from responsible r where r.resourceid = '"
					+ businessType + "') ");
		}
		if (StringUtils.isNotBlank(personName)) {
			sb.append("and u.USERNAME like '%" + personName + "%'");
		}
		return sb.toString();
	}

	/**
	 * 根据人员ID获取办理人员
	 * 
	 * @param parameters
	 *            参数封装
	 * @return
	 */
	public List<Map<String, Object>> searchProcesserByPid(
			Map<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		String personId = (String) parameters.get("personId");
		sb.append(" select t.*,u.username as processername from ah_personflow t ");
		sb.append(" left join view_userinfo u on t.processer = u.sid ");
		sb.append(" where t.person_id = '" + personId
				+ "' order by t.flow_num ");
		return this.jdbcTemplate.queryForList(sb.toString());
	}
}