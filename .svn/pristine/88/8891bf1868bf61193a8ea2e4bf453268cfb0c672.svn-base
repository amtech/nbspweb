package com.cabletech.business.desktop.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.desktop.service.DesktopService;
import com.cabletech.business.desktop.service.LeavePersonStatisticService;
import com.cabletech.business.desktop.service.OnlineManService;
import com.cabletech.business.desktop.service.PatrolPlanSeivice;
import com.cabletech.business.workflow.common.service.WorkflowWaitHandledService;
import com.cabletech.business.workflow.workorder.service.WorkOrderStatisticService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.externalresources.ExternalResourcesAccessService;

/**
 * 桌面处理Action
 * 
 * @author 杨隽 2011-11-29 创建
 * @author 杨隽 2011-12-13 添加代维单位人员图表显示方法
 * 
 */
@Namespace("/desktop")
@Results({
		@Result(name = "index", location = "/frames/default/welcome3.jsp"),
		@Result(name = "overtimetaskmore", location = "/frames/default/more/overtime_task_more_list.jsp"),
		@Result(name = "equipmore", location = "/frames/default/more/contractor_res_equip_list.jsp"),
		@Result(name = "notice", location = "/frames/default/more/welcome_notice.jsp"),
		@Result(name = "resequip", location = "/frames/default/more/welcome_resqeuip.jsp"),
		@Result(name = "workorder", location = "/frames/default/more/welcome_workorder.jsp"),
		@Result(name = "onlineman", location = "/frames/default/more/welcome_onlineman.jsp")
})
@Action("/desktop")
public class DesktopAction extends BaseAction<String, String> {
	private static final long serialVersionUID = 1L;

	@Resource(name = "desktopServiceImpl")
	private DesktopService desktopService;

	@Resource(name = "workOrderStatisticServiceImpl")
	private WorkOrderStatisticService workOrderStatisticService;

	@Resource(name = "workflowWaitHandledServiceImpl")
	private WorkflowWaitHandledService workflowWaitHandledService;

	@Resource(name = "leavePersonStatisticServiceImpl")
	private LeavePersonStatisticService leavePersonStatisticService;
	@Resource(name = "onlineManServiceImpl")
	private OnlineManService onlineManService;
	/**
	 * 巡检计划服务
	 */
	@Resource(name = "patrolPlanSeiviceImpl")
	private PatrolPlanSeivice patrolPlanSeivice;

	@Resource(name = "externalResourcesAccessService")
	private ExternalResourcesAccessService externalResourcesService;

	/**
	 * 获取统计数据
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getNumber() {
		UserInfo userInfo = super.getUser();
		Map map = workOrderStatisticService
				.getWorkOrderStatisticResultMap(userInfo);
		logger.info(map);
		desktopService.getOvertimeWorkOrderAndFaultNumberList(userInfo);
		logger.info(workflowWaitHandledService
				.getWorkflowWaitHandledNumber(userInfo));
		// map=desktopService.getWaitHandledTasksNumber();
		logger.info(map);
		map = leavePersonStatisticService
				.getLeavePersonStatisticResultMap(userInfo);
		logger.info(map);
		return null;
	}

	/**
	 * 获取公告、通知
	 */
	public String getinformation() {
		String type = this.getParameter("type");
		desktopService.setUserInfo(this.getUser());
		List<Map<String, Object>> informationList = desktopService
				.getLatestNoticeList(type, SysConstant.MAX_INFORMATION_COUNT);
		logger.info("公告列表信息已执行：");
		super.getRequest().setAttribute("informationList", informationList);
		return "notice";

	}

	/**
	 * 登录首页信息
	 * 
	 * @return
	 */
	public String index() {
		this.getRequest().setAttribute("ordermonth", new Date());
		// 工单图表
		// getWorkChart();
		// 获取会议信息
		// getMeetInfo();
		return "index";
	}

	/**
	 * 获取会议列表
	 */
	private void getMeetInfo() {
		desktopService.setUserInfo(super.getUser());
		int meetNumber = desktopService.getTodayMeetNumber();
		List<String> meetDatelist = desktopService.getMeetDateList();
		String meetDateStr = "'";
		if (null != meetDatelist && meetDatelist.size() > 0) {
			for (int i = 0; i < meetDatelist.size(); i++) {
				meetDateStr += meetDatelist.get(i).toString() + "','";
			}
		}
		if (meetDateStr.length() > 1) {
			meetDateStr = meetDateStr.substring(0, meetDateStr.length() - 2);
		} else {
			meetDateStr = "";
		}
		super.getRequest().setAttribute("meet_date_list", meetDateStr);
		super.getRequest().setAttribute("today_meet_num", meetNumber + "");
	}

	/**
	 * 代维资源配备 --更多列表
	 * 
	 * @return
	 */
	public String contractorResEquipMoreList() {
		String objectName = "组织";
		if (getUser().isProvinceMobile())
			objectName = "区域";
		getRequest().setAttribute("objectName", objectName);
		getRequest().setAttribute("resequiplist",
				desktopService.getContractorResEquipList(getUser()));
		return "equipmore";
	}

	/**
	 * 超时工作流的更多列表
	 * 
	 * @return
	 */
	public String overtimeTaskMoreList() {
		super.getRequest().setAttribute(
				"workorder",
				desktopService.getOvertimeWorkOrderAndFaultNumberList(this
						.getUser()));
		super.getRequest().setAttribute("ordermonth", new Date());
		return "overtimetaskmore";
	}

	/**
	 * 退出Action
	 */
	public void logout() {
		try {
			String url = externalResourcesService.getCaslogoutredirect();
			super.getResponse().sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	/**
	 * 获取巡检计划
	 */
	public void getPatrolPlan() {
		Map<String, Object> map = new HashMap<String, Object>();

		String timetype = this.getParameter("timetype");
		if (StringUtils.isBlank(timetype)) {
			timetype = "";
		}

		map = patrolPlanSeivice.getPatrolRate(this.getUser(), timetype);
		this.convertObjToJson(map);
	}

	/**
	 * 获取工单图表
	 */
	private void getWorkChart() {
		this.getRequest().setAttribute(
				"workchart",
				workOrderStatisticService.getWorkOrderStatisticResultMap(this
						.getUser()));

	}

	/**
	 * 获取工单数据
	 * 
	 * @return
	 */
	public String getWorkOrder() {
		this.getRequest().setAttribute(
				"workorder",
				desktopService.getOvertimeWorkOrderAndFaultNumberList(this
						.getUser()));
		return "workorder";

	}

	/**
	 * 获取代维人员图表
	 * 
	 * @throws IOException
	 */
	public void getContractorPersonChart() throws IOException {
		outPrint(desktopService.getContractorPersonChartData(this.getUser()),
				true);
	}

	/**
	 * 代维资源配备
	 */
	public String getContractorResEquipList() {
		this.getRequest().setAttribute("resequiplist",
				desktopService.getContractorResEquipList(getUser()));
		return "resequip";
	}

	/**
	 * 获取待办数量
	 */
	private void gettasknum() {
		this.getRequest().setAttribute(
				"tasknum",
				workflowWaitHandledService.getWorkflowWaitHandledNumber(this
						.getUser()));
	}

	/**
	 * 获取在线人员树
	 */
	public String getonlinemantree() {
		List<Map<String, Object>> onlinemanList = onlineManService
				.getOnlineManTreeList(this.getUser());
		String jsonstr = JSON.toJSONString(onlinemanList);
		
		super.getRequest().getSession().setAttribute("onlinemanJson", jsonstr);
		return "onlineman";
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
