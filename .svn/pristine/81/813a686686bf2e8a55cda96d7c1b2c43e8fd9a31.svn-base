package com.cabletech.business.wplan.patrolitem.condition.parameter;

import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.common.util.Page;

/**
 * 巡检项Action的查询参数类
 * 
 * @author 杨隽 2012-02-14 创建
 * 
 */
public class ItemQueryParameter extends QueryParameter {
	private static final long serialVersionUID = 1L;
	// 只显示启用的巡检项
	public static final String NO_SHOW_ALL = "0";
	// 专业类型
	private String businessType;
	// 区域编号
	private String regionId;
	// 巡检项编号
	private String itemId;
	// 计划模板编号
	private String templateId;
	// 巡检子项状态
	private String state;
	// 是否显示全部巡检子项
	private String showAll;
	// 是否为省用户
	private String isProvince;
	// 分页信息数据（列表分页数据，不存储数据库）
	@SuppressWarnings("rawtypes")
	private Page page;

	/**
	 * 子项查询条件
	 */
	public ItemQueryParameter() {
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getShowAll() {
		return showAll;
	}

	public void setShowAll(String showAll) {
		this.showAll = showAll;
	}

	public String getIsProvince() {
		return isProvince;
	}

	public void setIsProvince(String isProvince) {
		this.isProvince = isProvince;
	}

	@SuppressWarnings("rawtypes")
	public Page getPage() {
		return page;
	}

	@SuppressWarnings("rawtypes")
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * 清空查询条件数据
	 */
	public void clear() {
		if (super.getUser().isProvinceMobile()) {
			setRegionId("");
		}
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
}
