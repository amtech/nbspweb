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
import com.cabletech.business.wplan.plan.service.PatrolinfoScheduleService;
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
		@Result(name = "statisticsregion", location = "/wplan/plan/schedule/schedule_statistics_region.jsp"),
		@Result(name = "statisticsorg", location = "/wplan/plan/schedule/schedule_statistics_org.jsp"),
		@Result(name = "statisticspatrolgroup", location = "/wplan/plan/schedule/schedule_statistics_patrolgroup.jsp"),
		@Result(name = "statisticspatrolman", location = "/wplan/plan/schedule/schedule_statistics_patrolman.jsp"),
		@Result(name = "detailedlist", location = "/wplan/plan/schedule/schedule_detailed_list.jsp")
})
@Action("/patrolinfoScheduleAction")
public class PatrolinfoScheduleAction extends BaseAction {
	
	private final static String QUERYTYPE_BYREGION = "byRegion";
	private final static String QUERYTYPE_BYORG = "byOrg";
	private final static String QUERYTYPE_BYPATROLGROUP = "byPatrolGroup";
	private final static String QUERYTYPE_BYPATROLMAN = "byPatrolMan";

	/**
	 * 巡检计划信息服务
	 */
	@Resource(name = "patrolinfoScheduleServiceImpl")
	private PatrolinfoScheduleService patrolinfoScheduleService;
	
	
	/**
	 * 统计列表
	 */
	public String statisticsQuery() {
		return "statisticsquery";
	}
	
	/**
	 * 统计列表
	 */
	public String statisticsList() {
		Map<String, Object> parameters = initConditions();
		String queryType = (String)parameters.get("queryType");
		getRequest().setAttribute("parameters", parameters);
		if(QUERYTYPE_BYREGION.equals(queryType)){//按区域查询
			return "statisticsregion";
		}
		if(QUERYTYPE_BYORG.equals(queryType)){//按组织查询
			return "statisticsorg";
		}
		if(QUERYTYPE_BYPATROLGROUP.equals(queryType)){//按巡检组查询
			return "statisticspatrolgroup";
		}
		if(QUERYTYPE_BYPATROLMAN.equals(queryType)){//按巡检员查询
			return "statisticspatrolman";
		}
		return "";
	}

	
	/**
	 * 统计列表 数据
	 */
	public void statisticsData() {
		Map<String, Object> parameter = initConditions();
		String queryType = (String)parameter.get("queryType");
		Page<Map<String, Object>> page = this.initPage();
		parameter.put("user", super.getUser());
		if(QUERYTYPE_BYREGION.equals(queryType)){//按区域查询
			page = patrolinfoScheduleService.statisticsByRegion(parameter, page);
		}
		if(QUERYTYPE_BYORG.equals(queryType)){//按组织查询
			page = patrolinfoScheduleService.statisticsByOrg(parameter, page);
		}
		if(QUERYTYPE_BYPATROLGROUP.equals(queryType)){//按巡检组查询
			page = patrolinfoScheduleService.statisticsByPatrolGroup(parameter, page);
		}
		if(QUERYTYPE_BYPATROLMAN.equals(queryType)){//按巡检员查询
			page = patrolinfoScheduleService.statisticsByPatrolMan(parameter, page);
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
	 * 详细列表 数据
	 */
	public void detailedData() {
		Map<String, Object> parameter = initConditions();
		parameter.put("user", super.getUser());
		Page<Map<String, Object>> page = this.initPage();
		page = patrolinfoScheduleService.searchDetailed(parameter, page);
		setExcelParameter(page);
	    convertObjToJson(page);
	}
	
	/**
	 * 封装查询条件
	 * @return Map
	 */
	private Map<String, Object> initConditions() {
		HttpServletRequest request = this.getRequest();
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo userInfo = super.getUser();
		String regionId = request.getParameter("regionId");
		if(StringUtils.isBlank(request.getParameter("regionId"))){
			regionId = userInfo.getRegionId();
		}
		String queryType = request.getParameter("queryType");
		if(StringUtils.isBlank(request.getParameter("queryType"))){
			if(userInfo.isMobile())queryType = QUERYTYPE_BYREGION;
			if(userInfo.isContractor())queryType = QUERYTYPE_BYPATROLGROUP;
		}
		map.put("queryType",queryType);
		map.put("regionId", regionId);
		map.put("orgId", request.getParameter("orgId"));
		map.put("patrolGroupId", request.getParameter("patrolGroupId"));
		map.put("patrolManId", request.getParameter("patrolManId"));
		map.put("startTime", request.getParameter("startTime"));
		map.put("endTime", request.getParameter("endTime"));
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