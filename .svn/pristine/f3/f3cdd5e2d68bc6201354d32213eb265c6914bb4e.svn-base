package com.cabletech.business.workflow.wmaintain.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainApprovePlanService;
import com.cabletech.common.base.SysConstant;

/**
 * 审核维修作业计划Action
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-16 添加注释部分
 * @author 杨隽 2012-04-18 添加list()方法
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/wmaintain/wmaintainplan_audit_input.jsp"),
		@Result(name = "list", location = "/workflow/wmaintain/wmaintainplan_audit_list.jsp") })
@Action("/wmaintainApprovePlanAction")
public class WMaintainApprovePlanAction extends
		WMaintainBaseAction<WMaintainPlan, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 维修作业计划审核表单数据
	private WMaintainPlan plan;
	// 审核维修作业计划服务
	@Resource(name = "WMaintainApprovePlanServiceImpl")
	private WMaintainApprovePlanService wMaintainApprovePlanService;

	public WMaintainPlan getPlan() {
		return plan;
	}

	public void setPlan(WMaintainPlan plan) {
		this.plan = plan;
	}

	/**
	 * 进入维修作业计划审核页面
	 * 
	 * @return String
	 */
	public String input() {
		super.preSetInput();
		return INPUT;
	}

	/**
	 * 执行维修作业计划审核
	 * 
	 * @return String
	 */
	public String save() {
		UserInfo userInfo = super.getUser();
		wMaintainApprovePlanService.approvePlan(plan, userInfo);
		super.addMessage("维修作业计划审核成功！", WAIT_HANDLED_PAGE_URL,
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 进入维修作业计划审核记录列表页面
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
