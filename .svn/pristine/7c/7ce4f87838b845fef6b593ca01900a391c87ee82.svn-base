package com.cabletech.business.workflow.wmaintain.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainDraftService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 隐患处理草稿箱列表ACTION
 * 
 * @author 王甜 2012-04-14 创建
 * @author 杨隽 2012-04-16 补充方法内容并实现该列表功能
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/wmaintain/wmaintain_drafts_list.jsp") })
@Action("/wmaintainDraftAction")
public class WMaintainDraftAction extends BaseAction<WMaintainPlan, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 隐患处理草稿箱表单数据
	private WMaintainPlan plan = new WMaintainPlan();
	// 维修作业计划的专业类型
	private String businessType;
	// 维修作业计划草稿箱业务处理
	@Resource(name = "WMaintainDraftServiceImpl")
	private WMaintainDraftService wmaintainDraftService;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public WMaintainPlan getModel() {
		return plan;
	}

	/**
	 * 进入维修作业计划草稿箱列表页面
	 * 
	 * @return String
	 */
	public String list() {
		super.getRequest().setAttribute("businessType", businessType);
		return LIST;
	}

	/**
	 * 获取草稿箱数据
	 */
	@SuppressWarnings("rawtypes")
	public void listData() {
		UserInfo userInfo = super.getUser();
		Page page = super.initPage();
		plan.setPage(page);
		page = wmaintainDraftService.getDraftList(plan, userInfo);
		convertObjToJson(page);
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}
}
