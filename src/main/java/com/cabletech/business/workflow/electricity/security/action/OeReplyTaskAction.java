package com.cabletech.business.workflow.electricity.security.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.security.model.OeReplyTask;
import com.cabletech.business.workflow.electricity.security.service.OeReplyTaskService;
import com.cabletech.common.base.SysConstant;

/**
 * 供电保障回单Action
 * 
 * @author 杨隽 2012-05-04 创建
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/electricity/security/oe_dispatchtask_reply.jsp"),
		@Result(name = "list", location = "/workflow/electricity/security/oe_dispatchtask_reply_list.jsp") })
@Action("/oeReplyTaskAction")
public class OeReplyTaskAction extends
		OeDispatchTaskBaseAction<OeReplyTask, String> {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	// 供电保障回单表单数据
	private OeReplyTask oeReplyTask;
	// 断电告警回单业务处理
	@Resource(name = "oeReplyTaskServiceImpl")
	private OeReplyTaskService oeReplyTaskService;

	/**
	 * 进入断电告警回单页面
	 * 
	 * @return String
	 */
	public String input() {
		super.preSetInput();
		return INPUT;
	}

	/**
	 * 执行断电告警回单
	 * 
	 * @return String
	 */
	public String save() {
		UserInfo userInfo = super.getUser();
		oeReplyTaskService.save(oeReplyTask, userInfo);
		super.addMessage("供电保障派单回单成功!", WAIT_HANDLED_PAGE_URL,
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 进入断电告警回单记录列表页面
	 * 
	 * @return String
	 */
	public String list() {
		super.setProcessHistoryMap();
		return LIST;
	}

	@Override
	public OeReplyTask getModel() {
		return oeReplyTask;
	}

	public OeReplyTask getOeReplyTask() {
		return oeReplyTask;
	}

	public void setOeReplyTask(OeReplyTask oeReplyTask) {
		this.oeReplyTask = oeReplyTask;
	}

	public OeReplyTaskService getOeReplyTaskService() {
		return oeReplyTaskService;
	}

	public void setOeReplyTaskService(OeReplyTaskService oeReplyTaskService) {
		this.oeReplyTaskService = oeReplyTaskService;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
