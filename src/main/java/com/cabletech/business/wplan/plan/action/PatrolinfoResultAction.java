package com.cabletech.business.wplan.plan.action;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.wplan.plan.service.PatrolinfoResultService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 巡检进度查询页面
 * 
 * @author wj
 * 
 */
@Namespace("/wplan")
@Results({
		@Result(name = "statisticsquery", location = "/wplan/plan/schedule/schedule_statistics_query.jsp"),
		@Result(name = "statisticsregion", location = "/wplan/plan/result/patrolinfo_result_region.jsp"),
		@Result(name = "statisticsorg", location = "/wplan/plan/result/patrolinfo_result_org.jsp"),
		@Result(name = "statisticspatrolgroup", location = "/wplan/plan/result/patrolinfo_result_patrolgroup.jsp"),
		@Result(name = "nopatroldetails", location = "/wplan/plan/result/patrolinfo_result_nopatroldetails.jsp"),
		@Result(name = "statisticsproblemstation", location = "/wplan/plan/result/patrolinfo_result_problemstation.jsp"),
		@Result(name = "statisticsstationerror", location = "/wplan/plan/result/patrolinfo_result_stationerror.jsp"),
		@Result(name = "planlist", location = "/wplan/plan/result/patrolinfo_plan_list.jsp"),
		@Result(name = "orgdetails", location = "/wplan/plan/result/patrolinfo_result_orgdetails.jsp"),
		@Result(name = "patrolgroupdetails", location = "/wplan/plan/result/patrolinfo_result_patrolgroupdetails.jsp"),
		@Result(name = "detailedlist", location = "/wplan/plan/schedule/schedule_detailed_list.jsp") })
@Action("/patrolinfoResultAction")
public class PatrolinfoResultAction extends BaseAction {

	private final static String QUERYTYPE_BYREGION = "byRegion";
	private final static String QUERYTYPE_BYORG = "byOrg";
	private final static String QUERYTYPE_BYPATROLGROUP = "byPatrolGroup";

	/**
	 * 巡检计划信息服务
	 */
	@Resource(name = "patrolinfoResultServiceImpl")
	private PatrolinfoResultService patrolinfoResultService;

	/**
	 * 统计列表
	 */
	public String statisticsList() {
		UserInfo userInfo = super.getUser();

		if (userInfo.isProvinceMobile()) {// 省移动 按区域查询
			return "statisticsregion";
		}
		if (userInfo.isProvinceContractor()) {// 省代维 按组织查询
			return "statisticsorg";
		}
		if (userInfo.isCityMobile()) {// 市移动 按区域查询
			return "statisticsorg";
		}
		if (userInfo.isCityContractor()) {// 市代维 按巡检组查询
			return "statisticspatrolgroup";
		}
		return "";
	}

	/**
	 * 统计列表 数据
	 */
	public void statisticsData() {
		Map<String, Object> parameter = initConditions();
		Page<Map<String, Object>> page = this.initPage();
		parameter.put("user", super.getUser());
		if (QUERYTYPE_BYREGION.equals(getRequest().getParameter("queryType"))) {// 按区域查询
			page = patrolinfoResultService.statisticsByRegion(parameter, page);
		}
		if (QUERYTYPE_BYORG.equals(getRequest().getParameter("queryType"))) {// 按组织查询
			page = patrolinfoResultService.statisticsByOrg(parameter, page);
		}
		if (QUERYTYPE_BYPATROLGROUP.equals(getRequest().getParameter(
				"queryType"))) {// 按巡检组查询
			page = patrolinfoResultService.statisticsByPatrolGroup(parameter,
					page);
		}
		setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 详细列表
	 */
	public String detailedList() {
		getRequest().setAttribute("parameters", initConditions());
		return "detailedlist";
	}

	/**
	 * 未巡检站点明细
	 */
	public String noPatrolDetails() {
		Map<String, Object> parameter = initConditions();
		getRequest().setAttribute("parameters", parameter);
		return "nopatroldetails";
	}

	/**
	 * 未巡检站点明细
	 */
	public void noPatrolDetailsData() {
		Map<String, Object> parameter = initConditions();
		Page<Map<String, Object>> page = this.initPage();
		page = patrolinfoResultService.noPatrolDetails(parameter, page);
		setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 详细列表 数据
	 */
	public void detailedData() {
		Map<String, Object> parameter = initConditions();
		Page<Map<String, Object>> page = this.initPage();
		// page = patrolinfoScheduleService.searchDetailed(parameter, page);
		setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 详细列表
	 */
	public String problemStationList() {
		getRequest().setAttribute("parameters", initConditions());
		return "statisticsproblemstation";
	}

	/**
	 * 详细列表 数据
	 */
	public void problemStationListData() {
		Map<String, Object> parameter = initConditions();
		Page<Map<String, Object>> page = initPage();
		parameter.put("user", super.getUser());
		page = patrolinfoResultService.statisticProblemStation(parameter, page);
		setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 详细列表
	 */
	public String stationErrorList() {
		getRequest().setAttribute("parameters", initConditions());
		return "statisticsstationerror";
	}

	/**
	 * 详细列表 数据
	 */
	public void stationErrorListData() {
		Map<String, Object> parameter = initConditions();
		Page<Map<String, Object>> page = initPage();
		parameter.put("user", super.getUser());
		page = patrolinfoResultService.statisticStationError(parameter, page);
		setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 计划详细列表
	 */
	public String planInfoList() {
		getRequest().setAttribute("parameters", initConditions());
		return "planlist";
	}

	/**
	 * 计划详细列表 数据
	 */
	public void planInfoListData() {
		Map<String, Object> parameter = initConditions();
		Page<Map<String, Object>> page = initPage();
		parameter.put("user", super.getUser());
		page = patrolinfoResultService.getPlanInfo(parameter, page);
		setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 组织巡检明细
	 */
	public String orgDetails() {
		Map<String, Object> parameter = initConditions();
		getRequest().setAttribute("parameters", parameter);
		return "orgdetails";
	}

	/**
	 * 组织巡检站点明细
	 */
	public void orgDetailsData() {
		Map<String, Object> parameter = initConditions();
		Page<Map<String, Object>> page = this.initPage();
		page = patrolinfoResultService.statisticsByOrg(parameter, page);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 巡检组巡检站点明细
	 */
	public String patrolgroupDetails() {
		Map<String, Object> parameter = initConditions();
		getRequest().setAttribute("parameters", parameter);
		return "patrolgroupdetails";
	}

	/**
	 * 巡检组巡检站点明细
	 */
	public void patrolgroupDetailsData() {
		Map<String, Object> parameter = initConditions();
		Page<Map<String, Object>> page = this.initPage();
		page = patrolinfoResultService.statisticsByPatrolGroup(parameter, page);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 封装查询条件
	 * 
	 * @return Map
	 */
	private Map<String, Object> initConditions() {
		HttpServletRequest request = this.getRequest();
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo userInfo = super.getUser();
		String regionId = request.getParameter("regionId");
		String orgId = request.getParameter("orgId");
		String planId = request.getParameter("planId");
		String rsId = request.getParameter("rsId");
		if (StringUtils.isBlank(request.getParameter("regionId"))) {
			regionId = userInfo.getRegionId();
		}
		if (StringUtils.isBlank(request.getParameter("orgId"))) {
			if (userInfo.isContractor()) {
				orgId = userInfo.getOrgId();
			}
		}
		map.put("problemType", request.getParameter("problemType"));
		map.put("businessType", request.getParameter("businessType"));
		map.put("regionId", regionId);
		map.put("orgId", orgId);
		map.put("patrolGroupId", request.getParameter("patrolGroupId"));
		map.put("patrolId", request.getParameter("patrolGroupId"));
		map.put("patrolManId", request.getParameter("patrolManId"));
		map.put("startTime", request.getParameter("startTime"));
		map.put("endTime", request.getParameter("endTime"));
		map.put("rsId", rsId);
		map.put("planId", planId);
		return map;
	}

	@Override
	public Object getModel() {
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
