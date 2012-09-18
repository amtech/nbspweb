package com.cabletech.business.workflow.wmaintain.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainApproveReportService;
import com.cabletech.business.workflow.wmaintain.service.WMaintainSiteService;
import com.cabletech.common.base.SysConstant;

/**
 * 审核维修作业计划维护报告Action
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-16 添加注释部分
 * @author 杨隽 2012-04-18 添加list()方法
 * @author 杨隽 2012-04-19 在input()方法中添加获取计划中站点不同维护状态的维护数量信息功能
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/wmaintain/wmaintainplan_filing_input.jsp"),
		@Result(name = "list", location = "/workflow/wmaintain/wmaintainplan_filing_list.jsp") })
@Action("/wmaintainApproveReportAction")
public class WMaintainApproveReportAction extends
		WMaintainBaseAction<WMaintainPlan, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 维修作业计划报告审核表单数据
	private WMaintainPlan plan;
	// 计划维护站点服务
	@Resource(name = "WMaintainSiteServiceImpl")
	private WMaintainSiteService wMaintainSiteService;
	// 审核维修作业计划维护报告服务
	@Resource(name = "WMaintainApproveReportServiceImpl")
	private WMaintainApproveReportService wMaintainApproveReportService;

	public WMaintainPlan getPlan() {
		return plan;
	}

	public void setPlan(WMaintainPlan plan) {
		this.plan = plan;
	}

	/**
	 * 进入维修作业维护报告审核页面
	 * 
	 * @return String
	 */
	public String input() {
		super.preSetInput();
		String id = super.getParameter("id");
		plan = super.wMaintainCreatePlanService.view(id);
		Map<String, Object> numberMap = wMaintainSiteService
				.getPlanSiteMaintainNumberMap(id);
		super.getRequest().setAttribute("number_map", numberMap);
		return INPUT;
	}

	/**
	 * 执行维修作业维护报告审核
	 * 
	 * @return String
	 */
	public String save() {
		UserInfo userInfo = super.getUser();
		wMaintainApproveReportService.approveRecord(plan, userInfo);
		super.addMessage("审核维修作业计划报告成功！", WAIT_HANDLED_PAGE_URL,
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 进入维修作业维护报告审核记录列表页面
	 * 
	 * @return String
	 */
	public String list() {
		super.setProcessHistoryMap();
		return LIST;
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
