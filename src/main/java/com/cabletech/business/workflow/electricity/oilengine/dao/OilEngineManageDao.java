package com.cabletech.business.workflow.electricity.oilengine.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.oilengine.model.OilEngine;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 获取单个实体信息
 * 
 * @author wangt
 * @author 杨隽 2012-05-14 修改额定功率的查询条件比较
 */
@Repository
public class OilEngineManageDao extends BaseDao<OilEngine, String> {

	/**
	 * 获取查询列表的语句
	 * 
	 * @param entity
	 *            OilEngine
	 * @param user
	 *            登录用户
	 * @return
	 */
	public StringBuffer getSqlBuffer(OilEngine entity, UserInfo user) {
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer
				.append(" select t.id,t.oilengine_code,t.oilengine_model,n1.lable oil_type,t.producer,t.ration_power, ");
		sqlBuffer
				.append(" t.standard_oilwear,t.oilengine_type,t.oilengine_weight,t.property_right, ");
		sqlBuffer
				.append(" t.oilengine_state,r.REGIONNAME district,t.station_type,t.station_id,t.address,t.lon,t.lat, ");
		sqlBuffer
				.append(" t.ct_x,t.ct_y,e.orgname maintenance_id,t.principal,t.phone,d.lable state,t.remark ");
		sqlBuffer
				.append(" from oe_oilengine t left join base_sysdictionary n1 on t.oil_type=n1.codevalue and n1.columntype='OIL_TYPE'");
		sqlBuffer.append(" left join region r on t.district = r.REGIONID");
		sqlBuffer.append(" left join view_org e on e.id=t.maintenance_id");
		sqlBuffer
				.append(" left join base_sysdictionary d on t.state=d.codevalue and d.columntype='OLIENGINE_USE'");
		sqlBuffer.append(" WHERE 1=1 ");
		getCondition(entity, sqlBuffer, user);
		return sqlBuffer;
	}

	/**
	 * 获取查询条件
	 * 
	 * @param entity
	 *            OilEngine
	 * @param sqlBuffer
	 *            StringBuffer
	 * @param user
	 *            UserInfo
	 * @param user
	 */
	private void getCondition(OilEngine entity, StringBuffer sqlBuffer,
			UserInfo user) {
		if (user.isMobile()) {
			sqlBuffer
					.append(" and exists(select 1 from region r1 where r1.regionid=t.district start with r1.REGIONID='");
			sqlBuffer.append(user.getRegionId());
			sqlBuffer
					.append("' connect by prior r1.regionid=r1.PARENTREGIONID)");
		}
		if (user.isContractor()) {
			sqlBuffer
					.append(" and exists(select 1 from view_org r1 where r1.id=t.maintenance_id start with r1.ID='");
			sqlBuffer.append(user.getOrgId());
			sqlBuffer.append("' connect by prior r1.ID=r1.PARENTID)");
		}
		sqlBuffer.append(" and t.oilengine_state!='");
		sqlBuffer.append(SysConstant.OILENGINEUSE_STATE);
		sqlBuffer.append("'");
		if (StringUtils.isNotBlank(entity.getOilengineCode())) {
			sqlBuffer.append(" and t.oilengine_code like '%");
			sqlBuffer.append(entity.getOilengineCode());
			sqlBuffer.append("%'");
		}
		if (StringUtils.isNotBlank(entity.getOilengineModel())) {
			sqlBuffer.append(" and t.oilengine_model like '%");
			sqlBuffer.append(entity.getOilengineModel());
			sqlBuffer.append("%'");
		}
		if (StringUtils.isNotBlank(entity.getOilType())) {
			sqlBuffer.append(" and t.oil_type = '");
			sqlBuffer.append(entity.getOilType());
			sqlBuffer.append("'");
		}
		if (StringUtils.isNotBlank(entity.getRationPowerMin())) {
			sqlBuffer.append(" and t.ration_power >= ");
			sqlBuffer.append(entity.getRationPowerMin());
		}
		if (StringUtils.isNotBlank(entity.getRationPowerMax())) {
			sqlBuffer.append(" and t.ration_power <= ");
			sqlBuffer.append(entity.getRationPowerMax());
		}
		if (StringUtils.isNotBlank(entity.getDistrict())) {
			sqlBuffer.append(" and t.district = '");
			sqlBuffer.append(entity.getDistrict());
			sqlBuffer.append("'");
		}
		if (StringUtils.isNotBlank(entity.getMaintenanceId())) {
			sqlBuffer.append(" and t.maintenance_id = '");
			sqlBuffer.append(entity.getMaintenanceId());
			sqlBuffer.append("'");
		}
		if (StringUtils.isNotBlank(entity.getPrincipal())) {
			sqlBuffer.append(" and t.principal like '%");
			sqlBuffer.append(entity.getPrincipal());
			sqlBuffer.append("%'");
		}
	}

	/**
	 * 获取列表
	 * 
	 * @param entity
	 *            OilEngine
	 * @param user
	 *            登录用户user
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page getlist(OilEngine entity, UserInfo user) {
		StringBuffer sql = getSqlBuffer(entity, user);
		return getSQLPageAll(entity.getPage(), sql.toString());
	}

	/**
	 * 检查编码是否已存在
	 * 
	 * @param id
	 *            要填写编码的数据编号
	 * @param codevalue
	 *            编号
	 * @return
	 */
	public long getCodeNumber(String id, String codevalue) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" select 1 from oe_oilengine t where t.oilengine_code = '");
		sql.append(codevalue);
		sql.append("' ");
		if (StringUtils.isNotBlank(id)) {
			sql.append(" and t.id!='");
			sql.append(id);
			sql.append("' ");
		}
		sql.append(" and t.oilengine_state<>'");
		sql.append(SysConstant.OILENGINEUSE_STATE);
		sql.append("' ");
		return countSQLResult(sql.toString());
	}

	/**
	 * 获取可分配滴油机列表
	 * 
	 * @param propertyRight
	 *            String
	 * @param oilengineCode
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getOilEngine(String propertyRight,
			String oilengineCode) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" select t.id,t.oilengine_code name from oe_oilengine t");
		sql.append(" join view_org vo on vo.id=t.property_right ");
		sql.append(" where 1=1 and vo.orgtype='1' and t.oilengine_state !='");
		sql.append(SysConstant.OILENGINEUSE_STATE);
		sql.append("'");
		if (StringUtils.isNotBlank(propertyRight)) {
			sql.append(" and t.property_right = '");
			sql.append(propertyRight);
			sql.append("'");
		}
		if (StringUtils.isNotBlank(oilengineCode)) {
			sql.append(" and t.oilengine_code like '%");
			sql.append(oilengineCode);
			sql.append("%'");
		}
		sql.append(" and rownum < 50");
		return super.getSQLALL(sql.toString());
	}
}
