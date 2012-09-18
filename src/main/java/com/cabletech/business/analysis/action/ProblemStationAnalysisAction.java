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
import com.cabletech.business.analysis.service.PatrolAnalyseService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 问题站点统计
 * 
 * @author 杨隽 2012-07-27 创建
 * 
 */
@Namespace("/analysis")
@Results({
		@Result(name = "list", location = "/analysis/problem_station_analyse.jsp"),
		@Result(name = "problem_list", location = "/analysis/problem_station_problem_analyse.jsp") })
@Action("/problemStationAnalysisAction")
public class ProblemStationAnalysisAction extends BaseAction<String, String> {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 问题信息列表页面url
	 */
	private static final String PROBLEM_LIST = "problem_list";
	/**
	 * 问题站点统计服务
	 */
	@Resource(name = "problemStationAnalyseServiceImpl")
	private PatrolAnalyseService patrolAnalyseService;

	/**
	 * 进入列表页面
	 * 
	 * @return String
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 获取列表JSON
	 */
	@SuppressWarnings("rawtypes")
	public void listData() {
		Page page = super.initPage();
		Map<String, Object> map = getParameters();
		map.put("user", super.getUser());
		patrolAnalyseService.analyse(map, page);
		super.setExcelParameter(page);
		super.convertObjToJson(page);
	}

	/**
	 * 进入问题信息列表页面
	 * 
	 * @return String
	 */
	public String problemList() {
		Map<String, Object> map = getParameters();
		map.put("id", super.getParameter("id"));
		super.getRequest().setAttribute("map", map);
		return PROBLEM_LIST;
	}

	/**
	 * 获取问题信息列表JSON
	 */
	@SuppressWarnings("rawtypes")
	public void problemListData() {
		Page page = super.initPage();
		Map<String, Object> map = getParameters();
		map.put("id", super.getParameter("id"));
		patrolAnalyseService.analyseDetail(map, page);
		super.setExcelParameter(page);
		super.convertObjToJson(page);
	}

	@Override
	public String getModel() {
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	/**
	 * 获取查询参数
	 */
	public Map<String, Object> getParameters() {
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo user = this.getUser();
		if (StringUtils.isNotBlank(getParameter("orgId"))) {
			map.put("orgId", getParameter("orgId"));
		} else {
			if (user.isContractor()) {
				map.put("orgId", user.getOrgId());
			}
		}
		if (StringUtils.isNotBlank(getParameter("regionId"))) {
			map.put("regionId", getParameter("regionId"));
		} else {
			map.put("regionId", user.getRegionId());
		}
		map.put("patrolId", getParameter("patrolId"));
		map.put("businessType", getParameter("businessType"));
		map.put("startTime", getParameter("startTime"));
		map.put("endTime", getParameter("endTime"));
		return map;
	}
}