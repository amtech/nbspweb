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

/**
 * 计划工作质量分析
 * 
 * @author Administrator
 * 
 */
@Namespace("/analysis")
@Results({
		@Result(name = "rescoverlist", location = "/analysis/planrescoverage_analysis.jsp"),
		@Result(name = "finishratelist", location = "/analysis/planfinishrate_analysis.jsp"),
		@Result(name = "list", location = "/wplan/plan/patrolinfo_list.jsp") })
@Action("/planAnalysisAction")
public class PlanAnalysisAction extends BaseAction<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 计划资源覆盖率
	 */
	private static final String SHOW_RES_COVER = "rescoverlist";
	
	private static final String SHOW_FINISH_RATE="finishratelist";

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
	public String planrescover() {
		return SHOW_RES_COVER;
	}

	/**
	 * 获取计划资源覆盖率数据
	 */
	public void planrescoverlist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", planResCoverageRateService.getMainList(getParameters()));
		convertObjToJson(data);
	}

	/**
	 * 获取计划资源覆盖率数据
	 */
	public void planrescoversublist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", planResCoverageRateService.getSubList(getParameters()));
		convertObjToJson(data);
	}
	
	/**
	 * 转到巡检完成率页面
	 * 
	 * @return
	 */
	public String planfinishrate() {
		return SHOW_FINISH_RATE;
	}

	/**
	 *  巡检完成率主表数据
	 */
	public void planfinishratelist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", pollingAccomplishRateService.getMainList(getParameters()));
		convertObjToJson(data);
	}

	/**
	 * 巡检完成率从表数据
	 */
	public void planfinishratesublist() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", pollingAccomplishRateService.getSubList(getParameters()));
		convertObjToJson(data);
	}
	
	/**
	 * 获取查询参数
	 */
	public Map<String, String> getParameters(){
		Map<String, String> map = new HashMap<String, String>();
		UserInfo user = this.getUser();
		if (StringUtils.isNotBlank(this.getParameter("orgId"))) {
			map.put("orgId", this.getParameter("orgId"));
		} else {
			if (user.isContractor()) {
				map.put("orgId", user.getOrgId());
			}
		}
		map.put("regionId", user.getRegionId());
		map.put("patrolId", this.getParameter("patrolId"));
		map.put("resourceType", this.getParameter("resourceType"));
		map.put("startTime", this.getParameter("startTime"));
		map.put("endTime", this.getParameter("endTime"));
		return map;
	}
}