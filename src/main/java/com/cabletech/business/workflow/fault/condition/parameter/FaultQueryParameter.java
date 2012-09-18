package com.cabletech.business.workflow.fault.condition.parameter;

import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.common.util.Page;

/**
 * 故障Action的查询参数类
 * 
 * @author 杨隽 2011-10-26 创建
 * @author 杨隽 2011-11-01 添加“查询条件”的参数
 * @author 杨隽 2011-11-02 添加判空方法和清除数据方法
 * @author 杨隽 2011-11-03 添加“故障专业”参数变量
 * @author 杨隽 2011-11-04 修改clear()方法
 * @author 杨隽 2011-11-24 添加“代维单位”、“巡检组”和“资源名称”参数变量
 * @author 杨隽 2012-02-07 添加“分页页面”参数变量
 * @author 杨隽 2012-02-22 添加“派单编号”参数变量
 * @author 杨隽 2012-02-22 添加“故障派单页面的入口来源”参数变量
 * @author 杨隽 2012-02-22 添加“故障派单草稿箱入口来源”常量
 * @author 杨隽 2012-02-23 添加“EMOS单号”参数变量
 * @author 杨隽 2012-07-12 添加“故障站点名称”和“故障站点类型”参数变量
 * 
 */
public class FaultQueryParameter extends QueryParameter {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 故障派单草稿箱入口来源常量
	public static final String DRAFT_ORIGIN = "draft";
	// 故障派单待删除列表入口来源常量
	public static final String WAIT_DELETED_ORIGIN = "waitdeleted";
	// 故障派单编号或者故障告警单编号
	private String id;
	// 故障派单编号
	private String dispatchId;
	// 故障告警单标题
	private String troubleTitle;
	// EMOS单号
	private String eomsId;
	// 故障发生时间的开始值
	private String troubleTimeStart;
	// 故障发生时间的结束值
	private String troubleTimeEnd;
	// 故障发生地点
	private String address;
	// 故障是否紧急
	private String isInstancy;
	// 故障发现方式
	private String findType;
	// 故障站点类型
	private String stationType;
	// 故障站点
	private String stationId;
	// 故障站点名称
	private String stationName;
	// 故障专业类型
	private String businessType;
	// 用于获取资源信息的参数变量
	// 代维单位编号
	private String maintenceId;
	// 巡检组编号
	private String patrolmanId;
	// 资源名称
	private String resourceName;
	// 分页信息数据（列表分页数据，不存储数据库）
	@SuppressWarnings("rawtypes")
	private Page page;
	// 故障派单页面的入口来源
	private String origin = "";

	/**
	 * 
	 */
	public FaultQueryParameter() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}

	public String getTroubleTitle() {
		return troubleTitle;
	}

	public void setTroubleTitle(String troubleTitle) {
		this.troubleTitle = troubleTitle;
	}

	public String getEomsId() {
		return eomsId;
	}

	public void setEomsId(String eomsId) {
		this.eomsId = eomsId;
	}

	public String getTroubleTimeStart() {
		return troubleTimeStart;
	}

	public void setTroubleTimeStart(String troubleTimeStart) {
		this.troubleTimeStart = troubleTimeStart;
	}

	public String getTroubleTimeEnd() {
		return troubleTimeEnd;
	}

	public void setTroubleTimeEnd(String troubleTimeEnd) {
		this.troubleTimeEnd = troubleTimeEnd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsInstancy() {
		return isInstancy;
	}

	public void setIsInstancy(String isInstancy) {
		this.isInstancy = isInstancy;
	}

	public String getFindType() {
		return findType;
	}

	public void setFindType(String findType) {
		this.findType = findType;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getMaintenceId() {
		return maintenceId;
	}

	public void setMaintenceId(String maintenceId) {
		this.maintenceId = maintenceId;
	}

	public String getPatrolmanId() {
		return patrolmanId;
	}

	public void setPatrolmanId(String patrolmanId) {
		this.patrolmanId = patrolmanId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@SuppressWarnings("rawtypes")
	public Page getPage() {
		return page;
	}

	@SuppressWarnings("rawtypes")
	public void setPage(Page page) {
		this.page = page;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * 清空查询条件数据
	 */
	public void clear() {
		setAddress("");
		setFindType("");
		setIsInstancy("");
		setStationId("");
		setTroubleTimeEnd("");
		setTroubleTimeStart("");
		setTroubleTitle("");
		super.clear();
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param parameter
	 *            Object 判断的对象
	 * @return boolean 对象是否为空
	 */
	public static boolean isNull(Object parameter) {
		return (parameter == null);
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
}
