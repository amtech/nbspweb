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
import com.cabletech.business.workflow.wmaintain.service.WMaintainResultService;
import com.cabletech.business.workflow.wmaintain.service.WMaintainSiteService;

/**
 * 维修作业计划中站点信息Action
 * 
 * @author 杨隽 2012-04-18 创建
 * @author 杨隽 2012-04-19 添加recordList()方法
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "list", location = "/workflow/wmaintain/wmaintain_plan_site_list.jsp"),
		@Result(name = "record_list", location = "/workflow/wmaintain/wmaintain_plan_record_site_list.jsp") })
@Action("/wmaintainSiteAction")
public class WMaintainSiteAction extends
		WMaintainBaseAction<WMaintainPlan, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 计划维护站点列表（用于提交和审核作业报告）页面跳转路径
	private static final String RECORD_LIST = "record_list";
	// 计划维护站点服务
	@Resource(name = "WMaintainSiteServiceImpl")
	private WMaintainSiteService wMaintainSiteService;
	// 站点异常项及处理结果服务
	@Resource(name = "WMaintainResultServiceImpl")
	private WMaintainResultService wMaintainResultService;

	/**
	 * 获取计划维护站点列表（用于提交和审核维修作业计划）
	 * 
	 * @return String
	 */
	public String list() {
		putPlanToRequest();
		return LIST;
	}

	/**
	 * 获取计划维护站点列表（用于提交和审核维修作业报告）
	 * 
	 * @return String
	 */
	public String recordList() {
		putPlanToRequest();
		return RECORD_LIST;
	}

	/**
	 * 获取维修作业计划中计划维护站点编号信息
	 */
	public void mainList() {
		String id = super.getParameter("id");
		List<Map<String, Object>> siteList = wMaintainSiteService
				.getWMaintainSiteList(id);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", siteList);
		convertObjToJson(data);
	}

	/**
	 * 获取维修作业计划中计划维护站点异常项及维护结果编号信息
	 */
	public void subList() {
		String id = super.getParameter("id");
		String type = super.getParameter("type");
		List<Map<String, Object>> resultList = wMaintainResultService
				.getWMaintainResultList(id, type);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", resultList);
		convertObjToJson(data);
	}

	@Override
	public WMaintainPlan getModel() {
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
	 * 根据计划编号获取计划信息放到request的属性中
	 */
	private void putPlanToRequest() {
		String id = super.getParameter("id");
		WMaintainPlan plan = super.wMaintainCreatePlanService.view(id);
		super.getRequest().setAttribute("plan", plan);
	}
}
