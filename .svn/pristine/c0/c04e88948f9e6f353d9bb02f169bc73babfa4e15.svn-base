package com.cabletech.business.workflow.electricity.security.action;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.workflow.common.model.CommonWorkflowResult;
import com.cabletech.business.workflow.electricity.security.model.OeDispatchTask;
import com.cabletech.business.workflow.electricity.security.service.ElectricitySecurityWorkflowService;
import com.cabletech.business.workflow.electricity.security.service.OeDispatchTaskService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 供电保障基类Action
 * 
 * @author 杨隽 2012-05-04 创建
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * @param <T>
 *            实体类
 * @param <PK>
 *            主键类
 * 
 */
public abstract class OeDispatchTaskBaseAction<T, PK extends Serializable>
		extends BaseAction<T, PK> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 流程处理历史key
	public static final String PROCESS_HISTORY_MAP = "PROCESS_HISTORY_MAP";
	// 供电保障断电告警单列表页面跳转路径
	public static final String ALARMLIST_PAGE_URL = "/workflow/oeOutageAlarmAction!list.action?businessType=";
	// 供电保障草稿箱页面跳转路径
	public static final String DRAFT_PAGE_URL = "/workflow/oeDispatchTaskDraftAction!list.action?businessType=";
	// 供电保障待办页面跳转路径
	public static final String WAIT_HANDLED_PAGE_URL = "/workflow/oeDispatchTaskWaitHandledAction!list.action?businessType=";
	// 供电保障待取消页面跳转路径
	public static final String WAIT_CANCELED_PAGE_URL = "/workflow/oeDispatchTaskCancelAction!waitCanceledList.action?businessType=";
	// 供电保障待删除页面跳转路径
	public static final String WAIT_DELETED_PAGE_URL = "/workflow/oeDispatchTaskDeleteAction!list.action?businessType=";
	// 供电保障新建页面跳转路径
	public static final String NEW_INPUT_PAGE_URL = "/workflow/oeDispatchTaskAction!input.action?businessType=";
	// 供电保障专业类型
	protected String businessType;
	// 供电保障业务处理接口
	@Resource(name = "oeDispatchTaskServiceImpl")
	protected OeDispatchTaskService oeDispatchTaskService;
	// 供电保障工作流业务处理
	@Resource(name = "electricitySecurityWorkflowService")
	protected ElectricitySecurityWorkflowService electricitySecurityWorkflowService;
	// 用户信息业务处理
	@Resource(name = "userInfoServiceImpl")
	protected UserInfoService userInfoService;

	/**
	 * 设置流程处理历史Map
	 */
	protected void setProcessHistoryMap() {
		String id = super.getRequest().getParameter("id");
		Map<String, List<CommonWorkflowResult>> map = electricitySecurityWorkflowService
				.getProcessHistoryList(id);
		super.getRequest().setAttribute(PROCESS_HISTORY_MAP, map);
	}

	/**
	 * 返回供电保障状态Map
	 * 
	 * @return Map<String, String> 供电保障状态Map
	 */
	protected Map<String, String> getTaskStateMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(OeDispatchTask.WAIT_SCHEDULED_STATE, "待调度");
		map.put(OeDispatchTask.PROCESSING_STATE, "处理中");
		map.put(OeDispatchTask.WAIT_APPROVED_STATE, "待审核");
		map.put(OeDispatchTask.END_STATE, "结束");
		return map;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 设置页面编号到Request的属性中
	 */
	public void setPageNoToRequest() {
		super.getRequest().setAttribute("pageNo",
				super.getRequest().getParameter("pageNo"));
	}

	/**
	 * 将传入的参数信息存放的Request的属性中
	 */
	public void transferParameterToPage() {
		String id = super.getRequest().getParameter("id");
		OeDispatchTask oeDispatchTask = oeDispatchTaskService
				.viewOeDispatchTask(id);
		super.getRequest().setAttribute("oeDispatchTask", oeDispatchTask);
		super.getRequest().setAttribute("task_id",
				super.getRequest().getParameter("taskId"));
	}

	/**
	 * 预置列表查询的参数信息
	 * 
	 * @param oeDispatchTask
	 *            OeDispatchTask 传入的查询参数信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@SuppressWarnings("rawtypes")
	public void preSetListQuery(OeDispatchTask oeDispatchTask, UserInfo userInfo) {
		if (StringUtils.isNotBlank(oeDispatchTask.getCreator())) {
			UserInfo user = userInfoService
					.getUserInfoByPersonId(oeDispatchTask.getCreator());
			if (user != null) {
				String createrName = user.getUserName();
				oeDispatchTask.setCreaterName(createrName);
			}
		}
		Page page = super.initPage();
		oeDispatchTask.setPage(page);
		oeDispatchTask.setLoginUser(userInfo);
	}

	/**
	 * 输入表单信息页面预处理
	 */
	public void preSetInput() {
		String id = super.getParameter("id");
		String taskId = super.getParameter("taskId");
		OeDispatchTask oeDispatchTask = oeDispatchTaskService
				.viewOeDispatchTask(id);
		super.getRequest().setAttribute("oeDispatchTask", oeDispatchTask);
		super.getRequest().setAttribute("id", id);
		super.getRequest().setAttribute("task_id", taskId);
		super.getRequest().setAttribute("businessType", businessType);
	}
}