package com.cabletech.business.workflow.electricity.oilengine.model;

import org.apache.commons.lang.StringUtils;

import com.cabletech.common.base.BaseEntity;
import com.cabletech.common.util.Page;

/**
 * 油机信息
 * 
 * @author 王甜
 * @author 杨隽 2012-05-14 添加额定功率最小值和额定功率最大值属性
 * 
 */
public class OilEngine extends BaseEntity  {
	// 系统编号
	private String id;
	// 油机编号
	private String oilengineCode;
	// 油机型号
	private String oilengineModel;
	// 油料类型
	private String oilType;
	// 油机厂商
	private String producer;
	// 额定功率
	private String rationPower;
	// 标准油耗
	private String standardOilwear;
	// 油机类型
	private String oilengineType;
	// 油机重量
	private String oilengineWeight;
	// 油机产权
	private String propertyRight;
	// 油机状态
	private String oilengineState;
	// 所属区县
	private String district;
	// 站点类型
	private String stationType;
	// 所属站点编号
	private String stationId;
	// 所属站点名称
	private String stationName;
	// 位置描述
	private String address;
	// 经纬度
	private String xy;
	// 经度
	private String lon;
	// 纬度
	private String lat;
	// 投影坐标x
	private String ctX;
	// 投影坐标y
	private String ctY;
	// 维护单位
	private String maintenanceId;
	// 负责人
	private String principal;
	// 联系电话
	private String phone;
	// 油机使用状态
	private String state;
	// 备注
	private String remark;
	// 页面信息（用于页面查询）
	@SuppressWarnings("rawtypes")
	private Page page;
	// 额定功率最小值（用于页面查询）
	private String rationPowerMin;
	// 额定功率最大值（用于页面查询）
	private String rationPowerMax;

	@SuppressWarnings("rawtypes")
	public Page getPage() {
		return page;
	}

	@SuppressWarnings("rawtypes")
	public void setPage(Page page) {
		this.page = page;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOilengineCode() {
		return oilengineCode;
	}

	public void setOilengineCode(String oilengine_code) {
		this.oilengineCode = oilengine_code;
	}

	public String getOilengineModel() {
		return oilengineModel;
	}

	public void setOilengineModel(String oilengine_model) {
		this.oilengineModel = oilengine_model;
	}

	public String getOilType() {
		return oilType;
	}

	public void setOilType(String oil_type) {
		this.oilType = oil_type;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getRationPower() {
		return rationPower;
	}

	public void setRationPower(String ration_power) {
		this.rationPower = ration_power;
	}

	public String getStandardOilwear() {
		return standardOilwear;
	}

	public void setStandardOilwear(String standard_oilwear) {
		this.standardOilwear = standard_oilwear;
	}

	public String getOilengineType() {
		return oilengineType;
	}

	public void setOilengineType(String oilengine_type) {
		this.oilengineType = oilengine_type;
	}

	public String getOilengineWeight() {
		return oilengineWeight;
	}

	public void setOilengineWeight(String oilengine_weight) {
		this.oilengineWeight = oilengine_weight;
	}

	public String getPropertyRight() {
		return propertyRight;
	}

	public void setPropertyRight(String property_right) {
		this.propertyRight = property_right;
	}

	public String getOilengineState() {
		return oilengineState;
	}

	public void setOilengineState(String oilengine_state) {
		this.oilengineState = oilengine_state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String station_type) {
		this.stationType = station_type;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String station_id) {
		this.stationId = station_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getXy() {
		return xy;
	}

	/**
	 * 获取的xy坐标分开初始化x、y值
	 * 
	 * @param xy
	 *            获取的xy值
	 */
	public void setXy(String xy) {
		this.xy = xy;
		if (StringUtils.isNotBlank(xy)) {
			String[] coordinate = xy.split(",");
			setLon(coordinate[0]);
			setLat(coordinate[1]);
		}
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getCtX() {
		return ctX;
	}

	public void setCtX(String ct_x) {
		this.ctX = ct_x;
	}

	public String getCtY() {
		return ctY;
	}

	public void setCtY(String ct_y) {
		this.ctY = ct_y;
	}

	public String getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(String maintenance_id) {
		this.maintenanceId = maintenance_id;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String station_name) {
		this.stationName = station_name;
	}

	public String getRationPowerMin() {
		return rationPowerMin;
	}

	public void setRationPowerMin(String rationPowerMin) {
		this.rationPowerMin = rationPowerMin;
	}

	public String getRationPowerMax() {
		return rationPowerMax;
	}

	public void setRationPowerMax(String rationPowerMax) {
		this.rationPowerMax = rationPowerMax;
	}

}
