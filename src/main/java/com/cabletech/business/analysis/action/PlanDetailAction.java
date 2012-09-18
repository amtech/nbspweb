package com.cabletech.business.analysis.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.analysis.service.PlanResCoverageRateService;
import com.cabletech.business.analysis.service.PollingAccomplishRateService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 计划工作统计明细
 * 
 * @author Administrator
 * 
 */
@Namespace("/analysis")
@Results({
		@Result(name = "rescoverdetail", location = "/analysis/planrescoverage_detail.jsp"),
		@Result(name = "finishratedetail", location = "/analysis/planfinishrate_detail.jsp") })
@Action("/planDetailAction")
public class PlanDetailAction extends BaseAction<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 计划资源覆盖明细
	 */
	private static final String SHOW_RESCOVER_DETAIL = "rescoverdetail";
	/**
	 * 计划资源完成明细
	 */
	private static final String SHOW_FINISH_DETAIL = "finishratedetail";

	/**
	 * 计划巡检完成率
	 */
	@Resource(name = "pollingAccomplishRateServiceImpl")
	private PollingAccomplishRateService pollingAccomplishRateService;
	/**
	 * 计划巡检覆盖率
	 */
	@Resource(name = "planResCoverageRateServiceImpl")
	private PlanResCoverageRateService planResCoverageRateService;

	@Override
	public String getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 转换到计划资源覆盖率分析页面
	 * 
	 * @return
	 */
	public String planrescoverdetail() {
		this.getRequest().setAttribute("paramMap",
				convertObjToJsonStr(this.getParameters()));
		return SHOW_RESCOVER_DETAIL;
	}

	/**
	 * 获取计划已覆盖资源明细
	 */
	@SuppressWarnings("unchecked")
	public void planresincoverlist() {
		Page<Map<String, Object>> page = this.initPage();
		convertObjToJson(planResCoverageRateService.getPlannedResDetailList(
				page, this.getParameters()));
	}

	/**
	 * 获取计划未覆盖资源明细
	 */
	@SuppressWarnings("unchecked")
	public void planresovercoverlist() {
		Page<Map<String, Object>> page = this.initPage();
		convertObjToJson(planResCoverageRateService.getUnPlannedResDetailList(
				page, this.getParameters()));
	}

	/**
	 * 转到巡检完成率明细
	 * 
	 * @return
	 */
	public String planfinishdetail() {
		this.getRequest().setAttribute("paramMap",
				convertObjToJsonStr(this.getParameters()));
		return SHOW_FINISH_DETAIL;
	}

	/**
	 * 巡检完成率已巡检明细
	 */
	@SuppressWarnings("unchecked")
	public void planinfinishlist() {
		Page<Map<String, Object>> page = this.initPage();
		convertObjToJson(pollingAccomplishRateService
				.getPollingAccomplishResDetailList(page, getParameters()));

	}

	/**
	 * 巡检完成率未覆盖站点明细
	 */
	@SuppressWarnings("unchecked")
	public void planoverfinishlist() {
		Page<Map<String, Object>> page = this.initPage();
		convertObjToJson(pollingAccomplishRateService
				.getUnPlannedResDetailList(page, getParameters()));

	}

	/**
	 * 获取查询参数
	 */
	public Map<String, String> getParameters() {
		Map<String, String> map = new HashMap<String, String>();
		UserInfo user = this.getUser();
		if (StringUtils.isNotBlank(this.getParameter("orgId"))) {
			map.put("orgId", this.getParameter("orgId"));
		} else {
			if (user.isContractor()) {
				map.put("orgId", user.getOrgId());
			}
		}
		map.put("patrolId", this.getParameter("patrolId"));
		map.put("regionId", user.getRegionId());
		map.put("resourceType", this.getParameter("resourceType"));
		map.put("startTime", this.getParameter("startTime"));
		map.put("endTime", this.getParameter("endTime"));
		return map;
	}
}