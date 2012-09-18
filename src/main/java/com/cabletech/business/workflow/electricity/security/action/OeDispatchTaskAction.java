package com.cabletech.business.workflow.electricity.security.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.model.OeOutageAlarm;
import com.cabletech.business.workflow.electricity.security.service.OeOutageAlarmService;
import com.cabletech.common.base.SysConstant;

/**
 * 供电保障派单Action
 * 
 * @author 杨隽 2012-05-04 创建
 * @author 杨隽 2012-05-07 进行后台处理的细化
 * 
 */
@Namespace("/workflow")
@Results({
		@Result(name = "input", location = "/workflow/electricity/security/oe_dispatchtask_input.jsp"),
		@Result(name = "view", location = "/workflow/electricity/security/oe_dispatchtask_view.jsp") })
@Action("/oeDispatchTaskAction")
public class OeDispatchTaskAction extends
		OeDispatchTaskBaseAction<OeDispatchTask, String> {
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 1L;
	// 供电保障派单表单数据
	private OeDispatchTask oeDispatchTask;
	// 断电告警单业务处理服务
	@Resource(name = "oeOutageAlarmServiceImpl")
	private OeOutageAlarmService oeOutageAlarmService;

	/**
	 * 进入断电告警派单页面
	 * 
	 * @return String
	 */
	public String input() {
		String id = super.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			OeDispatchTask oeDispatchTask = oeDispatchTaskService
					.viewOeDispatchTask(id);
			super.getRequest().setAttribute("oeDispatchTask", oeDispatchTask);
			super.getRequest().setAttribute("showReturn", "y");
		}
		return INPUT;
	}

	/**
	 * 从断电告警单列表中进行派单
	 * 
	 * @return String
	 */
	public String dispatchInput() {
		String id = super.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			OeOutageAlarm oeOutageAlarm = oeOutageAlarmService
					.viewOeOutageAlarm(id);
			OeDispatchTask oeDispatchTask = oeOutageAlarmService
					.cloneAlarmToDispatchTask(oeOutageAlarm);
			super.getRequest().setAttribute("oeDispatchTask", oeDispatchTask);
		}
		super.getRequest().setAttribute("showReturn", "y");
		super.getRequest().setAttribute("origin", "alarm");
		return INPUT;
	}

	/**
	 * 执行断电告警派单
	 * 
	 * @return String
	 */
	public String save() {
		UserInfo userInfo = super.getUser();
		String id = oeDispatchTask.getId();
		oeDispatchTaskService.save(oeDispatchTask, userInfo);
		String message = getMessage();
		String url = getUrl(id);
		super.addMessage(message, url, SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 删除断电告警派单
	 * 
	 * @return String
	 */
	public String delete() {
		String[] id = super.getRequest().getParameterValues("id");
		String state = super.getParameter("state");
		oeDispatchTaskService.deleteOeDispatchTask(id);
		String url = "";
		if (OeDispatchTask.WAIT_DISPATCHED_STATE.equals(state)) {
			url = DRAFT_PAGE_URL;
		} else {
			url = WAIT_DELETED_PAGE_URL;
		}
		super.addMessage("供电故障派单删除成功!", url, SysConstant.SUCCESS);
		return SUCCESS;
	}

	/**
	 * 查看断电结果派单
	 * 
	 * @return String
	 */
	public String view() {
		String id = super.getParameter("id");
		String origin = super.getParameter("origin");
		String showReturn = super.getParameter("showReturn");
		if (StringUtils.isBlank(showReturn)) {
			showReturn = "1";
		}
		OeDispatchTask oeDispatchTask = oeDispatchTaskService
				.viewOeDispatchTask(id);
		super.getRequest().setAttribute("oeDispatchTask", oeDispatchTask);
		super.getRequest().setAttribute("origin", origin);
		super.getRequest().setAttribute("showReturn", showReturn);
		return VIEW;
	}

	@Override
	public OeDispatchTask getModel() {
		return oeDispatchTask;
	}

	public OeDispatchTask getOeDispatchTask() {
		return oeDispatchTask;
	}

	public void setOeDispatchTask(OeDispatchTask oeDispatchTask) {
		this.oeDispatchTask = oeDispatchTask;
	}

	/**
	 * 获取“返回”按钮的URL
	 * 
	 * @param id
	 *            String 发电派单编号
	 * @return String “返回”按钮的URL
	 */
	private String getUrl(String id) {
		String url = NEW_INPUT_PAGE_URL;
		if (StringUtils.isNotBlank(oeDispatchTask.getAlarmId())) {
			url = ALARMLIST_PAGE_URL;
		}
		if (StringUtils.isNotBlank(id)) {
			url = DRAFT_PAGE_URL;
		}
		return url;
	}

	/**
	 * 获取提示信息
	 * 
	 * @return String 提示信息
	 */
	private String getMessage() {
		String message = "保存断电告警派单成功!";
		if (OeDispatchTask.IS_SUBMITED.equals(oeDispatchTask.getIsSubmited())) {
			message = "提交断电告警派单成功!";
		}
		return message;
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}
