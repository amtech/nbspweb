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
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 工单质量指标统计明细
 * 
 * @author Administrator
 * 
 */
@Namespace("/analysis")
@Results({
		@Result(name = "prossetimelydetail", location = "/analysis/workordertimelyrate_detail.jsp"),
		@Result(name = "approvepassdetail", location = "/analysis/workorderapprovepass_detail.jsp") })
@Action("/workOrderDetailAction")
public class WorkOrderDetailAction extends BaseAction<String, String> {
	private static final long serialVersionUID = 1L;

	/**
	 * 工单处理及时率
	 */
	private static final String SHOW_PROSSETIMELY_DETAIL = "prossetimelydetail";

	/**
	 * 工单审批明细
	 */
	private static final String SHOW_APPROVE_DETAIL = "approvepassdetail";

	/**
	 * 工单处理及时率
	 */
	@Resource(name = "workOrderProcessInTimeRateServiceImpl")
	private WorkOrderProcessInTimeRateService workOrderProcessInTimeRateService;
	/**
	 * 工单审核通过率统计
	 */
	@Resource(name = "workOrderApprovePassedRateServiceImpl")
	private WorkOrderApprovePassedRateService workOrderApprovePassedRateService;
	@Override
	public String getModel() {
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
	 * 工单处理及时率页面
	 * 
	 * @return
	 */
	public String prossetimelydetail() {
		this.getRequest().setAttribute("paramMap",
				convertObjToJsonStr(this.getParameters()));
		return SHOW_PROSSETIMELY_DETAIL;
	}

	/**
	 * 获取g工单处理及时明细
	 */
	@SuppressWarnings("unchecked")
	public void timelyprosseinlist() {
		Page<Map<String, Object>> page = this.initPage();
		convertObjToJson(workOrderProcessInTimeRateService
				.getInTimeWorkOrderList(this.getParameters(), page));
	}

	/**
	 * 获取工单处理超时明细
	 */
	@SuppressWarnings("unchecked")
	public void timelyprosseoverlist() {
		Page<Map<String, Object>> page = this.initPage();
		convertObjToJson(workOrderProcessInTimeRateService
				.getOverTimeWorkOrderList(this.getParameters(), page));
	}

	/**
	 * 工单审批通过率明细页面
	 * 
	 * @return
	 */
	public String approvepassdetail() {
		this.getRequest().setAttribute("paramMap",
				convertObjToJsonStr(this.getParameters()));
		return SHOW_APPROVE_DETAIL;
	}

	/**
	 * 工单通过N次通过明细
	 */
	@SuppressWarnings("unchecked")
	public void ntimespassedlist() {
		Page<Map<String, Object>> page = this.initPage();
		String ntime = this.getParameter("ntime");
		if (StringUtils.isNotBlank(ntime)) {
			convertObjToJson(workOrderApprovePassedRateService
					.getNTimesApprovePassedWorkOrderList(this.getParameters(),
							Integer.valueOf(ntime), page));
		}
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