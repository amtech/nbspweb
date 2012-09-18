package com.cabletech.business.workflow.electricity.security.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.security.model.OeScheduleTask;
import com.cabletech.business.workflow.electricity.security.service.OeScheduleTaskService;
import com.cabletech.common.base.SysConstant;

/**
 * 供电保障调度Action
 * 
 * @author 杨隽 2012-05-04 创建
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/electricity/security/oe_dispatchtask_schedule.jsp"),
		@Result(name = "list", location = "/workflow/electricity/security/oe_dispatchtask_schedule_list.jsp") })
@Action("/oeScheduleTaskAction")
public class OeScheduleTaskAction extends
		OeDispatchTaskBaseAction<OeScheduleTask, String> {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	// 供电保障调度表单数据
	private OeScheduleTask oeScheduleTask;
	// 断电告警调度业务处理
	@Resource(name = "oeScheduleTaskServiceImpl")
	private OeScheduleTaskService oeScheduleTaskService;

	/**
	 * 进入断电告警调度页面
	 * 
	 * @return String
	 */
	public String input() {
		super.preSetInput();
		return INPUT;
	}

	/**
	 * 执行断电告警调度
	 * 
	 * @return String
	 */
	public String save() {
		UserInfo userInfo = super.getUser();
		oeScheduleTaskService.save(oeScheduleTask, userInfo);
		super.addMessage("供电保障派单调度成功!", WAIT_HANDLED_PAGE_URL,
				SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 进入断电告警调度记录列表页面
	 * 
	 * @return
	 */
	public String list() {
		super.setProcessHistoryMap();
		return LIST;
	}

	@Override
	public OeScheduleTask getModel() {
		return oeScheduleTask;
	}

	public OeScheduleTask getOeScheduleTask() {
		return oeScheduleTask;
	}

	public void setOeScheduleTask(OeScheduleTask oeScheduleTask) {
		this.oeScheduleTask = oeScheduleTask;
	}

	public OeScheduleTaskService getOeScheduleTaskService() {
		return oeScheduleTaskService;
	}

	public void setOeScheduleTaskService(
			OeScheduleTaskService oeScheduleTaskService) {
		this.oeScheduleTaskService = oeScheduleTaskService;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
