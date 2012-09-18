package com.cabletech.business.resource.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.resource.model.ResourceInfo;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.Page;

/**
 * 资源信息Dao
 * 
 * @author zhaobi
 * @author 杨隽 2012-07-25 添加getResourceNum()和updateResourceState()方法
 * 
 */
@Repository
public class ResourceInfoDao extends BaseDao<ResourceInfo, String> {
	/**
	 * 根据资源编号获取资源信息
	 * 
	 * @param resourceId
	 *            String
	 * @return ResourceInfo
	 */
	public ResourceInfo view(String resourceId) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT rz.XTBH,rz.ZYMC,rz.ZDBH FROM RES_ZDXX rz ");
		sql.append(" WHERE rz.XTBH='");
		sql.append(resourceId);
		sql.append("'");
		List<Map<String, Object>> list = super.getJdbcTemplate().queryForList(
				sql.toString());
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		ResourceInfo res = new ResourceInfo();
		Map<String, Object> map = list.get(0);
		res.setRsid((String) map.get("XTBH"));
		res.setStationcode((String) map.get("ZDBH"));
		res.setResourceName((String) map.get("ZYMC"));
		return res;
	}

	/**
	 * 分页获取资源信息
	 * 
	 * @param page
	 *            Page
	 * @param resourceInfo
	 *            资源信息
	 * @return
	 */
	public Page getResourceInfo(ResourceInfo resourceInfo, Page page) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" select v.*,pg.ID patrolgroupid,pg.NAME patrolgroupname,pg.ORGID,pg.ORGNAME from RS_RESOURCERECORD_V v ");
		sql.append(" left join res_maintenance m on v.ID=m.rs_id and v.TYPE=m.rs_type ");
		sql.append(" left join view_patrolgroup pg on pg.ORGID=m.maintenance_id and pg.ID=m.patrol_group_id ");
		sql.append(" where 1=1 ");
		sql.append(getQueryCondition(resourceInfo));
		logger.info("获取资源信息sql:" + sql);
		return super.findSQLPage(page, sql.toString());
	}

	/**
	 * 获取巡检组维护资源数量
	 * 
	 * @param patrolgroupid
	 *            String 巡检组编号
	 * @return int 维护资源数量
	 */
	public int getResourceNum(String patrolgroupid,String busstype) {
		if (StringUtils.isBlank(patrolgroupid)) {
			return 0;
		}
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT COUNT(DISTINCT rz.XTBH) AS NUM ");
		sql.append(" FROM RES_ZDXX rz join res_resourcetype rt on rz.xtbh=rt.xtbh");
		sql.append(" JOIN RES_MAINTENANCE rm ");
		sql.append(" ON rz.XTBH=rm.RS_ID and rt.business_type=rm.rs_type");
		sql.append(" WHERE rm.PATROL_GROUP_ID='");
		sql.append(patrolgroupid);
		sql.append("' ");
		sql.append(" and rt.business_type='");
		sql.append(busstype);
		sql.append("' AND ( rz.status!='9");
		sql.append("' or  rz.status is null)");
		return super.getJdbcTemplate().queryForInt(sql.toString());
	}

	/**
	 * 更改资源的状态
	 * 
	 * @param rsId
	 *            String 资源编号
	 * @param state
	 *            String 资源状态
	 */
	public void updateResourceState(String rsId, String state) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" UPDATE RES_ZDXX SET STATE='");
		sql.append(state);
		sql.append("' WHERE XTBH='");
		sql.append(rsId);
		sql.append("'");
		super.getJdbcTemplate().execute(sql.toString());
	}

	/**
	 * 获取查询条件
	 * 
	 * @param resourceInfo
	 *            资源信息
	 * @return
	 */
	private StringBuffer getQueryCondition(ResourceInfo resourceInfo) {
		StringBuffer sql = new StringBuffer("");
		// 资源名称
		if (StringUtils.isNotBlank(resourceInfo.getRsname())) {
			sql.append(" and v.name like '%" + resourceInfo.getRsname() + "%' ");
		}
		// 资源编号
		if (StringUtils.isNotBlank(resourceInfo.getStationcode())) {
			sql.append(" and v.stationcode like '%" + resourceInfo.getStationcode() + "%' ");
		}
		// 资源地址
		if (StringUtils.isNotBlank(resourceInfo.getAddress())) {
			sql.append(" and v.address like '%" + resourceInfo.getAddress()
					+ "%'");
		}
		// 专业类型
		if (StringUtils.isNotBlank(resourceInfo.getBusinessType())) {
			String[] types = resourceInfo.getBusinessType().split(",");
			String type = "'";
			for (int i = 0; i < types.length; i++) {
				type += types[i] + "','";
			}
			type = type.substring(0, type.length() - 2);
			sql.append(" and v.type in(" + type + ")");
		}
		// 按组织
		if (StringUtils.isNotBlank(resourceInfo.getOrgid())) {
			sql.append(" and pg.ORGID='" + resourceInfo.getOrgid() + "'");
		}
		// 按巡检组
		if (StringUtils.isNotBlank(resourceInfo.getPatrolgroupid())) {
			sql.append(" and pg.ID='" + resourceInfo.getPatrolgroupid() + "'");
		}
		// 按区域
		if (StringUtils.isNotBlank(resourceInfo.getRegionid())) {
			sql.append(" and v.REGIONID= any(select regionid from view_region start with regionid='"
					+ resourceInfo.getRegionid()
					+ "' connect by prior regionid=parentid)");
		}
		// 按用户管辖专业类型
		if (MapUtils.isNotEmpty(resourceInfo.getBusinessTypeMap())) {
			Map<String, Object> map = resourceInfo.getBusinessTypeMap();
			Iterator<String> it = map.keySet().iterator();
			if (it != null) {
				String resType = "";
				while (it.hasNext()) {
					resType += "'";
					resType += it.next();
					resType += "'";
					resType += ",";
				}
				resType = resType.substring(0, resType.length() - 1);
				sql.append(" and v.type in(" + resType + ")");
			}
		}
		return sql;
	}
}
