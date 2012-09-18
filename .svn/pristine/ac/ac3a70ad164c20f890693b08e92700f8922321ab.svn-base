package com.cabletech.business.ah.rating.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.ah.rating.model.PersonRatingForm;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 人员考核表定义
 * 
 * @author wangt
 * 
 */
@Repository
public class PersonRatingFormDao extends BaseDao<PersonRatingForm, String> {

	/**
	 * 查询
	 * 
	 * @param entity
	 *            PersonRatingForm
	 * @param page
	 *            Page
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page queryPage(PersonRatingForm entity, Page page) {
		StringBuffer sql = getSqlBuffer(entity);
		return getSQLPageAll(page, sql.toString());
	}

	/**
	 * 获取Hql查询语句
	 * 
	 * @param entity
	 *            条件
	 * @return
	 */
	private StringBuffer getSqlBuffer(PersonRatingForm entity) {
		StringBuffer buf = new StringBuffer("");
		buf.append(" select t.title,t.use_state,t.id,t.creater,d.lable business_type from ah_ratingform t");
		buf.append(" left join base_sysdictionary d on t.business_type=d.codevalue and d.columntype='BUSINESSTYPE'");
		buf.append(" WHERE 1=1 and t.use_state='1'");
		return buf;
	}

	/**
	 * 获取可分配的人员
	 * 
	 * @param regionid
	 *            String
	 * @param jobtype
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getPersons(String regionid, String jobtype) {
		StringBuffer buf = new StringBuffer("");
		buf.append(" select t.sid id,t.username name from view_userinfo t where t.orgtype = '2' and t.REGIONID in (select r.REGIONID from region r start with r.regionid='");
		buf.append(regionid);
		buf.append("' connect by prior r.regionid=r.PARENTREGIONID) and t.sid not in (select distinct person_id from ah_personratingform) ");
		if (StringUtils.isNotBlank(jobtype)) {
			buf.append("and t.JOBINFO='");
			buf.append(jobtype);
			buf.append("'");
		}
		buf.append(" order by t.username ");
		return this.getSQLALL(buf.toString());
	}

	/**
	 * 批量删除表之前的关系
	 * 
	 * @param tableId
	 *            String
	 */
	public void deleteOldRelationship(String tableId) {
		StringBuffer buf = new StringBuffer("");
		buf.append(" delete PersonRatingForm where table_id=?");
		this.batchHQLExecute(buf.toString(), tableId);
	}

	/**
	 * 获取可分配人员
	 * 
	 * @param regionId
	 *            String
	 * @param jobtype
	 *            String
	 * @param tableid
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getPersonsAssigned(String regionId,
			String jobtype, String tableid) {
		StringBuffer buf = new StringBuffer("");
		buf.append(" select t.sid id,t.username name from view_userinfo t,ah_personratingform p where t.sid=p.person_id and p.table_id='");
		buf.append(tableid);
		buf.append("' ");
		buf.append(" and t.orgtype = '2' and t.REGIONID in (select r.REGIONID from region r start with r.regionid='");
		buf.append(regionId);
		buf.append("' connect by prior r.regionid=r.PARENTREGIONID) ");
		if (StringUtils.isNotBlank(jobtype)) {
			buf.append("and t.JOBINFO='");
			buf.append(jobtype);
			buf.append("'");
		}
		buf.append(" order by t.username ");
		return this.getSQLALL(buf.toString());
	}

}
