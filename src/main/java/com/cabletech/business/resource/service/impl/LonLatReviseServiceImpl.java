package com.cabletech.business.resource.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.resource.model.ResSite;
import com.cabletech.business.resource.service.LonLatReviseService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.base.SysConstant;

/**
 * 坐标校正服务实现
 * 
 * @author zhaobi
 * 
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional
public class LonLatReviseServiceImpl extends BaseServiceImpl implements
		LonLatReviseService {

//	@Resource(name = "pointInfoDao")
//	private PointInfoDao pointInfoDao;
	
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 得到点资源
	 * @param pointinfo 
	 */
	@Transactional(readOnly=true)
	public List<Map<String, Object>> getResourcePoint(ResSite pointinfo) {
		// TODO Auto-generated method stub
//		return pointInfoDao.getResourcePoint(pointinfo);
		return null;
	}

	/**
	 * 修改资源坐标
	 * @param pointinfo 
	 */
	@Transactional
	public void updateCoordinate(ResSite pointinfo) {
		// 获取SRID
		String srid = this.getSRID("POINTINFO");
//		pointInfoDao.updateCoordinate(pointinfo, srid);
		this.updateResoureCoordinate(pointinfo);
	}

	/**
	 * 获取SDE的SRID
	 * @param tablename 表名 
	 */
	@Transactional(readOnly=true)
	public String getSRID(String tablename) {
//		String srid = pointInfoDao.getSRID(tablename);
		return "";
	}

	/**
	 * 更新资源坐标
	 * @param pointinfo 
	 */
	@Transactional
	public void updateResoureCoordinate(ResSite pointinfo) {
//		if (StringUtils.isNotBlank(pointinfo.getId())) {
//			// 主键
//			String id = pointinfo.getId();
//			// 类型
//			String type = pointinfo.getPointtype();
//			BigDecimal lon = pointinfo.getLon();
//			BigDecimal lat = pointinfo.getLat();
//			BigDecimal ct_x = pointinfo.getCt_x();
//			BigDecimal ct_y = pointinfo.getCt_y();
//			if (SysConstant.DB_TABLENAME_RS_BASESTATION.equals(type)) {
//				BaseStation model = baseStationDao.get(id);
//				if (null != model) {
//					model.setLon(lon);
//					model.setLat(lat);
//					model.setCt_x(ct_x);
//					model.setCt_y(ct_y);
//					baseStationDao.save(model);
//				}
//			} else if (SysConstant.DB_TABLENAME_RS_OURDOOR_FACILITIES
//					.equals(type)) {
//				OutdoorFacilities model = outdoorFacilitiesDao.get(id);
//				if (null != model) {
//					model.setLon(lon);
//					model.setLat(lat);
//					model.setCt_x(ct_x);
//					model.setCt_y(ct_y);
//					outdoorFacilitiesDao.save(model);
//				}
//			} else if (SysConstant.DB_TABLENAME_RS_OVERRIDEINFO.equals(type)) {
//				IndoorOverRide model = indoorOverRideDao.get(id);
//				if (null != model) {
//					model.setLon(lon);
//					model.setLat(lat);
//					model.setCt_x(ct_x);
//					model.setCt_y(ct_y);
//					indoorOverRideDao.save(model);
//				}
//			} else if (SysConstant.DB_TABLENAME_RS_REPEATER.equals(type)) {
//				Repeater model = repeaterDao.get(id);
//				if (null != model) {
//					model.setLon(lon);
//					model.setLat(lat);
//					model.setCt_x(ct_x);
//					model.setCt_y(ct_y);
//					repeaterDao.save(model);
//				}
//			} else if (SysConstant.DB_TABLENAME_RS_GROUPCUSTOMER.equals(type)
//					|| SysConstant.DB_TABLENAME_RS_CUSTOMER.equals(type)) {
//				GroupCustomer model = groupCustomerDao.get(id);
//				if (null != model) {
//					model.setLon(lon);
//					model.setLat(lat);
//					model.setCt_x(ct_x);
//					model.setCt_y(ct_y);
//					groupCustomerDao.save(model);
//				}
//			}
//		}
	}
}
