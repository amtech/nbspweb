package com.cabletech.business.workflow.workorder.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.workorder.model.WorkOrderReply;
import com.cabletech.business.workflow.workorder.service.WorkOrderReplyService;
import com.cabletech.common.base.SysConstant;

/**
 * 通用工单回复Action
 * 
 * @author 杨隽 2011-12-29 创建
 * @author 杨隽 2012-01-06 实现回复功能
 * @author 杨隽 2012-01-09 实现list方法
 * @author 杨隽 2012-01-11 修改页面跳转提示功能
 * @author 杨隽 2012-01-31 进行input方法的代码重构
 * @author 杨隽 2012-05-04 更改使用公共的表单提交标识
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/workorder/workorder_reply.jsp"),
		@Result(name = "list", location = "/workflow/workorder/workorder_reply_list.jsp") })
@Action("/workorderReplyAction")
public class WorkOrderReplyAction extends
		WorkOrderBaseAction<WorkOrderReply, String> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 通用工单回复实体
	private WorkOrderReply workOrderReply;
	// 通用工单回复业务处理接口
	@Resource(name = "workOrderReplyServiceImpl")
	private WorkOrderReplyService workOrderReplyService;

	/**
	 * 进入通用工单回复页面
	 */
	public String input() {
		super.transferParameterToPage();
		super.setPageNoToRequest();
		return INPUT;
	}

	/**
	 * 执行通用工单回复
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String save() {
		UserInfo user = super.getUser();
		List<FileItem> list = (List<FileItem>) super.sessionManager
				.get("FILES");   //上传的文件
		workOrderReply.setCreater(user.getPersonId());
		workOrderReply.setCreateDate(new Date());
		workOrderReply.setFileList(list);
		workOrderReplyService.save(workOrderReply);
		if (SysConstant.FORM_IS_SUBMITED.equals(workOrderReply.getIsSubmited())) {
			super.addMessage("工单回单提交成功!", super.getUrl(), SysConstant.SUCCESS);
		} else {
			super.addMessage("工单回单保存成功!", super.getUrl(), SysConstant.SUCCESS);
		}
		return SUCCESS;  
	}

	/**
	 * 进入通用工单回复记录列表页面
	 * 
	 * @return
	 */
	public String list() {
		String id = super.getRequest().getParameter("id");
		List<Map<String, Object>> list = workOrderReplyService.getReplyList(id);
		super.getRequest().setAttribute("list", list);
		return LIST;
	}

	@Override
	public WorkOrderReply getModel() {
		return workOrderReply;
	}

	public WorkOrderReply getWorkOrderReply() {
		return workOrderReply;
	}

	public void setWorkOrderReply(WorkOrderReply workOrderReply) {
		this.workOrderReply = workOrderReply;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
