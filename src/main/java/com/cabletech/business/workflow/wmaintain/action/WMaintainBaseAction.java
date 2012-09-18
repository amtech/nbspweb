package com.cabletech.business.workflow.wmaintain.action;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.workflow.common.model.CommonWorkflowResult;
import com.cabletech.business.workflow.wmaintain.model.WMaintainPlan;
import com.cabletech.business.workflow.wmaintain.service.WMaintainCreatePlanService;
import com.cabletech.business.workflow.wmaintain.service.WMaintainWorkflowService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 隐患维修作业计划基类Action
 * 
 * @author 杨隽 2012-04-11 创建
 * @author 杨隽 2012-04-17 添加页面跳转常量路径，提取businessType公共属性并添加preSetInput()方法
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * @param <T>
 *            实体类
 * @param <PK>
 *            主键类
 * 
 */
public abstract class WMaintainBaseAction<T, PK extends Serializable> extends
		BaseAction<T, PK> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 流程处理历史key
	public static final String PROCESS_HISTORY_MAP = "PROCESS_HISTORY_MAP";
	// 隐患维修作业计划草稿箱页面跳转路径
	public static final String DRAFT_PAGE_URL = "/workflow/wmaintainDraftAction!list.action?businessType=";
	// 隐患维修作业计划待办页面跳转路径
	public static final String WAIT_HANDLED_PAGE_URL = "/workflow/wmaintainHandleAction!waitHandleList.action?businessType=";
	// 隐患维修作业计划待取消页面跳转路径
	public static final String WAIT_CANCELED_PAGE_URL = "/workflow/wmaintainCancelAction!waitCancelList.action?businessType=";
	// 隐患维修作业计划待删除页面跳转路径
	public static final String WAIT_DELETED_PAGE_URL = "/workflow/wmaintainDeleteAction!list.action?businessType=";
	// 隐患维修作业计划新建页面跳转路径
	public static final String NEW_INPUT_PAGE_URL = "/workflow/wmaintainCreatePlanAction!input.action?businessType=";
	// 隐患维修作业计划专业类型
	protected String businessType;
	// 隐患维修作业计划业务处理接口
	@Resource(name = "WMaintainCreatePlanServiceImpl")
	protected WMaintainCreatePlanService wMaintainCreatePlanService;
	// 隐患维修作业计划工作流业务处理
	@Resource(name = "WMaintainWorkflowService")
	protected WMaintainWorkflowService wMaintainWorkflowService;
	// 用户信息业务处理
	@Resource(name = "userInfoServiceImpl")
	protected UserInfoService userInfoService;

	/**
	 * 设置流程处理历史Map
	 */
	protected void setProcessHistoryMap() {
		String id = super.getRequest().getParameter("id");
		Map<String, List<CommonWorkflowResult>> map = wMaintainWorkflowService
				.getProcessHistoryList(id);
		super.getRequest().setAttribute(PROCESS_HISTORY_MAP, map);
	}

	/**
	 * 返回维修作业计划状态Map
	 * 
	 * @return Map<String, String> 维修作业计划状态Map
	 */
	protected Map<String, String> getTaskStateMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(WMaintainPlan.WMAINTAIN_PLAN_SUBMITED_STATE, "提交审核");
		map.put(WMaintainPlan.WMAINTAIN_PLAN_PASSED_STATE, "提交报告");
		map.put(WMaintainPlan.WMAINTAIN_RECORD_SUBMITED_STATE, "报告提交审核");
		map.put(WMaintainPlan.WMAINTAIN_END_STATE, "结束 ");
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
	 * 获取成功返回页面中“返回”按钮的跳转路径（从待办列表中跳入处理功能页面）
	 * 
	 * @return String “返回”按钮的跳转路径
	 */
	public String getUrl() {
		return getUrl(WAIT_HANDLED_PAGE_URL);
	}

	/**
	 * 获取成功返回页面中“返回”按钮的跳转路径（从待办列表中跳入处理功能页面）
	 * 
	 * @param baseUrl
	 *            String 跳转url的前缀
	 * @return String “返回”按钮的跳转路径
	 */
	public String getUrl(String baseUrl) {
		StringBuffer url = new StringBuffer("");
		String pageNo = super.getRequest().getParameter("pageNo");
		url.append(baseUrl);
		url.append("&pageNo=");
		url.append(pageNo);
		return url.toString();
	}

	/**
	 * 预置列表查询的参数信息
	 * 
	 * @param plan
	 *            WMaintainPlan 传入的查询参数信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@SuppressWarnings("rawtypes")
	public void preSetListQuery(WMaintainPlan plan, UserInfo userInfo) {
		if (StringUtils.isNotBlank(plan.getCreater())) {
			UserInfo user = userInfoService.getUserInfoByPersonId(plan
					.getCreater());
			if (user != null) {
				String createrName = user.getUserName();
				plan.setCreaterName(createrName);
			}
		}
		Page page = super.initPage();
		plan.setPage(page);
		plan.setLoginUser(userInfo);
	}

	/**
	 * 输入表单信息页面预处理
	 */
	public void preSetInput() {
		String id = super.getParameter("id");
		String taskId = super.getParameter("taskId");
		super.getRequest().setAttribute("id", id);
		super.getRequest().setAttribute("task_id", taskId);
		super.getRequest().setAttribute("businessType", businessType);
	}
}