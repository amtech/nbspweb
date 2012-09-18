package com.cabletech.business.workflow.electricity.security.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.service.OeDispatchTaskCancelService;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 
 * 取消任务
 * 
 * @author 杨隽 2012-05-04 创建
 * @author 杨隽 2012-05-10
 *         在waitCanceledList()和canceledList()方法中放入设置任务状态map的request属性
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "waitcancellist", location = "/workflow/electricity/security/oe_dispatchtask_waitcanceled_list.jsp"),
		@Result(name = "canceledlist", location = "/workflow/electricity/security/oe_dispatchtask_canceled_list.jsp") })
@Action("/oeDispatchTaskCancelAction")
public class OeDispatchTaskCancelAction extends
		OeDispatchTaskBaseAction<OeDispatchTask, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 供电保障查询表单数据
	private OeDispatchTask oeDispatchTask = new OeDispatchTask();
	// 供电保障的专业类型
	private String businessType;
	// 供电保障取消任务列表业务处理
	@Resource(name = "oeDispatchTaskCancelServiceImpl")
	private OeDispatchTaskCancelService oeDispatchTaskCancelService;

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
	public String waitCanceledList() {
		super.getRequest().setAttribute("taskStates", super.getTaskStateMap());
		super.getRequest().setAttribute("businessType", businessType);
		return "waitcancellist";
	}

	/**
	 * 待取消任务数据
	 */
	@SuppressWarnings("rawtypes")
	public void waitCanceledListData() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(oeDispatchTask, userInfo);
		Page page = oeDispatchTaskCancelService
				.getWaitCanceledList(oeDispatchTask);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 已取消任务列表
	 * 
	 * @return String
	 */
	public String canceledList() {
		super.getRequest().setAttribute("taskStates", super.getTaskStateMap());
		super.getRequest().setAttribute("businessType", businessType);
		return "canceledlist";
	}

	/**
	 * 已取消任务数据
	 */
	@SuppressWarnings("rawtypes")
	public void canceledListData() {
		UserInfo userInfo = super.getUser();
		super.preSetListQuery(oeDispatchTask, userInfo);
		Page page = oeDispatchTaskCancelService.getCanceledList(oeDispatchTask);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 取消供电保障派单
	 * 
	 * @return String
	 */
	public String cancel() {
		String[] id = super.getRequest().getParameterValues("id");
		oeDispatchTaskCancelService.cancelTask(id);
		super.addMessage("取消供电保障流程成功!", WAIT_CANCELED_PAGE_URL,
				SysConstant.SUCCESS);
		return SUCCESS;
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