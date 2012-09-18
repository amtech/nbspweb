package com.cabletech.business.wplan.template.dao;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.condition.BusinessConditionUtils;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.business.wplan.template.model.WplanTemplate;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 计划模板 DAO
 * 
 * @author 汪杰
 * 
 * @author 杨隽 2011-10-25 修改计划模板状态更新方法，添加计划模板复制方法
 */
@Repository
public class WplanTemplateDao extends BaseDao<WplanTemplate, String> {

	/**
	 * 保存
	 * 
	 * @param templateId
	 *            模板ID
	 * @param subItemId
	 *            巡检ID
	 */
	public void saveTemplateSubItem(String templateId, String subItemId) {
		String sql = "insert into wplan_templatesubitem values('" + templateId
				+ "','" + subItemId + "')";
		this.getJdbcTemplate().execute(sql);
	}

	/**
	 * 修改模板状态
	 * 
	 * @param templateId
	 *            模板ID
	 * @param state
	 *            状态
	 */
	public void changeTemplateSubItemState(String templateId, String state) {
		String sql = "update wplan_template set state = '" + state
				+ "' where id = '" + templateId + "'";
		this.getJdbcTemplate().execute(sql);
	}

	/**
	 * 删除子项
	 * 
	 * @param templateId
	 *            模板ID
	 */
	public void removeTemplateSubItem(String templateId) {
		String sql = "delete from wplan_templatesubitem t where t.template_id = '"
				+ templateId + "'";
		this.getJdbcTemplate().execute(sql);
	}

	/**
	 * 获取子项
	 * 
	 * @param templateId
	 *            模板ID
	 */
	public List<Map<String, Object>> getSubItemByTemplate(String templateId) {
		String sql = "select t.*,w.item_id,w.subitem_name,w.quality_standard,i.item_name, "
				+ " (select count(id) from wplan_templatesubitem wts,wplan_patrolsubitem wps where wts.template_id=t.template_id and wts.subitem_id=wps.id and wps.item_id=i.id) as rowspan "
				+ " from  wplan_templatesubitem t "
				+ " left join  wplan_patrolsubitem w on t.subitem_id = w.id  "
				+ " left join wplan_patrolitem i on w.item_id = i.id  "
				+ " where t.template_id = '" + templateId + "' order by W.ITEM_ID ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 查询模板
	 * 
	 * @param businessType
	 *            专业
	 * @param templateNamee
	 *            模板名称
	 * @param user
	 *            UserInfo
	 * @param page
	 *            分页器
	 */
	public Page queryWplanTemplate(String businessType, String templateNamee,
			UserInfo user, Page page) {
		String sql = "select t.*,d.lable as businesstypename,r.regionname,decode(t.state,'"
				+ SysConstant.TEMPLATE_STOP_USING_STATE
				+ "','Y','N') as is_forbidden_state from wplan_template t left join view_sysdictionary d on t.business_type = d.CODEVALUE and d.COLUMNTYPE = 'BUSINESSTYPE' left join view_region r on t.REGIONID=r.regionid where 1=1 ";
		if (StringUtils.isNotBlank(businessType)) {
			sql = sql + "and t.business_type = '" + businessType + "'";
		}
		if (StringUtils.isNotBlank(templateNamee)) {
			sql = sql + "and t.template_name like '%" + templateNamee + "%'";
		}
		// 按区域
		if (StringUtils.isNotBlank(user.getRegionId())) {
			sql = sql
					+ " and t.REGIONID= any(select regionid from view_region start with regionid='"
					+ user.getRegionId()
					+ "' connect by prior regionid=parentid)";
		}
		List<Map<String, Object>> businessTypeList = user.getBusinessTypes();
		String businessTypeStr = "";
		if (!CollectionUtils.isEmpty(businessTypeList)) {
			for (int i = 0; i < businessTypeList.size(); i++) {
				Map<String, Object> map = businessTypeList.get(i);
				businessTypeStr += "'";
				businessTypeStr += map.get("CODEVALUE");
				businessTypeStr += "'";
				if (i < businessTypeList.size() - 1) {
					businessTypeStr += ",";
				}
			}
		}
		sql += " and t.business_type in (" + businessTypeStr + ")";
		sql = sql + " order by t.id";
		super.findSQLPage(page, sql.toString());
		return page;
	}

	/**
	 * 获取无线模板
	 * 
	 * @param businessType
	 *            business_Type
	 * @param regionid
	 *            regionid
	 * @param state
	 *            state
	 * @return
	 */
	public List<Map<String, Object>> getWplanTemplate(String businessType,
			String regionid, String state) {
		String sql = "select w.* from wplan_template w where 1=1 ";
		if (StringUtils.isNotBlank(businessType)) {
			sql = sql + "and w.business_type = '" + businessType + "'";
		}
		if (StringUtils.isNotBlank(state)) {
			sql = sql + "and w.state like '%" + state + "%'";
		}
		// 按区域
		if (StringUtils.isNotBlank(regionid)) {
			sql = sql
					+ " and w.REGIONID= any(select regionid from view_region start with regionid='"
					+ regionid + "' connect by prior regionid=parentid)";
		}
		sql = sql + " order by w.ID";
		return super.getJdbcTemplate().queryForList(sql);

	}
}
