package com.cabletech.business.workflow.wmaintain.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainCancelService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 
 * 取消任务
 * 
 * @author 王甜 2012-04-14 创建
 * @author 杨隽 2012-04-17 补充方法内容并实现该列表功能
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "waitcancellist", location = "/workflow/wmaintain/wmaintain_waitcancel_list.jsp"),
		@Result(name = "canceledlist", location = "/workflow/wmaintain/wmaintain_canceled_list.jsp") })
@Action("/wmaintainCancelAction")
public class WMaintainCancelAction extends
		WMaintainBaseAction<WMaintainPlan, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 隐患维修作业计划表单数据
	private WMaintainPlan plan = new WMaintainPlan();
	// 维修作业计划的专业类型
	private String businessType;
	// 隐患维修作业计划取消任务列表业务处理
	@Resource(name = "WMaintainCancelServiceImpl")
	private WMaintainCancelService wmaintainCancelService;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 待取消任务列表
	 * 
	 * @return String
	 */
	public String waitCancelList() {
		super.getRequest().setAttribute("businessType", businessType);
		return "waitcancellist";
	}

	/**
	 * 待取消任务数据
	 */
	@SuppressWarnings("rawtypes")
	public void waitCancelListData() {
		UserInfo userInfo = super.getUser();
		Page page = super.initPage();
		plan.setPage(page);
		page = wmaintainCancelService.getWaitCancelList(plan, userInfo);
		convertObjToJson(page);
	}

	/**
	 * 已取消任务列表
	 * 
	 * @return String
	 */
	public String canceledList() {
		super.getRequest().setAttribute("businessType", businessType);
		return "canceledlist";
	}

	/**
	 * 已取消任务数据
	 */
	@SuppressWarnings("rawtypes")
	public void canceledListData() {
		UserInfo userInfo = super.getUser();
		Page page = super.initPage();
		plan.setPage(page);
		page = wmaintainCancelService.getCancedlList(plan, userInfo);
		convertObjToJson(page);
	}

	/**
	 * 取消隐患维修作业计划
	 * 
	 * @return String
	 */
	public String cancel() {
		String[] id = super.getRequest().getParameterValues("id");
		wmaintainCancelService.cancel(id);
		super.addMessage("取消维修作业计划流程成功！", WAIT_CANCELED_PAGE_URL,
				SysConstant.SUCCESS);
		return SUCCESS;
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