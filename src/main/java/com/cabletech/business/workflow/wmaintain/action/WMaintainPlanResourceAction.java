package com.cabletech.business.workflow.wmaintain.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainResourceService;

/**
 * 查询待处理的资源站点Action
 * 
 * @author 杨隽 2012-04-23 创建
 * @author 杨隽 2012-06-06 修改问题站点选择的bug问题
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/wmaintain/wmaintain_select_resources.jsp") })
@Action("/wmaintainPlanResourceAction")
public class WMaintainPlanResourceAction extends
		WMaintainBaseAction<WMaintainPlan, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 查询待处理的资源站点表单数据
	private WMaintainPlan plan;
	// 查询待处理的资源站点业务处理
	@Resource(name = "WMaintainResourceServiceImpl")
	private WMaintainResourceService wmaintainResourceService;

	/**
	 * 进入待处理的资源站点列表页面
	 * 
	 * @return String
	 */
	public String list() {
		super.getRequest().setAttribute("businessType",
				super.getParameter("businessType"));
		super.getRequest().setAttribute("patrolGroup",
				super.getParameter("patrolGroup"));
		super.getRequest().setAttribute("planId", super.getParameter("planId"));
		return LIST;
	}

	/**
	 * 获取无线资源计划列表字符串
	 */
	public void planList() {
		String planListStr = wmaintainResourceService.getWplanList(plan);
		try {
			super.outPrint(planListStr, false);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 获取查询待处理的资源站点列表信息
	 */
	public void resourcesList() {
		List<Map<String, Object>> siteList = wmaintainResourceService
				.getWMaintainResourceList(plan);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", siteList);
		convertObjToJson(data);
	}

	/**
	 * 获取查询待处理的资源站点中存在问题列表信息
	 */
	public void resourceProblemesList() {
		List<Map<String, Object>> problemList = wmaintainResourceService
				.getWMaintainResourceProblemList(plan);
		List<Map<String, Object>> siteList = wmaintainResourceService
				.getWMaintainResourceList(plan);
		problemList.addAll(siteList);
		convertObjToJson(problemList);
	}

	public WMaintainPlan getPlan() {
		return plan;
	}

	public void setPlan(WMaintainPlan plan) {
		this.plan = plan;
	}

	@Override
	public WMaintainPlan getModel() {
		// TODO Auto-generated method stub
		return plan;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub
	}
}
