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
 * 巡检异常项统计
 * 
 * @author 杨隽 2012-07-27 创建
 * 
 */
@Namespace("/analysis")
@Results({ @Result(name = "list", location = "/analysis/problem_patrolitem_analyse.jsp") })
@Action("/problemPatrolItemAnalysisAction")
public class ProblemPatrolItemAnalysisAction extends BaseAction<String, String> {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 巡检异常项统计服务
	 */
	@Resource(name = "problemPatrolItemAnalyseServiceImpl")
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