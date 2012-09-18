package com.cabletech.business.resource.model;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 资源实体
 * 
 * @author Administrator
 * 
 */
public class ResourceInfo {
	/**
	 * 资源退服状态
	 */
	public static final String EXIT_NET_STATE = "AD101";
	/**
	 * 资源已删除状态
	 */
	public static final String DELETED_STATE = "9";
	/**
	 * 用户管辖资源类型map
	 */
	private Map<String, Object> resTypeMap;
	/**
	 * 用户管辖专业类型map
	 */
	private Map<String, Object> businessTypeMap;
	/**
	 * 资源主键
	 */
	private String rsid;
	/**
	 * 站点名称
	 */
	private String rsname;
	/**
	 * 资源类型
	 */
	private String rstype;
	/**
	 * 专业类型
	 */
	private String businessType;
	/**
	 * 资源编号
	 */
	private String stationcode;
	/**
	 * 资源地址
	 */
	private String address;
	/**
	 * 区域ID
	 */
	private String regionid;
	/**
	 * 区域名称
	 */
	private String regionname;
	/**
	 * 组织ID
	 */
	private String orgid;
	/**
	 * 组织名称
	 */
	private String orgname;
	/**
	 * 巡检组ID
	 */
	private String patrolgroupid;
	/**
	 * 巡检组名称
	 */
	private String patrolgroupname;
	/**
	 * ct_x
	 */
	private BigDecimal ct_x;
	/**
	 * ct_y
	 */
	private BigDecimal ct_y;
	/**
	 * lon
	 */
	private BigDecimal lon;
	/**
	 * lat
	 */
	private BigDecimal lat;
	/**
	 * pointid
	 */
	private String pointid;
	/**
	 * resourceName
	 */
	private String resourceName;

	public Map<String, Object> getResTypeMap() {
		return resTypeMap;
	}

	public void setResTypeMap(Map<String, Object> resTypeMap) {
		this.resTypeMap = resTypeMap;
	}

	/**
	 * @return the rsid
	 */
	public String getRsid() {
		return rsid;
	}

	/**
	 * @param rsid
	 *            the rsid to set
	 */
	public void setRsid(String rsid) {
		this.rsid = rsid;
	}

	/**
	 * @return the rsname
	 */
	public String getRsname() {
		return rsname;
	}

	/**
	 * @param rsname
	 *            the rsname to set
	 */
	public void setRsname(String rsname) {
		this.rsname = rsname;
	}

	/**
	 * @return the stationcode
	 */
	public String getStationcode() {
		return stationcode;
	}

	/**
	 * @param stationcode
	 *            the stationcode to set
	 */
	public void setStationcode(String stationcode) {
		this.stationcode = stationcode;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the regionid
	 */
	public String getRegionid() {
		return regionid;
	}

	/**
	 * @param regionid
	 *            the regionid to set
	 */
	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

	/**
	 * @return the regionname
	 */
	public String getRegionname() {
		return regionname;
	}

	/**
	 * @param regionname
	 *            the regionname to set
	 */
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	/**
	 * @return the orgid
	 */
	public String getOrgid() {
		return orgid;
	}

	/**
	 * @param orgid
	 *            the orgid to set
	 */
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	/**
	 * @return the orgname
	 */
	public String getOrgname() {
		return orgname;
	}

	/**
	 * @param orgname
	 *            the orgname to set
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	/**
	 * @return the patrolgroupid
	 */
	public String getPatrolgroupid() {
		return patrolgroupid;
	}

	/**
	 * @param patrolgroupid
	 *            the patrolgroupid to set
	 */
	public void setPatrolgroupid(String patrolgroupid) {
		this.patrolgroupid = patrolgroupid;
	}

	/**
	 * @return the patrolgroupname
	 */
	public String getPatrolgroupname() {
		return patrolgroupname;
	}

	/**
	 * @param patrolgroupname
	 *            the patrolgroupname to set
	 */
	public void setPatrolgroupname(String patrolgroupname) {
		this.patrolgroupname = patrolgroupname;
	}

	/**
	 * @return the ct_x
	 */
	public BigDecimal getCt_x() {
		return ct_x;
	}

	/**
	 * @param ct_x
	 *            the ct_x to set
	 */
	public void setCt_x(BigDecimal ct_x) {
		this.ct_x = ct_x;
	}

	/**
	 * @return the ct_y
	 */
	public BigDecimal getCt_y() {
		return ct_y;
	}

	/**
	 * @param ct_y
	 *            the ct_y to set
	 */
	public void setCt_y(BigDecimal ct_y) {
		this.ct_y = ct_y;
	}

	/**
	 * @return the lon
	 */
	public BigDecimal getLon() {
		return lon;
	}

	/**
	 * @param lon
	 *            the lon to set
	 */
	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	/**
	 * @return the lat
	 */
	public BigDecimal getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	/**
	 * @return the pointid
	 */
	public String getPointid() {
		return pointid;
	}

	/**
	 * @param pointid
	 *            the pointid to set
	 */
	public void setPointid(String pointid) {
		this.pointid = pointid;
	}

	/**
	 * @return the rstype
	 */
	public String getRstype() {
		return rstype;
	}

	/**
	 * @param rstype
	 *            the rstype to set
	 */
	public void setRstype(String rstype) {
		this.rstype = rstype;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Map<String, Object> getBusinessTypeMap() {
		return businessTypeMap;
	}

	public void setBusinessTypeMap(Map<String, Object> businessTypeMap) {
		this.businessTypeMap = businessTypeMap;
	}
}
