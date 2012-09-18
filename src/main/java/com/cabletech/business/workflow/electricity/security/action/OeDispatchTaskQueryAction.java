package com.cabletech.business.workflow.electricity.security.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.service.OeDispatchTaskQueryService;
import com.cabletech.common.util.Page;

/**
 * 
 * 供电保障查询列表
 * 
 * @author 杨隽 2012-05-04 创建
 * @author 杨隽 2012-05-10 在list()方法中放入设置任务状态map的request属性
 * 
 */
@Namespace("/workflow")
@Results({ @Result(name = "list", location = "/workflow/electricity/security/oe_dispatch_task_list.jsp") })
@Action("/oeDispatchTaskQueryAction")
public class OeDispatchTaskQueryAction extends
		OeDispatchTaskBaseAction<OeDispatchTask, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 供电保障查询表单数据
	private OeDispatchTask oeDispatchTask = new OeDispatchTask();
	// 供电保障的专业类型
	private String businessType;
	// 供电故障查询列表业务处理
	@Resource(name = "oeDispatchTaskQueryServiceImpl")
	private OeDispatchTaskQueryService oeDispatchTaskQueryService;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 查询列表
	 * 
	 * @return String
	 */
	public String list() {
		super.getRequest().setAttribute("taskStates", super.getTaskStateMap());
		super.getRequest().setAttribute("businessType", businessType);
		return LIST;
	}

	/**
	 * 查询列表数据
	 */
	@SuppressWarnings("rawtypes")
	public void listData() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(oeDispatchTask, userInfo);
		Page page = oeDispatchTaskQueryService.getList(oeDispatchTask);
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