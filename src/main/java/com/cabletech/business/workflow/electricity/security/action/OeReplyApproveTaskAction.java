package com.cabletech.business.workflow.electricity.security.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.security.model.OeReplyApproveTask;
import com.cabletech.business.workflow.electricity.security.service.OeReplyApproveTaskService;
import com.cabletech.common.base.SysConstant;

/**
 * 供电保障回单审核Action
 * 
 * @author 杨隽 2012-05-04 创建
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/electricity/security/oe_dispatchtask_replyapprove.jsp"),
		@Result(name = "list", location = "/workflow/electricity/security/oe_dispatchtask_replyapprove_list.jsp") })
@Action("/oeReplyApproveTaskAction")
public class OeReplyApproveTaskAction extends
		OeDispatchTaskBaseAction<OeReplyApproveTask, String> {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	// 供电保障回单审核表单数据
	private OeReplyApproveTask oeReplyApproveTask;
	// 断电告警回单审核业务处理
	@Resource(name = "oeReplyApproveTaskServiceImpl")
	private OeReplyApproveTaskService oeReplyApproveTaskService;

	/**
	 * 进入断电告警回单审核页面
	 * 
	 * @return String
	 */
	public String input() {
		super.preSetInput();
		return INPUT;
	}

	/**
	 * 执行断电告警回单审核
	 * 
	 * @return String
	 */
	public String save() {
		UserInfo userInfo = super.getUser();
		oeReplyApproveTaskService.save(oeReplyApproveTask, userInfo);
		super.addMessage("供电保障派单回单审核成功!", WAIT_HANDLED_PAGE_URL,
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 进入断电告警回单审核记录列表页面
	 * 
	 * @return String
	 */
	public String list() {
		super.setProcessHistoryMap();
		return LIST;
	}

	@Override
	public OeReplyApproveTask getModel() {
		return oeReplyApproveTask;
	}

	public OeReplyApproveTask getOeReplyApproveTask() {
		return oeReplyApproveTask;
	}

	public void setOeReplyApproveTask(OeReplyApproveTask oeReplyApproveTask) {
		this.oeReplyApproveTask = oeReplyApproveTask;
	}

	public OeReplyApproveTaskService getOeReplyApproveTaskService() {
		return oeReplyApproveTaskService;
	}

	public void setOeReplyApproveTaskService(
			OeReplyApproveTaskService oeReplyApproveTaskService) {
		this.oeReplyApproveTaskService = oeReplyApproveTaskService;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
