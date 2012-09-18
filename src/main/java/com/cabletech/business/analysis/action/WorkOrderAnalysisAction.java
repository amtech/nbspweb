package com.cabletech.business.analysis.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.analysis.service.WorkOrderApprovePassedRateService;
import com.cabletech.business.analysis.service.WorkOrderProcessInTimeRateService;
import com.cabletech.business.analysis.service.WorkOrderProcessOverTimeRateService;
import com.cabletech.business.analysis.service.WorkOrderProcessTimeLengthService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 工单质量指标统计
 * 
 * @author Administrator
 * 
 */
@Namespace("/analysis")
@Results({ @Result(name = "prossetimelyrate", location = "/analysis/workordertimelyrate_analysis.jsp"),
		@Result(name = "overtimerate", location = "/analysis/workorderovertimerate_analysis.jsp"),
		@Result(name = "processtime", location = "/analysis/workorderprocesstime_analysis.jsp"),
		@Result(name = "approvepass", location = "/analysis/workorderapprovepass_analysis.jsp") })
@Action("/workOrderAnalysisAction")
public class WorkOrderAnalysisAction extends BaseAction<String, String> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 工单处理及时率
	 */
	private static final String SHOW_PROSSETIMELY_RATE = "prossetimelyrate";
	/**
	 * 超时工单统计
	 */
	private static final String SHOW_OVERTIME_RATE = "overtimerate";

	/**
	 * 工单历时
	 */
	private static final String SHOW_PROCESS_TIME= "processtime";

	/**
	 * 工单审批
	 */
	private static final String SHOW_APPROVE_PASS= "approvepass";
	/**
	 * 工单处理及时率
	 */
	@Resource(name = "workOrderProcessInTimeRateServiceImpl")
	private WorkOrderProcessInTimeRateService workOrderProcessInTimeRateService;
	/**
	 * 超时工单统计
	 */
	@Resource(name = "workOrderProcessOverTimeRateServiceImpl")
	private WorkOrderProcessOverTimeRateService workOrderProcessOverTimeRateService;
	/**
	 * 工单历时统计
	 */
	@Resource(name = "workOrderProcessTimeLengthServiceImpl")
	private WorkOrderProcessTimeLengthService workOrderProcessTimeLengthService;
	/**
	 * 工单审核通过率统计
	 */
	@Resource(name = "workOrderApprovePassedRateServiceImpl")
	private WorkOrderApprovePassedRateService workOrderApprovePassedRateService;

	@Override
	public String getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * 工单质量指标统计列表
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String test() {
		Map<String, String> map = new HashMap<String, String>();
		UserInfo user = this.getUser();
		Page page = super.initPage();
		map.put("regionId", user.getRegionId());
		// 工单处理及时率列表
		workOrderProcessInTimeRateService
				.getWorkOrderProcessInTimeMainRateList(map);
		// 及时处理工单列表
		workOrderProcessInTimeRateService.getInTimeWorkOrderList(map, page);
		// 超时处理工单列表
		workOrderProcessInTimeRateService.getOverTimeWorkOrderList(map, page);
		// 超时工单统计列表
		workOrderProcessOverTimeRateService
				.getWorkOrderProcessOverTimeMainRateList(map);
		// 工单历时统计列表
		workOrderProcessTimeLengthService
				.getWorkOrderProcessMainTimeLengthList(map);
		// 工单审核通过率列表
		workOrderApprovePassedRateService
				.getWorkOrderApprovePassedRateList(map);
		// 一次审核通过工单列表
		workOrderApprovePassedRateService.getNTimesApprovePassedWorkOrderList(
				map, 1, page);
		// 二次审核通过工单列表
		workOrderApprovePassedRateService.getNTimesApprovePassedWorkOrderList(
				map, 2, page);
		return null;
	}
	/**
	 * 工单处理及时率页面
	 * @return
	 */
	public String prossetimelyrate(){
		return SHOW_PROSSETIMELY_RATE;
	}
	/**
	 * 工单处理及时率数据
	 */
	public void prossetimelyratelist(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", workOrderProcessInTimeRateService.getWorkOrderProcessInTimeMainRateList(getParameters()));
		convertObjToJson(data);
	}
	
	/**
	 * 超时工单页面
	 * @return
	 */
	public String overtimerate(){
		return SHOW_OVERTIME_RATE;
	}
	/**
	 * 超时工单数据
	 */
	public void overtimeratelist(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root", workOrderProcessOverTimeRateService.getWorkOrderProcessOverTimeMainRateList(getParameters()));
		convertObjToJson(data);
	}
	
	/**
	 * 工单历时页面
	 * @return
	 */
	public String processtime(){
		return SHOW_PROCESS_TIME;
	}
	/**
	 * 工单历时数据
	 */
	public void processtimelist(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root",workOrderProcessTimeLengthService.getWorkOrderProcessMainTimeLengthList(getParameters()));
		convertObjToJson(data);
	}
	/**
	 * 工单审批通过率页面
	 * @return
	 */
	public String approvepass(){
		return SHOW_APPROVE_PASS;
	}
	/**
	 * 工单审批通过率数据
	 */
	public void approvepasslist(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("root",workOrderApprovePassedRateService.getWorkOrderApprovePassedRateList(getParameters()));
		convertObjToJson(data);
	}
	
	/**
	 * 获取查询参数
	 */
	public Map<String, String> getParameters() {
		Map<String, String> map = new HashMap<String, String>();
		UserInfo user = this.getUser();
		if (StringUtils.isNotBlank(this.getParameter("orgId"))) {
			map.put("orgId", this.getParameter("orgId"));
		} else {
			if (user.isContractor()) {
				map.put("orgId", user.getOrgId());
			}
		}
		map.put("regionId", user.getRegionId());
		map.put("startTime", this.getParameter("startTime"));
		map.put("endTime", this.getParameter("endTime"));
		map.put("taskType", this.getParameter("taskType"));
		return map;
	}

}