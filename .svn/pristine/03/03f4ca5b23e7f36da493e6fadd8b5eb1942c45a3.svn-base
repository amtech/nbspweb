package com.cabletech.business.workflow.electricity.security.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.service.OeDispatchTaskDraftService;
import com.cabletech.common.util.Page;

/**
 * 供电保障草稿箱列表ACTION
 * 
 * @author 杨隽 2012-05-04 创建
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/electricity/security/oe_dispatchtask_draft_list.jsp") })
@Action("/oeDispatchTaskDraftAction")
public class OeDispatchTaskDraftAction extends
		OeDispatchTaskBaseAction<OeDispatchTask, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 供电保障草稿箱表单数据
	private OeDispatchTask oeDispatchTask = new OeDispatchTask();
	// 供电保障的专业类型
	private String businessType;
	// 供电保障草稿箱业务处理
	@Resource(name = "oeDispatchTaskDraftServiceImpl")
	private OeDispatchTaskDraftService oeDispatchTaskDraftService;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 进入草稿箱列表页面
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
		super.preSetListQuery(oeDispatchTask, userInfo);
		Page page = oeDispatchTaskDraftService.getDraftList(oeDispatchTask);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	public OeDispatchTask getModel() {
		return oeDispatchTask;
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}
}
