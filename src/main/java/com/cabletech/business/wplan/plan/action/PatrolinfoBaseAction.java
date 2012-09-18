package com.cabletech.business.wplan.plan.action;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cabletech.business.wplan.template.service.WplanTemplateService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;

/**
 * 巡检计划基类
 * 
 * @author zhaobi
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 * @param <T>
 * @param <PK>
 */
public abstract class PatrolinfoBaseAction<T, PK extends Serializable> extends
		BaseAction<T, PK> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * “待办页面跳转”常量
	 */
	protected static final String WAIT_HANDLED_PAGE_URL = "/wplan/patrolinfoWaitHandledAction!query.action?isQuery=1&businessType=";
	/**
	 * 巡检模板服务
	 */
	@Resource(name = "wplanTemplateServiceImpl")
	private WplanTemplateService wplanTemplateService;

	/**
	 * 返回计划类型Map
	 */
	protected Map<String, String> getPlanTypeMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", "年度");
		map.put("2", "季度");
		map.put("3", "月份");
		map.put("4", "自定义");
		return map;
	}

	/**
	 * 返回计划年份Map
	 */
	protected Map<String, String> getPlanYearMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		Calendar c = Calendar.getInstance();
		int startyear = c.get(Calendar.YEAR) - 1;
		for (int i = 0; i < 4; i++) {
			int tempYear = startyear + i;
			map.put(Integer.toString(tempYear), tempYear + "年");
		}
		return map;
	}

	/**
	 * 返回季度类型
	 * 
	 * @return
	 */
	protected Map<String, String> getSeasonTypeMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", "第一季度");
		map.put("2", "第二季度");
		map.put("3", "第三季度");
		map.put("4", "第四季度");
		return map;
	}

	/**
	 * 返回年度类型
	 * 
	 * @return
	 */
	protected Map<String, String> getYearTypeMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", "上半年");
		map.put("2", "下半年");
		return map;
	}

	/**
	 * 获取计划模板
	 * 
	 * @param business_type
	 *            专业类型
	 * @param regionId
	 *            String 区域编号
	 * @return
	 */
	protected List<Map<String, Object>> getPlanTemplate(String business_type,
			String regionId) {
		List<Map<String, Object>> list = wplanTemplateService
				.getWplanTemplate(business_type, regionId,
						SysConstant.TEMPLATE_START_USING_STATE);
		return list;
	}

	/**
	 * 设置页面Tag数据
	 * 
	 * @param businessType
	 *            专业类型
	 * @param regionId
	 *            String 区域编号
	 */
	protected void setViewTag(String businessType, String regionId) {
		// 计划模版类型TAG
		this.getRequest().setAttribute("plantemplateMap",
				this.getPlanTemplate(businessType, regionId));
		// 计划类型TAG
		this.getRequest().setAttribute("plantypeMap", this.getPlanTypeMap());
		// 计划年份TAG
		this.getRequest().setAttribute("planyearMap", this.getPlanYearMap());
		// 年类型TAG
		this.getRequest().setAttribute("yearTypeMap", this.getYearTypeMap());
		// 季度类型TAG
		this.getRequest()
				.setAttribute("seasonTypeMap", this.getSeasonTypeMap());
	}

	/**
	 * 设置计划Input页面Tag数据
	 */
	protected void setInputTag() {
		// 计划类型TAG
		this.getRequest().setAttribute("plantypeMap", this.getPlanTypeMap());
		// 计划年份TAG
		this.getRequest().setAttribute("planyearMap", this.getPlanYearMap());
		// 年类型TAG
		this.getRequest().setAttribute("yearTypeMap", this.getYearTypeMap());
		// 季度类型TAG
		this.getRequest()
				.setAttribute("seasonTypeMap", this.getSeasonTypeMap());
	}
}
