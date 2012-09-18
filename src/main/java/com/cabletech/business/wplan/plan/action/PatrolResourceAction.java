package com.cabletech.business.wplan.plan.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.business.wplan.plan.service.PatrolResourceService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 巡检计划资源Action
 * 
 * @author zhaob
 * 
 */
@Namespace("/wplan")
@Results({ @Result(name = "list", location = "/wplan/plan/patrolinfo_resource_list.jsp") })
@Action("/patrolresourceAction")
public class PatrolResourceAction extends BaseAction<String, String> {

	/**
	 * 巡检资源服务
	 */
	@Resource(name = "patrolResourceServiceImpl")
	private PatrolResourceService patrolResourceService;

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
	 * 获取巡检资源树
	 */
	public void getpatrolresourcetree() {
		String patrolgroupid = this.getRequest().getParameter("patrolgroup_id");
		String businesstype = this.getRequest().getParameter("businesstype");
		List<Map<String, Object>> patrolResourceList = patrolResourceService
				.getPatrolResource(businesstype, patrolgroupid);
		if (CollectionUtils.isEmpty(patrolResourceList)) {
			super.convertObjToJson("");
			return;
		}
		if (patrolResourceList.size() == 1) {
			super.convertObjToJson("");
			return;
		}
		// 向前台输出JSON数据
		super.convertObjToJson(patrolResourceList);
	}

	/**
	 * 查询计划维护资源列表信息
	 * 
	 * @return
	 */
	public String list() {
		String planid = this.getRequest().getParameter("planid");
		this.getRequest().setAttribute("planid", planid);
		return LIST;
	}

	/**
	 * 查询数据
	 */
	public void query() {
		Page<Map<String, Object>> page = this.initPage();
		String planid = this.getRequest().getParameter("planid");
		page = patrolResourceService.getPlanResource(planid, page);
		convertObjToJson(page);
	}

}
