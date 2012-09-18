package com.cabletech.business.workflow.wmaintain.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainHandleService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 
 * 隐患维修作业计划待办任务
 * @author 王甜 2012-04-14 创建
 * @author 杨隽 2012-04-17 补充方法内容并实现该列表功能
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "waithandledlist", location = "/workflow/wmaintain/wmaintain_waithandled_list.jsp"),
		@Result(name = "handledlist", location = "/workflow/wmaintain/wmaintain_handled_list.jsp") })
@Action("/wmaintainHandleAction")
public class WMaintainHandleAction extends BaseAction<WMaintainPlan, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 隐患维修作业计划表单数据
	private WMaintainPlan plan = new WMaintainPlan();
	// 维修作业计划的专业类型
	private String businessType;
	// 隐患维修作业计划工作流列表业务处理
	@Resource(name = "WMaintainHandleServiceImpl")
	private WMaintainHandleService wmaintainHandleService;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 待办任务列表
	 * 
	 * @return String
	 */
	public String waitHandleList() {
		super.getRequest().setAttribute("businessType", businessType);
		return "waithandledlist";
	}

	/**
	 * 待办任务数据
	 */
	@SuppressWarnings("rawtypes")
	public void waitHandleListData() {
		UserInfo userInfo = super.getUser();
		Page page = super.initPage();
		plan.setPage(page);
		page = wmaintainHandleService.getWaitHandledList(plan, userInfo);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 已办任务列表
	 * 
	 * @return String
	 */
	public String handledList() {
		super.getRequest().setAttribute("businessType", businessType);
		return "handledlist";
	}

	/**
	 * 已办任务数据
	 */
	@SuppressWarnings("rawtypes")
	public void handledListData() {
		UserInfo userInfo = super.getUser();
		Page page = super.initPage();
		plan.setPage(page);
		page = wmaintainHandleService.getHandledList(plan, userInfo);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	public WMaintainPlan getModel() {
		return plan;
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}
}