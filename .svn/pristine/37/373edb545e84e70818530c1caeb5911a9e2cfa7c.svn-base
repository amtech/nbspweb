package com.cabletech.business.workflow.wmaintain.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainDeleteService;
import com.cabletech.common.util.Page;

/**
 * 作废任务
 * 
 * @author 王甜 2012-04-14 创建
 * @author 杨隽 2012-04-17 补充方法内容并实现该列表功能
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/wmaintain/wmaintain_waitdelete_list.jsp") })
@Action("/wmaintainDeleteAction")
public class WMaintainDeleteAction extends
		WMaintainBaseAction<WMaintainPlan, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 隐患维修作业计划表单数据
	private WMaintainPlan plan = new WMaintainPlan();
	// 维修作业计划的专业类型
	private String businessType;
	// 隐患维修作业计划作废任务列表业务处理
	@Resource(name = "WMaintainDeleteServiceImpl")
	private WMaintainDeleteService wmaintainDeleteService;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 待作废任务列表
	 * 
	 * @return String
	 */
	public String list() {
		super.getRequest().setAttribute("businessType", businessType);
		return LIST;
	}

	/**
	 * 待作废任务数据
	 */
	@SuppressWarnings("rawtypes")
	public void listData() {
		UserInfo userInfo = super.getUser();
		Page page = super.initPage();
		plan.setPage(page);
		page = wmaintainDeleteService.getWaitDeleteList(plan, userInfo);
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