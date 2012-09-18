package com.cabletech.business.workflow.workorder.action;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.base.service.UserInfoService;
import com.cabletech.business.workflow.common.model.CommonWorkflowResult;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.service.WorkOrderDispatchService;
import com.cabletech.business.workflow.workorder.service.WorkOrderWorkflowService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 通用工单基类Action
 * 
 * @param <T>
 *            实体类
 * @param <PK>
 *            主键类
 * @author 杨隽 2011-12-29 创建
 * @author 杨隽 2012-01-09 添加工作流业务处理变量和setProcessHistoryMap方法
 * @author 杨隽 2012-01-11 添加通用工单待办页面跳转路径常量
 * @author 杨隽 2012-01-12 添加通用工单新建页面跳转路径常量
 * @author 杨隽 2012-01-12 添加通用工单的业务序号生成generateCodeSequence方法
 * @author 杨隽 2012-01-13 去除generateCodeSequence方法
 * @author 杨隽 2012-01-13 添加通用工单草稿箱页面跳转路径常量
 * @author 汪杰 2012-01-13 添加工单状态集合 用于页面获取
 * @author 杨隽 2012-01-17 用常量替换字符串
 * @author 杨隽 2012-01-31
 *         添加setPageNoToRequest()、getUrl()和transferParameterToPage()方法
 * @author 杨隽 2012-02-07 添加preSetListQuery()方法
 * @author 杨隽 2012-02-07 添加“通用工单待删除页面跳转路径”和“通用工单待删除页面跳转路径”常量
 * @author 杨隽 2012-02-07 添加getUrl(baseUrl)方法
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 */
public abstract class WorkOrderBaseAction<T, PK extends Serializable> extends
		BaseAction<T, PK> {
	// 序列化编号
	private static final long serialVersionUID = 1L;
	// 流程处理历史key
	public static final String PROCESS_HISTORY_MAP = "PROCESS_HISTORY_MAP";
	// 通用工单草稿箱页面跳转路径
	public static final String DRAFT_PAGE_URL = "/workflow/workOrderDraftAction!list.action?rnd=";
	// 通用工单待办页面跳转路径
	public static final String WAIT_HANDLED_PAGE_URL = "/workflow/workOrderWaitHandledAction!list.action?rnd=";
	// 通用工单待取消页面跳转路径
	public static final String WAIT_CANCELED_PAGE_URL = "/workflow/workOrderCancelAction!waitCanceledList.action?rnd=";
	// 通用工单待删除页面跳转路径
	public static final String WAIT_DELETED_PAGE_URL = "/workflow/workOrderWaitDeletedAction!list.action?rnd=";
	// 通用工单新建页面跳转路径
	public static final String NEW_INPUT_PAGE_URL = "/workflow/workorderDispatchAction!input.action?rnd=";
	// 通用工单业务处理接口
	@Resource(name = "workOrderDispatchServiceImpl")
	private WorkOrderDispatchService workOrderDispatchService;
	// 通用工单工作流业务处理
	@Resource(name = "workOrderWorkflowService")
	protected WorkOrderWorkflowService workOrderWorkflowService;
	// 用户信息业务处理
	@Resource(name = "userInfoServiceImpl")
	protected UserInfoService userInfoService;

	/**
	 * 设置流程处理历史Map
	 */
	protected void setProcessHistoryMap() {
		String id = super.getRequest().getParameter("id");
		Map<String, List<CommonWorkflowResult>> map = workOrderWorkflowService
				.getProcessHistoryList(id);
		super.getRequest().setAttribute(PROCESS_HISTORY_MAP, map);
	}

	/**
	 * 返回工单状态Map
	 * 
	 * @return Map<String, String> 工单状态Map
	 */
	protected Map<String, String> getTaskStateMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(WorkOrder.WORKORDER_SIGN_FOR_STATE, "签收");
		map.put(WorkOrder.WORKORDER_REPLY_STATE, "回单");
		map.put(WorkOrder.WORKORDER_REPLY_CHECK_STATE, "验证审核");
		map.put(WorkOrder.WORKORDER_END_STATE, "结束 ");
		map.put(WorkOrder.WORKORDER_CANCELED_STATE, "已取消 ");
		map.put(WorkOrder.WORKORDER_REFUSE_STATE, "退单");
		map.put(WorkOrder.WORKORDER_REFUSE_CONFIRM_STATE, "退单审核");
		return map;
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
	 * 将传入的参数信息存放的Request的属性中
	 */
	public void transferParameterToPage() {
		String id = super.getRequest().getParameter("id");
		WorkOrder workOrder = workOrderDispatchService.view(id);
		super.getRequest().setAttribute("workOrder", workOrder);
		super.getRequest().setAttribute("pid",
				super.getRequest().getParameter("pId"));
		super.getRequest().setAttribute("task_id",
				super.getRequest().getParameter("taskId"));
	}

	/**
	 * 预置列表查询的参数信息
	 * 
	 * @param workOrder
	 *            WorkOrder 传入的查询参数信息
	 * @param userInfo
	 *            UserInfo 当前登录用户信息
	 */
	@SuppressWarnings("rawtypes")
	public void preSetListQuery(WorkOrder workOrder, UserInfo userInfo) {
		if (StringUtils.isNotBlank(workOrder.getCreater())) {
			UserInfo user = userInfoService.getUserInfoByPersonId(workOrder
					.getCreater());
			if (user != null) {
				String createrName = user.getUserName();
				workOrder.setCreaterName(createrName);
			}
		}
		Page page = super.initPage();
		workOrder.setPage(page);
		workOrder.setLoginUser(userInfo);
	}
}