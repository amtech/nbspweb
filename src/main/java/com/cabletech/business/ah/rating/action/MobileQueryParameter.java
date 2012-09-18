package com.cabletech.business.ah.rating.action;

import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.common.util.Page;

/**
 * 
 * @author wangt
 *
 */
public class MobileQueryParameter extends QueryParameter {
	private static final long serialVersionUID = 1L;
	// 专业类型
	private String businessType;
	// 巡检项编号
	private String itemId;
	// 分页信息数据（列表分页数据，不存储数据库）
	@SuppressWarnings("rawtypes")
	private Page page;

	public String getBusinessType() {
		return businessType;
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

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@SuppressWarnings("rawtypes")
	public Page getPage() {
		return page;
	}

	@SuppressWarnings("rawtypes")
	public void setPage(Page page) {
		this.page = page;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
