package com.cabletech.business.workflow.electricity.oilengine.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.baseinfo.business.util.CoordinateTransformer;
import com.cabletech.business.resource.model.ResourceInfo;
import com.cabletech.business.resource.service.ResourceService;
import com.cabletech.business.workflow.electricity.oilengine.dao.OilEngineManageDao;
import com.cabletech.business.workflow.electricity.oilengine.model.OilEngine;
import com.cabletech.business.workflow.electricity.oilengine.service.OilEngineManageService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.externalresources.ExternalResourcesAccessService;
import com.cabletech.common.util.Page;

/**
 * 油机管理业务实现
 * 
 * @author wangt
 * @author 杨隽 2012-05-15 提取导出公共方法
 * 
 */
@SuppressWarnings({ "rawtypes" })
@Service
public class OilEngineManageServiceImpl extends
		BaseServiceImpl<OilEngine, String> implements OilEngineManageService {
	// 油机管理Dao
	@Resource(name = "oilEngineManageDao")
	private OilEngineManageDao dao;
	// 油机管理业务实现
	@Resource(name = "resourceServiceImpl")
	private ResourceService resourceservice;
	// 外部资源服务业务服务
	@Resource(name = "externalResourcesAccessService")
	private ExternalResourcesAccessService externalResourcesAccessService;

	@SuppressWarnings("unchecked")
	@Override
	protected BaseDao getBaseDao() {
		return dao;
	}

	/**
	 * 查询
	 * 
	 * @param entity
	 *            OilEngine
	 * @param user
	 *            登录用户
	 */
	@Override
	public Page getOilEngineList(OilEngine entity, UserInfo user) {
		return dao.getlist(entity, user);
	}

	@Override
	@Transactional(readOnly = true)
	public long getCodeNumber(String id, String codevalue) {
		return dao.getCodeNumber(id, codevalue);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getOilEngine(String propertyRight,
			String oilengineCode) {
		return dao.getOilEngine(propertyRight, oilengineCode);
	}

	@Override
	@Transactional(readOnly = true)
	public OilEngine viewOilEngine(String id) {
		OilEngine entity = dao.get(id);
		if (StringUtils.isNotBlank(entity.getStationId())) {
			ResourceInfo resource = resourceservice.viewResourceInfo(entity
					.getStationId());
			if (resource != null) {
				entity.setStationName(resource.getResourceName());
			}
		}
		return entity;
	}

	@Override
	@Transactional
	public void saveOilEngine(OilEngine entity) {
		if (StringUtils.isBlank(entity.getId())) {
			entity.setId(null);
			entity.setState(SysConstant.OILENGINEUSE_SPACE);
		}
		if (StringUtils.isNotBlank(entity.getCtX())
				&& StringUtils.isBlank(entity.getLon())) {
			Map<String, Double> map = CoordinateTransformer.transformForMap(
					new Double(entity.getCtX()).doubleValue(), new Double(
							entity.getCtY()).doubleValue(),
					externalResourcesAccessService.getCoordinatetarget(),
					externalResourcesAccessService.getCoordinatesource());
			entity.setLon(map.get("x").toString());
			entity.setLat(map.get("y").toString());
		}
		super.save(entity);
	}

	@Override
	@Transactional
	public void deleteOilEngine(String[] id) {
		if (ArrayUtils.isEmpty(id)) {
			return;
		}
		for (int i = 0; i < id.length; i++) {
			OilEngine entity = dao.get(id[i]);
			entity.setOilengineState(SysConstant.OILENGINEUSE_STATE);
			super.save(entity);
		}
	}

	@Override
	@Transactional
	public void assEngine(String id, String maintenanceId) {
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotBlank(ids[i])) {
				OilEngine oilent = this.viewOilEngine(ids[i]);
				oilent.setMaintenanceId(maintenanceId);
				dao.save(oilent);
			}
		}
	}
}